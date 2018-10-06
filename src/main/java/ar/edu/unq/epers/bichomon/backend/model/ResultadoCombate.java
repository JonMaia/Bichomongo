package ar.edu.unq.epers.bichomon.backend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultadoCombate {

    private Bicho ganadorCombate;
    private List<Ataque> informacionAtaques;

    public ResultadoCombate(Bicho bicho, List<Ataque> ataquesRetador, List<Ataque> ataquesCampeon) {
        ganadorCombate = bicho;
        informacionAtaques.addAll(ataquesCampeon);
        informacionAtaques.addAll(ataquesRetador);
    }
}
