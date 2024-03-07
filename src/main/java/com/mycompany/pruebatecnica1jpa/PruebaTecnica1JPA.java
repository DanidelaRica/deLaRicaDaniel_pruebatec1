package com.mycompany.pruebatecnica1jpa;

import com.mycompany.pruebatecnica1jpa.logica.Controladora;
import com.mycompany.pruebatecnica1jpa.logica.Empleado;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PruebaTecnica1JPA {

    private static Controladora control = new Controladora();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int opcion; // Ya no es necesario inicializarlo con -1

        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim()); // Leer la opción y convertirla a entero

                switch (opcion) {
                    case 1:
                        System.out.println("Opción 1: Agregar un nuevo empleado");
                        crearNuevoEmpleado();
                        break;
                    case 2:
                        System.out.println("Opción 2: Listar empleados");
                        System.out.println("-----------------------------------------------");
                        listarEmpleados();
                        break;
                    case 3:
                        System.out.println("-----------------------------------------------");
                        System.out.println("Opción 3: Actualizar información de un empleado");
                        // Obtener el ID del empleado a actualizar
                        System.out.println("Ingrese el ID del empleado a actualizar: ");
                        System.out.println("-----------------------------------------------");
                        int idEmpleado = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer del Scanner
                        // Obtener el empleado con el ID proporcionado
                        Empleado empleadoActualizar = control.traerEmpleadoPorId(idEmpleado);
                        // Verificar si el empleado existe
                        if (empleadoActualizar != null) {
                            mostrarSubMenuActualizar(empleadoActualizar);
                        } else {
                            System.out.println("Empleado no encontrado.");
                        }
                        break;
                    case 4:
                        System.out.println("Opción 4: Eliminar un empleado");
                        eliminarEmpleado();
                        scanner.nextLine();
                        break;
                    case 5:
                        System.out.println("Opción 5: Buscar empleados por cargo");
                        System.out.print("Ingrese el cargo del empleado a buscar: ");
                        String cargoABuscar = scanner.next();
                        scanner.nextLine();
                        List<Empleado> empleadosPorCargo = control.traerEmpleadosPorCargo(cargoABuscar);
                        if (empleadosPorCargo.isEmpty()) {
                            System.out.println("No se encontraron empleados con el cargo especificado.");
                        } else {
                            System.out.println("Empleados encontrados con el cargo '" + cargoABuscar + "':");
                            for (Empleado empleado : empleadosPorCargo) {
                                System.out.println(empleado.toString());
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Error: Opción no válida");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                System.out.println("Presione enter para volver al menú");
                opcion = -1; // Establecer una opción inválida para que se repita el bucle
                scanner.nextLine(); // Limpiar el buffer del Scanner
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│          Gestión de Empleados              │");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("│ 1. Agregar un nuevo empleado               │");
        System.out.println("│ 2. Listar empleados                        │");
        System.out.println("│ 3. Actualizar información de un empleado   │");
        System.out.println("│ 4. Eliminar un empleado                    │");
        System.out.println("│ 5. Buscar empleados por cargo              │");
        System.out.println("│ 0. Salir                                   │");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.print("Selecciona una opción: ");
    }

    private static void crearNuevoEmpleado() {
        String nombre = "";
        while (nombre.isEmpty()) {
            System.out.print("Ingrese el nombre del empleado: ");
            nombre = scanner.nextLine();
            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
            }
        }

        String apellido = "";
        while (apellido.isEmpty()) {
            System.out.print("Ingrese el apellido del empleado: ");
            apellido = scanner.nextLine().trim();
            if (apellido.isEmpty()) {
                System.out.println("Error: El apellido no puede estar vacío.");
            }
        }

        System.out.print("Ingrese el cargo del empleado: ");
        String cargo = scanner.nextLine().trim();

        double salario = 0;
        boolean salarioValido = false;
        while (!salarioValido) {
            try {
                System.out.print("Ingrese el salario del empleado: ");
                salario = scanner.nextDouble();
                salarioValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: El salario debe ser un número válido.");
                scanner.next(); // Limpiar el buffer del Scanner en caso de error
            }
        }

        String fechaInicio = "";
        while (fechaInicio.isEmpty() || !fechaInicio.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.print("Ingrese la fecha de inicio del empleado (formato YYYY-MM-DD): ");
            fechaInicio = scanner.next();
            if (!fechaInicio.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Error: Formato de fecha incorrecto. Utilice el formato YYYY-MM-DD.");
            }
        }

        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombre(nombre);
        nuevoEmpleado.setApellido(apellido);
        nuevoEmpleado.setCargo(cargo);
        nuevoEmpleado.setSalario(salario);
        nuevoEmpleado.setFechaInicio(fechaInicio);

        control.crearEmpleado(nuevoEmpleado);
        System.out.println("Empleado creado exitosamente.");

        // Esperar la entrada del usuario antes de continuar
        System.out.println("Presione Enter para volver al menú principal...");
        scanner.nextLine(); // Consumir la nueva línea después de nextDouble()
        scanner.nextLine(); // Esperar a que el usuario presione Enter
    }

    private static void listarEmpleados() {
        List<Empleado> listaEmpleados = control.traerEmpleado();
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("Lista de empleados:");
            System.out.println("-----------------------------------------------");
            for (Empleado empleado : listaEmpleados) {
                System.out.println(empleado.toString());
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    private static void mostrarSubMenuActualizar(Empleado empleado) {
        System.out.println("-----------------------------------------------");
        System.out.println("Seleccione el atributo a actualizar:");
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. Cargo");
        System.out.println("4. Salario");
        System.out.println("5. Fecha de inicio");
        System.out.print("Ingrese su opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente

        switch (opcion) {
            case 1:
                actualizarNombre(empleado);
                break;
            case 2:
                actualizarApellido(empleado);
                break;
            case 3:
                actualizarCargo(empleado);
                break;
            case 4:
                actualizarSalario(empleado);
                break;
            case 5:
                actualizarFechaInicio(empleado);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void actualizarNombre(Empleado empleado) {
        System.out.print("Ingrese el nuevo nombre del empleado: ");
        String nuevoNombre = scanner.nextLine();
        empleado.setNombre(nuevoNombre);
        control.editarEmpleado(empleado);
        System.out.println("Nombre actualizado exitosamente.");
    }

    private static void actualizarApellido(Empleado empleado) {
        System.out.print("Ingrese el nuevo apellido del empleado: ");
        String nuevoApellido = scanner.nextLine();
        empleado.setApellido(nuevoApellido);
        control.editarEmpleado(empleado);
        System.out.println("Apellido actualizado exitosamente.");
    }

    private static void actualizarCargo(Empleado empleado) {
        System.out.print("Ingrese el nuevo cargo del empleado: ");
        String nuevoCargo = scanner.nextLine();
        empleado.setCargo(nuevoCargo);
        control.editarEmpleado(empleado);
        System.out.println("Cargo actualizado exitosamente.");
    }

    private static void actualizarSalario(Empleado empleado) {
        System.out.print("Ingrese el nuevo salario del empleado: ");
        double nuevoSalario;
        try {
            nuevoSalario = scanner.nextDouble();
            scanner.nextLine(); // Consumir la nueva línea pendiente
            if (nuevoSalario <= 0) {
                throw new SalarioInvalidoException("Error: El salario debe ser un número válido y mayor que cero.");//Lanzamos la excepcion personalizada
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: El salario debe ser un número válido.");
            scanner.nextLine(); // Limpiar el búfer del scanner
            return;
        } catch (SalarioInvalidoException e) {
            System.out.println(e.getMessage());
            return;
        }
        empleado.setSalario(nuevoSalario);
        control.editarEmpleado(empleado);
        System.out.println("Salario actualizado exitosamente.");
    }

    private static void actualizarFechaInicio(Empleado empleado) {
        System.out.print("Ingrese la nueva fecha de inicio del empleado (formato YYYY-MM-DD): ");
        String nuevaFechaInicio = scanner.nextLine();
        // Verificar el formato de la fecha de inicio
        if (!nuevaFechaInicio.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Error: Formato de fecha incorrecto. Utilice el formato YYYY-MM-DD.");
            return;
        }
        empleado.setFechaInicio(nuevaFechaInicio);
        control.editarEmpleado(empleado);
        System.out.println("Fecha de inicio actualizada exitosamente.");
    }

    private static void eliminarEmpleado() throws Exception {
        System.out.print("Ingrese el ID del empleado que desea eliminar: ");
        int idEmpleado = scanner.nextInt();

        control.eliminarEmpleado(idEmpleado);
        System.out.println("Empleado eliminado exitosamente.");
    }

}
