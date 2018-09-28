package ar.edu.unq.epers.bichomon.backend.model;

import java.util.List;

public class Guarderia extends Ubicacion {

    @Override
    public List<Bicho> getBichomones() {
        return super.bichomones;
    }

    @Override
    public Boolean dejarBicho(Bicho unBicho) {
        super.bichomones.add(unBicho);
        return true;
    }

    @Override
    public Bicho entregarBicho(Bicho unBicho) {
        return unBicho;
    }

    @Override
    public void buscar(Entrenador entrenador) {
        entrenador.obtenerBicho(super.unaBusqueda.realizarBusqueda(entrenador, this));
    }

}
