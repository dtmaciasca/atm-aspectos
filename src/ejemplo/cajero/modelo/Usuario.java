package ejemplo.cajero.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
		
	private String cedula;
	private String clave;
	private Cuenta cuenta;
	private List<String> operaciones = new ArrayList<>();
	
	public Usuario(String cedula, String clave, Cuenta cuenta) {
		this.cedula =cedula;
		this.clave =clave;
		this.cuenta =cuenta;
	}

	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}	
	
	public List<String> getOperaciones() {
		return operaciones;
	}
}
