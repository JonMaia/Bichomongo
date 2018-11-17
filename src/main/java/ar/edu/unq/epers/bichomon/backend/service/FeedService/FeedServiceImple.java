package ar.edu.unq.epers.bichomon.backend.service.FeedService;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.EventoDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.mongoImple.EventoDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.FeedService.FeedService;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Evento;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class FeedServiceImple implements FeedService {

    private MapaService mapaService;
    private EntrenadorDao entrenadorDAO;
    private EventoDao eventoDAO;

    public FeedServiceImple(){
        mapaService = new MapaServiceImpl();
        entrenadorDAO = new HibernateEntrenadorDaoImple();
        eventoDAO = new EventoDaoImple();
    }
    @Override
    public List<Evento> feedEntrenador(String entrenador) {
        return null;
    }

    @Override
    public List<Evento> feedUbicacion(String entrenador) {
        List<Evento> eventos = new ArrayList<>();
        List<Ubicacion> ubicacionesConectadas = new ArrayList<>();
        Entrenador trainer = Runner.runInSession(()  -> {
            return entrenadorDAO.getById(entrenador);
        });
        ubicacionesConectadas = mapaService.todosLosConectados(trainer.getUbicacion().getNombre());

        //eventos = eventoDAO.eventosPorEntrenadorYUbicaciones(entrenador, ubicacionesConectadas); TODO REDEFINIR TODOS LOS CONECTADOS PARA QUE DEVUELVA STRING

        return eventos;
    }
}
