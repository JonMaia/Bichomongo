package ar.edu.unq.dao.mongoDB;

import ar.edu.unq.epers.bichomon.backend.dao.EventoDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.mongoImple.EventoDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Arribo;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Evento;
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

public class FeedServiceDAOTest {

    private DataService dataService = new DataServiceImpl();
    private EventoDao eventoDAO = new EventoDaoImple();

    @After
    public void eliminar(){
        dataService.eliminarDatos();
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

        //Evento arribo2 = eventoDAO.findArribos().get(0);


        //Assert.assertEquals();
	}

}
