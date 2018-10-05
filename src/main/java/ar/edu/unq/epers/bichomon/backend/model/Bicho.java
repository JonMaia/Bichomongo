package ar.edu.unq.epers.bichomon.backend.model;

import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;

import javax.persistence.*;
import org.joda.time.LocalDate;


/**
 * Un {@link Bicho} existente en el sistema, el mismo tiene un nombre
 * y pertenece a una {@link Especie} en particular.
 * 
 * @author Charly Backend
 */
@Entity
public class Bicho {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	private Entrenador entrenador;

	@OneToOne
	private Especie especie;

	private float energia;
	private float danioRecibidoCombate;
	private int victorias;
	private LocalDate fechaNacimiento;
	private LocalDate fechaCaptura;
	private int edad;

	public Bicho (Especie especie){
	    this.especie = especie;
	    this.victorias = 0;
	    this.fechaNacimiento = LocalDate.now();

    }

	
	public Especie getEspecie() {
		return this.especie;
	}

	public void setEspecie(Especie especie) { this.especie = especie; }

	public float getDanioRecibidoCombate() {
		return danioRecibidoCombate;
	}

	public void setDanioRecibidoCombate(float danio){
		this.setDanioRecibidoCombate(danio);
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public Entrenador getEntrenador() { return entrenador; }

	public void setFechaCaptura(LocalDate fecha){
		this.fechaCaptura = fecha;
	}

	public LocalDate getFechaCaptura (){
		return this.fechaCaptura;
	}



	public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }

	public int getVictorias() { return victorias; }

	public void setVictorias(int victorias) { this.victorias = victorias; }

	public int getEdad() { return edad;	}

	public void setEdad(int edad) { this.edad = edad; }

	public float getEnergia() {
		return this.energia;
	}

	public void setEnergia(float energia) {
		this.energia = energia;
	}

	public boolean puedeEvolucionar(){
		boolean esEvolucionable = true;
		for ( Condicion condicion : this.getEspecie().getCondicionDeEvolucion())
			esEvolucionable = esEvolucionable && condicion.cumpleCondicion(this);
		return esEvolucionable;
	}

	public Bicho evolucionar(){
		if(this.puedeEvolucionar()){
			Especie evolucion = this.getEspecie().getEvolucion();
			this.setEspecie(evolucion);
		}
		return this;
	}


	public void atacar(Bicho bicho, float calculadorAtaque) {
		float danioRecibido = bicho.getDanioRecibidoCombate() + calculadorAtaque;
		bicho.setDanioRecibidoCombate(danioRecibido);
	}

	public void aumentarEnergiaCombate() {
		//this.energia = this.energia + Math.random();
	}
}
