package ar.edu.unq.dao.hibernate;

import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.ubicacion.UbicacionService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class UbicacionDAOTest {

	@Before
	public void crearModelo() {
	}

	@Test
	public void retornaLaUbicacionDelEntrenador() {

		Ubicacion ubicacion = new Guarderia();

		Nivel nivel = new Nivel(1, 100, 50, null,1.0);

		Entrenador brock = new Entrenador("Brock", ubicacion, nivel, null );


		ubicacion.setNombre("CiudadPlateada");

		brock.setUbicacion(ubicacion);
		assertEquals(brock.getUbicacion().getNombre(), "CiudadPlateada");

	}
}