package ar.edu.unq.epers.bichomon.backend.model;

import ar.edu.unq.epers.bichomon.backend.model.exception.UbicacionIncorrectaException;
import ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Ubicacion {

    @Id
    private String nombre;

    @OneToMany
    protected List<Entrenador> entrenadores = new ArrayList<Entrenador>();

    private Double factorPoblacion = 1.0;

    @Transient
    private ExitoDeBusqueda exitoDeBusqueda = new ExitoDeBusquedaNormal();


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

    public void setBichomones(List<Bicho> bichomones){
        bichomones = bichomones;
    }

    public void dejarBicho(Bicho unBicho) throws UbicacionIncorrectaException {
        throw new UbicacionIncorrectaException("No puede dejar un bicho en esta ubicación");
    }

    public Bicho buscar(Entrenador entrenador) {
        if(exitoDeBusqueda(entrenador.getFactorCaptura(), entrenador.getNivel().getFactorDeNivel())){
            return encontrarBichomon(entrenador);
        }
        return null;
    }

    public void entregarBicho(Entrenador unEntrenador, Bicho unBicho){
        unEntrenador.obtenerBicho(unBicho);
    }

    public abstract Bicho encontrarBichomon(Entrenador unEntrenador);

    public Boolean exitoDeBusqueda(Double factorTiempo, Double factorNivel){
        return exitoDeBusqueda.ejecutar(factorTiempo,factorNivel,this.factorPoblacion);
    }

    public ResultadoCombate combatirCon(Bicho unBicho) throws UbicacionIncorrectaException {
        throw new UbicacionIncorrectaException("No se puede combatir en la ubicacion");
    }

    public Double getFactorPoblacion() {
        return factorPoblacion;
    }

    public void setFactorPoblacion(Double factorPoblacion) {
        this.factorPoblacion = factorPoblacion;
    }

    public ExitoDeBusqueda getExitoDeBusqueda() {
        return exitoDeBusqueda;
    }

    public void setExitoDeBusqueda(ExitoDeBusqueda exitoDeBusqueda) {
        this.exitoDeBusqueda = exitoDeBusqueda;
    }

    public void agregarEntrenador(Entrenador entrenador){
        this.entrenadores.add(entrenador);
    }
}
