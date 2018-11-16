package ar.edu.unq.epers.bichomon.backend.model.Eventos;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;

public class Abandono extends Evento{

    Bicho bicho;

    public void setBicho(Bicho bicho){
        this.bicho = bicho;
    }

    public Bicho getBicho(){
        return bicho;
    }


}
