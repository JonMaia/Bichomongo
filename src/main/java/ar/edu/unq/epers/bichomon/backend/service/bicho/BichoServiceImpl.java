package ar.edu.unq.epers.bichomon.backend.service.bicho;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;

public class BichoServiceImpl {

    private HibernateBichoDaoImple bichoDAO;
    private HibernateEntrenadorDaoImple entrenadorDAO;

    public BichoServiceImpl (HibernateBichoDaoImple bichoDAO, HibernateEntrenadorDaoImple entrenadorDAO){
        this.bichoDAO = bichoDAO;
        this.entrenadorDAO = entrenadorDAO;
    }

   // Bicho buscar (String entrenador){

    //  Entrenador trainer = this.entrenadorDAO.getById(entrenador);
    //     return trainer.buscar();

    //}
}
