package comum;

public enum ConstantesDinheiro {

	DINHEIRO(1), BOLETOBANCARIO(2), CARTAOCREDITO(3), CARTAODEBITO(4);
	
	Integer i ;
	
	private ConstantesDinheiro(Integer i) {
		this.i = i;
	}
}
