package ar.edu.unq.epers.bichomon.backend.dao.impl.JDBCImple;

import ar.edu.unq.epers.bichomon.backend.dao.EspecieDao;
import ar.edu.unq.epers.bichomon.backend.model.Especie;
import ar.edu.unq.epers.bichomon.backend.model.TipoBicho;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCEspecieDAO implements EspecieDao {

    public JDBCConection conection = new JDBCConection();


    @Override
    public Especie getById(Integer id) {
        //TODO: implementar
        throw new RuntimeException("Pendiente implementacion");
    }

	@Override
	public void guardar(Especie especie) {
        conection.executeWithConnection(conn -> {
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

	public Boolean guardarValidado(Especie especie) {
		if (recuperar(especie.getNombre()) == null) {
			return conection.executeWithConnection(conn -> {
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

				boolean result = (ps.getUpdateCount() == 1);
				ps.close();
				return result;
			});
		}else{
			return false;
		}
	}

	@Override
	public void actualizar(Especie especie) {
        conection.executeWithConnection( conn -> {
			PreparedStatement ps = conn.prepareStatement("UPDATE bichomongo.especie SET nombre = ?,peso = ?,altura = ?,tipo = ?,energia_inicial = ?,url_foto = ?,cantidad_bichos = ? WHERE id = ? ");
			ps.setString(1, especie.getNombre());
			ps.setInt(2, especie.getPeso());
			ps.setInt(3, especie.getAltura());
			ps.setString(4, especie.getTipo().name());
			ps.setInt(5, especie.getEnergiaInicial());
			ps.setString(6, especie.getUrlFoto());
			ps.setInt(7, especie.getCantidadBichos());
			ps.setInt(8, especie.getId());

			int n = ps.executeUpdate();

			if (n>0) {
				//Mensaje para verificar la actualizacion exitosa en la base de datos. Se deja comentado para utilizarse en caso de ser necesario
				//JOptionPane.showMessageDialog(null, "Los datos se actualizaron sastifactoriamente");
			}
			ps.close();
			return null;
		});
	}

    @Override
    public void eliminar(Especie object) {
        //TODO: implementar
        throw new RuntimeException("Pendiente implementacion");
    }


    @Override
	public Especie recuperar(String nombreEspecie) {
		return conection.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement("SELECT id,nombre,peso,altura,tipo,url_foto,energia_inicial,cantidad_bichos FROM especie WHERE nombre = ?");
			ps.setString(1, nombreEspecie);

			ResultSet resultSet = ps.executeQuery();

			Especie especie = null;
			while (resultSet.next()) {
				//si especie no es null significa que el while dio mas de una vuelta, eso
				//suele pasar cuando el resultado (resultset) tiene mas de un elemento.
				if (especie != null) {
					throw new RuntimeException("Existe mas de una especie con el nombre " + nombreEspecie);
				}

				especie = especieFromResultSet(resultSet);
			}

			ps.close();
			return especie;
		});
	}


	@Override
	public List<Especie> recuperarTodos() {
		return conection.executeWithConnection(conn -> {
			List<Especie> especies = new ArrayList<Especie>();

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM especie ORDER BY nombre ASC");
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				especies.add(especieFromResultSet(resultSet));
			}
			ps.close();
			return especies;
		});
	}

	@Override
	public List<Especie> getPopulares() {
		return null;
	}

	@Override
	public List<Especie> getImpopulares() {
		return null;
	}

	@Override
	public Especie getEspecieLider() {
		return null;
	}

	@Override
	public void eliminarEspecies() {
        conection.executeWithConnection(conn -> {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM especie");
			ps.executeUpdate();
			ps.close();
			return null;
		});
	}

	/**
	 * Recibe un ResultSet y devuelve una especie en base a los datos que recibe
	 **/
	private Especie especieFromResultSet(ResultSet resultSet) throws SQLException {
		Especie especie = new Especie(resultSet.getInt("id"),resultSet.getString("nombre"), TipoBicho.valueOf(resultSet.getString("tipo")));
		especie.setAltura(resultSet.getInt("altura"));
		especie.setPeso(resultSet.getInt("peso"));
		especie.setUrlFoto(resultSet.getString("url_foto"));
		especie.setEnergiaIncial(resultSet.getInt("energia_inicial"));
		especie.setCantidadBichos(resultSet.getInt("cantidad_bichos"));
		especie.setUrlFoto(resultSet.getString("url_foto"));

		return especie;
	}
}
