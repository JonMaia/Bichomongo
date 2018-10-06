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

    protected Double factorPoblacion;


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

    public void dejarBicho(Bicho unBicho) throws Exception {
        throw new Exception();
    }

    public abstract void entregarBicho(Entrenador unEntrenador, Bicho unBicho);

    public abstract void buscar(Entrenador unEntrenador);

    public abstract Boolean exitoDeBusqueda(Double factorTiempo, Double factorNivel);

    public void combatirCon(Bicho unBicho) throws  Exception {
        throw new Exception();
    }

}
