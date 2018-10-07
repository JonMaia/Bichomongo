package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
public class Duelo {

    @Id
    Integer id;

    Date fecha;

    @OneToOne
    Bicho retador;
    float danioRecibidoRetador;

    @OneToMany
    @ElementCollection(targetClass = Ataque.class)
    List<Ataque> ataquesRetador;

    @OneToOne
    Bicho campeon;
    float getDanioRecibidoCampeon;

    @OneToMany
    @ElementCollection(targetClass = Ataque.class)
    List<Ataque> ataquesCampeon;

    @OneToOne
    Dojo dojo;

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

    public Bicho getCampeon() {
        return campeon;
    }

    public void setCampeon(Bicho retado) {
        this.campeon = retado;
    }

    public Ubicacion getDojo() {
        return dojo;
    }

    public void setDojo(Dojo dojo) {
        this.dojo = dojo;
    }

    public Boolean getTriunfoRetador() {
        return triunfoRetador;
    }

    public void setTriunfoRetador(Boolean triunfoRetador) {
        this.triunfoRetador = triunfoRetador;
    }


    public ResultadoCombate combatir(Bicho retador, Bicho campeon){
        if(campeon == null)
            return new ResultadoCombate(retador, new ArrayList<>(), new ArrayList<>());

        int contadorAtaques = 0;
        boolean atacaRetador = true;
        boolean terminoCombate = false;
        while(contadorAtaques < 20 && !terminoCombate){
            contadorAtaques++;
            if(atacaRetador){
                Ataque ataqueRetador = retador.atacar(campeon);
                ataquesRetador.add(ataqueRetador);
                terminoCombate = this.chequearContinuidadCombate(retador, campeon);
                atacaRetador = false;
            }
            else{
                Ataque ataqueCampeon = campeon.atacar(retador);
                ataquesCampeon.add(ataqueCampeon);
                terminoCombate = this.chequearContinuidadCombate(campeon,retador);
                atacaRetador = true;
            }
        }
        retador.aumentarEnergiaCombate();
        campeon.aumentarEnergiaCombate();
        ResultadoCombate resultadoCombate= new ResultadoCombate(campeon, ataquesRetador, ataquesCampeon);
        return resultadoCombate;
    }

    private boolean chequearContinuidadCombate(Bicho atacante, Bicho atacado) {
        boolean alcanzoMaximoDeAtaques = ataquesRetador.size() >= 10;
        if(atacado.perdioCombate()){
            this.campeon = atacante;
        }

        return atacado.perdioCombate() || alcanzoMaximoDeAtaques;

    }

}
