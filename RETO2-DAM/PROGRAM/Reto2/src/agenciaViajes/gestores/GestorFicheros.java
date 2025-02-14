package agenciaViajes.gestores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
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
			crearPdf(document, agencia, vuelos, alojamientos, actividades, precioTotal, viaje);

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

		String texto = crearTexto(agencia, vuelos, alojamientos, actividades, precioTotal, viaje);

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
			ArrayList<Alojamiento> alojamientos, ArrayList<Actividad> actividades, double precioTotal, Viaje viaje) {

		// Creamos una tabla con 2 columnas para poner la imagen de la agencia y el
		// titulo alineados
		PdfPTable tablaHeader = new PdfPTable(2);
		tablaHeader.setWidthPercentage(100);
		tablaHeader.setSpacingBefore(10f);
		tablaHeader.setSpacingAfter(10f);

		// Obtenemos los colores de la agencia
		// El caracter 0 es el #, por eso empezamos en 1
		int colorAgenciaRojo = Integer.parseInt(agencia.getColor().substring(1, 3), 16);
		int colorAgenciaVerde = Integer.parseInt(agencia.getColor().substring(3, 5), 16);
		int colorAgenciaAzul = Integer.parseInt(agencia.getColor().substring(5, 7), 16);

		// Creamos un título con formato para que se vea bonito en el PDF
		Font fuenteTituloMain = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 19);
		// Cambiamos el color de la fuente al color de la agencia
		fuenteTituloMain.setColor(new BaseColor(colorAgenciaRojo, colorAgenciaVerde, colorAgenciaAzul));
		Paragraph tituloMain = new Paragraph("Oferta de cliente para la agencia: " + agencia.getNombre(),
				fuenteTituloMain);

		// Añadimos el titulo a la tabla invisible de la cabecera
		PdfPCell celdaTitulo = new PdfPCell(tituloMain);
		celdaTitulo.setBorder(PdfPCell.NO_BORDER);
		celdaTitulo.setVerticalAlignment(Element.ALIGN_BOTTOM);
		tablaHeader.addCell(celdaTitulo);

		// Obtenemos la imagen de la agencia para ponerla en la tabla
		try {
			Image logo = Image.getInstance(agencia.getLogo());
			logo.scaleToFit(100, 100);
			PdfPCell celdaLogo = new PdfPCell(logo);
			celdaLogo.setBorder(PdfPCell.NO_BORDER);
			celdaLogo.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tablaHeader.addCell(celdaLogo);
		} catch (Exception e) {
			System.err.println("no se ha encontrado el logo");
			  // Añade una celda vacia por si no se encuentra el logo
		    PdfPCell celdaVacia = new PdfPCell();
		    celdaVacia.setBorder(PdfPCell.NO_BORDER);
		    tablaHeader.addCell(celdaVacia);
		}

		try {
			document.add(tablaHeader);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// Creamos un título con formato para que se vea bonito en el PDF
		Font fuenteAgencia = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
		// Cambiamos el color de la fuente al color de la agencia
		fuenteAgencia.setColor(new BaseColor(colorAgenciaRojo, colorAgenciaVerde, colorAgenciaAzul));
		Paragraph tituloAgencia = new Paragraph("\nDatos de la Agencia:", fuenteAgencia);
		try {
			document.add(tituloAgencia);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// Añadimos los datos de la Agencia en una tabla de 5 columnas
		PdfPTable tablaAgencia = new PdfPTable(5);
		tablaAgencia.setWidthPercentage(100);
		tablaAgencia.setSpacingBefore(10f);
		tablaAgencia.setSpacingAfter(10f);

		// Añadimos los encabezados de la tabla
		tablaAgencia.addCell("Nombre");
		tablaAgencia.addCell("Logo");
		tablaAgencia.addCell("Color");
		tablaAgencia.addCell("Numero de empleados");
		tablaAgencia.addCell("Tipo de Agencia");

		tablaAgencia.addCell(agencia.getNombre());
		tablaAgencia.addCell(agencia.getLogo());
		tablaAgencia.addCell(agencia.getColor());
		tablaAgencia.addCell(agencia.getNumeroEmpleados().getNumeroEmpleados());
		tablaAgencia.addCell(agencia.getTipoAgencia().getDescripcion());
		try {
			document.add(tablaAgencia);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// Creamos un título con formato para que se vea bonito en el PDF
		Font fuenteViaje = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
		// Cambiamos el color de la fuente al color de la agencia
		fuenteViaje.setColor(new BaseColor(colorAgenciaRojo, colorAgenciaVerde, colorAgenciaAzul));
		Paragraph tituloViaje = new Paragraph("Datos del Viaje:", fuenteViaje);

		try {
			document.add(tituloViaje);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// Añadimos los datos del viaje en una tabla de 8 columnas
		PdfPTable tablaViaje = new PdfPTable(8);
		tablaViaje.setWidthPercentage(100);
		tablaViaje.setSpacingBefore(10f);
		tablaViaje.setSpacingAfter(10f);

		// Añadimos los encabezados de la tabla
		tablaViaje.addCell("Nombre del Viaje");
		tablaViaje.addCell("Descripcion del Viaje");
		tablaViaje.addCell("Fecha Inicio");
		tablaViaje.addCell("Fecha Fin");
		tablaViaje.addCell("Numero de días");
		tablaViaje.addCell("Servicios no incluidos");
		tablaViaje.addCell("Tipo de Viaje");
		tablaViaje.addCell("Pais de Destino");

		tablaViaje.addCell(viaje.getNombreViaje());
		tablaViaje.addCell(viaje.getDescViaje());
		tablaViaje.addCell(String.valueOf(viaje.getInicioViaje()));
		tablaViaje.addCell(String.valueOf(viaje.getFinViaje()));
		tablaViaje.addCell(String.valueOf(viaje.getNumeroDias()));
		tablaViaje.addCell(viaje.getServNoIncluidos());
		tablaViaje.addCell(viaje.getTipoViaje().getDescripcion());
		tablaViaje.addCell(viaje.getPais().getNombre());
		try {
			document.add(tablaViaje);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// Añadimos los datos de los vuelos
		if (vuelos != null) {
			// Creamos un título con formato para que se vea bonito en el PDF
			Font fuenteVuelo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
			// Cambiamos el color de la fuente al color de la agencia
			fuenteVuelo.setColor(new BaseColor(colorAgenciaRojo, colorAgenciaVerde, colorAgenciaAzul));
			Paragraph tituloVuelo = new Paragraph("Datos del Vuelo:", fuenteVuelo);

			try {
				document.add(tituloVuelo);
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
			// Creamos un título con formato para que se vea bonito en el PDF
			Font fuenteAlojamiento = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
			// Cambiamos el color de la fuente al color de la agencia
			fuenteAlojamiento.setColor(new BaseColor(colorAgenciaRojo, colorAgenciaVerde, colorAgenciaAzul));
			Paragraph tituloAlojamiento = new Paragraph("Datos del Alojamiento:", fuenteAlojamiento);

			try {
				document.add(tituloAlojamiento);
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
			// Creamos un título con formato para que se vea bonito en el PDF
			Font fuenteActividad = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
			// Cambiamos el color de la fuente al color de la agencia
			fuenteActividad.setColor(new BaseColor(colorAgenciaRojo, colorAgenciaVerde, colorAgenciaAzul));
			Paragraph tituloActividad = new Paragraph("Datos de la Actividad:", fuenteActividad);

			try {
				document.add(tituloActividad);
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
			ArrayList<Actividad> actividades, double precioTotal, Viaje viaje) {
		String ret = new String();

		// Creamos el texto con los datos de la agencia
		String texto1 = "\nDatos de la Agencia:" + "\nNombre: %s" + "\nLogo: %s" + "\nColor: %s"
				+ "\nNumero de empleados: %s" + "\nTipo de Agencia: %s";

		String format1 = String.format(texto1, agencia.getNombre(), agencia.getLogo(), agencia.getColor(),
				agencia.getNumeroEmpleados().getNumeroEmpleados(), agencia.getTipoAgencia().getDescripcion());
		// Añadimos el texto a ret
		ret += format1;

		// Añadimos los datos del viaje
		String tituloViaje = "\n\n///////////////////// VIAJE \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n";
		ret += tituloViaje;

		String textoViaje = "\n\nDatos del Viaje:" + "\nNombre del Viaje: %s" + "\nDescripcion del Viaje: %s"
				+ "\nFecha Inicio: %s" + "\nFecha Fin: %s" + "\nNumero de días: %s" + "\nServicios no incluidos: %s"
				+ "\nTipo de Viaje: %s" + "\nPais de Destino: %s";
		String format2 = String.format(textoViaje, viaje.getNombreViaje(), viaje.getDescViaje(), viaje.getInicioViaje(),
				viaje.getFinViaje(), viaje.getNumeroDias(), viaje.getServNoIncluidos(),
				viaje.getTipoViaje().getDescripcion(), viaje.getPais().getNombre());
		ret += format2;

		// Añadimos los datos de los vuelos si hay alguno
		if (vuelos != null) {

			String tituloVuelos = "\n\n///////////////////// VUELOS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n";
			ret += tituloVuelos;

			for (Vuelo vuelo : vuelos) {
				String txtVuelo = "\nTipo: %s" + "\nCodigo: %s" + "\nFecha: %s" + "\nHora Salida: %s" + "\nDuración: %s"
						+ "\nAerolinea: %s" + "\nAeropuerto Origen: %s" + "\nAeropuerto Destino: %s" + "\nPrecio: %s"
						+ "\nVuelo Asociado: %s" + "\n";
				String format3 = String.format(txtVuelo, vuelo.getTipoVuelo(), vuelo.getCodigo(), vuelo.getFecha(),
						vuelo.getHoraSalida(), vuelo.getDuracion(), vuelo.getAerolinea().getCodigo(),
						vuelo.getAeropuertoOrigen().getCodigo(), vuelo.getAeropuertoDestino().getCodigo(),
						vuelo.getPrecio(), vuelo.getCodigoAsociado());
				precioTotal += vuelo.getPrecio();
				ret += format3;
			}
		}

		if (alojamientos != null) {

			String tituloAlojamientos = "\n\n///////////////////// ALOJAMIENTOS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n";
			ret += tituloAlojamientos;

			for (Alojamiento alojamiento : alojamientos) {
				String txtAlojamiento = "\nNombre: %s" + "\nCiudad: %s" + "\nFecha Entrada: %s" + "\nFecha Salida: %s"
						+ "\nTipo Dormitorio: %s" + "\nPrecio: %s" + "\n";
				String format4 = String.format(txtAlojamiento, alojamiento.getNombreHotel(),
						alojamiento.getCiudad().getNombre(), alojamiento.getFechaEntrada(),
						alojamiento.getFechaSalida(), alojamiento.getTipoDormitorio().getDescripcion(),
						alojamiento.getPrecio());
				precioTotal += alojamiento.getPrecio();
				ret += format4;
			}
		}

		if (actividades != null) {

			String tituloActividades = "\n\n///////////////////// ACTIVIDADES \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n";
			ret += tituloActividades;

			for (Actividad actividad : actividades) {
				String txtActividad = "\nNombre: %s" + "\nDescripción: %s" + "\nFecha: %s" + "\nPrecio: %s" + "\n";
				String format5 = String.format(txtActividad, actividad.getNombre(), actividad.getDescripcion(),
						actividad.getFecha(), actividad.getPrecio());
				precioTotal += actividad.getPrecio();
				ret += format5;
			}
		}
		String total = "\n\n///////////////////// TOTAL A PAGAR \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n";
		ret += total + "\nPrecio Total del viaje: " + precioTotal + "€";

		return ret;

	}

}
