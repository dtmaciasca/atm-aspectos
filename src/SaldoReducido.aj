import ejemplo.cajero.modelo.Cuenta;

public aspect SaldoReducido {

	pointcut retirarDinero(): call(void retirarDinero(..));

	before() throws Exception: retirarDinero(){
		Object[] parametros = thisJoinPoint.getArgs();
		Cuenta cuenta = (Cuenta) parametros[1];
		long valor = (long) parametros[0];
		if (valor  > cuenta.getSaldo() - 200000) {
			throw new Exception("No puedes dejar menos de $200.000 en tu cuenta.");
		}
	}

	void around() throws Exception:retirarDinero(){
		proceed();
	}
}