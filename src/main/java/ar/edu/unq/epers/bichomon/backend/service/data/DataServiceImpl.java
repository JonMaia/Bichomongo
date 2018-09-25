package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.dao.NivelDao;
import ar.edu.unq.epers.bichomon.backend.model.*;

import java.util.List;

public class DataServiceImpl implements DataService {

    private EspecieDao especieDao;
    private NivelDao nivelDao;

    public DataServiceImpl(EspecieDao especieDao,NivelDao nivelDao){
        this.especieDao = especieDao;
        this.nivelDao = nivelDao;
    }

    @Override
    public void eliminarDatos() {
        this.especieDao.eliminarEspecies();
    }

    @Override
    public void crearSetDatosIniciales() {

        crearSetEspeciesIniciales();

        nuevoNivel(1,99,2);
        nuevoNivel(100,399,3);
        nuevoNivel(400,999,4);
        nuevoNivel(1000,1999,5);
        nuevoNivel(2000,2999,6);

       // Ubicacion ciudadLuz = nuevaUbicacion("ciudadLuz");

    }
    @Override
    public void crearSetEspeciesIniciales() {

        Especie rojomon = nuevaEspecie("Rojomon", TipoBicho.FUEGO,180, 75, 100, "/image/rojomon.jpg");
        Especie amarillomon = nuevaEspecie("Amarillomon", TipoBicho.ELECTRICIDAD,170, 69, 300, "/image/amarillomon.png");
        Especie verdemon = nuevaEspecie("Verdemon", TipoBicho.PLANTA,150, 55 , 5000, "/image/verdemon.png");
        Especie tierramon = nuevaEspecie("Tierramon", TipoBicho.TIERRA,1050, 99 , 5000, "/image/tierramon.png");
        Especie fantasmon = nuevaEspecie("Fantasmon", TipoBicho.AIRE,1900, 0 , 400, "/image/fantasmon.png");
        Especie vanpiron = nuevaEspecie("Vanpiron", TipoBicho.AIRE,3000, 150 , 4000, "/image/vampiromon.png");
        Especie fortmon = nuevaEspecie("Fortmon", TipoBicho.CHOCOLATE,1600, 110 , 100, "/image/fortmon.png");
        Especie dientemon = nuevaEspecie("Dientemon", TipoBicho.AGUA,60, 3 , 80, "/image/dientmon.png");
    }

    private void nuevoEntrenador(String nombre, Ubicacion ubicacion, List<Bicho> bichomones, Integer experiencia){
        Entrenador entrenador = new Entrenador();
        entrenador.setNombre(nombre);
        entrenador.setUbicacion(ubicacion);
        entrenador.setBichomones(bichomones);
        entrenador.setExperiencia(experiencia);

    }

    private Bicho nuevoBicho(Especie especie,Integer edad,Integer energia){
        Bicho bicho = new Bicho(especie);
        bicho.setEdad(edad);
        bicho.setEnergia(energia);
        return bicho;

    }

    private Ubicacion nuevaUbicacion(String nombre){
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setNombre(nombre);
        return ubicacion;
    }

    private void nuevoNivel(Integer expMinima,Integer expMaxima, Integer maximoDeBichos) {
        Nivel nivel = new Nivel();
        nivel.setExpMinima(expMinima);
        nivel.setExpMaxima(expMaxima);
        nivel.setMaximoDeBichos(maximoDeBichos);

        this.nivelDao.guardar(nivel);
    }

    private Especie nuevaEspecie(String nombre, TipoBicho tipo, Integer altura, Integer peso, Integer energia, String foto) {
        Especie especie = new Especie(nombre, tipo, altura, peso, energia, foto);
        this.especieDao.guardar(especie);
        return especie;
    }

}
