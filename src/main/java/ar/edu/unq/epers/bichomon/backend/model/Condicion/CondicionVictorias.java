package ar.edu.unq.epers.bichomon.backend.model.Condicion;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;

public class CondicionVictorias extends Condicion {

    public CondicionVictorias (int victorias){
        super.valor = victorias;
    }

    @Override
    public boolean cumpleCondicion(Bicho bicho) {
        return bicho.getVictorias() > super.valor;
    }
}
