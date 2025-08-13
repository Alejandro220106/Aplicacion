/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiux;

import servicio.TareaServicio;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private TareaServicio servicio;
    private PanelFormulario panelFormulario;
    private PanelListaTareas panelLista;

    public VentanaPrincipal() {
        setTitle("Gestor de Tareas");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        servicio = new TareaServicio();

        panelFormulario = new PanelFormulario(servicio);
        panelLista = new PanelListaTareas(servicio);

        add(panelFormulario, BorderLayout.NORTH);
        add(panelLista, BorderLayout.CENTER);
    }

    public void setServicio(TareaServicio servicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
