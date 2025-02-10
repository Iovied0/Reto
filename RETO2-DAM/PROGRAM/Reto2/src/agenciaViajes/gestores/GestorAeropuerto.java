package agenciaViajes.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import agenciaViajes.bbdd.*;
import agenciaViajes.bbdd.pojos.*;
import agenciaViajes.controlador.Controlador;

/**
 * Esta clase tiene todos los metodos relevantes a la tabla t_alumno de BBDD.
 */
public class GestorAeropuerto {

	/**
	 * Recoge un aeropuerto de la bbdd por su codigo
	 * 
	 * @param id
	 * @return
	 */
	public Aeropuerto getAeropuertoPorCodigo(String codigo) {

		Aeropuerto ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_AEROPUERTO_WHERE_CODIGO);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setString(1, codigo);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				ret = new Aeropuerto();
				ret.setCodigo(resultSet.getString("codigo"));
				Controlador controlador = Controlador.getInstanceControlador();
				Ciudad ciudad = controlador.getCiudadPorId(resultSet.getInt("id_ciudad"));
				ret.setCiudad(ciudad);

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}

	public Aeropuerto getAeropuertoPorIdCiudad(int id) {

		Aeropuerto ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_AEROPUERTO_WHERE_ID);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				ret = new Aeropuerto();
				ret.setCodigo(resultSet.getString("codigo"));
				Controlador controlador = Controlador.getInstanceControlador();
				Ciudad ciudad = controlador.getCiudadPorId(resultSet.getInt("id_ciudad"));
				ret.setCiudad(ciudad);

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}

	public ArrayList<Aeropuerto> getAeropuertos() {

		ArrayList<Aeropuerto> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_AEROPUERTO_Y_COD_CIUDAD);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Aeropuerto>();
				Aeropuerto aeropuerto = new Aeropuerto();
				aeropuerto.setCodigo(resultSet.getString("codigo"));
				Ciudad ciudad = new Ciudad();
				ciudad.setNombre(resultSet.getString("nombre"));
				aeropuerto.setCiudad(ciudad);
				ret.add(aeropuerto);

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}
}
