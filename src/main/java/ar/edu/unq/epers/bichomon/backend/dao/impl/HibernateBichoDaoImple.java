package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDao;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateBichoDaoImple extends BaseHibernateDAO<Bicho, Integer> implements BichoDao {

    public HibernateBichoDaoImple() {}

    @Override
    public List<Bicho> recuperarTodosConEntrenador() {
        Session session = Runner.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Bicho> criteriaQuery = cb.createQuery(Bicho.class);
        Root<Bicho> root = criteriaQuery.from(Bicho.class);
        criteriaQuery.where(cb.isNotNull(root.get("entrenador")));
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Bicho> recuperarTodosSinEntrenador() {
        Session session = Runner.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Bicho> criteriaQuery = cb.createQuery(Bicho.class);
        Root<Bicho> root = criteriaQuery.from(Bicho.class);
        criteriaQuery.where(cb.isNull(root.get("entrenador")));
        return session.createQuery(criteriaQuery).getResultList();
    }
}
