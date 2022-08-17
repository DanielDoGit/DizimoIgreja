package beans;

import java.util.List;

public class PermissaoColetor {
	
	private Coletor coletor;
	private List<Permissoes> listaPermissoes;
	
	public Coletor getColetor() {
		return coletor;
	}
	public void setColetor(Coletor coletor) {
		this.coletor = coletor;
	}
	public List<Permissoes> getListaPermissoes() {
		return listaPermissoes;
	}
	public void setListaPermissoes(List<Permissoes> listaPermissoes) {
		this.listaPermissoes = listaPermissoes;
	}
	public PermissaoColetor(Coletor coletor, List<Permissoes> listaPermissoes) {
		super();
		this.coletor = coletor;
		this.listaPermissoes = listaPermissoes;
	}
	public PermissaoColetor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
