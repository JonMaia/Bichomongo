package ar.edu.unq.epers.bichomon.backend.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Entity
public class Dojo extends Ubicacion {

    @OneToOne
    private Champion campeon = null;

    @OneToMany  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<ResultadoCombate> resultadoCombates = new ArrayList<>();

    @OneToMany  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Champion> campeones = new ArrayList<Champion>();

    public Dojo() {}

    public List<Champion> getCampeones() {
        return campeones;
    }

    public void setCampeones(List<Champion> campeones) {
        this.campeones = campeones;
    }

    @Override
    public Bicho encontrarBichomon(Entrenador unEntrenador) {
        Bicho bicho = null;
        if(campeon != null) {
            bicho = new Bicho(campeon.getBicho().getEspecie().getEspecieInicial());
            entregarBicho(unEntrenador, bicho);
        }
        return bicho;
    }

    @Override
    public ResultadoCombate combatirCon(Bicho unBicho){
        Duelo duelo = new Duelo((campeon != null ? campeon.getBicho() : null));
        ResultadoCombate resultado = duelo.combatir(unBicho);
        Bicho ganador = resultado.getGanadorCombate();
        if(campeon == null || ganador != campeon.getBicho())
            setCampeon(ganador);

        return resultado;
    }

    public Champion getCampeon() {
        return campeon;
    }

    public Champion setCampeon(Bicho campeon) {

        if(campeones.size() > 0){
            long diffInMillies = new Date().getTime() - campeones.get(campeones.size()-1).getFechaCoronado().getTime();
            campeones.get(campeones.size()-1).setPeriodo(diffInMillies);
        }
        Champion champion = new Champion(campeon);
        campeones.add(champion);
        this.campeon = champion;
        return champion;


    }

    public List<ResultadoCombate> getResultadoCombates() {
        return resultadoCombates;
    }

    public void setResultadoCombates(List<ResultadoCombate> resultadoCombates) {
        this.resultadoCombates = resultadoCombates;
    }

    public void agregarResultadooCombate(ResultadoCombate resultadoCombate){
        this.resultadoCombates.add(resultadoCombate);
    }
}
