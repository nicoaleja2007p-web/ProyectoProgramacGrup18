package interfaz;

import negocio.GestorPerfil;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
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
    private JComboBox<String> comboEstilo;
    private JTextField campoGasto;
    private JButton botonGuardar;
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

        labelEstilo = new JLabel("Estilo de viaje:");
        labelEstilo.setFont(new Font("Arial", Font.PLAIN, 14));

        labelGasto = new JLabel("Gasto diario maximo ($):");
        labelGasto.setFont(new Font("Arial", Font.PLAIN, 14));

        String[] estilos = {"Aventura", "Cultural", "Gastronomico", "Relajacion"};
        comboEstilo = new JComboBox<String>(estilos);
        comboEstilo.setFont(new Font("Arial", Font.PLAIN, 13));
        campoGasto = new JTextField(15);

        botonGuardar = new JButton("Guardar y Continuar");
        botonGuardar.setFont(new Font("Arial", Font.BOLD, 13));
        botonGuardar.setBackground(new Color(41, 98, 160));
        botonGuardar.setForeground(Color.WHITE);
        botonGuardar.setFocusPainted(false);

        labelMensaje = new JLabel(" ");
        labelMensaje.setFont(new Font("Arial", Font.ITALIC, 12));
        labelMensaje.setForeground(Color.RED);
    }

    private void configurarLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelTitulo, gbc);

        gbc.gridwidth = 1; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(labelEstilo, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(comboEstilo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        add(labelGasto, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(campoGasto, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelMensaje, gbc);

        gbc.gridy = 4;
        add(botonGuardar, gbc);
    }

    private void configurarEventos() {
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarPerfil();
            }
        });
    }

    private void guardarPerfil() {
        String estilo = (String) comboEstilo.getSelectedItem();
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

        ArrayList<String> categorias = new ArrayList<String>();
        ArrayList<Double> puntajes = new ArrayList<Double>();

        if (estilo.equals("Aventura")) {
            categorias.add("aventura");
            puntajes.add(90.0);
            categorias.add("relajacion");
            puntajes.add(60.0);
        } else if (estilo.equals("Cultural")) {
            categorias.add("cultural");
            puntajes.add(90.0);
            categorias.add("gastronomia");
            puntajes.add(70.0);
        } else if (estilo.equals("Gastronomico")) {
            categorias.add("gastronomia");
            puntajes.add(90.0);
            categorias.add("cultural");
            puntajes.add(65.0);
        } else if (estilo.equals("Relajacion")) {
            categorias.add("relajacion");
            puntajes.add(90.0);
            categorias.add("aventura");
            puntajes.add(55.0);
        }

        gestorPerfil.configurarPerfil(estilo, gasto, categorias, puntajes);
        labelMensaje.setText(" ");
        JOptionPane.showMessageDialog(ventana, "Perfil guardado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        ventana.mostrarPanel("planificador");
    }
}
