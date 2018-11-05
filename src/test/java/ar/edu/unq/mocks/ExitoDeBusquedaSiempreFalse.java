package ar.edu.unq.mocks;

import ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda.ExitoDeBusqueda;

public class ExitoDeBusquedaSiempreFalse implements ExitoDeBusqueda {
    @Override
    public boolean ejecutar(Double factorTiempo, Double factorNivel, Double factorPoblacion) {
        return false;
    }
}
