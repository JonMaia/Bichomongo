package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.exception.UbicacionIncorrectaException;
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
            entrenadorDAO.actualizar(trainer);
            return trainer.buscarBicho();

        });
    }

    @Override
    public void abandonar(String entrenador, int bicho) {
        Entrenador trainer = this.entrenadorDAO.getById(entrenador);
        Bicho bichomon = this.bichoDAO.getById(bicho);
        trainer.abandonarBicho(bichomon);
        entrenadorDAO.actualizar(trainer);
    }

    @Override
    public ResultadoCombate duelo(String entrenador, int bicho)  {
        return Runner.runInSession(()  -> {
            Entrenador trainer = this.entrenadorDAO.getById(entrenador);
            Bicho bichomon = this.bichoDAO.getById(bicho);
            ResultadoCombate resultadoCombate = null;
            try {
                resultadoCombate = trainer.iniciarDuelo(bichomon);
            } catch (UbicacionIncorrectaException e) {
                e.printStackTrace();
            }
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
                bichoDAO.guardar(bichomon);
            }
            bichoDAO.actualizar(bichomon);
            return bichomon;
        });
    }
}
