package ar.edu.unq.epers.bichomon.backend.service.bicho;


import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Combate;

public interface BichoService {

    /*
    El entrenador deberá buscar un nuevo bicho en la localización actual en la que se encuentre.
    Si la captura es exitosa el bicho será agregado al inventario del entrenador (ver sección Busquedas)
    y devuelto por el servicio.
     */

    Bicho buscar(String entrenador);

    /*El entrenador abandonará el bicho especificado en la localización actual.
     Si la ubicación no es una Guarderia se arrojará UbicacionIncorrectaException. */

    void abandonar(String entrenador, int bicho);

    /*ResultadoCombate el entrenador desafiará al actual campeon del dojo a duelo.
     Si la ubicación no es un Guarderia se arrojará UbicacionIncorrectaException.
     El objeto resultante ResultadoCombate informará no solo quién fue el ganador del
     combate sino el resultado de cada uno de los ataques realizados. */

    Combate duelo(String entrenador, int bicho);

    //deberá devolver true si el bicho especificado está en condiciones de evolucionar.

    boolean puedeEvolucionar(int bicho);

    //bicho evoluciona el bicho especificado (si cumple con las codiciones para evolucionar)

    Bicho evolucionar(int bicho);
}
