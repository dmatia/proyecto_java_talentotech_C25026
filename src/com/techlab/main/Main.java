package com.techlab.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.entidades.Articulo;

public class Main {
	
	static ArrayList<Articulo> listaArticulos = new ArrayList();
	static Scanner sc = new Scanner(System.in);
	
	public static void main (String[] args) {
		int opcion;
		
		do {
			System.out.println("\n--- Menú de artículos ---");
			System.out.println("1 - Crear artículo");
			System.out.println("2 - Listar artículos");
			System.out.println("3 - Modificar artículo");
			System.out.println("4 - Eliminar artículo");
			System.out.println("5 - Salir");
			System.out.println("Seleccione una opción:");
			opcion = sc.nextInt();
			sc.nextLine();
			
			switch(opcion) {
			case 1 -> crearArticulo();
			case 2 -> listarArticulos();
			case 3 -> modificarArticulo();
			case 4 -> eliminarArticulo();
			case 5 -> System.out.println("Saliendo...");
			default -> System.out.println("Opción inválida");
			}
	} while (opcion != 5);		
}
	
	public static void crearArticulo() {
	    String nombre;
	    double precio = -1;

	    // si el nombre esta vacio queda en bucle hasta que ingrese un nombre
	    while (true) {
	        System.out.print("Nombre: ");
	        nombre = sc.nextLine().trim();
	        if (!nombre.isEmpty()) {
	            break;
	        } else {
	            System.out.println("⚠️ El nombre no puede estar vacío. Intente de nuevo.");
	        }
	    }

	    // si el nombre esta vacio queda en bucle hasta que ingrese un precio valido
	    while (true) {
	        System.out.print("Precio: ");
	        try {
	            precio = Double.parseDouble(sc.nextLine());
	            if (precio > 0) {
	                break;
	            } else {
	                System.out.println("El precio debe ser un número positivo. Intente de nuevo.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("El precio debe ser un valor númerico positivo. Intente de nuevo.");
	        }
	    }

	    Articulo nuevoArticulo = new Articulo(nombre, precio);
	    listaArticulos.add(nuevoArticulo);
	    System.out.println("\n--- Nuevo Articulo: ");
	    nuevoArticulo.mostrar();
	    System.out.println("\n--- Artículo agregado correctamente");
	}
	
	public static void listarArticulos() {
		if(listaArticulos.isEmpty()) {
			System.out.println("La lista está vacia.");
		}else {
			for (Articulo a : listaArticulos) {
				a.mostrar();
			}
		}
	}
	
	public static void modificarArticulo() {
		System.out.println("ID del artículo a modificar: ");
		int id = sc.nextInt();
		for (Articulo a : listaArticulos) {
			if(a.getId() == id) {
				sc.nextLine();
				System.out.println("Nuevo nombre: ");
				a.setNombre(sc.nextLine());
				System.out.println("Nuevo precio: ");
				a.setPrecio(sc.nextDouble());
				System.out.println("Artículo Actualizado!");
				return;
			}
		}
		System.out.println("Artículo no encontrado.");
	}
	
	public static void eliminarArticulo() {
		System.out.println("ID del articulo a eliminar: ");
		int id = sc.nextInt();
		if(listaArticulos.removeIf(a -> a.getId() == id)) {
		System.out.println("Articulo eliminado correctamente!");
		}else {
			System.out.println("No se encontró un artículo para el Id ingresado.");
		}
	}
	
	
	
}
