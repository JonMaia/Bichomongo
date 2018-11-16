package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

public class BichoServiceImpl implements BichoService{

    private HibernateBichoDaoImple bichoDAO;
    private HibernateEntrenadorDaoImple entrenadorDAO;

    public BichoServiceImpl() {
        this.bichoDAO = new HibernateBichoDaoImple();
        this.entrenadorDAO = new HibernateEntrenadorDaoImple();
    }

    @Override
    public Bicho buscar(String entrenador) {
        return Runner.runInSession(() -> {
            Entrenador trainer = this.entrenadorDAO.getById(entrenador);
            Bicho bicho = trainer.buscarBicho();
            entrenadorDAO.actualizar(trainer);
            return bicho;
        });
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
            return resultadoCombate;
        });
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
