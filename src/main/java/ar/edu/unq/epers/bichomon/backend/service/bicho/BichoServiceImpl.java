package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Combate;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

public class BichoServiceImpl implements BichoService{

    private HibernateBichoDaoImple bichoDAO;
    private HibernateEntrenadorDaoImple entrenadorDAO;

    public BichoServiceImpl (HibernateBichoDaoImple bichoDAO, HibernateEntrenadorDaoImple entrenadorDAO){
        this.bichoDAO = bichoDAO;
        this.entrenadorDAO = entrenadorDAO;
    }

    public BichoServiceImpl() {
        this.bichoDAO = new HibernateBichoDaoImple();
        this.entrenadorDAO = new HibernateEntrenadorDaoImple();
    }

    @Override
    public Bicho buscar(String entrenador) {
        Entrenador trainer = this.entrenadorDAO.getById(entrenador);
        //return trainer.buscarBicho();
        return null;
    }

    @Override
    public void abandonar(String entrenador, int bicho) {
        Entrenador trainer = this.entrenadorDAO.getById(entrenador);
        Bicho bichomon = this.bichoDAO.getById(bicho);
        trainer.abandonarBicho(bichomon);
    }

    @Override
    public Combate duelo(String entrenador, int bicho) {
        return null;
    }

    @Override
    public boolean puedeEvolucionar(int bicho) {
        return Runner.runInSession(() -> bichoDAO.getById(bicho).puedeEvolucionar());
    }

    @Override
    public Bicho evolucionar(int bicho) {
        Bicho bichomon = this.bichoDAO.getById(bicho);
        if(bichomon.puedeEvolucionar()){
            bichomon = bichomon.evolucionar();
        }
        return bichomon;
    }
}
