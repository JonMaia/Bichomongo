package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import javax.persistence.Entity;

@Entity
public class CondicionEnergia extends Condicion {

    public CondicionEnergia() {
    }

    public CondicionEnergia(int energia) { super.valor = energia;}

    @Override
    public boolean cumpleCondicion(Bicho bicho) {
        return bicho.getEnergia() > super.valor;
    }
}
