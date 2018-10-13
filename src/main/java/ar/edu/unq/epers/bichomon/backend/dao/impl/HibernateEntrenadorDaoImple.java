package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Champion;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class HibernateEntrenadorDaoImple extends BaseHibernateDAO<Entrenador, String> implements EntrenadorDao{
    @Override
    public List<Entrenador> conMejoresBichos() {
        StringBuffer squery = new StringBuffer();

        squery.append(" SELECT b.entrenador ");
        squery.append(" FROM " + Bicho.class.getSimpleName() + " b ");
        squery.append(" GROUP BY b.entrenador");
        squery.append(" ORDER BY sum(b.energia) DESC");


        Session session = Runner.getCurrentSession();

        Query<Entrenador> query = session.createQuery(squery.toString(), Entrenador.class);
        query.setMaxResults(10);
        return query.list();
    }

}
