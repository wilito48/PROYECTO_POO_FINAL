package com.simulador.votacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {

	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=VotacionDB;encrypt=true;trustServerCertificate=true;";
    private static final String USUARIO = "sa"; // ← reemplaza con tu usuario real
    private static final String CONTRASENA = "Admin123"; // ← reemplaza con tu contraseña real

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
