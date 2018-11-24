package ar.edu.unq.dao.mongoDB;

import ar.edu.unq.epers.bichomon.backend.dao.EventoDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.mongoImple.EventoDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.*;
import ar.edu.unq.epers.bichomon.backend.model.Pueblo;
import ar.edu.unq.epers.bichomon.backend.service.FeedService.FeedService;
import ar.edu.unq.epers.bichomon.backend.service.FeedService.FeedServiceImple;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.service.MapaServiceTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class EventoDAOTest {

    private DataService dataService = new DataServiceImpl();
    private EventoDao eventoDAO = new EventoDaoImple();

    @After
    public void eliminar(){
        dataService.eliminarDatos();
    }


    @Test
    public void se_crea_un_Abandono_y_se_persiste() {

        String nombreentrenador = "El_Entrenador";
        String nombreUbicacion = "La_Ubicacion";
        LocalDate fecha = LocalDate.now();
        int idBicho = 1;

        Abandono abandono = new Abandono();
        abandono.setEntrenador(nombreentrenador);
        abandono.setFecha(fecha);
        abandono.setUbicacion(nombreUbicacion);
        abandono.setBicho(idBicho);

        eventoDAO.save(abandono);
        Abandono abandono2 = (Abandono) eventoDAO.findAbandonos().get(0);

        Assert.assertEquals(abandono.getId(),abandono2.getId());
    }

    @Test
    public void se_crea_una_Captura_y_se_persiste() {

        String nombreentrenador = "El_Entrenador";
        String nombreUbicacion = "La_Ubicacion";
        LocalDate fecha = LocalDate.now();

        Captura captura = new Captura();
        captura.setEntrenador(nombreentrenador);
        captura.setFecha(fecha);
        captura.setUbicacion(nombreUbicacion);

        eventoDAO.save(captura);
        Captura captura2 = (Captura) eventoDAO.findCapturas().get(0);

        Assert.assertEquals(captura2.getId(),captura2.getId());
    }

    @Test
    public void se_crea_una_Coronacion_y_se_persiste() {

        String nombreentrenador = "El_Entrenador";
        String nombreUbicacion = "La_Ubicacion";
        LocalDate fecha = LocalDate.now();

        Coronacion coronacion = new Coronacion();
        coronacion.setEntrenador(nombreentrenador);
        coronacion.setFecha(fecha);
        coronacion.setUbicacion(nombreUbicacion);

        eventoDAO.save(coronacion);
        Coronacion coronacion2 = (Coronacion) eventoDAO.findCoronaciones().get(0);

        Assert.assertEquals(coronacion.getId(),coronacion2.getId());
    }
    @Test
    public void se_crea_un_Arribo_y_se_persiste() {

        String nombreentrenador = "El_Entrenador";
        String nombreUbicacion = "La_Ubicacion";
        LocalDate fecha = LocalDate.now();

        Arribo arribo = new Arribo();
        arribo.setEntrenador(nombreentrenador);
        arribo.setFecha(fecha);
        arribo.setUbicacion(nombreUbicacion);

        eventoDAO.save(arribo);
        Arribo arribo2 = (Arribo) eventoDAO.findArribos().get(0);

        Assert.assertEquals(arribo.getId(),arribo2.getId());
    }

}
