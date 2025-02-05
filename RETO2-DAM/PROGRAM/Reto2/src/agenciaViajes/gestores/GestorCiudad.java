package agenciaViajes.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import agenciaViajes.bbdd.*;
import agenciaViajes.bbdd.pojos.*;
import agenciaViajes.controlador.Controlador;

/**
 * Esta clase tiene todos los metodos relevantes a la tabla t_alumno de BBDD.
 */
public class GestorCiudad {

	/**
	 * Recoge una ciudad por su id
	 * 
	 * @param id
	 * @return
	 */
	public Ciudad getCiudadPorId(int id) {

		Ciudad ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_CIUDAD_WHERE_ID);
			preparedStatement.setInt(1, id);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				ret = new Ciudad();

				ret.setId(resultSet.getInt("id"));
				ret.setNombre(resultSet.getString("nombre"));
				Controlador controlador = Controlador.getInstanceControlador();
				Pais pais = controlador.getPaisPorCodigo(resultSet.getString("codPais"));
				ret.setPais(pais);
				Aeropuerto aeropuerto = controlador.getAeropuertoPorIdCiudad(resultSet.getInt("id"));
				ret.setAeropuerto(aeropuerto);
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
