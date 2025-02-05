
package agenciaViajes.vista.paneles;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import agenciaViajes.ViajesErrekamari;
import agenciaViajes.bbdd.pojos.Pais;
import agenciaViajes.bbdd.pojos.TipoViaje;
import agenciaViajes.controlador.Controlador;

public class NuevoViaje {

    public JPanel panel;
    public Controlador controlador;

    public NuevoViaje(ArrayList<JPanel> paneles, ViajesErrekamari frame) {
        panel = new JPanel();
        panel.setBounds(0, 0, 1300, 900);
        panel.setLayout(null);

        controlador = Controlador.getInstanceControlador();

        JLabel labelTitulo = new JLabel("INTRODUZCA LOS PARÁMETROS DEL NUEVO VIAJE");
        labelTitulo.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 20));
        labelTitulo.setBounds(15, 20, 600, 25);
        panel.add(labelTitulo);

        JLabel labelNombre = new JLabel("Nombre del viaje");
        labelNombre.setBounds(30, 100, 150, 25);
        panel.add(labelNombre);

        JTextField textNombre = new JTextField();
        textNombre.setBounds(175, 100, 150, 25);
        panel.add(textNombre);

        JLabel labelDescripcion = new JLabel("Descripción del viaje");
        labelDescripcion.setBounds(30, 140, 150, 25);
        panel.add(labelDescripcion);

        JTextArea textDescripcion = new JTextArea();
        textDescripcion.setBounds(175, 140, 400, 100);
        panel.add(textDescripcion);

        JLabel labelFechaInicio = new JLabel("Fecha de inicio");
        labelFechaInicio.setBounds(30, 260, 150, 25);
        panel.add(labelFechaInicio);

        UtilDateModel modelInicio = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanelInicio = new JDatePanelImpl(modelInicio, p);
        JDatePickerImpl datePickerInicio = new JDatePickerImpl(datePanelInicio, new DateLabelFormatter());
        datePickerInicio.setBounds(175, 260, 150, 25);
        panel.add(datePickerInicio);

        JLabel labelFechaFin = new JLabel("Fecha de fin");
        labelFechaFin.setBounds(30, 300, 150, 25);
        panel.add(labelFechaFin);

        UtilDateModel modelFin = new UtilDateModel();
        JDatePanelImpl datePanelFin = new JDatePanelImpl(modelFin, p);
        JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanelFin, new DateLabelFormatter());
        datePickerFin.setBounds(175, 300, 150, 25);
        panel.add(datePickerFin);

        JLabel labelDuracion = new JLabel("Duración (días)");
        labelDuracion.setBounds(30, 340, 150, 25);
        panel.add(labelDuracion);

        JTextField textDuracion = new JTextField();
        textDuracion.setBounds(175, 340, 150, 25);
        textDuracion.setEditable(false);
        panel.add(textDuracion);

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Date fechaInicio = (Date) datePickerInicio.getModel().getValue();
                Date fechaFin = (Date) datePickerFin.getModel().getValue();
                if (fechaInicio != null && fechaFin != null) {
                    long duracion = (fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24);
                    textDuracion.setText(String.valueOf(duracion));
                } else {
                    textDuracion.setText("");
                }
            }
        };

        datePickerInicio.getModel().addChangeListener(changeListener);
        datePickerFin.getModel().addChangeListener(changeListener);

        JLabel labelServicios = new JLabel("Servicios no incluidos");
        labelServicios.setBounds(30, 380, 150, 25);
        panel.add(labelServicios);

        JTextArea textServicios = new JTextArea();
        textServicios.setBounds(175, 380, 400, 100);
        panel.add(textServicios);

        JLabel labelTipoViaje = new JLabel("Tipo de viaje");
        labelTipoViaje.setBounds(30, 500, 150, 25);
        panel.add(labelTipoViaje);

        JComboBox<String> comboTipoViaje = new JComboBox<>();
        ArrayList<TipoViaje> tiposViaje = controlador.getTiposViaje();
        for (TipoViaje tipoViaje : tiposViaje) {
            comboTipoViaje.addItem(tipoViaje.getDescripcion());
        }
        comboTipoViaje.setBounds(175, 500, 190, 25);
        panel.add(comboTipoViaje);

        JLabel labelPais = new JLabel("País destino");
        labelPais.setBounds(30, 540, 150, 25);
        panel.add(labelPais);

        JComboBox<String> comboPais = new JComboBox<>();
        ArrayList<Pais> paises = controlador.mostrarPaises();
        for (Pais pais : paises) {
            comboPais.addItem(pais.getNombre());
        }
        comboPais.setBounds(175, 540, 190, 25);
        panel.add(comboPais);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(120, 600, 150, 25);
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pendiente hacer que se guarden los datos en la base de datos
            }
        });
        panel.add(btnConfirmar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(400, 600, 150, 25);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Boton de cancelar que lleve a donde haga falta
                frame.gotoViajes();
            }
        });
        panel.add(btnCancelar);
    }

    public JPanel getPanel() {
        return panel;
    }

    @SuppressWarnings("serial")
	class DateLabelFormatter extends AbstractFormatter {
        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parse(text);
        }

        @Override
        public String valueToString(Object value) {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Nuevo Viaje Test");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(900, 700);
//
//        ArrayList<JPanel> paneles = new ArrayList<>();
//        ViajesErrekamari viajesErrekamari = new ViajesErrekamari();
//        NuevoViaje nuevoViaje = new NuevoViaje(paneles, viajesErrekamari);
//
//        frame.add(nuevoViaje.getPanel());
//        frame.setVisible(true);
//    }
}
