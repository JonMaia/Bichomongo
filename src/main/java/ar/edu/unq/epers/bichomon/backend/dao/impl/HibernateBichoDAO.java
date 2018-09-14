package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.BaseDAO;
import ar.edu.unq.epers.bichomon.backend.dao.BichoDAO;
import ar.edu.unq.epers.bichomon.backend.model.bicho.Bicho;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;

public class HibernateBichoDAO extends BaseDAO<String, Bicho> implements BichoDAO {

    public HibernateBichoDAO(Class<Bicho> claz) {
        super(claz);
    }

}
