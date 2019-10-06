package ejemplo.cajero.control;

import java.util.Scanner;

import ejemplo.cajero.modelo.Banco;
import ejemplo.cajero.modelo.Cuenta;
import ejemplo.cajero.modelo.TipoComando;
import ejemplo.cajero.modelo.Usuario;

/**
 * Comando usado para retirar dinero
 */
public class ComandoRetirar implements Comando {

	private long valorRetirado; 
	
	@Override
	public TipoComando getNombre() {
		return TipoComando.RETIRAR;
	}

	@SuppressWarnings("resource")
	@Override
	public void ejecutar(Banco contexto, Usuario usuario) throws Exception {
		
		System.out.println("Retiro de Dinero");
		System.out.println();
		
		// la clase Console no funciona bien en Eclipse
		Scanner console = new Scanner(System.in);			
		
		// Ingresa los datos
		/*System.out.println("Ingrese el número de cuenta");
		String numeroDeCuenta = console.nextLine();*/
		
		Cuenta cuenta = usuario.getCuenta();
		
		System.out.println("Ingrese el valor a retirar");
		String valor = console.nextLine();
	
		try {
			long valorNumerico = Long.parseLong(valor);
			retirarDinero(valorNumerico, cuenta);
		
		} catch (NumberFormatException e) {
			throw new Exception("Valor a retirar no válido : " + valor);
		}
	}
	
	public void retirarDinero(long valorNumerico, Cuenta cuenta) throws Exception {
		setValorRetirado(valorNumerico);
		cuenta.retirar(getValorRetirado());
		System.out.println("Saldo actual: " + cuenta.getSaldo());
	}

	public long getValorRetirado() {
		return valorRetirado;
	}

	public void setValorRetirado(long valorRetirado) {
		this.valorRetirado = valorRetirado;
	}
}
