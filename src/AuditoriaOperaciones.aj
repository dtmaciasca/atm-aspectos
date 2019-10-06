
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import ejemplo.cajero.modelo.Usuario;

public aspect AuditoriaOperaciones {

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	pointcut agregarLineaEjecucion(): call(void ejecutar(..));

	after(): agregarLineaEjecucion() {
		Object[] parametros = thisJoinPoint.getArgs();

		Usuario usuario = (Usuario) parametros[1];
		int ultimoValor = usuario.getOperaciones().size() -1;
		escribirArchivo("auditoria/operaciones.txt", usuario.getOperaciones().get(ultimoValor));
	}

	public static void escribirArchivo(String filename, String linea) {
		FileWriter archivo = null;
		PrintWriter pw = null;
		try {
			archivo = new FileWriter(filename, true);
			pw = new PrintWriter(archivo);
			pw.println(linea);
		} catch (IOException e) {
			System.out.println("Error escribiendo en el archivo: " + filename);
		} finally {
			try {
				if (null != archivo)
					archivo.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}