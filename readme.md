# Gestión de Empleados

Este proyecto es una aplicación de consola que permite gestionar empleados, incluyendo la creación, actualización, listado y eliminación de empleados.

## Funcionalidades

- Agregar un nuevo empleado.
- Listar empleados existentes.
- Actualizar la información de un empleado.
- Eliminar un empleado.
- Buscar empleados por cargo.

## Tecnologías Utilizadas

- Java
- JPA (Java Persistence API)
- MySQL 
- Git

## Instrucciones de Uso

1. Clona este repositorio en tu máquina local.
2. Configura tu base de datos MySQL y actualiza la configuración de conexión en el archivo `persistence.xml`.
3. Compila y ejecuta el proyecto usando tu IDE preferido o desde la línea de comandos.
4. Sigue las instrucciones en la consola para utilizar las diferentes funcionalidades de la aplicación.

## Pruebas Realizadas

- Creación de un nuevo empleado con datos válidos.
- Creación de un nuevo empleado con datos inválidos (por ejemplo, nombre o apellido vacíos, introducir letras en el salario).
- Actualización de la información de un empleado.
- Eliminación de un empleado, utilizando borrado lógico.
- Búsqueda de empleados por cargo.
- Manejo de errores y excepciones. Se creo una excepcion personalizada SalarioInvalidoException.
- Integración con una base de datos MySQL.
