package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.dao.Neo4JUbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.Neo4JImple.Neo4JUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Evento;
import ar.edu.unq.epers.bichomon.backend.service.FeedService.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.FeedService.FeedServiceImple;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.entrenador.EntrenadorService;
import ar.edu.unq.epers.bichomon.backend.service.entrenador.EntrenadorServiceImple;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FeedServiceTest {

    private DataService dataService = new DataServiceImpl();
    private MapaService mapaService = new MapaServiceImpl();
    private FeedService feedService = new FeedServiceImple();
    private Neo4JUbicacionDao ubicacionDao = new Neo4JUbicacionDaoImple();
    private EntrenadorService entrenadorService = new EntrenadorServiceImple();
    private BichoService bichoService = new BichoServiceImpl();

    @After
    public void eliminar(){
        dataService.eliminarDatos();
    }

    @Test
    public void si_un_entrenador_se_mueve_se_crea_un_evento() {
        String nombreOrigen ="PueblitoOrigen";
        Pueblo puebloOrigen = new Pueblo();
        puebloOrigen.setNombre(nombreOrigen);
        this.mapaService.crearUbicacion(puebloOrigen);

        String nombreDestino ="PueblitoDestino";
        Pueblo puebloDestino = new Pueblo();
        puebloDestino.setNombre(nombreDestino);
        this.mapaService.crearUbicacion(puebloDestino);

        this.mapaService.conectar(puebloOrigen.getNombre(), puebloDestino.getNombre(),new Terrestre());
        this.mapaService.conectar(puebloDestino.getNombre(), puebloOrigen.getNombre(),new Aereo());

        Entrenador entrenador = dataService.crearEntrenadorConUbicacionYPlata("unEntrenador", puebloOrigen);

        mapaService.mover(entrenador.getNombre(),puebloDestino.getNombre());

        List<Evento> eventos = feedService.feedUbicacion(entrenador.getNombre());

        Assert.assertEquals(1,eventos.size());
    }

    @Test
    public void entrenador_atrapa_un_bichomon_y_se_crea_el_evento(){

        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucionEnPuebloConProbabilidad100();
        Entrenador entrenador = bicho.getEntrenador();
        Ubicacion pueblo = entrenador.getUbicacion();
        ubicacionDao.create(pueblo);

        this.bichoService.buscar(entrenador.getNombre());

        List<Evento> eventos = feedService.feedEntrenador(entrenador.getNombre());

        Assert.assertEquals(1, eventos.size());
    }

    @Test
    public void entrenador_se_mueve_abandona_bicho_y_registra_eventos(){
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucionEnPuebloConProbabilidad100();
        Entrenador entrenador = bicho.getEntrenador();
        Ubicacion pueblo = entrenador.getUbicacion();
        ubicacionDao.create(pueblo);

        String nombreDestino ="Guarderia";
        Guarderia guarderia = new Guarderia();
        guarderia.setNombre(nombreDestino);
        this.mapaService.crearUbicacion(guarderia);

        this.mapaService.conectar(pueblo.getNombre(), guarderia.getNombre(),new Terrestre());
        this.mapaService.conectar(guarderia.getNombre(), pueblo.getNombre(),new Aereo());

        mapaService.mover(entrenador.getNombre(),guarderia.getNombre());

        this.bichoService.abandonar(entrenador.getNombre(), bicho.getId());

        List<Evento> eventos = feedService.feedEntrenador(entrenador.getNombre());

        Assert.assertEquals(2, eventos.size());

    }

    @Test
    public void entrenador_combate_en_dojo_y_ocurre_evento(){
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieYEnergia200EnDojo();
        Entrenador entrenador = bicho.getEntrenador();
        Ubicacion dojo = entrenador.getUbicacion();
        ubicacionDao.create(dojo);
        ResultadoCombate resultadoCombate = bichoService.duelo(entrenador.getNombre(), bicho.getId());
        List<Evento> eventos = feedService.feedEntrenador(entrenador.getNombre());
        Assert.assertEquals(1, eventos.size());

    }

}
