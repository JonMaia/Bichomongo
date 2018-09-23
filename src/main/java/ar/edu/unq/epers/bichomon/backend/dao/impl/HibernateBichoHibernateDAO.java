package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;

public class HibernateBichoHibernateDAO extends BaseHibernateDAO<Bicho, String> implements BichoDAO {

    public HibernateBichoHibernateDAO(Class<Bicho> claz) {
        super(claz);
    }

}
