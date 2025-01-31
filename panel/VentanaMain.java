package agenciaViajes.vista.panel;

import javax.swing.SwingUtilities;

public class VentanaMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Bienvenida bienvenida = new Bienvenida();
                bienvenida.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error al iniciar la aplicacion: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
