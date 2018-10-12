package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class HibernateEntrenadorDaoImple extends BaseHibernateDAO<Entrenador, String> implements EntrenadorDao{

    //@Override
    public Nivel getNivel(Entrenador entrenador) {
        StringBuffer squery = new StringBuffer();

        squery.append(" FROM " + Nivel.class.getSimpleName() + " n ");
        squery.append(" WHERE 1=1 ");
        squery.append(" AND n.expMinima < :experiencia ");
        squery.append(" AND n.expMaxima > :experiencia ");

        Session session = Runner.getCurrentSession();
        Query query = session.createQuery(squery.toString());
        query.setParameter("experiencia", entrenador.getExperiencia());
        return (Nivel) query.uniqueResult();
    }

    @Override
    public Entrenador recuperar(String nombreEntrenador) {
        Session session = Runner.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Entrenador> criteriaQuery = cb.createQuery(Entrenador.class);
        Root<Entrenador> root = criteriaQuery.from(Entrenador.class);
        criteriaQuery.select(root).where(cb.equal(root.get("nombre"), nombreEntrenador));
        return session.createQuery(criteriaQuery).uniqueResult();
    }
}
