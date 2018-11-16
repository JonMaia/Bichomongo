package ar.edu.unq.epers.bichomon.backend.dao.impl.Neo4JImple;

import ar.edu.unq.epers.bichomon.backend.dao.Neo4JUbicacionDao;
import ar.edu.unq.epers.bichomon.backend.model.TipoDeCamino;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;
import org.neo4j.driver.v1.*;

import java.util.List;

public class Neo4JUbicacionDaoImple implements Neo4JUbicacionDao {

    private Driver driver;

    public Neo4JUbicacionDaoImple() {
        this.driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "root" ) );
       // this.driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "root" ) );
    }

    @Override
    public void eliminarDatos() {
        Session session = this.driver.session();

        try {
            String query = "MATCH (n) DETACH DELETE n";
            session.run(query);
        } finally {
            session.close();
        }
    }

    @Override
    public void create(Ubicacion ubicacion) {
        Session session = this.driver.session();

        try {
            String query = "MERGE (n:Ubicacion {nombre: {elNombre}}) ";
            session.run(query, Values.parameters("elNombre", ubicacion.getNombre()));
        } finally {
            session.close();
        }
    }

    @Override
    public boolean existeUbicacion(String ubicacion) {
        Session session = this.driver.session();

        try {
            //MATCH (u:Ubicacion) return u
            String query = "MATCH (u:Ubicacion {nombre: {elNombre}}) " +
                    "RETURN u";
            StatementResult result = session.run(query, Values.parameters("elNombre", ubicacion));

            return result.hasNext();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean existeRelacion(String nombre1, String nombre2) {
        Session session = this.driver.session();

        try {
            //    MATCH (u)-[r:TERRESTRES]->(c) RETURN r
            String query = "MATCH (u:Ubicacion {nombre: {elNombre1}})-[r]->(u2:Ubicacion {nombre: {elNombre2}}) RETURN r";

            StatementResult result = session.run(query, Values.parameters("elNombre1", nombre1,"elNombre2", nombre2));

            return result.hasNext();
        } finally {
            session.close();
        }
    }

    @Override
    public Integer getPrecioCaminoCorto(String origen, String destino) {
        Session session = this.driver.session();
        Integer costo = null;
        try{
            String query = "MATCH (:Ubicacion {nombre: {origen}})-[camino *1..]->(:Ubicacion{nombre: {destino}}) " +
                    "RETURN min(reduce(total=0,r IN camino|total + r.costo)) as costoFinal";
            Record result =  session.run(query,Values.parameters("origen",origen,
                    "destino",destino)).single();
            if( result != null && !result.get("costoFinal").isNull()) {
                costo = new Integer(result.get("costoFinal").asInt());
            }

        } finally {
            session.close();
        }
        return costo;
    }

    @Override
    public void conectar(String ubicacion1, String ubicacion2, TipoDeCamino tipoCamino) {
        Session session = this.driver.session();

        try {
            String query = "MATCH (u1:Ubicacion {nombre: {ubicacion1}}), (u2:Ubicacion {nombre: {ubicacion2}}) " +
                    "MERGE (u1)-[r:"+ tipoCamino.getTipo() +" {costo: {costo1}}]->(u2)";
            session.run(query, Values.parameters("ubicacion1", ubicacion1,"ubicacion2", ubicacion2,"costo1",tipoCamino.getCosto()));
        } finally {
            session.close();
        }
    }

    @Override
    public Integer costoCaminoMasCorto(String origen, String destino) {
        Integer costo = null;
        Session session = this.driver.session();
        try{
            String query = "MATCH (:Ubicacion{nombre:{ubicacionOrigen}})-[caminos *1.. ]->(:Ubicacion{nombre:{ubicacionDestino}}) " +
                    "RETURN reduce(costoTotal=0, c IN caminos | costoTotal + c.costo) as costo, " +
                    "       reduce(cantidadTramos=0, c IN caminos | cantidadTramos + 1) as tramos " +
                    "ORDER BY tramos, costo LIMIT 1";

            Record result =  session.run(query,Values.parameters("ubicacionOrigen",origen,
                    "ubicacionDestino",destino)).single();

            if( result != null && !result.get("costo").isNull()) {
                costo = new Integer(result.get("costo").asInt());
            }

        } finally {
            session.close();
        }

        return costo;
    }


    @Override
    public List<Record> conectados(String ubicacion, String tipoCamino) {
        Session session = this.driver.session();

        try {
            //MATCH (u:Ubicacion)-[r:TERRESTRE]->(u2:Ubicacion) RETURN u2
            String query = "MATCH (u:Ubicacion {nombre: {elNombre}})-[r:" + tipoCamino +
                    "]->(u2:Ubicacion) RETURN u2.nombre";
            StatementResult result = session.run(query, Values.parameters("elNombre", ubicacion));

            return result.list();
        } finally {
            session.close();
        }
    }

}
