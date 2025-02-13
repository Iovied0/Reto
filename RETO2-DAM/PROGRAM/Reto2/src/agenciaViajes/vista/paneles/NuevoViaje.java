
package agenciaViajes.vista.paneles;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import agenciaViajes.ViajesErrekamari;
import agenciaViajes.bbdd.pojos.Pais;
import agenciaViajes.bbdd.pojos.TipoViaje;
import agenciaViajes.bbdd.pojos.Viaje;
import agenciaViajes.controlador.Controlador;

public class NuevoViaje {

	public JPanel panel;
	public Controlador controlador;

	public NuevoViaje(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1300, 900);
		panel.setLayout(null);

		controlador = Controlador.getInstanceControlador();

		JLabel labelTitulo = new JLabel("INTRODUZCA LOS PARÁMETROS DEL NUEVO VIAJE");
		labelTitulo.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
		labelTitulo.setBounds(15, 20, 600, 25);
		panel.add(labelTitulo);

		JLabel labelNombre = new JLabel("Nombre del viaje");
		labelNombre.setBounds(30, 100, 150, 25);
		panel.add(labelNombre);

		ArrayList<Viaje> viajes = controlador.getViajes();

		JTextField txtNombreViaje = new JTextField();
		txtNombreViaje.setBounds(175, 100, 150, 25);
		panel.add(txtNombreViaje);

		JLabel labelDescripcion = new JLabel("Descripción del viaje");
		labelDescripcion.setBounds(30, 396, 150, 25);
		panel.add(labelDescripcion);

		JTextArea textDescripcion = new JTextArea();
		textDescripcion.setBounds(175, 396, 400, 100);
		textDescripcion.setLineWrap(true); // Salto de línea automático
		panel.add(textDescripcion);

		JLabel labelFechaInicio = new JLabel("Fecha de inicio");
		labelFechaInicio.setBounds(30, 209, 150, 25);
		panel.add(labelFechaInicio);

		UtilDateModel modelInicio = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanelInicio = new JDatePanelImpl(modelInicio, p);
		JDatePickerImpl datePickerInicio = new JDatePickerImpl(datePanelInicio, new DateLabelFormatter());
		datePickerInicio.setBounds(175, 209, 150, 25);
		panel.add(datePickerInicio);

		JLabel labelFechaFin = new JLabel("Fecha de fin");
		labelFechaFin.setBounds(30, 259, 150, 25);
		panel.add(labelFechaFin);

		UtilDateModel modelFin = new UtilDateModel();
		JDatePanelImpl datePanelFin = new JDatePanelImpl(modelFin, p);
		JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanelFin, new DateLabelFormatter());
		datePickerFin.setBounds(175, 259, 150, 25);
		panel.add(datePickerFin);

		JLabel labelDuracion = new JLabel("Duración (días)");
		labelDuracion.setBounds(30, 303, 150, 25);
		panel.add(labelDuracion);

		JTextField textDuracion = new JTextField();
		textDuracion.setBounds(175, 303, 150, 25);
		textDuracion.setEditable(false);
		panel.add(textDuracion);

		ChangeListener changeListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				java.util.Date fechaInicioUtil = modelInicio.getValue();
				java.util.Date fechaFinUtil = modelFin.getValue();

				if (fechaInicioUtil != null && fechaFinUtil != null) {
					long duracion = (fechaFinUtil.getTime() - fechaInicioUtil.getTime()) / (1000 * 60 * 60 * 24);
					textDuracion.setText(String.valueOf(duracion));
				} else {
					textDuracion.setText("");
				}
			}
		};

		datePickerInicio.getModel().addChangeListener(changeListener);
		datePickerFin.getModel().addChangeListener(changeListener);

		JLabel labelServicios = new JLabel("Servicios no incluidos");
		labelServicios.setBounds(30, 525, 150, 25);
		panel.add(labelServicios);

		JTextArea textServicios = new JTextArea();
		textServicios.setBounds(175, 525, 400, 100);
		textServicios.setLineWrap(true); // Salto de línea automático
		panel.add(textServicios);

		JLabel labelTipoViaje = new JLabel("Tipo de viaje");
		labelTipoViaje.setBounds(30, 155, 150, 25);
		panel.add(labelTipoViaje);

		JComboBox<String> comboTipoViaje = new JComboBox<>();
		ArrayList<TipoViaje> tiposViaje = controlador.getTiposViaje();
		for (TipoViaje tipoViaje : tiposViaje) {
			comboTipoViaje.addItem(tipoViaje.getDescripcion());
		}
		comboTipoViaje.setBounds(175, 155, 190, 25);
		panel.add(comboTipoViaje);

		JLabel labelPais = new JLabel("País destino");
		labelPais.setBounds(30, 350, 150, 25);
		panel.add(labelPais);

		JComboBox<String> comboPais = new JComboBox<>();
		ArrayList<Pais> paises = controlador.mostrarPaises();
		for (Pais pais : paises) {
			comboPais.addItem(pais.getNombre());
		}
		comboPais.setBounds(175, 350, 190, 25);
		panel.add(comboPais);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(131, 722, 150, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Comprobamos que no haya campos vacíos
				if (txtNombreViaje.getText().replace(" ", "").equals("")
						|| textDescripcion.getText().replace(" ", "").equals("")
						|| textServicios.getText().replace(" ", "").equals("") || modelInicio.getValue() == null
						|| modelFin.getValue() == null || textDuracion.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "Campos vacíos",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Comprobamos que el nombre del viaje no se encuentre en uso
				for (Viaje viaje : viajes) {
					if (txtNombreViaje.getText().equals(viaje.getNombreViaje())) {
						JOptionPane.showMessageDialog(null, "El nombre proporcionado ya está en uso actualmente",
								"Nombre en uso", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

			}
		});
		panel.add(btnConfirmar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(411, 722, 150, 25);
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

	/**
	 * Clase que permite formatear las fechas en el formato yyyy-MM-dd
	 */
	@SuppressWarnings("serial")
	class DateLabelFormatter extends AbstractFormatter {
		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		// Convierte un texto en un objeto de tipo Calendar
		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parse(text);
		}

		// Cuando se selecciona una fecha, se muestra en el campo de texto
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
