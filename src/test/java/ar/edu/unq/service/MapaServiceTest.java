package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.dao.DojoDao;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.Neo4JUbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateDojoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.Neo4JUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MapaServiceTest {

    private DataService dataService = new DataServiceImpl();
    private Neo4JUbicacionDao neo4JUbicacionDao = new Neo4JUbicacionDaoImple();
    private MapaService mapaService = new MapaServiceImpl();
    private EntrenadorDao entrenadorDao = new HibernateEntrenadorDaoImple();
    private DojoDao dojoDao = new HibernateDojoDaoImple();
    private UbicacionDao ubicacionDao = new HibernateUbicacionDaoImple();

    @After
    public void clear(){
        dataService.eliminarDatos();
    }

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

    @Test
    public void dado_un_dojo_le_pregunto_el_actual_campeon_y_me_lo_retorna(){
        Bicho campeon = this.dataService.crearBichoCampeonConEntrenadorYEspecieYEnDojo("Pikachu", "Ash", "dojo");
        assertEquals(campeon.getId(), mapaService.campeon("dojo").getId());
    }

    @Test
    public void dado_un_dojo_le_pregunto_los_campeones_historicos_y_retorna_al_campeon_historico_con_mas_tiempo(){
        Dojo dojo = this.dataService.crearDojo();
        Champion unCampeon = new Champion(this.dataService.crearBichoDeEspecieYDeEntrenador("Charmander", "Ash"));
        Champion otroCampeon = new Champion(this.dataService.crearBichoDeEspecieYDeEntrenador("Squirtle", "Misty"));
        Champion bichoCampeonHistorico = new Champion(this.dataService.crearBichoDeEspecieYDeEntrenador("Bulbasaur", "Brock"));
        unCampeon.setPeriodo(2); otroCampeon.setPeriodo(3); bichoCampeonHistorico.setPeriodo(4);


        ArrayList<Champion> c = new ArrayList<Champion>();
        c.add(unCampeon); c.add(otroCampeon); c.add(bichoCampeonHistorico);
        dojo.setCampeones(c);
        Runner.runInSession(() -> {
            dojoDao.actualizar(dojo);
            return dojoDao.getCampeonHistorico("dojo");
        });

        assertEquals(mapaService.campeonHistorico("dojo").getId(), bichoCampeonHistorico.getBicho().getId());
    }

    @Test
    public void crea_una_ubicacion_en_sql_y_neo4J(){

        String nombre ="NodoPueblito";
        Pueblo pueblo = new Pueblo();
        pueblo.setNombre(nombre);
        this.mapaService.crearUbicacion(pueblo);

        assertTrue(this.neo4JUbicacionDao.existeUbicacion(pueblo));
        assertNotNull( Runner.runInSession(() -> this.ubicacionDao.recuperar(nombre)) );
    }

    @Test
    public void crea_dos_ubicaciones_y_las_conecta_con_un_tipoCamino(){

        String nombreOrigen ="NodoPueblitoOrigen";
        Pueblo puebloOrigen = new Pueblo();
        puebloOrigen.setNombre(nombreOrigen);
        this.mapaService.crearUbicacion(puebloOrigen);

        String nombreDestino ="NodoPueblitoDestino";
        Pueblo puebloDestino = new Pueblo();
        puebloDestino.setNombre(nombreDestino);
        this.mapaService.crearUbicacion(puebloDestino);

        this.mapaService.conectar(nombreOrigen, nombreDestino,new Terrestre());

        assertTrue(this.neo4JUbicacionDao.existeUbicacion(puebloOrigen));
        assertTrue(this.neo4JUbicacionDao.existeUbicacion(puebloDestino));
        assertTrue(this.neo4JUbicacionDao.existeRelacion(new Terrestre()));

    }

    @Test
    public void si_hay_una_ubicacion_conectada_por_un_camino_terrestre_la_retorna(){

        String nombreOrigen ="NodoPueblitoOrigen";
        Pueblo puebloOrigen = new Pueblo();
        puebloOrigen.setNombre(nombreOrigen);
        this.mapaService.crearUbicacion(puebloOrigen);

        String nombreDestino ="NodoPueblitoDestino";
        Pueblo puebloDestino = new Pueblo();
        puebloDestino.setNombre(nombreDestino);
        this.mapaService.crearUbicacion(puebloDestino);

        Terrestre camino = new Terrestre();

        this.mapaService.conectar(nombreOrigen, nombreDestino,camino);

        List<Ubicacion> ubicaciones = this.mapaService.conectados(nombreOrigen,camino.getTipo());

        assertTrue(this.neo4JUbicacionDao.existeUbicacion(puebloOrigen));
        assertTrue(this.neo4JUbicacionDao.existeUbicacion(puebloDestino));
        assertTrue(this.neo4JUbicacionDao.existeRelacion(camino));
        assertEquals(ubicaciones.get(0).getNombre(), nombreDestino);

    }
}
