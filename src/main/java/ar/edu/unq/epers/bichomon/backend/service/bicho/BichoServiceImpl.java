package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Combate;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;

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
        return null;
    }

    @Override
    public void abandonar(String entrenador, int bicho) {

    }

    @Override
    public Combate duelo(String entrenador, int bicho) {
        return null;
    }

    @Override
    public boolean puedeEvolucionar(String entrenador, int bicho) {
        return false;
    }

    @Override
    public Bicho evolucionar(String entrenador, int bicho) {
        return null;
    }

    //bicho buscar (String entrenador){
    //   Runner.runInSession(() -> {
    //       //entrenador entrenador = this.entrenadorDAO.recuperar(entrenador);
    //       //return entrenador.buscar();
    //  }
    //   }
}
