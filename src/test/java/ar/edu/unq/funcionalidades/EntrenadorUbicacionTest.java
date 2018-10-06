package ar.edu.unq.funcionalidades;

import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.exception.AccionEnUbicacionErroneaException;
import ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda.ExitoDeBusquedaSiempreFalse;
import ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda.ExitoDeBusquedaSiempreTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EntrenadorUbicacionTest {

    Entrenador entrenador = null;
    Pueblo ciudadCarmín = null;
    Dojo gimnasioCiudadCarmín = null;

    @Before
    public void iniciaTodo(){
        ciudadCarmín = new Pueblo();
        gimnasioCiudadCarmín = new Dojo();
        Especie macriMon = new Especie();
        Especie peniaMon = new Especie();
        List<ProbabilidadDeOcurrencia> probabilidades = new ArrayList<>();
        ProbabilidadDeOcurrencia probabilidadDeMacrimon = new ProbabilidadDeOcurrencia(macriMon,30);
        ProbabilidadDeOcurrencia probabilidadDePeniamon = new ProbabilidadDeOcurrencia(peniaMon,70);
        probabilidades.add(probabilidadDeMacrimon);
        probabilidades.add(probabilidadDePeniamon);
        ciudadCarmín.setEspeciesEnPueblo(probabilidades);


        Nivel nivel1 = new Nivel(1,100,1,
                new Nivel(2,200,2,
                        new Nivel(3,300,4,null,0.3),0.15),0.1);
        Acciones acciones = new Acciones(10,10,5);
        entrenador = new Entrenador("ash", ciudadCarmín,nivel1,acciones);
    }


    @Test
    public void unNuevoEntrenadorEmpiezaConElNivelInicial(){
        assertEquals(1, (int) entrenador.getNivel().getNumero());
    }

    @Test
    public void unNuevoEntrenadorCapturaUnBichomonAdquiereExperienciaYbichomon(){
        ciudadCarmín.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());
        entrenador.buscarBicho();
        assertEquals(10, (int) entrenador.getExperiencia());
        assertEquals(1, entrenador.getBichomones().size());
    }

    @Test
    public void unNuevoEntrenadorIntentaCapturarUnBichomonyFallaNoTieneNingunoEnSuInventario(){
        ciudadCarmín.setExitoDeBusqueda(new ExitoDeBusquedaSiempreFalse());
        entrenador.buscarBicho();
        assertEquals(0, (int) entrenador.getExperiencia());
        assertEquals(0, entrenador.getBichomones().size());
    }

    @Test
    public void unEntrenadorConsigueSuficienteExperienciaParaPasarDeNivel(){
        entrenador.addExperiencia(101);
        assertEquals(2, (int) entrenador.getNivel().getNumero());
    }


    @Test
    public void unNuevoEntrenadorIntentaCapturarUnBichoPeroNoSeLoPermiteElNivel(){
        ciudadCarmín.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());
        entrenador.buscarBicho();
        entrenador.buscarBicho();
        assertEquals(10, (int) entrenador.getExperiencia());
        assertEquals(1, entrenador.getBichomones().size());
    }

    @Test
    public void unNuevoEntrenadorCaptura2BichomonesEnNivel2(){
        ciudadCarmín.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());
        entrenador.addExperiencia(95);
        entrenador.buscarBicho();
        entrenador.buscarBicho();
        assertEquals(115, (int) entrenador.getExperiencia());
        assertEquals(2, (int) entrenador.getNivel().getNumero());
        assertEquals(2, entrenador.getBichomones().size());
    }


    @Test
    public void unEntrenadorSeMueveAOtraUbicacion(){
        entrenador.moverA(gimnasioCiudadCarmín);
        assertEquals(gimnasioCiudadCarmín, entrenador.getUbicacion());

    }

    @Test
    public void unEntrenadorSeMueveAUnDojoYLuchaPeroEstaVacioEntoncesGanaElLugarDelDojo(){
        ciudadCarmín.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());
        entrenador.buscarBicho();

        entrenador.moverA(gimnasioCiudadCarmín);
        Bicho bichoElegido = entrenador.getBichomones().get(0);
        entrenador.iniciarDuelo(entrenador.getBichomones().get(0));

        assertEquals(gimnasioCiudadCarmín, entrenador.getUbicacion());
        assertEquals(bichoElegido, gimnasioCiudadCarmín.getCampeon());

    }

}
