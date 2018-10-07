package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class ResultadoCombate {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Bicho ganadorCombate;
    @OneToMany
    private List<Ataque> informacionAtaques;

    public ResultadoCombate(Bicho bicho, List<Ataque> ataquesRetador, List<Ataque> ataquesCampeon) {
        ganadorCombate = bicho;
        informacionAtaques = ataquesCampeon;
        informacionAtaques = ataquesRetador;
    }

    public Bicho getGanadorCombate() {
        return ganadorCombate;
    }

    public void setGanadorCombate(Bicho ganadorCombate) {
        this.ganadorCombate = ganadorCombate;
    }

    public List<Ataque> getInformacionAtaques() {
        return informacionAtaques;
    }

    public void setInformacionAtaques(List<Ataque> informacionAtaques) {
        this.informacionAtaques = informacionAtaques;
    }
}
