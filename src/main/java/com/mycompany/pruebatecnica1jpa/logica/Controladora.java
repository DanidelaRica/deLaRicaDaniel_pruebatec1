package com.mycompany.pruebatecnica1jpa.logica;

import com.mycompany.pruebatecnica1jpa.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void crearEmpleado(Empleado emple) {
        controlPersis.crearEmpleado(emple);
    }

    public void eliminarEmpleado(int id) throws Exception {
        controlPersis.eliminarEmpleado(id);
    }

    public List<Empleado> traerEmpleado() {
        return controlPersis.traerEmpleados();
    }

    public Empleado traerMascota(int id) {
        return controlPersis.traerEmpleado(id);
    }

    public void editarEmpleado(Empleado empleado) {
        controlPersis.editarEmpleado(empleado);
    }

    public Empleado traerEmpleadoPorId(int id) {
        return controlPersis.traerEmpleadoPorId(id);
    }

    public List<Empleado> traerEmpleadosPorCargo(String cargo) {
        return controlPersis.traerEmpleadosPorCargo(cargo);
    }

}
