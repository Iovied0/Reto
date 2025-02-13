package agenciaViajes.gestores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import agenciaViajes.bbdd.pojos.Actividad;
import agenciaViajes.bbdd.pojos.Agencia;
import agenciaViajes.bbdd.pojos.Alojamiento;
import agenciaViajes.bbdd.pojos.Viaje;
import agenciaViajes.bbdd.pojos.Vuelo;
import agenciaViajes.controlador.Controlador;

/**
 * Pide al usuario un texto y lo escribe en el fichero. Si el fichero no existe,
 * lo crea.
 * 
 * Por defecto, cada vez que se abre el fichero para escribir, borra su
 * contenido. Si queremos que en vez de eso lo que escribamos se ponga al final,
 * debemos de indicarselo (ver linea 30)
 */

public class GestorFicheros {

	public void generarOfertaClientePdf(Viaje viaje) {

		String NOMBRE_FICHERO = "OfertaCliente.pdf";
		String RUTA_FICHERO = "Ficheros";
		File f = new File(RUTA_FICHERO + "/" + NOMBRE_FICHERO);

		Controlador controlador = Controlador.getInstanceControlador();

		// recogemos la agencia
		Agencia agencia = controlador.getInstanceAgencia();

		// recogemos los vuelos del viaje
		ArrayList<Vuelo> vuelos = controlador.getVuelosPorIdViaje(viaje);

		// recogemos los alojamientos del viaje
		ArrayList<Alojamiento> alojamientos = controlador.getAlojamientosPorIdViaje(viaje);

		// recogemos las actividades del viaje
		ArrayList<Actividad> actividades = controlador.getActividadesPorIdViaje(viaje);

		// Calculamos el precio total
		double precioTotal = 0;

		if (vuelos != null) {
			for (Vuelo vuelo : vuelos) {
				precioTotal += vuelo.getPrecio();
			}
		}
		if (alojamientos != null) {
			for (Alojamiento alojamiento : alojamientos) {
				precioTotal += alojamiento.getPrecio();
			}
		}
		if (actividades != null) {
			for (Actividad actividad : actividades) {
				precioTotal += actividad.getPrecio();
			}
		}

		System.out.println("Vamos a escribir en el fichero " + NOMBRE_FICHERO + " en la ruta " + RUTA_FICHERO);

		// Si no existe la carpeta, la crea
		File carpeta = new File(RUTA_FICHERO);
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}
		// Preparamos las clases necesarias para escribir un fichero
		Document document = new Document(PageSize.A4.rotate()); // Creamos un documento con tamaño A4 en horizontal

		try {
			// Asignamos el fichero que vamos a escribir
			PdfWriter.getInstance(document, new FileOutputStream(f));
			document.open();

			// Rellenamos el PDF con los datos
			crearPdf(document, agencia, vuelos, alojamientos, actividades, precioTotal);

			document.close();
			System.out.println("El fichero " + NOMBRE_FICHERO + " se ha guardado correctamente.");
		} catch (DocumentException e) {
			System.out.println("DocumentException - Error de escritura en el fichero " + RUTA_FICHERO + NOMBRE_FICHERO);
		} catch (IOException e) {
			System.out.println("IOException - Error de escritura en el fichero " + RUTA_FICHERO + NOMBRE_FICHERO);
		} finally {
			if (null != document) {
				document.close();
			}
		}

	}

	public void generarOfertaClienteTxt(Viaje viaje) {
		String NOMBRE_FICHERO = "OfertaCliente.txt";
		String RUTA_FICHERO = "Ficheros";
		File f = new File(RUTA_FICHERO + "/" + NOMBRE_FICHERO);
		Controlador controlador = Controlador.getInstanceControlador();

		// recogemos la agencia
		Agencia agencia = controlador.getInstanceAgencia();

		// recogemos los vuelos del viaje
		ArrayList<Vuelo> vuelos = controlador.getVuelosPorIdViaje(viaje);

		// recogemos los alojamientos del viaje
		ArrayList<Alojamiento> alojamientos = controlador.getAlojamientosPorIdViaje(viaje);

		// recogemos las actividades del viaje
		ArrayList<Actividad> actividades = controlador.getActividadesPorIdViaje(viaje);

		// Calculamos el precio total
		double precioTotal = 0;

		for (Vuelo vuelo : vuelos) {
			precioTotal += vuelo.getPrecio();
		}
		for (Alojamiento alojamiento : alojamientos) {
			precioTotal += alojamiento.getPrecio();
		}
		for (Actividad actividad : actividades) {
			precioTotal += actividad.getPrecio();
		}

		String texto = crearTexto(agencia, vuelos, alojamientos, actividades, precioTotal);

		System.out.println("Vamos a escribir en el fichero " + NOMBRE_FICHERO + " en la ruta " + RUTA_FICHERO);

		// Si no existe la carpeta, la crea
		File carpeta = new File(RUTA_FICHERO);
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}
		// Preparamos las clases necesarias para escribir un fichero
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;

		try {
			// Asignamos el fichero que vamos a escribir
			fileWriter = new FileWriter(f);

			// Si preferimos que el fichero se actualice a final...
			// fileWriter = new FileWriter(RUTA_FICHERO + NOMBRE_FICHERO, true);

			printWriter = new PrintWriter(fileWriter);

			// Lo mandamos al fichero
			printWriter.println(texto);
			System.out.println("El fichero " + NOMBRE_FICHERO + " se ha guardado correctamente.");

		} catch (IOException e) {
			System.out.println("IOException - Error de escritura en el fichero " + RUTA_FICHERO + NOMBRE_FICHERO);
		} finally {
			if (null != printWriter)
				printWriter.close();
			try {
				if (null != fileWriter)
					fileWriter.close();
			} catch (IOException e) {
				// Nos da igual
			}
		}
	}

	private void crearPdf(Document document, Agencia agencia, ArrayList<Vuelo> vuelos,
			ArrayList<Alojamiento> alojamientos, ArrayList<Actividad> actividades, double precioTotal) {

		// Creamos un título con formato para que se vea bonito en el PDF
		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22);
		Paragraph title = new Paragraph("Oferta de cliente para la agencia: " + agencia.getNombre(), titleFont);

		try {
			document.add(title);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		String texto1 = "\nDatos de la Agencia:" + "\nNombre: %s" + "\nLogo: %s" + "\nColor: %s"
				+ "\nNumero de empleados: %s" + "\nTipo de Agencia: %s";

		String combiAgencia = String.format(texto1, agencia.getNombre(), agencia.getLogo(), agencia.getColor(),
				agencia.getNumeroEmpleados().getNumeroEmpleados(), agencia.getTipoAgencia().getDescripcion());

		try {
			document.add(new Paragraph(combiAgencia));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Añadimos los datos de los vuelos
		if (vuelos != null) {
			String tituloVuelos = "\nVuelos:";
			try {
				document.add(new Paragraph(tituloVuelos));
			} catch (DocumentException e) {
				e.printStackTrace();
			}

			// Creamos una tabla con 10 columnas
			PdfPTable tablaVuelos = new PdfPTable(10);
			tablaVuelos.setWidthPercentage(100);
			tablaVuelos.setSpacingBefore(10f);
			tablaVuelos.setSpacingAfter(10f);

			// Añadimos los encabezados de la tabla
			tablaVuelos.addCell("Tipo");
			tablaVuelos.addCell("Codigo");
			tablaVuelos.addCell("Fecha");
			tablaVuelos.addCell("Hora Salida");
			tablaVuelos.addCell("Duración");
			tablaVuelos.addCell("Aerolinea");
			tablaVuelos.addCell("Aeropuerto Origen");
			tablaVuelos.addCell("Aeropuerto Destino");
			tablaVuelos.addCell("Precio");
			tablaVuelos.addCell("Vuelo Asociado");
			for (Vuelo vuelo : vuelos) {
				tablaVuelos.addCell(vuelo.getTipoVuelo());
				tablaVuelos.addCell(vuelo.getCodigo());
				tablaVuelos.addCell(vuelo.getFecha().toString());
				tablaVuelos.addCell(vuelo.getHoraSalida().toString());
				tablaVuelos.addCell(vuelo.getDuracion().toString());
				tablaVuelos.addCell(vuelo.getAerolinea().getCodigo());
				tablaVuelos.addCell(vuelo.getAeropuertoOrigen().getCodigo());
				tablaVuelos.addCell(vuelo.getAeropuertoDestino().getCodigo());
				tablaVuelos.addCell(String.valueOf(vuelo.getPrecio() + "€"));
				tablaVuelos.addCell(vuelo.getCodigoAsociado());
				precioTotal += vuelo.getPrecio();
			}
			try {
				document.add(tablaVuelos);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		// Añadimos los datos de los alojamientos
		if (alojamientos != null) {
			String tituloAlojamientos = "\nAlojamientos:";
			try {
				document.add(new Paragraph(tituloAlojamientos));
			} catch (DocumentException e) {
				e.printStackTrace();
			}

			// Creamos una tabla con 6 columnas
			PdfPTable tablaAlojamientos = new PdfPTable(6);
			tablaAlojamientos.setWidthPercentage(100);
			tablaAlojamientos.setSpacingBefore(10f);
			tablaAlojamientos.setSpacingAfter(10f);

			// Añadimos los encabezados de la tabla
			tablaAlojamientos.addCell("Nombre");
			tablaAlojamientos.addCell("Ciudad");
			tablaAlojamientos.addCell("Fecha Entrada");
			tablaAlojamientos.addCell("Fecha Salida");
			tablaAlojamientos.addCell("Tipo Dormitorio");
			tablaAlojamientos.addCell("Precio");
			for (Alojamiento alojamiento : alojamientos) {
				tablaAlojamientos.addCell(alojamiento.getNombreHotel());
				tablaAlojamientos.addCell(alojamiento.getCiudad().getNombre());
				tablaAlojamientos.addCell(String.valueOf(alojamiento.getFechaEntrada()));
				tablaAlojamientos.addCell(String.valueOf(alojamiento.getFechaSalida()));
				tablaAlojamientos.addCell(alojamiento.getTipoDormitorio().getDescripcion());
				tablaAlojamientos.addCell(String.valueOf(alojamiento.getPrecio() + "€"));
				precioTotal += alojamiento.getPrecio();
			}
			try {
				document.add(tablaAlojamientos);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		// Añadimos los datos de las actividades
		if (actividades != null) {
			String tituloActividades = "\nActividades:";
			try {
				document.add(new Paragraph(tituloActividades));
			} catch (DocumentException e) {
				e.printStackTrace();
			}

			// Creamos una tabla con 4 columnas
			PdfPTable tablaActividades = new PdfPTable(4);
			tablaActividades.setWidthPercentage(100);
			tablaActividades.setSpacingBefore(10f);
			tablaActividades.setSpacingAfter(10f);

			// Añadimos los encabezados de la tabla
			tablaActividades.addCell("Nombre");
			tablaActividades.addCell("Descripción");
			tablaActividades.addCell("Fecha");
			tablaActividades.addCell("Precio");
			for (Actividad actividad : actividades) {
				tablaActividades.addCell(actividad.getNombre());
				tablaActividades.addCell(actividad.getDescripcion());
				tablaActividades.addCell(String.valueOf(actividad.getFecha()));
				tablaActividades.addCell(String.valueOf(actividad.getPrecio() + "€"));
				precioTotal += actividad.getPrecio();
			}
			try {
				document.add(tablaActividades);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}

		// Añadimos el precio total

		try {
			Paragraph txtPrecioTotal = new Paragraph(("Precio Total del viaje: " + precioTotal + "€"));
			txtPrecioTotal.setAlignment(Element.ALIGN_RIGHT);
			document.add(txtPrecioTotal);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	private String crearTexto(Agencia agencia, ArrayList<Vuelo> vuelos, ArrayList<Alojamiento> alojamientos,
			ArrayList<Actividad> actividades, double precioTotal) {
		String ret = new String();

		// Creamos el texto con los datos de la agencia
		String texto1 = "\nDatos de la Agencia:" + "\nNombre: %s" + "\nLogo: %s" + "\nColor: %s"
				+ "\nNumero de empleados: %s" + "\nTipo de Agencia: %s";

		String format1 = String.format(texto1, agencia.getNombre(), agencia.getLogo(), agencia.getColor(),
				agencia.getNumeroEmpleados().getNumeroEmpleados(), agencia.getTipoAgencia().getDescripcion());
		// Añadimos el texto a ret
		ret += format1;

		// Añadimos los datos de los vuelos si hay alguno
		if (vuelos != null) {

			String tituloVuelos = "\n\n///////////////////// VUELOS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n";
			ret += tituloVuelos;

			for (Vuelo vuelo : vuelos) {
				String txtVuelo = "\nTipo: %s" + "\nCodigo: %s" + "\nFecha: %s" + "\nHora Salida: %s" + "\nDuración: %s"
						+ "\nAerolinea: %s" + "\nAeropuerto Origen: %s" + "\nAeropuerto Destino: %s" + "\nPrecio: %s"
						+ "\nVuelo Asociado: %s" + "\n";
				String format2 = String.format(txtVuelo, vuelo.getTipoVuelo(), vuelo.getCodigo(), vuelo.getFecha(),
						vuelo.getHoraSalida(), vuelo.getDuracion(), vuelo.getAerolinea().getCodigo(),
						vuelo.getAeropuertoOrigen().getCodigo(), vuelo.getAeropuertoDestino().getCodigo(),
						vuelo.getPrecio(), vuelo.getCodigoAsociado());
				precioTotal += vuelo.getPrecio();
				ret += format2;
			}
		}

		if (alojamientos != null) {

			String tituloAlojamientos = "\n\n///////////////////// ALOJAMIENTOS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n";
			ret += tituloAlojamientos;

			for (Alojamiento alojamiento : alojamientos) {
				String txtAlojamiento = "\nNombre: %s" + "\nCiudad: %s" + "\nFecha Entrada: %s" + "\nFecha Salida: %s"
						+ "\nTipo Dormitorio: %s" + "\nPrecio: %s" + "\n";
				String format3 = String.format(txtAlojamiento, alojamiento.getNombreHotel(),
						alojamiento.getCiudad().getNombre(), alojamiento.getFechaEntrada(),
						alojamiento.getFechaSalida(), alojamiento.getTipoDormitorio().getDescripcion(),
						alojamiento.getPrecio());
				precioTotal += alojamiento.getPrecio();
				ret += format3;
			}
		}

		if (actividades != null) {

			String tituloActividades = "\n\n///////////////////// ACTIVIDADES \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n";
			ret += tituloActividades;

			for (Actividad actividad : actividades) {
				String txtActividad = "\nNombre: %s" + "\nDescripción: %s" + "\nFecha: %s" + "\nPrecio: %s" + "\n";
				String format4 = String.format(txtActividad, actividad.getNombre(), actividad.getDescripcion(),
						actividad.getFecha(), actividad.getPrecio());
				precioTotal += actividad.getPrecio();
				ret += format4;
			}
		}
		String total = "\n\n///////////////////// TOTAL A PAGAR \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n";
		ret += total + "\nPrecio Total del viaje: " + precioTotal + "€";

		return ret;

	}

}
