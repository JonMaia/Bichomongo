package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;

public class DataServiceImpl implements DataService {

    private EspecieDAO dao;

    public DataServiceImpl(EspecieDAO dao){
        this.dao = dao;
    }

    @Override
    public void eliminarDatos() {
        this.dao.eliminarEspecies();
    }

    @Override
    public void crearSetDatosIniciales() {
        Especie red = new Especie();
        red.setNombre("Rojomon");
        red.setTipo(TipoBicho.FUEGO);
        red.setAltura(180);
        red.setPeso(75);
        red.setEnergiaIncial(100);
        red.setUrlFoto("/image/rojomon.jpg");
        red.getNombre();
        this.dao.guardar(red);

        Especie amarillo = new Especie();
        amarillo.setNombre("Amarillomon");
        amarillo.setTipo(TipoBicho.ELECTRICIDAD);
        amarillo.setAltura(170);
        amarillo.setPeso(69);
        amarillo.setEnergiaIncial(300);
        amarillo.setUrlFoto("/image/amarillomon.png");
        amarillo.getNombre();
        this.dao.guardar(amarillo);

        Especie green = new Especie();
        green.setNombre("Verdemon");
        green.setTipo(TipoBicho.PLANTA);
        green.setAltura(150);
        green.setPeso(55);
        green.setEnergiaIncial(5000);
        green.setUrlFoto("/image/verdemon.jpg");
        green.getNombre();
        this.dao.guardar(green);

        Especie turronmon = new Especie();
        turronmon.setNombre("Tierramon");
        turronmon.setTipo(TipoBicho.TIERRA);
        turronmon.setAltura(1050);
        turronmon.setPeso(99);
        turronmon.setEnergiaIncial(5000);
        turronmon.setUrlFoto("/image/tierramon.jpg");
        turronmon.getNombre();
        this.dao.guardar(turronmon);

        Especie fantasmon = new Especie();
        fantasmon.setNombre("Fantasmon");
        fantasmon.setTipo(TipoBicho.AIRE);
        fantasmon.setAltura(1050);
        fantasmon.setPeso(99);
        fantasmon.setEnergiaIncial(5000);
        fantasmon.setUrlFoto("/image/fantasmon.jpg");
        fantasmon.getNombre();
        this.dao.guardar(fantasmon);

        Especie vampiron = new Especie();
        vampiron.setNombre("Vanpiron");
        vampiron.setTipo(TipoBicho.AIRE);
        vampiron.setAltura(1050);
        vampiron.setPeso(99);
        vampiron.setEnergiaIncial(5000);
        vampiron.setUrlFoto("/image/vampiromon.jpg");
        vampiron.getNombre();
        this.dao.guardar(vampiron);

        Especie fortmon = new Especie();
        fortmon.setNombre("Fortmon");
        fortmon.setTipo(TipoBicho.CHOCOLATE);
        fortmon.setAltura(1050);
        fortmon.setPeso(99);
        fortmon.setEnergiaIncial(5000);
        fortmon.setUrlFoto("/image/fortmon.png");
        fortmon.getNombre();
        this.dao.guardar(fortmon);

        Especie dientemon = new Especie();
        dientemon.setNombre("Dientemon");
        dientemon.setTipo(TipoBicho.AGUA);
        dientemon.setAltura(1050);
        dientemon.setPeso(99);
        dientemon.setEnergiaIncial(5000);
        dientemon.setUrlFoto("/image/dientmon.jpg");
        dientemon.getNombre();
        this.dao.guardar(dientemon);
    }

}
