package ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda;

public class ExitoDeBusquedaSiempreFalse implements ExitoDeBusqueda {
    @Override
    public boolean ejecutar(Double factorTiempo, Double factorNivel, Double factorPoblacion) {
        return false;
    }
}
