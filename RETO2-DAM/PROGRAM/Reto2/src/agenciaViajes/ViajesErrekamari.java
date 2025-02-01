package agenciaViajes;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import agenciaViajes.vista.paneles.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class ViajesErrekamari extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<JPanel> paneles = null;
	private JFrame frame;
	JMenuBar menuBar = new JMenuBar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ViajesErrekamari().frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViajesErrekamari() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Viajes Errekamari");

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		menuBar.setBounds(0, 0, 900, 22);
		frame.getContentPane().add(menuBar);

		JMenu mnLogOut = new JMenu("Cerrar Sesion");
		mnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logOut();
			}
		});
		menuBar.add(mnLogOut);

		JMenu mnMenu2 = new JMenu("Menu");
		menuBar.add(mnMenu2);
		menuBar.setVisible(false);

		// Lista que contiene paneles
		paneles = new ArrayList<JPanel>();

		////////////////////// Creación de paneles \\\\\\\\\\\\\\\\\\\\\\

		Bienvenida bienvenida = new Bienvenida(paneles, this);
		JPanel panelBienvenida = bienvenida.getPanel();
		panelBienvenida.setVisible(true);

		FormularioLogin login = new FormularioLogin(paneles, this);
		JPanel panelLogin = login.getPanel();
		panelLogin.setVisible(false);

		Viajes viaje = new Viajes(paneles, this);
		JPanel panelViaje = viaje.getPanel();
		panelViaje.setVisible(false);

		NuevaAgencia nuevaAgencia = new NuevaAgencia(paneles, this);
		JPanel panelNuevaAgencia = nuevaAgencia.getPanel();
		panelNuevaAgencia.setVisible(false);

		// lo metemos en el array y en la ventana
		paneles.add(panelBienvenida);
		frame.getContentPane().add(panelBienvenida);

		paneles.add(panelLogin);
		frame.getContentPane().add(panelLogin);

		paneles.add(panelViaje);
		frame.getContentPane().add(panelViaje);

		paneles.add(panelNuevaAgencia);
		frame.getContentPane().add(panelNuevaAgencia);

//		JMenuItem miRojo = new JMenuItem("Cerrar Sesion");
//		miRojo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("GoTo -> panel1 (Rojo)");
//				paneles.get(0).setVisible(true);
//				paneles.get(1).setVisible(false);
//				paneles.get(2).setVisible(false);
//				
//			}
//		});
//		mnMenu.add(miRojo);

//		JMenuItem miVerde = new JMenuItem("Ver Fichero");
//		miVerde.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("GoTo -> panel2 (Verde)");
//				paneles.get(0).setVisible(false);
//				paneles.get(1).setVisible(true);
//				paneles.get(2).setVisible(false);
//			}
//		});
//		mnMenu.add(miVerde);

//		JMenuItem miAzul = new JMenuItem("Añadir Texto");
//		miAzul.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("GoTo -> panel3 (Azul)");
//				paneles.get(0).setVisible(false);
//				paneles.get(1).setVisible(false);
//				paneles.get(2).setVisible(true);
//			}
//		});
//		mnMenu.add(miAzul);

	}

	////////////////////// Cambio de panel por función \\\\\\\\\\\\\\\\\\\\\\

	public void logOut() {
		paneles.get(0).setVisible(true);
		paneles.get(1).setVisible(false);
		paneles.get(2).setVisible(false);
		paneles.get(3).setVisible(false);
		menuBar.setVisible(false);
	}

	public void gotoFormLogin() {
		paneles.get(0).setVisible(false);
		paneles.get(1).setVisible(true);
		paneles.get(2).setVisible(false);
		paneles.get(3).setVisible(false);
	}

	public void gotoViajes() {
		paneles.get(0).setVisible(false);
		paneles.get(1).setVisible(false);
		paneles.get(2).setVisible(true);
		paneles.get(3).setVisible(false);
		
		menuBar.setVisible(true);
	}

	public void gotoNuevaAgencia() {
		paneles.get(0).setVisible(false);
		paneles.get(1).setVisible(false);
		paneles.get(2).setVisible(false);
		paneles.get(3).setVisible(true);
	}

}
