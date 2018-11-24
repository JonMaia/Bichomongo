package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.dao.DojoDao;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.Neo4JUbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateDojoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.Neo4JImple.Neo4JUbicacionDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.exception.CaminoMuyCostosoException;
import ar.edu.unq.epers.bichomon.backend.model.exception.NoHayCampeonHistoricoException;
import ar.edu.unq.epers.bichomon.backend.model.exception.UbicacionIncorrectaException;
import ar.edu.unq.epers.bichomon.backend.model.exception.UbicacionMuyLejanaException;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaService;
import ar.edu.unq.epers.bichomon.backend.service.mapa.MapaServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @After
    public void eliminar(){
        dataService.eliminarDatos();
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

        assertTrue(this.mapaService.existeUbicacion(pueblo.getNombre()));
        assertNotNull( Runner.runInSession(() ->  this.ubicacionDao.recuperar(nombre)));
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

        assertTrue(this.neo4JUbicacionDao.existeUbicacion(puebloOrigen.getNombre()));
        assertTrue(this.neo4JUbicacionDao.existeUbicacion(puebloDestino.getNombre()));
        assertTrue(this.neo4JUbicacionDao.existeRelacion(puebloOrigen.getNombre(),puebloDestino.getNombre()));

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

        assertTrue(this.neo4JUbicacionDao.existeUbicacion(puebloOrigen.getNombre()));
        assertTrue(this.neo4JUbicacionDao.existeUbicacion(puebloDestino.getNombre()));
        assertTrue(this.neo4JUbicacionDao.existeRelacion(puebloOrigen.getNombre(),puebloDestino.getNombre()));
        assertEquals(ubicaciones.get(0).getNombre(), nombreDestino);

    }

    @Test
    public void meMovereALaUbicacionMasCorta(){

        crearMapa();


        String TRACEY = "Tracey Sketchit";
        Entrenador elMismo = Runner.runInSession(() -> {
            Pueblo yantra = (Pueblo) ubicacionDao.recuperar("Ciudad Yantra");
            Entrenador entrenador = this.dataService.crearEntrenadorConUbicacion(TRACEY , yantra);
            entrenador.setBilletera(4);
            entrenadorDao.actualizar(entrenador);
            this.mapaService.moverMasCorto(TRACEY, "Pueblo Fresco");
            return entrenadorDao.getById(entrenador.getNombre());
        });
        assertTrue("Pueblo Fresco".equalsIgnoreCase(elMismo.getUbicacion().getNombre()));
        //el entrenador se queda pobre y gasta todas sus monedas... pero le alcanza para llegar
        assertTrue(elMismo.getBilletera() == 0);

    }

    @Test
    public void seQuiereMoverAUnaUbicacionAledaniaYLoConsigue(){

        crearMapa();

        String TRACEY = "Tracey Sketchit";
        Entrenador elMismo = Runner.runInSession(() -> {
            Pueblo yantra = (Pueblo) ubicacionDao.recuperar("Ciudad Yantra");
            Entrenador entrenador = this.dataService.crearEntrenadorConUbicacion(TRACEY , yantra);
            entrenador.setBilletera(4);
            entrenadorDao.actualizar(entrenador);
            this.mapaService.mover(TRACEY, "Ciudad Tempera");
            return entrenadorDao.getById(entrenador.getNombre());
        });
        assertTrue(elMismo.getBilletera() == 3);
        assertTrue("Ciudad Tempera".equalsIgnoreCase(elMismo.getUbicacion().getNombre()));
    }

    @Test(expected = CaminoMuyCostosoException.class)
    public void seQuiereMoverAUnaUbicacionAledaniaPeroSeOlvidoLaBilleteraEnCasa(){

        crearMapa();

        String TRACEY = "Tracey Sketchit";
        Entrenador elMismo = Runner.runInSession(() -> {
            Pueblo yantra = (Pueblo) ubicacionDao.recuperar("Ciudad Yantra");
            Entrenador entrenador = this.dataService.crearEntrenadorConUbicacion(TRACEY , yantra);
            entrenador.setBilletera(0);
            entrenadorDao.actualizar(entrenador);
            this.mapaService.mover(TRACEY, "Ciudad Tempera");
            return entrenadorDao.getById(entrenador.getNombre());
        });
    }

    @Test(expected = UbicacionMuyLejanaException.class)
    public void seQuiereMoverPeroLaUbicacionEstaMuyLejos(){

        crearMapa();

        String TRACEY = "Tracey Sketchit";
        Entrenador elMismo = Runner.runInSession(() -> {
            crearPuebloConNombre("Pueblo Perdido");
            Pueblo yantra = (Pueblo) ubicacionDao.recuperar("Ciudad Yantra");
            Entrenador entrenador = this.dataService.crearEntrenadorConUbicacion(TRACEY , yantra);
            entrenador.setBilletera(200);
            entrenadorDao.actualizar(entrenador);
            this.mapaService.mover(TRACEY, "Pueblo Perdido");
            return entrenadorDao.getById(entrenador.getNombre());
        });
    }


    private void crearMapa() {
        Pueblo yantra = crearPuebloConNombre("Ciudad Yantra");
        Pueblo cromlech = crearPuebloConNombre("Pueblo Cromlech");
        Pueblo relieve = crearPuebloConNombre("Ciudad Relieve");
        Pueblo petroglifo = crearPuebloConNombre("Pueblo Petroglifo");
        Pueblo tempera = crearPuebloConNombre("Ciudad Tempera");
        Pueblo romantis = crearPuebloConNombre("Ciudad Romantis");
        Pueblo luminalia = crearPuebloConNombre("Ciudad Luminalia");
        Pueblo fresco = crearPuebloConNombre("Pueblo Fresco");
        Pueblo acuarela = crearPuebloConNombre("Pueblo Acuarela");
        Pueblo vanitas = crearPuebloConNombre("Pueblo Vanitas");
        Pueblo novarte = crearPuebloConNombre("Ciudad Novarte");
        Pueblo mosaico = crearPuebloConNombre("Pueblo Mosaico");
        Pueblo fluxus = crearPuebloConNombre("Ciudad Fluxus");
        Pueblo fractal = crearPuebloConNombre("Ciudad Fractal");
        Pueblo batik = crearPuebloConNombre("Ciudad Batik");
        Pueblo boceto = crearPuebloConNombre("Pueblo Boceto");
        Pueblo cenit = crearPuebloConNombre("Palacio Cenit(4)");



        this.mapaService.conectar(yantra.getNombre(), cromlech.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(yantra.getNombre(), tempera.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(cromlech.getNombre(), relieve.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(relieve.getNombre(), petroglifo.getNombre(),SingletonCaminos.getMaritimo());
        this.mapaService.conectar(relieve.getNombre(), cenit.getNombre(),SingletonCaminos.getAereo());

        this.mapaService.conectar(petroglifo.getNombre(), cromlech.getNombre(),SingletonCaminos.getMaritimo());
        this.mapaService.conectar(petroglifo.getNombre(), relieve.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(petroglifo.getNombre(), acuarela.getNombre(),SingletonCaminos.getAereo());

        this.mapaService.conectar(tempera.getNombre(), romantis.getNombre(),SingletonCaminos.getMaritimo());
        this.mapaService.conectar(tempera.getNombre(), yantra.getNombre(),SingletonCaminos.getMaritimo());

        this.mapaService.conectar(romantis.getNombre(), tempera.getNombre(),SingletonCaminos.getMaritimo());
        this.mapaService.conectar(romantis.getNombre(), luminalia.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(romantis.getNombre(), fresco.getNombre(),SingletonCaminos.getTerrestre());

        this.mapaService.conectar(vanitas.getNombre(), cenit.getNombre(),SingletonCaminos.getTerrestre());

        this.mapaService.conectar(cenit.getNombre(), luminalia.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(cenit.getNombre(), novarte.getNombre(),SingletonCaminos.getAereo());
        this.mapaService.conectar(cenit.getNombre(), vanitas.getNombre(),SingletonCaminos.getTerrestre());

        this.mapaService.conectar(fresco.getNombre(), romantis.getNombre(),SingletonCaminos.getAereo());
        this.mapaService.conectar(fresco.getNombre(), mosaico.getNombre(),SingletonCaminos.getAereo());
        this.mapaService.conectar(fresco.getNombre(), luminalia.getNombre(),SingletonCaminos.getTerrestre());

        this.mapaService.conectar(mosaico.getNombre(), fractal.getNombre(),SingletonCaminos.getTerrestre());

        this.mapaService.conectar(fluxus.getNombre(), fresco.getNombre(),SingletonCaminos.getMaritimo());
        this.mapaService.conectar(fluxus.getNombre(), mosaico.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(fluxus.getNombre(), fractal.getNombre(),SingletonCaminos.getTerrestre());

        this.mapaService.conectar(fractal.getNombre(), acuarela.getNombre(),SingletonCaminos.getTerrestre());

        this.mapaService.conectar(novarte.getNombre(), fractal.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(novarte.getNombre(), vanitas.getNombre(),SingletonCaminos.getTerrestre());

        this.mapaService.conectar(acuarela.getNombre(), boceto.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(acuarela.getNombre(), novarte.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(acuarela.getNombre(), fractal.getNombre(),SingletonCaminos.getAereo());

        this.mapaService.conectar(boceto.getNombre(), acuarela.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(boceto.getNombre(), batik.getNombre(),SingletonCaminos.getTerrestre());
        this.mapaService.conectar(boceto.getNombre(), fractal.getNombre(),SingletonCaminos.getAereo());

        this.mapaService.conectar(batik.getNombre(), fractal.getNombre(),SingletonCaminos.getAereo());
    }

    private Pueblo crearPuebloConNombre(String nombre) {
        Pueblo pueblo = new Pueblo();
        pueblo.setNombre(nombre);
        this.mapaService.crearUbicacion(pueblo);
        return pueblo;
    }

    public static class SingletonCaminos {

        private static Terrestre terrestre;
        private static Aereo aereo;
        private static Maritimo maritimo;

        public static Terrestre getTerrestre() {
            if (terrestre == null){
                terrestre = new Terrestre();
            }
            return terrestre;
        }
        public static Aereo getAereo() {
            if (aereo == null){
                aereo = new Aereo();
            }
            return aereo;
        }
        public static Maritimo getMaritimo() {
            if (maritimo == null){
                maritimo = new Maritimo();
            }
            return maritimo;
        }
    }

    @Test(expected = NoHayCampeonHistoricoException.class)
    public void dado_un_dojo_le_pregunto_los_campeones_historicos_y_retorna_que_no_hay_ninguno() throws NoHayCampeonHistoricoException{
        Dojo dojo = this.dataService.crearDojo();

        Runner.runInSession(() -> {
            dojoDao.actualizar(dojo);
            return null;
        });

        mapaService.campeonHistorico("dojo");
    }

    @Test
    public void seMueveAUnaUbicacionAledaniaLaCualLeDescuentaPlataDeLaBilleteraAlEntrenador(){

        crearMapa();

        String TRACEY = "Tracey Sketchit";
        Entrenador elMismo = Runner.runInSession(() -> {
            Pueblo yantra = (Pueblo) ubicacionDao.recuperar("Ciudad Yantra");
            Entrenador entrenador = this.dataService.crearEntrenadorConUbicacion(TRACEY , yantra);
            entrenador.setBilletera(5);
            entrenadorDao.actualizar(entrenador);
            this.mapaService.mover(TRACEY, "Ciudad Tempera");
            return entrenadorDao.getById(entrenador.getNombre());
        });
        assertTrue(elMismo.getBilletera() == 4);
    }
}
