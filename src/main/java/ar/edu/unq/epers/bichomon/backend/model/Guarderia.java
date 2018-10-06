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
    public Bicho entregarBicho(Entrenador unEntrenador) {
        return null;
    }

    @Override
    public void buscar(Entrenador entrenador) {
        entrenador.obtenerBicho(super.unaBusqueda.realizarBusqueda(entrenador, this));
    }

}
