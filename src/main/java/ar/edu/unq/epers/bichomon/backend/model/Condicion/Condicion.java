package ar.edu.unq.epers.bichomon.backend.model.Condicion;

import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;

public abstract class Condicion {

    public int valor;

    public abstract boolean cumpleCondicion(Bicho bicho);
}
