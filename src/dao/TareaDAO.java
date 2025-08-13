/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dominio.Tarea;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {

    public void insertarTarea(Tarea tarea) {
        String sql = "INSERT INTO Tareas (titulo, prioridad, fecha, especial, hecha, eliminada) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionSQLServer.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarea.getTitulo());
            stmt.setInt(2, tarea.getPrioridad());
            stmt.setDate(3, tarea.getFecha() != null ? Date.valueOf(tarea.getFecha()) : null);
            stmt.setBoolean(4, tarea.isEspecial());
            stmt.setBoolean(5, tarea.isHecha());
            stmt.setBoolean(6, tarea.isEliminada());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar tarea: " + e.getMessage());
        }
    }

    public List<Tarea> listarTareas() {
        List<Tarea> tareas = new ArrayList<>();
        String sql = "SELECT * FROM Tareas WHERE eliminada = 0 ORDER BY id";

        try (Connection conn = ConexionSQLServer.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tarea tarea = new Tarea(
                    rs.getString("titulo"),
                    rs.getInt("prioridad"),
                    rs.getDate("fecha") != null ? rs.getDate("fecha").toLocalDate() : null,
                    rs.getBoolean("especial")
                );
                tarea.setId(rs.getInt("id"));
                tarea.setHecha(rs.getBoolean("hecha"));
                tareas.add(tarea);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar tareas: " + e.getMessage());
        }

        return tareas;
    }

    public void marcarEliminada(int id) {
        String sql = "UPDATE Tareas SET eliminada = 1 WHERE id = ?";
        try (Connection conn = ConexionSQLServer.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar tarea: " + e.getMessage());
        }
    }

    public void alternarEstado(int id, boolean nuevaEstado) {
        String sql = "UPDATE Tareas SET hecha = ? WHERE id = ?";
        try (Connection conn = ConexionSQLServer.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, nuevaEstado);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado: " + e.getMessage());
        }
    }
}
