package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.UbicacionDao;
import ar.edu.unq.epers.bichomon.backend.model.Guarderia;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class HibernateUbicacionDaoImple extends BaseHibernateDAO<Ubicacion,String> implements UbicacionDao{

    @Override
    public Ubicacion recuperar(String nombre) {
        Session session = Runner.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Ubicacion> criteriaQuery = cb.createQuery(Ubicacion.class);
        Root<Ubicacion> root = criteriaQuery.from(Ubicacion.class);
        criteriaQuery.select(root).where(cb.equal(root.get("nombre"), nombre));
        return session.createQuery(criteriaQuery).uniqueResult();
    }

    @Override
    public Guarderia recuperarGuarderia(String nombre) {
        Session session = Runner.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Guarderia> criteriaQuery = cb.createQuery(Guarderia.class);
        Root<Guarderia> root = criteriaQuery.from(Guarderia.class);
        criteriaQuery.select(root).where(cb.equal(root.get("nombre"), nombre));
        return session.createQuery(criteriaQuery).uniqueResult();
    }


}
