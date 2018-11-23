package ar.edu.unq.epers.bichomon.backend.model.Eventos;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;

import java.time.LocalDate;

public class Captura extends Evento{

    Integer bichocapturado;

    public Captura() {
    }

    public Captura(Integer bichocapturado, String entrenador, LocalDate fecha, String ubicacion) {
        this.bichocapturado = bichocapturado;
        this.setEntrenador(entrenador);
        this.setFecha(fecha);
        this.setUbicacion(ubicacion);
    }
}
