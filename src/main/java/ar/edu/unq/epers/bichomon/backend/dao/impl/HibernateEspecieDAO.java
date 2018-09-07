package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

import java.util.List;

public class HibernateEspecieDAO implements EspecieDAO {
    @Override
    public void guardar(Especie especie) {

    }

    @Override
    public void actualizar(Especie especie) {

    }

    @Override
    public Especie recuperar(String nombreEspecie) {
        return null;
    }

    @Override
    public List<Especie> recuperarTodos() {
        return null;
    }

    @Override
    public void eliminarEspecies() {

    }
}
