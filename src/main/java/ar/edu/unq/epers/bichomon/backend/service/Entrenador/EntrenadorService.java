package ar.edu.unq.epers.bichomon.backend.service.Entrenador;

import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;

public interface EntrenadorService {
    void setUbicacionEntrenador(String entrenador, String ubicacion);

    Nivel getNivel(Entrenador entrenador);


}
