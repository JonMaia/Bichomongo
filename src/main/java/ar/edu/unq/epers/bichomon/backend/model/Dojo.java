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
    private Bicho campeon = null;
    @OneToMany  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<ResultadoCombate> resultadoCombates = new ArrayList<>();

    public List<Champion> getCampeones() {
        return campeones;
    }

    public void setCampeones(List<Champion> campeones) {
        this.campeones = campeones;
    }

    @OneToMany  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Champion> campeones = new ArrayList<Champion>();

    @Override
    public Bicho encontrarBichomon(Entrenador unEntrenador) {
        if(campeon == null)
            return null;
        entregarBicho(unEntrenador , new Bicho(campeon.getEspecie().getEspecieInicial()));
        return null;
    }

    @Override
    public ResultadoCombate combatirCon(Bicho unBicho){
        Duelo duelo = new Duelo(campeon);
        ResultadoCombate resultado = duelo.combatir(unBicho);
        Bicho ganador = resultado.getGanadorCombate();
        if(ganador != campeon)
            setCampeon(ganador);

        return resultado;
    }

    public Bicho getCampeon() {
        return campeon;
    }

    public void setCampeon(Bicho campeon) {

        if(campeones.size() > 0){
            long diffInMillies = new Date().getTime() - campeones.get(campeones.size()-1).getFechaCoronado().getTime();
            campeones.get(campeones.size()-1).setPeriodo(diffInMillies);
        }

        campeones.add(new Champion(campeon));
        this.campeon = campeon;
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
