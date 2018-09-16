package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Entrenador.Entrenador;

public interface EntrenadorDao extends GenericDAO<Entrenador, String>{

    void setUbicacionDAO(String ubicacion);

    void setNombreDAO(String entrenador);

}
