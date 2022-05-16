package entidades;

import java.time.LocalDate;

public class Coletor implements Usuario{

	private Integer idColetor;
	private String nomeColetor;
	private static String login, senha;
	private Cidade cidade;
	private String rgColetor;
	private String cpfColetor;
	private LocalDate datanascimentoColetor;
	private String celularColetor;
	private String telefoneColetor;
	private String enderecoColetor;
	private Integer numeroenderecoColetor;
	private String observacoesColetor;
	private Comunidade comunidade;
	
	
	@Override
	public void AcessarSistema(Usuario a) {
		// TODO Auto-generated method stub
		this.login = a.login;
		this.senha = a.senha;
	}

	public Integer getIdColetor() {
		return idColetor;
	}

	public void setIdColetor(Integer idColetor) {
		this.idColetor = idColetor;
	}

	public String getNomeColetor() {
		return nomeColetor;
	}

	public void setNomeColetor(String nomeColetor) {
		this.nomeColetor = nomeColetor;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getRgColetor() {
		return rgColetor;
	}

	public void setRgColetor(String rgColetor) {
		this.rgColetor = rgColetor;
	}

	public String getCpfColetor() {
		return cpfColetor;
	}

	public void setCpfColetor(String cpfColetor) {
		this.cpfColetor = cpfColetor;
	}

	public LocalDate getDatanascimentoColetor() {
		return datanascimentoColetor;
	}

	public void setDatanascimentoColetor(LocalDate datanascimentoColetor) {
		this.datanascimentoColetor = datanascimentoColetor;
	}

	public String getCelularColetor() {
		return celularColetor;
	}

	public void setCelularColetor(String celularColetor) {
		this.celularColetor = celularColetor;
	}

	public String getTelefoneColetor() {
		return telefoneColetor;
	}

	public void setTelefoneColetor(String telefoneColetor) {
		this.telefoneColetor = telefoneColetor;
	}

	public String getEnderecoColetor() {
		return enderecoColetor;
	}

	public void setEnderecoColetor(String enderecoColetor) {
		this.enderecoColetor = enderecoColetor;
	}

	public Integer getNumeroenderecoColetor() {
		return numeroenderecoColetor;
	}

	public void setNumeroenderecoColetor(Integer numeroenderecoColetor) {
		this.numeroenderecoColetor = numeroenderecoColetor;
	}

	public String getObservacoesColetor() {
		return observacoesColetor;
	}

	public void setObservacoesColetor(String observacoesColetor) {
		this.observacoesColetor = observacoesColetor;
	}
	
	

}
