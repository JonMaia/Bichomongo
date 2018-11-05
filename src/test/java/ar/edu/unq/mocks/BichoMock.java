package ar.edu.unq.mocks;

import ar.edu.unq.epers.bichomon.backend.model.Ataque;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BichoMock extends Bicho {


    private int id;


    private Entrenador entrenador;


    private Especie especie;


    private List<Entrenador> exEntrenadores = new ArrayList<>();

    private float energia;
    private float danioRecibidoCombate;
    private int victorias;
    private LocalDate fechaNacimiento;
    private LocalDate fechaCaptura;
    private int edad;

    public BichoMock (){}

    public BichoMock (Especie especie){
        this.especie = especie;
        this.victorias = 0;
        this.fechaNacimiento = LocalDate.now();
        this.energia = especie.getEnergiaInicial();

    }

    public void setDanioRecibidoCombate(float danio){
        this.danioRecibidoCombate = 0f;
    }

    public Ataque atacar(Bicho bicho) {

        bicho.setDanioRecibidoCombate(0);
        return new Ataque(this, bicho , 0);
    }


}
