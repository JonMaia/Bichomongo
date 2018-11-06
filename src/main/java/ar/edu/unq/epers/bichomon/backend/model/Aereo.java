package ar.edu.unq.epers.bichomon.backend.model;

public class Aereo implements TipoDeCamino {
    @Override
    public String getTipo() {
        return "AEREO";
    }

    @Override
    public Integer getCosto() {
        return 5;
    }
}
