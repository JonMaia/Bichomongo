package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import javax.persistence.Entity;

@Entity
public class CondicionVictorias extends Condicion {

    public CondicionVictorias() {
    }

    public CondicionVictorias (int victorias){
        super.valor = victorias;
    }

    @Override
    public boolean cumpleCondicion(Bicho bicho) {
        return bicho.getVictorias() > super.valor;
    }
}
