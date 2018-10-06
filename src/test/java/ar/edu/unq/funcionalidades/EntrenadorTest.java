package ar.edu.unq.funcionalidades;

import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda.ExitoDeBusquedaSiempreFalse;
import ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda.ExitoDeBusquedaSiempreTrue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EntrenadorTest {

    Entrenador entrenador = null;
    Pueblo puebloPaleta = null;

    @Before
    public void iniciaTodo(){
        puebloPaleta = new Pueblo();
        Especie macriMon = new Especie();
        Especie peniaMon = new Especie();
        List<ProbabilidadDeOcurrencia> probabilidades = new ArrayList<>();
        ProbabilidadDeOcurrencia probabilidadDeMacrimon = new ProbabilidadDeOcurrencia(macriMon,30);
        ProbabilidadDeOcurrencia probabilidadDePeniamon = new ProbabilidadDeOcurrencia(peniaMon,70);
        probabilidades.add(probabilidadDeMacrimon);
        probabilidades.add(probabilidadDePeniamon);
        puebloPaleta.setEspeciesEnPueblo(probabilidades);


        Nivel nivel1 = new Nivel(1,100,1,
                new Nivel(2,200,2,
                        new Nivel(3,300,4,null,0.3),0.15),0.1);
        Acciones acciones = new Acciones(10,10,5);
        entrenador = new Entrenador("ash", puebloPaleta,nivel1,acciones);
    }


    @Test
    public void unNuevoEntrenadorEmpiezaConElNivelInicial(){
        assertTrue(entrenador.getNivel().getNumero()== 1);
    }

    @Test
    public void unNuevoEntrenadorCapturaUnBichomonAdquiereExperienciaYbichomon(){
        puebloPaleta.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());
        entrenador.buscarBicho();
        assertTrue(entrenador.getExperiencia()==10);
        assertTrue(entrenador.getBichomones().size()== 1);
    }

    @Test
    public void unNuevoEntrenadorIntentaCapturarUnBichomonyFallaNoTieneNingunoEnSuInventario(){
        puebloPaleta.setExitoDeBusqueda(new ExitoDeBusquedaSiempreFalse());
        entrenador.buscarBicho();
        assertTrue(entrenador.getExperiencia()==0);
        assertTrue(entrenador.getBichomones().size()== 0);
    }

    @Test
    public void unEntrenadorConsigueSuficienteExperienciaParaPasarDeNivel(){
        entrenador.addExperiencia(101);
        assertTrue(entrenador.getNivel().getNumero()== 2);
    }


    @Test
    public void unNuevoEntrenadorIntentaCapturarUnBichoPeroNoSeLoPermiteElNivel(){
        puebloPaleta.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());
        entrenador.buscarBicho();
        entrenador.buscarBicho();
        assertTrue(entrenador.getExperiencia()==10);
        assertTrue(entrenador.getBichomones().size()== 1);
    }

    @Test
    public void unNuevoEntrenadorCaptura2BichomonesEnNivel2(){
        puebloPaleta.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());
        entrenador.addExperiencia(95);
        entrenador.buscarBicho();
        entrenador.buscarBicho();
        assertTrue(entrenador.getExperiencia()==115);
        assertTrue(entrenador.getNivel().getNumero()== 2);
        assertTrue(entrenador.getBichomones().size()== 2);
    }

}
