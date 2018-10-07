package ar.edu.unq.epers.bichomon.backend.model;

import java.time.LocalDate;

public class Champions {

    private LocalDate fechaDescoronado;
    private Bicho campeon;
    private LocalDate fechaCoronado;

    public Champions(Bicho unBicho) {
        campeon = unBicho;
        fechaCoronado = LocalDate.now();
        fechaDescoronado = LocalDate.now();
    }
}
