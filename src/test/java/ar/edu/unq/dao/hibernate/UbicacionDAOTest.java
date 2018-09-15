package ar.edu.unq.dao.hibernate;

import ar.edu.unq.epers.bichomon.backend.model.Entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.ubicacion.UbicacionService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UbicacionDAOTest {

	private UbicacionService ubicacionService = new UbicacionService();


	@Before
	public void crearModelo() {
	}

	@Test
	public void retornaLaUbicacionDelEntrenador() {

		Entrenador brock = new Entrenador();

		brock.setUbicacion("CiudadPlateada");

		assertEquals(brock.getUbicacion(), "CiudadPlateada");

	}
}