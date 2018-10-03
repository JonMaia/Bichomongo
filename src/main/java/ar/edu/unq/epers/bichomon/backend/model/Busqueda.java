package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Busqueda {

    @Id
    @GeneratedValue
    private Integer id;
    private Double busquedaExitosa;
    private Double valorDeExito = 0.5D;

    public Bicho realizarBusqueda(Entrenador entrenador, Guarderia guarderia) {
        if (this.busquedaExitosa > valorDeExito) {

        }
        return null;
    }
}
