package beans;

import java.time.LocalDate;

public class Funcionario extends Usuario{
	
	private CategoriaFuncionario categoriaFuncionario;
	private String isativo;

	public CategoriaFuncionario getCategoriaFuncionario() {
		return categoriaFuncionario;
	}

	public void setCategoriaFuncionario(CategoriaFuncionario categoriaFuncionario) {
		this.categoriaFuncionario = categoriaFuncionario;
	}
	
	public String getIsativo() {
		return isativo;
	}

	public void setIsativo(String isativo) {
		this.isativo = isativo;
	}

	public Funcionario() {}

	public Funcionario(Integer idUsuario, String nomeUsuario, Cidade cidadeUsuario, String rgUsuario, String cpfUsuario,
			LocalDate datanascimentoUsuario, String celularUsuario, String telefoneUsuario, String enderecoUsuario,
			String numeroenderecoUsuario, String observacoesUsuario, LocalDate dataCadastro,
			CategoriaFuncionario categoriaFuncionario, String isativo) {
		super(idUsuario, nomeUsuario, cidadeUsuario, rgUsuario, cpfUsuario, datanascimentoUsuario, celularUsuario,
				telefoneUsuario, enderecoUsuario, numeroenderecoUsuario, observacoesUsuario, dataCadastro);
		this.categoriaFuncionario = categoriaFuncionario;
		this.isativo = isativo;
	}
	
	

	

}
