package agenciaViajes.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import agenciaViajes.bbdd.DBUtils;
import agenciaViajes.bbdd.SQLQuerys;
import agenciaViajes.bbdd.pojos.NumeroEmpleados;

public class GestorNumeroEmpleados {

	public ArrayList<NumeroEmpleados> getNumeroEmpleados() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<NumeroEmpleados> ret = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_NUMERO_EMPLEADOS);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<NumeroEmpleados>();

				NumeroEmpleados numeroEmpleados = new NumeroEmpleados();
				numeroEmpleados.setCodigo(resultSet.getString("codigo"));
				numeroEmpleados.setNumeroEmpleados(resultSet.getString("numero_empleados"));
				ret.add(numeroEmpleados);
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

	public NumeroEmpleados getNumeroEmpleadosDescripcion(String descripcion) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		NumeroEmpleados ret = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_NUMERO_EMPLEADOS_WHERE_DESCRIPCION);
			preparedStatement.setString(1, descripcion);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				ret = new NumeroEmpleados();
				ret.setCodigo(resultSet.getString("codigo"));
				ret.setNumeroEmpleados(resultSet.getString("numero_empleados"));
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

	public NumeroEmpleados getNumeroEmpleadosCodigo(String descripcion) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		NumeroEmpleados ret = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_NUMERO_EMPLEADOS_WHERE_CODIGO);
			preparedStatement.setString(1, descripcion);
			resultSet = preparedStatement.executeQuery();
			
			
			if (resultSet.next()) {
				ret = new NumeroEmpleados();
				ret.setCodigo(resultSet.getString("codigo"));
				ret.setNumeroEmpleados(resultSet.getString("numero_empleados"));
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
