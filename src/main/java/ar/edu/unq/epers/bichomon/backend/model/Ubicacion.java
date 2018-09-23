package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;

@Entity
public class Ubicacion {

    private String nombre = "";


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
