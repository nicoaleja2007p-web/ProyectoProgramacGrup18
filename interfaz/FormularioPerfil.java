package interfaz;

import negocio.GestorPerfil;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormularioPerfil extends JPanel {

    private VentanaPrincipal ventana;
    private GestorPerfil gestorPerfil;

    private JLabel labelTitulo;
    private JLabel labelEstilo;
    private JLabel labelGasto;

    // Multi-selección de estilos con checkboxes
    private JCheckBox checkAventura;
    private JCheckBox checkCultural;
    private JCheckBox checkGastronomico;
    private JCheckBox checkRelajacion;

    private JTextField campoGasto;
    private JButton botonGuardar;
    private JButton botonVerHistorial;
    private JLabel labelMensaje;

    public FormularioPerfil(VentanaPrincipal ventana, GestorPerfil gestorPerfil) {
        this.ventana = ventana;
        this.gestorPerfil = gestorPerfil;
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
    }

    private void inicializarComponentes() {
        setBackground(new Color(245, 245, 250));

        labelTitulo = new JLabel("Configura tu perfil de viaje");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setForeground(new Color(41, 98, 160));

        labelEstilo = new JLabel("Estilos de viaje (elige uno o mas):");
        labelEstilo.setFont(new Font("Arial", Font.PLAIN, 14));

        labelGasto = new JLabel("Gasto diario maximo ($):");
        labelGasto.setFont(new Font("Arial", Font.PLAIN, 14));

        checkAventura     = new JCheckBox("Aventura");
        checkCultural     = new JCheckBox("Cultural");
        checkGastronomico = new JCheckBox("Gastronomico");
        checkRelajacion   = new JCheckBox("Relajacion");

        for (JCheckBox cb : new JCheckBox[]{checkAventura, checkCultural, checkGastronomico, checkRelajacion}) {
            cb.setFont(new Font("Arial", Font.PLAIN, 13));
            cb.setBackground(new Color(245, 245, 250));
        }

        campoGasto = new JTextField(15);

        botonGuardar = new JButton("Guardar y Continuar");
        botonGuardar.setFont(new Font("Arial", Font.BOLD, 13));
        botonGuardar.setBackground(new Color(41, 98, 160));
        botonGuardar.setForeground(Color.WHITE);
        botonGuardar.setFocusPainted(false);

        botonVerHistorial = new JButton("Ver mi historial de viajes");
        botonVerHistorial.setFont(new Font("Arial", Font.PLAIN, 13));
        botonVerHistorial.setBackground(new Color(200, 200, 200));
        botonVerHistorial.setFocusPainted(false);

        labelMensaje = new JLabel(" ");
        labelMensaje.setFont(new Font("Arial", Font.ITALIC, 12));
        labelMensaje.setForeground(Color.RED);
    }

    private void configurarLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelTitulo, gbc);

        gbc.gridwidth = 2; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        add(labelEstilo, gbc);

        gbc.gridwidth = 1; gbc.gridy = 2; gbc.gridx = 0;
        add(checkAventura, gbc);
        gbc.gridx = 1;
        add(checkCultural, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(checkGastronomico, gbc);
        gbc.gridx = 1;
        add(checkRelajacion, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.EAST;
        add(labelGasto, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(campoGasto, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelMensaje, gbc);

        gbc.gridy = 6; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.EAST;
        add(botonGuardar, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(botonVerHistorial, gbc);
    }

    private void configurarEventos() {
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarPerfil();
            }
        });

        botonVerHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarHistorial();
            }
        });
    }

    private void guardarPerfil() {
        // Recoger estilos seleccionados
        ArrayList<String> estilosSeleccionados = new ArrayList<String>();
        if (checkAventura.isSelected())     estilosSeleccionados.add("Aventura");
        if (checkCultural.isSelected())     estilosSeleccionados.add("Cultural");
        if (checkGastronomico.isSelected()) estilosSeleccionados.add("Gastronomico");
        if (checkRelajacion.isSelected())   estilosSeleccionados.add("Relajacion");

        if (estilosSeleccionados.size() == 0) {
            labelMensaje.setText("Error: Selecciona al menos un estilo de viaje.");
            return;
        }

        String gastoTexto = campoGasto.getText().trim();
        if (gastoTexto.equals("")) {
            labelMensaje.setText("Error: El gasto diario es obligatorio.");
            return;
        }

        double gasto = 0.0;
        try {
            gasto = Double.parseDouble(gastoTexto);
        } catch (NumberFormatException ex) {
            labelMensaje.setText("Error: El gasto debe ser un numero valido.");
            return;
        }

        if (gasto <= 0) {
            labelMensaje.setText("Error: El gasto debe ser mayor a cero.");
            return;
        }

        // Construir categorías y puntajes combinados de todos los estilos
        ArrayList<String> categorias   = new ArrayList<String>();
        ArrayList<Double> puntajes     = new ArrayList<Double>();

        for (int e = 0; e < estilosSeleccionados.size(); e++) {
            String estilo = estilosSeleccionados.get(e);
            // Puntaje base decrece un poco por cada estilo adicional para no saturar
            double base = (e == 0) ? 90.0 : 75.0;

            if (estilo.equals("Aventura")) {
                agregarSiNoExiste(categorias, puntajes, "aventura",    base);
                agregarSiNoExiste(categorias, puntajes, "relajacion",  60.0);
            } else if (estilo.equals("Cultural")) {
                agregarSiNoExiste(categorias, puntajes, "cultural",    base);
                agregarSiNoExiste(categorias, puntajes, "gastronomia", 70.0);
            } else if (estilo.equals("Gastronomico")) {
                agregarSiNoExiste(categorias, puntajes, "gastronomia", base);
                agregarSiNoExiste(categorias, puntajes, "cultural",    65.0);
            } else if (estilo.equals("Relajacion")) {
                agregarSiNoExiste(categorias, puntajes, "relajacion",  base);
                agregarSiNoExiste(categorias, puntajes, "aventura",    55.0);
            }
        }

        gestorPerfil.configurarPerfil(estilosSeleccionados, gasto, categorias, puntajes);
        labelMensaje.setText(" ");
        JOptionPane.showMessageDialog(ventana,
                "Perfil guardado con estilos: " + estilosSeleccionados.toString(),
                "Exito", JOptionPane.INFORMATION_MESSAGE);
        ventana.mostrarPanel("planificador");
    }

    // Evita duplicados al combinar estilos
    private void agregarSiNoExiste(ArrayList<String> cats, ArrayList<Double> punts,
                                   String categoria, double puntaje) {
        for (int i = 0; i < cats.size(); i++) {
            if (cats.get(i).equals(categoria)) {
                // Si ya existe, queda el puntaje más alto
                if (puntaje > punts.get(i)) {
                    punts.set(i, puntaje);
                }
                return;
            }
        }
        cats.add(categoria);
        punts.add(puntaje);
    }

    private void mostrarHistorial() {
        String historial = ventana.getGestorUsuario().obtenerHistorial();
        JTextArea area = new JTextArea(historial);
        area.setEditable(false);
        area.setFont(new Font("Courier New", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new java.awt.Dimension(400, 250));
        JOptionPane.showMessageDialog(ventana, scroll, "Historial de viajes", JOptionPane.INFORMATION_MESSAGE);
    }
}
