package ar.edu.unq.epers.bichomon.backend.service.leaderboard;

import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.service.ubicacion.UbicacionService;

import java.util.List;

public class LeaderboardServiceImple implements LeaderboardService {

    private UbicacionDao ubicacionDao;

    public LeaderboardServiceImple(UbicacionDao ubicacionDao) {
        this.ubicacionDao = ubicacionDao;
    }

    public LeaderboardServiceImple() {
        this.ubicacionDao = new HibernateUbicacionDaoImple();
    }

    @Override
    public List<Entrenador> campeones() {
        return null;
    }

    @Override
    public Especie especieLider() {
        return null;
    }

    @Override
    public List<Entrenador> lideres() {
        return null;
    }
}
