package beans;

import java.time.LocalDate;

public class Coletor extends Usuario{

	private Comunidade comunidade;
	
	public Comunidade getComunidade() {
		return comunidade;
	}

	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
	}
	
}
