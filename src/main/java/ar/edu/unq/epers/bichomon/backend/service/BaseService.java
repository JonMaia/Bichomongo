package ar.edu.unq.epers.bichomon.backend.service;

import ar.edu.unq.epers.bichomon.backend.dao.GenericDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.BaseHibernateDAO;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class BaseService<T, ID extends Serializable> implements GenericDao<T, ID> {

    private final Class<T> entityClass;

    private GenericDao<T,ID> dao;

    public void setDao(GenericDao<T, ID> dao){
        this.dao = dao;
    }

    public BaseService() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public BaseService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T getById(ID id) {
        return dao.getById(id);
    }

    @Override
    public void guardar(T object) {
        dao.guardar(object);
    }

    @Override
    public void actualizar(T object) {
        dao.actualizar(object);
    }

    @Override
    public void eliminar(T object) {
        dao.eliminar(object);
    }


}
