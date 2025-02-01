package agenciaViajes.vista.paneles;

import java.awt.Color;

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

import com.mysql.cj.x.protobuf.MysqlxSession.Reset;

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
		panel.setBounds(0, 0, 900, 700);
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

		//////////////////// BOTON PARA INICIAR SESIÓN \\\\\\\\\\\\\\\\\\\\
		JButton btnLogin = new JButton("Iniciar Sesión");
		btnLogin.setBounds(300, 365, 120, 25);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<Agencia> agencias = new ArrayList<Agencia>();
				Controlador controlador = new Controlador();
				char[] contraseñaChar = textPassword.getPassword();
				String contraseña = new String(contraseñaChar);
				Boolean login = false;
				agencias = controlador.getAgencias();

				for (Agencia agencia : agencias) {

					if (agencia.getNombre().equalsIgnoreCase(textUsuario.getText())
							&& agencia.getContraseña().equals(contraseña)) {
						login = true;
					}
				}
				if (login = false) {
					JOptionPane.showMessageDialog(null, "Credenciales incorrectas.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					frame.gotoViajes();
					reset();
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
		JLabel labelImagen = new JLabel(bienvenida);
		labelImagen.setBounds(0, -106, 887, 750);
		labelImagen.setOpaque(false);
		panel.add(labelImagen);

	}
	//////////////////////////// FUNCIONES \\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void reset() {

	}

	/**
	 * Devuelve el panel
	 * 
	 * @return panel
	 */
	public JPanel getPanel() {
		return panel;
	}
}
