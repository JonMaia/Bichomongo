package ar.edu.unq.epers.bichomon.backend.model.condicion;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Condicion {

    @Id @GeneratedValue
    private Integer id;
    public int valor;

    public abstract boolean cumpleCondicion(Bicho bicho);
}
