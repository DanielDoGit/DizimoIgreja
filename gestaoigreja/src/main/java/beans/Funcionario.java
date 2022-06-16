package beans;

import java.time.LocalDate;

public class Funcionario extends Usuario{
	
	private CategoriaFuncionario categoriaFuncionario;

	public CategoriaFuncionario getCategoriaFuncionario() {
		return categoriaFuncionario;
	}

	public void setCategoriaFuncionario(CategoriaFuncionario categoriaFuncionario) {
		this.categoriaFuncionario = categoriaFuncionario;
	}

	public Funcionario(CategoriaFuncionario categoriaFuncionario) {
		super();
		this.categoriaFuncionario = categoriaFuncionario;
	}
	
	public Funcionario() {}

	

}
