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

public class GestorAlojamiento {

	/**
	 * Recoge todas las actividades asignadas a un id_viaje
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<Alojamiento> getAlojamientosPorIdViaje(int id) {

		ArrayList<Alojamiento> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_ALOJAMIENTO_WHERE_IDVIAJE);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Alojamiento>();

				Alojamiento alojamiento = new Alojamiento();
				alojamiento.setId(resultSet.getInt("id"));
				alojamiento.setNombreHotel(resultSet.getString("nombre_hotel"));
				alojamiento.setFechaEntrada(resultSet.getDate("fecha_entrada"));
				alojamiento.setFechaSalida(resultSet.getDate("fecha_salida"));
				alojamiento.setPrecio(resultSet.getDouble("precio"));

				Controlador controlador = Controlador.getInstanceControlador();
				Viaje viaje = controlador.getViajePorId(resultSet.getInt("id_viaje"));
				alojamiento.setViaje(viaje);
				Ciudad ciudad = controlador.getCiudadPorId(resultSet.getInt("id_ciudad"));
				alojamiento.setCiudad(ciudad);
				TipoDormitorio tipoDormitorio = controlador
						.getTipoDormitorioPorCodigo(resultSet.getString("tipo_dormitorio"));
				alojamiento.setTipoDormitorio(tipoDormitorio);
				ret.add(alojamiento);
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

	public Alojamiento getAlojamientoPorCodigoDormitorio(String codigo) {

		Alojamiento ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_ALOJAMIENTO_WHERE_CODIGO_DORMITORIO);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setString(1, codigo);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				ret = new Alojamiento();
				ret.setId(resultSet.getInt("id"));
				ret.setNombreHotel(resultSet.getString("nombre_hotel"));
				ret.setFechaEntrada(resultSet.getDate("fecha_entrada"));
				ret.setFechaSalida(resultSet.getDate("fecha_salida"));
				ret.setPrecio(resultSet.getDouble("precio"));

				Controlador controlador = Controlador.getInstanceControlador();
				Viaje viaje = controlador.getViajePorId(resultSet.getInt("id_viaje"));
				ret.setViaje(viaje);
				Ciudad ciudad = controlador.getCiudadPorId(resultSet.getInt("id_ciudad"));
				ret.setCiudad(ciudad);
				TipoDormitorio tipoDormitorio = controlador
						.getTipoDormitorioPorCodigo(resultSet.getString("tipo_dormitorio"));
				ret.setTipoDormitorio(tipoDormitorio);
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
