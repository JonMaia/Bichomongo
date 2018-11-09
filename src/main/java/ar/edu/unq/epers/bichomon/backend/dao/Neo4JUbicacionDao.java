package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.TipoDeCamino;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;
import org.neo4j.driver.v1.Record;

import java.util.List;

public interface Neo4JUbicacionDao {
    // TODO: VERIFICAR SI TODOAS LAS FIRMAS DE LOS METODOS COINCIDEN CON LAS DE UbicacionDao Y SI ES ASI DEJAR UNA SOLA INTERFAZ, SINO BUSCAR UN NOMBRE QUE NO HAGA REFERENCIA A LA IMPLEMENTACION

    void eliminarDatos();

    void create(Ubicacion ubicacion);

    boolean existeUbicacion(String string);

    //List<Record> caminoA(Ubicacion origen, Ubicacion destino);


 //   Integer getPrecioCaminoRelacion(String nombre1, String nombre2);

   // Integer getPrecioCaminoCorto(String nombre1, String nombre2);

    Integer getPrecioCaminoCorto(String nombre1, String nombre2);

    void conectar(String ubicacion1, String ubicacion2, TipoDeCamino tipoCamino);

    Integer costoCaminoMasCorto(String origen, String destino);

    List<Record> conectados(String ubicacion, String tipoCamino);

    boolean existeRelacion(String nombre, String nombre1);
}
