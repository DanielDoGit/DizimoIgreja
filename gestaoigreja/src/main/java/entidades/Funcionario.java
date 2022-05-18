package entidades;

import java.time.LocalDate;

public class Funcionario{
	
	private Integer idFuncionario;
	private String nomeFuncionario;
	private static String login, senha;
	private Cidade cidade;
	private String rgFuncionario;
	private String cpfFuncionario;
	private LocalDate datanascimentoFuncionario;
	private String celularFuncionario;
	private String telefoneFuncionario;
	private String enderecoFuncionario;
	private String bairroFuncionario;
	private Integer numeroenderecoFuncionario;
	private String observacoesFuncionario;
	private CategoriaFuncionario categoriaFuncionario;
	
	public String getBairroFuncionario() {
		return bairroFuncionario;
	}

	public void setBairroFuncionario(String bairroFuncionario) {
		this.bairroFuncionario = bairroFuncionario;
	}

	public CategoriaFuncionario getCategoriaFuncionario() {
		return categoriaFuncionario;
	}

	public void setCategoriaFuncionario(CategoriaFuncionario categoriaFuncionario) {
		this.categoriaFuncionario = categoriaFuncionario;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
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

	public String getRgFuncionario() {
		return rgFuncionario;
	}

	public void setRgFuncionario(String rgFuncionario) {
		this.rgFuncionario = rgFuncionario;
	}

	public String getCpfFuncionario() {
		return cpfFuncionario;
	}

	public void setCpfFuncionario(String cpfFuncionario) {
		this.cpfFuncionario = cpfFuncionario;
	}

	public LocalDate getDatanascimentoFuncionario() {
		return datanascimentoFuncionario;
	}

	public void setDatanascimentoFuncionario(LocalDate datanascimentoFuncionario) {
		this.datanascimentoFuncionario = datanascimentoFuncionario;
	}

	public String getCelularFuncionario() {
		return celularFuncionario;
	}

	public void setCelularFuncionario(String celularFuncionario) {
		this.celularFuncionario = celularFuncionario;
	}

	public String getTelefoneFuncionario() {
		return telefoneFuncionario;
	}

	public void setTelefoneFuncionario(String telefoneFuncionario) {
		this.telefoneFuncionario = telefoneFuncionario;
	}

	public String getEnderecoFuncionario() {
		return enderecoFuncionario;
	}

	public void setEnderecoFuncionario(String enderecoFuncionario) {
		this.enderecoFuncionario = enderecoFuncionario;
	}

	public Integer getNumeroenderecoFuncionario() {
		return numeroenderecoFuncionario;
	}

	public void setNumeroenderecoFuncionario(Integer numeroenderecoFuncionario) {
		this.numeroenderecoFuncionario = numeroenderecoFuncionario;
	}

	public String getObservacoesFuncionario() {
		return observacoesFuncionario;
	}

	public void setObservacoesFuncionario(String observacoesFuncionario) {
		this.observacoesFuncionario = observacoesFuncionario;
	}

	

}
