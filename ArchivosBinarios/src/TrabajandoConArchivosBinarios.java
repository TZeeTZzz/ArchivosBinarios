import java.io.*;
import java.util.List;

import Clases.Perro;
public class TrabajandoConArchivosBinarios {

	public static void main(String[] args) {
		
	}
	
	// Crear un archivo BINARIO. 
	public static void crearArchivo(String nombreArchivo) {
		try {
			/* Abrimos el flujo de datos entre el archivo y el procesador, pasandole el nombre
			 * del archivo que queremos crear. (Si el archivo no existe, se crea).*/
			FileOutputStream file = new FileOutputStream(nombreArchivo);
			/* Persistimos el archivo. Abrimos el flujo de dato más detallado y le pasamos el archivo.
			 * Podríamos escribir un dato adentro.*/		
			DataOutputStream salida = new DataOutputStream(file);
			
			salida.close();
			file.close();
			
			System.out.println("El archivo se ha creado correctamente");

		} catch (FileNotFoundException e) {
			e.printStackTrace(); // Excepcion para el FileOutputStream.
		} catch (IOException e) {
			e.printStackTrace(); // Excepcion para el DataOutputStream.
		}
	}
	
	// Escribir un archivo BINARIO.
	/* En este caso, es similar al crear el archivo pero recibimos un parámetro extra. 
	 * Además, en el FileOutputStream tenemos un true y, debajo, el data.writeInt(dato).*/
	public static void escribirArchivo(String nombreArchivo, int dato /* Recibimos un dato tipo entero.*/) {
		try {
			FileOutputStream file = new FileOutputStream(nombreArchivo,true /* Le decimos que todos los datos que se vayan
			a escribir, sean agregados al final del archivo. */);
			DataOutputStream data = new DataOutputStream(file);
			
			/* Desde la misma variable del flujo (data en este caso) escribimos el dato recibido como parámetro.*/
			data.writeInt(dato);
			
			data.close();
			file.close();
			
			System.out.println("El archivo se ha escrito correctamente");
			
		} catch( FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Leer un archivo BINARIO. 
	public static void leerArchivo(String nombreArchivo) {
		try {
			/* Abrimos el flujo de datos */
			FileInputStream file = new FileInputStream(nombreArchivo);
			DataInputStream data = new DataInputStream(file);
			
			/* Luego, para escribir: */
			while(data.available() > 0 /* Mientras la información disponible (data.available()) sea mayor a 0.*/) {
				
				/* Lo ideal sería guardar todos los datos que leamos dentro de un arreglo específico 
				 * para cada tipo de dato. Ya que, una vez leídos, los datos se pierden. (En este ejemplo únicamente
				 * se están mostrando por consola).*/
				System.out.println(data.readInt());
				
				System.out.println(data.readUTF());
				
				System.out.println(data.readDouble());
				
				System.out.println("----------------------------------");
				
				data.close();
				file.close();
			}
			
		} catch(IOException e) {
			System.out.println("Ocurrió un error al intentar leer el archivo binario.");
		}
	}
	
	// Borrar el contenido de un archivo BINARIO. 
	public static void borrarContenidoArchivo(String nombreArchivo) {
		
		try {
			
			// Referenciamos al archivo, como si fuera un archivo normal.
			File file= new File(nombreArchivo);
			
			/* Abrimos el archivo en modo de escritura, sin agregar datos nuevos. 
			 * Además, ponemos "false", para que se reemplace la información anterior se reemplace.
			 * Como no le estamos agregando información nueva, ahora no tendrá contenido. */
			FileOutputStream flujoSalida = new FileOutputStream(file, false);
			
			/* Cerramos el flujo de salida para borrar el contenido del archivo. */
			flujoSalida.close();
			
			System.out.println("Se ha borrado el contenido del archivo binario.");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Eliminar un archivo BINARIO.
	public static void eliminarArchivo(String nombreArchivo) {
		
		try {
			// Referenciamos el archivo. 
			File file = new File(nombreArchivo);
			
			if(file.delete()) {
				System.out.println("Se ha eliminado el archivo binario.");
			} else {
				System.out.println("No se pudo eliminar el archivo binario.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ARCHIVOS CON FORMATO DE OBJETO en vez de DATOS PRIMITIVOS.
	
	// Crear archivo. 
	public static void crearArchivoDeObjetos(String nombreArchivo) {
		
		try {
			
			// Creamos la salida del archivo.
			FileOutputStream file = new FileOutputStream(nombreArchivo);
			
			// Abrimos el flujo de datos diciendo que este flujo contendrá objetos y no variables sueltas.
			ObjectOutputStream salida = new ObjectOutputStream(file);
			
			salida.close();
			file.close();
			System.out.println("El archivo se ha creado correctamente.");
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Escribir archivo. 
	public static void escribirArchivoDeObjetos(String nombreArchivo, List<Perro> listaObjetos) {
		
		try {
			FileOutputStream file = new FileOutputStream(nombreArchivo);
			
			ObjectOutputStream objOutput = new ObjectOutputStream(file);
			
			for(Perro objeto: listaObjetos) {
				objOutput.writeObject(objeto);
			}
			
			objOutput.close();
			file.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// Leer archivo. 
	public static void leerArchivoDeObjetos(String nombreArchivo) {
		// Primero, pasamos la referencia del archivo. 
		File archivo = new File(nombreArchivo);
		
		// Comprobamos si el archivo existe. 
		if(archivo.exists()) {
			/* Estas variables siempre van a ser creadas por lo que se hacen fuera del try. 
			 * Se crean en null ya que su ejecución luego irá en el try/catch.*/
			
			FileInputStream file = null;
			ObjectInputStream input = null;
			
			try {
				/* Primero, se intenta cambiar el valor de file. (Se intenta abrir el archivo. */
				file = new FileInputStream(nombreArchivo);
				/* Se va a intentar el flujo de datos de objetos del archivo. */
				input = new ObjectInputStream(file);
				
				/* Desde el input, declaramos una variable del tipo de nuestra clase. Va a ser igual al 
				 * casteo a ese dato (en este caso Perro) a lo que nos devuelva input.readObject()
				 * input.readObject() lee el primer objeto disponible de nuestro archivo. */
				Perro obj = (Perro) input.readObject();
				
				while(obj != null) {
					
					// Realizar las operaciones necesarias con el objeto leído.
					/* Por ejemplo, lo que podríamos hacer es guardar esos objetos en un ArrayList para tenerlas 
					 * disponibles en memoria.*/
					System.out.println(obj.toString());
					
					// Cambiamos el valor de obj por lo mismo. Leer el siguiente objeto del archivo.
					obj = (Perro) input.readObject();
				}
				
				input.close();
				file.close();
				
			} catch(EOFException e) { /* Este error va a saltar cuando ya no haya más datos por leer. */
				System.out.println("Se han leído todos los objetos del archivo");
				try {
					/* Se intentan cerrar ambas partes del archivo. Si no se puede, se maneja el error. */
					input.close();
					file.close();
				} catch(IOException e1) {
					e1.printStackTrace();
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
		System.out.println("No se puede leer el archivo porque no existe.");
		}
	}
	
}
