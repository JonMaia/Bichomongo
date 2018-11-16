package ar.edu.unq.epers.bichomon.backend.model.Eventos;

import ar.edu.unq.epers.bichomon.backend.model.Entrenador;

public class Coronacion extends Evento{

    private Entrenador perdedor;

    public void setPerdedor(Entrenador perdedor){
        this.perdedor = perdedor;
    }

    public Entrenador getPerdedor(){
        return this.perdedor;
    }

}
