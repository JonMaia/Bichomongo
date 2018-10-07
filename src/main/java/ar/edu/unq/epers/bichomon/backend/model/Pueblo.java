package ar.edu.unq.epers.bichomon.backend.model;

import java.util.List;

public class Pueblo extends Ubicacion {

    private List<ProbabilidadDeOcurrencia> especiesEnPueblo;

    @Override
    public Bicho encontrarBichomon(Entrenador unEntrenador) {
        if(especiesEnPueblo.isEmpty())
            return null;
        Especie  especie = getEspecieSeleccionada();

        return entregarBicho(unEntrenador, new Bicho(especie));
    }

    private Especie getEspecieSeleccionada() {
        double selector = Math.random() * totalDivisorDeProbabilidadDeOcurrencia();
        Integer i = 0;
        ProbabilidadDeOcurrencia res;
        do{
            res = especiesEnPueblo.get(i);
            selector -= res.getProbabilidad();
            i++;
        }
        while (selector > 0 && i < especiesEnPueblo.size());
        return res.getEspecie();
    }


    public Integer totalDivisorDeProbabilidadDeOcurrencia(){
        Integer res = 0;
        for (ProbabilidadDeOcurrencia p: especiesEnPueblo) 
            res +=p.getProbabilidad();
        return res;
    }

    public List<ProbabilidadDeOcurrencia> getEspeciesEnPueblo() {
        return especiesEnPueblo;
    }

    public void setEspeciesEnPueblo(List<ProbabilidadDeOcurrencia> especiesEnPueblo) {
        this.especiesEnPueblo = especiesEnPueblo;
    }
}
