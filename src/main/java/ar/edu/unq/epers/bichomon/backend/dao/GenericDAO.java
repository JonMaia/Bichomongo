package ar.edu.unq.epers.bichomon.backend.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

    T getById(ID id);

    List<T> recuperarTodos();

    void guardar(T object);

    void actualizar(T object);

    void eliminar(T object);
}