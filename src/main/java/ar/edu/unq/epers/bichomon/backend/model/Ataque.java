package ar.edu.unq.epers.bichomon.backend.model;


import javax.persistence.*;

@Entity
public class Ataque {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Bicho atacante;

    @OneToOne
    private Bicho atacado;
    private Float danioAtaque;

    public Ataque(Bicho bicho, Bicho atacado ,float danioAtaque) {
        this.atacado = atacado;
        this.atacante = bicho;
        this.danioAtaque = danioAtaque;
    }
}
