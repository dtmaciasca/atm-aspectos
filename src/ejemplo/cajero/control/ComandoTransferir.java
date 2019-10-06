package ejemplo.cajero.control;

import java.util.Scanner;

import ejemplo.cajero.modelo.Banco;
import ejemplo.cajero.modelo.Cuenta;
import ejemplo.cajero.modelo.TipoComando;
import ejemplo.cajero.modelo.Usuario;

/**
 * Comando usado para transferir dinero entre cuentas
 */
public class ComandoTransferir implements Comando {

	private Cuenta cuentaDestino; 
	private long valorTransferencia;
	
	@Override
	public TipoComando getNombre() {
		return TipoComando.TRANSFERENCIA;
	}

	@SuppressWarnings("resource")
	@Override
	public void ejecutar(Banco contexto, Usuario usuario) throws Exception {
		
		System.out.println("Transferencia de Dinero");
		System.out.println();
		
		// la clase Console no funciona bien en Eclipse
		Scanner console = new Scanner(System.in);			
		
		// Ingresa los datos
		Cuenta cuentaOrigen = usuario.getCuenta();
		
		System.out.println("Ingrese el número de cuenta destino");
		String numeroCuentaDestino = console.nextLine();
		
		cuentaDestino = contexto.buscarCuenta(numeroCuentaDestino);
		if (getCuentaDestino() == null) {
			throw new Exception("No existe cuenta con el número " + numeroCuentaDestino);
		}
		
		System.out.println("Ingrese el valor a transferir");
		String valor = console.nextLine();
	
		try {
			
			// se retira primero y luego se consigna
			// si no se puede retirar, no se hace la consignación
			
			long valorNumerico = Long.parseLong(valor);
			valorTransferencia = valorNumerico;
			cuentaOrigen.retirar(getValorTransferencia());
			cuentaDestino.consignar(getValorTransferencia());
		
		} catch (NumberFormatException e) {
			throw new Exception("Valor a transferir no válido : " + valor);
		}
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public long getValorTransferencia() {
		return valorTransferencia;
	}
}
