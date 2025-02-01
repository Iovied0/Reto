package agenciaViajes.vista.paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import agenciaViajes.ViajesErrekamari;

public class Bienvenida {

	private JPanel panel = null;
	private JLabel labelImagen;

	/**
	 * Crea el panel "Bienvenida"
	 * 
	 * @param paneles
	 * @param frame
	 */
	public Bienvenida(ArrayList<JPanel> paneles, ViajesErrekamari frame) {

		panel = new JPanel();
		panel.setBounds(0, 0, 900, 700);
		panel.setLayout(null);

		//////////////////////////// TEXTO DEL FONDO \\\\\\\\\\\\\\\\\\\\\\\\\\\\

		JLabel labelBienvenida = new JLabel("PULSE PARA AVANZAR");
		Font fuenteTexto = new Font("Lucida Sans Unicode", Font.PLAIN, 24);
		labelBienvenida.setFont(fuenteTexto);
		Color colorFondoLabel = new Color(255, 255, 255, 100);
		labelBienvenida.setBackground(colorFondoLabel);
		labelBienvenida.setOpaque(true);
		labelBienvenida.setBounds(300, 300, 265, 24);
		panel.add(labelBienvenida);

		//////////////////////////// FONDO DEL PANEL \\\\\\\\\\\\\\\\\\\\\\\\\\\\

		ImageIcon bienvenida = new ImageIcon("src/agenciaViajes/vista/img/bienvenida.jpg");
		labelImagen = new JLabel(bienvenida);
		labelImagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.gotoFormLogin();
			}
		});
		labelImagen.setBounds(0, -106, 887, 750);
		labelImagen.setOpaque(false);
		panel.add(labelImagen);

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
