package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDao;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardService;
import ar.edu.unq.epers.bichomon.backend.service.leaderboard.LeaderboardServiceImple;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LeaderboardServiceTest {

    private UbicacionDao ubicacionDao = new HibernateUbicacionDaoImple();
    private DataService dataService = new DataServiceImpl();
    private LeaderboardService leaderboardService = new LeaderboardServiceImple();
    private BichoDao bichoDao = new HibernateBichoDaoImple();
/*
    @After
    public void dropAll(){
        dataService.eliminarDatos();
    }
*/

    @Test
    public void si_hay_un_solo_entrenador_con_un_bicho_campeon_este_es_campeon() {
        Runner.runInSession(() -> {
        Dojo dojo = this.dataService.crearDojo();
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();

        dojo.setCampeon(bicho);

        List<Entrenador> campeones = this.leaderboardService.campeones();

        assertEquals(1, campeones.size());
        assertTrue(campeones.contains(bicho.getEntrenador()));
        return null;
        });
    }

    @Test
    public void si_hay_mas_de_un_entrenador_con_un_bicho_campeon_los_ordena() {
         Runner.runInSession(() -> {
            Dojo dojo = this.dataService.crearDojo();
            Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
            Champion champion1 = dojo.setCampeon(bicho);

            ubicacionDao.guardar(dojo);




            Bicho bicho2 = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
            dojo.setCampeon(bicho2);

            champion1.setPeriodo(500000000);


            ubicacionDao.guardar(dojo);
            List<Entrenador> campeones = this.leaderboardService.campeones();

            assertTrue(campeones.size() == 1);
            assertEquals(campeones.get(0), bicho.getEntrenador());
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


    @Test
    public void dosEntrenadoresconUnoYDosBichos(){

        Runner.runInSession(() -> {
            Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
            Bicho bicho2 = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();



            List<Entrenador> lideres = leaderboardService.lideres();
            //hay otro lider con un bichomon que viene de otro test
            assertEquals(2, lideres.size());
            assertEquals("Ash", lideres.get(0).getNombre());
            return null;
        });
    }




}
