package agenciaViajes.vista.paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import agenciaViajes.ViajesErrekamari;
import agenciaViajes.bbdd.pojos.*;
import agenciaViajes.controlador.Controlador;

public class NuevoAlojamiento {
	JPanel panel;

	public NuevoAlojamiento(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1300, 900);
		panel.setLayout(null);
		Controlador controlador = Controlador.getInstanceControlador();

		JLabel labelTitulo = new JLabel("INTRODUZCA LOS PARÁMETROS DEL NUEVO ALOJAMIENTO");
		labelTitulo.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
		labelTitulo.setBounds(25, 40, 615, 35);
		panel.add(labelTitulo);

		JLabel lblViaje = new JLabel("Seleccione Viaje");
		lblViaje.setBounds(33, 110, 150, 25);
		panel.add(lblViaje);

		JComboBox<String> comboViaje = new JComboBox<String>();
		ArrayList<Viaje> viajes = controlador.getViajesPorIdAgencia(controlador.getInstanceAgencia());
		for (Viaje viaje : viajes) {
			comboViaje.addItem(viaje.getNombreViaje());
		}
		comboViaje.setBackground(Color.WHITE);
		comboViaje.setBounds(178, 109, 150, 27);
		panel.add(comboViaje);

		// Nombre del evento
		JLabel nombreHotelLabel = new JLabel("Nombre evento: ");
		nombreHotelLabel.setBounds(33, 179, 150, 25);
		panel.add(nombreHotelLabel);

		JTextField nombreHotelField = new JTextField();
		nombreHotelField.setBounds(178, 178, 150, 27);
		panel.add(nombreHotelField);

		// Tipo de habitación
		JLabel tipoHabitacionLabel = new JLabel("Tipo de habitación:");
		tipoHabitacionLabel.setBounds(33, 245, 150, 25);
		panel.add(tipoHabitacionLabel);

		JComboBox<String> tipoDormitorioComboBox = new JComboBox<>();
		ArrayList<TipoDormitorio> tipoDormitorios = controlador.getTipoDormitorio();

		for (TipoDormitorio tipoDormitorio : tipoDormitorios) {
			// "Código - Descripción"
			String item = tipoDormitorio.getCodigo() + " - " + tipoDormitorio.getDescripcion();
			tipoDormitorioComboBox.addItem(item);
		}
		tipoDormitorioComboBox.setBounds(178, 245, 250, 25);
		panel.add(tipoDormitorioComboBox);

		// Ciudad
		JLabel ciudadLabel = new JLabel("Ciudad:");
		ciudadLabel.setBounds(33, 316, 150, 25);
		panel.add(ciudadLabel);

		JComboBox<String> ciudadComboBox = new JComboBox<>();
		ArrayList<Ciudad> ciudades = controlador.getCiudades();
		for (Ciudad ciudad : ciudades) {
			ciudadComboBox.addItem(ciudad.getNombre());
		}
		ciudadComboBox.setBounds(178, 316, 250, 25);
		panel.add(ciudadComboBox);

		// Precio
		JLabel labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(33, 368, 150, 25);
		panel.add(labelPrecio);

		JTextField textPrecio = new JTextField();
		textPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();

				boolean numeroValido = key >= '0' && key <= '9';
				boolean punto = key == '.';

				if (!(numeroValido || punto))
					e.consume();
			}
		});
		textPrecio.setBounds(178, 366, 150, 27);
		panel.add(textPrecio);

		// Fecha entrada
		JLabel labelFechaInicio = new JLabel("Fecha de entrada");
		labelFechaInicio.setBounds(33, 421, 150, 25);
		panel.add(labelFechaInicio);

		UtilDateModel modelInicio = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanelInicio = new JDatePanelImpl(modelInicio, p);
		JDatePickerImpl datePickerInicio = new JDatePickerImpl(datePanelInicio, new DateLabelFormatter());
		datePickerInicio.setBounds(178, 421, 150, 35);
		panel.add(datePickerInicio);

		// Fecha salida
		JLabel labelFechaFin = new JLabel("Fecha salida");
		labelFechaFin.setBounds(33, 479, 150, 25);
		panel.add(labelFechaFin);

		UtilDateModel modelFin = new UtilDateModel();
		JDatePanelImpl datePanelFin = new JDatePanelImpl(modelFin, p);
		JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanelFin, new DateLabelFormatter());
		datePickerFin.setBounds(178, 479, 250, 35);
		panel.add(datePickerFin);

		JButton btnConfirmar = new JButton("GUARDAR");
		btnConfirmar.setBounds(310, 810, 118, 23);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// validar nombre
				if (nombreHotelField.getText().replace(" ", "").length()== 0) {
					JOptionPane.showMessageDialog(null, "Introduce un nombre", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// validar precio

				if (textPrecio.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Introduce un precio", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if (validarPrecio(textPrecio.getText()) == false) {
					JOptionPane.showMessageDialog(null, "El formato del precio no es válido", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Obtener el viaje seleccionado
				Viaje viajeSeleccionado = viajes.get(comboViaje.getSelectedIndex());
				int idViaje = viajeSeleccionado.getId();

				// Obtener la ciudad seleccionada
				Ciudad ciudadSeleccionada = ciudades.get(ciudadComboBox.getSelectedIndex());
				int idCiudad = ciudadSeleccionada.getId();

				// Obtener el tipo de dormitorio seleccionado
				String itemSeleccionado = (String) tipoDormitorioComboBox.getSelectedItem();

				// Extraer el código (la parte antes del guion "-")
				String codigoTipoDormitorio = itemSeleccionado.split(" - ")[0];

				// Convertir fechas
				java.util.Date fechaUtil = modelInicio.getValue();
				if (fechaUtil == null) {
					JOptionPane.showMessageDialog(null, "Seleccione una fecha de entrada", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Date fechaSql1 = new Date(fechaUtil.getTime());

				java.util.Date fechaUtil2 = modelFin.getValue();
				if (fechaUtil2 == null) {
					JOptionPane.showMessageDialog(null, "Seleccione una fecha de salida", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Date fechaSql2 = new Date(fechaUtil2.getTime());

				// Convertir string a double
				Double precio = Double.parseDouble(textPrecio.getText());

				controlador.insertAlojamiento(nombreHotelField.getText(), fechaSql1, fechaSql2, precio, idViaje,
						idCiudad, codigoTipoDormitorio, frame);
				JOptionPane.showMessageDialog(null, "Actividad creada con exito", "Nueva actividad",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		panel.add(btnConfirmar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(609, 810, 118, 23);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Boton de cancelar que lleve a donde haga falta
				frame.gotoViajes();
			}
		});
		panel.add(btnCancelar);
	}

	public JPanel getPanel() {
		return panel;
	}

	public boolean validarPrecio(String precio) {
		// Validar precio
		String texto = precio;

		// Validar cada posición del carácter
		for (int posicion = 0; posicion < texto.length(); posicion++) {
			char caracterIntroducido = texto.charAt(posicion);

			boolean numeroValido = caracterIntroducido >= '0' && caracterIntroducido <= '9';
			boolean punto = caracterIntroducido == '.';

			if (!(numeroValido || punto)) {
				return false;
			}
			// evita que se pueda meter mas de un punto
			if (punto && texto.indexOf('.') != texto.lastIndexOf('.')) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("serial")
	class DateLabelFormatter extends AbstractFormatter {
		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parse(text);
		}

		@Override
		public String valueToString(Object value) {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}
			return "";
		}
	}
}