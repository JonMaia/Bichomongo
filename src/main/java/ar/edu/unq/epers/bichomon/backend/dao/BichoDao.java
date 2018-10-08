package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;

import java.util.List;

public interface BichoDao extends GenericDao<Bicho, Integer> {

    List<Bicho> recuperarTodosConEntrenador();

    List<Bicho> recuperarTodosSinEntrenador();

}
