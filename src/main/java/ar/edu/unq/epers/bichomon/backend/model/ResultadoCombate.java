package ar.edu.unq.epers.bichomon.backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultadoCombate {

    private Bicho ganadorCombate;
    private List<Ataque> informacionAtaques;
    private LocalDate fechaCombate;

    public ResultadoCombate(Bicho bicho, List<Ataque> ataquesRetador, List<Ataque> ataquesCampeon) {
        ganadorCombate = bicho;
        informacionAtaques = ataquesCampeon;
        informacionAtaques = ataquesRetador;
        fechaCombate = LocalDate.now();
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
