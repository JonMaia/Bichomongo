package ar.edu.unq.epers.bichomon.backend.service.entrenador;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;

import org.joda.time.LocalDate;

public class EntrenadorServiceImple implements EntrenadorService{

    private EntrenadorDao dao;

    public EntrenadorServiceImple(EntrenadorDao dao){
        this.dao = dao;
    }

    @Override
    public void setUbicacionEntrenador(String entrenador, String ubicacion) {

    }

    @Override
    public void capturaBicho(Entrenador entrenador, Bicho bicho){
        if(puedeCapturarOtroBichomon(entrenador)) {
            bicho.setEntrenador(entrenador);
            bicho.setFechaCaptura(LocalDate.now());
        }
    }

    @Override
    public boolean puedeCapturarOtroBichomon(Entrenador entrenador) {
        Nivel nivel = entrenador.getNivel();
        return entrenador.getBichomones().size()<nivel.getMaximoDeBichos();
    }


}
