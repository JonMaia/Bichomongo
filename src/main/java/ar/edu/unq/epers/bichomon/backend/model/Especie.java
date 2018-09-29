package ar.edu.unq.epers.bichomon.backend.model;

import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;

import javax.persistence.*;
import java.util.List;

/**
 * Representa una {@link Especie} de bicho.
 * 
 * @author Charly Backend
 */
@Entity
public class Especie {

	@Id @GeneratedValue
	private Integer id;
	@Column(unique=true)
	private String nombre;
	private int altura;
	private int peso;
	private TipoBicho tipo;
	private int energiaInicial;
	private String urlFoto;
	private int cantidadBichos;
	@OneToOne
	private Especie evolucion;
	@ManyToMany
	private List<Condicion> condicionDeEvolucion;

	/**
	 * @return el nombre de la especie (por ejemplo: Perromon)
	 */
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @return la altura de todos los bichos de esta especie
	 */
	public int getAltura() {
		return this.altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	/**
	 * @return el peso de todos los bichos de esta especie
	 */
	public int getPeso() {
		return this.peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	/**
	 * @return una url que apunta al un recurso imagen el cual será
	 * utilizado para mostrar un thumbnail del bichomon por el frontend.
	 */
	public String getUrlFoto() {
		return this.urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	
	/**
	 * @return la cantidad de energia de poder iniciales para los bichos
	 * de esta especie.
	 */
	public int getEnergiaInicial() {
		return this.energiaInicial;
	}
	public void setEnergiaIncial(int energiaInicial) {
		this.energiaInicial = energiaInicial;
	}

	/**
	 * @return el tipo de todos los bichos de esta especie
	 */
	public TipoBicho getTipo() {
		return this.tipo;
	}
	public void setTipo(TipoBicho tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @return la cantidad de bichos que se han creado para esta
	 * especie.
	 */
	public int getCantidadBichos() {
		return this.cantidadBichos;
	}
	public void setCantidadBichos(int i) {
		this.cantidadBichos = i;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	public Especie(){

	}

	public Especie(String nombre, TipoBicho tipo, Integer altura, Integer peso, Integer energia, String foto) {
		this.setNombre(nombre);
		this.setTipo(tipo);
		this.setAltura(altura);
		this.setPeso(peso);
		this.setEnergiaIncial(energia);
		this.setUrlFoto(foto);
	}

	public Especie(int id, String nombre, TipoBicho tipo) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
	}


	public Bicho crearBicho(){
		this.cantidadBichos++;
		return new Bicho(this);
	}

    public List<Condicion> getCondicionDeEvolucion() {
        return condicionDeEvolucion;
    }

    public void setCondicionDeEvolucion(List<Condicion> condicionDeEvolucion) {
        this.condicionDeEvolucion = condicionDeEvolucion;
    }

    public Especie getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(Especie evolucion) {
        this.evolucion = evolucion;
    }


	public boolean puedeEvolucionar(Bicho bicho){
		boolean esEvolucionable = true;
		for ( Condicion condicion : condicionDeEvolucion)
			esEvolucionable = esEvolucionable && condicion.cumpleCondicion(bicho);

		return esEvolucionable;
	}

}