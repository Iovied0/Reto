package agenciaViajes.bbdd;

/**
 * Esta clase contiene las sentencias SQL del proyecto
 */
public class SQLQuerys {

	// SELECTS

	public static final String SELECT_TODOS_PAISES_WERE_CODIGO = "SELECT * FROM pais WHERE codigo = ?";

	public static final String SELECT_TODOS_PAISES = "select * from pais";

	public static final String SELECT_TODOS_AEROLINEAS_WHERE_CODIGO = "SELECT * FROM aerolineas WHERE codigo = ?";
	
	public static final String SELECT_NOMBRES_AEROLINEAS = "SELECT nombre FROM aerolineas";

	public static final String SELECT_TODOS_AEROPUERTO_WHERE_CODIGO = "SELECT * FROM aeropuerto WHERE codigo = ?";

	public static final String SELECT_TODOS_AEROPUERTO_WHERE_ID = "SELECT * FROM aeropuerto WHERE id_ciudad = ?";
	
	public static final String SELECT_TODOS_AEROPUERTO_Y_COD_CIUDAD = "SELECT aeropuerto.codigo, ciudad.nombre FROM aeropuerto JOIN ciudad ON aeropuerto.id_ciudad = ciudad.id ORDER BY ciudad.nombre ASC";

	public static final String SELECT_TODOS_CIUDAD_WHERE_ID = "SELECT * FROM ciudad WHERE id = ?";

	public static final String SELECT_TODOS_VIAJES_WHERE_IDAGENCIA = "SELECT * from viaje where id_agencia = ?";
	
	public static final String SELECT_TODOS_VIAJES = "SELECT * from viaje";

	public static final String SELECT_TODOS_VIAJES_WHERE_ID = "SELECT * from viaje where id = ?";

	public static final String SELECT_TODOS_ACTIVIDAD = "select * from actividad";

	public static final String SELECT_TODOS_ACTIVIDAD_ID = "SELECT * from actividad where id_viaje = ?";

	public static final String SELECT_TODOS_TIPODORMITORIO_WHERE_CODIGO = "SELECT * from tipodormitorio where codigo = ?";

	public static final String SELECT_TODOS_ALOJAMIENTO_WHERE_IDVIAJE = "SELECT * from alojamiento where id_viaje = ?";

	public static final String SELECT_TODOS_ALOJAMIENTO_WHERE_CODIGO_DORMITORIO = "SELECT * from alojamiento where tipo_dormitorio = ?";

	public static final String SELECT_TODOS_VUELO_ID = "SELECT * FROM `vuelo` where id_viaje = ?";
	
	public static final String SELECT_TODOS_VUELO = "SELECT * FROM vuelo";

	public static final String SELECT_TODOS_NUMERO_EMPLEADOS = "select * from numeroempleados";

	public static final String SELECT_TODOS_TIPO_AGENCIA = "select * from tiposagencia";

	public static final String SELECT_TODOS_AGENCIA = "select * from agencia";

	public static final String SELECT_TODOS_AGENCIA_WHERE_ID = "select * from agencia where id = ?";

	public static final String SELECT_TODOS_NUMERO_EMPLEADOS_WHERE_DESCRIPCION = "select * from numeroempleados where numero_empleados = ?";

	public static final String SELECT_TODOS_NUMERO_EMPLEADOS_WHERE_CODIGO = "select * from numeroempleados where codigo = ?";

	public static final String SELECT_TODOS_TIPOS_AGENCIA_WHERE_DESCRIPCION = "select * from tiposagencia where descripcion = ?";

	public static final String SELECT_TODOS_TIPOS_AGENCIA_WHERE_CODIGO = "select * from tiposagencia where codigo = ?";

	public static final String SELECT_TODOS_TIPOS_VIAJE = "select * from tipoviaje";

	public static final String SELECT_TODOS_TIPOS_VIAJE_WHERE_DESCRIPCION = "select * from tipoviaje where descripcion = ?";

	public static final String SELECT_TODOS_TIPOS_VIAJE_WHERE_CODIGO = "select * from tipoviaje where codigo = ?";

	// INSERTS

	public static final String INSERT_NEW_ACTIVIDAD = "insert into actividad (nombre, descripcion, fecha, precio, id_viaje) VALUES (?, ?, ?, ?, ? )";

	public static final String INSERT_NEW_AGENCIA = "INSERT INTO Agencia (nombre, logo, color, contraseña, numero_empleados, tipo_agencia) VALUES (?, ?, ?, ?, ?, ?)";
	
	public static final String INSERT_NEW_ALOJAMIENTO = "INSERT INTO Alojamiento (id, nombre_hotel, fecha_entrada, fecha_salida, precio, id_viaje, id_ciudad, tipo_dormitorio) VALUES (?, ?, ?, ?, ?, ?)";
	
	public static final String SEPARATOR = "', '";

	public static final String END_BLOCK = "')";

	
//	public static final String INSERT_NEW_ALUMNO = "insert into alumnos (dni, nombre, apellidos, grupo) VALUES ('";
//
//	public static final String INSERT_NEW_NOTA = "insert into calificaciones (dni, CodAsignatura, nota) VALUES ('";

	// UPDATES

//	public static final String UPDATE_MEDIA_ALUMNO_BY_DNI = "update alumnos set grupo = ? where dni = ?";

	// DELETES
	
	public static final String DELETE_ALL_VIAJE_WHERE_ID = "delete from viaje where id = ?";
	
	public static final String DELETE_ALL_ACTIVIDAD_WHERE_VIAJE = "delete from actividad where id_viaje = ?";
	
	public static final String DELETE_ALL_VUELO_WHERE_VIAJE = "delete from vuelo where id_viaje = ?";
	
	public static final String DELETE_ALL_ALOJAMIENTO_WHERE_VIAJE = "delete from alojamiento where id_viaje = ?";
	
	public static final String DELETE_ALL_ACTIVIDAD_WHERE_NOMBRE = "delete from actividad where nombre = ?";
	
	public static final String DELETE_ALL_VUELO_WHERE_CODIGO = "delete from vuelo where codigo_vuelo = ?";
	
	public static final String DELETE_ALL_ALOJAMIENTO_WHERE_ID = "delete from alojamiento where id = ?";
	
//	public static final String DELETE_ALL_NOTAS = "delete from calificaciones";
}