package ar.edu.unq.epers.bichomon.backend.service.entrenador;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;

public interface EntrenadorService {

    void setUbicacionEntrenador(String entrenador, String ubicacion);


    void capturaBicho(Entrenador entrenador, Bicho bicho);

    boolean puedeCapturarOtroBichomon(Entrenador entrenador);
}
