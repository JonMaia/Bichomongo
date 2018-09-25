package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.GenericDao;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseHibernateDAO<T, ID extends Serializable> implements GenericDao<T, ID> {

    private final Class<T> entityClass;

    public BaseHibernateDAO() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public BaseHibernateDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T getById(ID id) {
        Session session = Runner.getCurrentSession();
        return session.get(this.entityClass, id);
    }

    @Override
    public void guardar(T object) {
        try {
            Session session = Runner.getCurrentSession();
            session.save(object);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(T object) {
        Session session = Runner.getCurrentSession();
        session.saveOrUpdate(object);
    }

    @Override
    public void eliminar(T object) {
        Session session = Runner.getCurrentSession();
        session.delete(object);
    }



}
