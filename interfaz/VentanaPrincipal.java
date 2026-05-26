package interfaz;

import negocio.GestorUsuario;
import negocio.GestorActividades;
import negocio.GestorPerfil;
import negocio.GestorReporte;
import modelo.PerfilPreferencias;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class VentanaPrincipal extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelPrincipal;

    private GestorUsuario gestorUsuario;
    private GestorActividades gestorActividades;
    private GestorPerfil gestorPerfil;

    private FormularioLogin formularioLogin;
    private FormularioPerfil formularioPerfil;
    private FormularioPlanificador formularioPlanificador;
    private VistaItinerario vistaItinerario;
    private VistaReporte vistaReporte;

    public VentanaPrincipal() {
        gestorUsuario = new GestorUsuario();
        gestorActividades = new GestorActividades();
        PerfilPreferencias perfil = new PerfilPreferencias("cultural", 100.0);
        gestorPerfil = new GestorPerfil(perfil);

        cargarActividades();
        inicializarVentana();
        inicializarPaneles();
        mostrarPanel("login");
    }

    private void cargarActividades() {
        // Cultural
        gestorActividades.registrarActividad("Tour ciudad historica", 30.0, 3, "cultural");
        gestorActividades.registrarActividad("Visita a museo de arte", 15.0, 2, "cultural");
        gestorActividades.registrarActividad("Visita a mercado artesanal", 5.0, 1, "cultural");
        gestorActividades.registrarActividad("Recorrido arquitectonico", 20.0, 2, "cultural");
        gestorActividades.registrarActividad("Visita a sitio arqueologico", 25.0, 3, "cultural");
        gestorActividades.registrarActividad("Tour de galeria de arte", 10.0, 2, "cultural");
        gestorActividades.registrarActividad("Visita a iglesia historica", 8.0, 1, "cultural");
        gestorActividades.registrarActividad("Espectaculo de danza folklorica", 35.0, 2, "cultural");
        // Aventura
        gestorActividades.registrarActividad("Senderismo en montaña", 20.0, 5, "aventura");
        gestorActividades.registrarActividad("Rafting en rio", 45.0, 4, "aventura");
        gestorActividades.registrarActividad("Paseo en bicicleta", 10.0, 2, "aventura");
        gestorActividades.registrarActividad("Escalada en roca", 55.0, 4, "aventura");
        gestorActividades.registrarActividad("Zipline en bosque", 40.0, 2, "aventura");
        gestorActividades.registrarActividad("Kayak en laguna", 30.0, 3, "aventura");
        gestorActividades.registrarActividad("Cabalgata en campo", 35.0, 3, "aventura");
        gestorActividades.registrarActividad("Caminata nocturna", 15.0, 2, "aventura");
        // Gastronomia
        gestorActividades.registrarActividad("Degustacion gastronomica", 50.0, 2, "gastronomia");
        gestorActividades.registrarActividad("Clase de cocina local", 60.0, 3, "gastronomia");
        gestorActividades.registrarActividad("Tour de mercado y cocina", 40.0, 3, "gastronomia");
        gestorActividades.registrarActividad("Cena en restaurante tipico", 35.0, 2, "gastronomia");
        gestorActividades.registrarActividad("Cata de vinos locales", 45.0, 2, "gastronomia");
        gestorActividades.registrarActividad("Desayuno tradicional guiado", 20.0, 1, "gastronomia");
        gestorActividades.registrarActividad("Visita a finca caficultora", 30.0, 3, "gastronomia");
        gestorActividades.registrarActividad("Taller de reposteria local", 25.0, 2, "gastronomia");
        // Relajacion
        gestorActividades.registrarActividad("Sesion de yoga al amanecer", 15.0, 1, "relajacion");
        gestorActividades.registrarActividad("Visita a aguas termales", 25.0, 3, "relajacion");
        gestorActividades.registrarActividad("Masaje tradicional", 50.0, 2, "relajacion");
        gestorActividades.registrarActividad("Paseo en bote por lago", 20.0, 2, "relajacion");
        gestorActividades.registrarActividad("Picnic en parque natural", 10.0, 2, "relajacion");
        gestorActividades.registrarActividad("Meditacion guiada", 12.0, 1, "relajacion");
        gestorActividades.registrarActividad("Tarde libre en playa", 5.0, 4, "relajacion");
        gestorActividades.registrarActividad("Observacion de estrellas", 18.0, 2, "relajacion");
    }

    private void inicializarVentana() {
        setTitle("Planificador Inteligente de Viajes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarPaneles() {
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        formularioLogin = new FormularioLogin(this, gestorUsuario);
        formularioPerfil = new FormularioPerfil(this, gestorPerfil);
        formularioPlanificador = new FormularioPlanificador(this, gestorActividades, gestorPerfil);
        vistaItinerario = new VistaItinerario(this);
        vistaReporte = new VistaReporte(this);

        panelPrincipal.add(formularioLogin, "login");
        panelPrincipal.add(formularioPerfil, "perfil");
        panelPrincipal.add(formularioPlanificador, "planificador");
        panelPrincipal.add(vistaItinerario, "itinerario");
        panelPrincipal.add(vistaReporte, "reporte");

        add(panelPrincipal);
    }

    public void mostrarPanel(String nombre) {
        cardLayout.show(panelPrincipal, nombre);
    }

    public GestorUsuario getGestorUsuario() {
        return gestorUsuario;
    }

    public GestorActividades getGestorActividades() {
        return gestorActividades;
    }

    public GestorPerfil getGestorPerfil() {
        return gestorPerfil;
    }

    public VistaItinerario getVistaItinerario() {
        return vistaItinerario;
    }

    public VistaReporte getVistaReporte() {
        return vistaReporte;
    }

    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}