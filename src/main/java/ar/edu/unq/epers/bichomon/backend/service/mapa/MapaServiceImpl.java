package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.service.ubicacion.UbicacionService;

public class MapaServiceImpl implements MapaService {

    private UbicacionService ubicacionService;
    private HibernateEntrenadorDAO entrenadorDAO;

    @Override
    public void mover(String entrenador, String ubicacion) {
    /*se cambiará al entrenador desde su ubicación actual a la especificada por parametro.*/
        entrenadorDAO.setUbicacionDAO(ubicacion);
    }

    @Override
    public int cantidadEntrenadores(String ubicacion) {
        /*se deberá devolver la cantidad de entrenadores que se encuentren actualmente en dicha localización.*/
       return ubicacionService.getEntrenadoresEnUbicacion(ubicacion);
    }

    @Override
    public Bicho campeon(String dojo) {
        /*retorna el actual campeon del Dojo especificado.*/
        return null;
    }

    @Override
    public Bicho campeonHistorico(String dojo) {
        /*retorna el bicho que haya sido campeon por mas tiempo en el Dojo.*/
        return null;
    }
}
