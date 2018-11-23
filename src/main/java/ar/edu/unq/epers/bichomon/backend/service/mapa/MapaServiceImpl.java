package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.dao.*;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateDojoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.Neo4JImple.Neo4JUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.mongoImple.EventoDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Arribo;
import ar.edu.unq.epers.bichomon.backend.model.exception.CaminoMuyCostosoException;
import ar.edu.unq.epers.bichomon.backend.model.exception.UbicacionMuyLejanaException;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.joda.time.LocalDate;
import org.neo4j.driver.v1.Record;

import java.util.ArrayList;
import java.util.List;

public class MapaServiceImpl implements MapaService {

    private EntrenadorDao entrenadorDao;
    private UbicacionDao ubicacionDao;
    private Neo4JUbicacionDao neo4JUbicacionDao;
    private DojoDao dojoDao;
    private EventoDao eventoDao;

    public MapaServiceImpl() {
        this.entrenadorDao = new HibernateEntrenadorDaoImple();
        this.ubicacionDao = new HibernateUbicacionDaoImple();
        this.dojoDao = new HibernateDojoDaoImple();
        this.neo4JUbicacionDao = new Neo4JUbicacionDaoImple();
        this.eventoDao = new EventoDaoImple();
    }

    @Override
    public void mover(String nombreEntrenador, String nombreUbicacion)throws RuntimeException {

        Runner.runInSession(() -> {

            Entrenador entrenador = entrenadorDao.getById(nombreEntrenador);
            Ubicacion ubicacion = ubicacionDao.getById(nombreUbicacion);
            if(!entrenador.getUbicacion().getNombre().equals(ubicacion.getNombre())) {
                Integer costo = neo4JUbicacionDao.getPrecioCaminoCorto(entrenador.getUbicacion().getNombre(), nombreUbicacion);
                if (costo == null) {
                    throw new UbicacionMuyLejanaException("");
                }
                if (costo > entrenador.getBilletera()) {
                    throw new CaminoMuyCostosoException("");
                }
                entrenador.setBilletera(entrenador.getBilletera() - costo);
                entrenador.moverA(ubicacion);
                crearEventoDeArribo(ubicacion,entrenador);
                entrenadorDao.actualizar(entrenador);

            }
            return null;
        });
    }

    private void crearEventoDeArribo(Ubicacion ubicacion, Entrenador entrenador) {
        Arribo arribo = new Arribo();
        arribo.setEntrenador(entrenador.getNombre());
        arribo.setUbicacion(ubicacion.getNombre());
        arribo.setFecha(LocalDate.now());
        eventoDao.save(arribo);
    }

    @Override
    public int cantidadEntrenadores(String ubicacion) {
        /*se deberá devolver la cantidad de entrenadores que se encuentren actualmente en dicha localización.*/
        return Runner.runInSession(() -> {
            Ubicacion u = ubicacionDao.getById(ubicacion);
            return u.getEntrenadores().size();
        });
    }

    @Override
    public Bicho campeon(String dojo) {
        /*retorna el actual campeon del Dojo especificado.*/
        return Runner.runInSession(() -> {
            Dojo d = dojoDao.getById(dojo);
            return (d.getCampeon() != null ? d.getCampeon().getBicho(): null);
        });
    }

    @Override
    public Bicho campeonHistorico(String dojo) {
        /*retorna el bicho que haya sido campeon por mas tiempo en el Dojo.*/
        return Runner.runInSession(() -> this.dojoDao.getCampeonHistorico(dojo));
    }

    @Override
    public void crearUbicacion(Ubicacion ubicacion){
            Runner.runInSession(() -> {
            ubicacionDao.guardar(ubicacion);
            return true;
        });
            neo4JUbicacionDao.create(ubicacion);
    }

    @Override
    public void conectar(String ubicacion1, String ubicacion2, TipoDeCamino tipoCamino){
        neo4JUbicacionDao.conectar(ubicacion1, ubicacion2, tipoCamino);
    }

    @Override
    public List<Ubicacion> conectados(String ubicacion, String tipoCamino){
        List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();

        List<Record> nombresDeUbicaciones = neo4JUbicacionDao.conectados(ubicacion, tipoCamino);

        for (Record r:nombresDeUbicaciones) {
            ubicaciones.add(
                    Runner.runInSession(() ->this.ubicacionDao.recuperar( r.values().get(0).asString()))
                    );
        }

        return ubicaciones;
    }

    @Override
    public List<Ubicacion> todosLosConectados(String ubicacion){
        List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();

        Aereo aereo = new Aereo();
        Terrestre terrestre = new Terrestre();
        Maritimo maritimo = new Maritimo();

        List<TipoDeCamino> tiposDeCamino = new ArrayList<TipoDeCamino>(){{
            add(aereo);
            add(terrestre);
            add(maritimo);
        }};

        List<Record> nombresDeUbicaciones = new ArrayList<>();

        for (TipoDeCamino tc: tiposDeCamino) {
            nombresDeUbicaciones.addAll(neo4JUbicacionDao.conectados(ubicacion, tc.getTipo()));
        }

        for (Record r:nombresDeUbicaciones) {
            ubicaciones.add(
                    Runner.runInSession(() ->this.ubicacionDao.recuperar( r.values().get(0).asString()))
            );
        }

        return ubicaciones;

    }

    @Override
    public void moverMasCorto(String nombreEntrenador, String destino) {
        Runner.runInSession(() -> {
                    Entrenador entrenador = entrenadorDao.getById(nombreEntrenador);

                    Ubicacion ubicacion = ubicacionDao.getById(destino);

        Integer costo = this.neo4JUbicacionDao.costoCaminoMasCorto(entrenador.getUbicacion().getNombre(),destino);
        Integer recurso = entrenador.getBilletera();
        if (costo == null) {
            throw new UbicacionMuyLejanaException("");
        }
        if(costo > recurso)
            throw new CaminoMuyCostosoException("necesitas " + (costo - recurso) + " para llegar" );
        entrenador.setBilletera(recurso - costo);
        entrenador.moverA(ubicacion);
        entrenadorDao.actualizar(entrenador);
        return null;
        });
    }

    @Override
    public boolean existeUbicacion(String pueblo) {
        return Runner.runInSession(() -> this.neo4JUbicacionDao.existeUbicacion(pueblo));
    }
}
