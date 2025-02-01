package agenciaViajes.bbdd;

/**
 * Esta clase contiene las sentencias SQL del proyecto
 */
public class SQLQuerys {

	// SELECTS

	public static final String SELECT_TODOS_PAISES_POR_CODIGO = "SELECT * FROM pais WHERE codigo = ?";

//	public static final String SELECT_TODOS_PAISES_POR_CODIGO = "SELECT * FROM actividad WHERE idViaje = ?";

	public static final String SELECT_TODOS_PAISES = "select * from pais";

	public static final String SELECT_TODOS_ACTIVIDAD = "select * from actividad";

	public static final String SELECT_TODOS_ACTIVIDAD_ID = "SELECT * from actividad where id_viaje = ?"; 
	
	public static final String SELECT_TODOS_NUMERO_EMPLEADOS = "select * from numeroempleados";
	
	public static final String SELECT_TODOS_TIPO_AGENCIA = "select * from tiposagencia";
	
	public static final String SELECT_TODOS_AGENCIA = "select * from agencia";
	
	public static final String SELECT_TODOS_NUMERO_EMPLEADOS_WHERE_DESCRIPCION = "select * from numeroempleados where numero_empleados = ?";
	
	public static final String SELECT_TODOS_TIPOS_AGENCIA_WHERE_DESCRIPCION = "select * from tiposagencia where descripcion = ?";

//	public static final String SELECT_TODOS_NOTAS_BY_ID_ALUMNO = "SELECT * FROM `calificaciones` WHERE `Dni` = ?";

	// INSERTS

	public static final String INSERT_NEW_ACTIVIDAD = "insert into actividad (nombre, descripcion, fecha, precio, id_evento) VALUES ('";

	public static final String SEPARATOR = "', '";

	public static final String END_BLOCK = "')";
	
	public static final String INSERT_NEW_AGENCIA = "INSERT INTO Agencia (nombre, logo, color, numero_empleados, tipo_agencia) VALUES (?, ?, ?, ?, ?)";

//	public static final String INSERT_NEW_ALUMNO = "insert into alumnos (dni, nombre, apellidos, grupo) VALUES ('";
//
//	public static final String INSERT_NEW_NOTA = "insert into calificaciones (dni, CodAsignatura, nota) VALUES ('";

	// UPDATES

//	public static final String UPDATE_MEDIA_ALUMNO_BY_DNI = "update alumnos set grupo = ? where dni = ?";

	// DELETES

//	public static final String DELETE_ALL_ALUMNOS = "delete from alumnos";
//	public static final String DELETE_ALL_NOTAS = "delete from calificaciones";
}