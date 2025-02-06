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
public class GestorVuelos {

	/**
	 * Recoge todos los vuelos asignados a un id_viaje
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<Vuelo> getVuelosPorIdViaje(Viaje viaje) {

		ArrayList<Vuelo> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_VUELO_ID);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setInt(1, viaje.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Vuelo>();

				Vuelo vuelo = new Vuelo();
				vuelo.setTipoVuelo(resultSet.getString("tipo_vuelo"));
				vuelo.setCodigo(resultSet.getString("codigo_vuelo"));
				vuelo.setFecha(resultSet.getDate("fecha"));
				vuelo.setHoraSalida(resultSet.getTime("hora_salida"));
				vuelo.setDuracion(resultSet.getTime("duracion"));

				Aerolineas aerolinea = new Aerolineas();
				Controlador controlador = Controlador.getInstanceControlador();
				aerolinea = controlador.getAerolineaPorCodigo(resultSet.getString("aerolinea"));
				vuelo.setAerolinea(aerolinea);
				Aeropuerto aeropuertoOrigen = controlador
						.getAeropuertoPorCodigo(resultSet.getString("aeropuerto_origen"));
				vuelo.setAeropuertoOrigen(aeropuertoOrigen);
				Aeropuerto aeropuertoDestino = controlador
						.getAeropuertoPorCodigo(resultSet.getString("aeropuerto_destino"));
				vuelo.setAeropuertoDestino(aeropuertoDestino);
				vuelo.setViaje(viaje);
				vuelo.setPrecio(resultSet.getDouble("precio_total"));
				ret.add(vuelo);
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

	public void deleteVuelosPorIdViaje(int id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.DELETE_ALL_VUELO_WHERE_VIAJE);
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

	public void deleteVueloPorCodigo(String codigo) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.DELETE_ALL_VUELO_WHERE_CODIGO);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setString(1, codigo);
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
