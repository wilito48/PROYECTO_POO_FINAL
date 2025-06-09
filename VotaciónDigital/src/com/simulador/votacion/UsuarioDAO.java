	package com.simulador.votacion;
	
	import java.sql.*;
	
	public class UsuarioDAO {
	
		public static boolean registrarUsuario(String usuario, String contrasena, String nombre) {
		    try {
		        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	
		        String url = "jdbc:sqlserver://localhost:1433;databaseName=VotacionDB;encrypt=true;trustServerCertificate=true;";
		        String user = "sa";
		        String password = "Admin123";
	
		        try (Connection con = DriverManager.getConnection(url, user, password)) {
		            String sql = "INSERT INTO Usuarios (usuario, contrasena, nombre) VALUES (?, ?, ?)";
		            try (PreparedStatement ps = con.prepareStatement(sql)) {
		                ps.setString(1, usuario);
		                ps.setString(2, contrasena);
		                ps.setString(3, nombre);
		                int filas = ps.executeUpdate();
		                return filas > 0;
		            }
		        }
		    } catch (ClassNotFoundException | SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	
	
	    public static boolean validarLogin(String usuario, String contrasena) {
	        String sql = "SELECT * FROM Usuarios WHERE usuario = ? AND contrasena = ?";
	        try (Connection con = ConexionDB.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, usuario);
	            ps.setString(2, contrasena);
	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next(); // Si hay fila, usuario y contraseña correctos
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	}

