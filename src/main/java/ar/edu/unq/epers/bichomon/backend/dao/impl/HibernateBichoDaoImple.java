package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDao;
import ar.edu.unq.epers.bichomon.backend.model.Bicho;

public class HibernateBichoDaoImple extends BaseHibernateDAO<Bicho, String> implements BichoDao {

    public HibernateBichoDaoImple(Class<Bicho> claz) {
        super(claz);
    }

}
