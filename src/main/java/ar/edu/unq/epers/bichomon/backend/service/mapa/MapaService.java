package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;

public interface MapaService {

    void mover(String entrenador, String ubicacion) ;
        /*se cambiará al entrenador desde su ubicación actual a la especificada por parametro.*/


    int cantidadEntrenadores(String ubicacion) ;
        /*se deberá devolver la cantidad de entrenadores que se encuentren actualmente en dicha localización.*/


    Bicho campeon(String dojo);
        /*retorna el actual campeon del Guarderia especificado.*/


    Bicho campeonHistorico(String dojo);

    void crearUbicacion(Ubicacion ubicacion);

}
