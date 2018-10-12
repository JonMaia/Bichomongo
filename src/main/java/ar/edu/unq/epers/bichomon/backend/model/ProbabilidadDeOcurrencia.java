package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ProbabilidadDeOcurrencia {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    Especie especie;
    Integer probabilidad;

    public ProbabilidadDeOcurrencia() {}

    public ProbabilidadDeOcurrencia(Especie especie, Integer probabilidad) {
        this.especie = especie;
        this.probabilidad = probabilidad;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Integer getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Integer probabilidad) {
        this.probabilidad = probabilidad;
    }
}
