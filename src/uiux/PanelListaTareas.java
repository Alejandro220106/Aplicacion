/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiux;

import dominio.Tarea;
import servicio.TareaServicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelListaTareas extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;
    private TareaServicio servicio;

    public PanelListaTareas(TareaServicio servicio) {
        this.servicio = servicio;
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"ID", "Título", "Prioridad", "★", "Estado"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel botones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnAlternar = new JButton("Alternar Estado");
        JButton btnDeshacer = new JButton("Deshacer");

        botones.add(btnEliminar);
        botones.add(btnAlternar);
        botones.add(btnDeshacer);
        add(botones, BorderLayout.SOUTH);

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int id = (int) modelo.getValueAt(fila, 0);
                Tarea tarea = new Tarea((String) modelo.getValueAt(fila, 1), 1, null, false);
                tarea.setId(id);
                servicio.eliminarTarea(tarea);
                cargarTareas();
            }
        });

        btnAlternar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int id = (int) modelo.getValueAt(fila, 0);
                Tarea tarea = new Tarea((String) modelo.getValueAt(fila, 1), 1, null, false);
                tarea.setId(id);
                servicio.alternarEstado(tarea);
                cargarTareas();
            }
        });

        btnDeshacer.addActionListener(e -> {
            servicio.deshacerEliminacion();
            cargarTareas();
        });

        cargarTareas();
    }

    public void cargarTareas() {
        modelo.setRowCount(0);
        List<Tarea> tareas = servicio.obtenerTareas();
        for (Tarea t : tareas) {
            modelo.addRow(new Object[]{
                t.getId(),
                t.getTitulo(),
                t.getPrioridad(),
                t.isEspecial() ? "★" : "",
                t.isHecha() ? "✔" : "✘"
            });
        }
    }
}
