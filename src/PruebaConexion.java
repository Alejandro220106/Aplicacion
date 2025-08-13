/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebaConexion {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=GestorTareas;encrypt=true;trustServerCertificate=true";        
        String usuario = "Ale";
        String contrasena = "1234";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println(" Conexión exitosa a SQL Server");
            conexion.close();
        } catch (ClassNotFoundException e) {
            System.err.println(" Driver JDBC no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println(" Error de conexión: " + e.getMessage());
        }
    }
}
