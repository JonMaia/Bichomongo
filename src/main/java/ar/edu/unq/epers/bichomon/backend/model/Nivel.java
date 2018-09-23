package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Nivel {

    @Id
    Integer id;

    Integer expMinima;

    Integer expMaxima;

    Integer maximoDeBichos;


}
