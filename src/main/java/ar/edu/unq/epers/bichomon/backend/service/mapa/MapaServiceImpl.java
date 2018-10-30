package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.dao.DojoDao;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateDojoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

public class MapaServiceImpl implements MapaService {

    private EntrenadorDao entrenadorDao;
    private UbicacionDao ubicacionDao;
    private DojoDao dojoDao;

    public MapaServiceImpl() {
        this.entrenadorDao = new HibernateEntrenadorDaoImple();
        this.ubicacionDao = new HibernateUbicacionDaoImple();
        this.dojoDao = new HibernateDojoDaoImple();
    }

    @Override
    public void mover(String nombreEntrenador, String nombreUbicacion) {

        Runner.runInSession(() -> {
            Entrenador entrenador = entrenadorDao.getById(nombreEntrenador);
            Ubicacion ubicacion = ubicacionDao.getById(nombreUbicacion);
            entrenador.moverA(ubicacion);
            entrenadorDao.actualizar(entrenador);
            return null;
        });
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
}
