package ar.edu.unq.dao;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.dao.NivelDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateNivelDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.JDBCEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.model.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;


public class JDBCEspecieDAOTest {
/*
	private EspecieDao dao = new JDBCEspecieDAO();
    private NivelDao nivelDao = new HibernateNivelDaoImple();
    private DataService dataService = new DataServiceImpl(dao, nivelDao);

    private Especie crearDefaultEspecie(String nombre) {
        Especie especie = new Especie(nombre, TipoBicho.AGUA,180, 100, 100, "https://i.ytimg.com/vi/MSV1z4-14Pw/hqdefault.jpg");
        especie.setCantidadBichos(5);
        return especie;
    }

	@Before
	public void crearModelo() {
		this.dataService.crearSetDatosIniciales();
	}

	@After
	public void eliminarModelo() {
		this.dataService.eliminarDatos();
	}

	@Test
	public void al_guardar_y_luego_recuperar_se_obtiene_objetos_similares() {

		Especie especie = crearDefaultEspecie("FidelMon");
		this.dao.guardar(especie);

		//Las especies son iguales
		Especie fidelMon = this.dao.recuperar("FidelMon");
		assertEquals(especie.getNombre(), fidelMon.getNombre());
		assertEquals(especie.getAltura(), fidelMon.getAltura());
		assertEquals(especie.getPeso(), fidelMon.getPeso());
		assertEquals(especie.getTipo(), fidelMon.getTipo());
		assertEquals(especie.getEnergiaInicial(), fidelMon.getEnergiaInicial());
		assertEquals(especie.getUrlFoto(), fidelMon.getUrlFoto());
		assertEquals(especie.getCantidadBichos(), fidelMon.getCantidadBichos());
		//Pero no son el mismo objeto =(
		assertTrue(especie != fidelMon);
	}


	@Test
	public void al_guardar_mas_de_una_especie_con_el_mismo_nombre_no_la_persiste_y_devuelve_false() {
		this.dataService.eliminarDatos();
		Especie fidelmon = crearDefaultEspecie("FidelMon");
		assertFalse(this.dao.guardarValidado(fidelmon) && this.dao.guardarValidado(fidelmon));
	}

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
	public void al_guardar_varios_y_luego_recuperar_todos_se_obtienen_en_una_lista_ordenada_ascendente() {
		// En el @before se insertaron 8 especies
		List<Especie> especiesRecuperadas = this.dao.recuperarTodos();
		for (int i = 0; i < especiesRecuperadas.size(); i++) {
			assertTrue(especiesRecuperadas.get(i).getNombre().equalsIgnoreCase(this.dao.recuperarTodos().get(i).getNombre()));
		}
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