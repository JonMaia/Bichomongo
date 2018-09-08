package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.BaseDAO;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador.Entrenador;

public class HibernateEntrenadorDAO extends BaseDAO<String, Entrenador> implements EntrenadorDao{

    public HibernateEntrenadorDAO(Class<Entrenador> claz) {
        super(claz);
    }

}
