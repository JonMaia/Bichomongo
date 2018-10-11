package ar.edu.unq.funcionalidades;

import ar.edu.unq.epers.bichomon.backend.model.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.annotation.Target;

public class DuelosTest {

    public Bicho crearBichoConEnergiaYnombre(int energia, String nombre){
        Especie base =  new Especie(nombre, TipoBicho.FUEGO,180, 75, energia, "/image/rojomon.jpg");
        Bicho bicho = new Bicho(base);
        return bicho;
    }

    @Test
    public void combatePepitaConPepitoYGanaPepita(){
        Bicho pepita = crearBichoConEnergiaYnombre(200, "pepita");
        Bicho pepito = crearBichoConEnergiaYnombre(50, "pepito");
        Duelo combate = new Duelo(pepito);
        ResultadoCombate resultado = combate.combatir(pepita);
        assertEquals(pepita, resultado.getGanadorCombate());
    }

    @Test
    public void pepitaCombateEnUnDojoSinCamepeonYEsNombradaCampeonaSinPelear(){
        Bicho pepita = crearBichoConEnergiaYnombre(200, "pepita");
        Duelo combate = new Duelo(null);
        ResultadoCombate resultado = combate.combatir(pepita);
        assertEquals(pepita, resultado.getGanadorCombate());
    }

    @Test
    public void pepitaCombateContraElCampeonYPierde(){
        Bicho pepita = crearBichoConEnergiaYnombre(20, "pepita");
        Bicho pepito = crearBichoConEnergiaYnombre(500, "pepito");
        Duelo combate = new Duelo(pepito);
        ResultadoCombate resultado = combate.combatir(pepita);
        assertEquals("pepito", resultado.getGanadorCombate().getEspecie().getNombre());
    }
}
