package votacion;

import votacion_vista.VentanaPrincipal;

import javax.swing.*;


public class Main {
    
    /**
     * Método principal de la aplicación
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Configurar el look and feel del sistema operativo
        configurarLookAndFeel();
        
        // Ejecutar la aplicación en el Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Crear y mostrar la ventana principal
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
                
                // Mostrar mensaje de bienvenida
                mostrarMensajeBienvenida();
                
            } catch (Exception e) {
                // Manejar errores de inicialización
                JOptionPane.showMessageDialog(null,
                    "Error al iniciar la aplicación: " + e.getMessage(),
                    "Error de Inicialización",
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
    
    /**
     * Configura el look and feel del sistema
     */
    private static void configurarLookAndFeel() {
        try {
            // Intentar usar el look and feel del sistema operativo
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            
            // Configurar colores y fuentes personalizados
            configurarEstilosPersonalizados();
            
        } catch (Exception e) {
            // Si falla, usar el look and feel por defecto
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // Si todo falla, continuar con el look and feel por defecto
                System.err.println("No se pudo configurar el look and feel: " + ex.getMessage());
            }
        }
    }
    
    /**
     * Configura estilos personalizados para la aplicación
     */
    private static void configurarEstilosPersonalizados() {
        // Configurar colores de la interfaz
        UIManager.put("Button.background", new java.awt.Color(52, 152, 219));
        UIManager.put("Button.foreground", java.awt.Color.WHITE);
        UIManager.put("Button.focus", new java.awt.Color(41, 128, 185));
        
        // Configurar colores de los paneles
        UIManager.put("Panel.background", new java.awt.Color(236, 240, 241));
        
        // Configurar colores de los campos de texto
        UIManager.put("TextField.background", java.awt.Color.WHITE);
        UIManager.put("TextField.foreground", new java.awt.Color(52, 73, 94));
        UIManager.put("TextField.caretForeground", new java.awt.Color(52, 73, 94));
        
        // Configurar colores de las áreas de texto
        UIManager.put("TextArea.background", java.awt.Color.WHITE);
        UIManager.put("TextArea.foreground", new java.awt.Color(52, 73, 94));
        
        // Configurar colores de las etiquetas
        UIManager.put("Label.foreground", new java.awt.Color(52, 73, 94));
        
        // Configurar colores de los menús
        UIManager.put("MenuBar.background", new java.awt.Color(52, 73, 94));
        UIManager.put("MenuBar.foreground", java.awt.Color.WHITE);
        UIManager.put("Menu.background", new java.awt.Color(52, 73, 94));
        UIManager.put("Menu.foreground", java.awt.Color.WHITE);
        UIManager.put("MenuItem.background", new java.awt.Color(52, 73, 94));
        UIManager.put("MenuItem.foreground", java.awt.Color.WHITE);
    }
    
    /**
     * Muestra el mensaje de bienvenida
     */
    private static void mostrarMensajeBienvenida() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null,
                "¡Bienvenido al Sistema de Votación Electrónica!\n\n" +
                "Este sistema le permitirá:\n" +
                "• Registrar candidatos para la elección\n" +
                "• Realizar votaciones en tiempo real\n" +
                "• Ver resultados y estadísticas\n" +
                "• Detectar empates automáticamente\n\n" +
                "Para comenzar, vaya a la pestaña 'Registro de Candidatos'\n" +
                "y registre al menos un candidato.",
                "Bienvenido",
                JOptionPane.INFORMATION_MESSAGE);
        });
    }
    
    /**
     * Método para verificar la versión de Java
     */
    private static void verificarVersionJava() {
        String version = System.getProperty("java.version");
        String vendor = System.getProperty("java.vendor");
        
        System.out.println("Información del Sistema:");
        System.out.println("Java Version: " + version);
        System.out.println("Java Vendor: " + vendor);
        System.out.println("Sistema Operativo: " + System.getProperty("os.name"));
        System.out.println("Arquitectura: " + System.getProperty("os.arch"));
        System.out.println("================================");
    }
    
    /**
     * Método para mostrar información del sistema
     */
    public static void mostrarInformacionSistema() {
        StringBuilder info = new StringBuilder();
        info.append("INFORMACIÓN DEL SISTEMA\n");
        info.append("=======================\n\n");
        info.append("Java Version: ").append(System.getProperty("java.version")).append("\n");
        info.append("Java Vendor: ").append(System.getProperty("java.vendor")).append("\n");
        info.append("Sistema Operativo: ").append(System.getProperty("os.name")).append("\n");
        info.append("Versión del SO: ").append(System.getProperty("os.version")).append("\n");
        info.append("Arquitectura: ").append(System.getProperty("os.arch")).append("\n");
        info.append("Usuario: ").append(System.getProperty("user.name")).append("\n");
        info.append("Directorio de trabajo: ").append(System.getProperty("user.dir")).append("\n");
        info.append("Memoria total: ").append(Runtime.getRuntime().totalMemory() / 1024 / 1024).append(" MB\n");
        info.append("Memoria libre: ").append(Runtime.getRuntime().freeMemory() / 1024 / 1024).append(" MB\n");
        
        JTextArea textArea = new JTextArea(info.toString());
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
        
        JOptionPane.showMessageDialog(null,
            scrollPane,
            "Información del Sistema",
            JOptionPane.INFORMATION_MESSAGE);
    }
} 