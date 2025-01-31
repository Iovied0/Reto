package agenciaViajes.vista.panel;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import agenciaViajes.bbdd.gestores.GestorAgencia;
import agenciaViajes.bbdd.pojos.Agencia;
import agenciaViajes.bbdd.pojos.NumeroEmpleados;
import agenciaViajes.bbdd.pojos.TiposAgencia;

public class NuevaAgencia extends JFrame {
 
	private static final long serialVersionUID = -4181883082017603383L;
	
	private JTextField nombreAgenciaField;
    private JComboBox<String> empleadosComboBox;
    private JComboBox<String> tipoAgenciaComboBox;
    private JTextField logoUrlField;
    private JPanel colorPreviewPanel;
    private JTextField colorHexField;

    public NuevaAgencia() {
        setTitle("Nueva Agencia - Viajes Erreka-Mari");
        setSize(800, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Los componentes se expanden horizontalmente

        // Nombre de agencia
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0.2; // Asignar un peso menor a la etiqueta
        panel.add(new JLabel("Nombre de Agencia"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 0.8; // Asignar un peso mayor al campo de texto
        nombreAgenciaField = new JTextField();
        panel.add(nombreAgenciaField, gbc);

        // Color de marca, Codigo Hexadecimal, Boton Seleccionar Color, Vista del Color
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.2;
        panel.add(new JLabel("Color de Marca"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 0.3;
        colorHexField = new JTextField();
        colorHexField.setEditable(false);
        panel.add(colorHexField, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        gbc.weightx = 0.2;
        JButton colorPickerButton = new JButton("Seleccionar Color");
        panel.add(colorPickerButton, gbc);

        gbc.gridx = 3; gbc.gridy = 1;
        gbc.weightx = 0.3;
        colorPreviewPanel = new JPanel();
        colorPreviewPanel.setPreferredSize(new Dimension(100, 30)); // Tamanio fijo para el panel de color
        colorPreviewPanel.setBackground(Color.WHITE);
        panel.add(colorPreviewPanel, gbc);

        colorPickerButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(this, "Seleccionar Color", colorPreviewPanel.getBackground());
            if (selectedColor != null) {
                colorPreviewPanel.setBackground(selectedColor);
                colorHexField.setText(String.format("#%02X%02X%02X", selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue()));
            }
        });

        // Numero de empleados (Desplegable con rangos)
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.weightx = 0.2;
        panel.add(new JLabel("Número de empleados"), gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.weightx = 0.8;
        String[] opcionesEmpleados = {"Entre 2 y 10 empleados", "Entre 10 y 100 empleados", "Entre 100 y 1000 empleados"};
        empleadosComboBox = new JComboBox<>(opcionesEmpleados);
        panel.add(empleadosComboBox, gbc);

        // Tipo de Agencia
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.weightx = 0.2;
        panel.add(new JLabel("Tipo de Agencia"), gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        gbc.weightx = 0.8;
        String[] opcionesTipoAgencia = {"A1", "A2", "A3"};  // Clave foranea de la tabla TipoAgencia
        tipoAgenciaComboBox = new JComboBox<>(opcionesTipoAgencia);
        panel.add(tipoAgenciaComboBox, gbc);

        // URL del logo
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.weightx = 0.2;
        panel.add(new JLabel("Logo (URL)"), gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        gbc.weightx = 0.8;
        logoUrlField = new JTextField();
        panel.add(logoUrlField, gbc);

        // Botones
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2; // Ocupar dos columnas
        gbc.weightx = 1.0;
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(new Dimension(150, 30)); 
        panel.add(btnGuardar, gbc);

        gbc.gridx = 2; gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton btnVolver = new JButton("Volver a Login");
        btnVolver.setPreferredSize(new Dimension(150, 30)); 
        panel.add(btnVolver, gbc);

        add(panel, BorderLayout.CENTER);
        
     // Agregar una imagen al panel
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 4; // Ocupar cuatro columnas
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Expandir verticalmente
        gbc.fill = GridBagConstraints.BOTH; // Expandir en ambas direcciones

        // Cargar la imagen desde un archivo
        ImageIcon imagenIcon = new ImageIcon("img/bienvenida.jpg"); 
        JLabel imagenLabel = new JLabel(imagenIcon);
        panel.add(imagenLabel, gbc);

        add(panel, BorderLayout.CENTER);

        // Evento para validar y guardar
        btnGuardar.addActionListener(e -> guardarEnBaseDeDatos());

        // Evento para volver a la pantalla de login
        btnVolver.addActionListener(e -> {
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
            dispose();
        });
    }
    
    private NumeroEmpleados determinarLimiteEmpleados(String numeroEmpleados) {
        switch (numeroEmpleados) {
            case "Entre 2 y 10 empleados":
                return new NumeroEmpleados("L1", "Entre 2 y 10 empleados");
            case "Entre 10 y 100 empleados":
                return new NumeroEmpleados("L2", "Entre 10 y 100 empleados");
            case "Entre 100 y 1000 empleados":
                return new NumeroEmpleados("L3", "Entre 100 y 1000 empleados");
            default:
                return new NumeroEmpleados("L1", "Entre 2 y 10 empleados"); // Valor por defecto
        }
    }
    // gaurdar datos 
    private void guardarEnBaseDeDatos() {
      
        String nombre = nombreAgenciaField.getText().trim();
        String color = colorHexField.getText().trim();
        String logo = logoUrlField.getText().trim();
        String numEmpleados = (String) empleadosComboBox.getSelectedItem();
        String tipoAgen = (String) tipoAgenciaComboBox.getSelectedItem();

        // limiteEmpleados no incluye en la vetana Nueva Agencia pero en la tabla agencia bbdd es un foreing key (tabla NumeroEmpleados)  
        NumeroEmpleados limiteEmpleados = determinarLimiteEmpleados(numEmpleados);

        if (nombre.isEmpty() || color.isEmpty() || numEmpleados.isEmpty() || logo.isEmpty() || tipoAgen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Agencia agencia = new Agencia();
        agencia.setNombre(nombre);
        agencia.setLogo(logo);
        agencia.setColor(color);
        agencia.setNumeroEmpleados(numEmpleados);
        agencia.setLimiteEmpleados(limiteEmpleados); // pojo NumeroEmpleados

        TiposAgencia tipoAgenciaObj = new TiposAgencia();
        tipoAgenciaObj.setCodigo(tipoAgen);
        agencia.setTipoAgencia(tipoAgenciaObj);

        GestorAgencia gestorAgencia = new GestorAgencia();
        gestorAgencia.insertEjemplo(agencia);

        JOptionPane.showMessageDialog(this, "Agencia guardada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Agencia guardada correctamente.");
    }
    
}
