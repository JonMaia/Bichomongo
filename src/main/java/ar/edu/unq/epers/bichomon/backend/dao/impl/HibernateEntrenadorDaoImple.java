package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;

public class HibernateEntrenadorDaoImple extends BaseHibernateDAO<Entrenador, String> implements EntrenadorDao{

    public HibernateEntrenadorDaoImple(Class<Entrenador> claz) {
        super(claz);
    }


    @Override
    public void setUbicacionDAO(String ubicacion) {

    }

    @Override
    public void setNombreDAO(String entrenador) {

    }
}
