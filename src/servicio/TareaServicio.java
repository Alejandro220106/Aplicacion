/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import dao.TareaDAO;
import dominio.Tarea;
import java.util.List;
import java.util.Stack;

public class TareaServicio {
    private TareaDAO dao = new TareaDAO();
    private Stack<Tarea> pilaEliminadas = new Stack<>();

    public void agregarTarea(Tarea tarea) {
        dao.insertarTarea(tarea);
    }

    public List<Tarea> obtenerTareas() {
        return dao.listarTareas();
    }

    public void eliminarTarea(Tarea tarea) {
        dao.marcarEliminada(tarea.getId());
        pilaEliminadas.push(tarea);
    }

    public void deshacerEliminacion() {
        if (!pilaEliminadas.isEmpty()) {
            Tarea tarea = pilaEliminadas.pop();
            tarea.setEliminada(false);
            dao.insertarTarea(tarea); // Reinsertar como nueva
        }
    }

    public void alternarEstado(Tarea tarea) {
        boolean nuevoEstado = !tarea.isHecha();
        dao.alternarEstado(tarea.getId(), nuevoEstado);
        tarea.setHecha(nuevoEstado);
    }
}
