package ar.edu.unq.epers.bichomon.backend.model;

import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

	private int energia;
	private int victorias;
	private int edad;
	private Date fechaCaptura;

	public Bicho (Especie especie){
	    this.especie = especie;
    }
	
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



}
