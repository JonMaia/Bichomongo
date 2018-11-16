package ar.edu.unq.epers.bichomon.backend.model.Eventos;

import ar.edu.unq.epers.bichomon.backend.model.Entrenador;

public class Coronacion extends Evento{

    private String perdedor;

    public void setPerdedor(String perdedor){
        this.perdedor = perdedor;
    }

    public String getPerdedor(){
        return this.perdedor;
    }

}
