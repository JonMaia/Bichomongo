package ar.edu.unq.dao;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class HibernateEspecieDAOTest {

    private EspecieDAO dao = new HibernateEspecieDAO();
    private DataService dataService = new DataServiceImpl(dao);

    private Especie crearDefaultEspecie(String nombre) {
        Especie especie = new Especie();
        especie.setNombre(nombre);
        especie.setAltura(180);
        especie.setPeso(100);
        especie.setTipo(TipoBicho.AGUA);
        especie.setEnergiaIncial(100);
        especie.setUrlFoto("https://i.ytimg.com/vi/MSV1z4-14Pw/hqdefault.jpg");
        especie.setCantidadBichos(5);
        return especie;
    }

    @Before
    public void crearModelo() {
        //this.dataService.crearSetDatosIniciales();
    }

    @After
    public void eliminarModelo() {
        //this.dataService.eliminarDatos();
    }

    @Test
    public void al_guardar_y_luego_recuperar_se_obtiene_objetos_similares() {

        Especie especie = crearDefaultEspecie("FidelMon");
        Especie fidelMon = Runner.runInSession(() -> {
            this.dao.guardar(especie);

            return this.dao.getById(especie.getId());
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
            return this.dao.recuperarTodos();
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
