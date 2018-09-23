package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Especie;

import java.util.List;


public interface EspecieDAO extends GenericDAO<Especie, Integer>{
	
	void guardar(Especie especie);

	Boolean guardarValidado(Especie especie);

    void actualizar(Especie especie);

	Especie recuperar(String nombreEspecie);

	void eliminarEspecies();

}
