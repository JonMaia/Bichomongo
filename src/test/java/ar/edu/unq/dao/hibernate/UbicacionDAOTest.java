package ar.edu.unq.dao.hibernate;

import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;
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

		Ubicacion ubicacion = new Ubicacion();

		ubicacion.setNombre("CiudadPlateada");

		brock.setUbicacion(ubicacion);
		assertEquals(brock.getUbicacion().getNombre(), "CiudadPlateada");

	}
}