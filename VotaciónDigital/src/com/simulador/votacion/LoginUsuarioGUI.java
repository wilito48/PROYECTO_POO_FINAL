package com.simulador.votacion;

import javax.swing.*;
import java.awt.*;


public class LoginUsuarioGUI extends JFrame {

    private JTextField usuarioField = new JTextField(20);
    private JPasswordField contrasenaField = new JPasswordField(20);

    public LoginUsuarioGUI() {
        super("Login de Usuario");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Imagen de fondo
        JLabel background = new JLabel(new ImageIcon("/com.simulador.votacion/img/login2.png")); // Asegúrate de tener esta imagen
        background.setLayout(new GridBagLayout());

        // Panel de login con fondo blanco semitransparente
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setPreferredSize(new Dimension(300, 220));
        loginPanel.setBackground(new Color(255, 255, 255, 200));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(new JLabel("Usuario:"), gbc);
        gbc.gridy++;
        loginPanel.add(usuarioField, gbc);

        gbc.gridy++;
        loginPanel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridy++;
        loginPanel.add(contrasenaField, gbc);

        JButton loginBtn = new JButton("Iniciar sesión");
        loginBtn.setFocusPainted(false);
        loginBtn.setBackground(new Color(70, 130, 180));
        loginBtn.setForeground(Color.WHITE);
        gbc.gridy++;
        loginPanel.add(loginBtn, gbc);

        // Acción del botón
        loginBtn.addActionListener(e -> {
            String usuario = usuarioField.getText().trim();
            String contrasena = new String(contrasenaField.getPassword());

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nombreUsuario = UsuarioDAO.validarLogin(usuario, contrasena);  // Devuelve el nombre

            if (nombreUsuario != null) {
                JOptionPane.showMessageDialog(this, "Login exitoso. Bienvenido " + nombreUsuario + "!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();  // Cerramos la ventana de login

                // Abrimos el sistema con el nombre
                new SimuladorVotacionGUI(nombreUsuario).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });



        background.add(loginPanel);
        setContentPane(background);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginUsuarioGUI().setVisible(true));
    }
}

