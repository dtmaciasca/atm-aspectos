
import java.util.List;

import ejemplo.cajero.control.Comando;
import ejemplo.cajero.control.ComandoConsignar;

public aspect Consignar {
	
	pointcut agregarComandoConsignar(): call(List<Comando> cargaComandos());
	
	after() returning(List<Comando> resultado): agregarComandoConsignar() {
		resultado.add(new ComandoConsignar());
	}
}