package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.dao.EventoDao;
import ar.edu.unq.epers.bichomon.backend.dao.GenericMongoDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.mongoImple.EventoDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Abandono;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Arribo;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Coronacion;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Evento;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.joda.time.LocalDate;

import java.util.List;

public class BichoServiceImpl implements BichoService{

    private HibernateBichoDaoImple bichoDAO;
    private HibernateEntrenadorDaoImple entrenadorDAO;
    private EventoDao eventoDao;

    public BichoServiceImpl() {
        this.bichoDAO = new HibernateBichoDaoImple();
        this.entrenadorDAO = new HibernateEntrenadorDaoImple();
        this.eventoDao = new EventoDaoImple();
    }

    @Override
    public Bicho buscar(String entrenador) {
        return Runner.runInSession(() -> {
            Entrenador trainer = this.entrenadorDAO.getById(entrenador);
            Bicho bicho = trainer.buscarBicho();
            crearEventoDeAbandono(trainer.getUbicacion().getNombre(), trainer.getNombre(), bicho.getId());
            entrenadorDAO.actualizar(trainer);
            return bicho;
        });
    }

    private void crearEventoDeAbandono(String ubicacion, String entrenador, Integer bicho) {
        Abandono abandono = new Abandono();
        abandono.setEntrenador(entrenador);
        abandono.setUbicacion(ubicacion);
        abandono.setFecha(LocalDate.now());
        abandono.setBicho(bicho);
        eventoDao.save(abandono);
    }

    @Override
    public void abandonar(String entrenador, int bicho) {
        Runner.runInSession(() -> {
            Entrenador trainer = this.entrenadorDAO.getById(entrenador);
            Bicho bichomon = this.bichoDAO.getById(bicho);
            trainer.abandonarBicho(bichomon);
            entrenadorDAO.actualizar(trainer);
            return null;
        });
    }

    @Override
    public ResultadoCombate duelo(String entrenador, int bicho)  {
        return Runner.runInSession(()  -> {
            Entrenador trainer = this.entrenadorDAO.getById(entrenador);
            Bicho bichomon = this.bichoDAO.getById(bicho);
            ResultadoCombate resultadoCombate = null;
            resultadoCombate = trainer.iniciarDuelo(bichomon);
            entrenadorDAO.actualizar(trainer);
            if(resultadoCombate.getGanadorCombate() == bichomon){
                crearEventoDeCoronacion(bichomon.getEntrenador(), trainer);
            }else{
                crearEventoDeCoronacion(trainer, bichomon.getEntrenador());
            }
            return resultadoCombate;
        });
    }

    private void crearEventoDeCoronacion(Entrenador entrenador, Entrenador trainer) {
        Coronacion coronacion = new Coronacion();
        coronacion.setEntrenador(entrenador.getNombre());
        coronacion.setPerdedor(trainer.getNombre());
        coronacion.setFecha(LocalDate.now());
        eventoDao.save(coronacion);
    }

    @Override
    public boolean puedeEvolucionar(int bicho) {
        return Runner.runInSession(() -> bichoDAO.getById(bicho).puedeEvolucionar());
    }

    @Override
    public Bicho evolucionar(int bicho) {
        return Runner.runInSession(() -> {
            Bicho bichomon = this.bichoDAO.getById(bicho);
            if(bichomon.puedeEvolucionar()){
                bichomon = bichomon.evolucionar();
                bichomon.getEntrenador().ganarExperienciaPorEvolucion();
            }
            bichoDAO.actualizar(bichomon);
            return bichomon;
        });
    }
}
