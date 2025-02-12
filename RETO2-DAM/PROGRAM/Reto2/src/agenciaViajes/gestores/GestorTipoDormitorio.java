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

public class GestorTipoDormitorio {

	/**
	 * Recoge un tipo de dormitorio por su codigo
	 * 
	 * @param id
	 * @return
	 */
	public TipoDormitorio getTipoDormitorioPorCodigo(String codigo) {

		TipoDormitorio ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_TIPODORMITORIO_WHERE_CODIGO);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setString(1, codigo);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				ret = new TipoDormitorio();
				ret.setCodigo(resultSet.getString("codigo"));
				ret.setDescripcion(resultSet.getString("descripcion"));
//				Controlador controlador = Controlador.getInstanceControlador();
//				Alojamiento alojamiento = controlador.getAlojamientoPorCodigoDormitorio(resultSet.getString("codigo"));
//				ret.setAlojamiento(alojamiento);

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

	public ArrayList<TipoDormitorio> getTipoDormitorio() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<TipoDormitorio> ret = new ArrayList<>();

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_TIPOS_DORMITORIO);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				TipoDormitorio tipoDormitorio = new TipoDormitorio();
	            tipoDormitorio.setCodigo(resultSet.getString("codigo"));
				tipoDormitorio.setDescripcion(resultSet.getString("descripcion"));
				ret.add(tipoDormitorio);
			}

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
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
