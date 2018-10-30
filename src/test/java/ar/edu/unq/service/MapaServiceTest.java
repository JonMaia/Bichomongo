package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Pueblo;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MapaServiceTest {

    private DataService dataService = new DataServiceImpl();
    private MapaService mapaService = new MapaServiceImpl();
    private EntrenadorDao entrenadorDao = new HibernateEntrenadorDaoImple();

    @Test
    public void si_se_mueve_a_un_entrenador_a_una_ubicacion_diferente_a_la_actual_su_ubicacion_cambia(){
        Pueblo pueblito1 = this.dataService.crearPuebloConProbabilidadExitoYEspecie("pueblito1",0,"especie1");
        Pueblo pueblito2 = this.dataService.crearPuebloConProbabilidadExitoYEspecie("pueblito2",0,"especie1");
        Entrenador entrenador = this.dataService.crearEntrenadorConUbicacion("Entrenador1", pueblito1);

        assertEquals(pueblito1.getNombre(), entrenador.getUbicacion().getNombre());

        this.mapaService.mover(entrenador.getNombre(), pueblito2.getNombre());
        entrenador = Runner.runInSession(() -> this.entrenadorDao.getById("Entrenador1"));

        assertEquals(entrenador.getUbicacion().getNombre(),pueblito2.getNombre());
    }

    @Test
    public void si_se_mueve_a_un_entrenador_a_su_ubicacion_actual_su_ubicacion_no_cambia(){
        Pueblo pueblito1 = this.dataService.crearPuebloConProbabilidadExitoYEspecie("pueblitoA",0,"especie1");
        Entrenador entrenador = this.dataService.crearEntrenadorConUbicacion("EntrenadorZ", pueblito1);

        assertEquals(pueblito1.getNombre(), entrenador.getUbicacion().getNombre());

        this.mapaService.mover(entrenador.getNombre(), pueblito1.getNombre());
        entrenador = Runner.runInSession(() -> this.entrenadorDao.getById("EntrenadorZ"));

        assertEquals(entrenador.getUbicacion().getNombre(),pueblito1.getNombre());
    }

    @Test
    public void si_hay_entrenadores_en_una_ubicacion_los_devuelve_a_todos(){
        Pueblo pueblito = this.dataService.crearPuebloConProbabilidadExitoYEspecie("pueblitoCon5Entrenadores",0,"especie1");
        Entrenador entrenador1 = this.dataService.crearEntrenadorConUbicacion("EntrenadorA", pueblito);
        Entrenador entrenador2 = this.dataService.crearEntrenadorConUbicacion("EntrenadorB", pueblito);
        Entrenador entrenador3 = this.dataService.crearEntrenadorConUbicacion("EntrenadorC", pueblito);
        Entrenador entrenador4 = this.dataService.crearEntrenadorConUbicacion("EntrenadorD", pueblito);
        Entrenador entrenador5 = this.dataService.crearEntrenadorConUbicacion("EntrenadorE", pueblito);

        assertEquals(5, mapaService.cantidadEntrenadores(pueblito.getNombre()));
    }
    @Test

    public void si_hay_un_unico_campeon_en_un_dojo_es_el_historico(){
        Bicho unBicho = this.dataService.crearBichoCampeonConEntrenadorYEspecieYEnDojo("EspecieCampeona", "EntrenadorCampeon", "DojoDeCampeones");
        Bicho campeonHistorico = this.mapaService.campeonHistorico(unBicho.getEntrenador().getUbicacion().getNombre());
        assertEquals(unBicho.getId(), campeonHistorico.getId());
    }


}
