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

public class GestorAerolineas {

	/**
	 * Recoge la aerolinea asociada al codigo proporcionado
	 * 
	 * @param id
	 * @return
	 */
	public Aerolineas getAerolineaPorCodigo(String codigo) {

		Aerolineas ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_AEROLINEAS_WHERE_CODIGO);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setString(1, codigo);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				ret = new Aerolineas();
				ret.setNombre(resultSet.getString("nombre"));
				ret.setCodigo(resultSet.getString("codigo"));

				Controlador controlador = Controlador.getInstanceControlador();
				Pais pais = controlador.getPaisPorCodigo(resultSet.getString("pais"));
				ret.setPais(pais);
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

	public ArrayList<Aerolineas> getAerolineas() {

		ArrayList<Aerolineas> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_NOMBRES_AEROLINEAS);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Aerolineas>();
				Aerolineas aerolineas = new Aerolineas();
				aerolineas.setNombre(resultSet.getString("nombre"));
				ret.add(aerolineas);

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
