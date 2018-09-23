package ar.edu.unq.epers.bichomon.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Entrenador {

    @Id
    private String nombre;

    @ManyToOne()
    private Ubicacion ubicacion;

    private Integer experiencia;

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void capturaBicho(Bicho bicho){
        bicho.setEntrenador(this);
        bicho.setFechaCaptura(new Date());
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
}
