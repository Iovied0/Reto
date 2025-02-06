
package agenciaViajes.vista.paneles;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import agenciaViajes.ViajesErrekamari;
import agenciaViajes.bbdd.pojos.Actividad;
import agenciaViajes.bbdd.pojos.Agencia;
import agenciaViajes.bbdd.pojos.Alojamiento;
import agenciaViajes.bbdd.pojos.Viaje;
import agenciaViajes.bbdd.pojos.Vuelo;
import agenciaViajes.controlador.Controlador;

public class ViajesyEventos {

	private JPanel panel;
	private JTable travelTable, eventTable;;
	private DefaultTableModel travelModel, eventModel;
	private JButton generateOfferButton, btnBorrarViaje, btnBorrarEvento;

	public ViajesyEventos(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1300, 900);
		panel.setLayout(null);
		Controlador controlador = Controlador.getInstanceControlador();
		Agencia agencia = controlador.getInstanceAgencia();
		JLabel labelTitulo = new JLabel("Viajes y Eventos de " + agencia.getNombre());

		labelTitulo.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
		Color colorAgencia = Color.decode(agencia.getColor());
		labelTitulo.setForeground(colorAgencia);
		labelTitulo.setBounds(200, 65, 600, 27);
		panel.add(labelTitulo);

		JLabel labelViajes = new JLabel("Viajes");
		labelViajes.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 20));
		labelViajes.setBounds(570, 180, 600, 27);
		panel.add(labelViajes);

		JLabel labelEventos = new JLabel("Eventos");
		labelEventos.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 20));
		labelEventos.setBounds(570, 490, 600, 27);
		panel.add(labelEventos);

		JLabel labelLogo = new JLabel();
		try {
			URI uriLogo = new URI(agencia.getLogo());
			URL urlLogo = uriLogo.toURL();
			ImageIcon logo = new ImageIcon(urlLogo);
			Image logoReescalado = logo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			labelLogo.setIcon(new ImageIcon(logoReescalado));
			labelLogo.setBounds(50, 50, 100, 100);
			panel.add(labelLogo);
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		}

		controlador = Controlador.getInstanceControlador();
		ArrayList<Viaje> viajes = controlador.getViajesPorIdAgencia(agencia);

		// Initialize travelModel and travelTable

		travelModel = new DefaultTableModel(
				new Object[] { "Nombre", "Tipo", "Días", "Fecha Inicio", "Fecha Fin", "País" }, 0) {
			private static final long serialVersionUID = 1L; // si no lo pongo me da warning

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Ninguna celda será editable
			}
		};

		travelTable = new JTable(travelModel);
		travelTable.getTableHeader().setReorderingAllowed(false);
		travelTable.setFocusable(false); // asi no muestra el cuadrado del focus al pulsar una celda
		JScrollPane travelScrollPane = new JScrollPane(travelTable);
		if (viajes != null) {
			for (Viaje viaje : viajes) {
				Object[] newRow = { viaje.getNombreViaje(), viaje.getTipoViaje().getDescripcion(),
						viaje.getNumeroDias(), viaje.getInicioViaje(), viaje.getFinViaje(),
						viaje.getPais().getNombre() };
				travelModel.addRow(newRow);

			}
		}

		travelScrollPane.setBounds(200, 230, 860, 200);
		panel.add(travelScrollPane);

		// Initialize eventModel and eventTable
		eventModel = new DefaultTableModel(new Object[] { "Nombre evento", "Tipo", "Fecha", "Precio" }, 0) {
			private static final long serialVersionUID = 1L; // si no lo pongo me da warning

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Ninguna celda será editable
			}
		};

		eventTable = new JTable(eventModel);
		eventTable.getTableHeader().setReorderingAllowed(false);
		eventTable.setFocusable(false);
		JScrollPane eventScrollPane = new JScrollPane(eventTable);
		eventScrollPane.setBounds(200, 530, 860, 200);
		panel.add(eventScrollPane);

		// Buttons
		generateOfferButton = new JButton("Generar oferta cliente");
		generateOfferButton.setBounds(350, 800, 200, 25);
		panel.add(generateOfferButton);

		btnBorrarEvento = new JButton("Borrar Evento");
		btnBorrarEvento.setBounds(150, 800, 200, 25);
		panel.add(btnBorrarEvento);

		btnBorrarViaje = new JButton("Borrar Viaje");
		btnBorrarViaje.setBounds(550, 800, 200, 25);
		panel.add(btnBorrarViaje);

		// Action Listeners
		generateOfferButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = travelTable.getSelectedRow();
				if (selectedRow != -1) {
					String nombre = (String) travelTable.getValueAt(selectedRow, 0);
					String tipo = (String) travelTable.getValueAt(selectedRow, 1);
					String dias = (String) travelTable.getValueAt(selectedRow, 2);
					String fechaInicio = (String) travelTable.getValueAt(selectedRow, 3);
					String fechaFin = (String) travelTable.getValueAt(selectedRow, 4);
					String pais = (String) travelTable.getValueAt(selectedRow, 5);

					try (PrintWriter writer = new PrintWriter("oferta.txt", "UTF-8")) {
						writer.println("Oferta de Viaje:");
						writer.println("Nombre: " + nombre);
						writer.println("Tipo: " + tipo);
						writer.println("Días: " + dias);
						writer.println("Fecha Inicio: " + fechaInicio);
						writer.println("Fecha Fin: " + fechaFin);
						writer.println("País: " + pais);
						JOptionPane.showMessageDialog(null, "Oferta generada exitosamente.");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error al generar la oferta: " + ex.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecciona un viaje primero.");
				}
			}
		});

		// Mouse Listener for travelTable
		travelTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Doble clic
					int selectedRow = travelTable.getSelectedRow();
					if (selectedRow != -1) {
						Controlador controlador = Controlador.getInstanceControlador();
						Viaje viaje = viajes.get(selectedRow);
						ArrayList<Vuelo> vuelos = controlador.getVuelosPorIdViaje(viaje);
						ArrayList<Alojamiento> alojamientos = controlador.getAlojamientosPorIdViaje(viaje);
						ArrayList<Actividad> actividades = controlador.getActividadesPorIdViaje(viaje);

						if (eventModel.getRowCount() == 0) {
							if (vuelos != null) {
								for (Vuelo vuelo : vuelos) {

									Object[] newRow = new Object[4];
									if (vuelo.getTipoVuelo().equalsIgnoreCase("IDA")) {
										newRow[0] = "Vuelo Ida - " + vuelo.getCodigo();
										newRow[1] = "Vuelo";
										newRow[2] = vuelo.getFecha();
										newRow[3] = vuelo.getPrecio() + "€";
									} else {
										newRow[0] = "Vuelo Vuelta - " + vuelo.getCodigo();
										newRow[1] = "Vuelo";
										newRow[2] = vuelo.getFecha();
										newRow[3] = vuelo.getPrecio() + "€";
									}
									eventModel.addRow(newRow);
								}
							}
							if (alojamientos != null) {
								for (Alojamiento alojamiento : alojamientos) {
									Object[] newrow = new Object[4];
									newrow[0] = alojamiento.getNombreHotel() + " - Id: " + alojamiento.getId();
									newrow[1] = "Alojamiento";
									newrow[2] = alojamiento.getFechaEntrada();
									newrow[3] = alojamiento.getPrecio() + "€";
									eventModel.addRow(newrow);
								}
							}
							if (actividades != null) {
								for (Actividad actividad : actividades) {
									Object[] newRow = new Object[4];
									newRow[0] = actividad.getNombre();
									newRow[1] = "Actividad";
									newRow[2] = actividad.getFecha();
									newRow[3] = actividad.getPrecio() + "€";
									eventModel.addRow(newRow);
								}
							}
						} else {
							eventModel.setRowCount(0);// vacia la tabla

							if (vuelos != null) {
								for (Vuelo vuelo : vuelos) {

									Object[] newRow = new Object[4];
									if (vuelo.getTipoVuelo().equalsIgnoreCase("IDA")) {
										newRow[0] = "Vuelo Ida - " + vuelo.getCodigo();
										newRow[1] = "Vuelo";
										newRow[2] = vuelo.getFecha();
										newRow[3] = vuelo.getPrecio() + "€";
									} else {
										newRow[0] = "Vuelo Vuelta - " + vuelo.getCodigo();
										newRow[1] = "Vuelo";
										newRow[2] = vuelo.getFecha();
										newRow[3] = vuelo.getPrecio() + "€";
									}
									eventModel.addRow(newRow);
								}
							}
							if (alojamientos != null) {
								for (Alojamiento alojamiento : alojamientos) {
									Object[] newrow = new Object[4];
									newrow[0] = alojamiento.getNombreHotel() + " - Id: " + alojamiento.getId();
									newrow[1] = "Alojamiento";
									newrow[2] = alojamiento.getFechaEntrada();
									newrow[3] = alojamiento.getPrecio() + "€";
									eventModel.addRow(newrow);
								}
							}
							if (actividades != null) {
								for (Actividad actividad : actividades) {
									Object[] newRow = new Object[4];
									newRow[0] = actividad.getNombre();
									newRow[1] = "Actividad";
									newRow[2] = actividad.getFecha();
									newRow[3] = actividad.getPrecio() + "€";
									eventModel.addRow(newRow);
								}
							}
						}
					}
				}
			}
		});

		btnBorrarViaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = travelTable.getSelectedRow();
				if (selectedRow != -1) {
					Controlador controlador = Controlador.getInstanceControlador();
					controlador.deleteActividadesPorIdViaje(viajes.get(selectedRow));
					controlador.deleteVuelosPorIdViaje(viajes.get(selectedRow));
					controlador.deleteAlojamientosPorIdViaje(viajes.get(selectedRow));
					controlador.deleteViajePorId(viajes.get(selectedRow), frame);
				}
			}
		});

		btnBorrarEvento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = eventTable.getSelectedRow();
				int columnaNombre = 0;
				int columnaTipo = 1;
				if (selectedRow != -1) {
					Controlador controlador = Controlador.getInstanceControlador();
					if (eventModel.getValueAt(selectedRow, columnaTipo).equals("Actividad")) {
						String nombre = eventModel.getValueAt(selectedRow, columnaNombre).toString();
						controlador.deleteActividadPorNombre(nombre, frame);
					} else if (eventModel.getValueAt(selectedRow, columnaTipo).equals("Vuelo")) {
						String nombre = eventModel.getValueAt(selectedRow, columnaNombre).toString();
						nombre = nombre.split(" - ")[1]; // lo primero que esté despues de " - "
						controlador.deleteVueloPorNombre(nombre, frame);
					} else if (eventModel.getValueAt(selectedRow, columnaTipo).equals("Alojamiento")) {
						String nombre = eventModel.getValueAt(selectedRow, columnaNombre).toString();
						int id = Integer.valueOf(nombre.split(" - Id: ")[1]); // lo primero que esté despues de " - id:"

						controlador.deleteAlojamientoPorId(id, frame);
					}
				}
			}
		});
	}

	public JPanel getPanel() {
		return panel;
	}
}
