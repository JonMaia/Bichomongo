package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;

public interface Neo4JUbicacionDao {

    void create(Ubicacion ubicacion);

    boolean existe(Ubicacion ubicacion);

    // TODO: VERIFICAR SI TODOAS LAS FIRMAS DE LOS METODOS COINCIDEN CON LAS DE UbicacionDao Y SI ES ASI DEJAR UNA SOLA INTERFAZ, SINO BUSCAR UN NOMBRE QUE NO HAGA REFERENCIA A LA IMPLEMENTACION

}
