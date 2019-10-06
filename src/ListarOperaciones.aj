
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import ejemplo.cajero.control.Comando;
import ejemplo.cajero.control.ComandoRetirar;
import ejemplo.cajero.control.ComandoConsignar;
import ejemplo.cajero.control.ComandoTransferir;
import ejemplo.cajero.modelo.Cuenta;
import ejemplo.cajero.modelo.TipoComando;
import ejemplo.cajero.modelo.Usuario;

public aspect ListarOperaciones {

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private long valorRetiradoL;
	private long valorConsignadoL;
	private Cuenta cuentaTransferencia;
	private long valorTransferenciaL;
	
	pointcut agregarLineaEjecucion(): call(void ejecutar(..));
	
	pointcut getValorRetirado(): execution(long ComandoRetirar.getValorRetirado());
	
	pointcut getValorConsignar(): execution(long ComandoConsignar.getValorConsignar());
	
	pointcut getCuentaDestino(): execution(Cuenta ComandoTransferir.getCuentaDestino());
	
	pointcut getValorTransferencia(): execution(long ComandoTransferir.getValorTransferencia());
	
	after() returning(long valorRetirado): getValorRetirado() {
		valorRetiradoL = valorRetirado;
	}
	
	after() returning(long valorConsignado): getValorConsignar() {
		valorConsignadoL = valorConsignado;
	}
	
	after() returning(Cuenta cuentaDestino): getCuentaDestino() {
		cuentaTransferencia = cuentaDestino;
	}
	
	after() returning(long valorTransferencia): getValorTransferencia() {
		valorTransferenciaL = valorTransferencia;
	}

	after(): agregarLineaEjecucion() {
		Object[] parametros = thisJoinPoint.getArgs();

		Comando comando = (Comando) thisJoinPoint.getTarget();
		Usuario usuario = (Usuario) parametros[1];
		
		String linea = usuario.getCedula() + ", " + comando.getNombre().getNombre() + ", " + obtenerLinea(comando.getNombre(),usuario)+ "," + formato.format(new Date());
		usuario.getOperaciones().add(linea);
	}
	
	private String obtenerLinea(TipoComando tipoComando, Usuario usuario) {
		switch (tipoComando) {
		case SALDO:
			return "SALDO: " + usuario.getCuenta().getSaldo();
		case RETIRAR:
			return "VALOR RETIRADO: " + valorRetiradoL;
		case CONSIGNACION: 
			return "VALOR CONSIGNADO: " + valorConsignadoL;
		case TRANSFERENCIA:
			return "CUENTA TRANSFERENCIA: " + cuentaTransferencia.getNumero()+ " VALOR TRANSFERENCIA: " + valorTransferenciaL;
		default:
			break;
		}
		return "";
	}
}