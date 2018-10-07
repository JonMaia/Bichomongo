package ar.edu.unq.epers.bichomon.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Dojo extends Ubicacion {

    private Bicho campeon = null;
    private List<ResultadoCombate> resultadoCombates = new ArrayList<>();

    @Override
    public void encontrarBichomon(Entrenador unEntrenador) {
        if(campeon == null)
            return;
        entregarBicho(unEntrenador , new Bicho(campeon.getEspecie().getEspecieInicial()));
    }

    @Override
    public void combatirCon(Bicho unBicho){
        Duelo duelo = new Duelo();
        ResultadoCombate resultado = duelo.combatir(unBicho, campeon);

        campeon = resultado.getGanadorCombate();
    }

    public Bicho getCampeon() {
        return campeon;
    }

    public void setCampeon(Bicho campeon) {
        this.campeon = campeon;
    }

    public List<ResultadoCombate> getResultadoCombates() {
        return resultadoCombates;
    }

    public void setResultadoCombates(List<ResultadoCombate> resultadoCombates) {
        this.resultadoCombates = resultadoCombates;
    }
}