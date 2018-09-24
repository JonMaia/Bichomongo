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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExpMinima() {
        return expMinima;
    }

    public void setExpMinima(Integer expMinima) {
        this.expMinima = expMinima;
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
}
