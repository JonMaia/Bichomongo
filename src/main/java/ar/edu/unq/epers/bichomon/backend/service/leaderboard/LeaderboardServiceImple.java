package ar.edu.unq.epers.bichomon.backend.service.leaderboard;

import ar.edu.unq.epers.bichomon.backend.dao.ChampionDao;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateChampionDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Champion;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.service.ubicacion.UbicacionService;

import java.util.*;

public class LeaderboardServiceImple implements LeaderboardService {

    private UbicacionDao ubicacionDao;

    private ChampionDao championDao;

    private EntrenadorDao entrenadorDao;

    public LeaderboardServiceImple() {
        this.ubicacionDao = new HibernateUbicacionDaoImple();
        this.championDao = new HibernateChampionDaoImple();
        this.entrenadorDao = new HibernateEntrenadorDaoImple();
    }

    @Override
    public List<Entrenador> campeones() {
        return championDao.findByActualChampion();
    }

    @Override
    public Especie especieLider() {
        return championDao.getEspecieLider();

    }

    @Override
    public List<Entrenador> lideres() {
        return entrenadorDao.conMejoresBichos();
    }
}
