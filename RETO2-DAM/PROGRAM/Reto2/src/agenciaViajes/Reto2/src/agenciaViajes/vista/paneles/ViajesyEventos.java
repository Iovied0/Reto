package agenciaViajes.vista.paneles;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import agenciaViajes.ViajesErrekamari;

public class ViajesyEventos {

	private JPanel panel;
	private JTable travelTable, eventTable;
	private DefaultTableModel travelModel, eventModel;
	private JButton newTravelButton, newEventButton, generateOfferButton, disconnectButton;

	public ViajesyEventos(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
		panel = new JPanel();
		panel.setBounds(0, 0, 900, 700);
		panel.setLayout(null);

		JLabel labelTitulo = new JLabel("GESTIÓN DE VIAJES Y EVENTOS");
		labelTitulo.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
		labelTitulo.setBounds(15, 20, 600, 25);
		panel.add(labelTitulo);

		// Initialize travelModel and travelTable
		travelModel = new DefaultTableModel(
				new Object[] { "Nombre", "Tipo", "Días", "Fecha Inicio", "Fecha Fin", "País" }, 0);
		travelTable = new JTable(travelModel);
		JScrollPane travelScrollPane = new JScrollPane(travelTable);
		travelScrollPane.setBounds(10, 60, 860, 200);
		panel.add(travelScrollPane);

		// Initialize eventModel and eventTable
		eventModel = new DefaultTableModel(new Object[] { "Nombre", "Fecha", "Hora", "Lugar", "Descripción" }, 0);
		eventTable = new JTable(eventModel);
		JScrollPane eventScrollPane = new JScrollPane(eventTable);
		eventScrollPane.setBounds(10, 280, 860, 200);
		panel.add(eventScrollPane);

		// Buttons
		newTravelButton = new JButton("Nuevo Viaje");
		newTravelButton.setBounds(10, 500, 150, 25);
		panel.add(newTravelButton);

		newEventButton = new JButton("Nuevo Evento");
		newEventButton.setBounds(180, 500, 150, 25);
		panel.add(newEventButton);

		generateOfferButton = new JButton("Generar oferta cliente");
		generateOfferButton.setBounds(350, 500, 200, 25);
		panel.add(generateOfferButton);

		disconnectButton = new JButton("Desconectar");
		disconnectButton.setBounds(570, 500, 150, 25);
		panel.add(disconnectButton);

		// Action Listeners
		newTravelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.gotoNuevoViaje();
			}
		});

		newEventButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.gotoNuevoEvento();

			}
		});

		generateOfferButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = travelTable.getSelectedRow();
				if (selectedRow != -1) {
					// Implementar lógica para generar oferta el txt 
					JOptionPane.showMessageDialog(null, "Funcionalidad de generar oferta no implementada.");
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecciona un viaje primero.");
				}
			}
		});

		disconnectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Desconectado correctamente.");
				frame.gotoFormLogin();
			}
		});

		// Mouse Listener for eventTable

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

						Object[] fields = { "Nombre:", nombreField, "Tipo:", tipoField, "Días:", diasField,
								"Fecha inicio:", fechaInicioField, "Fecha fin:", fechaFinField, "País:", paisField };

						int option = JOptionPane.showConfirmDialog(null, fields, "Editar Viaje",
								JOptionPane.OK_CANCEL_OPTION);
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

		eventTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int selectedRow = eventTable.getSelectedRow();
					if (selectedRow != -1) {
						// Implementar lógica para editar evento
						JOptionPane.showMessageDialog(null, "Funcionalidad de editar evento no implementada.");
					}
				}
			}
		});
	}

	public JPanel getPanel() {
		return panel;
	}
}
