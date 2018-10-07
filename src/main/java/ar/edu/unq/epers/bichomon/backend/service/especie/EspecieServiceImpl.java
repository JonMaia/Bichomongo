package ar.edu.unq.epers.bichomon.backend.service.especie;

import java.util.*;
import java.util.stream.Collectors;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDao;
import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDaoImple;
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

	@Override
	public List<Especie> populares() {
		return Runner.runInSession(() -> {
		    List<Bicho> bichosConEntrenador = this.bichoDAO.recuperarTodosConEntrenador();

		    Map<Especie,Integer> especiesConCantDeBichos = new HashMap<>();

            for (Bicho bicho: bichosConEntrenador) {
                if(!especiesConCantDeBichos.containsKey(bicho.getEspecie()))
                    especiesConCantDeBichos.put(bicho.getEspecie(),0);

                especiesConCantDeBichos.put(bicho.getEspecie(),especiesConCantDeBichos.get(bicho.getEspecie())+1);
            }

            List<Map.Entry<Especie, Integer>> especiesConCantDeBichosDesordenadas = new ArrayList<>(especiesConCantDeBichos.entrySet());

            especiesConCantDeBichosDesordenadas.sort(new Comparator<Map.Entry<Especie, Integer>>() {
                @Override
                public int compare(Map.Entry<Especie, Integer> o1, Map.Entry<Especie, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            List<Especie> especiesOrdenadas = especiesConCantDeBichosDesordenadas.stream().limit(10).map(Map.Entry::getKey).collect(Collectors.toList());

            return especiesOrdenadas;
		});
	}

	@Override
	public List<Especie> impopulares() {
		//TODO: IMPLEMENTAR
		throw new RuntimeException("PENDIENTE IMPLEMENTACION");
	}

}
