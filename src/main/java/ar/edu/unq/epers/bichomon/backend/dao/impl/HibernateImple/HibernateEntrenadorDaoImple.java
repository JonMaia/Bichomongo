package ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class HibernateEntrenadorDaoImple extends BaseHibernateDAO<Entrenador, String> implements EntrenadorDao {

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

}
