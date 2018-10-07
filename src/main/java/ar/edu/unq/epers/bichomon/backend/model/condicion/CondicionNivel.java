package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import javax.persistence.Entity;

@Entity
public class CondicionNivel extends Condicion{

    public CondicionNivel() {
    }

    public CondicionNivel(int nivel) { super.valor = nivel;}

    @Override
    public boolean cumpleCondicion(Bicho bicho) {
        return bicho.getEntrenador().getNivel().getNumero() > valor;
    }
}
