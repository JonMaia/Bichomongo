package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class HibernateEspecieDaoImple extends BaseHibernateDAO<Especie,Integer> implements EspecieDao {

    @Override
    public void guardar(Especie especie) {
        Session session = Runner.getCurrentSession();
        session.save(especie);
    }

    @Override
    public Boolean guardarValidado(Especie especie) {
        if (recuperar(especie.getNombre()) == null) {
                guardar(especie);
                return true;
        }else{
            return false;
        }
    }

    @Override
    public Especie recuperar(String nombreEspecie) {
        Session session = Runner.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Especie> criteriaQuery = cb.createQuery(Especie.class);
        Root<Especie> root = criteriaQuery.from(Especie.class);
        criteriaQuery.select(root).where(cb.equal(root.get("nombre"), nombreEspecie));
        return session.createQuery(criteriaQuery).uniqueResult();
        }

    @Override
    public void eliminarEspecies() {
        Session session = Runner.getCurrentSession();
        session.delete("%"); //utiliza el nombre para identificar la especie, por lo que un % coincide con cualquier nombre
    }

    @Override
    public List<Especie> recuperarTodos() {
        Session session = Runner.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Especie> criteriaQuery = cb.createQuery(Especie.class);
        Root<Especie> root = criteriaQuery.from(Especie.class);
        criteriaQuery.orderBy(cb.asc(root.get("nombre")));
        return session.createQuery(criteriaQuery).getResultList();
    }


    @Override
    public void actualizar(Especie especie) {
        Session session = Runner.getCurrentSession();
        session.saveOrUpdate(especie);

    }

    @Override
    public List<Especie> getPopulares() {
        Session session = Runner.getCurrentSession();
        StringBuffer hql = new StringBuffer();
        hql.append("SELECT b.especie FROM " + Bicho.class.getName() + " b ");
        hql.append("JOIN b.entrenador e ");
        hql.append("WHERE b.entrenador is not null ");
        hql.append("GROUP BY b.especie " );
        hql.append("ORDER BY COUNT(*) DESC " );
        return session.createQuery(hql.toString(), Especie.class).setMaxResults(10).list();
    }

    @Override
    public List<Especie> getImpopulares() {
        Session session = Runner.getCurrentSession();
        StringBuffer hql = new StringBuffer();
        hql.append("SELECT b.especie FROM " + Bicho.class.getName() + " b ");
        hql.append("WHERE b.entrenador is null ");
        hql.append("GROUP BY b.especie " );
        hql.append("ORDER BY COUNT(*) DESC " );
        return session.createQuery(hql.toString(), Especie.class).setMaxResults(10).list();
    }
}
