package ar.edu.unq.epers.bichomon.backend.dao;

import java.util.List;

public interface GenericMongoDao<T> {

    void save(T object);

    void save(List<T> objects);

    void deleteAll();


    T get(String id);

    List<T> findAndSortBy(String query, String sortCriteria, Object... parameters);

    List<T> find(String query, Object... parameters);

}
