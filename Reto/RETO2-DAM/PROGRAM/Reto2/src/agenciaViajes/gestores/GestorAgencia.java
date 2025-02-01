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
import agenciaViajes.bbdd.pojos.Pais;

public class GestorAgencia {

	public void insertEjemplo(Agencia agencia) {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			stmt = connection.prepareStatement(SQLQuerys.INSERT_NEW_AGENCIA);
			stmt.setString(1, agencia.getNombre());
			stmt.setString(2, agencia.getLogo());
			stmt.setString(3, agencia.getColor());
			stmt.setString(4, agencia.getNumeroEmpleados().getCodigo());

			if (agencia.getTipoAgencia() != null) {
				stmt.setString(5, agencia.getTipoAgencia().getCodigo());
			} else {
				stmt.setNull(5, java.sql.Types.VARCHAR);
			}

			stmt.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
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
			//Enlaza cada ? de SQLQuerys con los parametros que se pasan
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if(null == ret)
					ret = new ArrayList<Agencia>();
				
				Agencia agencia = new Agencia();
				agencia.setId(resultSet.getInt("id"));
				agencia.setNombre(resultSet.getString("nombre"));
				agencia.setLogo(resultSet.getString("logo"));
				agencia.setColor(resultSet.getString("color"));
				agencia.setContraseña(resultSet.getString("contraseña"));
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
