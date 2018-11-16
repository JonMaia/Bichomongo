package ar.edu.unq.epers.bichomon.backend.model.Eventos;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;
import org.joda.time.LocalDate;

public class Captura extends Evento{

    Integer bichocapturado;

    public Captura(Bicho bichocapturado, Entrenador entrenador, LocalDate fecha, Ubicacion ubicacion) {
        this.bichocapturado = bichocapturado.getId();
        this.setEntrenador(entrenador.getNombre());
        this.setFecha(fecha);
        this.setUbicacion(ubicacion.getNombre());
    }
}
