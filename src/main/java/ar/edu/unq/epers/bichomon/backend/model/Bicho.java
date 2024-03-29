package ar.edu.unq.epers.bichomon.backend.model;

import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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

	@OneToOne(cascade = CascadeType.ALL)
	private Entrenador entrenador;

	@OneToOne(cascade = CascadeType.ALL)
	private Especie especie;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Entrenador> exEntrenadores = new ArrayList<>();

	private float energia;
	private float danioRecibidoCombate;
	private int victorias;
	private LocalDate fechaNacimiento;
	private LocalDate fechaCaptura;
	private int edad;

	public Bicho (){}

	public Bicho (Especie especie){
	    this.especie = especie;
	    this.victorias = 0;
	    this.fechaNacimiento = LocalDate.now();
	    this.energia = especie.getEnergiaInicial();

    }


	public Especie getEspecie() {
		return this.especie;
	}

	public void setEspecie(Especie especie) { this.especie = especie; }

	public float getDanioRecibidoCombate() {
		return danioRecibidoCombate;
	}

	public void setDanioRecibidoCombate(float danio){
		this.danioRecibidoCombate = danioRecibidoCombate + danio;
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

	public List<Entrenador> getExEntrenadores(){
		return this.exEntrenadores;
	}

	public void setExEntrenadores(List<Entrenador> entrenadores){
		this.exEntrenadores = entrenadores;
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

		boolean esEvolucionable = (this.getEspecie().getEvolucion() != null ? true : false);
		for ( Condicion condicion : this.getEspecie().getCondicionDeEvolucion())
			esEvolucionable = esEvolucionable && condicion.cumpleCondicion(this);

		return esEvolucionable;
	}

	public Bicho evolucionar(){
		Especie evolucion = this.getEspecie().getEvolucion();
		this.setEspecie(evolucion);
		return this;
	}

	public Float danioAInfligir(){
		float leftLimit = 0.5f;
		float rightLimit = 1f;
		float danioAtaque = this.getEnergia() * (leftLimit + new Random().nextFloat() * (rightLimit - leftLimit));
		return danioAtaque;
	}


	public Ataque atacar(Bicho bicho) {
		float danioAtaque = this.danioAInfligir();
		float danioInfligido = bicho.getDanioRecibidoCombate() + danioAtaque;
		bicho.setDanioRecibidoCombate(danioInfligido);
		return new Ataque(this, bicho , danioAtaque);
	}

	public void aumentarEnergiaCombate() {
		Random rand = new Random();
		int randomNum = rand.nextInt((5-1)+1) + 1;
		this.energia = this.energia + randomNum;
	}

	public boolean perdioCombate(){
		return this.energia < this.danioRecibidoCombate;
	}

}
