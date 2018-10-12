package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer periodo;
    @OneToOne
    private Bicho campeon;
    private LocalDate fechaCoronado;

    public Champion(Bicho unBicho) {
        campeon = unBicho;
        fechaCoronado = LocalDate.now();
      //  fechaDescoronado = LocalDate.now();
    }



    public LocalDate getFechaCoronado() {
        return fechaCoronado;
    }

    public void setFechaCoronado(LocalDate fechaCoronado) {
        this.fechaCoronado = fechaCoronado;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Bicho getBicho() {
        return campeon;
    }
}
