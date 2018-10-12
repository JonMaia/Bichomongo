package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Champion;
import ar.edu.unq.epers.bichomon.backend.model.Especie;

import java.util.List;


public interface ChampionDao extends GenericDao<Champion, Integer> {

    /**
     * busca los campeones de cada dojo
     * @return
     */
    List<Champion> findByActualChampion();

    /**
     * Retorna la especie lider
     * @return
     */
    List<Bicho> getBichoChamion();
}
