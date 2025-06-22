package votacion_vista;

import votacion_modelo.Candidato;
import votacion_modelo.SistemaVotacion;



import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Panel para registrar candidatos en el sistema de votación
 * @author Sistema de Votación
 * @version 1.0
 */
public class PanelRegistroCandidatos extends JPanel {
    private SistemaVotacion sistemaVotacion;
    
    // Componentes de la interfaz
    private JTextField txtNumero;
    private JTextField txtNombre;
    private JTextField txtPartido;
    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private JTextArea txtAreaCandidatos;
    private JScrollPane scrollPane;
    
    /**
     * Constructor del panel
     * @param sistemaVotacion Sistema de votación principal
     */
    public PanelRegistroCandidatos(SistemaVotacion sistemaVotacion) {
        this.sistemaVotacion = sistemaVotacion;
        inicializarComponentes();
        configurarLayout();
        agregarEventos();
        actualizarListaCandidatos();
    }
    
    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void inicializarComponentes() {
        // Campos de entrada
        txtNumero = new JTextField(10);
        txtNombre = new JTextField(20);
        txtPartido = new JTextField(15);
        
        // Botones
        btnRegistrar = new JButton("Registrar Candidato");
        btnRegistrar.setBackground(new Color(46, 204, 113));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        
        btnLimpiar = new JButton("Limpiar Campos");
        btnLimpiar.setBackground(new Color(231, 76, 60));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        
        // Área de texto para mostrar candidatos
        txtAreaCandidatos = new JTextArea(15, 40);
        txtAreaCandidatos.setEditable(false);
        txtAreaCandidatos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPane = new JScrollPane(txtAreaCandidatos);
    }
    
    /**
     * Configura el layout del panel
     */
    private void configurarLayout() {
        setLayout(new GridBagLayout()); // Usar GridBagLayout para centrar contenido
        
        	// Panel contenedor para centrar los componentes
        	JPanel panelContenido = new JPanel(new BorderLayout(10, 10));
        	panelContenido.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Registro de Candidatos",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            new Color(52, 73, 94)
        ));
        
        // Panel para formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Etiquetas y campos
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Número:"), gbc);
        
        gbc.gridx = 1;
        panelFormulario.add(txtNumero, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        
        gbc.gridx = 1;
        panelFormulario.add(txtNombre, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Partido:"), gbc);
        
        gbc.gridx = 1;
        panelFormulario.add(txtPartido, gbc);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelFormulario.add(panelBotones, gbc);
        
        // Panel para la lista de candidatos
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Candidatos Registrados"));
        panelLista.add(scrollPane, BorderLayout.CENTER);
        
        // Agregar componentes al panel de contenido
        panelContenido.add(panelFormulario, BorderLayout.NORTH);
        panelContenido.add(panelLista, BorderLayout.CENTER);
        
        // Agregar panel de contenido al layout principal para centrarlo
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.weightx = 1.0;
        gbcMain.weighty = 1.0;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.insets = new Insets(20, 100, 20, 100);
        
        add(panelContenido, gbcMain);
    }
    
    /**
     * Agrega los eventos a los componentes
     */
    private void agregarEventos() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCandidato();
            }
        });
        
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
        // Evento para permitir solo números en el campo número
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                }
            }
        });
    }
    
    /**
     * Registra un nuevo candidato
     */
    private void registrarCandidato() {
        try {
            // Validar campos
            if (txtNumero.getText().trim().isEmpty() || 
                txtNombre.getText().trim().isEmpty() || 
                txtPartido.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int numero = Integer.parseInt(txtNumero.getText().trim());
            String nombre = txtNombre.getText().trim();
            String partido = txtPartido.getText().trim();
            
            // Validar número positivo
            if (numero <= 0) {
                JOptionPane.showMessageDialog(this,
                    "El número del candidato debe ser positivo",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Registrar candidato
            boolean registrado = sistemaVotacion.registrarCandidato(numero, nombre, partido);
            
            if (registrado) {
                JOptionPane.showMessageDialog(this,
                    "Candidato registrado exitosamente",
                    "Registro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                actualizarListaCandidatos();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Ya existe un candidato con ese número",
                    "Error de Registro",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "El número debe ser un valor numérico válido",
                "Error de Formato",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Limpia todos los campos del formulario
     */
    private void limpiarCampos() {
        txtNumero.setText("");
        txtNombre.setText("");
        txtPartido.setText("");
        txtNumero.requestFocus();
    }
    
    /**
     * Actualiza la lista de candidatos en el área de texto
     */
    public void actualizarListaCandidatos() {
        List<Candidato> candidatos = sistemaVotacion.getCandidatos();
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== CANDIDATOS REGISTRADOS ===\n\n");
        
        if (candidatos.isEmpty()) {
            sb.append("No hay candidatos registrados.\n");
        } else {
            for (Candidato candidato : candidatos) {
                sb.append(String.format("Candidato #%d\n", candidato.getNumero()));
                sb.append(String.format("  Nombre: %s\n", candidato.getNombre()));
                sb.append(String.format("  Partido: %s\n", candidato.getPartido()));
                sb.append(String.format("  Votos: %d\n", candidato.getVotos()));
                sb.append("  -------------------------\n");
            }
        }
        
        txtAreaCandidatos.setText(sb.toString());
    }
} 