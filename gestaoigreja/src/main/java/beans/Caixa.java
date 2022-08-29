package beans;

import java.math.BigDecimal;
import java.time.LocalDate;

import comum.ConstantesDinheiro;
import comum.Ocorrencia;

public class Caixa {

	private String codigoReferencia;
	private String historico;
	private BigDecimal valor;
	private Ocorrencia o;
	private ConstantesDinheiro formato;
	private LocalDate dataregistro;

	public LocalDate getDataregistro() {
		return dataregistro;
	}

	public void setDataregistro(LocalDate dataregistro) {
		this.dataregistro = dataregistro;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Ocorrencia getO() {
		return o;
	}

	public void setO(Ocorrencia o) {
		this.o = o;
	}

	public ConstantesDinheiro getFormato() {
		return formato;
	}

	public void setFormato(ConstantesDinheiro formato) {
		this.formato = formato;
	}

	public String getCodigoReferencia() {
		return codigoReferencia;
	}

	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

}
