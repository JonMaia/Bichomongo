package ar.edu.unq.epers.bichomon.backend.service.especie;

import java.util.*;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDao;
import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateEspecieDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;


public class EspecieServiceImpl implements EspecieService {

	private EspecieDao especieDAO;
    private BichoDao bichoDAO;

    public EspecieServiceImpl(HibernateEspecieDaoImple dao){
		this.especieDAO = dao;
	}

    public EspecieServiceImpl() {
        this.especieDAO = new HibernateEspecieDaoImple();
        this.bichoDAO = new HibernateBichoDaoImple();
    }


    @Override
	public void crearEspecie(Especie especie){
			especieDAO.guardar(especie);
	}
	

	@Override
	public Especie getEspecie(String nombreEspecie){
		Especie especie = especieDAO.recuperar(nombreEspecie);
		if(especie == null){
			throw new EspecieNoExistente(nombreEspecie);
		}
		return especie;
	}


	@Override
	public List<Especie> getAllEspecies(){
		return especieDAO.recuperarTodos();
	}


	@Override
	public Bicho crearBicho(String nombreEspecie, String nombreBicho){
		Especie especie = especieDAO.recuperar(nombreEspecie);
		Bicho bicho = especie.crearBicho();
		especieDAO.actualizar(especie);
		return bicho;
	}

	@Override
	public void evolucionar() {

	}

	/**
	 * retorna aquellos diez especies mas populares, o sea, aquellas que tengan mas bichos en manos de distintos entrenadores. No contaran los bichos en la Guardería.
	 * @return
	 */
	@Override
	public List<Especie> populares() {
		return Runner.runInSession(() -> this.especieDAO.getPopulares());
	}

    /**
     * retorna aquellos diez especies menos populares, o sea, aquellas que tengan actualmente mas bichos en la Guardería.
     * @return
     */
	@Override
	public List<Especie> impopulares() {
		return Runner.runInSession(() -> this.especieDAO.getImpopulares());
	}

}
