package entidades;

public class Cidade {
	
	private Integer idCidade;
	private String nomeCidade;
	private String ufCidade;
	
	public Integer getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	public String getUfCidade() {
		return ufCidade;
	}
	public void setUfCidade(String ufCidade) {
		this.ufCidade = ufCidade;
	}
	
	public Cidade(Integer idCidade, String nomeCidade, String ufCidade) {
		super();
		this.idCidade = idCidade;
		this.nomeCidade = nomeCidade;
		this.ufCidade = ufCidade;
	}
	
	
	

}
