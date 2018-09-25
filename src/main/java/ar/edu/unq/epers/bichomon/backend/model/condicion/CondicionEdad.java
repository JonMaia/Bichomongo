package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;

public class CondicionEdad extends Condicion {

    @Override
    public boolean cumpleCondicion(Bicho bicho) {
        //return bicho.getFechaCaptura() > edad;
        return true;
    }
}
