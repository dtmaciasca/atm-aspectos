package ejemplo.cajero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ejemplo.cajero.control.Comando;
import ejemplo.cajero.control.ComandoConsignar;
import ejemplo.cajero.control.ComandoListarOperaciones;
import ejemplo.cajero.control.ComandoRetirar;
import ejemplo.cajero.control.ComandoSaldo;
import ejemplo.cajero.control.ComandoTransferir;
import ejemplo.cajero.modelo.Banco;
import ejemplo.cajero.modelo.Cuenta;
import ejemplo.cajero.modelo.Usuario;

/**
 * Simulador de un Cajero de Banco
 */
public class Cajero {

	/**
	 * Programa principal
	 * 
	 * @param args parámetros de línea de comandos. Son ignorados por el programa.
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// crea el banco
		Banco banco = new Banco();

		// crea unas cuentas, para la prueba
		banco.agregarCuenta(new Cuenta("1", "clave", 500000));
		banco.agregarCuenta(new Cuenta("2", "clave", 200000));
		banco.agregarCuenta(new Cuenta("3", "clave", 300000));

		// crea usuarios para la prueba
		banco.agregarUsuario(new Usuario("1032", "0000", banco.buscarCuenta("1")));
		banco.agregarUsuario(new Usuario("1033", "1234", banco.buscarCuenta("2")));
		banco.agregarUsuario(new Usuario("1034", "4321", banco.buscarCuenta("3")));

		// crea los comandos que se van a usar en la aplicación
		List<Comando> comandos = cargaComandos();

		// Ciclo del Programa
		// ==================

		System.out.println("Cajero Automático");
		System.out.println("=================");
		System.out.println();

		boolean validarUsuario = false;
		Scanner console = new Scanner(System.in);
		System.out.print("Ingreso la cedula: ");
		String cedula = console.nextLine();
		System.out.print("Ingreso la clave: ");
		String clave = console.nextLine();
		validarUsuario = banco.validarUsuario(cedula, clave);
		System.out.println();

		if (validarUsuario) {
			Usuario usuario = banco.buscarUsuario(cedula);
			boolean fin = false;
			do {

				// muestra los nombres de los comandos
				muestraMenuConComandos(comandos);
				System.out.println("X.- Salir");

				// la clase Console no funciona bien en Eclipse
				String valorIngresado = console.nextLine();

				// obtiene el comando a ejecutar
				Comando comandoSeleccionado = retornaComandoSeleccionado(comandos, valorIngresado);
				if (comandoSeleccionado != null) {

					// intenta ejecutar el comando
					try {
						comandoSeleccionado.ejecutar(banco, usuario);

					} catch (Exception e) {
						// si hay una excepción, muestra el mensaje
						System.err.println(e.getMessage());
					}

				}
				// si no se obtuvo un comando, puede ser la opción de salir
				else if (valorIngresado.equalsIgnoreCase("X")) {
					fin = true;
				}

				System.out.println();
			} while (!fin);

			System.out.println("Gracias por usar el programa.");
		}
	}

	// Manejo de los comandos de la aplicación
	// =======================================

	// carga los comandos usados en el programa
	private static List<Comando> cargaComandos() {

		// crea los comandos que se van a usar en la aplicación
		List<Comando> comandos = new ArrayList<>();

		comandos.add(new ComandoListarOperaciones());
		comandos.add(new ComandoSaldo());
		comandos.add(new ComandoRetirar());

		return comandos;
	}

	// Muestra el listado de comandos, para mostrar un menú al usuario
	// muestra el índice en el arreglo de comandos y el nombre del comando
	private static void muestraMenuConComandos(List<Comando> comandos) {

		// muestra los nombres de los comandos
		for (int i = 0; i < comandos.size(); i++) {
			System.out.println(i + ".-" + comandos.get(i).getNombre().getNombre());
		}
	}

	// dado el texto ingresado por el usuario, retorna el comando correspondiente
	// retorna null si el texto no es un número o no existe ningún comando con ese
	// índice
	private static Comando retornaComandoSeleccionado(List<Comando> comandos, String valorIngresado) {

		Comando comandoSeleccionado = null;

		// el valorIngresado es un número ?
		if (valorIngresado.matches("[0-9]")) {
			int valorSeleccionado = Integer.valueOf(valorIngresado);
			// es un índice válido para la lista de comandos
			if (valorSeleccionado < comandos.size()) {
				comandoSeleccionado = comandos.get(valorSeleccionado);
			}
		}

		return comandoSeleccionado;
	}
}
