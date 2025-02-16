package agenciaViajes.controlador;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import agenciaViajes.ViajesErrekamari;
import agenciaViajes.bbdd.pojos.*;
import agenciaViajes.gestores.*;

public class Controlador {
	private static Controlador controlador = null;

	private Agencia instanceAgencia = null;

	public static Controlador getInstanceControlador() {

		if (null == controlador) {
			controlador = new Controlador();
		}
		return controlador;
	}

//////////////////////////// FUNCIONES GESTOR PAISES \\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public ArrayList<Pais> mostrarPaises() {
		ArrayList<Pais> ret = null;
		GestorPaises gestorPaises = new GestorPaises();
		ret = gestorPaises.mostrarPaises();
		return ret;
	}
//
//	public Pais mostrarPaisArray(int posicionArray) {
//		ArrayList<Pais> paises = mostrarPaises();
//		String codigoPais;
//		Pais ret = null;
//		GestorPaises gestorPaises = new GestorPaises();
//
//		codigoPais = paises.get(posicionArray).getCodigo();
//		ret = gestorPaises.getPaisPorCodigo(codigoPais);
//		return ret;
//	}

	public Pais getPaisPorCodigo(String codigo) {
		Pais ret = null;
		GestorPaises gestorPaises = new GestorPaises();
		ret = gestorPaises.getPaisPorCodigo(codigo);
		return ret;
	}

////////////////////////////FUNCIONES GESTOR VIAJES \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ArrayList<Viaje> getViajesPorIdAgencia(Agencia agencia) {
		ArrayList<Viaje> ret = null;
		GestorViajes gestorViajes = new GestorViajes();
		ret = gestorViajes.getViajesPorIdAgencia(agencia);
		return ret;
	}

	public ArrayList<Viaje> getViajes() {
		ArrayList<Viaje> ret = null;
		GestorViajes gestorViajes = new GestorViajes();
		ret = gestorViajes.getViajes();
		return ret;
	}

	public Viaje getViajePorId(int id) {
		Viaje ret = null;
		GestorViajes gestorviajes = new GestorViajes();
		ret = gestorviajes.getViajePorId(id);
		return ret;
	}

	public void deleteViajePorId(Viaje viaje, ViajesErrekamari frame) {
		GestorViajes gestorViajes = new GestorViajes();
		gestorViajes.deleteViajePorId(viaje.getId());
		frame.gotoViajes();
	}

//////////////////////////// FUNCIONES GESTOR NUMERO EMPLEADOS \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ArrayList<NumeroEmpleados> getNumeroEmpleados() {
		ArrayList<NumeroEmpleados> ret = null;
		GestorNumeroEmpleados gestorNumeroEmpleados = new GestorNumeroEmpleados();
		ret = gestorNumeroEmpleados.getNumeroEmpleados();
		return ret;
	}

	public NumeroEmpleados getNumeroEmpleadosObjetoPorDescripcion(String descripcion) {
		NumeroEmpleados ret = new NumeroEmpleados();
		GestorNumeroEmpleados gestorNumeroEmpleados = new GestorNumeroEmpleados();
		ret = gestorNumeroEmpleados.getNumeroEmpleadosDescripcion(descripcion);
		return ret;
	}

	public NumeroEmpleados getNumeroEmpleadosObjetoPorCodigo(String codigo) {
		NumeroEmpleados ret = new NumeroEmpleados();
		GestorNumeroEmpleados gestorNumeroEmpleados = new GestorNumeroEmpleados();
		ret = gestorNumeroEmpleados.getNumeroEmpleadosCodigo(codigo);
		return ret;
	}

//////////////////////////// FUNCIONES GESTOR TIPOS AGENCIA \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ArrayList<TiposAgencia> getTipoAgencia() {
		ArrayList<TiposAgencia> ret = null;
		GestorTiposAgencia gestorTiposAgencia = new GestorTiposAgencia();
		ret = gestorTiposAgencia.getTiposAgencia();
		return ret;
	}

	public TiposAgencia getTipoAgenciaObjetoPorDescripcion(String descripcion) {
		TiposAgencia ret = new TiposAgencia();
		GestorTiposAgencia gestorTiposAgencia = new GestorTiposAgencia();
		ret = gestorTiposAgencia.getTipoAgenciaDescripcion(descripcion);
		return ret;

	}

	public TiposAgencia getTipoAgenciaObjetoPorCodigo(String codigo) {
		TiposAgencia ret = new TiposAgencia();
		GestorTiposAgencia gestorTiposAgencia = new GestorTiposAgencia();
		ret = gestorTiposAgencia.getTipoAgenciaCodigo(codigo);
		return ret;
	}

//////////////////////////// FUNCIONES GESTOR AGENCIA \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ArrayList<Agencia> getAgencias() {
		ArrayList<Agencia> ret = new ArrayList<Agencia>();
		GestorAgencia gestorAgencia = new GestorAgencia();

		ret = gestorAgencia.getAgencias();
		return ret;
	}

	public void insertarAgencia(String nombre, String contrasenya, String color, NumeroEmpleados numeroEmpleados,
			TiposAgencia tipoAgencia, String logo) {
		GestorAgencia gestorAgencia = new GestorAgencia();

		gestorAgencia.insertAgencia(nombre, contrasenya, color, numeroEmpleados, tipoAgencia, logo);

	}

	public Agencia getAgenciaPorId(int id) {
		Agencia ret = null;
		GestorAgencia gestorAgencia = new GestorAgencia();
		ret = gestorAgencia.getAgenciaPorId(id);
		return ret;
	}

	public void setInstanceAgencia(Agencia agencia) {
		instanceAgencia = agencia;
	}

	public Agencia getInstanceAgencia() {
		if (null == instanceAgencia) {
			instanceAgencia = new Agencia();
		}
		return instanceAgencia;
	}

	public void deleteInstanceAgencia() {
		instanceAgencia = null;
	}

//////////////////////////// FUNCIONES GESTOR TIPOS VIAJE \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ArrayList<TipoViaje> getTiposViaje() {
		ArrayList<TipoViaje> ret = null;
		GestorTipoViaje gestorTipoViaje = new GestorTipoViaje();
		ret = gestorTipoViaje.getTiposViaje();
		return ret;
	}

	public ArrayList<TipoViaje> getTipoViajePorDescripcion(String descripcion) {
		ArrayList<TipoViaje> ret = null;
		GestorTipoViaje gestorTipoViaje = new GestorTipoViaje();
		ret = gestorTipoViaje.getTipoViajePorDescripcion(descripcion);
		return ret;
	}

	public TipoViaje getTipoViajeObjetoPorCodigo(String codigo) {
		TipoViaje ret = new TipoViaje();
		GestorTipoViaje gestorTiposAgencia = new GestorTipoViaje();
		ret = gestorTiposAgencia.getTipoViajePorCodigo(codigo);
		return ret;
	}

//////////////////////////// FUNCIONES GESTOR ACTIVIDADES \\\\\\\\\\\\\\\\\\\\\\\\\\\\
//	public ArrayList<Actividad> getActividadesAgencia(Agencia agencia) {
//		ArrayList<Actividad> ret = null;
//		GestorActividad gestorActividad = new GestorActividad();
//		ret = gestorActividad.getActividades();
//		return ret;
//	}

	public ArrayList<Actividad> getActividadesPorIdViaje(Viaje viaje) {
		ArrayList<Actividad> ret = null;
		GestorActividad gestorActividad = new GestorActividad();
		ret = gestorActividad.getActividadesPorIdViaje(viaje.getId());
		return ret;
	}

	public void deleteActividadesPorIdViaje(Viaje viaje) {
		GestorActividad gestorActividad = new GestorActividad();
		gestorActividad.deleteActividadesPorIdViaje(viaje.getId());
	}

	public void deleteActividadPorNombre(String nombre, ViajesErrekamari frame) {
		GestorActividad gestorActividad = new GestorActividad();
		gestorActividad.deleteActividadPorNombre(nombre);
		frame.gotoViajes();
	}

	public void insertActividad(String nombre, String descripcion, Date fecha, Double precio, int id_viaje,
			ViajesErrekamari frame) {
		GestorActividad gestorActividad = new GestorActividad();
		gestorActividad.insertActividad(nombre, descripcion, fecha, precio, id_viaje);
		frame.gotoViajes();
	}

//////////////////////////// FUNCIONES GESTOR VUELOS \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ArrayList<Vuelo> getVuelosPorIdViaje(Viaje viaje) {
		ArrayList<Vuelo> ret = null;
		GestorVuelos gestorVuelos = new GestorVuelos();
		ret = gestorVuelos.getVuelosPorIdViaje(viaje);
		return ret;
	}
	
	public ArrayList<Vuelo> getCodVuelos() {
		ArrayList<Vuelo> ret = null;
		GestorVuelos gestorVuelos = new GestorVuelos();
		ret = gestorVuelos.getCodVuelos();
		return ret;
	}

	public void deleteVuelosPorIdViaje(Viaje viaje) {
		GestorVuelos gestorVuelos = new GestorVuelos();
		gestorVuelos.deleteVuelosPorIdViaje(viaje.getId());
	}

	public void deleteVueloPorNombre(String codigo, ViajesErrekamari frame) {
		GestorVuelos gestorVuelos = new GestorVuelos();
		gestorVuelos.deleteVueloPorCodigo(codigo);
		frame.gotoViajes();
	}

	public void insertVuelo(String tipo_vuelo, String codigo_vuelo, Date fecha, Time hora_salida, Time duracion,
			String aerolinea, String aeropuerto_origen, String aeropuerto_destino, int id_viaje, Double precio,
			String codigo_asociado, ViajesErrekamari frame) {
		GestorVuelos gestorVuelos = new GestorVuelos();
		gestorVuelos.insertVuelos(tipo_vuelo, codigo_vuelo, fecha, hora_salida, duracion, aerolinea, aeropuerto_origen,
				aeropuerto_destino, id_viaje, precio, codigo_asociado);
		frame.gotoViajes();
	}

//////////////////////////// FUNCIONES GESTOR ALOJAMIENTO \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ArrayList<Alojamiento> getAlojamientosPorIdViaje(Viaje viaje) {
		ArrayList<Alojamiento> ret = null;
		GestorAlojamiento gestorAlojamiento = new GestorAlojamiento();
		ret = gestorAlojamiento.getAlojamientosPorIdViaje(viaje);
		return ret;
	}

	public Alojamiento getAlojamientoPorCodigoDormitorio(String codigo) {
		Alojamiento ret = null;
		GestorAlojamiento gestorAlojamiento = new GestorAlojamiento();
		ret = gestorAlojamiento.getAlojamientoPorCodigoDormitorio(codigo);
		return ret;
	}

	public void deleteAlojamientosPorIdViaje(Viaje viaje) {
		GestorAlojamiento gestorAlojamiento = new GestorAlojamiento();
		gestorAlojamiento.deleteAlojamientosPorIdViaje(viaje.getId());
	}

	public void deleteAlojamientoPorId(int id, ViajesErrekamari frame) {
		GestorAlojamiento gestorAlojamiento = new GestorAlojamiento();
		gestorAlojamiento.deleteAlojamientoPorId(id);
		frame.gotoViajes();
	}

	public void insertAlojamiento(String nombre_hotel, Date fecha_entrada, Date fecha_salida, Double precio,
			int id_viaje, int id_ciudad, String tipo_dormitorio, ViajesErrekamari frame) {
		GestorAlojamiento gestorAlojamiento = new GestorAlojamiento();
		gestorAlojamiento.insertAlojamiento(nombre_hotel, fecha_entrada, fecha_salida, precio, id_viaje, id_ciudad,
				tipo_dormitorio);
		frame.gotoViajes();
	}

//////////////////////////// FUNCIONES GESTOR AEROLINEAS \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public Aerolineas getAerolineaPorCodigo(String codigo) {
		Aerolineas ret = null;
		GestorAerolineas gestorAerolineas = new GestorAerolineas();
		ret = gestorAerolineas.getAerolineaPorCodigo(codigo);
		return ret;
	}

	public ArrayList<Aerolineas> getAerolineas() {
		ArrayList<Aerolineas> ret = null;
		GestorAerolineas gestorAerolineas = new GestorAerolineas();
		ret = gestorAerolineas.getAerolineas();
		return ret;
	}

//////////////////////////// FUNCIONES GESTOR AEROPUERTO \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public Aeropuerto getAeropuertoPorCodigo(String codigo) {
		Aeropuerto ret = null;
		GestorAeropuerto gestorAeropuerto = new GestorAeropuerto();
		ret = gestorAeropuerto.getAeropuertoPorCodigo(codigo);
		return ret;
	}

	public Aeropuerto getAeropuertoPorIdCiudad(int id) {
		Aeropuerto ret = null;
		GestorAeropuerto gestorAeropuerto = new GestorAeropuerto();
		ret = gestorAeropuerto.getAeropuertoPorIdCiudad(id);
		return ret;
	}

	public ArrayList<Aeropuerto> getAeropuertos() {
		ArrayList<Aeropuerto> ret = null;
		GestorAeropuerto gestorAeropuerto = new GestorAeropuerto();
		ret = gestorAeropuerto.getAeropuertos();
		return ret;
	}

//////////////////////////// FUNCIONES GESTOR CIUDAD \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public Ciudad getCiudadPorId(int id) {
		Ciudad ret = null;
		GestorCiudad gestorCiudad = new GestorCiudad();
		ret = gestorCiudad.getCiudadPorId(id);
		return ret;
	}

	public ArrayList<Ciudad> getCiudades() {
		ArrayList<Ciudad> ret = null;
		GestorCiudad gestorCiudad = new GestorCiudad();
		ret = gestorCiudad.getCiudades();
		return ret;
	}

//////////////////////////// FUNCIONES GESTOR TIPO DORMITORIO \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public TipoDormitorio getTipoDormitorioPorCodigo(String codigo) {
		TipoDormitorio ret = null;
		GestorTipoDormitorio gestorTipoDormitorio = new GestorTipoDormitorio();
		ret = gestorTipoDormitorio.getTipoDormitorioPorCodigo(codigo);

		return ret;
	}

	public ArrayList<TipoDormitorio> getTipoDormitorio() {
		ArrayList<TipoDormitorio> ret = null;
		GestorTipoDormitorio gestorTipoDormitorio = new GestorTipoDormitorio();
		ret = gestorTipoDormitorio.getTipoDormitorio();
		return ret;
	}

//////////////////////////// FUNCIONES GESTOR NUEVO VIAJE \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public void insertViaje(String nombreViaje, String descViaje, Date inicioViaje, Date finViaje,
			String servNoIncluidos, int id_agencia, String tipo_viaje, String paisSeleccionado,
			ViajesErrekamari frame) {
		GestorViajes gestorViajes = new GestorViajes();
		gestorViajes.insertViaje(nombreViaje, descViaje, inicioViaje, finViaje, servNoIncluidos, id_agencia, tipo_viaje,
				paisSeleccionado);
		frame.gotoViajes();
	}

//////////////////////////// FUNCIONES GESTOR FICHEROS \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public void generarOfertaClientePdf(Viaje viaje) {
		GestorFicheros gestorFicheros = new GestorFicheros();
		gestorFicheros.generarOfertaClientePdf(viaje);
	}
	
	public void generarOfertaClienteTxt(Viaje viaje) {
		GestorFicheros gestorFicheros = new GestorFicheros();
		gestorFicheros.generarOfertaClienteTxt(viaje);
	}
}
