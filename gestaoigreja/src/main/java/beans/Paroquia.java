package beans;

public class Paroquia {
	
	private Integer indice, numeroLogradouro;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String celular, telefone, contatos;
	private Cidade cidade;
	private String endereco;
	private String bairro;
	private String cep;
	
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public Integer getNumeroLogradouro() {
		return numeroLogradouro;
	}
	public void setNumeroLogradouro(Integer numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
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
	public String getContatos() {
		return contatos;
	}
	public void setContatos(String contatos) {
		this.contatos = contatos;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Paroquia() {
		super();
	}
	
	public Paroquia(Integer indice, Integer numeroLogradouro, String nomeFantasia, String razaoSocial, String cnpj,
			String celular, String telefone, String contatos, Cidade cidade, String endereco, String bairro,
			String cep) {
		super();
		this.indice = indice;
		this.numeroLogradouro = numeroLogradouro;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.celular = celular;
		this.telefone = telefone;
		this.contatos = contatos;
		this.cidade = cidade;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cep = cep;
	}
	
	
	

}
