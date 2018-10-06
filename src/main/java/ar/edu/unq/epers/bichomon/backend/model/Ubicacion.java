package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Ubicacion {

    @Id
    private String nombre;

    @OneToMany
    protected List<Entrenador> entrenadores;


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

    public void setBichomones(List<Bicho> bichomones){
        bichomones = bichomones;
    }

    public void dejarBicho(Bicho unBicho) throws Exception {
        throw new Exception();
    }

    public void buscar(Entrenador entrenador) {
        if(exitoDeBusqueda(entrenador.getFactorCaptura(), entrenador.getNivel().getFactorDeNivel())){
            encontrarBichomon(entrenador);
        }
    }


    public void entregarBicho(Entrenador unEntrenador, Bicho unBicho){
        unEntrenador.obtenerBicho(unBicho);
    };

    public abstract void encontrarBichomon(Entrenador unEntrenador);

    public Boolean exitoDeBusqueda(Double factorTiempo, Double factorNivel){
        return factorTiempo * factorNivel * this.factorPoblacion * Math.random() > 0.5;
    };

    public void combatirCon(Bicho unBicho) throws  Exception {
        throw new Exception();
    }

}
