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
    public boolean existe(Ubicacion ubicacion) {
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
}
