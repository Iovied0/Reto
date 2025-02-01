package agenciaViajes.controlador;

import java.util.ArrayList;

import agenciaViajes.bbdd.pojos.*;
import agenciaViajes.gestores.*;

public class Controlador {

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

//////////////////////////// FUNCIONES GESTOR ACTIVIDADES \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ArrayList<Actividad> mostrarActividades() {
		ArrayList<Actividad> ret = null;
		GestorActividad gestorActividad = new GestorActividad();
		ret = gestorActividad.mostrarActividades();
		return ret;
	}

//	public void insertarActividad (Actividad actividad) {
//		GestorActividad gestorActividad = new GestorActividad();
//		gestorActividad.insertarActividad(actividad);
//	}

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

	public NumeroEmpleados getNumeroEmpleadosObjeto(String descripcion) {
		NumeroEmpleados ret = new NumeroEmpleados();
		GestorNumeroEmpleados gestorNumeroEmpleados = new GestorNumeroEmpleados();
		ret = gestorNumeroEmpleados.getNumeroEmpleadosDescripcion(descripcion);
		return ret;
	}

//////////////////////////// FUNCIONES GESTOR TIPOS AGENCIA \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public ArrayList<TiposAgencia> getTipoAgencia() {
		ArrayList<TiposAgencia> ret = null;
		GestorTiposAgencia gestorTiposAgencia = new GestorTiposAgencia();
		ret = gestorTiposAgencia.getTiposAgencia();
		return ret;
	}

	public TiposAgencia getTipoAgenciaObjeto(String descripcion) {
		TiposAgencia ret = new TiposAgencia();
		GestorTiposAgencia gestorTiposAgencia = new GestorTiposAgencia();
		ret = gestorTiposAgencia.getTiposAgenciaDescripcion(descripcion);
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
		
		//NO ESTÁ TERMINADO PERO SOLO ES PARA CREAR EN EL FORMULARIO ASIQUE LO HAGO YO (YERAY)
		

	}
}
