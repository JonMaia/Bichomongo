package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;

public interface EntrenadorDao extends GenericDao<Entrenador, String> {

    Nivel getNivel(Entrenador entrenador);
}
