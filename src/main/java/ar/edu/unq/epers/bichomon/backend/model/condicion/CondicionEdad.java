package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import javax.persistence.Entity;

@Entity
public class CondicionEdad extends Condicion {

    public CondicionEdad (int edad){
        super.valor = edad;
    }

    public CondicionEdad() {
    }

    @Override
    public boolean cumpleCondicion(Bicho bicho) {
        //return bicho.getFechaCaptura() > edad;
        return true;
    }
}
