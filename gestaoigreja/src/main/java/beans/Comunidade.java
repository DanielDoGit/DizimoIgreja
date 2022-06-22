package beans;

public class Comunidade {
	
	private Integer idComunidade;
	private String nomefantaziaComunidade;
	private String nomerazaosocialComunidade;
	private String cnpjComunidade;
	private String enderecoComunidade;
	private String numeroenderecoComunidade;
	private String bairroComunidade;
	private Cidade cidade;
	private String observacoes;
	
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
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
	public String getCnpjComunidade() {
		return cnpjComunidade;
	}
	public void setCnpjComunidade(String cnpjComunidade) {
		this.cnpjComunidade = cnpjComunidade;
	}
	public String getEnderecoComunidade() {
		return enderecoComunidade;
	}
	public void setEnderecoComunidade(String enderecoComunidade) {
		this.enderecoComunidade = enderecoComunidade;
	}
	public String getNumeroenderecoComunidade() {
		return numeroenderecoComunidade;
	}
	public void setNumeroenderecoComunidade(String numeroenderecoComunidade) {
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
			String cnpjComunidade, String enderecoComunidade, String numeroenderecoComunidade,
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
	
	public Comunidade() {}
	
	
	

}
