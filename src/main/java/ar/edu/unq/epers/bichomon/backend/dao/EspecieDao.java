package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Especie;


public interface EspecieDao extends GenericDao<Especie, Integer> {
	
	void guardar(Especie especie);

	Boolean guardarValidado(Especie especie);

    void actualizar(Especie especie);

	Especie recuperar(String nombreEspecie);

	void eliminarEspecies();

}
