package beans;

public class CategoriaFuncionario {

	private Integer idCategoriaFuncionario;
	private String nomeCategoraiFuncionario;
	
	public Integer getIdCategoriaFuncionario() {
		return idCategoriaFuncionario;
	}
	public void setIdCategoriaFuncionario(Integer idCategoriaFuncionario) {
		this.idCategoriaFuncionario = idCategoriaFuncionario;
	}
	public String getNomeCategoraiFuncionario() {
		return nomeCategoraiFuncionario;
	}
	public void setNomeCategoraiFuncionario(String nomeCategoraiFuncionario) {
		this.nomeCategoraiFuncionario = nomeCategoraiFuncionario;
	}
	
	public CategoriaFuncionario(Integer idCategoriaFuncionario, String nomeCategoraiFuncionario) {
		super();
		this.idCategoriaFuncionario = idCategoriaFuncionario;
		this.nomeCategoraiFuncionario = nomeCategoraiFuncionario;
	}
	
	public CategoriaFuncionario() {}
}
