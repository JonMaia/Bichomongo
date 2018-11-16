package ar.edu.unq.epers.bichomon.backend.service.FeedService;

import ar.edu.unq.epers.bichomon.backend.model.Eventos.Evento;

import java.util.List;

public interface FeedService {

    /**
     * Devolverá la lista de eventos que involucren al entrenador provisto.
     * Esa lista incluirá eventos relacionados a todos los viajes que haya hecho el entrenador (arribos),
     * a todas las capturas, a todos los bichos que haya abandonado y a todas las coronaciones en las que
     * haya sido coronado o destronado. Dicha lista debe contener primero a los eventos más recientes.
     * @param entrenador
     */
    List<Evento> feedEntrenador(String entrenador);

    /**
     * Devolverá el feed de eventos principal que debe mostrarse al usuario.
     * El mismo deberá incluír todas los eventos de su ubicación actual y todos los eventos de las
     * ubicaciones que estén conectadas con la misma. Dicha lista debe contener primero a los eventos
     * más recientes.
     * @param entrenador
     * @return
     */
    List<Evento> feedUbicacion(String entrenador);

}
