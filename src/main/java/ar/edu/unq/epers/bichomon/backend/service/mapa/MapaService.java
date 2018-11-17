package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Pueblo;
import ar.edu.unq.epers.bichomon.backend.model.TipoDeCamino;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;

import java.util.List;

public interface MapaService {

    void mover(String entrenador, String ubicacion) throws RuntimeException;
    /*se cambiará al entrenador desde su ubicación actual a la especificada por parametro.*/


    int cantidadEntrenadores(String ubicacion);
    /*se deberá devolver la cantidad de entrenadores que se encuentren actualmente en dicha localización.*/


    Bicho campeon(String dojo);
    /*retorna el actual campeon del Guarderia especificado.*/


    Bicho campeonHistorico(String dojo);

    void crearUbicacion(Ubicacion ubicacion);

    void conectar(String ubicacion1, String ubicacion2, TipoDeCamino tipoCamino);

    /**
     * devuelve todas las Ubicaciones conectadas directamente a una ubicación provista
     * por medio de un tipo de camino especificado.
     * @param ubicacion
     * @param tipoCamino
     * @return Ubicaciones conectadas
     */
    List<Ubicacion> conectados(String ubicacion, String tipoCamino);


    List<Ubicacion> todosLosConectados(String ubicacion);

    /**
     * se mueve y toma la ruta mas corta entre ambas ubicaciones (no implica que sea la más barata)
     * @param entrenador
     * @param ubicacion
     */
    void moverMasCorto(String entrenador, String ubicacion);

    boolean existeUbicacion(String pueblo);
}
