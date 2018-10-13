package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;

import java.util.List;

public interface EntrenadorDao extends GenericDao<Entrenador, String> {

    List<Entrenador> conMejoresBichos();
}
