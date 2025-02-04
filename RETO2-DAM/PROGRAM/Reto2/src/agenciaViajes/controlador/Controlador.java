package agenciaViajes.controlador;

import java.util.ArrayList;

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

	public Pais mostrarPaisArray(int posicionArray) {
		ArrayList<Pais> paises = mostrarPaises();
		String codigoPais;
		Pais ret = null;
		GestorPaises gestorPaises = new GestorPaises();

		codigoPais = paises.get(posicionArray).getCodigo();
		ret = gestorPaises.mostrarPaisViaje(codigoPais);
		return ret;
	}
	
////////////////////////////FUNCIONES GESTOR VIAJES \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ArrayList<Viaje>getViajesId(Agencia agencia){
		ArrayList<Viaje>ret = null;
		
		return ret;
	}

//////////////////////////// FUNCIONES GESTOR ACTIVIDADES \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ArrayList<Actividad> mostrarActividades() {
		ArrayList<Actividad> ret = null;
		GestorActividad gestorActividad = new GestorActividad();
		ret = gestorActividad.mostrarActividades();
		return ret;
	}

	public void dbInsertActividadesEnViaje(Viaje viaje) {
		// Llama a la DB y consulta todas las Actividades en forma de ArrayList.
		GestorActividad gestorActividad = new GestorActividad();
		ArrayList<Actividad> actividades = gestorActividad.getActividades(viaje.getId());

		for (Actividad actividad : actividades) {
			actividad.setViaje(viaje);
		}
		viaje.setActividades(actividades);
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

////////////////////////////FUNCIONES GESTOR TIPOS VIAJE \\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public ArrayList<TipoViaje> getTipoViajePorDescripcion(String descripcion) {
		ArrayList<TipoViaje> ret = null;
		GestorTipoViaje gestorTipoViaje = new GestorTipoViaje();
		ret = gestorTipoViaje.getTipoViajePorDescripcion(descripcion);
		return ret;
	}

}
