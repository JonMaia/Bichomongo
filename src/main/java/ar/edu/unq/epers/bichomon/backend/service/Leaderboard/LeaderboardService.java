package ar.edu.unq.epers.bichomon.backend.service.Leaderboard;

import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Especie;

import java.util.List;

public interface LeaderboardService {

    /**
     *
     * @return aquellos entrenadores que posean un bicho que actualmente sea campeon de un Dojo,
     * retornando primero aquellos que ocupen el puesto de campeon desde hace mas tiempo.
     */
    List<Entrenador> campeones();

    /**
     *
     * @return la especie que tenga mas bichos que haya sido campeones de cualquier dojo. Cada bicho
     * deberá ser contando una sola vez (independientemente de si haya sido coronado campeon mas de
     * una vez o en mas de un Dojo)
     */
    Especie especieLider();

    /**
     *
     * @return los diez primeros entrenadores para los cuales el valor de poder combinado de todos
     * sus bichos sea superior.
     */
    List<Entrenador> lideres();


}
