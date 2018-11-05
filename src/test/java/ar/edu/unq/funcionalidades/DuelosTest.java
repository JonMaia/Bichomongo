package ar.edu.unq.funcionalidades;

import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.mocks.BichoMock;
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
    public void pepitaCombateContraElCampeonYPierdeYAmbosAumentanSuEnergia(){
        Bicho pepita = crearBichoConEnergiaYnombre(20, "pepita");
        Bicho pepito = crearBichoConEnergiaYnombre(500, "pepito");
        float energiaInicialPepita = pepita.getEnergia();
        float energiaInicialPepito = pepito.getEnergia();
        Duelo combate = new Duelo(pepito);
        ResultadoCombate resultado = combate.combatir(pepita);
        float energiaResultantePepita = pepita.getEnergia() - energiaInicialPepita;
        float energiaResultantePepito = pepito.getEnergia() - energiaInicialPepito;

        assertEquals("pepito", resultado.getGanadorCombate().getEspecie().getNombre());

        assertTrue(1 <= energiaResultantePepita);
        assertTrue(5 >= energiaResultantePepita);

        assertTrue(1 <= energiaResultantePepito);
        assertTrue(5 >= energiaResultantePepito);
    }

    @Test
    public void retadorAtaca10VecesACampeonYNoLoVenceEntoncesPierde(){
        Especie base =  new Especie("bichoMon", TipoBicho.FUEGO,180, 75, 20, "/image/rojomon.jpg");
        Bicho pepita = crearBichoConEnergiaYnombre(20, "pepita");
        BichoMock pepito = new BichoMock(base);
        Duelo combate = new Duelo(pepito);
        ResultadoCombate resultado = combate.combatir(pepita);

        assertEquals(pepito, resultado.getGanadorCombate());


    }

}
