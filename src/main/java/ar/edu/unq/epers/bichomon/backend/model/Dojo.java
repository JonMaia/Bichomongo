package ar.edu.unq.epers.bichomon.backend.model;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;
import java.util.List;

public class Dojo extends Ubicacion {

    private Champion campeon = null;
    private List<ResultadoCombate> resultadoCombates = new ArrayList<>();
    private List<Champion> campeones = new ArrayList<Champion>();

    @Override
    public Bicho encontrarBichomon(Entrenador unEntrenador) {
        if(campeon == null)
            return null;
        entregarBicho(unEntrenador , new Bicho(campeon.getBicho().getEspecie().getEspecieInicial()));
        return null;
    }

    @Override
    public void combatirCon(Bicho unBicho){
        Duelo duelo = new Duelo();

        ResultadoCombate resultado = duelo.combatir(unBicho, campeon.getBicho());
        Bicho nuevoCampeon = resultado.getGanadorCombate();
        if(campeon.getBicho() == nuevoCampeon) {
            return;
        }

        this.campeon.descoronar();
        this.campeon = new Champion(nuevoCampeon);
        campeones.add(campeon);
    }

    public Champion getCampeon() {
        return campeon;
    }

    public void setCampeon(Champion campeon) {
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

    public List<Champion> getCampeones() {
        return campeones;
    }

    //TODO campeonHistorio como variable interna y solamente queda comparar con el actual.
}
