package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.service.entrenador.EntrenadorService;

public class MapaServiceImpl implements MapaService {

    private EntrenadorService entrenadorService;
    private HibernateUbicacionDaoImple hibernateUbicacionDaoImple;

    @Override
    public void mover(String entrenador, String ubicacion) {
        entrenadorService.setUbicacionEntrenador(entrenador, ubicacion);
    }

    @Override
    public int cantidadEntrenadores(String ubicacion) {
        /*se deberá devolver la cantidad de entrenadores que se encuentren actualmente en dicha localización.*/
       return 0;
  }

    @Override
    public Bicho campeon(String dojo) {
        /*retorna el actual campeon del Guarderia especificado.*/
        return null;
    }

    @Override
    public Bicho campeonHistorico(String dojo) {
        /*retorna el bicho que haya sido campeon por mas tiempo en el Guarderia.*/
        return null;

    }
}
