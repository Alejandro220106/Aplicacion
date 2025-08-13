/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiux;

import dominio.Tarea;
import servicio.TareaServicio;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PanelFormulario extends JPanel {
    private JTextField campoTitulo;
    private JComboBox<String> comboPrioridad;
    private JCheckBox checkEspecial;
    private JButton btnAgregar;
    private TareaServicio servicio;

    public PanelFormulario(TareaServicio servicio) {
        this.servicio = servicio;
        setLayout(new GridLayout(2, 4));

        campoTitulo = new JTextField();
        comboPrioridad = new JComboBox<>(new String[]{"Alta", "Media", "Baja"});
        checkEspecial = new JCheckBox("Especial");
        btnAgregar = new JButton("Agregar");

        add(new JLabel("Título:"));
        add(campoTitulo);
        add(new JLabel("Prioridad:"));
        add(comboPrioridad);
        add(checkEspecial);
        add(new JLabel());
        add(btnAgregar);

        btnAgregar.addActionListener(e -> {
            String titulo = campoTitulo.getText().trim();
            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Título vacío");
                return;
            }

            int prioridad = comboPrioridad.getSelectedIndex() + 1;
            boolean especial = checkEspecial.isSelected();

            Tarea tarea = new Tarea(titulo, prioridad, LocalDate.now(), especial);
            servicio.agregarTarea(tarea);
            campoTitulo.setText("");
            comboPrioridad.setSelectedIndex(0);
            checkEspecial.setSelected(false);
        });
    }
}
