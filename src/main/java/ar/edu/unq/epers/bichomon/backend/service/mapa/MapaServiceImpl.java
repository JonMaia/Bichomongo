package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.dao.DojoDao;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.model.*;

import java.util.Comparator;

public class MapaServiceImpl implements MapaService {

    private EntrenadorDao entrenadorDao;
    private UbicacionDao ubicacionDao;
    private DojoDao dojoDao;

    @Override
    public void mover(String entrenador, String ubicacion) {
        Entrenador e = entrenadorDao.getById(entrenador);
        Ubicacion u = ubicacionDao.getById(ubicacion);

        e.moverA(u);

        entrenadorDao.actualizar(e);
    }

    @Override
    public int cantidadEntrenadores(String ubicacion) {
        /*se deberá devolver la cantidad de entrenadores que se encuentren actualmente en dicha localización.*/
        Ubicacion u = ubicacionDao.getById(ubicacion);
        return u.getEntrenadores().size();
    }

    @Override
    public Bicho campeon(String dojo) {
        /*retorna el actual campeon del Dojo especificado.*/
        Dojo d = dojoDao.getById(dojo);
        return d.getCampeon().getBicho();
    }

    @Override
    public Bicho campeonHistorico(String dojo) {
        /*retorna el bicho que haya sido campeon por mas tiempo en el Dojo.*/
        Dojo d = dojoDao.getById(dojo);

        return null;

    }
}
