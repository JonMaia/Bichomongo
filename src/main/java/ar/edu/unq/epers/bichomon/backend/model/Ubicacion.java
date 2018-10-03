package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Ubicacion {

    @Id
    private String nombre;

    @OneToMany
    protected List<Entrenador> entrenadores;

    @OneToMany
    protected List<Bicho> bichomones;

    @OneToOne
    protected Busqueda unaBusqueda;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Entrenador> getEntrenadores(){
        return entrenadores;
    }

    public void setEntrenadores(List<Entrenador> entrenadores) {
        entrenadores = entrenadores;
    }

    public void moverEntrenador(Entrenador entrenador) {
        entrenadores.add(entrenador);
    }

    public abstract List<Bicho> getBichomones();

    public void setBichomones(List<Bicho> bichomones){
        bichomones = bichomones;
    }

    public abstract Boolean dejarBicho(Bicho unBicho);

    public abstract Bicho entregarBicho(Bicho unBicho);

    public abstract void buscar(Entrenador entrenador);

}
