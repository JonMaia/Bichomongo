package ar.edu.unq.epers.bichomon.backend.service.entrenador;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;

import java.util.Date;

public class EntrenadorServiceImple implements EntrenadorService{

    private EntrenadorDao dao;

    public EntrenadorServiceImple(EntrenadorDao dao){
        this.dao = dao;
    }

    @Override
    public void setUbicacionEntrenador(String entrenador, String ubicacion) {

    }

    @Override
    public Nivel getNivel(Entrenador entrenador) {
        return dao.getNivel(entrenador);
    }

    @Override
    public void capturaBicho(Entrenador entrenador, Bicho bicho){
        if(puedeCapturarOtroBichomon(entrenador)) {
            bicho.setEntrenador(entrenador);
            bicho.setFechaCaptura(new Date());
        }
    }

    @Override
    public boolean puedeCapturarOtroBichomon(Entrenador entrenador) {
        Nivel nivel = getNivel(entrenador);
        return entrenador.getBichomones().size()<nivel.getMaximoDeBichos();
    }


}
