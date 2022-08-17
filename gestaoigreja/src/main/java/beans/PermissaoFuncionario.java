package beans;

import java.util.List;

public class PermissaoFuncionario {

	private Funcionario funcionario;
	private List<Permissoes> listaPermissoes;
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Permissoes> getListaPermissoes() {
		return listaPermissoes;
	}
	public void setListaPermissoes(List<Permissoes> listaPermissoes) {
		this.listaPermissoes = listaPermissoes;
	}
	public PermissaoFuncionario(Funcionario funcionario, List<Permissoes> listaPermissoes) {
		super();
		this.funcionario = funcionario;
		this.listaPermissoes = listaPermissoes;
	}
	public PermissaoFuncionario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
