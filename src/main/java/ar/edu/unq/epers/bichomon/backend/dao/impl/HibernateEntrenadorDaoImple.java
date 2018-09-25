package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class HibernateEntrenadorDaoImple extends BaseHibernateDAO<Entrenador, String> implements EntrenadorDao{

    public HibernateEntrenadorDaoImple(Class<Entrenador> claz) {
        super(claz);
    }

    @Override
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
}
