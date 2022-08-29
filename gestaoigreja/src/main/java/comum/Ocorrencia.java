package comum;

public enum Ocorrencia {
	
	DEBITO(1), CREDITO(2);
	
	Integer valor ;
	Ocorrencia(Integer valor) {
		this.valor = valor;
	}

}
