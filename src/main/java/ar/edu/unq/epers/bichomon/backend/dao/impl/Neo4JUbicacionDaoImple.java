package ar.edu.unq.epers.bichomon.backend.dao.impl;

import ar.edu.unq.epers.bichomon.backend.dao.Neo4JUbicacionDao;
import ar.edu.unq.epers.bichomon.backend.model.Dojo;
import ar.edu.unq.epers.bichomon.backend.model.Ubicacion;
import org.neo4j.driver.v1.*;

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
    public void conectar(String ubicacion1, String ubicacion2, String tipoCamino) {
        Session session = this.driver.session();

        try {
            String query = "MATCH (u1:Ubicacion {nombre: {ubicacion1}}), (u2:Ubicacion {nombre: {ubicacion2}}) " +
                    "CREATE (u1)-[r: "+ tipoCamino +"]->(u2)";
            session.run(query, Values.parameters("ubicacion1", ubicacion1,"ubicacion2", ubicacion2));
        } finally {
            session.close();
        }
    }
}
