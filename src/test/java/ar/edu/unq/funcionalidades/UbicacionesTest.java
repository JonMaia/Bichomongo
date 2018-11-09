package ar.edu.unq.funcionalidades;

import ar.edu.unq.mocks.ExitoDeBusquedaSiempreTrue;
import org.junit.Test;
import ar.edu.unq.epers.bichomon.backend.model.*;
import static org.junit.Assert.*;

public class UbicacionesTest {

    private Entrenador crearEntrenadorConBichoConEnergiaEnDojo(int energiaCampeon, int energiaRetador){
        Accion acciones = new Accion(10,10,10);
        Nivel nivel1 = new Nivel(1,200, 2,null,0.5);
        Dojo dojo = new Dojo();
        Especie planta = new Especie("Cerati", TipoBicho.PLANTA,150, 55 , energiaRetador, "/image/verdemon.png");
        Especie tierra = new Especie("Rocky", TipoBicho.TIERRA,1050, 99 , energiaCampeon, "/image/tierramon.png");
        Bicho cerati = new Bicho(planta);
        Bicho rocky = new Bicho(tierra);
        tierra.setEspecieInicial(tierra);
        dojo.setCampeon(rocky);
        Entrenador entrenador = new Entrenador("Ash", dojo, nivel1, acciones);
        entrenador.getBichomones().add(cerati);
        return entrenador;
    }


    @Test
    public void entrenadorPeleaEnDojoYPierdeEntoncesNoQuedaComoCampeon(){

        Entrenador entrenador = crearEntrenadorConBichoConEnergiaEnDojo(400, 20);
        Bicho retador = entrenador.getBichomones().get(0);
        Dojo dojo = (Dojo) entrenador.getUbicacion();
        Bicho campeon = dojo.getCampeon().getBicho();
        ResultadoCombate resultado = entrenador.iniciarDuelo(retador);
        assertEquals(resultado.getGanadorCombate(), campeon);
        assertEquals(dojo.getCampeones().size(), 1);
    }

    @Test
    public void entrenadorPeleaEnDojoYGanaEntoncesQuedaComoCampeonYSeAgregaAlHistorialDeCampeones(){

        Entrenador entrenador = crearEntrenadorConBichoConEnergiaEnDojo(20, 400);
        Bicho retador = entrenador.getBichomones().get(0);
        Dojo dojo = (Dojo) entrenador.getUbicacion();
        ResultadoCombate resultado = entrenador.iniciarDuelo(retador);
        assertEquals(resultado.getGanadorCombate(), retador);
        assertEquals(dojo.getCampeones().size(), 2);
    }

    @Test
    public void seBuscaBichoEnDojoYElEncontradoEsDeEspecieRaizDelCampeon(){
        Entrenador entrenador = crearEntrenadorConBichoConEnergiaEnDojo(20, 20);
        Dojo dojo = (Dojo) entrenador.getUbicacion();
        dojo.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());
        Bicho nuevoBicho = entrenador.buscarBicho();
        assertEquals(nuevoBicho.getEspecie(), dojo.getCampeon().getBicho().getEspecie());
    }

}
