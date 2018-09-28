package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;

public class CondicionNivel extends Condicion{

    @Override
    public boolean cumpleCondicion(Bicho bicho) {
        //return bicho.getEntrenador().getNivel() > nivel;
        return false;
    }
}
