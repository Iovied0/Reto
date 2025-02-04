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
	public ArrayList<Viaje> getViajesId(Agencia agencia) {

		ArrayList<Viaje> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_VIAJES_ID);
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
	 * Retorna de BBDD el pais por el nombre
	 * 
	 * @return Los paises en la BBDD
	 */
	public Pais mostrarPaisViaje(String codigo) {

		Pais ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_PAISES_POR_CODIGO);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setString(1, codigo);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				ret = new Pais();
				ret.setCodigo(resultSet.getString("codigo"));
				ret.setNombre(resultSet.getString("nombre"));

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
	 * Inserta un alumno en BBDD
	 * 
	 * @param alumno
	 */
//		public void insertarAlumno(Alumno alumno) {
//
//			Connection connection = null;
//			Statement statement = null;
//
//			try {
//				Class.forName(DBUtils.DRIVER);
//				connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
//				statement = connection.createStatement();
//
//				String sql = SQLQuerys.INSERT_NEW_ALUMNO + alumno.getDNI() + SQLQuerys.SEPARATOR + alumno.getNombre()
//						+ SQLQuerys.SEPARATOR + alumno.getApellidos() + SQLQuerys.SEPARATOR + alumno.getGrupo() + SQLQuerys.END_BLOCK;
//
//				statement.executeUpdate(sql);
//
//			} catch (SQLException sqle) {
//				System.out.println("Error con la BBDD - " + sqle.getMessage());
//			} catch (Exception e) {
//				System.out.println("Error generico - " + e.getMessage());
//			} finally {
//				// Cerramos al reves de como las abrimos
//				try {
//					if (statement != null)
//						statement.close();
//				} catch (Exception e) {
//					// No hace falta
//				}
//				try {
//					if (connection != null)
//						connection.close();
//				} catch (Exception e) {
//					// No hace falta
//				}
//			}
//		}
//
//		
//		/**
//		 * Actualiza el grupo de un alumno
//		 * 
//		 * @param alumno
//		 * @param grupo
//		 */
//		public void actualizarMediaAlumno(Alumno alumno, int grupo) {
//			Connection connection = null;
//			PreparedStatement preparedStatement = null;
//
//			try {
//				Class.forName(DBUtils.DRIVER);
//				connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
//
//				String sql = SQLQuerys.UPDATE_MEDIA_ALUMNO_BY_DNI;
//				preparedStatement = connection.prepareStatement(sql);
//				preparedStatement.setInt(1, grupo);
//				preparedStatement.setString(2, alumno.getDNI());
//
//				preparedStatement.executeUpdate();
//
//			} catch (SQLException sqle) {
//				System.out.println("Error con la BBDD - " + sqle.getMessage());
//			} catch (Exception e) {
//				System.out.println("Error generico - " + e.getMessage());
//			} finally {
//				try {
//					if (preparedStatement != null)
//						preparedStatement.close();
//				} catch (Exception e) {
//					// No hace falta
//				}
//				try {
//					if (connection != null)
//						connection.close();
//				} catch (Exception e) {
//					// No hace falta
//				}
//			}
//		}

}
