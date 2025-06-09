package com.simulador.votacion;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SimuladorVotacionGUI {

    private static List<Candidato> candidatos = new ArrayList<>();
    private static JTextArea resultadosTextArea = new JTextArea();
    private static int totalVotos = 0;

    public static void main(String[] args) {
        // Candidatos de ejemplo
        candidatos.add(new CandidatoPartido1("Ana"));
        candidatos.add(new CandidatoPartido2("Luis"));
        candidatos.add(new CandidatoPartido1("Pedro"));

        JFrame frame = new JFrame("🗳️ Simulador de Votación Electrónica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setLocationRelativeTo(null);

        // Panel principal con degradado de fondo
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Degradado vertical azul claro a blanco
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(63, 81, 181), 0, getHeight(), Color.WHITE);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(30, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Título
        JLabel titulo = new JLabel("Simulador de Votación Electrónica", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 48));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(new EmptyBorder(0, 0, 20, 0));
        mainPanel.add(titulo, BorderLayout.NORTH);

        // Panel candidatos con fondo blanco y sombra (borde)
        JPanel candidatosPanel = new JPanel();
        candidatosPanel.setLayout(new BoxLayout(candidatosPanel, BoxLayout.Y_AXIS));
        candidatosPanel.setBackground(Color.WHITE);
        candidatosPanel.setBorder(BorderFactory.createCompoundBorder(
                new DropShadowBorder(), 
                BorderFactory.createEmptyBorder(30, 30, 30, 30)));

        JLabel candidatosTitulo = new JLabel("Seleccione un candidato");
        candidatosTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        candidatosTitulo.setForeground(new Color(33, 33, 33));
        candidatosTitulo.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 0)); // Empuja 30px a la derecha
        candidatosPanel.add(candidatosTitulo);
        candidatosPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        ButtonGroup group = new ButtonGroup();
        ArrayList<JRadioButton> botones = new ArrayList<>();

        for (Candidato c : candidatos) {
            JRadioButton button = new JRadioButton(c.nombre);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            group.add(button);
            botones.add(button);
            candidatosPanel.add(button);
            candidatosPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        JScrollPane candidatosScroll = new JScrollPane(candidatosPanel);
        candidatosScroll.setPreferredSize(new Dimension(450, 0));
        candidatosScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        candidatosScroll.setBorder(null);
        candidatosScroll.getViewport().setBackground(Color.WHITE);

        // Resultados con fondo blanco, fuente grande y borde sombra
        resultadosTextArea.setEditable(false);
        resultadosTextArea.setFont(new Font("Consolas", Font.PLAIN, 26));
        resultadosTextArea.setBackground(Color.WHITE);
        resultadosTextArea.setBorder(BorderFactory.createCompoundBorder(
                new DropShadowBorder(),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)));

        JScrollPane resultadosScroll = new JScrollPane(resultadosTextArea);
        resultadosScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        resultadosScroll.setBorder(null);
        resultadosScroll.getViewport().setBackground(Color.WHITE);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, candidatosScroll, resultadosScroll);
        splitPane.setResizeWeight(0.35);
        splitPane.setDividerSize(6);
        splitPane.setContinuousLayout(true);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        // Panel botones con diseño moderno
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        botonesPanel.setBackground(new Color(63, 81, 181));

        JButton votarButton = crearBoton("Votar", new Color(76, 175, 80));
        JButton finalizarButton = crearBoton("Finalizar votación", new Color(244, 67, 54));

        botonesPanel.add(votarButton);
        botonesPanel.add(finalizarButton);

        mainPanel.add(botonesPanel, BorderLayout.SOUTH);

        votarButton.addActionListener(e -> {
            boolean votado = false;
            for (int i = 0; i < botones.size(); i++) {
                if (botones.get(i).isSelected()) {
                    candidatos.get(i).registrarVoto();
                    totalVotos++;
                    votado = true;
                    JOptionPane.showMessageDialog(frame, "✅ Voto registrado para: " + candidatos.get(i).nombre, "Votación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            if (!votado) {
                JOptionPane.showMessageDialog(frame, "⚠️ Por favor, seleccione un candidato antes de votar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            mostrarResultados();
        });

        finalizarButton.addActionListener(e -> {
            if (totalVotos == 0) {
                JOptionPane.showMessageDialog(frame, "No se han registrado votos aún.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }
            mostrarResultadosFinales();

            votarButton.setEnabled(false);
            finalizarButton.setEnabled(false);
            for (JRadioButton rb : botones) {
                rb.setEnabled(false);
            }
            JOptionPane.showMessageDialog(frame, "La votación ha finalizado. ¡Gracias por participar!", "Votación Finalizada", JOptionPane.INFORMATION_MESSAGE);
        });

        frame.add(mainPanel);
        frame.setVisible(true);

        mostrarResultados();
    }

    // Método para crear botones bonitos con hover
    private static JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 28));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(new RoundedBorder(20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorFondo.darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorFondo);
            }
        });
        return boton;
    }

    public static void mostrarResultados() {
        StringBuilder resultados = new StringBuilder();
        for (Candidato c : candidatos) {
            resultados.append(c.mostrarResultado()).append("\n");
        }
        resultadosTextArea.setText(resultados.toString());
    }

    public static void mostrarResultadosFinales() {
        StringBuilder resultados = new StringBuilder();
        resultados.append(String.format("Total de votos: %d\n\n", totalVotos));

        for (Candidato c : candidatos) {
            double porcentaje = (double) c.votos / totalVotos * 100;
            resultados.append(String.format("%s: %d votos (%.2f%%)\n", c.nombre, c.votos, porcentaje));
        }

        int maxVotos = candidatos.stream().mapToInt(c -> c.votos).max().orElse(0);

        List<Candidato> ganadores = new ArrayList<>();
        for (Candidato c : candidatos) {
            if (c.votos == maxVotos && maxVotos > 0) {
                ganadores.add(c);
            }
        }

        resultados.append("\n");
        if (ganadores.size() > 1) {
            resultados.append("¡Hay un empate entre: ");
            for (int i = 0; i < ganadores.size(); i++) {
                resultados.append(ganadores.get(i).nombre);
                if (i < ganadores.size() - 1) resultados.append(", ");
            }
            resultados.append("!");
        } else if (ganadores.size() == 1) {
            resultados.append("Ganador: ").append(ganadores.get(0).nombre);
        } else {
            resultados.append("No hay votos registrados.");
        }

        resultadosTextArea.setText(resultados.toString());
    }

    // Clase para bordes redondeados en botones
    static class RoundedBorder extends AbstractBorder {
        private final int radius;
        public RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(x + 1, y + 1, width - 3, height - 3, radius, radius);
            g2.dispose();
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+1, this.radius+1);
        }
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = this.radius+1;
            return insets;
        }
    }

    // Sombra simple para paneles
    static class DropShadowBorder extends AbstractBorder {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static final int SHADOW_SIZE = 8;
        private static final Color SHADOW_COLOR = new Color(0, 0, 0, 50);

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setColor(SHADOW_COLOR);
            for (int i = 0; i < SHADOW_SIZE; i++) {
                float opacity = 0.15f - (float) i / (SHADOW_SIZE * 10);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                g2.drawRoundRect(x + i, y + i, width - i * 2 - 1, height - i * 2 - 1, 20, 20);
            }

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(SHADOW_SIZE, SHADOW_SIZE, SHADOW_SIZE, SHADOW_SIZE);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = SHADOW_SIZE;
            return insets;
        }
    }
}
