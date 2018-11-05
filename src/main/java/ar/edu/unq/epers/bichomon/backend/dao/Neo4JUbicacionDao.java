package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;

public interface Neo4JUbicacionDao {
    // TODO: VERIFICAR SI TODOAS LAS FIRMAS DE LOS METODOS COINCIDEN CON LAS DE UbicacionDao Y SI ES ASI DEJAR UNA SOLA INTERFAZ, SINO BUSCAR UN NOMBRE QUE NO HAGA REFERENCIA A LA IMPLEMENTACION

    void eliminarDatos();

    void create(Ubicacion ubicacion);

    boolean existeUbicacion(Ubicacion ubicacion);

    void conectar(String ubicacion1, String ubicacion2, String tipoCamino);

}
