package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Guarderia extends Ubicacion {

    @OneToMany
    protected List<Bicho> bichomones = new ArrayList<>();

    public List<Bicho> getBichomones() {
        return bichomones;
    }

    @Override
    public void dejarBicho(Bicho unBicho) {
        bichomones.add(unBicho);
    }

    @Override
    public Bicho encontrarBichomon(Entrenador entrenador) {
        int i = 0;
        Bicho res;

        if(this.getBichomones().isEmpty())
            return null;
        while(this.getBichomones().size()>i &&
                this.getBichomones().get(i).getExEntrenadores().contains(entrenador)){
            i++;
        }
        if(this.getBichomones().size()==i)
            return null;
        res = this.getBichomones().get(i);
        entregarBicho(entrenador, res);
        this.getBichomones().remove(i);
        return res;
    }


}
