package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.Neo4JUbicacionDao;
import ar.edu.unq.epers.bichomon.backend.model.TipoDeCamino;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;
import org.neo4j.driver.v1.*;

import java.util.List;

public class Neo4JUbicacionDaoImple implements Neo4JUbicacionDao {

    private Driver driver;

    public Neo4JUbicacionDaoImple() {
        this.driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "password" ) );
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
    public boolean existeUbicacion(Ubicacion ubicacion) {
        Session session = this.driver.session();

        try {
            //MATCH (u:Ubicacion) return u
            String query = "MATCH (u:Ubicacion {nombre: {elNombre}}) " +
                    "RETURN u";
            StatementResult result = session.run(query, Values.parameters("elNombre", ubicacion.getNombre()));

            return result.hasNext();
        } finally {
            session.close();
        }
    }


    @Override
    public boolean existeRelacion(TipoDeCamino tipoCamino) {
        Session session = this.driver.session();

        try {
            //    MATCH (u)-[r:TERRESTRES]->(c) RETURN r
            String query = "MATCH (u)-[r:" + tipoCamino.getTipo() + "]->(c) RETURN r";
            StatementResult result = session.run(query);

            return result.hasNext();
        } finally {
            session.close();
        }
    }

    @Override
    public void conectar(String ubicacion1, String ubicacion2, TipoDeCamino tipoCamino) {
        Session session = this.driver.session();

        try {
            String query = "MATCH (u1:Ubicacion {nombre: {ubicacion1}}), (u2:Ubicacion {nombre: {ubicacion2}}) " +
                    "CREATE (u1)-[r: "+ tipoCamino.getTipo() +"]->(u2)";
            session.run(query, Values.parameters("ubicacion1", ubicacion1,"ubicacion2", ubicacion2));
        } finally {
            session.close();
        }
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
/*
esto sirve para buscar el camino de dos nodos
    MATCH p = shortestPath((martin:Person)-[*..15]-(oliver:Person))
    WHERE martin.name = 'Martin Sheen' AND oliver.name = 'Oliver Stone'
    RETURN p

    */
}
