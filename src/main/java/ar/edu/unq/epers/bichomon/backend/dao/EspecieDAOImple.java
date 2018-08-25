package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

/**
 * Esta es una implementacion mock de {@link EspecieDAO}
 * 
 */
public class EspecieDAOImple implements EspecieDAO {


	@Override
	public void guardar(Especie especie) {
		this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO bichomongo.especies (nombre,peso,altura,tipo,url_foto,cantidad_bichos)" +
					"VALUES (?,?,?,?,?,?);");
			ps.setString(1, especie.getNombre());
			ps.setInt(2, especie.getPeso());
			ps.setInt(3, especie.getAltura());
			ps.setString(4, especie.getTipo().name());
			ps.setString(5, especie.getUrlFoto());
			ps.setInt(6, especie.getCantidadBichos());
			//ojo, no estamos guardando el inventario!
			ps.execute();

			if (ps.getUpdateCount() != 1) {
				throw new RuntimeException("No se inserto la especie " + especie);
			}
			ps.close();

			return null;
		});
	}

	@Override
	public void actualizar(Especie especie) {

	}

	@Override
	public Especie recuperar(String nombreEspecie) {
		return null;
	}

	@Override
	public List<Especie> recuperarTodos() {
		return null;
	}


	/**
	 * Ejecuta un bloque de codigo contra una conexion.
	 */
	private <T> T executeWithConnection(ConnectionBlock<T> bloque) {
		Connection connection = this.openConnection();
		try {
			return bloque.executeWith(connection);
		} catch (SQLException e) {
			throw new RuntimeException("Error no esperado", e);
		} finally {
			this.closeConnection(connection);
		}
	}

		/**
		 * Establece una conexion a la url especificada
		 * @return la conexion establecida
		 */
		private Connection openConnection() {
			try {
				//La url de conexion no deberia estar harcodeada aca
				return DriverManager.getConnection("jdbc:mysql://localhost:3306/bichomongo?user=root&password=very_strong_pasword");
			} catch (SQLException e) {
				throw new RuntimeException("No se puede establecer una conexion", e);
			}
		}

		/**
		 * Cierra una conexion con la base de datos (libera los recursos utilizados por la misma)
		 * @param connection - la conexion a cerrar.
		 */
		private void closeConnection(Connection connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException("Error al cerrar la conexion", e);
			}
		}



	}
