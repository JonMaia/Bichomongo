package ar.edu.unq.service;

import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateImple.HibernateEntrenadorDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.Entrenador;
import ar.edu.unq.epers.bichomon.backend.model.Nivel;
import ar.edu.unq.epers.bichomon.backend.service.data.DataService;
import ar.edu.unq.epers.bichomon.backend.service.data.DataServiceImpl;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ExperienciaServiceTest {

    private DataService dataService = new DataServiceImpl();

    @Test
    public void un_entrenador_nivel_1_va_ganando_experiencia_y_subiendo_de_nivel_hasta_llegar_al_nivel_5(){
        Nivel nivel5 = new Nivel(5,3000,25,new Nivel(),2.5);
        Nivel nivel4 = new Nivel(4,2000,20,nivel5,2.4);
        Nivel nivel3 = new Nivel(3,1000,15,nivel4,2.3);
        Nivel nivel2 = new Nivel(2,400,10,nivel3,2.2);
        Nivel nivel = new Nivel(1,99,5,nivel2,2.1);
        Entrenador ash = this.dataService.crearEntrenador("ash");
        ash.setNivel(nivel);
        this.dataService.actualizarExperienciaEntrenador(ash,100);
        assertEquals(ash.getNivel().getNumero(),nivel2.getNumero());
        this.dataService.actualizarExperienciaEntrenador(ash,301);
        assertEquals(ash.getNivel().getNumero(),nivel3.getNumero());
        this.dataService.actualizarExperienciaEntrenador(ash, 600);
        assertEquals(ash.getNivel().getNumero(),nivel4.getNumero());
        this.dataService.actualizarExperienciaEntrenador(ash, 1000);
        assertEquals(ash.getNivel().getNumero(),nivel5.getNumero());
        }
}
