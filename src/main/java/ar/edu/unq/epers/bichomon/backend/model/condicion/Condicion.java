package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Condicion {

    @Id
    Integer id;

    public int valor;

    public abstract boolean cumpleCondicion(Bicho bicho);
}
