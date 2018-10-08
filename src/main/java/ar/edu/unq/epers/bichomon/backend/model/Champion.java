package ar.edu.unq.epers.bichomon.backend.model;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class Champion {

    private Bicho bicho;
    private LocalDate fechaCoronado;
    private int plazo;

    public Champion(Bicho unBicho) {
        bicho = unBicho;
        fechaCoronado = LocalDate.now();
    }

    public Champion() {

    }

    public LocalDate getFechaCoronado() {
        return fechaCoronado;
    }

    public void setFechaCoronado(LocalDate fechaCoronado) {
        this.fechaCoronado = fechaCoronado;
    }

    public Bicho getBicho() {
        return bicho;
    }

    public void setCampeon(Bicho campeon) {
        this.bicho = campeon;
    }

    public void descoronar() {
        plazo = Days.daysBetween(fechaCoronado, LocalDate.now()).getDays();
    }

    public int getPlazo(){
        return plazo;
    }
}
