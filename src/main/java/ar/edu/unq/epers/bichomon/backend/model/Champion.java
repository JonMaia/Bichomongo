package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate fechaDescoronado;
    @OneToOne
    private Bicho campeon;
    private LocalDate fechaCoronado;

    public Champion(Bicho unBicho) {
        campeon = unBicho;
        fechaCoronado = LocalDate.now();
        fechaDescoronado = LocalDate.now();
    }

    public LocalDate getFechaDescoronado() {
        return fechaDescoronado;
    }

    public void setFechaDescoronado(LocalDate fechaDescoronado) {
        this.fechaDescoronado = fechaDescoronado;
    }

    public LocalDate getFechaCoronado() {
        return fechaCoronado;
    }

    public void setFechaCoronado(LocalDate fechaCoronado) {
        this.fechaCoronado = fechaCoronado;
    }
}
