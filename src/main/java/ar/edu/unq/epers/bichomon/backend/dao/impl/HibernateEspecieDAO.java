package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.Session;



import javax.naming.OperationNotSupportedException;
import java.util.List;

public class HibernateEspecieDAO implements EspecieDAO {
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
