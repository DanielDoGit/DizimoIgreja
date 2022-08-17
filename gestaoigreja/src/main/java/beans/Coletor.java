package beans;

public class Coletor extends Usuario{

	private Comunidade comunidade;
	private String isativo;
	
	public String getIsativo() {
		return isativo;
	}

	public void setIsativo(String isativo) {
		this.isativo = isativo;
	}

	public Comunidade getComunidade() {
		return comunidade;
	}

	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
	}
	
}
