package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.ChampionDao;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class HibernateChampionDaoImple extends BaseHibernateDAO<Champion,Integer> implements ChampionDao {


    @Override
    public List<Champion> findByActualChampion() {
        StringBuffer squery = new StringBuffer();

        squery.append("SELECT c ");
        squery.append(" FROM " + Champion.class.getSimpleName() + " c ");
        squery.append(" WHERE c.perdiodo IS NULL");

        Session session = Runner.getCurrentSession();
        Query query = session.createQuery(squery.toString());
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
}

/*
    @Override
    public Especie getEspecieLider() {
        StringBuffer squery = new StringBuffer();
        squery.append(" SELECT e.espe")
        squery.append(" (SELECT (distinct c.campeon).especie as espe,  ");
        squery.append(" count(distinct c.campeon)  as n");
        squery.append(" FROM " + Champion.class.getSimpleName() + " c ");
        squery.append(" WHERE 1=1 ) e");

        Session session = Runner.getCurrentSession();
        Query query = session.createQuery(squery.toString());
    }
}
*/
/*
    select b.especie_id, count(b.id) as cant
        from Champion c
        inner join Bicho b on b.id = c.campeon_id
        GROUP by b.especie_id
        order By cant desc;
        */