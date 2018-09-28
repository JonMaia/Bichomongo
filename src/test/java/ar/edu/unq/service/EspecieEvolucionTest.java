package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EspecieEvolucionTest {


    @Test
    public void la_especie_de_un_bicho_cambia_al_evolucionar() {

        Especie especie = new Especie();
        Bicho bicho = new Bicho(especie);
    }



}
