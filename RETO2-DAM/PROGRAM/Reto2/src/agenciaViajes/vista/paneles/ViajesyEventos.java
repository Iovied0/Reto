
package agenciaViajes.vista.paneles;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import agenciaViajes.ViajesErrekamari;
import agenciaViajes.bbdd.pojos.Agencia;
import agenciaViajes.bbdd.pojos.Viaje;
import agenciaViajes.controlador.Controlador;

public class ViajesyEventos {

	private JPanel panel;

	private DefaultTableModel travelModel, eventModel;
	private JButton generateOfferButton;

	public ViajesyEventos(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1300, 900);
		panel.setLayout(null);
		Controlador controlador = Controlador.getInstanceControlador();
		Agencia agencia = controlador.getInstanceAgencia();
		JLabel labelTitulo = new JLabel("Viajes y Eventos de " + agencia.getNombre());

		labelTitulo.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
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
		ArrayList<Viaje> viajes = controlador.getViajesId(agencia);

		// Initialize travelModel and travelTable
		travelModel = new DefaultTableModel(
				new Object[] { "Nombre", "Tipo", "Días", "Fecha Inicio", "Fecha Fin", "País" }, 0);
		JTable travelTable;
		travelTable = new JTable(travelModel);

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
		eventModel = new DefaultTableModel(new Object[] { "Nombre", "Fecha", "Hora", "Lugar", "Descripción" }, 0);
		JTable eventTable;
		eventTable = new JTable(eventModel);
		JScrollPane eventScrollPane = new JScrollPane(eventTable);
		eventScrollPane.setBounds(200, 530, 860, 200);
		panel.add(eventScrollPane);

		// Buttons
		generateOfferButton = new JButton("Generar oferta cliente");
		generateOfferButton.setBounds(350, 800, 200, 25);
		panel.add(generateOfferButton);

		// Action Listeners
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
