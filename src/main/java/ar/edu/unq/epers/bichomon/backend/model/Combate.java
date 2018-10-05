package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
public class Combate {

    @Id
    Integer id;

    Date fecha;

    @ManyToOne
    Bicho retador;
    float danioRecibidoRetador;
    private List<Float> ataquesRetador;

    @ManyToOne
    Bicho campeon;
    float getDanioRecibidoCampeon;
    private List<Float> ataquesCampeon;

    @ManyToOne
    Ubicacion dojo;

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

    public void setDojo(Ubicacion dojo) {
        this.dojo = dojo;
    }

    public Boolean getTriunfoRetador() {
        return triunfoRetador;
    }

    public void setTriunfoRetador(Boolean triunfoRetador) {
        this.triunfoRetador = triunfoRetador;
    }

    public float ataqueDe(Bicho bicho){
        float leftLimit = 0.5f;
        float rightLimit = 1f;
        float danioAtaque = bicho.getEnergia() * (leftLimit + new Random().nextFloat() * (rightLimit - leftLimit));
        return danioAtaque;
    }

    public void combatir(Bicho retador, Bicho campeon){
        int contadorAtaques = 0;
        boolean atacaRetador = true;
        boolean terminoCombate = false;
        while(contadorAtaques < 20 && !terminoCombate){
            contadorAtaques++;
            if(atacaRetador){
                float calculadorAtaque = ataqueDe(retador);
                retador.atacar(campeon, calculadorAtaque);
                ataquesRetador.add(calculadorAtaque);
                terminoCombate = this.chequearContinuidadCombate(campeon);
                atacaRetador = false;
            }
            else{
                float calculadorAtaqueC = ataqueDe(campeon);
                campeon.atacar(retador, calculadorAtaqueC);
                ataquesCampeon.add(calculadorAtaqueC);
                terminoCombate = this.chequearContinuidadCombate(retador);
                atacaRetador = true;
            }
        }

        Entrenador entrenador = retador.getEntrenador();
        //entrenador.experienciaPorCombate();
        retador.aumentarEnergiaCombate();
        campeon.aumentarEnergiaCombate();
    }

    private boolean chequearContinuidadCombate(Bicho atacado) {
        boolean murioBicho = atacado.getEnergia() <= 0;
        boolean alcanzoMaximoDeAtaques = ataquesRetador.size() >= 10;
        return murioBicho || alcanzoMaximoDeAtaques;

    }

}
