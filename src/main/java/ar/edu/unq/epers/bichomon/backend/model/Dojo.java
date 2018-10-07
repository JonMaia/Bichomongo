package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dojo extends Ubicacion {

    @OneToOne
    private Bicho campeon = null;
    @OneToMany
    private List<ResultadoCombate> resultadoCombates = new ArrayList<>();
    @OneToMany
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
        Duelo duelo = new Duelo();
        ResultadoCombate resultado = duelo.combatir(unBicho, campeon);
        campeon = resultado.getGanadorCombate();
        campeones.add(new Champion(unBicho));
        return resultado;
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

    public void agregarResultadooCombate(ResultadoCombate resultadoCombate){
        this.resultadoCombates.add(resultadoCombate);
    }
}
