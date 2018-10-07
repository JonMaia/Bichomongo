package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
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

}
