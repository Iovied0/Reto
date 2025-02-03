package agenciaViajes.vista.paneles;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViajesyEventos {

	private static final long serialVersionUID = 1L;
	private JTable travelTable, eventTable;
	private DefaultTableModel travelModel, eventModel;
	private JButton newTravelButton, newEventButton, generateOfferButton, disconnectButton;

	public ViajesyEventos1() {
			setTitle("VIAJES BILBAO EXPRESS ");
			setSize(800, 600);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// Panel de botones
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBounds(0, 854, 784, 33);
			getContentPane().add(buttonPanel);
			newTravelButton = new JButton("Nuevo Viaje");
			newTravelButton.setBounds(624, 40, 101, 23);
			getContentPane().add(newTravelButton);
			generateOfferButton = new JButton("Generar oferta cliente");
			generateOfferButton.setBounds(204, 456, 139, 23);
			getContentPane().add(generateOfferButton);
			disconnectButton = new JButton("Desconectar");
			disconnectButton.setBounds(632, 456, 93, 23);
			getContentPane().add(disconnectButton);
			newEventButton = new JButton("Nuevo Evento");
			newEventButton.setBounds(624, 257, 101, 23);
			getContentPane().add(newEventButton);

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new ViajesyEventos().setVisible(true);
				}
			});

			disconnectButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        // Mostramos mensaje de confirmación
			        JOptionPane.showMessageDialog(null, "Desconectado correctamente.");

			        // Cerramos la ventana actual
			        JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(disconnectButton);
			        ventanaActual.dispose();

			        // Abrimos la ventana de bienvenida
			        Bienvenida bienvenida = new Bienvenida(); // Ventana de Bienvenida
			        bienvenida.setVisible(true);
			    }
			});
			
			generateOfferButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int selectedRow = travelTable.getSelectedRow();
					if (selectedRow != -1) {  // Verificamos que haya una fila seleccionada

						// Obtenmos los datos del viaje seleccionado
						String nombreViaje = (String) travelModel.getValueAt(selectedRow, 0);
						String tipoViaje = (String) travelModel.getValueAt(selectedRow, 1);
						String dias = (String) travelModel.getValueAt(selectedRow, 2);
						String fechaInicio = (String) travelModel.getValueAt(selectedRow, 3);
						String fechaFin = (String) travelModel.getValueAt(selectedRow, 4);
						String pais = (String) travelModel.getValueAt(selectedRow, 5);

						// Creamos el contenido del archivo de oferta
						StringBuilder oferta = new StringBuilder();
						oferta.append("Oferta de Viaje\n");
						oferta.append("Nombre: ").append(nombreViaje).append("\n");
						oferta.append("Tipo: ").append(tipoViaje).append("\n");
						oferta.append("Días: ").append(dias).append("\n");
						oferta.append("Fecha inicio: ").append(fechaInicio).append("\n");
						oferta.append("Fecha fin: ").append(fechaFin).append("\n");
						oferta.append("País: ").append(pais).append("\n\n");
						oferta.append("Eventos:\n");

						// Agregamos los eventos asociados al viaje
						for (int i = 0; i < eventModel.getRowCount(); i++) {
							oferta.append("Nombre evento: ").append(eventModel.getValueAt(i, 0)).append("\n");
							oferta.append("Tipo: ").append(eventModel.getValueAt(i, 1)).append("\n");
							oferta.append("Fecha: ").append(eventModel.getValueAt(i, 2)).append("\n");
							oferta.append("Precio: ").append(eventModel.getValueAt(i, 3)).append("\n\n");
						}

						// Guardamos la oferta en un archivo de texto
						try (java.io.FileWriter fileWriter = new java.io.FileWriter("oferta.txt")) {
							fileWriter.write(oferta.toString());
							JOptionPane.showMessageDialog(null, "Oferta generada correctamente en oferta.txt");
						} catch (java.io.IOException ex) {
							JOptionPane.showMessageDialog(null, "Error al generar la oferta: " + ex.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Por favor, selecciona un viaje primero.");
					}
				}
			});

			// Listeners
			newTravelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Creamos e iniciamos la ventana NuevoViaje
					NuevoViaje nuevoViaje = new NuevoViaje(); // ventana "NuevoViaje" aparte
					nuevoViaje.setVisible(true);
				}
			});

			travelTable.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			        if (e.getClickCount() == 2) { // Doble clic
			            int selectedRow = travelTable.getSelectedRow();
			            if (selectedRow != -1) { // Verificamos que haya una fila seleccionada
			                // Obtenemos datos del viaje seleccionado
			                String nombre = (String) travelTable.getValueAt(selectedRow, 0);
			                String tipo = (String) travelTable.getValueAt(selectedRow, 1);
			                String dias = (String) travelTable.getValueAt(selectedRow, 2);
			                String fechaInicio = (String) travelTable.getValueAt(selectedRow, 3);
			                String fechaFin = (String) travelTable.getValueAt(selectedRow, 4);
			                String pais = (String) travelTable.getValueAt(selectedRow, 5);

			                // Creamos campos de entrada con los valores actuales
			                JTextField nombreField = new JTextField(nombre);
			                JTextField tipoField = new JTextField(tipo);
			                JTextField diasField = new JTextField(dias);
			                JTextField fechaInicioField = new JTextField(fechaInicio);
			                JTextField fechaFinField = new JTextField(fechaFin);
			                JTextField paisField = new JTextField(pais);

			                Object[] fields = { 
			                    "Nombre:", nombreField, 
			                    "Tipo:", tipoField, 
			                    "Días:", diasField, 
			                    "Fecha inicio:", fechaInicioField, 
			                    "Fecha fin:", fechaFinField, 
			                    "País:", paisField 
			                };

			                int option = JOptionPane.showConfirmDialog(null, fields, "Editar Viaje", JOptionPane.OK_CANCEL_OPTION);
			                if (option == JOptionPane.OK_OPTION) {
			                    // Actualizamos los valores en la tabla
			                    travelTable.setValueAt(nombreField.getText(), selectedRow, 0);
			                    travelTable.setValueAt(tipoField.getText(), selectedRow, 1);
			                    travelTable.setValueAt(diasField.getText(), selectedRow, 2);
			                    travelTable.setValueAt(fechaInicioField.getText(), selectedRow, 3);
			                    travelTable.setValueAt(fechaFinField.getText(), selectedRow, 4);
			                    travelTable.setValueAt(paisField.getText(), selectedRow, 5);
			                }
			            }
			        }
			    }
			});


			newEventButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Crear e iniciar la ventana NuevoEvento
					NuevoEvento nuevoEvento = new NuevoEvento();  // ventana "NuevoEvento" aparte
					nuevoEvento.setVisible(true);
				}
			});
			}

		/**
		 * Agrega un listener para editar eventos
		 * 
		 * @param e Evento del mouse al hacer doble clic en una fila
		 */
		eventTable.addMouseListener(new MouseAdapter() {

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) { // Doble clic
			int selectedRow = eventTable.getSelectedRow();
			if (selectedRow != -1) { // Verificamos que haya una fila seleccionada
				// Obtenemos datos del evento seleccionado
				String nombre = (String) eventTable.getValueAt(selectedRow, 0);
				String fecha = (String) eventTable.getValueAt(selectedRow, 1);
				String hora = (String) eventTable.getValueAt(selectedRow, 2);
				String lugar = (String) eventTable.getValueAt(selectedRow, 3);
				String descripcion = (String) eventTable.getValueAt(selectedRow, 4);

				// Creamos campos de entrada con los valores actuales
				JTextField nombreField = new JTextField(nombre);
				JTextField fechaField = new JTextField(fecha);
				JTextField horaField = new JTextField(hora);
				JTextField lugarField = new JTextField(lugar);
				JTextField descripcionField = new JTextField(descripcion);

				Object[] fields = { "Nombre:", nombreField, "Fecha:", fechaField, "Hora:", horaField, "Lugar:",
						lugarField, "Descripción:", descripcionField };

				int option = JOptionPane.showConfirmDialog(null, fields, "Editar Evento", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					// Actualizamos los valores en la tabla
					eventTable.setValueAt(nombreField.getText(), selectedRow, 0);
					eventTable.setValueAt(fechaField.getText(), selectedRow, 1);
					eventTable.setValueAt(horaField.getText(), selectedRow, 2);
					eventTable.setValueAt(lugarField.getText(), selectedRow, 3);
					eventTable.setValueAt(descripcionField.getText(), selectedRow, 4);
				}
			}
		}
	}

	});

	//////// Main para ir probando el funcionamiento del panel \\\\\\\\\\
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ViajesyEventos().setVisible(true);
			}
		});
	}

}
