package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity
public class Combate {

    @Id
    Integer id;

    Date fecha;

    @ManyToOne
    Bicho retador;

    @ManyToOne
    Bicho retado;

    @ManyToOne
    Ubicacion dojo;

    Boolean triunfoRetador;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Bicho getRetador() {
        return retador;
    }

    public void setRetador(Bicho retador) {
        this.retador = retador;
    }

    public Bicho getRetado() {
        return retado;
    }

    public void setRetado(Bicho retado) {
        this.retado = retado;
    }

    public Ubicacion getDojo() {
        return dojo;
    }

    public void setDojo(Ubicacion dojo) {
        this.dojo = dojo;
    }

    public Boolean getTriunfoRetador() {
        return triunfoRetador;
    }

    public void setTriunfoRetador(Boolean triunfoRetador) {
        this.triunfoRetador = triunfoRetador;
    }
}
