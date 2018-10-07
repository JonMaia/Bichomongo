package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Especie;

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

	Entrenador crearEntrenedor();

	Bicho crearBichoConEntrenadorYEspecieConEvolucionSinCondicionDeEvolucion();

	Bicho crearBichoConEntrenadorYEspecieSinEvolucion();

	Bicho crearBichoConEnergia10ConEntrenadorYEspecieConEvolucionConCondicionDeEnergia0();

	Bicho crearBichoConVictorias10ConEntrenadorYEspecieConEvolucionConCondicionDeVictorias0();

	Bicho crearBichoConEdad10ConEntrenadorYEspecieConEvolucionConCondicionDeEdad0();

	Bicho crearBichoConNivel10ConEntrenadorYEspecieConEvolucionConCondicionDeNivel0();


}
