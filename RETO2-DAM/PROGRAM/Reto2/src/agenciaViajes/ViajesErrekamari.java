package agenciaViajes;

import java.awt.Component;
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
		mnNewTrip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gotoNuevoViaje();
			}
		});
		menuBar.add(mnNewTrip);

		JMenu mnNewEvent = new JMenu("Añadir Evento");
		menuBar.add(mnNewEvent);

		JMenuItem miNuevoVuelo = new JMenuItem("Nuevo Vuelo");
		miNuevoVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoNuevoVuelo();
			}
		});
		mnNewEvent.add(miNuevoVuelo);

		JMenuItem miNuevoAlojamiento = new JMenuItem("Nuevo Alojamiento");
		miNuevoAlojamiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoNuevoAlojamiento();
			}
		});
		mnNewEvent.add(miNuevoAlojamiento);

		JMenuItem miNuevaActividad = new JMenuItem("Nueva Actividad");
		miNuevaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoNuevaActividad();
			}
		});
		mnNewEvent.add(miNuevaActividad);

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

		// lo metemos en el array y en la ventana
		paneles.add(0, panelBienvenida);
		frame.getContentPane().add(panelBienvenida);

		paneles.add(1, panelLogin);
		frame.getContentPane().add(panelLogin);

		paneles.add(2, panelNuevaAgencia);
		frame.getContentPane().add(panelNuevaAgencia);

	}

	////////////////////// Cambio de panel por función \\\\\\\\\\\\\\\\\\\\\\

	public void logOut() {
		paneles.get(0).setVisible(true); // Mostrar el panel principal
		paneles.get(1).setVisible(false); // Ocultar el panel de login
		paneles.get(2).setVisible(false); // Ocultar el panel de "Nueva Agencia"

		// Eliminar cualquier panel extra
		while (paneles.size() > 3) {
			JPanel panel = paneles.remove(paneles.size() - 1);
			frame.getContentPane().remove(panel);
		}

		menuBar.setVisible(false);
		agenciaLogin = null;

		// Actualizar la ventana
		frame.revalidate();
		frame.repaint();
	}

	public void gotoFormLogin() {
		paneles.get(0).setVisible(false);
		paneles.get(1).setVisible(true);
		paneles.get(2).setVisible(false);
	}

	public void gotoNuevaAgencia() {

		paneles.get(0).setVisible(false);
		paneles.get(1).setVisible(false);
		paneles.get(2).setVisible(true);
	}

	public void gotoViajes() {

		ViajesyEventos viajesyEventos = new ViajesyEventos(paneles, this);
		JPanel panelViajesyEventos = viajesyEventos.getPanel();

		gestionarPaneles(panelViajesyEventos);

		menuBar.setVisible(true);

	}

	public void gotoNuevoViaje() {

		NuevoViaje nuevoViaje = new NuevoViaje(paneles, this);
		JPanel panelNuevoViaje = nuevoViaje.getPanel();

		gestionarPaneles(panelNuevoViaje);

	}

	public void gotoNuevoVuelo() {

		NuevoVuelo nuevoVuelo = new NuevoVuelo(paneles, this);
		JPanel panelNuevoVuelo = nuevoVuelo.getPanel();

		gestionarPaneles(panelNuevoVuelo);

	}

	public void gotoNuevoAlojamiento() {

		NuevoAlojamiento nuevoAlojamiento = new NuevoAlojamiento(paneles, this);
		JPanel panelNuevoAlojamiento = nuevoAlojamiento.getPanel();

		gestionarPaneles(panelNuevoAlojamiento);

	}

	public void gotoNuevaActividad() {

		NuevaActividad nuevaActividad = new NuevaActividad(paneles, this);
		JPanel panelNuevaActividad = nuevaActividad.getPanel();

		gestionarPaneles(panelNuevaActividad);

	}

	private void gestionarPaneles(JPanel nuevoPanel) {
		// Eliminar el panel si ya existe
		for (Component panelContentPane : frame.getContentPane().getComponents()) {
			if (panelContentPane.equals(nuevoPanel)) {
				frame.getContentPane().remove(panelContentPane);
				break;
			}
		}

		// Ocultar todos los paneles actuales
		for (JPanel panel : paneles) {
			panel.setVisible(false);
		}

		// Agregar el nuevo panel si no está en la lista
		if (!paneles.contains(nuevoPanel)) {
			paneles.add(nuevoPanel);
		}

		frame.getContentPane().add(nuevoPanel);
		nuevoPanel.setVisible(true);

		// Actualizar el frame
		frame.revalidate();
		frame.repaint();
	}

}
