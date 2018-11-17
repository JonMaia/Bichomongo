package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.Eventos.Evento;

import java.util.List;

public interface EventoDao extends GenericMongoDao<Evento> {
    List<Evento> eventosPorEntrenadorYUbicaciones(String entrnador, List<String> ubicaciones);
}
