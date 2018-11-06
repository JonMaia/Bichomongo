package ar.edu.unq.epers.bichomon.backend.model;

public class Maritimo implements TipoDeCamino {

    @Override
    public String getTipo() {
        return "MARITIMO";
    }

    @Override
    public Integer getCosto() {
        return 2;
    }
}
