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
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import agenciaViajes.ViajesErrekamari;
import agenciaViajes.bbdd.pojos.TipoViaje;
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
		labelTitulo.setBounds(30, 50, 150, 25);
		panel.add(labelTitulo);

		JComboBox<String> comboViaje = new JComboBox<>();
		ArrayList<Viaje> viajes = controlador.getViajes();
		for (Viaje viaje : viajes) {
			comboViaje.addItem(viaje.getNombreViaje());
		}
		comboViaje.setBounds(175, 50, 150, 25);
		panel.add(comboViaje);

		JComboBox<String> comboTipoViaje = new JComboBox<>();
		ArrayList<TipoViaje> tiposViaje = controlador.getTiposViaje();
		for (TipoViaje tipoViaje : tiposViaje) {
			comboTipoViaje.addItem(tipoViaje.getDescripcion());
		}
		comboTipoViaje.setBounds(175, 500, 190, 25);
		panel.add(comboTipoViaje);

		JLabel labelTrayecto = new JLabel("Nombre de actividad");
		labelTrayecto.setBounds(30, 100, 150, 25);
		panel.add(labelTrayecto);

		JTextField textNombre = new JTextField();
		textNombre.setBounds(175, 100, 150, 25);
		panel.add(textNombre);

		JLabel labelDescripcion = new JLabel("<html>Descripción de la <br/> actividad</html>");
		labelDescripcion.setBounds(30, 150, 150, 25);
		panel.add(labelDescripcion);

		JTextArea textDescripcion = new JTextArea();
		textDescripcion.setBounds(175, 140, 400, 100);
		panel.add(textDescripcion);

		JLabel labelFechaInicio = new JLabel("Fecha del evento");
		labelFechaInicio.setBounds(30, 260, 150, 25);
		panel.add(labelFechaInicio);

		UtilDateModel modelInicio = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanelInicio = new JDatePanelImpl(modelInicio, p);
		JDatePickerImpl datePickerInicio = new JDatePickerImpl(datePanelInicio, new DateLabelFormatter());
		datePickerInicio.setBounds(175, 260, 150, 25);
		panel.add(datePickerInicio);

		JLabel labelPrecio = new JLabel("Precio");
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

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(120, 400, 150, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Pendiente hacer que se guarden los datos en la base de datos
				Viaje viaje = viajes.get(comboViaje.getSelectedIndex());

				java.util.Date fechaUtil = modelInicio.getValue();
				Date fechaSql = new Date(fechaUtil.getTime());

				
				controlador.insertActividad(textNombre.getText(), textDescripcion.getText(), fechaSql,
						textPrecio.getText(), viaje.getId(), frame);
				JOptionPane.showMessageDialog(null, "Actividad creada con exito", "Nueva actividad",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(btnConfirmar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(400, 400, 150, 25);
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