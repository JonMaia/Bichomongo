package ar.edu.unq.epers.bichomon.backend.dao;

public interface GenericDAO<K, T> {
    T findById(K id);

    void save(T object);

    void delete(K id);
}