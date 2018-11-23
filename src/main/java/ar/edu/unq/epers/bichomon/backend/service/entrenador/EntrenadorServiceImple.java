package ar.edu.unq.epers.bichomon.backend.service.entrenador;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.EventoDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.mongoImple.EventoDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Eventos.Captura;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.BaseService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EntrenadorServiceImple extends BaseService<Entrenador,String> implements EntrenadorService {

    private EntrenadorDao dao;
    private EventoDao eventoDao;

    public void setDao(EntrenadorDao dao) {
        this.dao = new HibernateEntrenadorDaoImple();
    }

    public EntrenadorServiceImple() {
        this.dao = new HibernateEntrenadorDaoImple();
        this.eventoDao = new EventoDaoImple();
    }

    @Override
    public void setUbicacionEntrenador(String entrenador, String ubicacion) {

    }

    @Override
    public void capturaBicho(Entrenador entrenador, Bicho bicho){
        entrenador.obtenerBicho(bicho);
        crearEventoDeCaptura(bicho,entrenador,LocalDate.now(),entrenador.getUbicacion());
    }

    private void crearEventoDeCaptura(Bicho bichocapturado, Entrenador entrenador, LocalDate fecha, Ubicacion ubicacion) {
        eventoDao.save(new Captura(bichocapturado,entrenador,fecha,ubicacion));
    }

    @Override
    public boolean puedeCapturarOtroBichomon(Entrenador entrenador) {
        Nivel nivel = entrenador.getNivel();
        return entrenador.getBichomones().size()<nivel.getMaximoDeBichos();
    }

    @Override
    public void actualizar(Entrenador entrenador) {
        Entrenador oldEntrenador = getById(entrenador.getNombre());
        List<Bicho> result2 = entrenador.getBichomones().stream().filter(aObject ->
                oldEntrenador.getBichomones().contains(aObject)).collect(Collectors.toList());
        super.actualizar(entrenador);
    }



}
