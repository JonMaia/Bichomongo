package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieService;
import ar.edu.unq.epers.bichomon.backend.service.especie.EspecieServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.*;

public class EspecieServiceTest {

    private EspecieService especieService = new EspecieServiceImpl();
    private DataService dataService = new DataServiceImpl();

    @Before
    public void clear(){
        dataService.eliminarDatos();
    }

    @Test
    public void si_existe_una_sola_especie_con_bichos_en_manos_de_entrenadores_entonces_es_popular(){
        Bicho bicho = this.dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
        List<Especie> especiesPopulares = this.especieService.populares();

        assertEquals(1, especiesPopulares.size());
        assertEquals(bicho.getEspecie().getNombre(),especiesPopulares.get(0).getNombre());
    }

    @Test
    public void si_existen_mas_de_10_especies_con_bichos_en_manos_de_entrenadores_entonces_devuelve_las_10_con_mas_bichos(){
        List<Bicho> bichos = this.dataService.crear2BichosPara10EspeciesYUnBichoPara2EspeciesConEntrenador();

        List<String> nombresEspecie = new ArrayList<String>();
        for (Bicho bicho : bichos) {
            if(bicho.getEspecie().getCantidadBichos() == 2 && !nombresEspecie.contains(bicho.getEspecie().getNombre()))
                nombresEspecie.add(bicho.getEspecie().getNombre());
        }
        
        List<Especie> especiesPopulares = this.especieService.populares();

        assertEquals(10,especiesPopulares.size());

        for (Especie especie: especiesPopulares) {
            assertTrue(nombresEspecie.contains(especie.getNombre()));
        }
    }

    @Test
    public void si_existen_bichos_sin_entrenador_no_se_consideran_para_la_cantidad_de_bichos_de_las_especies_populares(){
        Bicho bicho = this.dataService.crearBchoConEsspecieSinEntrenador();

        List<Especie> especiesPopulares = this.especieService.populares();

        assertTrue(especiesPopulares.isEmpty());
    }


    @Test
    public void si_existe_una_sola_especie_con_bichos_en_la_guarderia_entonces_es_impopular(){
        Bicho bicho = this.dataService.crearBchoConEsspecieSinEntrenador();

        List<Especie> especiesImpopulares = this.especieService.impopulares();

        assertEquals(1, especiesImpopulares.size());
        assertEquals(bicho.getEspecie().getNombre(),especiesImpopulares.get(0).getNombre());
    }

    @Test
    public void si_existen_mas_de_10_especies_con_bichos_sin_entrenadores_entonces_devuelve_las_10_con_mas_bichos(){
        List<Bicho> bichos = this.dataService.crear2BichosPara10EspeciesYUnBichoPara2EspeciesSinEntrenador();

        List<String> nombresEspecie = new ArrayList<String>();
        for (Bicho bicho : bichos) {
            if(bicho.getEspecie().getCantidadBichos() == 2 && !nombresEspecie.contains(bicho.getEspecie().getNombre()))
                nombresEspecie.add(bicho.getEspecie().getNombre());
        }

        List<Especie> especiesImpopulares = this.especieService.impopulares();

        assertEquals(10,especiesImpopulares.size());

        for (Especie especie: especiesImpopulares) {
            assertTrue(nombresEspecie.contains(especie.getNombre()));
        }
    }
}
