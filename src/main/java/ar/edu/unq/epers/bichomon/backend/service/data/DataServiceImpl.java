package ar.edu.unq.epers.bichomon.backend.service.data;

import ar.edu.unq.epers.bichomon.backend.dao.*;
import ar.edu.unq.epers.bichomon.backend.dao.impl.*;
import ar.edu.unq.epers.bichomon.backend.model.*;
import ar.edu.unq.epers.bichomon.backend.model.condicion.*;
import ar.edu.unq.epers.bichomon.backend.service.runner.Runner;
import ar.edu.unq.epers.bichomon.backend.service.runner.SessionFactoryProvider;

import java.util.ArrayList;
import java.util.List;

public class DataServiceImpl implements DataService {

    private EspecieDao especieDao;
    private NivelDao nivelDao;
    private BichoDao bichoDao;
    private EntrenadorDao entrenadorDao;
    private DojoDao dojoDao = new HibernateDojoDaoImple();

    public DataServiceImpl(EspecieDao especieDao,NivelDao nivelDao){
        this.especieDao = especieDao;
        this.nivelDao = nivelDao;
    }


    public DataServiceImpl(){
        this.especieDao = new HibernateEspecieDaoImple();
        this.nivelDao = new HibernateNivelDaoImple();
        this.bichoDao = new HibernateBichoDaoImple();
        this.entrenadorDao = new HibernateEntrenadorDaoImple();
    }

    public DataServiceImpl(EspecieDao especieDao, EntrenadorDao entrenadorDao, BichoDao bichoDao, NivelDao nivelDao) {
        this.entrenadorDao = entrenadorDao;
        this.especieDao = especieDao;
        this.nivelDao = nivelDao;
        this.bichoDao = bichoDao;
    }

    @Override
    public void eliminarDatos() {
        SessionFactoryProvider.destroy();

    }

    @Override
    public void crearSetDatosIniciales() {

        crearSetEspeciesIniciales();
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



    private Especie nuevaEspecie(String nombre, TipoBicho tipo, Integer altura, Integer peso, Integer energia, String foto) {
        Especie especie = new Especie(nombre, tipo, altura, peso, energia, foto);
        this.especieDao.guardar(especie);
        return especie;
    }



    public Especie crearEspecieBase(){
        return Runner.runInSession(() -> {
            Especie base = especieDao.recuperar("Base");
            if(base != null)
                base.setEvolucion(null);
            else{
                base = new Especie("Base", TipoBicho.FUEGO, 180, 75, 100, "/image/rojomon.jpg");
                this.especieDao.guardar(base);
            }
                return base;
        });
    }

    public Especie crearEspecieBaseConEvolucion(){
        return Runner.runInSession(() -> {

            Especie base = especieDao.recuperar("Base");
            if(base == null)
                base = crearEspecieBase();

            List<Condicion> condiciones = new ArrayList<Condicion>();
            base.setCondicionDeEvolucion(condiciones);


            Especie evolucion = especieDao.recuperar("Evolucion");
            if(evolucion == null)
                evolucion = new Especie("Evolucion", TipoBicho.FUEGO,180, 75, 100, "/image/rojomon.jpg");

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

    @Override
    public Entrenador crearEntrenador() {
        Entrenador entrenador = entrenadorDao.getById("Ash");
        if(entrenador == null){
            entrenador =  new Entrenador();
            entrenador.setNombre("Ash");
            entrenador.setAccion(new Acciones(0,0,0));
            entrenador.setNivel(crearNivel(1));
            this.entrenadorDao.guardar(entrenador);
        }
        return entrenador;
    }

    private Nivel crearNivel(Integer numero) {
        Nivel nivel2 = new Nivel(numero+1,10, 1,null,0.5);
        Nivel nivel1 = new Nivel(numero,10, 1,nivel2,0.5);
        return nivel1;
    }

    public Bicho crearBichoConEntrenadorYEspecieConEvolucionSinCondicionDeEvolucion(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieBaseConEvolucion();
            Bicho bichoBase = especieBase.crearBicho();
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenador();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    public Bicho crearBichoConEntrenadorYEspecieSinEvolucion(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieBase();
            Bicho bichoBase = especieBase.crearBicho();
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenador();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    public Bicho crearBichoConEnergia10ConEntrenadorYEspecieConEvolucionConCondicionDeEnergia0(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeEnergia0();
            Bicho bichoBase = especieBase.crearBicho();
            bichoBase.setEnergia(10);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenador();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }


    public Bicho crearBichoConVictorias10ConEntrenadorYEspecieConEvolucionConCondicionDeVictorias0(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeVictorias0();
            Bicho bichoBase = especieBase.crearBicho();
            bichoBase.setVictorias(10);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenador();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    public Bicho crearBichoConEdad10ConEntrenadorYEspecieConEvolucionConCondicionDeEdad0(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeEdad0();
            Bicho bichoBase = especieBase.crearBicho();
            bichoBase.setEdad(10);
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Entrenador entrenador = crearEntrenador();
            bichoBase.setEntrenador(entrenador);
            entrenador.setBichomones(bichos);

            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    public Bicho crearBichoConNivel10ConEntrenadorYEspecieConEvolucionConCondicionDeNivel0(){
        return Runner.runInSession(() -> {
            Especie especieBase = this.crearEspecieConEvolucionConCondicionDeNivel0();
            Bicho bichoBase = especieBase.crearBicho();
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(bichoBase);
            Nivel nivel = crearNivel(10);
            Entrenador entrenador = crearEntrenador();
            entrenador.setNivel(nivel);
            bichoBase.setEntrenador(entrenador);

            this.entrenadorDao.guardar(entrenador);
            this.bichoDao.guardar(bichoBase);
            return bichoBase;
        });
    }

    public List<Bicho> crear2BichosPara10EspeciesYUnBichoPara2EspeciesConEntrenador(){
        return Runner.runInSession(() -> {
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon1","Entrenador1"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon1","Entrenador2"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon2","Entrenador3"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon2","Entrenador4"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon3","Entrenador5"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon3","Entrenador6"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon4","Entrenador7"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon4","Entrenador8"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon5","Entrenador9"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon5","Entrenador10"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon6","Entrenador11"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon6","Entrenador12"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon7","Entrenador13"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon7","Entrenador14"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon8","Entrenador15"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon8","Entrenador16"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon9","Entrenador17"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon9","Entrenador18"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon10","Entrenador19"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon10","Entrenador20"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon11","Entrenador21"));
            bichos.add(this.crearBichoDeEspecieYDeEntrenador("Fafamon12","Entrenador22"));

            return bichos;
        });
    }

    @Override
    public Bicho crearBchoConEsspecieSinEntrenador() {
        return Runner.runInSession(() -> {
            Especie especie = crearEspecie("Especie");
            Bicho bicho = especie.crearBicho();
            bichoDao.guardar(bicho);
            return bicho;
        });
    }
    @Override
    public Bicho crearBichoConEntrenadorYEspecieYEnergia200EnDojo(){
        return Runner.runInSession(() -> {
            Dojo dojo = new Dojo();

            Entrenador entrenador = crearEntrenador("Pepito");
            Especie especie = crearEspecie("Especie");
            Especie especieCampeon = crearEspecie("Especie");
            especie.setEnergiaIncial(200);
            especieCampeon.setEnergiaIncial(20);
            Bicho retador = especie.crearBicho();
            Bicho campeon = especieCampeon.crearBicho();
            retador.setEntrenador(entrenador);
            dojo.setCampeon(campeon);
            entrenador.moverA(dojo);
            bichoDao.guardar(retador);
            bichoDao.guardar(campeon);
            entrenadorDao.guardar(entrenador);
            dojoDao.guardar(dojo);
            return retador;
        });
    }

    @Override
    public List<Bicho> crear2BichosPara10EspeciesYUnBichoPara2EspeciesSinEntrenador() {
        return Runner.runInSession(() -> {
            List<Bicho> bichos = new ArrayList<Bicho>();
            bichos.add(this.crearBichoDeEspecie("Fafamon1"));
            bichos.add(this.crearBichoDeEspecie("Fafamon1"));
            bichos.add(this.crearBichoDeEspecie("Fafamon2"));
            bichos.add(this.crearBichoDeEspecie("Fafamon2"));
            bichos.add(this.crearBichoDeEspecie("Fafamon3"));
            bichos.add(this.crearBichoDeEspecie("Fafamon3"));
            bichos.add(this.crearBichoDeEspecie("Fafamon4"));
            bichos.add(this.crearBichoDeEspecie("Fafamon4"));
            bichos.add(this.crearBichoDeEspecie("Fafamon5"));
            bichos.add(this.crearBichoDeEspecie("Fafamon5"));
            bichos.add(this.crearBichoDeEspecie("Fafamon6"));
            bichos.add(this.crearBichoDeEspecie("Fafamon6"));
            bichos.add(this.crearBichoDeEspecie("Fafamon7"));
            bichos.add(this.crearBichoDeEspecie("Fafamon7"));
            bichos.add(this.crearBichoDeEspecie("Fafamon8"));
            bichos.add(this.crearBichoDeEspecie("Fafamon8"));
            bichos.add(this.crearBichoDeEspecie("Fafamon9"));
            bichos.add(this.crearBichoDeEspecie("Fafamon9"));
            bichos.add(this.crearBichoDeEspecie("Fafamon10"));
            bichos.add(this.crearBichoDeEspecie("Fafamon10"));
            bichos.add(this.crearBichoDeEspecie("Fafamon11"));
            bichos.add(this.crearBichoDeEspecie("Fafamon12"));

            return bichos;
        });
    }

    @Override
    public Dojo crearDojo() {
        Dojo dojo =  this.dojoDao.getById("DOJO");
        if(dojo == null) {
            dojo = new Dojo();
            dojo.setNombre("DOJO");
        }
        this.dojoDao.guardar(dojo);
        return dojo;
    }

    @Override
    public Pueblo crearPuebloConProbabilidadExito100(){
        Pueblo puebloPaleta = new Pueblo();

        Especie weedle = new Especie();
        List<ProbabilidadDeOcurrencia> probabilidades = new ArrayList<>();
        ProbabilidadDeOcurrencia probabilidadWeedle = new ProbabilidadDeOcurrencia(weedle,100);
        probabilidades.add(probabilidadWeedle);
        puebloPaleta.setEspeciesEnPueblo(probabilidades);

        return puebloPaleta;
    }
    @Override
    public Pueblo crearPuebloConProbabilidadExito0(){
        Pueblo puebloPaleta = new Pueblo();

        Especie weedle = new Especie();
        List<ProbabilidadDeOcurrencia> probabilidades = new ArrayList<>();
        ProbabilidadDeOcurrencia probabilidadWeedle = new ProbabilidadDeOcurrencia(weedle,0);
        probabilidades.add(probabilidadWeedle);
        puebloPaleta.setEspeciesEnPueblo(probabilidades);

        return puebloPaleta;
    }

    public Bicho crearBichoDeEspecieYDeEntrenador(String nombreEspecie, String nombreEntrenador) {

        Especie especie = this.especieDao.recuperar(nombreEspecie);
        if(especie == null)
            especie = this.crearEspecie(nombreEspecie);

        Bicho bicho = especie.crearBicho();
        List<Bicho> bichos = new ArrayList<Bicho>();
        bichos.add(bicho);
        Entrenador entrenador = crearEntrenador(nombreEntrenador);
        bicho.setEntrenador(entrenador);
        entrenador.setBichomones(bichos);

        this.bichoDao.guardar(bicho);
        return bicho;
    }

    private Bicho crearBichoDeEspecie(String nombreEspecie) {

        Especie especie = this.especieDao.recuperar(nombreEspecie);
        if(especie == null)
            especie = this.crearEspecie(nombreEspecie);

        Bicho bicho = especie.crearBicho();
        this.bichoDao.guardar(bicho);
        return bicho;
    }

    private Especie crearEspecie(String nombreEspecie) {
        return Runner.runInSession(() -> {
            Especie base =  new Especie(nombreEspecie, TipoBicho.FUEGO,180, 75, 100, "/image/rojomon.jpg");
            this.especieDao.guardar(base);
            return base;
        });
    }

    private Entrenador crearEntrenador(String nombreEntrenador) {
        return Runner.runInSession(() -> {
            Entrenador entrenador = new Entrenador();
            entrenador.setNombre(nombreEntrenador);
            this.entrenadorDao.guardar(entrenador);
            return entrenador;
        });
    }

}
