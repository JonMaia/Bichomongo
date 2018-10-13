package ar.edu.unq.epers.bichomon.backend.service.mapa;

import ar.edu.unq.epers.bichomon.backend.dao.DojoDao;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoService;
import ar.edu.unq.epers.bichomon.backend.service.data.HibernateDojoDaoImple;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;

import java.util.Comparator;

public class MapaServiceImpl implements MapaService {

    private EntrenadorDao entrenadorDao;
    private UbicacionDao ubicacionDao;
    private DojoDao dojoDao;

    public MapaServiceImpl(){
        this.entrenadorDao = new HibernateEntrenadorDaoImple();
        this.ubicacionDao = new HibernateUbicacionDaoImple();
        this.dojoDao = new HibernateDojoDaoImple();
    }

    @Override
    public void mover(String entrenador, String ubicacion) {
        Runner.runInSession(() -> {
            Entrenador e = entrenadorDao.getById(entrenador);
            Ubicacion u = ubicacionDao.getById(ubicacion);

            e.moverA(u);

            entrenadorDao.actualizar(e);
            ubicacionDao.actualizar(u);
            return null;
            });
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
        Champion campeonHist = d.getCampeones().get(0);
        if (d.getCampeon() == null && d.getCampeones().size() == 0) {
            return null;
        }

        for (Champion campeon : d.getCampeones()){
            if (campeon.getPeriodo()>campeonHist.getPeriodo()){
                campeonHist = campeon;
            }

        }

        return campeonHist.getBicho();

    }
}
