package beans;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DizimistavalorDia {
	
	private Integer id;

	private Dizimista dizimista;
	private BigDecimal valorcontribuido;
	private LocalDate datacontribuida;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Dizimista getDizimista() {
		return dizimista;
	}
	public void setDizimista(Dizimista dizimista) {
		this.dizimista = dizimista;
	}
	public BigDecimal getValorcontribuido() {
		return valorcontribuido;
	}
	public void setValorcontribuido(BigDecimal valorcontribuido) {
		this.valorcontribuido = valorcontribuido;
	}
	public LocalDate getDatacontribuida() {
		return datacontribuida;
	}
	public void setDatacontribuida(LocalDate datacontribuida) {
		this.datacontribuida = datacontribuida;
	}
	

}
