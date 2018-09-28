package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;

public class BichoServiceImpl {

    private HibernateBichoDaoImple bichoDAO;
    private HibernateEntrenadorDaoImple entrenadorDAO;

    public BichoServiceImpl (HibernateBichoDaoImple bichoDAO, HibernateEntrenadorDaoImple entrenadorDAO){
        this.bichoDAO = bichoDAO;
        this.entrenadorDAO = entrenadorDAO;
    }

    //bicho buscar (String entrenador){
    //   Runner.runInSession(() -> {
    //       //entrenador entrenador = this.entrenadorDAO.recuperar(entrenador);
    //       //return entrenador.buscar();
    //  }
    //   }
}
