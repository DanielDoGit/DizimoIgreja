package entidades;

public class Comunidade {
	
	private Integer idComunidade;
	private String nomefantaziaComunidade;
	private String nomerazaosocialComunidade;
	private Integer cnpjComunidade;
	private String enderecoComunidade;
	private Integer numeroenderecoComunidade;
	private String bairroComunidade;
	private Cidade cidade;
	
	public Integer getIdComunidade() {
		return idComunidade;
	}
	public void setIdComunidade(Integer idComunidade) {
		this.idComunidade = idComunidade;
	}
	public String getNomefantaziaComunidade() {
		return nomefantaziaComunidade;
	}
	public void setNomefantaziaComunidade(String nomefantaziaComunidade) {
		this.nomefantaziaComunidade = nomefantaziaComunidade;
	}
	public String getNomerazaosocialComunidade() {
		return nomerazaosocialComunidade;
	}
	public void setNomerazaosocialComunidade(String nomerazaosocialComunidade) {
		this.nomerazaosocialComunidade = nomerazaosocialComunidade;
	}
	public Integer getCnpjComunidade() {
		return cnpjComunidade;
	}
	public void setCnpjComunidade(Integer cnpjComunidade) {
		this.cnpjComunidade = cnpjComunidade;
	}
	public String getEnderecoComunidade() {
		return enderecoComunidade;
	}
	public void setEnderecoComunidade(String enderecoComunidade) {
		this.enderecoComunidade = enderecoComunidade;
	}
	public Integer getNumeroenderecoComunidade() {
		return numeroenderecoComunidade;
	}
	public void setNumeroenderecoComunidade(Integer numeroenderecoComunidade) {
		this.numeroenderecoComunidade = numeroenderecoComunidade;
	}
	public String getBairroComunidade() {
		return bairroComunidade;
	}
	public void setBairroComunidade(String bairroComunidade) {
		this.bairroComunidade = bairroComunidade;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public Comunidade(Integer idComunidade, String nomefantaziaComunidade, String nomerazaosocialComunidade,
			Integer cnpjComunidade, String enderecoComunidade, Integer numeroenderecoComunidade,
			String bairroComunidade, Cidade cidade) {
		super();
		this.idComunidade = idComunidade;
		this.nomefantaziaComunidade = nomefantaziaComunidade;
		this.nomerazaosocialComunidade = nomerazaosocialComunidade;
		this.cnpjComunidade = cnpjComunidade;
		this.enderecoComunidade = enderecoComunidade;
		this.numeroenderecoComunidade = numeroenderecoComunidade;
		this.bairroComunidade = bairroComunidade;
		this.cidade = cidade;
	}
	
	
	

}
