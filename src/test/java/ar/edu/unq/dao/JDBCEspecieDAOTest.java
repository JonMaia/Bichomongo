package ar.edu.unq.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.JDBCEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.*;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


public class JDBCEspecieDAOTest {

	// TODO: Lograr que rolbackee al finalizar los test.

	private EspecieDAO dao = new JDBCEspecieDAO();
	private DataService dataService = new DataServiceImpl(dao);


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


	@Test(expected = RuntimeException.class)
	public void al_guardar_mas_de_una_especie_con_el_mismo_nombre_lanza_una_excepcion() {
		this.dataService.eliminarDatos();
		this.dao.guardar(crearDefaultEspecie("FidelMon"));
		this.dao.guardar(crearDefaultEspecie("FidelMon"));
	}

	@Test
	public void al_guardar_varios_y_luego_recuperar_todos_se_obtienen_la_misma_Cantidad(){
		this.dataService.eliminarDatos();
		this.dataService.crearSetDatosIniciales();
		List<Especie> especiesRecuperadas = this.dao.recuperarTodos();

		assertTrue(especiesRecuperadas.size() == 8);
	}

	@Test
	public void al_actualizar_y_luego_recuperar_se_obtiene_objetos_similares() {

		Especie especie = this.dao.recuperar("Rojomon");
		especie.setNombre("Fidelmon");
		especie.setAltura(200);
		this.dao.actualizar(especie);

		Especie fidelmon = this.dao.recuperar("Fidelmon");
		assertEquals(fidelmon.getNombre(), "Fidelmon");
		assertEquals(fidelmon.getAltura(), 200);
		/*assertEquals(this.especie2.getPeso(), pablomon2.getPeso());
		assertEquals(this.especie2.getTipo(), pablomon2.getTipo());
		assertEquals(this.especie2.getEnergiaInicial(), pablomon2.getEnergiaInicial());
		assertEquals(this.especie2.getUrlFoto(), pablomon2.getUrlFoto());
		assertEquals(this.especie2.getCantidadBichos(), pablomon2.getCantidadBichos());
		*/
		assertTrue( especie != fidelmon);
	}
}

