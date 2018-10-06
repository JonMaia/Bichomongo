package ar.edu.unq.epers.bichomon.backend.model;

import ar.edu.unq.epers.bichomon.backend.model.exception.AccionEnUbicacionErroneaException;
import ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda.ExitoDeBusqueda;
import ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda.ExitoDeBusquedaNormal;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Ubicacion {

    @Id
    private String nombre;

    @OneToMany
    protected List<Entrenador> entrenadores;


    private Double factorPoblacion = 1.0;

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
        return exitoDeBusqueda.ejecutar(factorTiempo,factorNivel,this.factorPoblacion);
    }

    public void combatirCon(Bicho unBicho) throws  AccionEnUbicacionErroneaException {
        throw new AccionEnUbicacionErroneaException("No se puede combatir en la ubicacion");
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
}
