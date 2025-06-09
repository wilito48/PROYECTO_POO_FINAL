package com.simulador.votacion;

import javax.swing.*;
import java.awt.*;

public class RegistroUsuarioGUI extends JFrame {

    private JTextField usuarioField = new JTextField(20);
    private JPasswordField contrasenaField = new JPasswordField(20);
    private JTextField nombreField = new JTextField(20);

    public RegistroUsuarioGUI() {
        super("Registro de Usuario");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel background = new JLabel(new ImageIcon("/img/login.png")); // Asegúrate de tener esta imagen
        background.setLayout(new GridBagLayout());

        JPanel registroPanel = new JPanel(new GridBagLayout());
        registroPanel.setPreferredSize(new Dimension(320, 250));
        registroPanel.setBackground(new Color(255, 255, 255, 210));
        registroPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        registroPanel.add(new JLabel("Nombre completo:"), gbc);
        gbc.gridy++;
        registroPanel.add(nombreField, gbc);

        gbc.gridy++;
        registroPanel.add(new JLabel("Usuario:"), gbc);
        gbc.gridy++;
        registroPanel.add(usuarioField, gbc);

        gbc.gridy++;
        registroPanel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridy++;
        registroPanel.add(contrasenaField, gbc);

        JButton registrarBtn = new JButton("Registrarse");
        registrarBtn.setFocusPainted(false);
        registrarBtn.setBackground(new Color(34, 139, 34));
        registrarBtn.setForeground(Color.WHITE);
        gbc.gridy++;
        registroPanel.add(registrarBtn, gbc);

        registrarBtn.addActionListener(e -> {
            String usuario = usuarioField.getText().trim();
            String contrasena = new String(contrasenaField.getPassword());
            String nombre = nombreField.getText().trim();

            if (usuario.isEmpty() || contrasena.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean registrado = UsuarioDAO.registrarUsuario(usuario, contrasena, nombre);
            if (registrado) {
                JOptionPane.showMessageDialog(this, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new LoginUsuarioGUI().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        background.add(registroPanel);
        setContentPane(background);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroUsuarioGUI().setVisible(true));
    }
}

