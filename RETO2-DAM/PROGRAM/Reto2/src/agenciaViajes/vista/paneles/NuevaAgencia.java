package agenciaViajes.vista.paneles;

import agenciaViajes.ViajesErrekamari;
import agenciaViajes.bbdd.pojos.Agencia;
import agenciaViajes.bbdd.pojos.NumeroEmpleados;
import agenciaViajes.bbdd.pojos.TiposAgencia;
import agenciaViajes.controlador.Controlador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class NuevaAgencia {

	private JPanel panel = null;
	private Controlador controlador = null;

	/**
	 * Crea el panel "NuevaAgencia"
	 * 
	 * @param paneles
	 * @param frame
	 */
	public NuevaAgencia(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
		panel = new JPanel();
		panel.setBounds(0, 0, 1300, 900);
		panel.setLayout(null);

		//////////////////////////// LABEL TÍTULO \\\\\\\\\\\\\\\\\\\\\\\\\\\\
		JLabel labelTitulo = new JLabel("INTRODUZCA LOS PARÁMETROS DE LA NUEVA AGENCIA");
		Font fuenteLabelTitulo = new Font("Lucida Sans Unicode", Font.BOLD, 20);
		labelTitulo.setFont(fuenteLabelTitulo);
		labelTitulo.setBounds(15, 20, 600, 25);
		panel.add(labelTitulo);

		//////////////////////////// NOMBRE DE AGENCIA \\\\\\\\\\\\\\\\\\\\\\\\\\\\
		JLabel labelUsuario = new JLabel("Nombre de Agencia");
		labelUsuario.setBounds(30, 100, 150, 25);
		panel.add(labelUsuario);

		JTextField textUsuario = new JTextField();
		textUsuario.setBounds(175, 100, 150, 25);
		panel.add(textUsuario);

		//////////////////////////// CONTRASEÑA \\\\\\\\\\\\\\\\\\\\\\\\\\\\
		JLabel labelContrasenya = new JLabel("Contraseña");
		labelContrasenya.setBounds(400, 100, 150, 25);
		panel.add(labelContrasenya);

		JPasswordField textoContrasenya = new JPasswordField();
		textoContrasenya.setBounds(500, 100, 150, 25);
		panel.add(textoContrasenya);

		/////////////////////// MOSTRAR/OCULTAR CONTRASEÑA \\\\\\\\\\\\\\\\\\\\\\\
		ImageIcon ojoAbierto = new ImageIcon("src/agenciaViajes/vista/img/abierto.png");
		ImageIcon ojoCerrado = new ImageIcon("src/agenciaViajes/vista/img/cerrado.png");
		JButton mostrarOcultarContrasenya = new JButton();
		Image abiertoEscalado = ojoAbierto.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		Image cerradoEscalado = ojoCerrado.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

		mostrarOcultarContrasenya.setBounds(650, 100, 27, 25);
		mostrarOcultarContrasenya.setIcon(new ImageIcon(cerradoEscalado));
		mostrarOcultarContrasenya.setFocusPainted(false); // Quitar el borde de foco
		mostrarOcultarContrasenya.setContentAreaFilled(false); // Quitar el fondo del botón
		mostrarOcultarContrasenya.setBorderPainted(false);
		panel.add(mostrarOcultarContrasenya);

//		********************** ACTION LISTENERS MOSTRAR/OCULTAR CONTRASEÑA **********************
		mostrarOcultarContrasenya.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textoContrasenya.getEchoChar() == '•') { // contraseña oculta
					mostrarOcultarContrasenya.setIcon(new ImageIcon(abiertoEscalado));
					textoContrasenya.setEchoChar((char) 0); // muestra la contraseña
				} else {// contraseña visible
					mostrarOcultarContrasenya.setIcon(new ImageIcon(cerradoEscalado));
					textoContrasenya.setEchoChar('•'); // oculta la contraseña
				}

			}
		});

		//////////////////////////// COLOR DE MARCA \\\\\\\\\\\\\\\\\\\\\\\\\\\\
		JLabel labelColorMarca = new JLabel("Color de marca");
		labelColorMarca.setBounds(30, 170, 150, 25);
		panel.add(labelColorMarca);

		JTextField textoColorHexadecimal = new JTextField("#RRGGBB"); // PLACEHOLDER
		textoColorHexadecimal.setForeground(Color.LIGHT_GRAY);// CAMBIA EL COLOR DEL PLACEHOLDER
		textoColorHexadecimal.setBounds(175, 170, 70, 25);
		panel.add(textoColorHexadecimal);

		JLabel colorSeleccionado = new JLabel("");
		colorSeleccionado.setBackground(Color.white);
		colorSeleccionado.setOpaque(true);
		colorSeleccionado.setBounds(275, 170, 70, 25);
		panel.add(colorSeleccionado);

		JButton btnColorPicker = new JButton("Color picker!");
		btnColorPicker.setBounds(400, 170, 120, 25);
		btnColorPicker.setFocusPainted(false); // se quita ese cuadrado horrible al seleccionar con el tabulador
		panel.add(btnColorPicker);
		Border defaultBorder = btnColorPicker.getBorder(); // Guarda el borde por defecto
		Border focusBorder = BorderFactory.createLineBorder(Color.decode("#0078D7"), 1); // crea un borde nuevo como el
																							// que sale con el ratón

//		**************************** ACTION LISTENERS COLOR MARCA ****************************

		textoColorHexadecimal.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {// CUANDO SE SELECCIONA EL CUADRO DE TEXTO
				if (textoColorHexadecimal.getText().equals("#RRGGBB")) { // SI ESTÁ PUESTO EL PLACEHOLDER
					textoColorHexadecimal.setText("#"); // SE BORRA EL PLACEHOLDER Y SE PONE LA ALMOHADILLA
					textoColorHexadecimal.setForeground(Color.BLACK);// CAMBIA EL COLOR AL DE TEXTO NORMAL
				}
			}

			public void focusLost(FocusEvent e) { // CUANDO SE DEJA DE SELECCIONAR EL CUADRO DE TEXTO
				// actualizar el color del label con fondo
				try {
					colorSeleccionado.setBackground(Color.decode(textoColorHexadecimal.getText()));
				} catch (NumberFormatException a) {
					// NO HACEMOS NADA
				}
			}
		});

		btnColorPicker.addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent e) {// CUANDO SE SELECCIONA EL BOTON CON EL TABULADOR
				btnColorPicker.setBorder(focusBorder);
			}

			public void focusLost(FocusEvent e) { // CUANDO SE DEJA DE SELECCIONAR EL BOTON
				btnColorPicker.setBorder(defaultBorder);
			}
		});

		btnColorPicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color selectedColor = JColorChooser.showDialog(null, "Seleccionar Color",
						colorSeleccionado.getBackground());
				if (selectedColor != null) {
					colorSeleccionado.setBackground(selectedColor);
					textoColorHexadecimal.setForeground(Color.black); // CAMBIA EL COLOR AL DE TEXTO NORMAL

					String colorHexadecimal = String.format("#%02X%02X%02X", selectedColor.getRed(),
							selectedColor.getGreen(), selectedColor.getBlue());

					textoColorHexadecimal.setText(colorHexadecimal);
				}
			}
		});

		//////////////////////////// NÚMERO DE EMPLEADOS \\\\\\\\\\\\\\\\\\\\\\\\\\\\
		JLabel labelNumeroEmpleados = new JLabel("Numero de empleados");
		labelNumeroEmpleados.setBounds(30, 240, 150, 25);
		panel.add(labelNumeroEmpleados);

		JComboBox<String> numeroEmpleadosCombo;
		ArrayList<NumeroEmpleados> arrayNumeroEmpleados;
		controlador = Controlador.getInstanceControlador();

		arrayNumeroEmpleados = controlador.getNumeroEmpleados();
		numeroEmpleadosCombo = new JComboBox<String>();
		numeroEmpleadosCombo.setBackground(Color.white);
		for (NumeroEmpleados numeroEmpleados : arrayNumeroEmpleados) {
			numeroEmpleadosCombo.addItem(numeroEmpleados.getNumeroEmpleados());
		}
		numeroEmpleadosCombo.setBounds(175, 240, 190, 25);
		panel.add(numeroEmpleadosCombo);

		//////////////////////////// TIPO DE AGENCIA \\\\\\\\\\\\\\\\\\\\\\\\\\\\
		JLabel labelTipoAgencia = new JLabel("Tipo de agencia");
		labelTipoAgencia.setBounds(30, 320, 150, 25);
		panel.add(labelTipoAgencia);

		JComboBox<String> tipoAgenciaCombo;
		ArrayList<TiposAgencia> arrayTipoAgencia;
//		controlador = new Controlador();

		arrayTipoAgencia = controlador.getTipoAgencia();
		tipoAgenciaCombo = new JComboBox<String>();
		tipoAgenciaCombo.setBackground(Color.white);
		for (TiposAgencia tipoAgencia : arrayTipoAgencia) {
			tipoAgenciaCombo.addItem(tipoAgencia.getDescripcion());
		}
		tipoAgenciaCombo.setBounds(175, 320, 190, 25);
		panel.add(tipoAgenciaCombo);

		//////////////////////////// LOGO \\\\\\\\\\\\\\\\\\\\\\\\\\\\
		JLabel labelLogo = new JLabel("Logo");
		labelLogo.setBounds(30, 400, 150, 25);
		panel.add(labelLogo);

		JTextField textLogo = new JTextField();
		textLogo.setBounds(175, 400, 400, 25);
		panel.add(textLogo);

		JButton btnVerLogo = new JButton("Comprueba el logo!");
		btnVerLogo.setBounds(600, 400, 150, 25);
		panel.add(btnVerLogo);

//		**************************** ACTION LISTENERS LOGO ****************************
		btnVerLogo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				Abrir el enlace en el navegador predeterminado
				try {
					if (textLogo.getText().length() != 0) {
						// ABRE EL ENLACE EN EL NAVEGADOR PREDETERMINADO O EN EL EXPLORADOR DE ARCHIVOS
						// (DEPENDE DEL ENLACE)
						Desktop.getDesktop().browse(new URI(textLogo.getText()));
					} else {
						// nada
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		//////////////////////////// BOTON CONFIRMAR \\\\\\\\\\\\\\\\\\\\\\\\\\\\
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(120, 500, 150, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] contraseñaChar = textoContrasenya.getPassword();
				ArrayList<Agencia> agencias = controlador.getAgencias();

//				**************************** VALIDACIONES DE CAMPOS ****************************

				if (textUsuario.getText().replace(" ", "").length() == 0) {
					JOptionPane.showMessageDialog(null, "El nombre de agencia no puede estar en blanco", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				for (Agencia agencia : agencias) {
					if (textUsuario.getText().equalsIgnoreCase(agencia.getNombre())) {
						JOptionPane.showMessageDialog(null, "El nombre de agencia ya se encuentra en uso", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				if (String.valueOf(contraseñaChar).replace(" ", "").length() == 0) {
					JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía o ser únicamente espacios",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (colorSeleccionado.getText().equalsIgnoreCase("#RRGGBB")) {
					JOptionPane.showMessageDialog(null, "Color no válido", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!colorSeleccionado.getText().equalsIgnoreCase("#RRGGBB")) {
					try {
						colorSeleccionado.setBackground(Color.decode(textoColorHexadecimal.getText()));
					} catch (NumberFormatException a) {
						JOptionPane.showMessageDialog(null, "Color no válido", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				if (!rutaValida(textLogo.getText())) {
					JOptionPane.showMessageDialog(null, "Logo no válido", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}

				NumeroEmpleados numeroEmpleados = controlador
						.getNumeroEmpleadosObjetoPorDescripcion(numeroEmpleadosCombo.getSelectedItem().toString());

				TiposAgencia tipoAgencia = controlador
						.getTipoAgenciaObjetoPorDescripcion(tipoAgenciaCombo.getSelectedItem().toString());

				controlador.insertarAgencia(textUsuario.getText(), String.valueOf(contraseñaChar),
						textoColorHexadecimal.getText(), numeroEmpleados, tipoAgencia, textLogo.getText());

				JOptionPane.showMessageDialog(null, "Agencia creada con exito", "Nueva agencia",
						JOptionPane.INFORMATION_MESSAGE);
				reset(textUsuario, textoContrasenya, textoColorHexadecimal, colorSeleccionado, numeroEmpleadosCombo,
						tipoAgenciaCombo, textLogo);
				frame.gotoFormLogin();
			}

		});
		panel.add(btnConfirmar);

		//////////////////////////// BOTON CANCELAR \\\\\\\\\\\\\\\\\\\\\\\\\\\\
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(400, 500, 150, 25);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset(textUsuario, textoContrasenya, textoColorHexadecimal, labelColorMarca, numeroEmpleadosCombo,
						tipoAgenciaCombo, textLogo);
				frame.gotoFormLogin();
			}
		});
		panel.add(btnCancelar);
	}

	//////////////////////////// FUNCIONES \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public JPanel getPanel() {
		return panel;
	}

	private void reset(JTextField textUsuario, JPasswordField textoContrasenya, JTextField textoColorHexadecimal,
			JLabel colorSeleccionado, JComboBox<String> numeroEmpleadosCombo, JComboBox<String> tipoAgenciaCombo,
			JTextField textLogo) {
		textUsuario.setText("");
		textoContrasenya.setText("");
		textoColorHexadecimal.setText("#RRGGBB");
		colorSeleccionado.setBackground(Color.white);
		textoColorHexadecimal.setForeground(Color.LIGHT_GRAY);
		numeroEmpleadosCombo.setSelectedIndex(0);
		tipoAgenciaCombo.setSelectedIndex(0);
		textLogo.setText("");
	}

	private boolean rutaValida(String ruta) {
		try {
			if (!ruta.toLowerCase().matches(".*\\.(jpg|jpeg|png|gif|bmp)$")) {
				return false; // No es una imagen por la extensión
			}

			// URI: identifica de manera unica e inequivoca un recurso en la red (definicion
			// de internet)
			URI uri = URI.create(ruta);
			URL url = uri.toURL(); // Convertir URI a URL

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("HEAD"); // Usamos "HEAD" para solo obtener los headers
			connection.setConnectTimeout(5000); // 5 segundos de espera
			connection.setReadTimeout(5000);

			// Obtener el tipo de contenido de la URL
			String contentType = connection.getContentType();

			// Verificar si el tipo de contenido es una imagen
			return contentType != null && contentType.startsWith("image/");
		} catch (Exception e) {
			return false; // no es una url valida
		}

	}
}

//
//package agenciaViajes.vista.panel;
//
//
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Insets;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JColorChooser;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//import agenciaViajes.bbdd.gestores.GestorAgencia;
//import agenciaViajes.bbdd.pojos.Agencia;
//import agenciaViajes.bbdd.pojos.NumeroEmpleados;
//import agenciaViajes.bbdd.pojos.TiposAgencia;
//
//public class NuevaAgencia extends JFrame {
// 
//	private static final long serialVersionUID = -4181883082017603383L;
//	
//	private JTextField nombreAgenciaField;
//    private JComboBox<String> empleadosComboBox;
//    private JComboBox<String> tipoAgenciaComboBox;
//    private JTextField logoUrlField;
//    private JPanel colorPreviewPanel;
//    private JTextField colorHexField;
//
//    public NuevaAgencia() {
//
//        // Color de marca, Codigo Hexadecimal, Boton Seleccionar Color, Vista del Color
//        gbc.gridx = 0; gbc.gridy = 1;
//        gbc.weightx = 0.2;
//        panel.add(new JLabel("Color de Marca"), gbc);
//
//        gbc.gridx = 1; gbc.gridy = 1;
//        gbc.weightx = 0.3;
//        colorHexField = new JTextField();
//        colorHexField.setEditable(false);
//        panel.add(colorHexField, gbc);
//
//        gbc.gridx = 2; gbc.gridy = 1;
//        gbc.weightx = 0.2;
//        JButton colorPickerButton = new JButton("Seleccionar Color");
//        panel.add(colorPickerButton, gbc);
//
//        gbc.gridx = 3; gbc.gridy = 1;
//        gbc.weightx = 0.3;
//        colorPreviewPanel = new JPanel();
//        colorPreviewPanel.setPreferredSize(new Dimension(100, 30)); // Tamanio fijo para el panel de color
//        colorPreviewPanel.setBackground(Color.WHITE);
//        panel.add(colorPreviewPanel, gbc);
//
//        colorPickerButton.addActionListener(e -> {
//            Color selectedColor = JColorChooser.showDialog(this, "Seleccionar Color", colorPreviewPanel.getBackground());
//            if (selectedColor != null) {
//                colorPreviewPanel.setBackground(selectedColor);
//                colorHexField.setText(String.format("#%02X%02X%02X", selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue()));
//            }
//        });
//
//        // Numero de empleados (Desplegable con rangos)
//        gbc.gridx = 0; gbc.gridy = 2;
//        gbc.weightx = 0.2;
//        panel.add(new JLabel("Número de empleados"), gbc);
//
//        gbc.gridx = 1; gbc.gridy = 2;
//        gbc.weightx = 0.8;
//        String[] opcionesEmpleados = {"Entre 2 y 10 empleados", "Entre 10 y 100 empleados", "Entre 100 y 1000 empleados"};
//        empleadosComboBox = new JComboBox<>(opcionesEmpleados);
//        panel.add(empleadosComboBox, gbc);
//
//        // Tipo de Agencia
//        gbc.gridx = 0; gbc.gridy = 3;
//        gbc.weightx = 0.2;
//        panel.add(new JLabel("Tipo de Agencia"), gbc);
//
//        gbc.gridx = 1; gbc.gridy = 3;
//        gbc.weightx = 0.8;
//        String[] opcionesTipoAgencia = {"A1", "A2", "A3"};  // Clave foranea de la tabla TipoAgencia
//        tipoAgenciaComboBox = new JComboBox<>(opcionesTipoAgencia);
//        panel.add(tipoAgenciaComboBox, gbc);
//
//        // URL del logo
//        gbc.gridx = 0; gbc.gridy = 4;
//        gbc.weightx = 0.2;
//        panel.add(new JLabel("Logo (URL)"), gbc);
//
//        gbc.gridx = 1; gbc.gridy = 4;
//        gbc.weightx = 0.8;
//        logoUrlField = new JTextField();
//        panel.add(logoUrlField, gbc);
//
//        // Botones
//        gbc.gridx = 0; gbc.gridy = 5;
//        gbc.gridwidth = 2; // Ocupar dos columnas
//        gbc.weightx = 1.0;
//        JButton btnGuardar = new JButton("Guardar");
//        btnGuardar.setPreferredSize(new Dimension(150, 30)); 
//        panel.add(btnGuardar, gbc);
//
//        gbc.gridx = 2; gbc.gridy = 5;
//        gbc.gridwidth = 2;
//        JButton btnVolver = new JButton("Volver a Login");
//        btnVolver.setPreferredSize(new Dimension(150, 30)); 
//        panel.add(btnVolver, gbc);
//
//        add(panel, BorderLayout.CENTER);
//        
//     // Agregar una imagen al panel
//        gbc.gridx = 0; gbc.gridy = 6;
//        gbc.gridwidth = 4; // Ocupar cuatro columnas
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0; // Expandir verticalmente
//        gbc.fill = GridBagConstraints.BOTH; // Expandir en ambas direcciones
//
//        // Cargar la imagen desde un archivo
//        ImageIcon imagenIcon = new ImageIcon("img/bienvenida.jpg"); 
//        JLabel imagenLabel = new JLabel(imagenIcon);
//        panel.add(imagenLabel, gbc);
//
//        add(panel, BorderLayout.CENTER);
//
//        // Evento para validar y guardar
//        btnGuardar.addActionListener(e -> guardarEnBaseDeDatos());
//
//        // Evento para volver a la pantalla de login
//        btnVolver.addActionListener(e -> {
//            Login loginFrame = new Login();
//            loginFrame.setVisible(true);
//            dispose();
//        });
//    }
//    
//    private NumeroEmpleados determinarLimiteEmpleados(String numeroEmpleados) {
//        switch (numeroEmpleados) {
//            case "Entre 2 y 10 empleados":
//                return new NumeroEmpleados("L1", "Entre 2 y 10 empleados");
//            case "Entre 10 y 100 empleados":
//                return new NumeroEmpleados("L2", "Entre 10 y 100 empleados");
//            case "Entre 100 y 1000 empleados":
//                return new NumeroEmpleados("L3", "Entre 100 y 1000 empleados");
//            default:
//                return new NumeroEmpleados("L1", "Entre 2 y 10 empleados"); // Valor por defecto
//        }
//    }
//    // gaurdar datos 
//    private void guardarEnBaseDeDatos() {
//      
//        String nombre = nombreAgenciaField.getText().trim();
//        String color = colorHexField.getText().trim();
//        String logo = logoUrlField.getText().trim();
//        String numEmpleados = (String) empleadosComboBox.getSelectedItem();
//        String tipoAgen = (String) tipoAgenciaComboBox.getSelectedItem();
//
//        // limiteEmpleados no incluye en la vetana Nueva Agencia pero en la tabla agencia bbdd es un foreing key (tabla NumeroEmpleados)  
//        NumeroEmpleados limiteEmpleados = determinarLimiteEmpleados(numEmpleados);
//
//        if (nombre.isEmpty() || color.isEmpty() || numEmpleados.isEmpty() || logo.isEmpty() || tipoAgen.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        Agencia agencia = new Agencia();
//        agencia.setNombre(nombre);
//        agencia.setLogo(logo);
//        agencia.setColor(color);
//        agencia.setNumeroEmpleados(numEmpleados);
//        agencia.setLimiteEmpleados(limiteEmpleados); // pojo NumeroEmpleados
//
//        TiposAgencia tipoAgenciaObj = new TiposAgencia();
//        tipoAgenciaObj.setCodigo(tipoAgen);
//        agencia.setTipoAgencia(tipoAgenciaObj);
//
//        GestorAgencia gestorAgencia = new GestorAgencia();
//        gestorAgencia.insertEjemplo(agencia);
//
//        JOptionPane.showMessageDialog(this, "Agencia guardada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
//        System.out.println("Agencia guardada correctamente.");
//    }
//    
//}

//setTitle("Nueva Agencia - Viajes Erreka-Mari");
//setSize(1100, 700);
//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//setLocationRelativeTo(null);
//
//JPanel panel = new JPanel();
//panel.setLayout(new GridBagLayout());
//GridBagConstraints gbc = new GridBagConstraints();
//gbc.insets = new Insets(10, 10, 10, 10);
//gbc.fill = GridBagConstraints.BOTH;
//gbc.weightx = 1.0;
//gbc.weighty = 1.0;
//
//// Nombre de agencia
//gbc.gridx = 0; gbc.gridy = 0;
//panel.add(new JLabel("Nombre de Agencia"), gbc);
//gbc.gridx = 1;
//nombreAgenciaField = new JTextField(30);
//panel.add(nombreAgenciaField, gbc);
//
//// Color de marca, Codigo Hexadecimal, Boton Seleccionar Color, Vista del Color
//gbc.gridx = 0; gbc.gridy = 1;
//panel.add(new JLabel("Color de Marca"), gbc);
//gbc.gridx = 1;
//colorHexField = new JTextField(15);
//colorHexField.setEditable(false);
//panel.add(colorHexField, gbc);
//gbc.gridx = 2;
//JButton colorPickerButton = new JButton("Seleccionar Color");
//panel.add(colorPickerButton, gbc);
//gbc.gridx = 3;
//colorPreviewPanel = new JPanel();
//colorPreviewPanel.setPreferredSize(new Dimension(300, 80));
//colorPreviewPanel.setBackground(Color.WHITE);
//panel.add(colorPreviewPanel, gbc);
//
//colorPickerButton.addActionListener(e -> {
//    Color selectedColor = JColorChooser.showDialog(this, "Seleccionar Color", colorPreviewPanel.getBackground());
//    if (selectedColor != null) {
//        colorPreviewPanel.setBackground(selectedColor);
//        colorHexField.setText(String.format("#%02X%02X%02X", selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue()));
//    }
//});
//
//// Numero de empleados (Desplegable con rangos)
//gbc.gridx = 0; gbc.gridy = 2;
//panel.add(new JLabel("Numero de empleados"), gbc);
//gbc.gridx = 1;
//String[] opcionesEmpleados = {"Ente 2 y 10 rempleados", "Entre 10 y 100 empleados", "Entre 100 y 1000 empleados"};
//empleadosComboBox = new JComboBox<>(opcionesEmpleados);
//panel.add(empleadosComboBox, gbc);
//
//// Tipo de Agencia
//gbc.gridx = 0; gbc.gridy = 3;
//panel.add(new JLabel("Tipo de Agencia"), gbc);
//gbc.gridx = 1;
//String[] opcionesTipoAgencia = {"A1", "A2", "A3"};  // foreing key tabla Tipo agencia
//tipoAgenciaComboBox = new JComboBox<>(opcionesTipoAgencia);
//panel.add(tipoAgenciaComboBox, gbc);
//
//// URL del logo
//gbc.gridx = 0; gbc.gridy = 4;
//panel.add(new JLabel("Logo"), gbc);
//gbc.gridx = 1;
//logoUrlField = new JTextField(30);
//panel.add(logoUrlField, gbc);
//
//// Botones
//gbc.gridx = 0; gbc.gridy = 5;
//JButton btnGuardar = new JButton("Guardar");
//panel.add(btnGuardar, gbc);
//gbc.gridx = 1;
//JButton btnVolver = new JButton("Volver a Login");
//panel.add(btnVolver, gbc);
//
//add(panel, BorderLayout.CENTER);
//
//// Evento para validar y guardar
//btnGuardar.addActionListener(e -> guardarEnBaseDeDatos());
//
//// Evento para volver a la pantalla de login
//btnVolver.addActionListener(e -> {
//    Login_old loginFrame = new Login_old();
//    loginFrame.setVisible(true);
//    dispose();
//});
//}
//
//// gaurdar datos en BBDD
//private void guardarEnBaseDeDatos() {
//String nombre = nombreAgenciaField.getText().trim();
//String color = colorHexField.getText().trim();
//String numEmpleados = (String) empleadosComboBox.getSelectedItem();
//String logo = logoUrlField.getText().trim();
//String tipoAgen = (String) tipoAgenciaComboBox.getSelectedItem();
//
//if (nombre.isEmpty() || color.isEmpty() || numEmpleados.isEmpty() || logo.isEmpty() || tipoAgen.isEmpty()) {
//    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
//    return;
//}
//
//GestorAgencia gestorAgencia = new GestorAgencia();
//Agencia nuevaAgencia = new Agencia();
//nuevaAgencia.setNombre(nombre);
//nuevaAgencia.setLogo(logo);
//nuevaAgencia.setColor(color);
//
//
//NumeroEmpleados numeroEmpleados = new NumeroEmpleados();
//switch (numEmpleados) {
//    case "Ente 2 y 10 empleados":
//        numeroEmpleados.setCodigo("L1");
//        break;
//    case "Entre 10 y 100 empleados":
//        numeroEmpleados.setCodigo("L2");
//        break;
//    case "Entre 100 y 1000 empleados":
//        numeroEmpleados.setCodigo("L3");
//        break;
//    default:
//        numeroEmpleados.setCodigo("L1"); // Valor por defecto
//}
//nuevaAgencia.setNumeroEmpleados(numeroEmpleados);
//
//
//TiposAgencia tipoAgenciaObj = new TiposAgencia();
//tipoAgenciaObj.setCodigo(tipoAgen);
//nuevaAgencia.setTipoAgencia(tipoAgenciaObj);
//
//
//gestorAgencia.insertEjemplo(nuevaAgencia);

//        try {
//            Connection con = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
//            String sql = "INSERT INTO Agencia (nombre, logo, color, numero_empleados, tipo_agencia) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setString(1, nombre);
//            stmt.setString(2, logo);
//            stmt.setString(3, color);
//            stmt.setString(4, numeroEmpleados);
//            stmt.setString(5, tipoAgencia);
//
//            int filasAfectadas = stmt.executeUpdate();
//            if (filasAfectadas > 0) {
//                JOptionPane.showMessageDialog(this, "Agencia guardada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
//            }
//
//            stmt.close();
//            con.close();
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error al guardar en la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            ex.printStackTrace();
//        }
