package ar.edu.unq.epers.bichomon.backend.service.Bicho;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;

public class BichoServiceImpl {

    private HibernateBichoDAO bichoDAO;
    private HibernateEntrenadorDAO entrenadorDAO;

    public BichoServiceImpl (HibernateBichoDAO bichoDAO, HibernateEntrenadorDAO entrenadorDAO){
        this.bichoDAO = bichoDAO;
        this.entrenadorDAO = entrenadorDAO;
    }

    Bicho buscar (String entrenador){
        Runner.runInSession(() -> {
            Entrenador entrenador = this.entrenadorDAO.recuperar(entrenador);
            //return entrenador.buscar();
            return true;
        }

    }
}
