package agenciaViajes.vista.paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import agenciaViajes.ViajesErrekamari;
import agenciaViajes.bbdd.pojos.Viaje;
import agenciaViajes.controlador.Controlador;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;

public class NuevaActividad {
	JPanel panel;

	public NuevaActividad(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1300, 900);
		panel.setLayout(null);
		Controlador controlador = Controlador.getInstanceControlador();

		JLabel labelTitulo = new JLabel("INTRODUZCA LOS PARÁMETROS DE LA NUEVA ACTIVIDAD");
		labelTitulo.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
		labelTitulo.setBounds(25, 40, 600, 35);
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

		JLabel labelTrayecto = new JLabel("Nombre de actividad");
		labelTrayecto.setBounds(33, 179, 150, 25);
		panel.add(labelTrayecto);

		JTextField textNombre = new JTextField();
		textNombre.setBounds(178, 178, 150, 27);
		panel.add(textNombre);

		JLabel labelDescripcion = new JLabel("<html>Descripción de la <br/> actividad</html>");
		labelDescripcion.setBounds(33, 245, 150, 25);
		panel.add(labelDescripcion);

		JTextArea textDescripcion = new JTextArea();
		textDescripcion.setLineWrap(true); // Salto de línea automático
		textDescripcion.setBounds(178, 245, 250, 100);
		panel.add(textDescripcion);

		JLabel labelFechaInicio = new JLabel("Fecha del evento");
		labelFechaInicio.setBounds(33, 368, 150, 25);
		panel.add(labelFechaInicio);

		UtilDateModel modelInicio = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanelInicio = new JDatePanelImpl(modelInicio, p);
		JDatePickerImpl datePickerInicio = new JDatePickerImpl(datePanelInicio, new DateLabelFormatter());
		datePickerInicio.setBounds(178, 366, 150, 27);
		panel.add(datePickerInicio);

		JLabel labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(33, 421, 150, 25);
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
		textPrecio.setBounds(178, 421, 150, 25);
		panel.add(textPrecio);

		JButton btnConfirmar = new JButton("GUARDAR");
		btnConfirmar.setBounds(310, 810, 118, 23);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Viaje viaje = viajes.get(comboViaje.getSelectedIndex());

				java.util.Date fechaUtil = modelInicio.getValue();
				if (fechaUtil == null) {
					JOptionPane.showMessageDialog(null, "Seleccione una fecha de evento", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Date fechaSql = new Date(fechaUtil.getTime());

				// validar nombre
				if (textNombre.getText().replace(" ", "").length() == 0) {
					JOptionPane.showMessageDialog(null, "Introduce un nombre", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// validar descripcion
				if (textDescripcion.getText().replace(" ", "").length() == 0) {
					JOptionPane.showMessageDialog(null, "Introduce una descripción", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// validar precio

				if (textPrecio.getText().replace(" ", "").length() == 0) {
					JOptionPane.showMessageDialog(null, "Introduce un precio", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				} else if (validarPrecio(textPrecio.getText()) == false) {
					JOptionPane.showMessageDialog(null, "El formato del precio no es válido", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Convertir precio a double
				double precio = Double.parseDouble(textPrecio.getText());

				controlador.insertActividad(textNombre.getText(), textDescripcion.getText(), fechaSql, precio,
						viaje.getId(), frame);
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
}