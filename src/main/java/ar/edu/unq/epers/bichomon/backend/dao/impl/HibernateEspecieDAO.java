package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;

import java.util.List;

public class HibernateEspecieDAO implements EspecieDAO {
    @Override
    public void guardar(Especie especie) {
        Session session = Runner.getCurrentSession();
        session.save(especie);
    }

    @Override
    public void actualizar(Especie especie) {

    }

    @Override
    public Especie recuperar(String nombreEspecie) {
        Session session = Runner.getCurrentSession();
        return session.get(Especie.class, nombreEspecie);
    }

    @Override
    public Especie getById(Integer id) {
        Session session = Runner.getCurrentSession();
        return session.get(Especie.class, id);
    }

    @Override
    public List<Especie> recuperarTodos() {
        return null;
    }

    @Override
    public void eliminarEspecies() {

    }
}
