package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.dao.NivelDao;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.condicion.Condicion;
import ar.edu.unq.epers.bichomon.backend.model.condicion.CondicionEnergia;
import ar.edu.unq.epers.bichomon.backend.model.condicion.CondicionNivel;

import java.util.ArrayList;
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
        /*
        Nivel nivel5 = new Nivel("V" ,2999, 6, null);
        Nivel nivel4 = new Nivel("IV",1999,5, nivel5);
        Nivel nivel3 = new Nivel("III",999,4,nivel4);
        Nivel nivel2 = new Nivel("II",399,3, nivel3);
        Nivel nivel1 = new Nivel("I",99,2, nivel2);
        */

       // Ubicacion ciudadLuz = nuevaUbicacion("ciudadLuz");

    }
    @Override
    public void crearSetEspeciesIniciales() {

        Condicion condicionEnergia = new CondicionEnergia(0);
        List<Condicion> condiciones = new ArrayList<Condicion>();
        condiciones.add(condicionEnergia);

        Especie rojomonsote = nuevaEspecie("Rojomonsote", TipoBicho.FUEGO,180, 75, 100, "/image/rojomon.jpg");
        Especie rojomon = nuevaEspecie("Rojomon", TipoBicho.FUEGO,180, 75, 100, "/image/rojomon.jpg");
        rojomon.setEvolucion(rojomonsote);
        rojomon.setCondicionDeEvolucion(condiciones);

        Especie amarillomonsote = nuevaEspecie("Amarillomonsote", TipoBicho.ELECTRICIDAD,170, 69, 300, "/image/amarillomon.png");
        Especie amarillomon = nuevaEspecie("Amarillomon", TipoBicho.ELECTRICIDAD,170, 69, 300, "/image/amarillomon.png");
        amarillomon.setEvolucion(amarillomonsote);
        amarillomon.setCondicionDeEvolucion(new ArrayList<Condicion>());

        Especie verdemon = nuevaEspecie("Verdemon", TipoBicho.PLANTA,150, 55 , 5000, "/image/verdemon.png");
        Especie tierramon = nuevaEspecie("Tierramon", TipoBicho.TIERRA,1050, 99 , 5000, "/image/tierramon.png");
        Especie fantasmon = nuevaEspecie("Fantasmon", TipoBicho.AIRE,1900, 0 , 400, "/image/fantasmon.png");
        Especie vanpiron = nuevaEspecie("Vanpiron", TipoBicho.AIRE,3000, 150 , 4000, "/image/vampiromon.png");
        Especie fortmon = nuevaEspecie("Fortmon", TipoBicho.CHOCOLATE,1600, 110 , 100, "/image/fortmon.png");
        Especie dientemon = nuevaEspecie("Dientemon", TipoBicho.AGUA,60, 3 , 80, "/image/dientmon.png");
    }

    private void nuevoEntrenador(String nombre, Ubicacion ubicacion, List<Bicho> bichomones, Integer experiencia,Nivel nivel1){
        Entrenador entrenador = new Entrenador(nombre,ubicacion,nivel1);
        entrenador.setBichomones(bichomones);

    }

    private Bicho nuevoBicho(Especie especie,Integer edad,Integer energia){
        Bicho bicho = new Bicho(especie);
        bicho.setEdad(edad);
        bicho.setEnergia(energia);
        return bicho;

    }
/*
    private Ubicacion nuevaUbicacion(String nombre){
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setNombre(nombre);
        return ubicacion;
    }
*/


    private Especie nuevaEspecie(String nombre, TipoBicho tipo, Integer altura, Integer peso, Integer energia, String foto) {
        Especie especie = new Especie(nombre, tipo, altura, peso, energia, foto);
        this.especieDao.guardar(especie);
        return especie;
    }

}
