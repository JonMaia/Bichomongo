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
        List<Champion> campeones = championDao.findByActualChampion();
        List<Entrenador> entrenadoresCampeones = new ArrayList<>();
        campeones.sort(Comparator.comparing(Champion::getPeriodo));
        for(Champion campeon : campeones){
            entrenadoresCampeones.add(campeon.getBicho().getEntrenador());
        }
        return entrenadoresCampeones;
    }

    @Override
    public Especie especieLider() {
        Map<Especie,Integer> res = new HashMap<Especie,Integer>();
        for(Bicho bicho : championDao.getBichoChamion()){
            if(!res.containsKey(bicho.getEspecie()))
                res.put(bicho.getEspecie(),0);
            res.put(bicho.getEspecie(),res.get(bicho.getEspecie())+1);
        }
        ArrayList<Map.Entry<Especie, Integer>> listSet = new ArrayList<>(res.entrySet());
        listSet.sort(Comparator.comparing(Map.Entry::getValue));

        return listSet.get(0).getKey();
    }

    @Override
    public List<Entrenador> lideres() {
        return entrenadorDao.conMejoresBichos();
    }
}
