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
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EspecieEvolucionTest {

    private EspecieDao especieDao = new HibernateEspecieDaoImple();
    private EntrenadorDao entrenadorDao = new HibernateEntrenadorDaoImple();
    private BichoDao bichoDao = new HibernateBichoDaoImple();
    private NivelDao nivelDao = new HibernateNivelDaoImple();
    private DataService dataService = new DataServiceImpl(especieDao, nivelDao);
    private BichoService bichoService = new BichoServiceImpl();

    private Especie crearEspecieBase(){
        return Runner.runInSession(() -> {
            Especie base =  new Especie("Base", TipoBicho.FUEGO,180, 75, 100, "/image/rojomon.jpg");
            this.especieDao.guardar(base);
            return base;
        });
    }

    private Especie crearEspecieBaseConEvolucion(){
        return Runner.runInSession(() -> {
            Especie base = crearEspecieBase();
            Especie evolucion = new Especie("Evolucion", TipoBicho.FUEGO,180, 75, 100, "/image/rojomon.jpg");
            base.setEvolucion(evolucion);
            this.especieDao.guardar(evolucion);
            this.especieDao.guardar(base);
            return base;
        });
    }

    private Especie crearEspecieConEvolucionConCondicionDeEnergia0(){
        //TODO: mover a testService
            return Runner.runInSession(() -> {
            Especie base = crearEspecieBaseConEvolucion();
            Condicion condicionEnergia = new CondicionEnergia(0);
            List<Condicion> condiciones = new ArrayList<Condicion>();
            condiciones.add(condicionEnergia);
            base.setCondicionDeEvolucion(condiciones);

            this.especieDao.guardar(base);
            return base;
        });
    }

    private Especie crearEspecieConEvolucionConCondicionDeVictorias0(){
        //TODO: mover a testService
        return Runner.runInSession(() -> {
            Especie base = crearEspecieBaseConEvolucion();
            Condicion condicionVictorias = new CondicionVictorias(0);
            List<Condicion> condiciones = new ArrayList<Condicion>();
            condiciones.add(condicionVictorias);
            base.setCondicionDeEvolucion(condiciones);

            this.especieDao.guardar(base);
            return base;
        });
    }

    private Especie crearEspecieConEvolucionConCondicionDeEdad0(){
        //TODO: mover a testService
        return Runner.runInSession(() -> {
            Especie base = crearEspecieBaseConEvolucion();
            Condicion condicionEdad = new CondicionEdad(0);
            List<Condicion> condiciones = new ArrayList<Condicion>();
            condiciones.add(condicionEdad);
            base.setCondicionDeEvolucion(condiciones);

            this.especieDao.guardar(base);
            return base;
        });
    }

    private Especie crearEspecieConEvolucionConCondicionDeNivel0(){
        //TODO: mover a testService
        return Runner.runInSession(() -> {
            Especie base = crearEspecieBaseConEvolucion();
            Condicion condicionNivel = new CondicionNivel(0);
            List<Condicion> condiciones = new ArrayList<Condicion>();
            condiciones.add(condicionNivel);
            base.setCondicionDeEvolucion(condiciones);

            this.especieDao.guardar(base);
            return base;
        });
    }

    private Entrenador crearEntrenedor(){
        Entrenador entrenador = new Entrenador();
        entrenador.setNombre("Ash");
        this.entrenadorDao.guardar(entrenador);
        return entrenador;
    }

    private Bicho crearBichoConEntrenadorYEspecieConEvolucionSinCondicionDeEvolucion(){
        //TODO: mover a testService
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieBaseConEvolucion();
            Bicho bichoBase = new Bicho(especieBase);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenedor();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    private Bicho crearBichoConEntrenadorYEspecieSinEvolucion(){
        //TODO: mover a testService
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieBase();
            Bicho bichoBase = new Bicho(especieBase);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenedor();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    private Bicho crearBichoConEnergia10ConEntrenadorYEspecieConEvolucionConCondicionDeEnergia0(){
        //TODO: mover a testService
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeEnergia0();
            Bicho bichoBase = new Bicho(especieBase);
            bichoBase.setEnergia(10);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenedor();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }


    private Bicho crearBichoConVictorias10ConEntrenadorYEspecieConEvolucionConCondicionDeVictorias0(){
        //TODO: mover a testService
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeVictorias0();
            Bicho bichoBase = new Bicho(especieBase);
            bichoBase.setVictorias(10);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenedor();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    private Bicho crearBichoConEdad10ConEntrenadorYEspecieConEvolucionConCondicionDeEdad0(){
        //TODO: mover a testService
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeEdad0();
            Bicho bichoBase = new Bicho(especieBase);
            bichoBase.setEdad(10);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenedor();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    private Bicho crearBichoConNivel10ConEntrenadorYEspecieConEvolucionConCondicionDeNivel0(){
        //TODO: mover a testService
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeNivel0();
            Bicho bichoBase = new Bicho(especieBase);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenedor();
            //entrenador.SetNivel(10);
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    //TEST

    @Test
    public void una_especie_puede_no_tener_evolucion_ni_condiciones_de_evolucion(){
        Especie especie = this.crearEspecieBase();
        assertNull(especie.getEvolucion());
        assertTrue(especie.getCondicionDeEvolucion().isEmpty());
    }

    @Test
    public void una_especie_puede_tener_evolucion_y_no_tener_condiciones_de_evolucion(){
        Especie especie = this.crearEspecieBaseConEvolucion();
        assertNotNull(especie.getEvolucion());
        assertTrue(especie.getCondicionDeEvolucion().isEmpty());
    }

    @Test
    public void si_una_especie_tiene_evolucion_y_no_tiene_condiciones_de_evolucion_el_bicho_puede_evolucionar_siempre() {
        Bicho bichoBase = crearBichoConEntrenadorYEspecieConEvolucionSinCondicionDeEvolucion();
        assertTrue(bichoService.puedeEvolucionar(bichoBase.getEntrenador().getNombre(),bichoBase.getId()));
    }

    @Test
    public void si_una_especie_no_tiene_evolucion_el_bicho_no_puede_evolucionar() {
        Bicho bichoBase = crearBichoConEntrenadorYEspecieSinEvolucion();
        assertFalse(bichoService.puedeEvolucionar(bichoBase.getEntrenador().getNombre(),bichoBase.getId()));
    }

    @Test
    public void si_una_especie_tiene_evolucion_y_condiciones_de_energia_y_el_bicho_las_cumple_puede_evolucionar() {
        Bicho bichoBase = crearBichoConEnergia10ConEntrenadorYEspecieConEvolucionConCondicionDeEnergia0();
        assertTrue(bichoService.puedeEvolucionar(bichoBase.getEntrenador().getNombre(),bichoBase.getId()));
    }

    @Test
    public void si_una_especie_tiene_evolucion_y_condiciones_de_victorias_y_el_bicho_las_cumple_puede_evolucionar() {
        Bicho bichoBase = crearBichoConVictorias10ConEntrenadorYEspecieConEvolucionConCondicionDeVictorias0();
        assertTrue(bichoService.puedeEvolucionar(bichoBase.getEntrenador().getNombre(),bichoBase.getId()));
    }

    @Test
    public void si_una_especie_tiene_evolucion_y_condiciones_de_edad_y_el_bicho_las_cumple_puede_evolucionar() {
        Bicho bichoBase = crearBichoConEdad10ConEntrenadorYEspecieConEvolucionConCondicionDeEdad0();
        assertTrue(bichoService.puedeEvolucionar(bichoBase.getEntrenador().getNombre(),bichoBase.getId()));
    }

    @Test
    public void si_una_especie_tiene_evolucion_y_condiciones_de_nivel_y_el_bicho_las_cumple_puede_evolucionar() {
        Bicho bichoBase = crearBichoConNivel10ConEntrenadorYEspecieConEvolucionConCondicionDeNivel0();
        assertTrue(bichoService.puedeEvolucionar(bichoBase.getEntrenador().getNombre(),bichoBase.getId()));
    }

    @Test
    public void la_especie_de_un_bicho_cambia_al_evolucionar() {
        Bicho bichoBase = crearBichoConEntrenadorYEspecieConEvolucionSinCondicionDeEvolucion();
        Especie especieBase = bichoBase.getEspecie();

        bichoService.evolucionar(bichoBase.getEntrenador().getNombre(),bichoBase.getId());

        assertNotEquals(bichoBase.getEspecie(), especieBase);
        assertEquals(bichoBase.getEspecie(), especieBase.getEvolucion());
    }



}
