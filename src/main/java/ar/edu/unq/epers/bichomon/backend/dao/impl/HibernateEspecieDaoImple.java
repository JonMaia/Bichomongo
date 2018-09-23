package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;


public class HibernateEspecieDaoImple extends BaseHibernateDAO<Especie,Integer> implements EspecieDao {
    @Override
    public void guardar(Especie especie) {
        try {
            Session session = Runner.getCurrentSession();
            session.save(especie);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Boolean guardarValidado(Especie especie) {
        throw new RuntimeException("No Implementado");
        //TODO: implementar
    }

    @Override
    public void actualizar(Especie especie) {

    }

    @Override
    public Especie recuperar(String nombreEspecie) {
        Session session = Runner.getCurrentSession();
        return session.get(Especie.class, nombreEspecie);
    }

    @Override
    public void eliminarEspecies() {

    }
}
