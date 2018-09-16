package ar.edu.unq.epers.bichomon.backend.service.Bicho;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoHibernateDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorHibernateDAO;

public class BichoServiceImpl {

    private HibernateBichoHibernateDAO bichoDAO;
    private HibernateEntrenadorHibernateDAO entrenadorDAO;

    public BichoServiceImpl (HibernateBichoHibernateDAO bichoDAO, HibernateEntrenadorHibernateDAO entrenadorDAO){
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
