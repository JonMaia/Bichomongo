package ar.edu.unq.epers.bichomon.backend.service.Bicho;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;

public class BichoServiceImpl {

    private HibernateBichoDaoImple bichoDAO;
    private HibernateEntrenadorDaoImple entrenadorDAO;

    public BichoServiceImpl (HibernateBichoDaoImple bichoDAO, HibernateEntrenadorDaoImple entrenadorDAO){
        this.bichoDAO = bichoDAO;
        this.entrenadorDAO = entrenadorDAO;
    }

    //Bicho buscar (String entrenador){
    //   Runner.runInSession(() -> {
    //       //Entrenador entrenador = this.entrenadorDAO.recuperar(entrenador);
    //       //return entrenador.buscar();
    //  }
    //   }
}
