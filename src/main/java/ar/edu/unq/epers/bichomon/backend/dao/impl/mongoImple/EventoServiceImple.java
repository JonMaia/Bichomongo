package ar.edu.unq.epers.bichomon.backend.dao.impl.mongoImple;

import ar.edu.unq.epers.bichomon.backend.dao.EventoService;
import ar.edu.unq.epers.bichomon.backend.dao.FeedService;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Evento;

import java.util.List;

public class EventoServiceImple extends GenericMongoDAO<Evento> implements EventoService {

    public EventoServiceImple(Class<Evento> entityType) {
        super(entityType);
    }

}
