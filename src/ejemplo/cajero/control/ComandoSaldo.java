package ejemplo.cajero.control;

import ejemplo.cajero.modelo.Banco;
import ejemplo.cajero.modelo.TipoComando;
import ejemplo.cajero.modelo.Usuario;

public class ComandoSaldo implements Comando{

	@Override
	public TipoComando getNombre() {
		return TipoComando.SALDO;
	}

	@Override
	public void ejecutar(Banco contexto, Usuario usuario) throws Exception {
		System.out.println("Saldo");
		System.out.println();
		
		System.out.println(usuario.getCuenta().getSaldo());
	}

}
