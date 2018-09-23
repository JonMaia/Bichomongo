package ar.edu.unq.dao;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.model.TipoBicho;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HibernateEspecieDAOTest {

    private EspecieDao dao = new HibernateEspecieDaoImple();
    private DataService dataService = new DataServiceImpl(dao);

    private Especie crearDefaultEspecie(String nombre) {
        Especie especie = new Especie();
        especie.setNombre(nombre);
        especie.setAltura(180);
        especie.setPeso(100);
        especie.setTipo(TipoBicho.AGUA);
        especie.setEnergiaIncial(100);
        especie.setUrlFoto("https://i.ytimg.com/vi/MSV1z4-14Pw/hqdefault.jpg");
        especie.setCantidadBichos(5);
        return especie;
    }


    @Test
    public void al_guardar_y_luego_recuperar_se_obtiene_objetos_similares() {

        Especie especie = crearDefaultEspecie("FidelMon");
        Especie fidelMon = Runner.runInSession(() -> {
            this.dao.guardar(especie);

            return this.dao.getById(especie.getId());
        });

        assertEquals(especie.getNombre(), fidelMon.getNombre());
        assertEquals(especie.getAltura(), fidelMon.getAltura());
        assertEquals(especie.getPeso(), fidelMon.getPeso());
        assertEquals(especie.getTipo(), fidelMon.getTipo());
        assertEquals(especie.getEnergiaInicial(), fidelMon.getEnergiaInicial());
        assertEquals(especie.getUrlFoto(), fidelMon.getUrlFoto());
        assertEquals(especie.getCantidadBichos(), fidelMon.getCantidadBichos());
    }




}
