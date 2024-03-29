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
    public List<Evento> eventosPorEntrenadorYUbicaciones(String entrenador, List<String> ubicaciones) {
        Jongo jongo = MongoConnection.getInstance().getJongo();
        String command = "{ entrenador: #},{ ubicacion: {$in:#}}";

        return find(command, entrenador, ubicaciones);
    }

    @Override
    public List<Evento> findArribos() {
        Jongo jongo = MongoConnection.getInstance().getJongo();
        String command = "{ type: #}";

        return find(command, "Arribo");
    }

    @Override
    public List<Evento> findAbandonos() {
        Jongo jongo = MongoConnection.getInstance().getJongo();
        String command = "{ type: #}";

        return find(command, "Abandono");
    }

    @Override
    public List<Evento> findCapturas() {
        Jongo jongo = MongoConnection.getInstance().getJongo();
        String command = "{ type: #}";

        return find(command, "Captura");
    }

    @Override
    public List<Evento> findCoronaciones() {
        Jongo jongo = MongoConnection.getInstance().getJongo();
        String command = "{ type: #}";

        return find(command, "Coronacion");
    }
}
