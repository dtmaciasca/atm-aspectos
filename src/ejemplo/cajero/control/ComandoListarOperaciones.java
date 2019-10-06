package ejemplo.cajero.control;

import ejemplo.cajero.modelo.Banco;
import ejemplo.cajero.modelo.TipoComando;
import ejemplo.cajero.modelo.Usuario;

/**
 * Comando usado para listar las cuentas 
 */
public class ComandoListarOperaciones implements Comando {

	@Override
	public TipoComando getNombre() {
		return TipoComando.LISTAR_OPERACIONES;
	}

	@Override
	public void ejecutar(Banco contexto, Usuario usuario) throws Exception {
		
		System.out.println("Listado de operaciones");
		System.out.println();
	
		for (String operacion : usuario.getOperaciones()) {
			System.out.println(operacion);
		}

	}

}
