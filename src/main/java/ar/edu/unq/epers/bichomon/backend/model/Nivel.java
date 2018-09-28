package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Nivel {

    @Id
    Integer id;

    String nombre;

    Integer expMaxima;

    Integer maximoDeBichos;

    private Nivel nextNivel;


    public Nivel(String nombre,Integer expMaxima, Integer maximoDeBichos,Nivel nextLevel) {
        this.setNombre(nombre);
        this.setExpMaxima(expMaxima);
        this.setMaximoDeBichos(maximoDeBichos);
        this.setNextNivel(nextLevel);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExpMaxima() {
        return expMaxima;
    }

    public void setExpMaxima(Integer expMaxima) {
        this.expMaxima = expMaxima;
    }

    public Integer getMaximoDeBichos() {
        return maximoDeBichos;
    }

    public void setMaximoDeBichos(Integer maximoDeBichos) {
        this.maximoDeBichos = maximoDeBichos;
    }

    public Nivel next() {
        return this.nextNivel;
    }

    public void setNextNivel(Nivel nextNivel) {
        this.nextNivel = nextNivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
