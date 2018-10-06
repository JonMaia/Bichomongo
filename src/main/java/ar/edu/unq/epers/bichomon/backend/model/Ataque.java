package ar.edu.unq.epers.bichomon.backend.model;


import javax.persistence.Entity;

public class Ataque {
    private Bicho atacante;
    private Bicho atacado;
    private Float danioAtaque;

    public Ataque(Bicho bicho, Bicho atacado ,float danioAtaque) {
        this.atacado = atacado;
        this.atacante = bicho;
        this.danioAtaque = danioAtaque;
    }
}
