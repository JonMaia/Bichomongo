package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;

public class HibernateEntrenadorHibernateDAO extends BaseHibernateDAO<Entrenador, String> implements EntrenadorDao{

    public HibernateEntrenadorHibernateDAO(Class<Entrenador> claz) {
        super(claz);
    }


    @Override
    public void setUbicacionDAO(String ubicacion) {

    }

    @Override
    public void setNombreDAO(String entrenador) {

    }
}
