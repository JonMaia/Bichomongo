package ar.edu.unq.dao.hibernate;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.dao.NivelDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateNivelDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.model.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.After;
import org.junit.Before;
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
/*
    @Before
    public void crearModelo() {
        //this.dataService.crearSetDatosIniciales();
    }

    @After
    public void eliminarModelo() {
        //this.dataService.eliminarDatos();
    }
*/
    @Test
    public void al_guardar_y_luego_recuperar_se_obtiene_objetos_similares() {

        Especie especie = crearDefaultEspecie("FidelMon");
        Especie fidelMon = Runner.runInSession(() -> {
            this.especieDao.guardar(especie);

            return this.especieDao.getById(especie.getId());
        });

        assertEquals(especie.getNombre(), fidelMon.getNombre());
        assertEquals(especie.getAltura(), fidelMon.getAltura());
        assertEquals(especie.getPeso(), fidelMon.getPeso());
        assertEquals(especie.getTipo(), fidelMon.getTipo());
        assertEquals(especie.getEnergiaInicial(), fidelMon.getEnergiaInicial());
        assertEquals(especie.getUrlFoto(), fidelMon.getUrlFoto());
        assertEquals(especie.getCantidadBichos(), fidelMon.getCantidadBichos());
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


}

