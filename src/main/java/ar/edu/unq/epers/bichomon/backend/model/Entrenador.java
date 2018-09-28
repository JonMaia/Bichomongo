package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
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

    @OneToMany
    private Nivel nivel;

    Date fechaUltimoBichoEncontra;

    public Entrenador(String nombre, Ubicacion ubicacion, Nivel nivel) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.bichomones = new ArrayList<>();
        this.experiencia = 0;
        this.nivel = nivel;

    }

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

    public Nivel getNivel() {
        return this.nivel;
    }

    public void setNivel(Nivel nivel){
        this.nivel = nivel;
    }

    public void addExperiencia(Experiencia exp){
        this.experiencia += exp.getExperiencia();
        pasaDeNivel();
    }

    private void pasaDeNivel() {
        if(experiencia <getNivel().getExpMaxima())
            return;
        this.setNivel(this.getNivel().next());
    }

    public Boolean capturarBicho(Bicho bicho){
        return false;
    }

    public void abandonarBicho(Bicho bicho){
        //if(ubicacion.dejarBicho(bicho)){

        //}
    }


}
