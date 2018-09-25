package ar.edu.unq.epers.bichomon.backend.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

    T getById(ID id);

    void guardar(T object);

    void actualizar(T object);

    void eliminar(T object);
}