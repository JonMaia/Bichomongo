package ar.edu.unq.epers.bichomon.backend.dao.impl.mongoImple;

import ar.edu.unq.epers.bichomon.backend.dao.EventoDao;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Evento;

public class EventoDaoImple extends GenericMongoDAOImple<Evento> implements EventoDao {
    public EventoDaoImple() {
        super(Evento.class);
    }
}
