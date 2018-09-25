package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class HibernateEspecieDaoImple extends BaseHibernateDAO<Especie,Integer> implements EspecieDao {

    @Override
    public void guardar(Especie especie) {
        try {
            Session session = Runner.getCurrentSession();
            session.save(especie);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Boolean guardarValidado(Especie especie) {
        //TODO: Implementar
        throw new RuntimeException("Pendiente implementacion");
    }

    @Override
    public Especie recuperar(String nombreEspecie) {
        Session session = Runner.getCurrentSession();
        return session.get(Especie.class, nombreEspecie);
    }

    @Override
    public void eliminarEspecies() {
        // TODO: Implementar
        throw new RuntimeException("Pendiente implementacion");
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
        throw new RuntimeException("No Implementado");
        //TODO: implementar
    }
}
