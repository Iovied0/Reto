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
import agenciaViajes.bbdd.pojos.TipoDormitorio;
import agenciaViajes.bbdd.pojos.TipoViaje;
import agenciaViajes.bbdd.pojos.Viaje;
import agenciaViajes.controlador.Controlador;
import javax.swing.SpringLayout;
import java.awt.SystemColor;

public class NuevoAlojamiento {
	JPanel panel;

	public NuevoAlojamiento(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1300, 900);
		panel.setLayout(null);
		Controlador controlador = Controlador.getInstanceControlador();

		JLabel labelTitulo = new JLabel("INTRODUZCA LOS PARÁMETROS DEL NUEVO ALOJAMIENTO");
		labelTitulo.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
		labelTitulo.setBounds(25, 20, 700, 25);
		panel.add(labelTitulo);

		JComboBox<String> comboViaje = new JComboBox<>();
		ArrayList<Viaje> viajes = controlador.getViajes();
		for (Viaje viaje : viajes) {
			comboViaje.addItem(viaje.getNombreViaje());
		}
		comboViaje.setBounds(175, 50, 150, 25);
		panel.add(comboViaje);

		// Nombre del evento
		JLabel nombreEventoLabel = new JLabel("Nombre evento: ");
		nombreEventoLabel.setBounds(30, 100, 150, 25);
		panel.add(nombreEventoLabel);

		JTextField nombreEventoField = new JTextField();
		nombreEventoField.setBounds(175, 100, 150, 25);
		panel.add(nombreEventoField);

		 // Tipo de habitación
        JLabel tipoHabitacionLabel = new JLabel("Tipo de habitación:");
        tipoHabitacionLabel.setBounds(30, 180, 150, 25);
        panel.add(tipoHabitacionLabel);

        JComboBox<String> tipoDormitorioComboBox = new JComboBox<>();
        ArrayList<TipoDormitorio> tipoDormitorios = controlador.getTipoDormitorio();
        for (TipoDormitorio tipoDormitorio : tipoDormitorios) {
            tipoDormitorioComboBox.addItem(tipoDormitorio.getDescripcion());
        }
        tipoDormitorioComboBox.setBounds(175, 180, 150, 25);
        panel.add(tipoDormitorioComboBox);

		// Ciudad
		JLabel ciudadLabel = new JLabel("Ciudad:");
		ciudadLabel.setBounds(30, 220, 150, 25);
		panel.add(ciudadLabel);

		JTextField ciudadField = new JTextField();
		ciudadField.setBounds(175, 220, 150, 25);
		panel.add(ciudadField);

		// Precio
		JLabel labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(30, 260, 150, 25);
		panel.add(labelPrecio);

		JTextField textPrecio = new JTextField();
		textPrecio.setBounds(175, 260, 150, 25);
		panel.add(textPrecio);

		// Fecha entrada
		JLabel labelFechaInicio = new JLabel("Fecha de entrada");
		labelFechaInicio.setBounds(30, 300, 150, 25);
		panel.add(labelFechaInicio);

		UtilDateModel modelInicio = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanelInicio = new JDatePanelImpl(modelInicio, p);
		JDatePickerImpl datePickerInicio = new JDatePickerImpl(datePanelInicio, new DateLabelFormatter());
		datePickerInicio.setBounds(175, 300, 150, 25);
		panel.add(datePickerInicio);

		// Fecha salida
		JLabel labelFechaFin = new JLabel("Fecha salida");
		labelFechaFin.setBounds(30, 340, 150, 25);
		panel.add(labelFechaFin);

		UtilDateModel modelFin = new UtilDateModel();
		JDatePanelImpl datePanelFin = new JDatePanelImpl(modelFin, p);
		JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanelFin, new DateLabelFormatter());
		datePickerFin.setBounds(175, 340, 150, 25);
		panel.add(datePickerFin);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(SystemColor.activeCaption);
		btnConfirmar.setBounds(175, 400, 150, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				java.util.Date fechaUtil = modelInicio.getValue();
				Date fechaSql = new Date(fechaUtil.getTime());

//				controlador.insertAlojamiento(nombreEventoField.getText(), tipoDormitorioComboBox.getSelectedItem().toString(),
//						ciudadField.getText(), textPrecio.getText(), modelInicio.getValue(), modelFin.getValue(),
//						frame);
//				JOptionPane.showMessageDialog(null, "Actividad creada con exito", "Nueva actividad",
//						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		panel.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(195, 810, 89, 23);
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
}