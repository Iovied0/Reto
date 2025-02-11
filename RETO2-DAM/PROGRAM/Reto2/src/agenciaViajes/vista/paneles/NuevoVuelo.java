package agenciaViajes.vista.paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
import agenciaViajes.bbdd.pojos.Aerolineas;
import agenciaViajes.bbdd.pojos.Aeropuerto;
import agenciaViajes.bbdd.pojos.Pais;
import agenciaViajes.bbdd.pojos.TipoViaje;
import agenciaViajes.bbdd.pojos.TiposAgencia;
import agenciaViajes.bbdd.pojos.Viaje;
import agenciaViajes.controlador.Controlador;

public class NuevoVuelo {
	JPanel panel;

	public NuevoVuelo(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1300, 900);
		panel.setLayout(null);

		Controlador controlador = Controlador.getInstanceControlador();

		JLabel labelTitulo = new JLabel("INTRODUZCA LOS PARÁMETROS DEL NUEVO VUELO");
		labelTitulo.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
		labelTitulo.setBounds(15, 20, 600, 25);
		panel.add(labelTitulo);

		JComboBox<String> comboViaje = new JComboBox<>();
		ArrayList<Viaje> viajes = controlador.getViajes();
		for (Viaje viaje : viajes) {
			comboViaje.addItem(viaje.getNombreViaje());
		}
		comboViaje.setBounds(175, 50, 150, 25);
		panel.add(comboViaje);
		
		JLabel labelTrayecto = new JLabel("Trayecto");
		labelTrayecto.setBounds(30, 100, 150, 25);
		panel.add(labelTrayecto);

		JComboBox<String> trayectoCombo = new JComboBox<String>();
		trayectoCombo.addItem("IDA");
		trayectoCombo.addItem("IDA Y VUELTA");
		trayectoCombo.setBackground(Color.white);
		trayectoCombo.setBounds(175, 100, 150, 25);
		panel.add(trayectoCombo);

		JLabel AeropuertoOrigen = new JLabel("Aeropuerto Origen");
		AeropuertoOrigen.setBounds(30, 140, 150, 25);
		panel.add(AeropuertoOrigen);

		JComboBox<String> AeropuertoOrigenCombo = new JComboBox<String>();
		ArrayList<Aeropuerto> aeropuertosOrigen = controlador.getAeropuertos();
		for (Aeropuerto aeropuerto : aeropuertosOrigen) {
			AeropuertoOrigenCombo.addItem(aeropuerto.getCiudad().getNombre() + " (" + aeropuerto.getCodigo() + ")");
		}
		AeropuertoOrigenCombo.setBackground(Color.white);
		AeropuertoOrigenCombo.setBounds(175, 140, 250, 25);
		panel.add(AeropuertoOrigenCombo);

		JLabel AeropuertoDestino = new JLabel("Aeropuerto Destino");
		AeropuertoDestino.setBounds(30, 200, 150, 25);
		panel.add(AeropuertoDestino);

		JComboBox<String> AeropuertoDestinoCombo = new JComboBox<String>();
		ArrayList<Aeropuerto> aeropuertosDestino = controlador.getAeropuertos();
		for (Aeropuerto aeropuerto : aeropuertosDestino) {
			AeropuertoDestinoCombo.addItem(aeropuerto.getCiudad().getNombre() + " (" + aeropuerto.getCodigo() + ")");
		}
		AeropuertoDestinoCombo.setBackground(Color.white);
		AeropuertoDestinoCombo.setBounds(175, 200, 250, 25);
		panel.add(AeropuertoDestinoCombo);

		JLabel labelFechaInicio = new JLabel("Fecha Ida");
		labelFechaInicio.setBounds(30, 260, 150, 25);
		panel.add(labelFechaInicio);

		UtilDateModel modelIda = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanelIda = new JDatePanelImpl(modelIda, p);
		JDatePickerImpl datePickerIda = new JDatePickerImpl(datePanelIda, new DateLabelFormatter());
		datePickerIda.setBounds(175, 260, 150, 27);
		panel.add(datePickerIda);

		JLabel lblCodigoVueloIda = new JLabel("Codigo Vuelo");
		lblCodigoVueloIda.setBounds(30, 310, 150, 25);
		panel.add(lblCodigoVueloIda);
		JTextField txtCodigoVueloIda = new JTextField();
		txtCodigoVueloIda.setBounds(175, 310, 150, 25);
		panel.add(txtCodigoVueloIda);
//		JLabel labelFechaFin = new JLabel("Fecha de fin");
//		labelFechaFin.setBounds(30, 300, 150, 25);
//		panel.add(labelFechaFin);
//
//		UtilDateModel modelFin = new UtilDateModel();
//		JDatePanelImpl datePanelFin = new JDatePanelImpl(modelFin, p);
//		JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanelFin, new DateLabelFormatter());
//		datePickerFin.setBounds(175, 300, 150, 25);
//		panel.add(datePickerFin);

		JLabel labelAerolinea = new JLabel("Aerolinea");
		labelAerolinea.setBounds(30, 355, 150, 25);
		panel.add(labelAerolinea);

		JComboBox<String> comboAerolineasIda = new JComboBox<String>();
		ArrayList<Aerolineas> AerolineasIda = controlador.getAerolineas();
		for (Aerolineas aerolineas : AerolineasIda) {
			comboAerolineasIda.addItem(aerolineas.getNombre());
		}
		comboAerolineasIda.setBackground(Color.white);
		comboAerolineasIda.setBounds(175, 355, 250, 25);
		panel.add(comboAerolineasIda);

//		ChangeListener changeListener = new ChangeListener() {
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				Date fechaInicio = (Date) datePickerInicio.getModel().getValue();
//				Date fechaFin = (Date) datePickerFin.getModel().getValue();
//				if (fechaInicio != null && fechaFin != null) {
//					long duracion = (fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24);
//					textDuracion.setText(String.valueOf(duracion));
//				} else {
//					textDuracion.setText("");
//				}
//			}
//		};

//		datePickerInicio.getModel().addChangeListener(changeListener);
//		datePickerFin.getModel().addChangeListener(changeListener);

//		JLabel labelServicios = new JLabel("Servicios no incluidos");
//		labelServicios.setBounds(30, 380, 150, 25);
//		panel.add(labelServicios);
//
//		JTextArea textServicios = new JTextArea();
//		textServicios.setBounds(175, 380, 400, 100);
//		panel.add(textServicios);
//
//		JLabel labelTipoViaje = new JLabel("Tipo de viaje");
//		labelTipoViaje.setBounds(30, 500, 150, 25);
//		panel.add(labelTipoViaje);
//
//		JComboBox<String> comboTipoViaje = new JComboBox<>();
//		ArrayList<TipoViaje> tiposViaje = controlador.getTiposViaje();
//		for (TipoViaje tipoViaje : tiposViaje) {
//			comboTipoViaje.addItem(tipoViaje.getDescripcion());
//		}
//		comboTipoViaje.setBounds(175, 500, 190, 25);
//		panel.add(comboTipoViaje);
//
//		JLabel labelPais = new JLabel("País destino");
//		labelPais.setBounds(30, 540, 150, 25);
//		panel.add(labelPais);
//
//		JComboBox<String> comboPais = new JComboBox<>();
//		ArrayList<Pais> paises = controlador.mostrarPaises();
//		for (Pais pais : paises) {
//			comboPais.addItem(pais.getNombre());
//		}
//		comboPais.setBounds(175, 540, 190, 25);
//		panel.add(comboPais);
//
//		JButton btnConfirmar = new JButton("Confirmar");
//		btnConfirmar.setBounds(120, 600, 150, 25);
//		btnConfirmar.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// Pendiente hacer que se guarden los datos en la base de datos
//			}
//		});
//		panel.add(btnConfirmar);
//
//		JButton btnCancelar = new JButton("Cancelar");
//		btnCancelar.setBounds(400, 600, 150, 25);
//		btnCancelar.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// Boton de cancelar que lleve a donde haga falta
//				frame.gotoViajes();
//			}
//		});
//		panel.add(btnCancelar);
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