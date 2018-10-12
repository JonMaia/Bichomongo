package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.exception.UbicacionIncorrectaException;
import ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda.ExitoDeBusquedaSiempreTrue;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BichoServiceTest {

    private DataService dataService = new DataServiceImpl();
    private BichoService bichoService = new BichoServiceImpl();


    @Test
    public void se_busca_bicho_exitoso_en_ubicacion(){
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
        Pueblo puebloPaleta = dataService.crearPuebloConProbabilidadExito100();
        Entrenador entrenador = bicho.getEntrenador();
        entrenador.moverA(puebloPaleta);

        bichoService.buscar(entrenador.getNombre());

        assertEquals(2, entrenador.getBichomones().size());


    }

    @Test
    public void se_busca_bicho_sinExito_en_ubicacion(){
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
        Pueblo puebloPaleta = dataService.crearPuebloConProbabilidadExito0();
        Entrenador entrenador = bicho.getEntrenador();
        entrenador.moverA(puebloPaleta);

        bichoService.buscar(entrenador.getNombre());

        assertEquals(1, entrenador.getBichomones().size());

    }

    @Test
    public void se_abandona_bicho_exitosamente_en_guarderia(){
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
        Guarderia guarderia = new Guarderia();
        Entrenador entrenador = bicho.getEntrenador();
        entrenador.moverA(guarderia);

        bichoService.abandonar(entrenador.getNombre(), bicho.getId());

        assertEquals(1, guarderia.getBichomones().size());
    }

    @Rule
    public ExpectedException ubicacionIncorrecta = ExpectedException.none();

    @Test
    public void se_abandona_bicho_en_ubicacion_que_no_es_guarderia_y_tira_exception(){
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
        Pueblo puebloEsmeralda = new Pueblo();
        Entrenador entrenador = bicho.getEntrenador();
        entrenador.moverA(puebloEsmeralda);

        ubicacionIncorrecta.expect(UbicacionIncorrectaException.class);
        ubicacionIncorrecta.expectMessage("No se puede dejar un bicho en esta ubicacion");
        bichoService.abandonar(entrenador.getNombre(), bicho.getId());

    }

    @Test
    public void bicho_retador_combate_con_campeon_y_gana (){
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieYEnergia200EnDojo();
        Entrenador entrenador = bicho.getEntrenador();
        try {
           ResultadoCombate resultadoCombate = bichoService.duelo(entrenador.getNombre(), bicho.getId());
           assertEquals(resultadoCombate.getGanadorCombate(), bicho);
        }
        catch(UbicacionIncorrectaException e){

        }
    }

    @Test
    public void bicho_retador_intenta_duelear_fuera_de_dojo_y_explota(){
        Bicho bicho = dataService.crearBichoConEnergia10ConEntrenadorYEspecieConEvolucionConCondicionDeEnergia0();
        Entrenador entrenador = bicho.getEntrenador();
        try{
            ResultadoCombate resultadoCombate = bichoService.duelo(entrenador.getNombre(), bicho.getId());
            fail();
        }
        catch(UbicacionIncorrectaException e){
            assertThat(e.getMessage(), is("No se puede combatir en la ubicacion"));
        }
    }
}
