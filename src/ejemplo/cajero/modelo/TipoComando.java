package ejemplo.cajero.modelo;

public enum TipoComando {

	SALDO("Saldo"),
	RETIRAR("Retirar dinero"),
	CONSIGNACION("Consignar dinero"),
	TRANSFERENCIA("Transferencia de Dinero"),
	AUDITORIA("Consultar auditoria"),
	LISTAR_OPERACIONES("Listar operaciones del día");
	
	String nombre;

	private TipoComando(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
