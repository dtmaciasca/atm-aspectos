import java.text.SimpleDateFormat;
import java.util.Date;

public aspect AuditoriaUsuario {

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	pointcut ingresoUsuario(): call(boolean validarUsuario(..));

	after() returning(boolean logueado): ingresoUsuario() {
		System.out.println("Guardo !!!!!!!!!!!!!");
		Object[] parametros = thisJoinPoint.getArgs();

		String linea = parametros[0] + ", " + formato.format(new Date()) + ", " + (logueado?"INGRESO EXITOSO":"INGRESO FALLIDO");
		AuditoriaOperaciones.escribirArchivo("auditoria/usuarios.txt", linea);
	}
}