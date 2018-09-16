package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateEspecieDAO extends BaseHibernateDAO<Especie, Integer> implements EspecieDAO {

    @Override
    public Especie recuperar(String nombreEspecie) {
        Session session = Runner.getCurrentSession();
        return session.get(Especie.class, nombreEspecie);
    }

    @Override
    public void eliminarEspecies() {
        // TODO: Implementar
        throw new RuntimeException("Pendiente implementacion");
    }

    @Override
    public List<Especie> recuperarTodos() {
        Session session = Runner.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Especie> criteriaQuery = cb.createQuery(Especie.class);
        Root<Especie> root = criteriaQuery.from(Especie.class);
        criteriaQuery.orderBy(cb.asc(root.get("nombre")));
        return session.createQuery(criteriaQuery).getResultList();
    }
}
