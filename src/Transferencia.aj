import java.util.List;

import ejemplo.cajero.control.Comando;
import ejemplo.cajero.control.ComandoTransferir;

public aspect Transferencia {
	
pointcut agregarComandoTransferir(): call(List<Comando> cargaComandos());
	
	after() returning(List<Comando> resultado): agregarComandoTransferir() {
		resultado.add(new ComandoTransferir());
	}
}