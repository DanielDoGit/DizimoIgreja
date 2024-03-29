package beans;

import java.time.LocalDate;

public abstract class Usuario {

	protected Integer idUsuario;
	protected String nomeUsuario;
	protected String loginUsuario, senhaUsuario;
	protected Cidade cidadeUsuario;
	protected String rgUsuario;
	protected String cpfUsuario;
	protected LocalDate datanascimentoUsuario;
	protected String celularUsuario;
	protected String telefoneUsuario;
	protected String enderecoUsuario;
	protected String numeroenderecoUsuario;
	protected String observacoesUsuario;
	protected LocalDate dataCadastro;
	
	public Usuario(Integer idUsuario, String nomeUsuario, Cidade cidadeUsuario, String rgUsuario, String cpfUsuario,
			LocalDate datanascimentoUsuario, String celularUsuario, String telefoneUsuario, String enderecoUsuario,
			String numeroenderecoUsuario, String observacoesUsuario, LocalDate dataCadastro) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.cidadeUsuario = cidadeUsuario;
		this.rgUsuario = rgUsuario;
		this.cpfUsuario = cpfUsuario;
		this.datanascimentoUsuario = datanascimentoUsuario;
		this.celularUsuario = celularUsuario;
		this.telefoneUsuario = telefoneUsuario;
		this.enderecoUsuario = enderecoUsuario;
		this.numeroenderecoUsuario = numeroenderecoUsuario;
		this.observacoesUsuario = observacoesUsuario;
		this.dataCadastro = dataCadastro;
	}
	
	public Usuario() {}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public Cidade getCidadeUsuario() {
		return cidadeUsuario;
	}

	public void setCidadeUsuario(Cidade cidadeUsuario) {
		this.cidadeUsuario = cidadeUsuario;
	}

	public String getRgUsuario() {
		return rgUsuario;
	}

	public void setRgUsuario(String rgUsuario) {
		this.rgUsuario = rgUsuario;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public LocalDate getDatanascimentoUsuario() {
		return datanascimentoUsuario;
	}

	public void setDatanascimentoUsuario(LocalDate datanascimentoUsuario) {
		this.datanascimentoUsuario = datanascimentoUsuario;
	}

	public String getCelularUsuario() {
		return celularUsuario;
	}

	public void setCelularUsuario(String celularUsuario) {
		this.celularUsuario = celularUsuario;
	}

	public String getTelefoneUsuario() {
		return telefoneUsuario;
	}

	public void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}

	public String getEnderecoUsuario() {
		return enderecoUsuario;
	}

	public void setEnderecoUsuario(String enderecoUsuario) {
		this.enderecoUsuario = enderecoUsuario;
	}

	public String getNumeroenderecoUsuario() {
		return numeroenderecoUsuario;
	}

	public void setNumeroenderecoUsuario(String numeroenderecoUsuario) {
		this.numeroenderecoUsuario = numeroenderecoUsuario;
	}

	public String getObservacoesUsuario() {
		return observacoesUsuario;
	}

	public void setObservacoesUsuario(String observacoesUsuario) {
		this.observacoesUsuario = observacoesUsuario;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
}
