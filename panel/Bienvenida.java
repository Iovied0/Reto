package agenciaViajes.vista.panel;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


// Ventana de Bienvenida
class Bienvenida extends JFrame {

	public Bienvenida() {
        setTitle("Bienvenida - Viajes Erreka-Marri");
        setSize(800, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Imagen de bienvenida
      //  JLabel labelImagen = new JLabel(new ImageIcon("img/bienvenida.jpg")); // Ruta de la imagen
      //  panel.add(labelImagen, BorderLayout.CENTER);

        // Boton para continuar
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.addActionListener(e -> {
            Login login = new Login();
            login.setVisible(true);
            dispose(); // Cerrar ventana actual
        });
        panel.add(btnContinuar, BorderLayout.SOUTH);

        add(panel);
    }
}
