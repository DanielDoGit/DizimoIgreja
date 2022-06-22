package beans;

public class Fornecedor {
	
	private Integer indice;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String inscricaoEstadual;
	private Cidade cidade;
	private String celular, telefone;
	private String endereco, numeroendereco;
	private String observacoes;
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumeroendereco() {
		return numeroendereco;
	}
	public void setNumeroendereco(String numeroendereco) {
		this.numeroendereco = numeroendereco;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public Fornecedor(Integer indice, String nomeFantasia, String razaoSocial, String cnpj, String inscricaoEstadual,
			Cidade cidade, String celular, String telefone, String endereco, String numeroendereco,
			String observacoes) {
		super();
		this.indice = indice;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.cidade = cidade;
		this.celular = celular;
		this.telefone = telefone;
		this.endereco = endereco;
		this.numeroendereco = numeroendereco;
		this.observacoes = observacoes;
	}
	public Fornecedor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
