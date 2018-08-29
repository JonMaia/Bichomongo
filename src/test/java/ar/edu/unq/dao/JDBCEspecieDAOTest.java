package ar.edu.unq.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.JDBCEspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class JDBCEspecieDAOTest {

	private EspecieDAO dao = new JDBCEspecieDAO();
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

	@Test
	public void al_guardar_varios_y_luego_recuperar_todos_se_obtienen_la_misma_Cantidad_o_mayor(){
		List<Especie> especiesOriginales = generarTodos();
		List<Especie> especiesRecuperadas = this.dao.recuperarTodos();

		assertTrue(especiesRecuperadas.size() >= especiesOriginales.size());
	}

	/**
	 * Crea todos los bichomongos, los guarda y lso devuelve en una lista.
	 */
	private List<Especie> generarTodos(){

		List<Especie> especies = new ArrayList<Especie>();

		Especie red = new Especie();
		red.setNombre("Rojomon");
		red.setTipo(TipoBicho.FUEGO);
		red.setAltura(180);
		red.setPeso(75);
		red.setEnergiaIncial(100);
		red.setUrlFoto("/image/rojomon.jpg");
		red.getNombre();
		this.dao.guardar(red);
		especies.add(red);

		Especie amarillo = new Especie();
		amarillo.setNombre("Amarillomon");
		amarillo.setTipo(TipoBicho.ELECTRICIDAD);
		amarillo.setAltura(170);
		amarillo.setPeso(69);
		amarillo.setEnergiaIncial(300);
		amarillo.setUrlFoto("/image/amarillomon.png");
		amarillo.getNombre();
		this.dao.guardar(amarillo);
		especies.add(amarillo);

		Especie green = new Especie();
		green.setNombre("Verdemon");
		green.setTipo(TipoBicho.PLANTA);
		green.setAltura(150);
		green.setPeso(55);
		green.setEnergiaIncial(5000);
		green.setUrlFoto("/image/verdemon.jpg");
		green.getNombre();
		this.dao.guardar(green);
		especies.add(green);

		Especie turronmon = new Especie();
		turronmon.setNombre("Tierramon");
		turronmon.setTipo(TipoBicho.TIERRA);
		turronmon.setAltura(1050);
		turronmon.setPeso(99);
		turronmon.setEnergiaIncial(5000);
		turronmon.setUrlFoto("/image/tierramon.jpg");
		turronmon.getNombre();
		this.dao.guardar(turronmon);
		especies.add(turronmon);

		Especie fantasmon = new Especie();
		fantasmon.setNombre("Fantasmon");
		fantasmon.setTipo(TipoBicho.AIRE);
		fantasmon.setAltura(1050);
		fantasmon.setPeso(99);
		fantasmon.setEnergiaIncial(5000);
		fantasmon.setUrlFoto("/image/fantasmon.jpg");
		fantasmon.getNombre();
		this.dao.guardar(fantasmon);
		especies.add(fantasmon);

		Especie vampiron = new Especie();
		vampiron.setNombre("Vanpiron");
		vampiron.setTipo(TipoBicho.AIRE);
		vampiron.setAltura(1050);
		vampiron.setPeso(99);
		vampiron.setEnergiaIncial(5000);
		vampiron.setUrlFoto("/image/vampiromon.jpg");
		vampiron.getNombre();
		this.dao.guardar(vampiron);
		especies.add(vampiron);

		Especie fortmon = new Especie();
		fortmon.setNombre("Fortmon");
		fortmon.setTipo(TipoBicho.CHOCOLATE);
		fortmon.setAltura(1050);
		fortmon.setPeso(99);
		fortmon.setEnergiaIncial(5000);
		fortmon.setUrlFoto("/image/fortmon.png");
		fortmon.getNombre();
		this.dao.guardar(fortmon);
		especies.add(fortmon);

		Especie dientemon = new Especie();
		dientemon.setNombre("Dientemon");
		dientemon.setTipo(TipoBicho.AGUA);
		dientemon.setAltura(1050);
		dientemon.setPeso(99);
		dientemon.setEnergiaIncial(5000);
		dientemon.setUrlFoto("/image/dientmon.jpg");
		dientemon.getNombre();
		this.dao.guardar(dientemon);
		especies.add(dientemon);

		return especies;
	}
}
