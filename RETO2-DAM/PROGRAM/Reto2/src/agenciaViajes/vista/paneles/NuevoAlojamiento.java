package agenciaViajes.vista.paneles;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import agenciaViajes.ViajesErrekamari;
import agenciaViajes.bbdd.pojos.Pais;
import agenciaViajes.bbdd.pojos.TipoViaje;
import agenciaViajes.controlador.Controlador;
import javax.swing.SpringLayout;
import java.awt.SystemColor;

public class NuevoAlojamiento {
	JPanel panel;
	private SpringLayout springLayout;
	private SpringLayout springLayout_1;

	public NuevoAlojamiento(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1300, 900);
		panel.setLayout(null);

		// Initialize SpringLayout
		springLayout_1 = new SpringLayout();
		panel.setLayout(springLayout_1);
		Controlador controlador = Controlador.getInstanceControlador();

		JLabel labelTitulo = new JLabel("INTRODUZCA LOS PARÁMETROS DEL NUEVO ALOJAMIENTO");
		springLayout_1.putConstraint(SpringLayout.WEST, labelTitulo, 32, SpringLayout.WEST, panel);
		labelTitulo.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
		labelTitulo.setBounds(25, 20, 700, 25);
		panel.add(labelTitulo);

		// Nombre del evento
		JLabel nombreEventoLabel = new JLabel("Nombre evento: ");
		springLayout_1.putConstraint(SpringLayout.NORTH, nombreEventoLabel, 123, SpringLayout.NORTH, panel);
		springLayout_1.putConstraint(SpringLayout.SOUTH, labelTitulo, -16, SpringLayout.NORTH, nombreEventoLabel);
		springLayout_1.putConstraint(SpringLayout.WEST, nombreEventoLabel, 10, SpringLayout.WEST, panel);
		nombreEventoLabel.setBounds(30, 100, 150, 25);
		panel.add(nombreEventoLabel);

		// Tipo de habitación
		JLabel tipoHabitacionLabel = new JLabel("Tipo de habitación:");
		springLayout_1.putConstraint(SpringLayout.WEST, tipoHabitacionLabel, 10, SpringLayout.WEST, panel);
		tipoHabitacionLabel.setBounds(30, 180, 150, 25);
		panel.add(tipoHabitacionLabel);

		String[] tiposHabitacion = { "Doble", "Individual", "Triple" };
		JComboBox<String> tipoHabitacionComboBox = new JComboBox<>(tiposHabitacion);
		springLayout_1.putConstraint(SpringLayout.NORTH, tipoHabitacionComboBox, -3, SpringLayout.NORTH,
				tipoHabitacionLabel);
		springLayout_1.putConstraint(SpringLayout.WEST, tipoHabitacionComboBox, 28, SpringLayout.EAST,
				tipoHabitacionLabel);
		tipoHabitacionComboBox.setSelectedItem("Doble"); // Doble por defecto
		tipoHabitacionComboBox.setBounds(175, 180, 150, 25);
		panel.add(tipoHabitacionComboBox);

		// Ciudad
		JLabel ciudadLabel = new JLabel("Ciudad:");
		springLayout_1.putConstraint(SpringLayout.WEST, ciudadLabel, 0, SpringLayout.WEST, nombreEventoLabel);
		JTextField ciudadField = new JTextField();
		springLayout_1.putConstraint(SpringLayout.EAST, ciudadField, 128, SpringLayout.EAST, nombreEventoLabel);
		panel.add(ciudadLabel);
		panel.add(ciudadField);

		// Precio

		JLabel labelPrecio = new JLabel("Precio");
		springLayout_1.putConstraint(SpringLayout.NORTH, labelPrecio, 257, SpringLayout.NORTH, panel);
		springLayout_1.putConstraint(SpringLayout.SOUTH, ciudadLabel, -16, SpringLayout.NORTH, labelPrecio);
		springLayout_1.putConstraint(SpringLayout.NORTH, ciudadField, -3, SpringLayout.NORTH, labelPrecio);
		springLayout_1.putConstraint(SpringLayout.WEST, ciudadField, 89, SpringLayout.EAST, labelPrecio);
		springLayout_1.putConstraint(SpringLayout.WEST, labelPrecio, 20, SpringLayout.WEST, panel);
		labelPrecio.setBounds(30, 300, 150, 25);
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
		textPrecio.setBounds(175, 300, 150, 25);
		panel.add(textPrecio);

		// Fecha entrada
		JLabel labelFechaInicio = new JLabel("Fecha de entrada");
		springLayout_1.putConstraint(SpringLayout.WEST, labelFechaInicio, 0, SpringLayout.WEST, nombreEventoLabel);
		labelFechaInicio.setBounds(25, 236, 150, 25);
		panel.add(labelFechaInicio);

		UtilDateModel modelInicio = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanelInicio = new JDatePanelImpl(modelInicio, p);
		JDatePickerImpl datePickerInicio = new JDatePickerImpl(datePanelInicio, new DateLabelFormatter());
		springLayout_1.putConstraint(SpringLayout.SOUTH, labelFechaInicio, 0, SpringLayout.SOUTH, datePickerInicio);
		springLayout_1.putConstraint(SpringLayout.SOUTH, tipoHabitacionLabel, -17, SpringLayout.NORTH,
				datePickerInicio);
		springLayout_1.putConstraint(SpringLayout.NORTH, datePickerInicio, 79, SpringLayout.SOUTH, labelTitulo);
		springLayout_1.putConstraint(SpringLayout.WEST, datePickerInicio, 98, SpringLayout.WEST, panel);
		springLayout_1.putConstraint(SpringLayout.NORTH, datePickerInicio.getJFormattedTextField(), 0,
				SpringLayout.NORTH, datePickerInicio);
		springLayout_1.putConstraint(SpringLayout.WEST, datePickerInicio.getJFormattedTextField(), 10,
				SpringLayout.WEST, datePickerInicio);
		springLayout = (SpringLayout) datePickerInicio.getLayout();
		datePickerInicio.setBounds(175, 230, 150, 25);
		panel.add(datePickerInicio);

		// Fecha salida
		JLabel labelFechaFin = new JLabel("Fecha salida");
		springLayout_1.putConstraint(SpringLayout.WEST, labelFechaFin, 0, SpringLayout.WEST, nombreEventoLabel);
		labelFechaFin.setBounds(25, 349, 150, 25);
		panel.add(labelFechaFin);

		UtilDateModel modelFin = new UtilDateModel();
		JDatePanelImpl datePanelFin = new JDatePanelImpl(modelFin, p);
		JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanelFin, new DateLabelFormatter());
		springLayout_1.putConstraint(SpringLayout.SOUTH, labelFechaFin, 0, SpringLayout.SOUTH, datePickerFin);
		springLayout_1.putConstraint(SpringLayout.NORTH, datePickerFin, 90, SpringLayout.SOUTH, datePickerInicio);
		springLayout_1.putConstraint(SpringLayout.WEST, datePickerFin, 0, SpringLayout.WEST, datePickerInicio);
		datePickerFin.setBounds(175, 349, 150, 25);
		panel.add(datePickerFin);

		JButton btnConfirmar = new JButton("Buscar alojamiento");
		btnConfirmar.setBackground(SystemColor.activeCaption);
		springLayout_1.putConstraint(SpringLayout.NORTH, btnConfirmar, -4, SpringLayout.NORTH, ciudadLabel);
		springLayout_1.putConstraint(SpringLayout.WEST, btnConfirmar, 340, SpringLayout.EAST, ciudadLabel);
		// el tipo de habitación, la ciudad y las fechas
		// seleccionadas.
		btnConfirmar.setBounds(120, 600, 150, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener los valores seleccionados
				String tipoHabitacion = (String) tipoHabitacionComboBox.getSelectedItem();
				String ciudad = ciudadField.getText();
				Date fechaEntrada = (Date) datePickerInicio.getModel().getValue();
				Date fechaSalida = (Date) datePickerFin.getModel().getValue();

				// Formatear las fechas al formato requerido por Booking (yyyy-MM-dd)
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String fechaEntradaStr = dateFormat.format(fechaEntrada);
				String fechaSalidaStr = dateFormat.format(fechaSalida);

				// Construir la URL de Booking
				String url = String.format(
						"https://www.booking.com/searchresults.es.html?checkin=%s&checkout=%s&ss=%s&room_type=%s",
						fechaEntradaStr, fechaSalidaStr, ciudad, tipoHabitacion);

				// Abrir la URL en el navegador web predeterminado
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (java.io.IOException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(panel, "No se pudo abrir el navegador.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel.add(btnConfirmar);

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
}