package ar.edu.unq.dao.hibernate;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.model.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class EspecieDAOTest {

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
		this.dataService.eliminarDatos();
		this.dataService.crearSetDatosIniciales();
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

	@Test(expected = RuntimeException.class)
	public void al_guardar_mas_de_una_especie_con_el_mismo_nombre_lanza_una_excepcion() {
		this.dataService.eliminarDatos();
		this.dao.guardar(crearDefaultEspecie("FidelMon"));
		this.dao.guardar(crearDefaultEspecie("FidelMon"));
	}

	@Test
	public void al_recuperar_una_especie_que_no_existe_devuelve_null() {
		this.dataService.eliminarDatos();
		Especie especieQueNoExiste = this.dao.recuperar("NombreQueNoExiste");
		assertNull(especieQueNoExiste);
	}

	@Test
	public void al_guardar_varios_y_luego_recuperar_todos_se_obtienen_la_misma_Cantidad(){
		// En el @before se limpio la base y se insertaron 8 especies
		List<Especie> especiesRecuperadas = this.dao.recuperarTodos();

		assertTrue(especiesRecuperadas.size() == 8);
	}

	@Test
	public void al_recuperar_todos_de_una_base_sin_datos_devuelve_una_lista_vacia(){
		this.dataService.eliminarDatos();
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
}

