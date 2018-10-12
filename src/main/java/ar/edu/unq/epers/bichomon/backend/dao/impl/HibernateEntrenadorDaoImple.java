package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
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

        squery.append("SELECT e ");
        squery.append(" FROM " + Entrenador.class.getSimpleName() + " e ");
        squery.append(" GROUP BY e.nombre");
        squery.append(" ORDER BY sum(e.bichomones.energia) DESC");


        Session session = Runner.getCurrentSession();

        Query query = session.createQuery(squery.toString());
        query.setMaxResults(10);
        return query.list();
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




    /*
    select eb.Entrenador_nombre from Entrenador_Bicho eb
    inner join Bicho b on b.id = eb.bichomones_id
    GROUP by eb.Entrenador_nombre
    order by sum(b.energia) desc
    limit 10


     */

}
