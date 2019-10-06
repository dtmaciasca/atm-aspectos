package ejemplo.cajero.control;

import ejemplo.cajero.modelo.Banco;
import ejemplo.cajero.modelo.TipoComando;
import ejemplo.cajero.modelo.Usuario;

public class ComandoAuditoria implements Comando {

	@Override
	public TipoComando getNombre() {
		return TipoComando.AUDITORIA;
	}

	@Override
	public void ejecutar(Banco contexto, Usuario usuario) throws Exception {
		
	}

}
