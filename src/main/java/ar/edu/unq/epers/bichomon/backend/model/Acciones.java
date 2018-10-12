package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Acciones {

    @Id @GeneratedValue
    private Integer id;

    private Integer experienciaPorCapturarBicho;

    private Integer experienciaPorCombatir;

    private Integer experienciaPorEvolucion;


    public Acciones(Integer experienciaPorCapturarBicho, Integer experienciaPorCombatir, Integer experienciaPorEvolucion) {
        this.experienciaPorCapturarBicho = experienciaPorCapturarBicho;
        this.experienciaPorCombatir = experienciaPorCombatir;
        this.experienciaPorEvolucion = experienciaPorEvolucion;
    }

    public Acciones() {  }

    public Integer getExperienciaPorCapturarBicho() {
        return experienciaPorCapturarBicho;
    }

    public void setExperienciaPorCapturarBicho(Integer experienciaPorCapturarBicho) {
        this.experienciaPorCapturarBicho = experienciaPorCapturarBicho;
    }

    public Integer getExperienciaPorCombatir() {
        return experienciaPorCombatir;
    }

    public void setExperienciaPorCombatir(Integer experienciaPorCombatir) {
        this.experienciaPorCombatir = experienciaPorCombatir;
    }

    public Integer getExperienciaPorEvolucion() {
        return experienciaPorEvolucion;
    }

    public void setExperienciaPorEvolucion(Integer experienciaPorEvolucion) {
        this.experienciaPorEvolucion = experienciaPorEvolucion;
    }
}
