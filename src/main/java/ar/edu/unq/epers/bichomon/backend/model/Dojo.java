package ar.edu.unq.epers.bichomon.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Dojo extends Ubicacion {

    Bicho campeon = null;
    List<ResultadoCombate> resultadoCombates = new ArrayList<>();

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
        if(duelo.getTriunfoRetador())
            campeon = unBicho;
    }
}
