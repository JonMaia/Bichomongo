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
	private Especie especie;

	@Before
	public void crearModelo() {
		this.especie = new Especie();
		this.especie.setNombre("Pablomon");
		this.especie.setAltura(180);
		this.especie.setPeso(100);
	    this.especie.setTipo(TipoBicho.AGUA);
	    this.especie.setEnergiaIncial(100);
	    this.especie.setUrlFoto("image/pablomon.jpg");
	    this.especie.setCantidadBichos(5);
	}

	@Test
	public void al_guardar_y_luego_recuperar_se_obtiene_objetos_similares() {
		this.dataService.eliminarDatos();
		this.dao.guardar(this.especie);

		//Las especies son iguales
		Especie pablomon = this.dao.recuperar("Pablomon");
		assertEquals(this.especie.getNombre(), pablomon.getNombre());
		assertEquals(this.especie.getAltura(), pablomon.getAltura());
		assertEquals(this.especie.getPeso(), pablomon.getPeso());
		assertEquals(this.especie.getTipo(), pablomon.getTipo());
		assertEquals(this.especie.getEnergiaInicial(), pablomon.getEnergiaInicial());
		assertEquals(this.especie.getUrlFoto(), pablomon.getUrlFoto());
		assertEquals(this.especie.getCantidadBichos(), pablomon.getCantidadBichos());
		//Pero no son el mismo objeto =(
		assertTrue(this.especie != pablomon);
	}


	@Test(expected = RuntimeException.class)
	public void al_guardar_mas_de_una_especie_con_el_mismo_nombre_lanza_una_excepcion() {
		this.dataService.eliminarDatos();
		this.dao.guardar(this.especie);
		this.dao.guardar(this.especie);
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

		this.dao.guardar(especie);
		especie = this.dao.recuperar("Pablomon");
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
		assertTrue( this.especie != fidelmon);
	}
}

