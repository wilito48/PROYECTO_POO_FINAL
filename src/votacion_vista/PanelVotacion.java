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
 * Panel para realizar votaciones en el sistema
 * @author Sistema de Votación
 * @version 1.0
 */
public class PanelVotacion extends JPanel {
    private SistemaVotacion sistemaVotacion;
    
    // Componentes de la interfaz
    private JTextField txtNumeroVoto;
    private JButton btnVotar;
    private JButton btnIniciarVotacion;
    private JButton btnFinalizarVotacion;
    private JTextArea txtAreaResultados;
    private JScrollPane scrollPane;
    private JLabel lblEstadoVotacion;
    private JLabel lblTotalVotos;
    
    /**
     * Constructor del panel
     * @param sistemaVotacion Sistema de votación principal
     */
    public PanelVotacion(SistemaVotacion sistemaVotacion) {
        this.sistemaVotacion = sistemaVotacion;
        inicializarComponentes();
        configurarLayout();
        agregarEventos();
        actualizarEstado();
    }
    
    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void inicializarComponentes() {
        // Campo de entrada para voto
        txtNumeroVoto = new JTextField(10);
        
        // Botones
        btnVotar = new JButton("Votar");
        btnVotar.setBackground(new Color(52, 152, 219));
        btnVotar.setForeground(Color.WHITE);
        btnVotar.setFocusPainted(false);
        btnVotar.setEnabled(false);
        
        btnIniciarVotacion = new JButton("Iniciar Votación");
        btnIniciarVotacion.setBackground(new Color(46, 204, 113));
        btnIniciarVotacion.setForeground(Color.WHITE);
        btnIniciarVotacion.setFocusPainted(false);
        
        btnFinalizarVotacion = new JButton("Finalizar Votación");
        btnFinalizarVotacion.setBackground(new Color(231, 76, 60));
        btnFinalizarVotacion.setForeground(Color.WHITE);
        btnFinalizarVotacion.setFocusPainted(false);
        btnFinalizarVotacion.setEnabled(false);
        
        // Etiquetas de estado
        lblEstadoVotacion = new JLabel("Estado: Votación no iniciada");
        lblEstadoVotacion.setFont(new Font("Arial", Font.BOLD, 12));
        lblEstadoVotacion.setForeground(new Color(231, 76, 60));
        
        lblTotalVotos = new JLabel("Total de votos: 0");
        lblTotalVotos.setFont(new Font("Arial", Font.BOLD, 12));
        lblTotalVotos.setForeground(new Color(52, 73, 94));
        
        // Área de texto para resultados
        txtAreaResultados = new JTextArea(20, 50);
        txtAreaResultados.setEditable(false);
        txtAreaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPane = new JScrollPane(txtAreaResultados);
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
            "Sistema de Votación",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            new Color(52, 73, 94)
        ));
        
        // Panel superior para controles
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de estado
        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEstado.add(lblEstadoVotacion);
        panelEstado.add(Box.createHorizontalStrut(20));
        panelEstado.add(lblTotalVotos);
        
        // Panel de votación
        JPanel panelVotacion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelVotacion.add(new JLabel("Número del candidato:"));
        panelVotacion.add(txtNumeroVoto);
        panelVotacion.add(btnVotar);
        
        // Panel de botones de control
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnIniciarVotacion);
        panelBotones.add(btnFinalizarVotacion);
        
        // Agregar paneles al panel superior
        panelSuperior.add(panelEstado, BorderLayout.NORTH);
        panelSuperior.add(panelVotacion, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);
        
        // Panel para resultados
        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados en Vivo"));
        panelResultados.add(scrollPane, BorderLayout.CENTER);
        
        // Agregar componentes al panel de contenido
        panelContenido.add(panelSuperior, BorderLayout.NORTH);
        panelContenido.add(panelResultados, BorderLayout.CENTER);
        
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
        btnIniciarVotacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarVotacion();
            }
        });
        
        btnFinalizarVotacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarVotacion();
            }
        });
        
        btnVotar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVoto();
            }
        });
        
        // Evento para permitir solo números en el campo de voto
        txtNumeroVoto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                }
            }
        });
        
        // Evento para votar con Enter
        txtNumeroVoto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    registrarVoto();
                }
            }
        });
    }
    
    /**
     * Inicia el proceso de votación
     */
    private void iniciarVotacion() {
        if (sistemaVotacion.getCandidatos().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Debe registrar al menos un candidato antes de iniciar la votación",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        sistemaVotacion.iniciarVotacion();
        actualizarEstado();
        actualizarResultados();
        
        JOptionPane.showMessageDialog(this,
            "Votación iniciada. Los votantes pueden comenzar a votar.",
            "Votación Iniciada",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Finaliza el proceso de votación
     */
    private void finalizarVotacion() {
        int opcion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea finalizar la votación?",
            "Finalizar Votación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            sistemaVotacion.finalizarVotacion();
            actualizarEstado();
            mostrarResultadosFinales();
        }
    }
    
    /**
     * Registra un voto
     */
    private void registrarVoto() {
        try {
            if (!sistemaVotacion.isVotacionActiva()) {
                JOptionPane.showMessageDialog(this,
                    "La votación no está activa",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (txtNumeroVoto.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Ingrese el número del candidato",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int numeroCandidato = Integer.parseInt(txtNumeroVoto.getText().trim());
            
            boolean votoRegistrado = sistemaVotacion.registrarVoto(numeroCandidato);
            
            if (votoRegistrado) {
                Candidato candidato = sistemaVotacion.getCandidatoPorNumero(numeroCandidato);
                JOptionPane.showMessageDialog(this,
                    "Voto registrado exitosamente para " + candidato.getNombre(),
                    "Voto Registrado",
                    JOptionPane.INFORMATION_MESSAGE);
                
                txtNumeroVoto.setText("");
                actualizarEstado();
                actualizarResultados();
            } else {
                JOptionPane.showMessageDialog(this,
                    "No existe un candidato con ese número",
                    "Error",
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
     * Actualiza el estado de la interfaz
     */
    private void actualizarEstado() {
        if (sistemaVotacion.isVotacionActiva()) {
            lblEstadoVotacion.setText("Estado: Votación activa");
            lblEstadoVotacion.setForeground(new Color(46, 204, 113));
            btnVotar.setEnabled(true);
            btnIniciarVotacion.setEnabled(false);
            btnFinalizarVotacion.setEnabled(true);
            txtNumeroVoto.setEnabled(true);
        } else {
            lblEstadoVotacion.setText("Estado: Votación no iniciada");
            lblEstadoVotacion.setForeground(new Color(231, 76, 60));
            btnVotar.setEnabled(false);
            btnIniciarVotacion.setEnabled(true);
            btnFinalizarVotacion.setEnabled(false);
            txtNumeroVoto.setEnabled(false);
        }
        
        lblTotalVotos.setText("Total de votos: " + sistemaVotacion.getTotalVotos());
    }
    
    /**
     * Actualiza los resultados en tiempo real
     */
    private void actualizarResultados() {
        List<Candidato> candidatos = sistemaVotacion.getCandidatos();
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESULTADOS EN TIEMPO REAL ===\n\n");
        
        if (candidatos.isEmpty()) {
            sb.append("No hay candidatos registrados.\n");
        } else {
            for (Candidato candidato : candidatos) {
                double porcentaje = candidato.calcularPorcentaje(sistemaVotacion.getTotalVotos());
                sb.append(String.format("Candidato #%d: %s (%s)\n", 
                    candidato.getNumero(), candidato.getNombre(), candidato.getPartido()));
                sb.append(String.format("  Votos: %d (%.2f%%)\n", 
                    candidato.getVotos(), porcentaje));
                sb.append("  -------------------------\n");
            }
        }
        
        txtAreaResultados.setText(sb.toString());
    }
    
    /**
     * Muestra los resultados finales de la votación
     */
    private void mostrarResultadosFinales() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESULTADOS FINALES ===\n\n");
        
        // Mostrar estadísticas
        sb.append(sistemaVotacion.obtenerEstadisticas());
        sb.append("\n");
        
        // Determinar ganador o empate
        if (sistemaVotacion.hayEmpate()) {
            List<Candidato> empatados = sistemaVotacion.getCandidatosEmpatados();
            sb.append("¡EMPATE!\n");
            sb.append("Los siguientes candidatos están empatados:\n");
            for (Candidato candidato : empatados) {
                sb.append(String.format("- %s (%s) con %d votos\n", 
                    candidato.getNombre(), candidato.getPartido(), candidato.getVotos()));
            }
        } else {
            Candidato ganador = sistemaVotacion.determinarGanador();
            if (ganador != null) {
                sb.append("¡GANADOR!\n");
                sb.append(String.format("Candidato #%d: %s (%s)\n", 
                    ganador.getNumero(), ganador.getNombre(), ganador.getPartido()));
                sb.append(String.format("Con %d votos (%.2f%%)\n", 
                    ganador.getVotos(), ganador.calcularPorcentaje(sistemaVotacion.getTotalVotos())));
            } else {
                sb.append("No hay votos registrados.\n");
            }
        }
        
        txtAreaResultados.setText(sb.toString());
        
        // Mostrar diálogo con el resultado
        String mensaje = sistemaVotacion.hayEmpate() ? 
            "La votación ha terminado en empate." : 
            "La votación ha terminado. Hay un ganador.";
        
        JOptionPane.showMessageDialog(this,
            mensaje,
            "Votación Finalizada",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Método público para actualizar los resultados desde otros paneles
     */
    public void actualizarResultadosDesdeExterno() {
        actualizarEstado();
        actualizarResultados();
    }
} 