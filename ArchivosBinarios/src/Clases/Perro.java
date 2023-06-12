package Clases;
import java.io.Serializable;

public class Perro implements Serializable{
	String nombre;
	String raza;
	String edad;
	
	public Perro() {
		
	}
	
	public Perro(String nombre, String raza, String edad) {
		this.nombre = nombre;
		this.raza = raza;
		this.edad = edad;
	}
	
	

}
