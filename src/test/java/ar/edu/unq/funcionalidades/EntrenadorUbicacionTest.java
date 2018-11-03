package ar.edu.unq.funcionalidades;

import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.exitoDeBusqueda.ExitoDeBusquedaNormal;
import ar.edu.unq.mocks.ExitoDeBusquedaSiempreFalse;
import ar.edu.unq.mocks.ExitoDeBusquedaSiempreTrue;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class EntrenadorUbicacionTest {

    Entrenador entrenador;
    Entrenador entrenadorDos;
    Pueblo ciudadCarmín;
    Dojo gimnasioCiudadCarmín;
    Guarderia guarderiaCIudadCarmin;
    private DataService dataService = new DataServiceImpl();
    Pueblo puebloPaleta;


    @Before
    public void iniciaTodo(){
        ciudadCarmín = new Pueblo();
        gimnasioCiudadCarmín = new Dojo();
        guarderiaCIudadCarmin = new Guarderia();
        Especie macriMon = new Especie();
        Especie peniaMon = new Especie();
        Especie bichoMon = new Especie();
        List<ProbabilidadDeOcurrencia> probabilidades = new ArrayList<>();
        ProbabilidadDeOcurrencia probabilidadDeMacrimon = new ProbabilidadDeOcurrencia(macriMon,30);
        ProbabilidadDeOcurrencia probabilidadDePeniamon = new ProbabilidadDeOcurrencia(peniaMon,70);
        probabilidades.add(probabilidadDeMacrimon);
        probabilidades.add(probabilidadDePeniamon);
        ciudadCarmín.setEspeciesEnPueblo(probabilidades);


        Nivel nivel1 = new Nivel(1,100,1,
                new Nivel(2,200,2,
                        new Nivel(3,300,4,null,0.3),0.15),0.1);
        Accion accion = new Accion(10,10,5);
        entrenador = new Entrenador("ash", ciudadCarmín,nivel1, accion);
        entrenadorDos = new  Entrenador("Red", guarderiaCIudadCarmin, nivel1, accion);
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
        Bicho bichoElegido = this.dataService.crearBichoConEntrenadorYEspecieYEnergia200EnDojo();

        entrenador = bichoElegido.getEntrenador();

        entrenador.moverA(gimnasioCiudadCarmín);

        entrenador.iniciarDuelo(bichoElegido);

        assertEquals(gimnasioCiudadCarmín, entrenador.getUbicacion());
        assertEquals(bichoElegido, gimnasioCiudadCarmín.getCampeon().getBicho());

    }

    @Test
    public void unEntrenadorIntentaCapturarUnBichoEnUnDojoVacioPeroNoPuede(){
        gimnasioCiudadCarmín.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());

        entrenador.moverA(gimnasioCiudadCarmín);

        entrenador.buscarBicho();

        assertEquals(gimnasioCiudadCarmín, entrenador.getUbicacion());
        assertEquals(0, entrenador.getBichomones().size());

    }


    @Test
    public void unEntrenadorCapturaUnbichoEnunDojo(){
        gimnasioCiudadCarmín.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());
        Especie libroMon = new Especie("libroMon",TipoBicho.AGUA,20,10,5,null);
        Especie paperMon = new Especie("paperMon",TipoBicho.AGUA,20,10,5,null);
        paperMon.setEvolucion(libroMon);
        libroMon.setEspecieInicial(paperMon);
        gimnasioCiudadCarmín.setCampeon(new Bicho(libroMon));

        entrenador.moverA(gimnasioCiudadCarmín);

        entrenador.buscarBicho();

        assertEquals(gimnasioCiudadCarmín, entrenador.getUbicacion());
        assertEquals(1, entrenador.getBichomones().size());
        assertEquals(paperMon, entrenador.getBichomones().get(0).getEspecie());
        assertNotEquals(libroMon, entrenador.getBichomones().get(0).getEspecie());

    }


    @Test
    public void unEntrenadorCapturaUnbichoYLoDejaEnUnaGuarderia(){
        ciudadCarmín.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());

        entrenador.buscarBicho();

        entrenador.moverA(guarderiaCIudadCarmin);
        Bicho abandonado = entrenador.getBichomones().get(0);
        entrenador.abandonarBicho(abandonado);
        assertEquals(guarderiaCIudadCarmin, entrenador.getUbicacion());
        assertEquals(0, entrenador.getBichomones().size());
        assertEquals(1, guarderiaCIudadCarmin.getBichomones().size());



    }

    @Test
    public void unEntrenadorBuscaUnBichoEnUnaGuarderia(){
        guarderiaCIudadCarmin.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());
        Especie paperMon = new Especie("paperMon",TipoBicho.AGUA,20,10,5,null);

        Bicho bicho = new Bicho(paperMon);
        guarderiaCIudadCarmin.dejarBicho(bicho);

        entrenador.moverA(guarderiaCIudadCarmin);

        assertEquals(1, guarderiaCIudadCarmin.getBichomones().size());

        entrenador.buscarBicho();

        assertEquals(guarderiaCIudadCarmin, entrenador.getUbicacion());
        assertEquals(1, entrenador.getBichomones().size());
        assertEquals(bicho, entrenador.getBichomones().get(0));

        assertEquals(0, guarderiaCIudadCarmin.getBichomones().size());

    }

    @Test
    public void unEntrenadorAbandonaUnBichomonEnUnaGuarderiaEIntentaAtraparloDeNuevoYNoPuede(){
        ciudadCarmín.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());

        entrenador.buscarBicho();

        entrenador.moverA(guarderiaCIudadCarmin);
        Bicho abandonado = entrenador.getBichomones().get(0);
        entrenador.abandonarBicho(abandonado);

        entrenador.buscarBicho();

        assertEquals(entrenador.getBichomones().size(), 0);

        assertEquals(guarderiaCIudadCarmin.getBichomones().size(), 1);
    }

    @Test
    public void unEntrenadorBuscaUnBichoEnLaGuarderiaYNoHayBichos(){

        entrenador.moverA(guarderiaCIudadCarmin);

        entrenador.buscarBicho();

        assertEquals(guarderiaCIudadCarmin.getBichomones().size(), 0);
        assertEquals(entrenador.getBichomones().size(), 0);

    }

    @Test
    public void dosEntrenadoresAbandonanElMismoPokemonYNingunoLoPuedeRecuperar(){
        ciudadCarmín.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());
        guarderiaCIudadCarmin.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());

        entrenador.buscarBicho();

        entrenador.moverA(guarderiaCIudadCarmin);
        Bicho abandonado = entrenador.getBichomones().get(0);
        entrenador.abandonarBicho(abandonado);

        guarderiaCIudadCarmin.encontrarBichomon(entrenadorDos);
        guarderiaCIudadCarmin.setExitoDeBusqueda(new ExitoDeBusquedaNormal());
        entrenadorDos.abandonarBicho(abandonado);
        guarderiaCIudadCarmin.encontrarBichomon(entrenadorDos);


        assertEquals(entrenador.getBichomones().size(), 0);

        assertEquals(entrenadorDos.getBichomones().size(), 0);

    }


}
