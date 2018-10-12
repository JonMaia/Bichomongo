package ar.edu.unq.epers.bichomon.backend.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Pueblo extends Ubicacion {

    @OneToMany @Fetch(value = FetchMode.SUBSELECT) @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ProbabilidadDeOcurrencia> especiesEnPueblo;

    @Override
    public Bicho encontrarBichomon(Entrenador unEntrenador) {
        if(especiesEnPueblo.isEmpty())
            return null;
        Especie  especie = getEspecieSeleccionada();
        Bicho bicho = new Bicho(especie);
        entregarBicho(unEntrenador,bicho);
        return bicho;
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
