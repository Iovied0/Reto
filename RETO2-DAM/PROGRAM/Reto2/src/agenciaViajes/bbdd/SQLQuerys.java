package agenciaViajes.bbdd;

/**
 * Esta clase contiene las sentencias SQL del proyecto
 */
public class SQLQuerys {

	// SELECTS

	public static final String SELECT_TODOS_PAISES_POR_CODIGO = "SELECT * FROM pais WHERE codigo = ?";

//	public static final String SELECT_TODOS_ACTIVIDADES_POR_IDVIAJE = "SELECT * FROM actividad WHERE idViaje = ?";

	public static final String SELECT_TODOS_PAISES = "select * from pais";

	public static final String SELECT_TODOS_VIAJES_ID = "SELECT * from viaje where id_agencia = ?";

	public static final String SELECT_TODOS_ACTIVIDAD = "select * from actividad";

	public static final String SELECT_TODOS_ACTIVIDAD_ID = "SELECT * from actividad where id_viaje = ?";

	public static final String SELECT_TODOS_NUMERO_EMPLEADOS = "select * from numeroempleados";

	public static final String SELECT_TODOS_TIPO_AGENCIA = "select * from tiposagencia";

	public static final String SELECT_TODOS_AGENCIA = "select * from agencia";

	public static final String SELECT_TODOS_NUMERO_EMPLEADOS_WHERE_DESCRIPCION = "select * from numeroempleados where numero_empleados = ?";
	
	public static final String SELECT_TODOS_NUMERO_EMPLEADOS_WHERE_CODIGO = "select * from numeroempleados where codigo = ?";

	public static final String SELECT_TODOS_TIPOS_AGENCIA_WHERE_DESCRIPCION = "select * from tiposagencia where descripcion = ?";

	public static final String SELECT_TODOS_TIPOS_AGENCIA_WHERE_CODIGO = "select * from tiposagencia where codigo = ?";

	public static final String SELECT_TODOS_TIPOS_VIAJE = "select * from tipoviaje";
	
	public static final String SELECT_TODOS_TIPOS_VIAJE_WHERE_CODIGO = "select * from tipoviaje where codigo = ?";

	
	//	public static final String SELECT_TODOS_NOTAS_BY_ID_ALUMNO = "SELECT * FROM `calificaciones` WHERE `Dni` = ?";

	// INSERTS

	public static final String INSERT_NEW_ACTIVIDAD = "insert into actividad (nombre, descripcion, fecha, precio, id_evento) VALUES (?, ?, ?, ?, ?)";

	public static final String SEPARATOR = "', '";

	public static final String END_BLOCK = "')";

	public static final String INSERT_NEW_AGENCIA = "INSERT INTO Agencia (nombre, logo, color, contraseña, numero_empleados, tipo_agencia) VALUES (?, ?, ?, ?, ?, ?)";
	
	public static final String INSERT_NEW_VIAJE = "INSERT INTO viaje (nombreViaje, descViaje, inicioViaje, finViaje, numeroDias, servNoIncluidos, id_agencia, tipo_viaje, pais_destino) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String INSERT_NEW_VUELO = "INSERT INTO vuelo (tipo_vuelo, codigo_vuelo_ida, fecha_ida, hora_salida_ida, duracion_ida, aerolinea_ida, aeropuerto_origen, aeropuerto_destino, codigo_vuelo_vuelta, fecha_vuelta, hora_salida_vuelta, duracion_vuelta, aerolinea_vuelta, id_viaje, precio_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
	public static final String INSERT_NEW_ALOJAMIENTO = "INSERT INTO alojamiento (nombre_hotel, fecha_entrada, fecha_salida, precio, id_viaje, id_ciudad, tipo_dormitorio) VALUES (?, ?, ?, ?, ?, ?, ?)";


//	public static final String INSERT_NEW_ALUMNO = "insert into alumnos (dni, nombre, apellidos, grupo) VALUES ('";
//
//	public static final String INSERT_NEW_NOTA = "insert into calificaciones (dni, CodAsignatura, nota) VALUES ('";

	// UPDATES

//	public static final String UPDATE_MEDIA_ALUMNO_BY_DNI = "update alumnos set grupo = ? where dni = ?";

	// DELETES

//	public static final String DELETE_ALL_ALUMNOS = "delete from alumnos";
//	public static final String DELETE_ALL_NOTAS = "delete from calificaciones";
}