package ar.edu.unq.epers.bichomon.backend.model;

import ar.edu.unq.epers.bichomon.backend.model.exception.UbicacionIncorrectaException;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.joda.time.LocalDate;
import org.joda.time.Minutes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Entrenador {

    @Id
    private String nombre;

    @ManyToOne  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Ubicacion ubicacion;

    private Integer experiencia;

    @OneToMany @LazyCollection(LazyCollectionOption.FALSE) @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Bicho> bichomones;

    @OneToOne  (cascade = CascadeType.ALL)
    private Nivel nivel;

    LocalDate fechaUltimoBichoEncontra;

    @OneToOne (cascade = CascadeType.ALL)
    private Accion accion;

    private Integer billetera;

    public Entrenador(String nombre, Ubicacion ubicacion, Nivel nivel, Accion accion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.bichomones = new ArrayList<>();
        this.experiencia = 0;
        this.nivel = nivel;
        this.accion = accion;

    }

    public Entrenador() {
        experiencia = 0;
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
        ubicacion.agregarEntrenador(this);
    }

    public Integer getExperiencia() {
        return experiencia;
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

    public void setAccion(Accion accion){this.accion = accion;}

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
        if(!(this.experiencia <getNivel().getExpMaxima()))
           this.setNivel(this.getNivel().next());
    }

    public void obtenerBicho(Bicho bicho){
        addExperiencia(accion.getExperienciaPorCapturarBicho());
        this.bichomones.add(bicho);
        setFechaUltimoBichoEncontra(LocalDate.now());
    }


    public Bicho buscarBicho(){

        if(puedoBuscar())
            return ubicacion.buscar(this);
        return null;
    }

    private boolean puedoBuscar() {
        return getBichomones().size() < getNivel().getMaximoDeBichos();
    }


    public void abandonarBicho(Bicho bicho){
        ubicacion.dejarBicho(bicho);
        getBichomones().remove(bicho);
        bicho.getExEntrenadores().add(this);
    }

    public void ganarExperienciaPorEvolucion(){addExperiencia(accion.getExperienciaPorEvolucion());}

    public ResultadoCombate iniciarDuelo(Bicho bicho) throws UbicacionIncorrectaException {
        ResultadoCombate resultado = ubicacion.combatirCon(bicho);
        addExperiencia(accion.getExperienciaPorCombatir());
        return resultado;
    }

    public Double getFactorCaptura(){
        //En caso de no tener ningun bichomon le otorgamos una gran posibilidad de obtener uno!!
        if(getFechaUltimoBichoEncontra() == null)
            return 20.0;
        Integer minutosTranscurridos = (Minutes.minutesBetween(getFechaUltimoBichoEncontra(), LocalDate.now())).getMinutes();
        return minutosTranscurridos.doubleValue() / 10;

    }

    public void moverA(Ubicacion nuevaUbicacion) {

        this.ubicacion = nuevaUbicacion;
    }

    public void evolucionarBicho(Bicho bicho){
        if (bicho.puedeEvolucionar()) {
            bicho.evolucionar();
            addExperiencia(accion.getExperienciaPorEvolucion());
        }
    }

    public void setBilletera(Integer monedas) {
        this.billetera = monedas;
    }

    public Integer getBilletera() {
        return billetera;
    }
}
