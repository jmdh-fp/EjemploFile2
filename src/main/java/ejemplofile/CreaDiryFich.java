package ejemplofile;

import java.io.File;
import java.io.IOException;
import java.net.UnixDomainSocketAddress;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class CreaDiryFich {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Crea directorio llamado ficheros en la ruta actual
		File directorio = new File("ficheros");
		if (!directorio.mkdir()) {

			System.err.println("No se ha podido crear el directorio");
			System.exit(-1);

		}
		System.out.println("Directorio creado");
		//scanner.next();

		// Crea el fichero "fichero.txt" dentro del directorio creado anteriormente

		File fichero = new File(directorio, "fichero.txt");

		try {
			fichero.createNewFile();
			System.out.println("Fichero creado correctamente");
			//scanner.next();
		} catch (IOException ex) {
			// IOException es la excepcion principal de cualquier operacion de entrada y
			// salida en Java
			System.err.println("No se ha podido crear el fichero");
			System.err.println(ex.getMessage());
			System.exit(-1);
		}

		// Mueve/renombra el fichero a "fichero_copia.txt" dentro del mismo directorio
		File renombrado = new File(directorio, "fichero_copia.txt");
		fichero.renameTo(renombrado);
		System.out.println("Fichero " + fichero.getName() + " renombrado a " + renombrado.getName());
		//scanner.next();

		// ¿Se puede hacer copia con File? No.
		// Alternativas.
		// - Hacer programa usando clases FileInputStream y FileOutputStream del paquete
		// java.io
		// - Utilización de librerias FileUtils de Apache
		// - Etc. Ver: https://www.digitalocean.com/community/tutorials/java-copy-file

		// Usando la librería JavaUtils de Apache.
		// Hacemos coppia de "fichero_copia.txt" a "fichero.txt"
		try {
			FileUtils.copyFile(renombrado, fichero);
			System.out.println("renombrado " + renombrado.getAbsolutePath());
			System.out.println("fichero " + fichero.getAbsolutePath());
		} catch (IOException ex) {
			System.err.println("No se ha podido copiar el fichero " + renombrado.getName() + ", al fichero: " + fichero.getName());
		}
		
		// Mueve el fichero al directorio por defecto
		
		try {
			FileUtils.moveFile(fichero, new File("fichero.txt"));
		} catch (IOException ex) {
			System.err.println("No se ha podido mover el fichero: " + fichero.getName());
		}

		System.out.println("Finalizando programa...");
	}
}
