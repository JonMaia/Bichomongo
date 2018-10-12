package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardService;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardServiceImple;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeaderboardServiceTest {

    private UbicacionDao ubicacionDao = new HibernateUbicacionDaoImple();
    private DataService dataService = new DataServiceImpl();
    private LeaderboardService leaderboardService = new LeaderboardServiceImple();

    @Test
    public void si_hay_un_solo_entrenador_con_un_bicho_campeon_este_es_campeon() {
        Dojo dojo = this.dataService.crearDojo();
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();

        dojo.setCampeon(bicho);

        List<Entrenador> campeones = this.leaderboardService.campeones();

        assertEquals(1, campeones.size());
        assertTrue(campeones.contains(bicho.getEntrenador()));
    }

    @Test
    public void si_hay_mas_de_un_entrenador_con_un_bicho_campeon_los_ordena() {
         Runner.runInSession(() -> {
            Dojo dojo = this.dataService.crearDojo();
            Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
            dojo.setCampeon(bicho);
            ubicacionDao.guardar(dojo);

            Bicho bicho2 = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
            dojo.setCampeon(bicho2);

            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ubicacionDao.guardar(dojo);
            List<Entrenador> campeones = this.leaderboardService.campeones();

            assertTrue(campeones.size() == 2);
            assertEquals(campeones.indexOf(0), bicho2);
            assertEquals(campeones.indexOf(1), bicho);
            return null;
        });
    }

    @Test
    public void si_hubo_dos_bichos_campeones_de_la_misma_especie_y_uno_de_otra_retorna_la_especie_con_dos_bichos(){
        Runner.runInSession(() -> {
            Dojo dojo = this.dataService.crearDojo();
            Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
            dojo.setCampeon(bicho);
            ubicacionDao.guardar(dojo);

            Bicho bicho2 = dataService.crearBichoDeEspecieYDeEntrenador("OtraEspecie","OtroEntrenador");
            dojo.setCampeon(bicho2);
            ubicacionDao.guardar(dojo);

            Bicho bicho3 = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
            dojo.setCampeon(bicho3);
            ubicacionDao.guardar(dojo);

            Especie especieLider = leaderboardService.especieLider();

            assertEquals(bicho.getEspecie().getNombre(), especieLider.getNombre());
            return null;
        });
    }

}
