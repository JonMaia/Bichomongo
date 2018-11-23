package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Evento;
import ar.edu.unq.epers.bichomon.backend.service.FeedService.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.FeedService.FeedServiceImple;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
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

}
