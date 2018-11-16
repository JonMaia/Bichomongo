package ar.edu.unq.dao.mongoDB;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.EventoDao;
import ar.edu.unq.epers.bichomon.backend.service.FeedService.FeedService;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.mongoImple.EventoDaoImple;
import ar.edu.unq.epers.bichomon.backend.service.FeedService.FeedServiceImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaServiceImpl;
import ar.edu.unq.service.MapaServiceTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FeedServiceDAOTest {

	private static final String CODIGO_PRODUCTO_1 = "111";
	private static final String CODIGO_PRODUCTO_2 = "222";
	private static final String CODIGO_PRODUCTO_3 = "333";
	private static final String CODIGO_PRODUCTO_4 = "444";
	
	private FeedService dao;
    private DataService dataService;
    private MapaService mapaService;
    private UbicacionDao ubicacionDao;
    private EntrenadorDao entrenadorDao;
    private EventoDao eventoDao;
	@SuppressWarnings("unchecked")
	private <T> List<T> list(T... elements) {
		return Arrays.asList(elements);
	}


    Entrenador iris;

	@Before
	public void setup() {
		this.dao = new FeedServiceImple();
        this.dataService = new DataServiceImpl();
        this.mapaService = new MapaServiceImpl();
        this.ubicacionDao = new HibernateUbicacionDaoImple();
        this.entrenadorDao = new HibernateEntrenadorDaoImple();
        this.eventoDao = new EventoDaoImple();

    //    crearMapa();
    //    Pueblo yantra = (Pueblo) ubicacionDao.recuperar("Ciudad Yantra");
    //    this.iris = this.dataService.crearEntrenadorConUbicacion("Iris" , yantra);
    }
	
	@After
	public void dropAll() {
		//this.dao.deleteAll();
	}
	
	@Test
	public void elEntrenadorSeMueveYSeGeneraUnEventoDeArribo() {
	    //TODO: Implementar
        Assert.assertTrue(false);
	}

    private void crearMapa() {
        Pueblo yantra = crearPuebloConNombre("Ciudad Yantra");
        Pueblo cromlech = crearPuebloConNombre("Pueblo Cromlech");
        Pueblo relieve = crearPuebloConNombre("Ciudad Relieve");
        Pueblo petroglifo = crearPuebloConNombre("Pueblo Petroglifo");
        Pueblo tempera = crearPuebloConNombre("Ciudad Tempera");
        Pueblo romantis = crearPuebloConNombre("Ciudad Romantis");
        Pueblo luminalia = crearPuebloConNombre("Ciudad Luminalia");
        Pueblo fresco = crearPuebloConNombre("Pueblo Fresco");
        Pueblo acuarela = crearPuebloConNombre("Pueblo Acuarela");
        Pueblo vanitas = crearPuebloConNombre("Pueblo Vanitas");
        Pueblo novarte = crearPuebloConNombre("Ciudad Novarte");
        Pueblo mosaico = crearPuebloConNombre("Pueblo Mosaico");
        Pueblo fluxus = crearPuebloConNombre("Ciudad Fluxus");
        Pueblo fractal = crearPuebloConNombre("Ciudad Fractal");
        Pueblo batik = crearPuebloConNombre("Ciudad Batik");
        Pueblo boceto = crearPuebloConNombre("Pueblo Boceto");
        Pueblo cenit = crearPuebloConNombre("Palacio Cenit(4)");



        this.mapaService.conectar(yantra.getNombre(), cromlech.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(yantra.getNombre(), tempera.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(cromlech.getNombre(), relieve.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(relieve.getNombre(), petroglifo.getNombre(), MapaServiceTest.SingletonCaminos.getMaritimo());
        this.mapaService.conectar(relieve.getNombre(), cenit.getNombre(), MapaServiceTest.SingletonCaminos.getAereo());

        this.mapaService.conectar(petroglifo.getNombre(), cromlech.getNombre(), MapaServiceTest.SingletonCaminos.getMaritimo());
        this.mapaService.conectar(petroglifo.getNombre(), relieve.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(petroglifo.getNombre(), acuarela.getNombre(), MapaServiceTest.SingletonCaminos.getAereo());

        this.mapaService.conectar(tempera.getNombre(), romantis.getNombre(), MapaServiceTest.SingletonCaminos.getMaritimo());
        this.mapaService.conectar(tempera.getNombre(), yantra.getNombre(), MapaServiceTest.SingletonCaminos.getMaritimo());

        this.mapaService.conectar(romantis.getNombre(), tempera.getNombre(), MapaServiceTest.SingletonCaminos.getMaritimo());
        this.mapaService.conectar(romantis.getNombre(), luminalia.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(romantis.getNombre(), fresco.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());

        this.mapaService.conectar(vanitas.getNombre(), cenit.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());

        this.mapaService.conectar(cenit.getNombre(), luminalia.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(cenit.getNombre(), novarte.getNombre(), MapaServiceTest.SingletonCaminos.getAereo());
        this.mapaService.conectar(cenit.getNombre(), vanitas.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());

        this.mapaService.conectar(fresco.getNombre(), romantis.getNombre(), MapaServiceTest.SingletonCaminos.getAereo());
        this.mapaService.conectar(fresco.getNombre(), mosaico.getNombre(), MapaServiceTest.SingletonCaminos.getAereo());
        this.mapaService.conectar(fresco.getNombre(), luminalia.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());

        this.mapaService.conectar(mosaico.getNombre(), fractal.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());

        this.mapaService.conectar(fluxus.getNombre(), fresco.getNombre(), MapaServiceTest.SingletonCaminos.getMaritimo());
        this.mapaService.conectar(fluxus.getNombre(), mosaico.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(fluxus.getNombre(), fractal.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());

        this.mapaService.conectar(fractal.getNombre(), acuarela.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());

        this.mapaService.conectar(novarte.getNombre(), fractal.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(novarte.getNombre(), vanitas.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());

        this.mapaService.conectar(acuarela.getNombre(), boceto.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(acuarela.getNombre(), novarte.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(acuarela.getNombre(), fractal.getNombre(), MapaServiceTest.SingletonCaminos.getAereo());

        this.mapaService.conectar(boceto.getNombre(), acuarela.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(boceto.getNombre(), batik.getNombre(), MapaServiceTest.SingletonCaminos.getTerrestre());
        this.mapaService.conectar(boceto.getNombre(), fractal.getNombre(), MapaServiceTest.SingletonCaminos.getAereo());

        this.mapaService.conectar(batik.getNombre(), fractal.getNombre(), MapaServiceTest.SingletonCaminos.getAereo());
    }

    private Pueblo crearPuebloConNombre(String nombre) {
        Pueblo pueblo = new Pueblo();
        pueblo.setNombre(nombre);
        this.mapaService.crearUbicacion(pueblo);
        return pueblo;
    }

}
