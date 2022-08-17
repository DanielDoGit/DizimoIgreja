package beans;

import java.time.LocalDate;

public class Dizimista {
	
	private Integer idDizimista;
	private String nomeDizimista;
	private String cpfDizimista;
	private String rgDizimista;
	private String isativo;
	private LocalDate dataNascimentoDizimista, datacadastroDizimista;
	private String telefoneDizimista;
	private String celularDizimista;
	private Cidade cidade;
	private String observacoesDizimista;
	private String contatos;
	private String edereco, numerologradouro;
		
	
	public String getEdereco() {
		return edereco;
	}
	public void setEdereco(String edereco) {
		this.edereco = edereco;
	}
	public String getNumerologradouro() {
		return numerologradouro;
	}
	public void setNumerologradouro(String numerologradouro) {
		this.numerologradouro = numerologradouro;
	}
	public Dizimista() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dizimista(Integer idDizimista, String nomeDizimista, String cpfDizimista, String rgDizimista, String isativo,
			LocalDate dataNascimentoDizimista, LocalDate datacadastroDizimista, String telefoneDizimista,
			String celularDizimista, Cidade cidade, String observacoesDizimista, String contatos) {
		super();
		this.idDizimista = idDizimista;
		this.nomeDizimista = nomeDizimista;
		this.cpfDizimista = cpfDizimista;
		this.rgDizimista = rgDizimista;
		this.isativo = isativo;
		this.dataNascimentoDizimista = dataNascimentoDizimista;
		this.datacadastroDizimista = datacadastroDizimista;
		this.telefoneDizimista = telefoneDizimista;
		this.celularDizimista = celularDizimista;
		this.cidade = cidade;
		this.observacoesDizimista = observacoesDizimista;
		this.contatos = contatos;
	}

	public Integer getIdDizimista() {
		return idDizimista;
	}
	public void setIdDizimista(Integer idDizimista) {
		this.idDizimista = idDizimista;
	}
	public String getNomeDizimista() {
		return nomeDizimista;
	}
	public void setNomeDizimista(String nomeDizimista) {
		this.nomeDizimista = nomeDizimista;
	}
	public String getCpfDizimista() {
		return cpfDizimista;
	}
	public void setCpfDizimista(String cpfDizimista) {
		this.cpfDizimista = cpfDizimista;
	}
	public String getRgDizimista() {
		return rgDizimista;
	}
	public void setRgDizimista(String rgDizimista) {
		this.rgDizimista = rgDizimista;
	}
	public String getIsativo() {
		return isativo;
	}
	public void setIsativo(String isativo) {
		this.isativo = isativo;
	}
	public LocalDate getDataNascimentoDizimista() {
		return dataNascimentoDizimista;
	}
	public void setDataNascimentoDizimista(LocalDate dataNascimentoDizimista) {
		this.dataNascimentoDizimista = dataNascimentoDizimista;
	}
	public LocalDate getDatacadastroDizimista() {
		return datacadastroDizimista;
	}
	public void setDatacadastroDizimista(LocalDate datacadastroDizimista) {
		this.datacadastroDizimista = datacadastroDizimista;
	}
	public String getTelefoneDizimista() {
		return telefoneDizimista;
	}
	public void setTelefoneDizimista(String telefoneDizimista) {
		this.telefoneDizimista = telefoneDizimista;
	}
	public String getCelularDizimista() {
		return celularDizimista;
	}
	public void setCelularDizimista(String celularDizimista) {
		this.celularDizimista = celularDizimista;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public String getObservacoesDizimista() {
		return observacoesDizimista;
	}
	public void setObservacoesDizimista(String observacoesDizimista) {
		this.observacoesDizimista = observacoesDizimista;
	}
	public String getContatos() {
		return contatos;
	}
	public void setContatos(String contatos) {
		this.contatos = contatos;
	}

	
	

}
