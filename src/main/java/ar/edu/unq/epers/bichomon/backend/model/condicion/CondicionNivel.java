package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;

public class CondicionNivel extends Condicion{

    @Override
    public boolean cumpleCondicion(Bicho bicho) {
        //return bicho.getEntrenador().getNivel() > nivel;
        return false;
    }
}
