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
            System.out.println("3. Eliminar estudiante");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
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
                    System.out.print("ID del estudiante a eliminar: ");
                    String idEliminar = sc.nextLine();
                    dao.eliminar(idEliminar);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    Conexion.cerrarConexion();
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}