package com.techlab.entidades;

public class Articulo {
		
	private int id;
	private String nombre;
	private double precio;
	// estatica para la creacion de ids
	static int contador = 0;
	
	public Articulo(String nombreParametro, double precioParametro) {
		this.id = ++contador;
		this.nombre = nombreParametro;
		this.precio = precioParametro;
		
		
	}
	
	
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public void mostrar() {
		System.out.println("ID: " + this.id + " | Nombre: " + this.nombre  + " | Precio: $" + this.precio);
	}


}
