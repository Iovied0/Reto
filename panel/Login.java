package agenciaViajes.vista.panel;

import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {

    public Login() {
        setTitle("Login - Viajes Erreka-Mari");
        setSize(800, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo de usuario
        JLabel labelUsuario = new JLabel("Nombre de Agencia:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelUsuario, gbc);

        JTextField textUsuario = new JTextField(20);
        gbc.gridx = 1;
        panel.add(textUsuario, gbc);

        // Campo de contrasena
        JLabel labelPassword = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelPassword, gbc);

        JPasswordField textPassword = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(textPassword, gbc);

        // Boton de inicio de sesion
        JButton btnLogin = new JButton("Iniciar Sesión");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(btnLogin, gbc);

        btnLogin.addActionListener(e -> {
            String usuario = textUsuario.getText();
            String password = new String(textPassword.getPassword());

            if (usuario.equals("travel") && password.equals("123")) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botón para crear nueva agencia
        JButton btnNuevaAgencia = new JButton("Crear Nueva Agencia");
        gbc.gridy = 3;
        panel.add(btnNuevaAgencia, gbc);

        btnNuevaAgencia.addActionListener(e -> {
            NuevaAgencia nuevaAgencia = new NuevaAgencia();
            nuevaAgencia.setVisible(true);
            dispose();
        });

        add(panel);
    }
}
