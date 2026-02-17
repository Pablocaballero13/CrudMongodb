package com.mycompany.Main;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EstudianteDAO dao = new EstudianteDAO();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=============================");
            System.out.println("   CRUD - MongoDB + Java     ");
            System.out.println("=============================");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Ver todos los estudiantes");
            System.out.println("3. Buscar por nombre");
            System.out.println("4. Actualizar estudiante");
            System.out.println("5. Eliminar estudiante");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    // CREATE
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Carrera: ");
                    String carrera = sc.nextLine();

                    Estudiante nuevo = new Estudiante(nombre, apellido, edad, carrera);
                    dao.insertar(nuevo);
                    break;

                case 2:
                    // READ ALL
                    List<Estudiante> lista = dao.obtenerTodos();
                    if (lista.isEmpty()) {
                        System.out.println("No hay estudiantes registrados.");
                    } else {
                        System.out.println("\n--- Lista de Estudiantes ---");
                        for (Estudiante e : lista) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 3:
                    // READ BY NAME
                    System.out.print("Nombre a buscar: ");
                    String buscar = sc.nextLine();
                    Estudiante encontrado = dao.buscarPorNombre(buscar);
                    if (encontrado != null) {
                        System.out.println("Encontrado: " + encontrado);
                    } else {
                        System.out.println("No se encontró el estudiante.");
                    }
                    break;

                case 4:
                    // UPDATE
                    System.out.print("ID del estudiante a actualizar: ");
                    String idActualizar = sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nNombre = sc.nextLine();
                    System.out.print("Nuevo apellido: ");
                    String nApellido = sc.nextLine();
                    System.out.print("Nueva edad: ");
                    int nEdad = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nueva carrera: ");
                    String nCarrera = sc.nextLine();

                    Estudiante actualizado = new Estudiante(nNombre, nApellido, nEdad, nCarrera);
                    dao.actualizar(idActualizar, actualizado);
                    break;

                case 5:
                    // DELETE
                    System.out.print("ID del estudiante a eliminar: ");
                    String idEliminar = sc.nextLine();
                    dao.eliminar(idEliminar);
                    break;

                case 0:
                    System.out.println("👋 Saliendo...");
                    Conexion.cerrarConexion();
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}