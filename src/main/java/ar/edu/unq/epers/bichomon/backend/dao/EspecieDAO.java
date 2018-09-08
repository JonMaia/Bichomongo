package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

import java.util.List;

public interface EspecieDAO {
	
	void guardar(Especie especie);

    void actualizar(Especie especie);

	Especie recuperar(String nombreEspecie);

	Especie getById(Integer id);

	List<Especie> recuperarTodos();

	void eliminarEspecies();

}
