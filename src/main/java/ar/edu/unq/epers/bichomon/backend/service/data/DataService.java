package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Este servicio solo será utilizado para pruebas.
 * 
 * @author Steve Frontend
 */
public interface DataService {

	/**
	 *  Se espera que tras ejecutarse esto se elimine toda la información persistida
	 *  en la base de datos, de manera de poder comenzar el siguiente tests desde cero.
	 */
	void eliminarDatos();
	
	/**
	 * Crea un set de datos iniciales (de momento solo objetos {@link Especie}) para
	 * facilitar las pruebas de frontend.
	 */
	void crearSetDatosIniciales();

	void crearSetEspeciesIniciales();

	Especie crearEspecieBase();

	Especie crearEspecieBaseConEvolucion();

	Especie crearEspecieConEvolucionConCondicionDeEnergia0();

	Especie crearEspecieConEvolucionConCondicionDeVictorias0();

	Especie crearEspecieConEvolucionConCondicionDeEdad0();

	Especie crearEspecieConEvolucionConCondicionDeNivel0();

	Entrenador crearEntrenador();

	Bicho crearBichoConEntrenadorYEspecieConEvolucionSinCondicionDeEvolucion();

	Bicho crearBichoConEntrenadorYEspecieSinEvolucion();

	Bicho crearBichoConEnergia10ConEntrenadorYEspecieConEvolucionConCondicionDeEnergia0();

	Bicho crearBichoConVictorias10ConEntrenadorYEspecieConEvolucionConCondicionDeVictorias0();

	Bicho crearBichoConEdad10ConEntrenadorYEspecieConEvolucionConCondicionDeEdad0();

	Bicho crearBichoConNivel10ConEntrenadorYEspecieConEvolucionConCondicionDeNivel0();

	List<Bicho> crear2BichosPara10EspeciesYUnBichoPara2EspeciesConEntrenador();

    Bicho crearBchoConEsspecieSinEntrenador();

	Bicho crearBichoCampeonConEntrenadorYEspecieYEnDojo(String nombreEspecie, String nombreEntrenador, String nombreDojo);

	List<Bicho> crear2BichosPara10EspeciesYUnBichoPara2EspeciesSinEntrenador();

	Dojo crearDojo();

    Pueblo crearPuebloConProbabilidadExitoYEspecie(String nombrePueblo, Integer probabilidadExito, String nombreEspecie);

    Bicho crearBichoDeEspecieYDeEntrenador(String otraEspecie, String otroEntrenador);

	Pueblo crearPuebloConProbabilidadExito100();

	Pueblo crearPuebloConProbabilidadExito0();

	Bicho crearBichoConEntrenadorYEspecieYEnergia200EnDojo();

	Bicho crearBichoConEntrenadorYEspecieSinEvolucionEnPuebloConProbabilidad100();

    Entrenador crearEntrenador(String nombreEntrenador);

	Entrenador actualizarExperienciaEntrenador(Entrenador entrenador, Integer experiencia);

	Entrenador crearEntrenadorConUbicacion(String nombreEntrenador, Ubicacion ubicacion);

	Entrenador crearEntrenadorConUbicacionYPlata(String nombreEntrenador, Ubicacion ubicacion);
}
