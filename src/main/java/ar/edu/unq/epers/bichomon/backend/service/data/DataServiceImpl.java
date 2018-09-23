package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.model.TipoBicho;

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

        nuevaEspecie("Rojomon", TipoBicho.FUEGO,180, 75, 100, "/image/rojomon.jpg");
        nuevaEspecie("Amarillomon", TipoBicho.ELECTRICIDAD,170, 69, 300, "/image/amarillomon.png");
        nuevaEspecie("Verdemon", TipoBicho.PLANTA,150, 55 , 5000, "/image/verdemon.png");
        nuevaEspecie("Tierramon", TipoBicho.TIERRA,1050, 99 , 5000, "/image/tierramon.png");
        nuevaEspecie("Fantasmon", TipoBicho.AIRE,1900, 0 , 400, "/image/fantasmon.png");
        nuevaEspecie("Vanpiron", TipoBicho.AIRE,3000, 150 , 4000, "/image/vampiromon.png");
        nuevaEspecie("Fortmon", TipoBicho.CHOCOLATE,1600, 110 , 100, "/image/fortmon.png");
        nuevaEspecie("Dientemon", TipoBicho.AGUA,60, 3 , 80, "/image/dientmon.png");


    }

    private void nuevaEspecie(String nombre, TipoBicho tipo, Integer altura, Integer peso, Integer energia, String foto) {
        Especie especie = new Especie();
        especie.setNombre(nombre);
        especie.setTipo(tipo);
        especie.setAltura(altura);
        especie.setPeso(peso);
        especie.setEnergiaIncial(energia);
        especie.setUrlFoto(foto);
        especie.getNombre();
        this.dao.guardar(especie);
    }

}
