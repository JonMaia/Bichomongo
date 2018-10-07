package ar.edu.unq.funcionalidades;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDao;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.dao.NivelDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateNivelDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.model.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.model.condicion.*;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EspecieEvolucionTest {

    private EspecieDao especieDao = new HibernateEspecieDaoImple();
    private EntrenadorDao entrenadorDao = new HibernateEntrenadorDaoImple();
    private BichoDao bichoDao = new HibernateBichoDaoImple();
    private NivelDao nivelDao = new HibernateNivelDaoImple();
    private DataService dataService = new DataServiceImpl(especieDao, entrenadorDao, bichoDao,nivelDao);
    private BichoService bichoService = new BichoServiceImpl();

    //@After
    //public void limpiarBase() {
    //    dataService.eliminarDatos();
    //}


    //TEST

    @Test
    public void una_especie_puede_no_tener_evolucion_ni_condiciones_de_evolucion(){
        Especie especie = dataService.crearEspecieBase();
        assertNull(especie.getEvolucion());
        assertTrue(especie.getCondicionDeEvolucion().isEmpty());
    }

    @Test
    public void una_especie_puede_tener_evolucion_y_no_tener_condiciones_de_evolucion(){
        Especie especie = dataService.crearEspecieBaseConEvolucion();
        assertNotNull(especie.getEvolucion());
        assertTrue(especie.getCondicionDeEvolucion().isEmpty());
    }

    @Test
    public void si_una_especie_tiene_evolucion_y_no_tiene_condiciones_de_evolucion_el_bicho_puede_evolucionar_siempre() {
        Bicho bichoBase = dataService.crearBichoConEntrenadorYEspecieConEvolucionSinCondicionDeEvolucion();
        assertTrue(bichoService.puedeEvolucionar(bichoBase.getId()));
    }

    @Test
    public void si_una_especie_no_tiene_evolucion_el_bicho_no_puede_evolucionar() {
        Bicho bichoBase = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
        assertFalse(bichoService.puedeEvolucionar(bichoBase.getId()));
    }

    @Test
    public void si_una_especie_tiene_evolucion_y_condiciones_de_energia_y_el_bicho_las_cumple_puede_evolucionar() {
        Bicho bichoBase = dataService.crearBichoConEnergia10ConEntrenadorYEspecieConEvolucionConCondicionDeEnergia0();
        assertTrue(bichoService.puedeEvolucionar(bichoBase.getId()));
    }

    @Test
    public void si_una_especie_tiene_evolucion_y_condiciones_de_victorias_y_el_bicho_las_cumple_puede_evolucionar() {
        Bicho bichoBase = dataService.crearBichoConVictorias10ConEntrenadorYEspecieConEvolucionConCondicionDeVictorias0();
        assertTrue(bichoService.puedeEvolucionar(bichoBase.getId()));
    }

    @Test
    public void si_una_especie_tiene_evolucion_y_condiciones_de_edad_y_el_bicho_las_cumple_puede_evolucionar() {
        Bicho bichoBase = dataService.crearBichoConEdad10ConEntrenadorYEspecieConEvolucionConCondicionDeEdad0();
        assertTrue(bichoService.puedeEvolucionar(bichoBase.getId()));
    }

    @Test
    public void si_una_especie_tiene_evolucion_y_condiciones_de_nivel_y_el_bicho_las_cumple_puede_evolucionar() {
        Bicho bichoBase = dataService.crearBichoConNivel10ConEntrenadorYEspecieConEvolucionConCondicionDeNivel0();
        assertTrue(bichoService.puedeEvolucionar(bichoBase.getId()));
    }

    @Test
    public void la_especie_de_un_bicho_cambia_al_evolucionar() {
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieConEvolucionSinCondicionDeEvolucion();
        Especie especieBase = bicho.getEspecie();

        bicho = bichoService.evolucionar(bicho.getId());

        assertNotEquals(bicho.getEspecie().getNombre(), especieBase.getNombre());
        assertEquals(bicho.getEspecie().getNombre(), especieBase.getEvolucion().getNombre());
    }



}
