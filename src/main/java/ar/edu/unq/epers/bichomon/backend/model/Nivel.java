package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Nivel {

    @Id
    Integer id;

    Integer numero;

    Integer expMaxima;

    Integer maximoDeBichos;

    @OneToOne
    private Nivel nextNivel;

    Double factorDeNivel;


    public Nivel(Integer numero,Integer expMaxima, Integer maximoDeBichos,Nivel nextLevel,Double factorDeBusqueda) {
        this.setNumero(numero);
        this.setExpMaxima(expMaxima);
        this.setMaximoDeBichos(maximoDeBichos);
        this.setNextNivel(nextLevel);
        this.setFactorDeNivel(factorDeBusqueda);
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

    public Nivel getNextNivel() {
        return nextNivel;
    }

    public Double getFactorDeNivel() {
        return factorDeNivel;
    }

    public void setFactorDeNivel(Double factorDeNivel) {
        this.factorDeNivel = factorDeNivel;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
