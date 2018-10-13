package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.ChampionDao;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class HibernateChampionDaoImple extends BaseHibernateDAO<Champion,Integer> implements ChampionDao {


    @Override
    public List<Entrenador> findByActualChampion() {
        StringBuffer squery = new StringBuffer();

        squery.append("SELECT d.campeon.campeon.entrenador ");
        squery.append(" FROM " + Dojo.class.getSimpleName() + " d ");
        squery.append(" WHERE d.campeon IS NOT NULL");
        squery.append(" ORDER BY d.campeon.fechaCoronado ");


        Session session = Runner.getCurrentSession();
        Query<Entrenador> query = session.createQuery(squery.toString(), Entrenador.class);
        return query.list();
    }

    @Override
    public List<Bicho> getBichoChamion() {
        StringBuffer squery = new StringBuffer();

        squery.append("SELECT c.campeon ");
        squery.append(" FROM " + Champion.class.getSimpleName() + " c ");

        Session session = Runner.getCurrentSession();
        Query query = session.createQuery(squery.toString());
        return query.list();
    }

    @Override
    public Especie getEspecieLider() {


        StringBuffer squery = new StringBuffer();
        squery.append(" SELECT c.campeon.especie");
        squery.append(" FROM " + Champion.class.getSimpleName() + " c ");
        squery.append(" GROUP BY c.campeon.especie");
        squery.append(" ORDER BY count(distinct c.campeon) DESC");

        System.out.println(squery.toString());
        Session session = Runner.getCurrentSession();
        Query<Especie> query = session.createQuery(squery.toString(), Especie.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}