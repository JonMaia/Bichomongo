package ar.edu.unq.epers.bichomon.backend.service.entrenador;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;
import ar.edu.unq.epers.bichomon.backend.service.GenericService;

public interface EntrenadorService extends GenericService<Entrenador,String, EntrenadorDao> {

    void setUbicacionEntrenador(String entrenador, String ubicacion);


    void capturaBicho(Entrenador entrenador, Bicho bicho);

    boolean puedeCapturarOtroBichomon(Entrenador entrenador);
}
