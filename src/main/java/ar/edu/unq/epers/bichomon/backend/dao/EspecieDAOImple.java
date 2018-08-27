package ar.edu.unq.epers.bichomon.backend.dao;

import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;

import java.sql.*;

import java.util.List;

public class EspecieDAOImple implements EspecieDAO {


	@Override
	public void guardar(Especie especie) {
		this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO bichomongo.especie (nombre,peso,altura,tipo,energia_inicial,url_foto,cantidad_bichos)" +
					"VALUES (?,?,?,?,?,?,?);");
			ps.setString(1, especie.getNombre());
			ps.setInt(2, especie.getPeso());
			ps.setInt(3, especie.getAltura());
			ps.setString(4, especie.getTipo().name());
            ps.setInt(5, especie.getEnergiaInicial());
            ps.setString(6, especie.getUrlFoto());
			ps.setInt(7, especie.getCantidadBichos());
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
		return this.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement("SELECT id,nombre,peso,altura,tipo,url_foto,energia_inicial,cantidad_bichos FROM especie WHERE nombre = ?");
			ps.setString(1, nombreEspecie);

			ResultSet resultSet = ps.executeQuery();

			Especie especie = null;
			while (resultSet.next()) {
				//si especie no es null aca significa que el while dio mas de una vuelta, eso
				//suele pasar cuando el resultado (resultset) tiene mas de un elemento.
				if (especie != null) {
					throw new RuntimeException("Existe mas de una especie con el nombre " + nombreEspecie);
				}

				especie = new Especie(resultSet.getInt("id"),resultSet.getString("nombre"), TipoBicho.valueOf(resultSet.getString("tipo")));
				especie.setAltura(resultSet.getInt("altura"));
				especie.setPeso(resultSet.getInt("peso"));
				especie.setUrlFoto(resultSet.getString("url_foto"));
				especie.setEnergiaIncial(resultSet.getInt("energia_inicial"));
				especie.setCantidadBichos(resultSet.getInt("cantidad_bichos"));
				especie.setUrlFoto(resultSet.getString("url_foto"));
			}

			ps.close();
			return especie;
		});
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
				return DriverManager.getConnection("jdbc:mysql://localhost:3306/bichomongo?user=root&password=root&useSSL=false");
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
