package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;

public interface UbicacionDao extends GenericDao<Ubicacion, String> {
    Ubicacion recuperar(String nombre);

    Guarderia recuperarGuarderia(String nombre);
}
