package agenciaViajes;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import agenciaViajes.bbdd.pojos.Agencia;
import agenciaViajes.vista.paneles.*;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class ViajesErrekamari extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<JPanel> paneles = null;
	private JFrame frame;
	JMenuBar menuBar = new JMenuBar();
	Agencia agenciaLogin = null;

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
		frame.setSize(1300, 900);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Viajes Errekamari");
		frame.setResizable(false);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		menuBar.setBounds(0, 0, 900, 22);
		frame.getContentPane().add(menuBar);
		menuBar.setVisible(false);

		JMenu mnLogOut = new JMenu("Cerrar Sesion");
		mnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logOut();
			}
		});
		menuBar.add(mnLogOut);

		JMenu mnNewTrip = new JMenu("Nuevo Viaje");
		menuBar.add(mnNewTrip);

		JMenu mnNewEvent = new JMenu("Añadir Evento");
		menuBar.add(mnNewEvent);

		JMenuItem nuevoVuelo = new JMenuItem("Nuevo Vuelo");
		nuevoVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("GoTo -> panel3 (Nuevo vuelo)");
//				paneles.get(5).setVisible(false);
//				paneles.get(6).setVisible(true);
			}
		});
		mnNewEvent.add(nuevoVuelo);

		JMenuItem nuevoAlojamiento = new JMenuItem("Nuevo Alojamiento");
		nuevoAlojamiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("GoTo -> panel3 (Nuevo Alojamiento)");
//				paneles.get(5).setVisible(false);
//				paneles.get(7).setVisible(true);
			}
		});
		mnNewEvent.add(nuevoAlojamiento);

		JMenuItem nuevaActividad = new JMenuItem("Nueva Actividad");
		nuevaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("GoTo -> panel3 (Nueva Actividad)");
//				paneles.get(5).setVisible(false);
//				paneles.get(8).setVisible(true);
			}
		});
		mnNewEvent.add(nuevaActividad);

		// Lista que contiene paneles
		paneles = new ArrayList<JPanel>();

		////////////////////// Creación de paneles \\\\\\\\\\\\\\\\\\\\\\

		Bienvenida bienvenida = new Bienvenida(paneles, this);
		JPanel panelBienvenida = bienvenida.getPanel();
		panelBienvenida.setVisible(true);

		FormularioLogin login = new FormularioLogin(paneles, this);
		JPanel panelLogin = login.getPanel();
		panelLogin.setVisible(false);

		NuevaAgencia nuevaAgencia = new NuevaAgencia(paneles, this);
		JPanel panelNuevaAgencia = nuevaAgencia.getPanel();
		panelNuevaAgencia.setVisible(false);

		NuevoViaje nuevoViaje = new NuevoViaje(paneles, this);
		JPanel panelNuevoViaje = nuevoViaje.getPanel();
		panelNuevoViaje.setVisible(false);

		NuevoEvento nuevoEvento = new NuevoEvento(paneles, this);
		JPanel panelnuevoEvento = nuevoEvento.getPanel();
		panelnuevoEvento.setVisible(false);

		// lo metemos en el array y en la ventana
		paneles.add(0, panelBienvenida);
		frame.getContentPane().add(panelBienvenida);

		paneles.add(1, panelLogin);
		frame.getContentPane().add(panelLogin);

		paneles.add(2, panelNuevaAgencia);
		frame.getContentPane().add(panelNuevaAgencia);

		paneles.add(3, panelNuevoViaje);
		frame.getContentPane().add(panelNuevoViaje);

		paneles.add(4, panelnuevoEvento);
		frame.getContentPane().add(panelnuevoEvento);

	}

	////////////////////// Cambio de panel por función \\\\\\\\\\\\\\\\\\\\\\

	public void logOut() {
		paneles.get(0).setVisible(true);
		paneles.get(1).setVisible(false);
		paneles.get(2).setVisible(false);
		paneles.get(3).setVisible(false);
		paneles.get(4).setVisible(false);
		if (paneles.size() >= 6) {
			paneles.get(5).setVisible(false);
		}
		menuBar.setVisible(false);
		agenciaLogin = null;
	}

	public void gotoFormLogin() {
		paneles.get(0).setVisible(false);
		paneles.get(1).setVisible(true);
		paneles.get(2).setVisible(false);
		paneles.get(3).setVisible(false);
		paneles.get(4).setVisible(false);
		if (paneles.size() >= 6) {
			paneles.get(5).setVisible(false);
		}

	}

	public void gotoNuevaAgencia() {
		paneles.get(0).setVisible(false);
		paneles.get(1).setVisible(false);
		paneles.get(2).setVisible(true);
		paneles.get(3).setVisible(false);
		paneles.get(4).setVisible(false);
		if (paneles.size() >= 6) {
			paneles.get(5).setVisible(false);
		}
	}

	public void gotoNuevoViaje() {
		paneles.get(0).setVisible(false);
		paneles.get(1).setVisible(false);
		paneles.get(2).setVisible(false);
		paneles.get(3).setVisible(true);
		paneles.get(4).setVisible(false);
		if (paneles.size() >= 6) {
			paneles.get(5).setVisible(false);
		}
	}

	public void gotoNuevoEvento() {
		paneles.get(0).setVisible(false);
		paneles.get(1).setVisible(false);
		paneles.get(2).setVisible(false);
		paneles.get(3).setVisible(false);
		paneles.get(4).setVisible(true);
		if (paneles.size() >= 6) {
			paneles.get(5).setVisible(false);
		}
	}

	public void gotoViajes() {

		ViajesyEventos viajesyEventos = new ViajesyEventos(paneles, this);
		JPanel panelViajesyEventos = viajesyEventos.getPanel();

		paneles.add(5, panelViajesyEventos);
		frame.getContentPane().add(panelViajesyEventos);

		paneles.get(0).setVisible(false);
		paneles.get(1).setVisible(false);
		paneles.get(2).setVisible(false);
		paneles.get(3).setVisible(false);
		paneles.get(4).setVisible(false);
		paneles.get(5).setVisible(true);

		menuBar.setVisible(true);
	}

}
