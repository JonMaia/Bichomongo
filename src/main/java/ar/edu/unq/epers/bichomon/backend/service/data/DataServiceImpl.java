package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.dao.BichoDao;
import ar.edu.unq.epers.bichomon.backend.dao.EntrenadorDao;
import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.dao.NivelDao;
import ar.edu.unq.epers.bichomon.backend.dao.impl.BaseHibernateDAO;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateBichoDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateEspecieDaoImple;
import ar.edu.unq.epers.bichomon.backend.dao.impl.HibernateNivelDaoImple;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.condicion.*;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class DataServiceImpl implements DataService {

    private EspecieDao especieDao;
    private NivelDao nivelDao;
    private BichoDao bichoDao;
    private EntrenadorDao entrenadorDao;

    public DataServiceImpl(EspecieDao especieDao,NivelDao nivelDao){
        this.especieDao = especieDao;
        this.nivelDao = nivelDao;
    }


    public DataServiceImpl(){
        this.especieDao = new HibernateEspecieDaoImple();
        this.nivelDao = new HibernateNivelDaoImple();
        this.bichoDao = new HibernateBichoDaoImple();
    }

    public DataServiceImpl(EspecieDao especieDao, EntrenadorDao entrenadorDao, BichoDao bichoDao, NivelDao nivelDao) {
        this.entrenadorDao = entrenadorDao;
        this.especieDao = especieDao;
        this.nivelDao = nivelDao;
        this.bichoDao = bichoDao;
    }

    @Override
    public void eliminarDatos() {

        Runner.runInSession(() -> {
            Runner.getCurrentSession().createSQLQuery("alter table Bicho drop foreign key FK3w2054f6jgsk7l2s0dxskcmlj;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Bicho drop foreign key FK8fh9r2jp4j2gfphs47b6lp6fs;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Combate drop foreign key FKjful76fpto5l45mcr9y93d5dp;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Combate drop foreign key FKbe5tpf71j41k7ab803ka2fj4i;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Combate drop foreign key FKtpvr9spmp6vt4r20q7oqgwjue;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Combate_ataquesCampeon drop foreign key FK14nr2r47svcqian8u54xtbbgs;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Combate_ataquesRetador drop foreign key FKqjbowbcah5ew0r1y0v2be07gj;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Entrenador drop foreign key FK7n671vubnce8r6qipy7iu0qdv;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Entrenador drop foreign key FKg4ns64n3ylb1nk2g8ik9xg72v;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Entrenador_Bicho drop foreign key FKj8v53hkifx709fe3dr04j7um9;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Entrenador_Bicho drop foreign key FK5omf7yeuggrx4y50slh2c5iul;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Especie drop foreign key FKqcwkj83fk834hifybaat7qxve;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Especie_Condicion drop foreign key FKiwh03nawbboyd81uq18fstc4x;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Especie_Condicion drop foreign key FKl8g64xhx5mmpyeyrbqel00j9u;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Nivel drop foreign key FKergyr9mwnuaopox25iugc0w21;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Ubicacion drop foreign key FK9rc8es5lw8mg4c2lcmjynwcb9;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Ubicacion_Bicho drop foreign key FK4fgru7e0ngvibdank7vha13mp;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Ubicacion_Bicho drop foreign key FKmpg3xx372ocb3oxjpx76gh2w9;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Ubicacion_Entrenador drop foreign key FKt0kgpp5871f1jh099k9sb023e;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("alter table Ubicacion_Entrenador drop foreign key FKlq0jcejkgh34owvblikl7ahxn;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Bicho;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Busqueda;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Combate;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Combate_ataquesCampeon;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Combate_ataquesRetador;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Condicion;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Entrenador;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Entrenador_Bicho;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Especie;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Especie_Condicion;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Experiencia;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists hibernate_sequence;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Nivel;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Ubicacion;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Ubicacion_Bicho;").executeUpdate();
            Runner.getCurrentSession().createSQLQuery("drop table if exists Ubicacion_Entrenador;").executeUpdate();
            return null;
        });
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
        Entrenador entrenador = new Entrenador(nombre,ubicacion,nivel1, new Acciones(10,10,5));
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
        ubicacion.setNumero(nombre);
        return ubicacion;
    }
*/


    private Especie nuevaEspecie(String nombre, TipoBicho tipo, Integer altura, Integer peso, Integer energia, String foto) {
        Especie especie = new Especie(nombre, tipo, altura, peso, energia, foto);
        this.especieDao.guardar(especie);
        return especie;
    }



    public Especie crearEspecieBase(){
        return Runner.runInSession(() -> {
            Especie base =  new Especie("Base", TipoBicho.FUEGO,180, 75, 100, "/image/rojomon.jpg");
            this.especieDao.guardar(base);
            return base;
        });
    }

    public Especie crearEspecieBaseConEvolucion(){
        return Runner.runInSession(() -> {
            Especie base = crearEspecieBase();
            Especie evolucion = new Especie("Evolucion", TipoBicho.FUEGO,180, 75, 100, "/image/rojomon.jpg");
            base.setEvolucion(evolucion);
            this.especieDao.guardar(evolucion);
            this.especieDao.guardar(base);
            return base;
        });
    }

    public Especie crearEspecieConEvolucionConCondicionDeEnergia0(){
        return Runner.runInSession(() -> {
            Especie base = crearEspecieBaseConEvolucion();
            Condicion condicionEnergia = new CondicionEnergia(0);
            List<Condicion> condiciones = new ArrayList<Condicion>();
            condiciones.add(condicionEnergia);
            base.setCondicionDeEvolucion(condiciones);

            this.especieDao.guardar(base);
            return base;
        });
    }

    public Especie crearEspecieConEvolucionConCondicionDeVictorias0(){
        return Runner.runInSession(() -> {
            Especie base = crearEspecieBaseConEvolucion();
            Condicion condicionVictorias = new CondicionVictorias(0);
            List<Condicion> condiciones = new ArrayList<Condicion>();
            condiciones.add(condicionVictorias);
            base.setCondicionDeEvolucion(condiciones);

            this.especieDao.guardar(base);
            return base;
        });
    }

    public Especie crearEspecieConEvolucionConCondicionDeEdad0(){
        return Runner.runInSession(() -> {
            Especie base = crearEspecieBaseConEvolucion();
            Condicion condicionEdad = new CondicionEdad(0);
            List<Condicion> condiciones = new ArrayList<Condicion>();
            condiciones.add(condicionEdad);
            base.setCondicionDeEvolucion(condiciones);

            this.especieDao.guardar(base);
            return base;
        });
    }

    public Especie crearEspecieConEvolucionConCondicionDeNivel0(){
        return Runner.runInSession(() -> {
            Especie base = crearEspecieBaseConEvolucion();
            Condicion condicionNivel = new CondicionNivel(0);
            List<Condicion> condiciones = new ArrayList<Condicion>();
            condiciones.add(condicionNivel);
            base.setCondicionDeEvolucion(condiciones);

            this.especieDao.guardar(base);
            return base;
        });
    }

    public Entrenador crearEntrenedor(){
        Entrenador entrenador = new Entrenador();
        entrenador.setNombre("Ash");
        this.entrenadorDao.guardar(entrenador);
        return entrenador;
    }

    public Bicho crearBichoConEntrenadorYEspecieConEvolucionSinCondicionDeEvolucion(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieBaseConEvolucion();
            Bicho bichoBase = new Bicho(especieBase);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenedor();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    public Bicho crearBichoConEntrenadorYEspecieSinEvolucion(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieBase();
            Bicho bichoBase = new Bicho(especieBase);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenedor();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    public Bicho crearBichoConEnergia10ConEntrenadorYEspecieConEvolucionConCondicionDeEnergia0(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeEnergia0();
            Bicho bichoBase = new Bicho(especieBase);
            bichoBase.setEnergia(10);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenedor();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }


    public Bicho crearBichoConVictorias10ConEntrenadorYEspecieConEvolucionConCondicionDeVictorias0(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeVictorias0();
            Bicho bichoBase = new Bicho(especieBase);
            bichoBase.setVictorias(10);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenedor();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    public Bicho crearBichoConEdad10ConEntrenadorYEspecieConEvolucionConCondicionDeEdad0(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeEdad0();
            Bicho bichoBase = new Bicho(especieBase);
            bichoBase.setEdad(10);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenedor();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    public Bicho crearBichoConNivel10ConEntrenadorYEspecieConEvolucionConCondicionDeNivel0(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeNivel0();
            Bicho bichoBase = new Bicho(especieBase);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Nivel nivel = new Nivel();
            nivel.setNumero(10);
            Entrenador entrenador = crearEntrenedor();
            this.entrenadorDao.guardar(entrenador);
            entrenador.setNivel(nivel);
            bichoBase.setEntrenador(entrenador);

            this.entrenadorDao.guardar(entrenador);
            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }


}
