package ar.edu.unq.epers.bichomon.backend.model;

public class ProbabilidadDeOcurrencia {
    Especie especie;
    Integer probabilidad;


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
