package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

import java.util.List;

public interface EspecieDAO extends GenericDAO<Especie, Integer>{

	Especie recuperar(String nombreEspecie);

	void eliminarEspecies();

	List<Especie> recuperarTodos();

}
