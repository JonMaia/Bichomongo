package ar.edu.unq.epers.bichomon.backend.model.bicho;

import ar.edu.unq.epers.bichomon.backend.model.Condicion.Condicion;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

/**
 * Un {@link Bicho} existente en el sistema, el mismo tiene un nombre
 * y pertenece a una {@link Especie} en particular.
 * 
 * @author Charly Backend
 */
public class Bicho {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;

	private Entrenador entrenador;

	private Especie especie;

	private int energia;
	private int victorias;
	private int edad;
	private Date fechaCaptura;
	private List<Condicion> condicionesEvolucion;
	
	public Especie getEspecie() {
		return this.especie;
	}

	public void setEspecie(Especie especie) { this.especie = especie; }

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public Entrenador getEntrenador() { return entrenador; }

	public void setFechaCaptura(Date fecha){
		this.fechaCaptura = fecha;
	}

	public Date getFechaCaptura (){
		return this.fechaCaptura;
	}

	public void setCondicionesEvolucion(List<Condicion> condicionesEvolucion){
		this.condicionesEvolucion = condicionesEvolucion;
	}

	public List<Condicion> getCondicionesEvolucion(){
		return this.condicionesEvolucion;
	}

	public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }

	public int getVictorias() { return victorias; }

	public void setVictorias(int victorias) { this.victorias = victorias; }

	public int getEdad() { return edad;	}

	public void setEdad(int edad) { this.edad = edad; }

	public int getEnergia() {
		return this.energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public boolean puedeEvolucionar(){
		boolean esEvolucionable = true;
		for ( Condicion condicion : condicionesEvolucion){
			esEvolucionable = esEvolucionable && condicion.cumpleCondicion(this);

		}
		return esEvolucionable;
	}

}
