package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Entrenador {

    @Id
    private String nombre;

    @ManyToOne()
    private Ubicacion ubicacion;

    private Integer experiencia;

    @OneToMany
    private List<Bicho> bichomones;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    public List<Bicho> getBichomones() {
        return bichomones;
    }

    public void setBichomones(List<Bicho> bichomones) {
        this.bichomones = bichomones;
    }
}
