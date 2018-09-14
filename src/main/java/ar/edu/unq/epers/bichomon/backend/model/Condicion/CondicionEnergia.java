package ar.edu.unq.epers.bichomon.backend.model.Condicion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

public class CondicionEnergia extends Condicion {

    @Override
    public boolean cumpleCondicion(Bicho bicho) {
        return bicho.getEnergia() > super.valor;
    }
}
