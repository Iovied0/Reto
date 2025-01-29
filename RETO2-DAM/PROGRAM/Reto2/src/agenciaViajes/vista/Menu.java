package agenciaViajes.vista;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import agenciaViajes.bbdd.pojos.*;
import agenciaViajes.controlador.Controlador;

public class Menu {
	Scanner teclado = new Scanner(System.in);
	Controlador controlador = new Controlador();

	public void mostrarmenu() {
		int opcion = -1;
		while (opcion != 0) {

			System.out.println("Introduce una opcion:" + "\n0: salir" + "\n1: mostrar todos los paises"
					+ "\n2: mostrar pais del viaje" + "\n3: mostrar todas las actividades" + "\n4: insertar actividad");
			opcion = teclado.nextInt();
			switch (opcion) {
			case 0:
				break;
			case 1:
				mostrarPaises();
				break;
			case 2:
				//mostrarPaisViaje();
				break;
			case 3:
				mostrarActividades();
				break;
			case 4:
				insertarActividad();
				break;
			default:
				System.out.println("hola mundo!");
			}
		}

	}

	private void insertarActividad() {
		
		Actividad newActividad = new Actividad();
		Date fechaDate = null;
		try {
			fechaDate = new Date(newActividad.getFormatoFecha().parse("2025-01-12").getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newActividad.
		
		
		controlador.insertarActividad(newActividad);
	}

	private void mostrarActividades() {
		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		
		actividades = controlador.mostrarActividades();
		System.out.println("-----------------------------------------------------------");
		for (int i = 0; i < actividades.size(); i++) {
			System.out.println(actividades.get(i).toString());
		}
		System.out.println("-----------------------------------------------------------");
	}

	/*private void mostrarPaisViaje() {
		Viaje viaje = new Viaje("japon", 2, "12/02/2025", "22/02/2025", 10, 30, "viaje a japon chavales", "nada");
		Pais paisViaje = new Pais();

		paisViaje = controlador.mostrarPaisArray(viaje.getPais());
		System.out.println(paisViaje.toString());
	}*/

	public void mostrarPaises() {
		ArrayList<Pais> paises = new ArrayList<Pais>();

		paises = controlador.mostrarPaises();
		System.out.println("-----------------------------------------------------------");
		for (int i = 0; i < paises.size(); i++) {
			System.out.println(paises.get(i).toString());
		}
		System.out.println("-----------------------------------------------------------");
	}

}
