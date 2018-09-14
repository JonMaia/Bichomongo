package ar.edu.unq.epers.bichomon.backend.model.Exception;

import ar.edu.unq.epers.bichomon.backend.model.Entrenador.Entrenador;

public class UbicacionIncorrectaException extends RuntimeException {

    private Entrenador entrenador;

    /*
    private static final long serialVersionUID = 1L;

	private final Personaje personaje;
	private final Item item;

	public MuchoPesoException(Personaje personaje, Item item) {
		this.personaje = personaje;
		this.item = item;
	}

	@Override
	public String getMessage() {
		return "El personaje [" + this.personaje + "] no puede recoger [" + this.item + "] porque cagar mucho peso ya";
	}*/

    public UbicacionIncorrectaException(Entrenador entrenador){
        this.entrenador = entrenador;
    }



}
