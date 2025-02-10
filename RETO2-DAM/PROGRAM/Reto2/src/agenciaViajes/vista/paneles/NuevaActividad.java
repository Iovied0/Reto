package agenciaViajes.vista.paneles;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import agenciaViajes.ViajesErrekamari;
import agenciaViajes.controlador.Controlador;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NuevaActividad {
	JPanel panel;

	public NuevaActividad(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1300, 900);
		panel.setLayout(null);

		Controlador controlador = Controlador.getInstanceControlador();

		JLabel labelTitulo = new JLabel("INTRODUZCA LOS PARÁMETROS DE LA NUEVA ACTIVIDAD");
		labelTitulo.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
		labelTitulo.setBounds(15, 20, 600, 25);
		panel.add(labelTitulo);

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
				
				boolean numeroValido = key >= '0' && key <='9';
				boolean punto = key == '.';
				
				if(!(numeroValido || punto))
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