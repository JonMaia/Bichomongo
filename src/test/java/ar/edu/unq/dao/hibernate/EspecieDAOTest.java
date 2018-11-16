package ar.edu.unq.dao.hibernate;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.dao.NivelDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateEspecieDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateNivelDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.model.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;


public class EspecieDAOTest {

    private EspecieDao especieDao = new HibernateEspecieDaoImple();
    private NivelDao nivelDao = new HibernateNivelDaoImple();
    private DataService dataService = new DataServiceImpl(especieDao, nivelDao);

    private Especie crearDefaultEspecie(String nombre) {
        Especie especie = new Especie(nombre, TipoBicho.AGUA,180, 100, 100, "https://i.ytimg.com/vi/MSV1z4-14Pw/hqdefault.jpg");
        especie.setCantidadBichos(5);
        return especie;
    }

    @Test
    public void al_guardar_y_luego_recuperar_se_obtiene_objetos_similares() {

        Especie especie = crearDefaultEspecie("TotoMon");
        Especie fidelMon = Runner.runInSession(() -> {
            this.especieDao.guardar(especie);

            return this.especieDao.getById(especie.getId());
        });

        assertEquals(fidelMon.getNombre(), especie.getNombre());
        assertEquals(fidelMon.getTipo(), especie.getTipo());
        assertEquals(fidelMon.getCantidadBichos(), especie.getCantidadBichos());
        assertEquals(fidelMon.getEnergiaInicial(), especie.getEnergiaInicial());
        assertEquals(fidelMon.getPeso(), especie.getPeso());
        assertEquals(fidelMon.getUrlFoto(), especie.getUrlFoto());
        assertEquals(fidelMon.getEvolucion(), especie.getEvolucion());
        assertEquals(fidelMon.getCondicionDeEvolucion(), especie.getCondicionDeEvolucion());
        assertEquals(fidelMon.getAltura(), especie.getAltura());
        assertTrue( especie == fidelMon);
    }

    @Test
    public void al_guardar_varios_y_luego_recuperar_todos_se_obtienen_en_una_lista_ordenada_ascendente() {
        // En el @before se insertaron 8 especies
        List<Especie> especiesRecuperadas =  Runner.runInSession(() -> {
            this.dataService.crearSetDatosIniciales();
            return this.especieDao.recuperarTodos();
        });

        assertNotNull(especiesRecuperadas);
        assertTrue(especiesRecuperadas.size()>0);

        if(especiesRecuperadas.size()>0){
            Especie especieAnterior = especiesRecuperadas.get(0);
            for (int i = 1; i < especiesRecuperadas.size(); i++) {
                assertTrue(especieAnterior.getNombre().compareToIgnoreCase(especiesRecuperadas.get(i).getNombre()) < 0);
                especieAnterior = especiesRecuperadas.get(i);
            }
        }
    }

    @Test
    public void al_guardar_mas_de_una_especie_con_el_mismo_nombre_no_la_persiste_y_devuelve_false() {

        Especie especie = crearDefaultEspecie("FidelMon");
        assertFalse( Runner.runInSession(() -> {
            return (this.especieDao.guardarValidado(especie) && this.especieDao.guardarValidado(especie));
            }));
    }

    @Test
    public void al_guardar_una_especie_verifica_que_no_haya_otra_con_el_mismo_nombre_y_devuelve_true() {

        Especie especie = crearDefaultEspecie("FidelMon");
        assertTrue( Runner.runInSession(() -> {
            return (this.especieDao.guardarValidado(especie));
        }));
    }

    @Test
    public void al_recuperar_una_especie_que_no_existe_devuelve_null() {
        assertNull(Runner.runInSession(() -> {
            return (this.especieDao.recuperar("NombreQueNoExiste"));
        }));
    }

    @Test
    public void al_recuperar_todos_de_una_base_sin_datos_devuelve_una_lista_vacia(){
        assertTrue(Runner.runInSession(() -> {
            return (this.especieDao.recuperarTodos().isEmpty());
        }));
    }

    @Test
    public void al_actualizar_y_luego_recuperar_se_obtiene_la_especie_modificada() {


        Especie especie = crearDefaultEspecie("PepeMon");
        Especie monomon = Runner.runInSession(() -> {
            this.especieDao.guardar(especie);
            especie.setNombre("Monomon");
            this.especieDao.actualizar(especie);
            return (this.especieDao.recuperar("Monomon"));
        });

        assertEquals(monomon.getNombre(), especie.getNombre());
        assertEquals(monomon.getTipo(), especie.getTipo());
        assertEquals(monomon.getCantidadBichos(), especie.getCantidadBichos());
        assertEquals(monomon.getEnergiaInicial(), especie.getEnergiaInicial());
        assertEquals(monomon.getPeso(), especie.getPeso());
        assertEquals(monomon.getUrlFoto(), especie.getUrlFoto());
        assertEquals(monomon.getEvolucion(), especie.getEvolucion());
        assertEquals(monomon.getCondicionDeEvolucion(), especie.getCondicionDeEvolucion());
        assertEquals(monomon.getAltura(), especie.getAltura());
        assertTrue( especie == monomon);
    }

}

