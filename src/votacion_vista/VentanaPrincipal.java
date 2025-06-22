package votacion_vista;

import votacion_modelo.SistemaVotacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class VentanaPrincipal extends JFrame {
    private SistemaVotacion sistemaVotacion;
    private JTabbedPane tabbedPane;
    private PanelRegistroCandidatos panelRegistro;
    private PanelVotacion panelVotacion;
    
    /**
     * Constructor de la ventana principal
     */
    public VentanaPrincipal() {
        this.sistemaVotacion = new SistemaVotacion();
        inicializarVentana();
        inicializarComponentes();
        configurarLayout();
        agregarEventos();
    }
    
    /**
     * Inicializa la configuración básica de la ventana
     */
    private void inicializarVentana() {
        setTitle("Sistema de Votación Electrónica v1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Forzar pantalla completa de forma manual y estable
        setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        
        // Configurar icono de la aplicación
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono.png")));
            
        } catch (Exception e) {
            // Si no se encuentra el icono, continuar sin él
        }
    }
    
    /**
     * Inicializa los componentes principales
     */
    private void inicializarComponentes() {
        // Crear el panel de pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Crear los paneles
        panelRegistro = new PanelRegistroCandidatos(sistemaVotacion);
        panelVotacion = new PanelVotacion(sistemaVotacion);
        
        // Agregar paneles al tabbedPane con iconos redimensionados
        tabbedPane.addTab("Registro de Candidatos", 
            crearIconoRedimensionado("/icono_registro.png", 32, 32), 
            panelRegistro, 
            "Registrar candidatos para la votación");
        
        tabbedPane.addTab("Sistema de Votación", 
            crearIconoRedimensionado("/icono_votacion.png", 32, 32), 
            panelVotacion, 
            "Realizar votaciones y ver resultados");
    }
    
    /**
     * Crea un ImageIcon redimensionado desde una ruta de recurso.
     * @param path Ruta del recurso de la imagen.
     * @param width Ancho deseado.
     * @param height Alto deseado.
     * @return ImageIcon redimensionado, o null si la imagen no se encuentra.
     */
    private ImageIcon crearIconoRedimensionado(String path, int width, int height) {
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(path));
            if (originalIcon.getImage() == null) {
                return null;
            }
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            // En caso de error (p. ej. imagen no encontrada), no devuelve icono.
            return null;
        }
    }
    
    /**
     * Configura el layout de la ventana
     */
    private void configurarLayout() {
        setLayout(new BorderLayout());
        
        // Panel superior con título
        JPanel panelTitulo = crearPanelTitulo();
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel central con pestañas
        add(tabbedPane, BorderLayout.CENTER);
        
        // Panel inferior con información
        JPanel panelInferior = crearPanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    /**
     * Crea el panel del título
     */
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(52, 73, 94));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel lblTitulo = new JLabel("SISTEMA DE VOTACIÓN ELECTRÓNICA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        
        JLabel lblSubtitulo = new JLabel("Simulador para Elecciones Internas");
        lblSubtitulo.setFont(new Font("Arial", Font.ITALIC, 14));
        lblSubtitulo.setForeground(new Color(189, 195, 199));
        
        panel.setLayout(new BorderLayout());
        panel.add(lblTitulo, BorderLayout.CENTER);
        panel.add(lblSubtitulo, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Crea el panel inferior con información
     */
    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(236, 240, 241));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel lblInfo = new JLabel("© 2024 Sistema de Votación Electrónica - Versión 1.0");
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 10));
        lblInfo.setForeground(new Color(127, 140, 141));
        
        JLabel lblInstrucciones = new JLabel("Use las pestañas para navegar entre las diferentes funciones del sistema");
        lblInstrucciones.setFont(new Font("Arial", Font.PLAIN, 10));
        lblInstrucciones.setForeground(new Color(127, 140, 141));
        
        panel.add(lblInfo, BorderLayout.WEST);
        panel.add(lblInstrucciones, BorderLayout.EAST);
        
        return panel;
    }
    
    /**
     * Agrega los eventos a la ventana
     */
    private void agregarEventos() {
        // Evento para confirmar salida
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmarSalida();
            }
        });
        
        // Evento para cambio de pestaña
        tabbedPane.addChangeListener(e -> {
            // Actualizar información cuando se cambie de pestaña
            if (tabbedPane.getSelectedComponent() == panelVotacion) {
                panelVotacion.actualizarResultadosDesdeExterno();
            } else if (tabbedPane.getSelectedComponent() == panelRegistro) {
                panelRegistro.actualizarListaCandidatos();
            }
        });
        
        // Agregar menú
        crearMenu();
    }
    
    /**
     * Crea el menú de la aplicación
     */
    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        // Menú Archivo
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setMnemonic('A');
        
        JMenuItem menuItemSalir = new JMenuItem("Salir", 'S');
        menuItemSalir.addActionListener(e -> confirmarSalida());
        menuArchivo.add(menuItemSalir);
        
        // Menú Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        menuAyuda.setMnemonic('Y');
        
        JMenuItem menuItemAcercaDe = new JMenuItem("Acerca de", 'A');
        menuItemAcercaDe.addActionListener(e -> mostrarAcercaDe());
        menuAyuda.add(menuItemAcercaDe);
        
        JMenuItem menuItemManual = new JMenuItem("Manual de Usuario", 'M');
        menuItemManual.addActionListener(e -> mostrarManual());
        menuAyuda.add(menuItemManual);
        
        // Agregar menús a la barra
        menuBar.add(menuArchivo);
        menuBar.add(menuAyuda);
        
        setJMenuBar(menuBar);
    }
    
    /**
     * Confirma la salida de la aplicación
     */
    private void confirmarSalida() {
        int opcion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea salir del sistema de votación?",
            "Confirmar Salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    /**
     * Muestra la información "Acerca de"
     */
    private void mostrarAcercaDe() {
        JOptionPane.showMessageDialog(this,
            "Sistema de Votación Electrónica v1.0\n\n" +
            "Desarrollado para simular elecciones internas\n" +
            "con interfaz gráfica moderna y funcional.\n\n" +
            "Características:\n" +
            "• Registro de candidatos\n" +
            "• Sistema de votación en tiempo real\n" +
            "• Cálculo de porcentajes\n" +
            "• Detección de empates\n" +
            "• Interfaz intuitiva\n\n" +
            "© 2024 Todos los derechos reservados",
            "Acerca de",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra el manual de usuario
     */
    private void mostrarManual() {
        String manual = "MANUAL DE USUARIO - SISTEMA DE VOTACIÓN ELECTRÓNICA\n\n" +
            "1. REGISTRO DE CANDIDATOS:\n" +
            "   • Vaya a la pestaña 'Registro de Candidatos'\n" +
            "   • Complete los campos: Número, Nombre y Partido\n" +
            "   • Haga clic en 'Registrar Candidato'\n" +
            "   • Los candidatos aparecerán en la lista inferior\n\n" +
            "2. SISTEMA DE VOTACIÓN:\n" +
            "   • Vaya a la pestaña 'Sistema de Votación'\n" +
            "   • Haga clic en 'Iniciar Votación'\n" +
            "   • Los votantes ingresan el número del candidato\n" +
            "   • Haga clic en 'Votar' o presione Enter\n" +
            "   • Los resultados se actualizan en tiempo real\n" +
            "   • Haga clic en 'Finalizar Votación' para terminar\n\n" +
            "3. RESULTADOS:\n" +
            "   • Se muestran en tiempo real durante la votación\n" +
            "   • Al finalizar se determina el ganador o empate\n" +
            "   • Se calculan los porcentajes automáticamente\n\n" +
            "NOTAS:\n" +
            "• Debe registrar al menos un candidato antes de votar\n" +
            "• No se pueden registrar candidatos duplicados\n" +
            "• La votación debe estar activa para registrar votos\n" +
            "• El sistema detecta automáticamente empates";
        
        JTextArea textArea = new JTextArea(manual);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        
        JOptionPane.showMessageDialog(this,
            scrollPane,
            "Manual de Usuario",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Método principal para ejecutar la aplicación
     */
    public static void main(String[] args) {
        // Configurar look and feel del sistema
        try {
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        	

        } catch (Exception e) {
            // Si falla, usar el look and feel por defecto
        }
        
        // Ejecutar la aplicación en el EDT
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
} 