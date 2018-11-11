package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.exception.UbicacionIncorrectaException;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoService;
import ar.edu.unq.epers.bichomon.backend.service.bicho.BichoServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import ar.edu.unq.mocks.ExitoDeBusquedaSiempreTrue;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BichoServiceTest {

    private DataService dataService = new DataServiceImpl();
    private BichoService bichoService = new BichoServiceImpl();
    private EntrenadorDao entrenadorDao = new HibernateEntrenadorDaoImple();
    private UbicacionDao ubicacionDao = new HibernateUbicacionDaoImple();

    @After
    public void clear(){
        dataService.eliminarDatos();
    }

    @Test
    public void se_busca_bicho_exitoso_en_ubicacion(){
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucionEnPuebloConProbabilidad100();
        Entrenador entrenador = bicho.getEntrenador();
        Ubicacion pueblo = entrenador.getUbicacion();

        pueblo.setExitoDeBusqueda(new ExitoDeBusquedaSiempreTrue());

        Entrenador trainer = Runner.runInSession(()  -> {
            ubicacionDao.actualizar(pueblo);
            bichoService.buscar(entrenador.getNombre());
            return entrenadorDao.getById(entrenador.getNombre());
        });

        assertEquals(2, trainer.getBichomones().size());
    }

    @Test
    public void se_busca_bicho_sinExito_en_ubicacion(){
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
        Pueblo puebloPaleta = dataService.crearPuebloConProbabilidadExito0();
        Entrenador entrenador = bicho.getEntrenador();
        entrenador.moverA(puebloPaleta);

        Runner.runInSession(()  -> {
            entrenadorDao.actualizar(entrenador);
            return null;
        });

        bichoService.buscar(entrenador.getNombre());

        assertEquals(1, entrenador.getBichomones().size());
    }

    @Test
    public void se_abandona_bicho_exitosamente_en_guarderia(){
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
        Guarderia guarderia = new Guarderia();
        guarderia.setNombre("UnaGuaderia");
        Entrenador entrenador = bicho.getEntrenador();
        entrenador.moverA(guarderia);


        Guarderia ubicacionGuarderia =
                Runner.runInSession(()  ->{
                    entrenadorDao.actualizar(entrenador);
                    bichoService.abandonar(entrenador.getNombre(), bicho.getId());
                    return ubicacionDao.recuperarGuarderia(guarderia.getNombre());
                });

        assertEquals(1, guarderia.getBichomones().size());
    }

    @Test(expected = UbicacionIncorrectaException.class)
    public void se_abandona_bicho_en_ubicacion_que_no_es_guarderia_y_tira_exception(){
            Bicho bicho = dataService.crearBichoConEntrenadorYEspecieSinEvolucion();
            Entrenador entrenador = bicho.getEntrenador();

        //TODO: Extraer a un metodo en dataservice
            Runner.runInSession(()  -> {
            Pueblo puebloEsmeralda = new Pueblo();
            puebloEsmeralda.setNombre("PuebloEsmralda");
            entrenador.moverA(puebloEsmeralda);
            entrenadorDao.actualizar(entrenador);
            return null;
        });

        bichoService.abandonar(entrenador.getNombre(), bicho.getId());
    }

    @Test
    public void bicho_retador_combate_con_campeon_y_gana (){
        Bicho bicho = dataService.crearBichoConEntrenadorYEspecieYEnergia200EnDojo();
        Entrenador entrenador = bicho.getEntrenador();
        ResultadoCombate resultadoCombate = bichoService.duelo(entrenador.getNombre(), bicho.getId());
        assertEquals(resultadoCombate.getGanadorCombate().getId(), bicho.getId());
    }

    @Test(expected = UbicacionIncorrectaException.class)
    public void bicho_retador_intenta_duelear_fuera_de_dojo_y_explota() throws UbicacionIncorrectaException{
        Bicho bicho = dataService.crearBichoConEnergia10ConEntrenadorYEspecieConEvolucionConCondicionDeEnergia0();
        Entrenador entrenador = bicho.getEntrenador();

        //TODO: Extraer a un metodo en dataservice
        Runner.runInSession(()  -> {
            Ubicacion pueblo = new Pueblo();
            pueblo.setNombre("PUEBLO");
            entrenador.setUbicacion(pueblo);
            entrenadorDao.actualizar(entrenador);
            return null;
        });

        bichoService.duelo(entrenador.getNombre(), bicho.getId());
    }
}
