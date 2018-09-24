package ar.edu.unq.epers.bichomon.backend.service;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateNivelDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.Entrenador.EntrenadorService;
import ar.edu.unq.epers.bichomon.backend.service.Entrenador.EntrenadorServiceImple;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieServiceImpl;

/**
 * Esta clase es un singleton, el cual sera utilizado por equipo de frontend
 * para hacerse con implementaciones a los servicios.
 * 
 * @author Steve Frontend
 *
 */
public class ServiceFactory {
	
	/**
	 * @return un objeto que implementa {@link EspecieService}
	 */
	public EspecieService getEspecieService() {
		return new EspecieServiceImpl(new HibernateEspecieDaoImple());
	}

	public EntrenadorService getEntrenadorService() {
		return new EntrenadorServiceImple(new HibernateEntrenadorDaoImple(Entrenador.class));
	}

	/**
	 * @return un objeto que implementa {@link DataService}
	 */
	public DataService getDataService() { return new DataServiceImpl(new HibernateEspecieDaoImple(), new HibernateNivelDaoImple());	}

}
