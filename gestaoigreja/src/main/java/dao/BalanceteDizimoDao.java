package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import beans.BalanceteDizimo;
import beans.Coletor;
import beans.Comunidade;
import beans.Dizimista;
import beans.DizimistavalorDia;
import movimentacoes.Balancete;

public class BalanceteDizimoDao {

	private Connection con;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Integer recuperarIndiceMaxRecebimento() throws Exception {
		Integer i = null;
		StringBuilder st = new StringBuilder();
		st.append("select max(recdizimoid) from recebimentodizimo ;");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ResultSet rs = ps.executeQuery();
		rs.next();
		i = rs.getInt("max") + 1;
		return i;
	}

	public Integer recuperarIndiceMaxDizimistaContribuinte() throws Exception {
		Integer i = null;
		StringBuilder st = new StringBuilder();
		st.append("select max(id) from dizimistascontribuintes ;");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ResultSet rs = ps.executeQuery();
		rs.next();
		i = rs.getInt("max") + 1;
		return i;
	}

	public void cadastrarBalancete(BalanceteDizimo balanc) throws Exception {
		PreparedStatement ps = null;

		StringBuilder st0 = new StringBuilder();
		st0.append("insert into recebimentodizimo values (?,?,?,?,?)");
		ps = con.prepareStatement(st0.toString());
		ps.setInt(1, balanc.getIdbalancete());
		ps.setBigDecimal(2, balanc.getValortotalrecebido());
		ps.setDate(3, Date.valueOf(balanc.getDataEntregaBalancete()));
		ps.setString(4, balanc.getVerificador());
		ps.setInt(5, balanc.getColetor().getIdUsuario());
		ps.executeUpdate();
		ps.close();

		StringBuilder st1 = new StringBuilder();
		st1.append("insert into dizimistascontribuintes values (?,?,?,?,?);");
		PreparedStatement ps1 = con.prepareStatement(st1.toString());
		for (int i = 0; i < balanc.getListaDizimistavalorDia().size(); i++) {
			ps1.setInt(1, this.recuperarIndiceMaxDizimistaContribuinte());
			ps1.setInt(2, balanc.getListaDizimistavalorDia().get(i).getDizimista().getIdDizimista());
			ps1.setDate(3, Date.valueOf(balanc.getListaDizimistavalorDia().get(i).getDatacontribuida()));
			ps1.setBigDecimal(4, balanc.getListaDizimistavalorDia().get(i).getValorcontribuido());
			ps1.setInt(5, balanc.getIdbalancete());
			ps1.executeUpdate();
			ps1.clearParameters();
		}
		ps1.close();

	}

	public List<BalanceteDizimo> pesquisarListaNomeColetor(String s) throws SQLException {
		ArrayList<BalanceteDizimo> lista = new ArrayList<>();
		StringBuilder st = new StringBuilder();
		st.append(
				"select coletor.colnome, recebimentodizimo.recdizimoid, recebimentodizimo.verificador, recdizimodatarecebimento from recebimentodizimo \n"
						+ "inner join coletor on (colid = recebimentodizimo.coletor) where colnome ilike ?; ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setString(1, "%" + s + "%");
		ResultSet rs = ps.executeQuery();

		Coletor col = null;
		BalanceteDizimo bal = null;
		while (rs.next()) {
			bal = new BalanceteDizimo();
			col = new Coletor();
			col.setNomeUsuario(rs.getString("colnome"));
			bal.setIdbalancete(rs.getInt("recdizimoid"));
			bal.setColetor(col);
			bal.setVerificador(rs.getString("verificador"));
			bal.setDataEntregaBalancete(rs.getDate("recdizimodatarecebimento").toLocalDate());
			lista.add(bal);
		}
		return lista;
	}

	public List<BalanceteDizimo> pesquisarListaNomeDizimista(String di) throws SQLException {
		ArrayList<BalanceteDizimo> lista = new ArrayList<>();
		StringBuilder st = new StringBuilder();
		st.append(
				"select coletor.colnome, recebimentodizimo.recdizimoid, recebimentodizimo.verificador, recdizimodatarecebimento from recebimentodizimo, dizimistascontribuintes, dizimista, coletor\n"
						+ " where dizimista.diznome ilike ? and dizimista.dizid = dizimistascontribuintes.dizimista and colid = recebimentodizimo.coletor and recebimentodizimo.recdizimoid = dizimistascontribuintes.recebimentodizimo;");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setString(1, "%" + di + "%");
		ResultSet rs = ps.executeQuery();
		BalanceteDizimo bal = null;
		Coletor col = null;
		while (rs.next()) {
			col = new Coletor();
			bal = new BalanceteDizimo();
			col.setNomeUsuario(rs.getString("colnome"));
			bal.setColetor(col);
			bal.setIdbalancete(rs.getInt("recdizimoid"));
			bal.setVerificador(rs.getString("verificador"));
			bal.setDataEntregaBalancete(rs.getDate("recdizimodatarecebimento").toLocalDate());
			lista.add(bal);
		}
		return lista;
	}

	public BalanceteDizimo pesquisarCodigo(Integer i) throws SQLException {

		StringBuilder st = new StringBuilder();
		st.append(
				"select coletor.colnome, recebimentodizimo.recdizimoid, recebimentodizimo.verificador, recdizimodatarecebimento from recebimentodizimo,\n"
						+ " coletor where recebimentodizimo.recdizimoid = ? and colid = recebimentodizimo.coletor; ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setInt(1, i);
		ResultSet rs = ps.executeQuery();
		BalanceteDizimo bal = null;
		Coletor col = null;
		while (rs.next()) {
			col = new Coletor();
			bal = new BalanceteDizimo();
			col.setNomeUsuario(rs.getString("colnome"));
			bal.setColetor(col);
			bal.setIdbalancete(rs.getInt("recdizimoid"));
			bal.setVerificador(rs.getString("verificador"));
			bal.setDataEntregaBalancete(rs.getDate("recdizimodatarecebimento").toLocalDate());
		}
		return bal;
	}

	public List<BalanceteDizimo> pesquisarListaPeriodo(LocalDate de, LocalDate ate) throws SQLException {
		ArrayList<BalanceteDizimo> lista = new ArrayList<>();
		StringBuilder st = new StringBuilder();
		st.append(
				"select coletor.colnome, recebimentodizimo.recdizimoid, recebimentodizimo.verificador, recdizimodatarecebimento from recebimentodizimo,\n"
						+ " coletor where recebimentodizimo.recdizimodatarecebimento between ? and ? and(colid = recebimentodizimo.coletor);");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setDate(1, Date.valueOf(de));
		ps.setDate(2, Date.valueOf(ate));
		ResultSet rs = ps.executeQuery();
		BalanceteDizimo bal = null;
		Coletor col = null;
		while (rs.next()) {
			col = new Coletor();
			bal = new BalanceteDizimo();
			col.setNomeUsuario(rs.getString("colnome"));
			bal.setColetor(col);
			bal.setIdbalancete(rs.getInt("recdizimoid"));
			bal.setVerificador(rs.getString("verificador"));
			bal.setDataEntregaBalancete(rs.getDate("recdizimodatarecebimento").toLocalDate());
			lista.add(bal);
		}
		return lista;
	}

	public List<BalanceteDizimo> pesquisarListaPeriodoeNome(LocalDate de, LocalDate ate, String s) throws SQLException {
		ArrayList<BalanceteDizimo> lista = new ArrayList<>();
		StringBuilder st = new StringBuilder();
		st.append(
				"select coletor.colnome, recebimentodizimo.recdizimoid, recebimentodizimo.verificador, recdizimodatarecebimento from recebimentodizimo,\n"
						+ " coletor where recebimentodizimo.recdizimodatarecebimento between ? and ? and colid = recebimentodizimo.coletor and coletor.colnome ilike ? ;");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setDate(1, Date.valueOf(de));
		ps.setDate(2, Date.valueOf(ate));
		ps.setString(3, "%" + s + "%");
		ResultSet rs = ps.executeQuery();
		BalanceteDizimo bal = null;
		Coletor col = null;
		while (rs.next()) {
			col = new Coletor();
			bal = new BalanceteDizimo();
			col.setNomeUsuario(rs.getString("colnome"));
			bal.setIdbalancete(rs.getInt("recdizimoid"));
			bal.setColetor(col);
			bal.setVerificador(rs.getString("verificador"));
			bal.setDataEntregaBalancete(rs.getDate("recdizimodatarecebimento").toLocalDate());
			lista.add(bal);
		}
		return lista;
	}

	public BalanceteDizimo pesquisarListaPopularTela(Integer i) throws SQLException {
		BalanceteDizimo dizimo = new BalanceteDizimo();
		StringBuilder st = new StringBuilder();
		st.append(
				"select dizimista.diznome,valor, data, dizimista, id from dizimistascontribuintes inner join dizimista on "
						+ "(dizimistascontribuintes.dizimista = dizimista.dizid) where recebimentodizimo = ?");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setInt(1, i);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Dizimista dizi = new Dizimista();
			dizi.setNomeDizimista(rs.getString("diznome"));
			dizi.setIdDizimista(rs.getInt("dizimista"));
			DizimistavalorDia dizvalDia = new DizimistavalorDia();
			dizvalDia.setId(rs.getInt("id"));
			dizvalDia.setDizimista(dizi);
			dizvalDia.setDatacontribuida(rs.getDate("data").toLocalDate());
			dizvalDia.setValorcontribuido(rs.getBigDecimal("valor"));
			dizimo.getListaDizimistavalorDia().add(dizvalDia);
		}

		ps.clearParameters();

		StringBuilder st1 = new StringBuilder();
		st1.append("\n"
				+ "select recdizimoid, recdizimovalorrecebimento, recdizimodatarecebimento, verificador, coletor, coletor.colnome, comunidade.comufantasia from recebimentodizimo, comunidade, coletor\n"
				+ " where recdizimoid = ? and coletor.comunidade_comuid = comunidade.comuid and coletor.colid = recebimentodizimo.coletor;");
		ps = con.prepareStatement(st1.toString());
		ps.setInt(1, i);
		rs = ps.executeQuery();
		while (rs.next()) {
			Coletor coletor = new Coletor();
			Comunidade comu = new Comunidade();
			comu.setNomefantaziaComunidade(rs.getString("comufantasia"));
			coletor.setComunidade(comu);
			coletor.setIdUsuario(rs.getInt("coletor"));
			coletor.setNomeUsuario(rs.getString("colnome"));
			dizimo.setIdbalancete(rs.getInt("recdizimoid"));
			dizimo.setDataEntregaBalancete(rs.getDate("recdizimodatarecebimento").toLocalDate());
			dizimo.setValortotalrecebido(rs.getBigDecimal("recdizimovalorrecebimento"));
			dizimo.setVerificador(rs.getString("verificador"));
			dizimo.setColetor(coletor);
		}
		ps.close();
		rs.close();
		return dizimo;
	}

	public void delete(Integer i) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("delete from dizimistascontribuintes where dizimistascontribuintes.recebimentodizimo = ? ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setInt(1, i);
		ps.executeUpdate();

		ps.clearParameters();

		StringBuilder st1 = new StringBuilder();
		st1.append("delete from recebimentodizimo where recdizimoid = ?");
		ps = con.prepareStatement(st1.toString());
		ps.setInt(1, i);
		ps.executeUpdate();
		ps.close();

	}

}
