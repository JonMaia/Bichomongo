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

    @Test
    public void al_guardar_mas_de_una_especie_con_el_mismo_nombre_no_la_persiste_y_devuelve_false() {

        Especie especie = crearDefaultEspecie("FidelMon");
        assertFalse( Runner.runInSession(() -> {
            return (this.especieDao.guardarValidado(especie) && this.especieDao.guardarValidado(especie));
            }));
    }
/*
    @Test
    public void al_guardar_una_especie_verifica_que_no_haya_otra_con_el_mismo_nombre_y_devuelve_true() {
        this.dataService.eliminarDatos();
        Especie fidelmon = crearDefaultEspecie("FidelMon");
        assertTrue(this.dao.guardarValidado(fidelmon));
    }

    @Test
    public void al_recuperar_una_especie_que_no_existe_devuelve_null() {

        Especie especieQueNoExiste = this.dao.recuperar("NombreQueNoExiste");
        assertNull(especieQueNoExiste);
    }

    @Test
    public void al_recuperar_todos_de_una_base_sin_datos_devuelve_una_lista_vacia(){
        eliminarModelo();
        List<Especie> especiesRecuperadas = this.dao.recuperarTodos();
        assertTrue(especiesRecuperadas.isEmpty());
    }

    @Test
    public void al_actualizar_y_luego_recuperar_se_obtiene_la_especie_modificada_pero_no_el_mismo_objeto() {

        Especie especie = this.dao.recuperar("Rojomon");
        especie.setNombre("Fidelmon");
        especie.setAltura(200);
        this.dao.actualizar(especie);

        Especie fidelmon = this.dao.recuperar("Fidelmon");
        assertEquals(fidelmon.getNombre(), especie.getNombre());
        assertEquals(fidelmon.getAltura(), especie.getAltura());
        assertTrue( especie != fidelmon);
    }



*/


}

