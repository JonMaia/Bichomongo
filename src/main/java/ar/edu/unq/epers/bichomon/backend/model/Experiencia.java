package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Experiencia {

    @Id
    String nombre;

    Integer experiencia;
}
