package agenciaViajes.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import agenciaViajes.bbdd.DBUtils;
import agenciaViajes.bbdd.SQLQuerys;
import agenciaViajes.bbdd.pojos.Agencia;
import agenciaViajes.bbdd.pojos.NumeroEmpleados;
import agenciaViajes.bbdd.pojos.TiposAgencia;
import agenciaViajes.controlador.Controlador;

public class GestorAgencia {

	public void insertAgencia(String nombre, String contrasenya, String color, NumeroEmpleados numeroEmpleados, TiposAgencia tipoAgencia, String logo) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.prepareStatement(SQLQuerys.INSERT_NEW_AGENCIA);
			statement.setString(1, nombre);
			statement.setString(2, logo);
			statement.setString(3, color);
			statement.setString(4, contrasenya);
			statement.setString(5, numeroEmpleados.getCodigo());
			statement.setString(6, tipoAgencia.getCodigo());
			
			
			
			
			
//			if (tipoAgencia != null) {
//				statement.setString(5, agencia.getTipoAgencia().getCodigo());
//			} else {
//				statement.setNull(5, java.sql.Types.VARCHAR);
//			}

			statement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta manejarlo
			}
		}
	}

	public ArrayList<Agencia> getAgencias() {
		ArrayList<Agencia> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_AGENCIA);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Agencia>();

				Agencia agencia = new Agencia();
				TiposAgencia tipoagencia = new TiposAgencia();
				NumeroEmpleados numeroEmpleados = new NumeroEmpleados();
				Controlador controlador = new Controlador();
				agencia.setId(resultSet.getInt("id"));
				agencia.setNombre(resultSet.getString("nombre"));
				agencia.setLogo(resultSet.getString("logo"));
				agencia.setColor(resultSet.getString("color"));
				agencia.setContraseña(resultSet.getString("contraseña"));
				numeroEmpleados = controlador.getNumeroEmpleadosObjetoPorCodigo(resultSet.getString("numero_empleados"));
				agencia.setNumeroEmpleados(numeroEmpleados);
				tipoagencia = controlador.getTipoAgenciaObjetoPorCodigo(resultSet.getString("tipo_agencia"));
				agencia.setTipoAgencia(tipoagencia);
				ret.add(agencia);
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
