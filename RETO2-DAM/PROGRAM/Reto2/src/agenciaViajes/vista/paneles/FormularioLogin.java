package agenciaViajes.vista.paneles;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import agenciaViajes.ViajesErrekamari;
import agenciaViajes.bbdd.pojos.Agencia;
import agenciaViajes.controlador.Controlador;

public class FormularioLogin {
	private JPanel panel = null;

	/**
	 * Crea el panel "FormularioLogin"
	 * 
	 * @param paneles
	 * @param frame
	 */
	public FormularioLogin(ArrayList<JPanel> paneles, ViajesErrekamari frame) {

		panel = new JPanel();
		panel.setBounds(0, 0, 1300, 900);
		panel.setLayout(null);

		//////////////////// USUARIO \\\\\\\\\\\\\\\\\\\\
		JLabel labelUsuario = new JLabel("Nombre de Usuario");
		labelUsuario.setBounds(300, 250, 150, 25);
		panel.add(labelUsuario);

		JTextField textUsuario = new JTextField();
		textUsuario.setBounds(450, 250, 150, 25);
		panel.add(textUsuario);

		//////////////////// CONTRASEÑA \\\\\\\\\\\\\\\\\\\\
		JLabel labelPassword = new JLabel("Contraseña");
		labelPassword.setBounds(300, 300, 150, 25);
		panel.add(labelPassword);

		JPasswordField textPassword = new JPasswordField();
		textPassword.setBounds(450, 300, 150, 25);
		panel.add(textPassword);

		/////////////////////// MOSTRAR/OCULTAR CONTRASEÑA \\\\\\\\\\\\\\\\\\\\\\\
		ImageIcon ojoAbierto = new ImageIcon("src/agenciaViajes/vista/img/abierto.png");
		ImageIcon ojoCerrado = new ImageIcon("src/agenciaViajes/vista/img/cerrado.png");
		JButton mostrarOcultarContrasenya = new JButton();
		Image abiertoEscalado = ojoAbierto.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		Image cerradoEscalado = ojoCerrado.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

		mostrarOcultarContrasenya.setBounds(600, 300, 27, 25);
		mostrarOcultarContrasenya.setIcon(new ImageIcon(cerradoEscalado));
		mostrarOcultarContrasenya.setFocusPainted(false); // Quitar el borde de foco
		mostrarOcultarContrasenya.setContentAreaFilled(false); // Quitar el fondo del botón
		mostrarOcultarContrasenya.setBorderPainted(false);
		panel.add(mostrarOcultarContrasenya);

//		********************** ACTION LISTENERS MOSTRAR/OCULTAR CONTRASEÑA **********************
		mostrarOcultarContrasenya.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textPassword.getEchoChar() == '•') { // contraseña oculta
					mostrarOcultarContrasenya.setIcon(new ImageIcon(abiertoEscalado));
					textPassword.setEchoChar((char) 0); // muestra la contraseña
				} else {// contraseña visible
					mostrarOcultarContrasenya.setIcon(new ImageIcon(cerradoEscalado));
					textPassword.setEchoChar('•'); // oculta la contraseña
				}

			}
		});

		//////////////////// BOTON PARA INICIAR SESIÓN \\\\\\\\\\\\\\\\\\\\
		JButton btnLogin = new JButton("Iniciar Sesión");
		btnLogin.setBounds(300, 365, 120, 25);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login(textUsuario.getText(), textPassword.getPassword(), frame);
				reset(textUsuario, textPassword);
			}
		});
		textPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login(textUsuario.getText(), textPassword.getPassword(), frame);
					reset(textUsuario, textPassword);
				}
			}
		});
		panel.add(btnLogin);

		//////////////////// BOTON PARA CREAR NUEVA AGENCIA \\\\\\\\\\\\\\\\\\\\
		JButton btnNuevaAgencia = new JButton("Crear Nueva Agencia");
		btnNuevaAgencia.setBounds(450, 365, 160, 25);
		btnNuevaAgencia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.gotoNuevaAgencia();
			}
		});

		panel.add(btnNuevaAgencia);

		//////////////////////////// Fondo del panel \\\\\\\\\\\\\\\\\\\\\\\\\\\\

		JLabel labelFondoLogin = new JLabel();
		Color colorFondoLabel = new Color(238, 238, 238);
		labelFondoLogin.setBackground(colorFondoLabel);
		labelFondoLogin.setOpaque(true);
		labelFondoLogin.setBounds(0, 150, 900, 300);
		panel.add(labelFondoLogin);

		ImageIcon bienvenida = new ImageIcon("src/agenciaViajes/vista/img/bienvenida.jpg");
		Image bienvenidaReescalado = bienvenida.getImage().getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		JLabel labelImagen = new JLabel(new ImageIcon(bienvenidaReescalado));
		labelImagen.setBounds(0, 0, 1300, 900);
		labelImagen.setOpaque(false);
		panel.add(labelImagen);

	}
	//////////////////////////// FUNCIONES \\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void reset(JTextField textUsuario, JPasswordField contrasenya) {
		textUsuario.setText("");
		contrasenya.setText("");
	}

	/**
	 * Devuelve el panel
	 * 
	 * @return panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	private void login(String user, char[] passwd, ViajesErrekamari frame) {
		ArrayList<Agencia> agencias = new ArrayList<Agencia>();
		Controlador controlador = Controlador.getInstanceControlador();
		String contraseña = new String(passwd);
		Boolean login = false;
		agencias = controlador.getAgencias();

		for (Agencia agencia : agencias) {
			if (agencia.getNombre().equalsIgnoreCase(user) && agencia.getContraseña().equals(contraseña)) {
				login = true;
				controlador.setInstanceAgencia(agencia);
				frame.gotoViajes();

			}
		}
		if (login == false) {
			JOptionPane.showMessageDialog(null, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {

		}
	}
}
