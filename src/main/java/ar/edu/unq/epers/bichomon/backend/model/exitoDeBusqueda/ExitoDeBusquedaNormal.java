package ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda;

public class ExitoDeBusquedaNormal implements ExitoDeBusqueda {
    @Override
    public boolean ejecutar(Double factorTiempo, Double factorNivel, Double factorPoblacion) {
        return factorTiempo * factorNivel * factorPoblacion * Math.random() > 0.5;
    }
}
