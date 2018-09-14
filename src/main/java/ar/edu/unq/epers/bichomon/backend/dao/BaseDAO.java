package ar.edu.unq.epers.bichomon.backend.dao;

public abstract class BaseDAO<K, T> implements GenericDAO<K, T> {

    protected Class<T> claz;

    public BaseDAO(Class<T> claz) {
        this.claz = claz;
    }

    @Override
    public T findById(K id) {
        return null;
    }

    @Override
    public void save(T object) {
    }

    @Override
    public void delete(K id) {
    }
}
