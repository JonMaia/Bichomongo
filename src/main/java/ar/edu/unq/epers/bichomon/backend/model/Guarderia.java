package ar.edu.unq.epers.bichomon.backend.model;

import java.util.List;

public class Guarderia extends Ubicacion {

    @Override
    public List<Bicho> getBichomones() {
        return super.bichomones;
    }

    @Override
    public void dejarBicho(Bicho unBicho) {
        super.bichomones.add(unBicho);
    }

    @Override
    public void entregarBicho(Entrenador unEntrenador, Bicho unBicho){
        unEntrenador.obtenerBicho(unBicho);
    }


    @Override
    public void buscar(Entrenador entrenador) {
        if(exitoDeBusqueda(entrenador.getFactorCaptura(), entrenador.getNivel().getFactorDeNivel())){
            int i = 0;
            Bicho res;

            if(this.getBichomones().isEmpty())
                return;
            while(this.getBichomones().size()>i &&
                    this.getBichomones().get(i).getPadresDeNelson().contains(entrenador)){
                i++;
            }
            if(this.getBichomones().size()==i)
                return;
            res = this.getBichomones().get(i);
            entregarBicho(entrenador, res);
            this.getBichomones().remove(i);
        }
    }

    @Override
    public Boolean exitoDeBusqueda(Double factorTiempo, Double factorNivel) {
        return factorTiempo * factorNivel * this.factorPoblacion * Math.random() > 0.5;
    }

}
