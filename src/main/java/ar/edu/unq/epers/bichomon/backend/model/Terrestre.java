package ar.edu.unq.epers.bichomon.backend.model;

public class Terrestre implements TipoDeCamino {

    @Override
    public String getTipo() {
        return "TERRESTRE";
    }

    @Override
    public Integer getCosto() {
        return 1;
    }
}
