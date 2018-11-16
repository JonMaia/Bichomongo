package ar.edu.unq.epers.bichomon.backend.service;

import java.io.Serializable;

public interface GenericService<T, ID extends Serializable, D> {

    T getById(ID id);

    void guardar(T object);

    void actualizar(T object);

    void eliminar(T object);
}