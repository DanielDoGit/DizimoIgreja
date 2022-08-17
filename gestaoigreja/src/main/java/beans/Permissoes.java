package beans;

public class Permissoes {
	
	private Integer id;
	private String nomepermissao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomepermissao() {
		return nomepermissao;
	}
	public void setNomepermissao(String nomepermissao) {
		this.nomepermissao = nomepermissao;
	}
	public Permissoes(Integer id, String nomepermissao) {
		super();
		this.id = id;
		this.nomepermissao = nomepermissao;
	}
	public Permissoes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
