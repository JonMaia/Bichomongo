package ar.edu.unq.epers.bichomon.backend.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    @OneToOne
    private Nivel nivel;

    LocalDate fechaUltimoBichoEncontra;

    private Acciones accion;


    public Entrenador(String nombre, Ubicacion ubicacion, Nivel nivel, Acciones acciones) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.bichomones = new ArrayList<>();
        this.experiencia = 0;
        this.nivel = nivel;
        this.accion = acciones;

    }

    public Entrenador() {

    }

    //GETTERS AND SETTERS

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

    public void setFechaUltimoBichoEncontra(LocalDate date){
        this.fechaUltimoBichoEncontra = date;
    }
    public LocalDate getFechaUltimoBichoEncontra(){
        return this.fechaUltimoBichoEncontra;
    }


    public void addExperiencia(Integer exp){
        this.experiencia += exp;
        pasaDeNivel();
    }

    private void pasaDeNivel() {
        if(experiencia <getNivel().getExpMaxima())
            return;
        this.setNivel(this.getNivel().next());
    }

    public void obtenerBicho(Bicho bicho){
        addExperiencia(accion.getExperienciaPorCapturarBicho());
        setFechaUltimoBichoEncontra(LocalDate.now());
    }


    public void buscarBicho(){
        if(puedoBuscar())
            ubicacion.buscar(this);
    }

    private boolean puedoBuscar() {
        return getBichomones().size() < getNivel().getMaximoDeBichos();
    }


    public void abandonarBicho(Bicho bicho){
        if(ubicacion.dejarBicho(bicho)){
            Iterator<Bicho> iterator = getBichomones().iterator();
            while (iterator.hasNext()){
                Bicho bichomon = iterator.next();
                if(bichomon.equals(bicho)){
                    getBichomones().remove(bichomon);
                    break;
                }
            }
        }
    }

    public void ganarExperienciaPorEvolucion(){
        addExperiencia(accion.getExperienciaPorEvolucion());
    }

    public void iniciarDuelo(){
        ubicacion.combatirCon(Bicho bicho);
    }


}
