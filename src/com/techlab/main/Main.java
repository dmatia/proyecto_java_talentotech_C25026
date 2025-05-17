package com.techlab.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.entidades.Articulo;

public class Main {
	
	static ArrayList<Articulo> listaArticulos = new ArrayList();
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
	    int opcion = 0;

	    do {
	        System.out.println("\n--- Menú de artículos ---");
	        System.out.println("1 - Crear artículo");
	        System.out.println("2 - Listar artículos");
	        System.out.println("3 - Buscar/Modificar artículo");
	        System.out.println("4 - Eliminar artículo");
	        System.out.println("5 - Salir");
	        System.out.print("Seleccione una opción: ");

	        String entrada = sc.nextLine();

	        // valido que la entrada sea una dato numerico 
	        try {
	            opcion = Integer.parseInt(entrada);

	            switch (opcion) {
	                case 1 -> crearArticulo();
	                case 2 -> listarArticulos();
	                case 3 -> modificarArticulo();
	                case 4 -> eliminarArticulo();
	                case 5 -> System.out.println("Saliendo...");
	                default -> System.out.println("Opción inválida. Intente de nuevo.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Por favor ingrese un número del 1 al 5.");
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
	            System.out.println("Ingrese un nombre valido.");
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
	
	public static void eliminarArticulo() {
		System.out.println("ID del articulo a eliminar: ");
		int id = sc.nextInt();
		if(listaArticulos.removeIf(a -> a.getId() == id)) {
		System.out.println("Articulo eliminado correctamente!");
		}else {
			System.out.println("No se encontró un artículo para el Id ingresado.");
		}
	}
	
	public static void modificarArticulo() {
		//valido que la lista no esté vacía
	    if (listaArticulos.isEmpty()) {
	        System.out.println("No hay artículos cargados.");
	        return;
	    }

	    // seleccionar buscar por id o por nombre
	    System.out.println("¿Cómo desea buscar el artículo?");
	    System.out.println("1. Por ID");
	    System.out.println("2. Por Nombre");
	    System.out.print("Opción: ");
	    
	    // valido que el dato ingresado sea numerico
	    int opcionBusqueda;
	    while (true) {
	        try {
	            opcionBusqueda = Integer.parseInt(sc.nextLine());
	            if (opcionBusqueda == 1 || opcionBusqueda == 2) break;
	            else System.out.print("Opción inválida. Ingrese 1 o 2: ");
	        } catch (NumberFormatException e) {
	            System.out.print("Por favor ingrese un número: ");
	        }
	    }

	    Articulo articuloEncontrado = null;

	    // si elige por ID busca el articulo por id
	    if (opcionBusqueda == 1) {
	        System.out.print("Ingrese ID del artículo: ");
	        int id;
	        while (true) {
	        	// valido que sea un numero
	            try {
	                id = Integer.parseInt(sc.nextLine());
	                break;
	            } catch (NumberFormatException e) {
	                System.out.print("Por favor ingrese un número: ");
	            }
	        }

	        for (Articulo a : listaArticulos) {
	            if (a.getId() == id) {
	                articuloEncontrado = a;
	                break;
	            }
	        }
	    } else {
	    	// busqueda por nombre
	        System.out.print("Ingrese nombre del artículo: ");
	        String nombreBuscado = sc.nextLine().trim().toLowerCase();

	        for (Articulo a : listaArticulos) {
	            if (a.getNombre().toLowerCase().contains(nombreBuscado)) {
	                articuloEncontrado = a;
	                break;
	            }
	        }
	    }

	    // 	Si no se encontró el artículo por el dato ingresado
	    if (articuloEncontrado == null) {
	        System.out.println("Artículo no encontrado.");
	        return;
	    }

	    // si se encontró entra en flujo de modificación
	    System.out.println("\n--- ARTÍCULO ENCONTRADO ---");
	    articuloEncontrado.mostrar();

	    System.out.print("\n¿Desea modificar este artículo? (s/n): ");
	    String confirmacion = sc.nextLine().trim().toLowerCase();
	    if (!confirmacion.equals("s")) {
	        System.out.println("Modificación cancelada.");
	        return;
	    }

	    System.out.print("Nuevo nombre (deje vacío para mantener): ");
	    String nuevoNombre = sc.nextLine().trim();
	    if (!nuevoNombre.isEmpty()) {
	        articuloEncontrado.setNombre(nuevoNombre);
	    }

	    System.out.print("Nuevo precio (deje vacío para mantener): ");
	    String precioStr = sc.nextLine().trim();
	    if (!precioStr.isEmpty()) {
	        try {
	            double nuevoPrecio = Double.parseDouble(precioStr);
	            if (nuevoPrecio < 0) {
	                System.out.println("El precio no puede ser negativo. Se mantuvo el precio anterior.");
	            } else {
	                articuloEncontrado.setPrecio(nuevoPrecio);
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Precio inválido. Se mantuvo el precio anterior.");
	        }
	    }

	    System.out.println("Artículo actualizado con éxito.");
	}

	
	
	
}


// test branch