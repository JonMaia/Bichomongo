package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.DojoDao;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Dojo;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;

public class HibernateDojoDaoImple extends BaseHibernateDAO<Dojo, String> implements DojoDao {

    @Override
    public Bicho getCampeonHistorico(String nombreDojo) {
        Session session = Runner.getCurrentSession();
        StringBuffer hql = new StringBuffer();
        hql.append("SELECT c.campeon FROM " + Dojo.class.getName() + " d ");
        hql.append("JOIN d.campeones c ");
        hql.append("WHERE d.nombre == :nombreDojo " );
        hql.append("ORDER BY c.periodo DESC " );
        return session.createQuery(hql.toString(), Bicho.class)
                .setParameter("nombreDojo", nombreDojo)
                .setMaxResults(1)
                .uniqueResult();
    }
}
