package ar.edu.unq.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import ar.edu.unq.epers.bichomon.backend.dao.*;
import ar.edu.unq.epers.bichomon.backend.model.especie.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EspecieDAOTest {

	private EspecieDAO dao = new EspecieDAOImple();
	private Especie especie;

	@Before
	public void crearModelo() {
		this.especie = new Especie();
		this.especie.setNombre("Pablomon");
		this.especie.setAltura(180);
		this.especie.setPeso(100);
	    this.especie.setTipo(TipoBicho.AGUA);
	    this.especie.setEnergiaIncial(100);
	    this.especie.setUrlFoto("images/pablomon.jpg");
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

}
