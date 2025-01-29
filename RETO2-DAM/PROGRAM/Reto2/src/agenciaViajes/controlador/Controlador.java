package agenciaViajes.controlador;

import java.util.ArrayList;

import agenciaViajes.bbdd.pojos.*;
import agenciaViajes.gestores.*;
public class Controlador {

	public ArrayList<Pais> mostrarPaises() {
		ArrayList<Pais> ret = null;
		GestorPaises gestorPaises = new GestorPaises();
		ret = gestorPaises.mostrarPaises();
		return ret;
	}
	
	public Pais mostrarPaisArray(int posicionArray) {
		ArrayList<Pais>paises = mostrarPaises();
		String codigoPais;
		Pais ret = null;
		GestorPaises gestorPaises = new GestorPaises();
		
		codigoPais = paises.get(posicionArray).getCodigo();
		ret = gestorPaises.mostrarPaisViaje(codigoPais);
		return ret;
	}
	
	public ArrayList<Actividad> mostrarActividades() {
		ArrayList<Actividad> ret = null;
		GestorActividad gestorActividad = new GestorActividad();
		ret = gestorActividad.mostrarActividades();
		return ret;
	}
	
	public void insertarActividad (Actividad actividad) {
		GestorActividad gestorActividad = new GestorActividad();
		gestorActividad.insertarActividad(actividad);
	}
	
	public void dbInsertActividadesEnViaje(Viaje viaje) {
		// Llama a la DB y consulta todas las Actividades en forma de ArrayList.
		/*
		ArrayList<Actividades> actividades = gestorDB.getActividades(viaje.getId());
		
		for (Actividad actividad: actividades) {
			actividad.setViaje(viaje);
		}
		
		viaje.setActividades(actividades);
		*/
	}
	
}
