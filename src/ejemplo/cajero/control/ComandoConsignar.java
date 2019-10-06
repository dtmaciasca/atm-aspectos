package ejemplo.cajero.control;

import java.util.Scanner;

import ejemplo.cajero.modelo.Banco;
import ejemplo.cajero.modelo.Cuenta;
import ejemplo.cajero.modelo.TipoComando;
import ejemplo.cajero.modelo.Usuario;

/**
 * Comando usado para consignar dinero
 */
public class ComandoConsignar implements Comando {

	private long valorConsignar;
	
	@Override
	public TipoComando getNombre() {
		return TipoComando.CONSIGNACION;
	}

	@SuppressWarnings("resource")
	@Override
	public void ejecutar(Banco contexto, Usuario usuario) throws Exception {
		
		System.out.println("Consignación de Dinero");
		System.out.println();
		
		// la clase Console no funciona bien en Eclipse
		Scanner console = new Scanner(System.in);			
		
		Cuenta cuenta = usuario.getCuenta();
		
		System.out.println("Ingrese el valor a consignar");
		String valor = console.nextLine();
	
		try {
			long valorNumerico = Long.parseLong(valor);
			valorConsignar = valorNumerico;
			cuenta.consignar(getValorConsignar());
		
		} catch (NumberFormatException e) {
			throw new Exception("Valor a consignar no válido : " + valor);
		}
	}

	public long getValorConsignar() {
		return valorConsignar;
	}
}
