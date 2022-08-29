package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import beans.BalanceteDizimo;
import beans.Caixa;
import comum.ConstantesDinheiro;
import comum.NomeClasse;
import comum.Ocorrencia;

public class CaixaDao {

	private Connection c;
	private BigDecimal credito = new BigDecimal("0");
	private BigDecimal debito = new BigDecimal("0");
	private BigDecimal total;

	public Connection getConnection() {
		return this.c;
	}

	public void setConnection(Connection c) {
		this.c = c;
	}

	public List<Caixa> compilarCaixa(LocalDate de, LocalDate ate) throws SQLException {
		List<Caixa> listaCaixa = new ArrayList<>();

		List<BalanceteDizimo> listaBalancete = pesquisarBalanceteDizimo(de, ate);

		for (int i = 0; i < listaBalancete.size(); i++) {
			Caixa c = new Caixa();
			c.setCodigoReferencia(Integer.toString(listaBalancete.get(i).getIdbalancete()));
			c.setHistorico(NomeClasse.BALANCEDIZIMO.getB());
			c.setO(Ocorrencia.CREDITO);
			c.setValor(listaBalancete.get(i).getValortotalrecebido());
			credito = credito.add(listaBalancete.get(i).getValortotalrecebido());
			c.setFormato(ConstantesDinheiro.DINHEIRO);
			c.setDataregistro(listaBalancete.get(i).getDataEntregaBalancete());

			listaCaixa.add(c);
		}

		total = credito.subtract(debito);

		return listaCaixa;
	}

	public BigDecimal getCredito() {
		return credito;
	}

	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}

	public BigDecimal getDebito() {
		return debito;
	}

	public void setDebito(BigDecimal debito) {
		this.debito = debito;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	private List<BalanceteDizimo> pesquisarBalanceteDizimo(LocalDate de, LocalDate ate) throws SQLException {
		List<BalanceteDizimo> lista = new ArrayList<>();
		StringBuilder st = new StringBuilder();
		st.append("select recdizimovalorrecebimento, recdizimoid, recdizimodatarecebimento from recebimentodizimo "
				+ "where recdizimodatarecebimento between ? and ? and verificador = 's' ;");
		PreparedStatement ps = c.prepareStatement(st.toString());
		ps.setDate(1, java.sql.Date.valueOf(de));
		ps.setDate(2, java.sql.Date.valueOf(ate));
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			BalanceteDizimo b = new BalanceteDizimo();
			b.setIdbalancete(rs.getInt("recdizimoid"));
			b.setValortotalrecebido(rs.getBigDecimal("recdizimovalorrecebimento"));
			b.setDataEntregaBalancete(rs.getDate("recdizimodatarecebimento").toLocalDate());
			lista.add(b);
		}
		return lista;
	}

}
