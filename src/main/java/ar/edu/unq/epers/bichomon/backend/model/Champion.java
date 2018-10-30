package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private long periodo;

    @OneToOne
    private Bicho campeon;

    private Date fechaCoronado;

    public Champion(Bicho unBicho) {
        campeon = unBicho;
        fechaCoronado = new Date();
        periodo = 0;
    }

    public Champion() {}

    public Date getFechaCoronado() {
        return fechaCoronado;
    }

    public void setFechaCoronado(Date fechaCoronado) {
        this.fechaCoronado = fechaCoronado;
    }

    public long getPeriodo() {
        if (this.getPeriodo() == 0){
            long periodo = new Date().getTime() - this.getFechaCoronado().getTime();
        }
        return periodo;
    }

    public void setPeriodo(long periodo) {
        this.periodo = periodo;
    }

    public Bicho getBicho() {
        return campeon;
    }
}
