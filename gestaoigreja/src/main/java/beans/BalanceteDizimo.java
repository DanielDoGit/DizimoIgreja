package beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BalanceteDizimo {

	private Integer idbalancete;
	private List<DizimistavalorDia> listaDizimistavalorDia = new ArrayList<DizimistavalorDia>();
	private BigDecimal valortotalrecebido;
	private LocalDate dataEntregaBalancete;
	private String verificador;
	private Coletor coletor;
	
	public Coletor getColetor() {
		return coletor;
	}
	public void setColetor(Coletor coletor) {
		this.coletor = coletor;
	}
	public String getVerificador() {
		return verificador;
	}
	public void setVerificador(String verificador) {
		this.verificador = verificador;
	}
	public Integer getIdbalancete() {
		return idbalancete;
	}
	public void setIdbalancete(Integer idbalancete) {
		this.idbalancete = idbalancete;
	}
	public List<DizimistavalorDia> getListaDizimistavalorDia() {
		return listaDizimistavalorDia;
	}
	public void setListaDizimistavalorDia(List<DizimistavalorDia> listaDizimistavalorDia) {
		this.listaDizimistavalorDia = listaDizimistavalorDia;
	}
	public BigDecimal getValortotalrecebido() {
		return valortotalrecebido;
	}
	public void setValortotalrecebido(BigDecimal valortotalrecebido) {
		this.valortotalrecebido = valortotalrecebido;
	}
	public LocalDate getDataEntregaBalancete() {
		return dataEntregaBalancete;
	}
	public void setDataEntregaBalancete(LocalDate dataEntregaBalancete) {
		this.dataEntregaBalancete = dataEntregaBalancete;
	}
	
	public void somandoValores(BigDecimal valor) {
		 valortotalrecebido = valortotalrecebido.add(valor);
	}
	
	public void subtraindoValores(BigDecimal valor) {
		 valortotalrecebido = valortotalrecebido.subtract(valor);
	}
	
	
	
	
	
}
