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
public class GestorViajes {

	/**
	 * Retorna de BBDD todos los Viajes almacenados en la BBDD filtrados por
	 * ID_agencia
	 * 
	 * @return Los paises en la BBDD
	 */
	public ArrayList<Viaje> getViajesPorIdAgencia(Agencia agencia) {

		ArrayList<Viaje> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_VIAJES_WHERE_IDAGENCIA);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan

			preparedStatement.setInt(1, agencia.getId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Viaje>();

				Viaje viaje = new Viaje();
				viaje.setId(resultSet.getInt("id"));
				viaje.setNombreViaje(resultSet.getString("nombreViaje"));
				viaje.setDescViaje(resultSet.getString("descViaje"));
				viaje.setInicioViaje(resultSet.getDate("inicioViaje"));
				viaje.setFinViaje(resultSet.getDate("finViaje"));
				viaje.setNumeroDias(resultSet.getInt("numeroDias"));
				viaje.setServNoIncluidos(resultSet.getString("servNoIncluidos"));
				viaje.setAgencia(agencia);
				Controlador controlador = Controlador.getInstanceControlador();
				TipoViaje tipoViaje = controlador.getTipoViajeObjetoPorCodigo(resultSet.getString("tipo_viaje"));
				viaje.setTipoViaje(tipoViaje);
				Pais pais = controlador.getPaisPorCodigo(resultSet.getString("pais_destino"));
				viaje.setPais(pais);

				ret.add(viaje);
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

	/**
	 * Retorna de BBDD todos los datos de un viaje cuando se le da su ID
	 * 
	 * @return Los paises en la BBDD
	 */
	public Viaje getViajePorId(int id) {

		Viaje ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_VIAJES_WHERE_ID);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				ret = new Viaje();
				Viaje viaje = new Viaje();
				viaje.setId(resultSet.getInt("id"));
				viaje.setNombreViaje(resultSet.getString("nombreViaje"));
				viaje.setDescViaje(resultSet.getString("descViaje"));
				viaje.setInicioViaje(resultSet.getDate("inicioViaje"));
				viaje.setFinViaje(resultSet.getDate("finViaje"));
				viaje.setNumeroDias(resultSet.getInt("numeroDias"));
				viaje.setServNoIncluidos(resultSet.getString("servNoIncluidos"));
				Controlador controlador = Controlador.getInstanceControlador();
				Agencia agencia = controlador.getAgenciaPorId(resultSet.getInt("id_agencia"));
				viaje.setAgencia(agencia);
				TipoViaje tipoViaje = controlador.getTipoViajeObjetoPorCodigo(resultSet.getString("tipo_viaje"));
				viaje.setTipoViaje(tipoViaje);
				Pais pais = controlador.getPaisPorCodigo(resultSet.getString("pais_destino"));
				viaje.setPais(pais);
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

	public void deleteViajePorId(int id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.DELETE_ALL_VIAJE_WHERE_ID);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

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
	}
}
