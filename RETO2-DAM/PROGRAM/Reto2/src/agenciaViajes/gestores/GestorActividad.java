package agenciaViajes.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import agenciaViajes.bbdd.*;
import agenciaViajes.bbdd.pojos.*;

/**
 * Esta clase tiene todos los metodos relevantes a la tabla t_alumno de BBDD.
 */
public class GestorActividad {

	/**
	 * Retorna de BBDD todos los paises almacenados en la BBDD
	 * 
	 * @return Los paises en la BBDD
	 */
	public ArrayList<Actividad> mostrarActividades() {

		ArrayList<Actividad> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_ACTIVIDAD);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Actividad>();

				Actividad actividad = new Actividad();
				actividad.setNombre(resultSet.getString("nombre"));
				actividad.setDescripcion(resultSet.getString("descripcion"));
				actividad.setFecha(resultSet.getDate("fecha"));
				actividad.setPrecio(resultSet.getDouble("precio"));
				actividad.setId(resultSet.getInt("id_evento"));
				ret.add(actividad);
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
	public void insertarActividad(Actividad actividad) {

		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = SQLQuerys.INSERT_NEW_ACTIVIDAD + actividad.getNombre() + SQLQuerys.SEPARATOR + actividad.getDescripcion()
					+ SQLQuerys.SEPARATOR + actividad.getFecha()
					+ SQLQuerys.SEPARATOR + actividad.getPrecio() + SQLQuerys.SEPARATOR + actividad.getId()
					+ SQLQuerys.END_BLOCK;

			statement.executeUpdate(sql);

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (statement != null)
					statement.close();
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

//		/**
//		 * Retorna de BBDD el pais por el nombre
//		 * 
//		 * @return Los paises en la BBDD
//		 */
//		public Pais mostrarPaisViaje(String codigo) {
//
//			Pais ret = null;
//			Connection connection = null;
//			PreparedStatement preparedStatement = null;
//			ResultSet resultSet = null;
//
//			try {
//				Class.forName(DBUtils.DRIVER);
//				connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
//				preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_PAISES_POR_CODIGO);
//				//Enlaza cada ? de SQLQuerys con los parametros que se pasan
//				preparedStatement.setString(1, codigo);
//				resultSet = preparedStatement.executeQuery();
//
//				if (resultSet.next()) {
//					ret = new Pais();
//					ret.setCodigoPais(resultSet.getString("codigo"));
//					ret.setNombrePais(resultSet.getString("nombre"));
//					
//				}
//			} catch (SQLException sqle) {
//				System.out.println("Error con la BBDD - " + sqle.getMessage());
//			} catch (Exception e) {
//				System.out.println("Error generico - " + e.getMessage());
//			} finally {
//				// Cerramos al reves de como las abrimos
//				try {
//					if (resultSet != null)
//						resultSet.close();
//				} catch (Exception e) {
//					// No hace falta
//				}
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
//			return ret;
//		}
//		
//		
//		
//		/**
//		 * Inserta un alumno en BBDD
//		 * 
//		 * @param alumno
//		 */
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
