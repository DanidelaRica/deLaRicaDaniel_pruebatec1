package com.mycompany.pruebatecnica1jpa.persistencia;

import com.mycompany.pruebatecnica1jpa.logica.Empleado;
import com.mycompany.pruebatecnica1jpa.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    EmpleadoJpaController empleJpa = new EmpleadoJpaController();

    public void crearEmpleado(Empleado emple) {
        empleJpa.create(emple);
    }

    public void eliminarEmpleado(int id) throws Exception {
        try {
            // empleJpa.destroy(id);

            Empleado empleadoBorrar = empleJpa.findEmpleado(id);
            empleadoBorrar.setBorrado(true);
            empleJpa.edit(empleadoBorrar);

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Empleado> traerEmpleados() {
        //return empleJpa.findEmpleadoEntities();

        List<Empleado> listEmpleadosNoBorrados = new ArrayList();
        for (Empleado empleado : empleJpa.findEmpleadoEntities()) {
            if (!empleado.getBorrado()) {
                listEmpleadosNoBorrados.add(empleado);
            }
        }
        return listEmpleadosNoBorrados;
    }

    public Empleado traerEmpleado(int id) {
        return empleJpa.findEmpleado(id);
    }

    public void editarEmpleado(Empleado emplea) {
        try {
            empleJpa.edit(emplea);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Devuelve un empleado por su ID.
    public Empleado traerEmpleadoPorId(int id) {
        return empleJpa.findEmpleado(id);
    }
    // Devuelve una lista de empleados por su cargo.
    public List<Empleado> traerEmpleadosPorCargo(String cargo) {
        return empleJpa.findEmpleadosByCargo(cargo);
    }

}
