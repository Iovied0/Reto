package agenciaViajes.vista.paneles;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.Time;
import java.net.URI;
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
import agenciaViajes.bbdd.pojos.Aerolineas;
import agenciaViajes.bbdd.pojos.Aeropuerto;
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

		JLabel labelTrayecto = new JLabel("Trayecto");
		labelTrayecto.setBounds(33, 179, 150, 25);
		panel.add(labelTrayecto);

		JComboBox<String> trayectoCombo = new JComboBox<String>();
		trayectoCombo.addItem("IDA");
		trayectoCombo.addItem("IDA Y VUELTA");
		trayectoCombo.setBackground(Color.white);
		trayectoCombo.setBounds(178, 178, 150, 27);
		panel.add(trayectoCombo);

		JLabel AeropuertoOrigen = new JLabel("Aeropuerto Origen");
		AeropuertoOrigen.setBounds(33, 245, 150, 25);
		panel.add(AeropuertoOrigen);

		JComboBox<String> AeropuertoOrigenCombo = new JComboBox<String>();
		ArrayList<Aeropuerto> aeropuertosOrigen = controlador.getAeropuertos();
		for (Aeropuerto aeropuerto : aeropuertosOrigen) {
			AeropuertoOrigenCombo.addItem(aeropuerto.getCiudad().getNombre() + " (" + aeropuerto.getCodigo() + ")");
		}
		AeropuertoOrigenCombo.setBackground(Color.white);
		AeropuertoOrigenCombo.setBounds(178, 245, 250, 25);
		panel.add(AeropuertoOrigenCombo);

//		OBTENER CODIGO DEL AEROPUERTO SELECCIONADO
		String seleccionadoOrigen = (String) AeropuertoOrigenCombo.getSelectedItem();

        // Usar una expresión regular para extraer el código entre paréntesis
        String codigoOrigen= seleccionadoOrigen.replaceAll(".*\\((\\w{3})\\)$", "$1");
        System.out.println(codigoOrigen);

		JLabel AeropuertoDestino = new JLabel("Aeropuerto Destino");
		AeropuertoDestino.setBounds(33, 316, 150, 25);
		panel.add(AeropuertoDestino);

		JComboBox<String> AeropuertoDestinoCombo = new JComboBox<String>();
		ArrayList<Aeropuerto> aeropuertosDestino = controlador.getAeropuertos();
		for (Aeropuerto aeropuerto : aeropuertosDestino) {
			AeropuertoDestinoCombo.addItem(aeropuerto.getCiudad().getNombre() + " (" + aeropuerto.getCodigo() + ")");
		}
		AeropuertoDestinoCombo.setBackground(Color.white);
		AeropuertoDestinoCombo.setBounds(178, 316, 250, 25);
		panel.add(AeropuertoDestinoCombo);

//		OBTENER CODIGO DEL AEROPUERTO SELECCIONADO
		String seleccionadoDestino = (String) AeropuertoDestinoCombo.getSelectedItem();

        String codigoDestino= seleccionadoDestino.replaceAll(".*\\((\\w{3})\\)$", "$1");
        System.out.println(codigoDestino);
        
		JLabel labelFechaInicio = new JLabel("Fecha Ida");
		labelFechaInicio.setBounds(33, 368, 150, 25);
		panel.add(labelFechaInicio);

		UtilDateModel modelIda = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanelIda = new JDatePanelImpl(modelIda, p);
		JDatePickerImpl datePickerIda = new JDatePickerImpl(datePanelIda, new DateLabelFormatter());
		datePickerIda.setBounds(178, 366, 150, 27);
		panel.add(datePickerIda);

		JLabel lblCodigoVueloIda = new JLabel("Codigo Vuelo");
		lblCodigoVueloIda.setBounds(33, 421, 150, 25);
		panel.add(lblCodigoVueloIda);
		JTextField txtCodigoVueloIda = new JTextField();
		txtCodigoVueloIda.setBounds(178, 421, 150, 25);
		panel.add(txtCodigoVueloIda);

		JLabel labelAerolinea = new JLabel("Aerolinea");
		labelAerolinea.setBounds(33, 479, 150, 25);
		panel.add(labelAerolinea);
		JComboBox<String> comboAerolineasIda = new JComboBox<String>();
		ArrayList<Aerolineas> AerolineasIda = controlador.getAerolineas();
		for (Aerolineas aerolineas : AerolineasIda) {
			comboAerolineasIda.addItem(aerolineas.getNombre());
		}
		comboAerolineasIda.setBackground(Color.white);
		comboAerolineasIda.setBounds(178, 479, 250, 25);
		panel.add(comboAerolineasIda);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(33, 532, 150, 25);
		panel.add(lblPrecio);

		JTextField txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();

				boolean numeroValido = key >= '0' && key <= '9';
				boolean punto = key == '.';

				if (!(numeroValido || punto)) {
					e.consume();
					return;
				}
				// evita que se pueda meter mas de un punto
				if (punto && txtPrecio.getText().contains(".")) {
					e.consume();
				}
			}
		});

		txtPrecio.setBounds(178, 532, 150, 25);
		panel.add(txtPrecio);

		// AÑADIR EL FILTRO PARA QUE SOLO PUEDA METER NUMEROS O PUNTO

		JLabel lblHorarioSalidaIda = new JLabel("Horario Salida");
		lblHorarioSalidaIda.setBounds(33, 593, 150, 25);
		panel.add(lblHorarioSalidaIda);

		JTextField txtHorarioSalidaIda = new JTextField();
		txtHorarioSalidaIda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracterIntroducido = e.getKeyChar();
				String texto = txtHorarioSalidaIda.getText();

				// Longitud máxima de 5 caracteres
				if (texto.length() >= 5) {
					e.consume(); // Evita que se escriba el carácter
					return;
				}

				// Validar cada posición del carácter
				int posicion = texto.length();
				switch (posicion) {
				case 0: // Primer carácter: 0-2
					if (caracterIntroducido < '0' || caracterIntroducido > '2') {
						e.consume();
					}
					break;
				case 1:
					// Segundo carácter: 0-9 pero si el primer caracter es 2, el segundo no puede
					// ser mayor que 3
					if (caracterIntroducido < '0' || caracterIntroducido > '9') {
						e.consume();
					} else if (texto.charAt(0) == '2' && caracterIntroducido > '3') {
						e.consume();
					}
					break;
				case 2: // Tercer carácter: ':'
					if (caracterIntroducido != ':') {
						e.consume();
					}
					break;
				case 3: // Cuarto carácter: 0-5
					if (caracterIntroducido < '0' || caracterIntroducido > '5') {
						e.consume();
					}
					break;
				case 4: // Quinto carácter: 0-9
					if (caracterIntroducido < '0' || caracterIntroducido > '9') {
						e.consume();
					}
					break;
				}
			}
		});
		txtHorarioSalidaIda.setBounds(178, 593, 150, 25);
		panel.add(txtHorarioSalidaIda);

		JLabel lblDuracionIda = new JLabel("Duracion");
		lblDuracionIda.setBounds(33, 654, 150, 25);
		panel.add(lblDuracionIda);

		JTextField txtDuracionIda = new JTextField();
		txtDuracionIda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracterIntroducido = e.getKeyChar();
				String texto = txtDuracionIda.getText();

				// Longitud máxima de 5 caracteres
				if (texto.length() >= 5) {
					e.consume(); // Evita que se escriba el carácter
					return;
				}

				// Validar cada posición del carácter
				int posicion = texto.length();
				switch (posicion) {
				case 0: // Primer carácter: 0-2
					if (caracterIntroducido < '0' || caracterIntroducido > '2') {
						e.consume();
					}
					break;
				case 1:
					// Segundo carácter: 0-9 pero si el primer caracter es 2, el segundo no puede
					// ser mayor que 3
					if (caracterIntroducido < '0' || caracterIntroducido > '9') {
						e.consume();
					} else if (texto.charAt(0) == '2' && caracterIntroducido > '3') {
						e.consume();
					}
					break;
				case 2: // Tercer carácter: ':'
					if (caracterIntroducido != ':') {
						e.consume();
					}
					break;
				case 3: // Cuarto carácter: 0-5
					if (caracterIntroducido < '0' || caracterIntroducido > '5') {
						e.consume();
					}
					break;
				case 4: // Quinto carácter: 0-9
					if (caracterIntroducido < '0' || caracterIntroducido > '9') {
						e.consume();
					}
					break;
				}
			}
		});
		txtDuracionIda.setBounds(178, 654, 150, 25);
		panel.add(txtDuracionIda);

		JButton btnBuscarViaje = new JButton("BUSCAR VIAJE");
		btnBuscarViaje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				Abrir el enlace en el navegador predeterminado
				try {

					// ABRE LA WEB SKYSCANNER EN EL NAVEGADOR PREDETERMINADO
					Desktop.getDesktop().browse(new URI("https://www.skyscanner.es"));

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscarViaje.setBounds(524, 284, 257, 23);
		panel.add(btnBuscarViaje);

		JLabel lblCodigoVueloVuelta = new JLabel("Codigo Vuelo");
		lblCodigoVueloVuelta.setBounds(548, 421, 150, 25);
		lblCodigoVueloVuelta.setVisible(false);
		panel.add(lblCodigoVueloVuelta);

		JTextField txtCodigoVueloVuelta = new JTextField();
		txtCodigoVueloVuelta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracterIntroducido = e.getKeyChar();
				String texto = txtCodigoVueloVuelta.getText();

				boolean minuscula = caracterIntroducido >= 'a' && caracterIntroducido <= 'z';
				boolean mayuscula = caracterIntroducido >= 'A' && caracterIntroducido <= 'Z';
				boolean numero = caracterIntroducido >= '0' && caracterIntroducido <= '9';

				// Longitud máxima de 5 caracteres
				if (texto.length() == 6) {
					e.consume(); // Evita que se escriba el carácter
					return;
				}

				// Validar cada posición del carácter
				int posicion = texto.length();
				switch (posicion) {
				case 0: // Primer carácter: a-Z
					if (!(mayuscula || minuscula)) {
						e.consume();
					}
					break;
				case 1:
					// Segundo carácter: 0-9 pero si el primer caracter es 2, el segundo no puede
					// ser mayor que 3
					if (!(mayuscula || minuscula)) {
						e.consume();
					}
					break;
				case 2: // Tercer carácter: ':'
					if (caracterIntroducido != ':') {
						e.consume();
					}
					break;
				case 3: // Cuarto carácter: 0-5
					if (caracterIntroducido < '0' || caracterIntroducido > '5') {
						e.consume();
					}
					break;
				case 4: // Quinto carácter: 0-9
					if (caracterIntroducido < '0' || caracterIntroducido > '9') {
						e.consume();
					}
					break;
				}

			}
		});
		txtCodigoVueloVuelta.setBounds(693, 421, 150, 25);
		txtCodigoVueloVuelta.setVisible(false);
		panel.add(txtCodigoVueloVuelta);

		JLabel labelAerolineaVuelta = new JLabel("Aerolinea");
		labelAerolineaVuelta.setBounds(548, 479, 150, 25);
		labelAerolineaVuelta.setVisible(false);
		panel.add(labelAerolineaVuelta);

		JComboBox<String> comboAerolineasVuelta = new JComboBox<String>();
		ArrayList<Aerolineas> AerolineasVuelta = controlador.getAerolineas();
		for (Aerolineas aerolineas : AerolineasVuelta) {
			comboAerolineasVuelta.addItem(aerolineas.getNombre());
		}
		comboAerolineasVuelta.setBackground(Color.WHITE);
		comboAerolineasVuelta.setBounds(693, 479, 250, 25);
		comboAerolineasVuelta.setVisible(false);
		panel.add(comboAerolineasVuelta);

		JLabel lblPrecioTotal = new JLabel("Precio Total");
		lblPrecioTotal.setBounds(548, 532, 150, 25);
		lblPrecioTotal.setVisible(false);
		panel.add(lblPrecioTotal);

		JTextField txtPrecioTotal = new JTextField();
		txtPrecioTotal.setBounds(693, 532, 150, 25);
		txtPrecioTotal.setVisible(false);
		panel.add(txtPrecioTotal);

		JLabel lblHorarioSalidaVuelta = new JLabel("Horario Salida");
		lblHorarioSalidaVuelta.setBounds(548, 593, 150, 25);
		lblHorarioSalidaVuelta.setVisible(false);
		panel.add(lblHorarioSalidaVuelta);

		JTextField txtHorarioSalidaVuelta = new JTextField();
		txtHorarioSalidaVuelta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracterIntroducido = e.getKeyChar();
				String texto = txtHorarioSalidaVuelta.getText();

				// Longitud máxima de 5 caracteres
				if (texto.length() >= 5) {
					e.consume(); // Evita que se escriba el carácter
					return;
				}

				// Validar cada posición del carácter
				int posicion = texto.length();
				switch (posicion) {
				case 0: // Primer carácter: 0-2
					if (caracterIntroducido < '0' || caracterIntroducido > '2') {
						e.consume();
					}
					break;
				case 1:
					// Segundo carácter: 0-9 pero si el primer caracter es 2, el segundo no puede
					// ser mayor que 3
					if (caracterIntroducido < '0' || caracterIntroducido > '9') {
						e.consume();
					} else if (texto.charAt(0) == '2' && caracterIntroducido > '3') {
						e.consume();
					}
					break;
				case 2: // Tercer carácter: ':'
					if (caracterIntroducido != ':') {
						e.consume();
					}
					break;
				case 3: // Cuarto carácter: 0-5
					if (caracterIntroducido < '0' || caracterIntroducido > '5') {
						e.consume();
					}
					break;
				case 4: // Quinto carácter: 0-9
					if (caracterIntroducido < '0' || caracterIntroducido > '9') {
						e.consume();
					}
					break;
				}
			}
		});
		txtHorarioSalidaVuelta.setBounds(693, 593, 150, 25);
		txtHorarioSalidaVuelta.setVisible(false);
		panel.add(txtHorarioSalidaVuelta);

		JLabel lblDuracionVuelta = new JLabel("Duracion");
		lblDuracionVuelta.setBounds(548, 654, 150, 25);
		lblDuracionVuelta.setVisible(false);
		panel.add(lblDuracionVuelta);

		JTextField txtDuracionVuelta = new JTextField();
		txtDuracionVuelta.setBounds(693, 654, 150, 25);
		txtDuracionVuelta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracterIntroducido = e.getKeyChar();
				String texto = txtDuracionVuelta.getText();

				// Longitud máxima de 5 caracteres
				if (texto.length() >= 5) {
					e.consume(); // Evita que se escriba el carácter
					return;
				}

				// Validar cada posición del carácter
				int posicion = texto.length();
				switch (posicion) {
				case 0: // Primer carácter: 0-2
					if (caracterIntroducido < '0' || caracterIntroducido > '2') {
						e.consume();
					}
					break;
				case 1:
					// Segundo carácter: 0-9 pero si el primer caracter es 2, el segundo no puede
					// ser mayor que 3
					if (caracterIntroducido < '0' || caracterIntroducido > '9') {
						e.consume();
					} else if (texto.charAt(0) == '2' && caracterIntroducido > '3') {
						e.consume();
					}
					break;
				case 2: // Tercer carácter: ':'
					if (caracterIntroducido != ':') {
						e.consume();
					}
					break;
				case 3: // Cuarto carácter: 0-5
					if (caracterIntroducido < '0' || caracterIntroducido > '5') {
						e.consume();
					}
					break;
				case 4: // Quinto carácter: 0-9
					if (caracterIntroducido < '0' || caracterIntroducido > '9') {
						e.consume();
					}
					break;
				}
			}
		});
		txtDuracionVuelta.setVisible(false);
		panel.add(txtDuracionVuelta);

		JLabel labelFechaVuelta = new JLabel("Fecha Vuelta");
		labelFechaVuelta.setBounds(548, 373, 150, 25);
		labelFechaVuelta.setVisible(false);
		panel.add(labelFechaVuelta);

		UtilDateModel modelVuelta = new UtilDateModel();
		JDatePanelImpl datePanelVuelta = new JDatePanelImpl(modelVuelta, p);
		JDatePickerImpl datePickerVuelta = new JDatePickerImpl(datePanelVuelta, new DateLabelFormatter());
		datePickerVuelta.setBounds(693, 373, 150, 27);
		datePickerVuelta.setVisible(false);
		panel.add(datePickerVuelta);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        // Obtener el viaje seleccionado
		        Viaje viajeSeleccionado = viajes.get(comboViaje.getSelectedIndex());
		        int idViaje = viajeSeleccionado.getId();
		        		       
		        // Convertir fechas
				java.util.Date fechaUtil = modelIda.getValue();
				Date fechaIda = new Date(fechaUtil.getTime());


				
				// Convertir hora salida y duracion
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
				java.util.Date date1 = null;
				java.util.Date date2 = null;

				try {
					date1 = timeFormat.parse(txtHorarioSalidaIda.getText());
					date2 = timeFormat.parse(txtDuracionIda.getText());

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Time horaSalida = new Time(date1.getTime());				
				Time duracion = new Time(date2.getTime());
				


				//Aeroline
				Aerolineas aerolineaIda = AerolineasIda.get(comboAerolineasIda.getSelectedIndex());

                
				if (trayectoCombo.getSelectedIndex() == 0) {
					//Precio
					double precio = Double.parseDouble(txtPrecio.getText());
					
			        controlador.insertVuelo(//Vuelo de ida
			        		trayectoCombo.getSelectedItem().toString(),
			        		txtCodigoVueloIda.getText(),
			        		fechaIda,
			        		horaSalida, 
			        		duracion, 
			        		aerolineaIda.getCodigo(),
			        		codigoOrigen,
			        		codigoDestino,
			        		idViaje, 
			        		precio, 
			        		null,
			        		frame);
				} else {
					java.util.Date fechaUtil2 = modelVuelta.getValue();
					Date fechaVuelta = new Date(fechaUtil2.getTime());
										
					double precioTotal = Double.parseDouble(txtPrecioTotal.getText());
	                double precioMitad = precioTotal / 2;
	                
	                Aerolineas aerolineaVuelta = AerolineasVuelta.get(comboAerolineasVuelta.getSelectedIndex());
	                
			        controlador.insertVuelo(//Vuelo de ida
			        		"IDA",
			        		txtCodigoVueloIda.getText(),
			        		fechaIda,
			        		horaSalida, 
			        		duracion, 
			        		aerolineaIda.getCodigo(),
			        		codigoOrigen,
			        		codigoDestino,
			        		idViaje, 
			        		precioMitad, 
			        		null,
			        		frame);
					controlador.insertVuelo(//Vuelo de vuelta
							"VUELTA",
							txtCodigoVueloVuelta.getText(),
							fechaVuelta,
							horaSalida,
							duracion,
							aerolineaVuelta.getCodigo(),
							codigoDestino,
			        		codigoOrigen,
							idViaje,
							precioTotal,
			        		txtCodigoVueloIda.getText(),
							frame);
				}

			}
		});
		btnGuardar.setBounds(310, 810, 118, 23);
		panel.add(btnGuardar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.gotoViajes();
			}
		});
		btnCancelar.setBounds(609, 810, 118, 23);
		panel.add(btnCancelar);

		// ActionListener desplegable trayecto
		trayectoCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (trayectoCombo.getSelectedIndex() == 0) {
					lblPrecio.setVisible(true);
					txtPrecio.setVisible(true);

					labelFechaVuelta.setVisible(false);
					datePickerVuelta.setVisible(false);
					lblCodigoVueloVuelta.setVisible(false);
					txtCodigoVueloVuelta.setVisible(false);
					labelAerolineaVuelta.setVisible(false);
					comboAerolineasVuelta.setVisible(false);
					lblPrecioTotal.setVisible(false);
					txtPrecioTotal.setVisible(false);
					lblHorarioSalidaVuelta.setVisible(false);
					txtHorarioSalidaVuelta.setVisible(false);
					lblDuracionVuelta.setVisible(false);
					txtDuracionVuelta.setVisible(false);
				} else {
					lblPrecio.setVisible(false);
					txtPrecio.setVisible(false);

					labelFechaVuelta.setVisible(true);
					datePickerVuelta.setVisible(true);
					lblCodigoVueloVuelta.setVisible(true);
					txtCodigoVueloVuelta.setVisible(true);
					labelAerolineaVuelta.setVisible(true);
					comboAerolineasVuelta.setVisible(true);
					lblPrecioTotal.setVisible(true);
					txtPrecioTotal.setVisible(true);
					lblHorarioSalidaVuelta.setVisible(true);
					txtHorarioSalidaVuelta.setVisible(true);
					lblDuracionVuelta.setVisible(true);
					txtDuracionVuelta.setVisible(true);

				}
			}
		});
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