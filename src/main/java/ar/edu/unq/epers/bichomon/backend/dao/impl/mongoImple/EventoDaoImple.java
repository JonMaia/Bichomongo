package ar.edu.unq.epers.bichomon.backend.dao.impl.mongoImple;

import ar.edu.unq.epers.bichomon.backend.dao.EventoDao;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Evento;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.util.ArrayList;
import java.util.List;

public class EventoDaoImple extends GenericMongoDAOImple<Evento> implements EventoDao {
    public EventoDaoImple() {
        super(Evento.class);
    }

     @Override
    public List<Evento> eventosPorEntrenadorYUbicaciones(String entrnador, List<String> ubicaciones) {
        Jongo jongo = MongoConnection.getInstance().getJongo();
        String command = "{ ubicacion: {$in:#}}";

        return find(command, ubicaciones);
    }
}
