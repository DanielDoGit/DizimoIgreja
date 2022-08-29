package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cidade;
import beans.Coletor;
import beans.Comunidade;

public class ColetorDao {

	private Connection con;
	private PreparedStatement preparedStatement;

	public void setConnectionOnComunidadeDao(Connection com) {
		con = com;
	}

	public Connection getConection() {
		return con;
	}

	public void inserirColetor(Coletor coletor) throws Exception {

		StringBuilder st = new StringBuilder();
		st.append("insert into coletor values (" + "?," + "?," + "?,"

				+ "?," + "?," + "?,"

				+ "?," + "?," + "?,"

				+ "?," + "?," + "?,"

				+ "?," + "?," + "?," + "?"

				+ ");");
		preparedStatement = con.prepareStatement(st.toString());
		preparedStatement.setInt(1, coletor.getIdUsuario());
		preparedStatement.setInt(2, coletor.getComunidade().getIdComunidade());
		preparedStatement.setInt(3, coletor.getCidadeUsuario().getIdCidade());
		preparedStatement.setString(4, coletor.getCpfUsuario());
		preparedStatement.setString(5, coletor.getNomeUsuario());
		preparedStatement.setDate(6, Date.valueOf(coletor.getDatanascimentoUsuario()));
		preparedStatement.setString(7, coletor.getCelularUsuario());
		preparedStatement.setString(8, coletor.getTelefoneUsuario());
		preparedStatement.setString(9, coletor.getEnderecoUsuario());
		preparedStatement.setString(10, coletor.getNumeroenderecoUsuario());
		preparedStatement.setString(11, coletor.getObservacoesUsuario());
		preparedStatement.setString(12, coletor.getRgUsuario());
		preparedStatement.setString(13, coletor.getLoginUsuario());
		preparedStatement.setString(14, coletor.getSenhaUsuario());
		preparedStatement.setDate(15, Date.valueOf(coletor.getDataCadastro()));
		preparedStatement.setString(16, coletor.getIsativo());
		preparedStatement.executeUpdate();

	}

	public void editarColetor(Coletor coletor) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("update coletor set comunidade_comuid = ?," + " cidade_cidadeid = ?, "

				+ "colcpf = ?," + " colnome = ?," + " coldatanascimento = ?, "

				+ "colcelular = ?, " + "coltelefone = ?," + " colendereco = ?, "

				+ "colnendereco = ?, " + "colobservacoes = ?," + " colrg = ?, "

				+ "collogin = ?, " + "colsenha = ?," + " colativo = ?"

				+ " where colid = ?;");
		preparedStatement = con.prepareStatement(st.toString());
		preparedStatement.setInt(1, coletor.getComunidade().getIdComunidade());
		preparedStatement.setInt(2, coletor.getCidadeUsuario().getIdCidade());
		preparedStatement.setString(3, coletor.getCpfUsuario());
		preparedStatement.setString(4, coletor.getNomeUsuario());
		preparedStatement.setDate(5, Date.valueOf(coletor.getDatanascimentoUsuario()));
		preparedStatement.setString(6, coletor.getCelularUsuario());
		preparedStatement.setString(7, coletor.getTelefoneUsuario());
		preparedStatement.setString(8, coletor.getEnderecoUsuario());
		preparedStatement.setString(9, coletor.getNumeroenderecoUsuario());
		preparedStatement.setString(10, coletor.getObservacoesUsuario());
		preparedStatement.setString(11, coletor.getRgUsuario());
		preparedStatement.setString(12, coletor.getLoginUsuario());
		preparedStatement.setString(13, coletor.getSenhaUsuario());
		preparedStatement.setString(14, coletor.getIsativo());
		preparedStatement.setInt(15, coletor.getIdUsuario());
		preparedStatement.executeUpdate();
	}

	public void excluir(Coletor coletor) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("delete from coletor where colid = ?");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setInt(1, coletor.getIdUsuario());
		ps.executeUpdate();

	}

	public Coletor consultarIndiceMaximo() throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select max(colid) from coletor;");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ResultSet rs = ps.executeQuery();
		rs.next();
		Coletor coletor = new Coletor();
		coletor.setIdUsuario(rs.getInt("max"));
		return coletor;
	}

	public List<Coletor> pesquisarlistaColetorNome(Coletor coletor) throws SQLException {
		List<Coletor> listaColetor = new ArrayList<Coletor>();
		StringBuilder st = new StringBuilder();
		st.append("select colid, colnome, comufantasia, cidadenomecidade from coletor, cidade, comunidade "
				+ "where (comunidade_comuid = comuid) " + "and (cidadeid = comunidade.cidade_cidadeid)"
				+ " and colnome ilike ?; ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setString(1, "%" + coletor.getNomeUsuario() + "%");
		ResultSet rs = ps.executeQuery();
		Cidade c = null;
		Comunidade comunidade = null;
		while (rs.next()) {
			c = new Cidade();
			c.setNomeCidade(rs.getString("cidadenomecidade"));
			comunidade = new Comunidade();
			comunidade.setNomefantaziaComunidade(rs.getString("comufantasia"));
			coletor = new Coletor();
			coletor.setIdUsuario(rs.getInt("colid"));
			coletor.setNomeUsuario(rs.getString("colnome"));
			coletor.setCidadeUsuario(c);
			coletor.setComunidade(comunidade);
			listaColetor.add(coletor);
		}

		return listaColetor;

	}

	public List<Coletor> pesquisarlistaColetorCodigo(Coletor coletor) throws SQLException {
		List<Coletor> listaColetor = new ArrayList<Coletor>();
		StringBuilder st = new StringBuilder();
		st.append("select colid, colnome, comufantasia, cidadenomecidade from coletor, cidade, comunidade "
				+ "where (comunidade_comuid = comuid) " + "and (cidadeid = comunidade.cidade_cidadeid)"
				+ " and colid = ?; ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setInt(1, coletor.getIdUsuario());
		ResultSet rs = ps.executeQuery();
		Cidade c = null;
		Comunidade comunidade = null;
		while (rs.next()) {
			c = new Cidade();
			c.setNomeCidade(rs.getString("cidadenomecidade"));
			comunidade = new Comunidade();
			comunidade.setNomefantaziaComunidade(rs.getString("comufantasia"));
			coletor = new Coletor();
			coletor.setIdUsuario(rs.getInt("colid"));
			coletor.setNomeUsuario(rs.getString("colnome"));
			coletor.setCidadeUsuario(c);
			coletor.setComunidade(comunidade);
			listaColetor.add(coletor);
		}

		return listaColetor;

	}

	public List<Coletor> pesquisarListaColetorCidade(Cidade cidade) throws SQLException {
		List<Coletor> listaColetor = new ArrayList<Coletor>();
		StringBuilder st = new StringBuilder();
		st.append(
				"select colid, colnome, comufantasia, cidadenomecidade from coletor, cidade, comunidade where (comunidade_comuid = comuid) and (cidadeid = comunidade.cidade_cidadeid)\r\n"
				+ " and cidadenomecidade ilike ? ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setString(1, "%" + cidade.getNomeCidade() + "%");
		ResultSet rs = ps.executeQuery();

		Comunidade comunidade = null;
		Coletor coletor = null;
		while (rs.next()) {
			cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			comunidade = new Comunidade();
			comunidade.setNomefantaziaComunidade(rs.getString("comufantasia"));
			coletor = new Coletor();
			coletor.setIdUsuario(rs.getInt("colid"));
			coletor.setNomeUsuario(rs.getString("colnome"));
			coletor.setCidadeUsuario(cidade);
			coletor.setComunidade(comunidade);
			listaColetor.add(coletor);
		}

		return listaColetor;
	}

	public List<Coletor> pesquisarListaColetorCoomunidade(Comunidade comunidade) throws SQLException {
		List<Coletor> listaColetor = new ArrayList<Coletor>();
		StringBuilder st = new StringBuilder();
		st.append(
				"select colid, colnome, comufantasia, cidadenomecidade from coletor inner join comunidade on (comunidade_comuid = comuid ) inner join cidade on (cidadeid = coletor.cidade_cidadeid)\r\n"
						+ "where comufantasia ilike ?" + "; ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setString(1, "%" + comunidade.getNomefantaziaComunidade() + "%");
		ResultSet rs = ps.executeQuery();
		Cidade cidade = null;
		Coletor coletor = null;
		while (rs.next()) {
			cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			comunidade = new Comunidade();
			comunidade.setNomefantaziaComunidade(rs.getString("comufantasia"));
			coletor = new Coletor();
			coletor.setIdUsuario(rs.getInt("colid"));
			coletor.setNomeUsuario(rs.getString("colnome"));
			coletor.setComunidade(comunidade);
			coletor.setCidadeUsuario(cidade);
			listaColetor.add(coletor);
		}

		return listaColetor;
	}

	public Coletor consultatotal(Coletor coletor) throws SQLException {

		StringBuilder st = new StringBuilder();
		st.append("select " + "colid," + "colcpf, \r\n" + "colnome,\r\n" + "coldatanascimento,\r\n" + "colcelular,\r\n"
				+ "coltelefone,\r\n" + "colendereco,\r\n" + "colnendereco,\r\n" + "colobservacoes,\r\n" + "colrg,\r\n"
				+ "colcpf,\r\n" + "collogin,\r\n" + "colsenha,\r\n" + "colativo,\r\n" + "datacadastro,"
				+ "cidade.cidadeid,\r\n" + "cidade.cidadenomecidade," + "comunidade.comuid, "
				+ "comunidade.comufantasia\r\n"
				+ "from coletor inner join cidade on (cidadeid = coletor.cidade_cidadeid) inner join comunidade on (comuid = coletor.comunidade_comuid) where colid = ?;");
		preparedStatement = con.prepareStatement(st.toString());
		preparedStatement.setInt(1, coletor.getIdUsuario());
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			coletor = new Coletor();
			coletor.setIdUsuario(rs.getInt("colid"));
			Comunidade comunidade = new Comunidade();
			comunidade.setIdComunidade(rs.getInt("comuid"));
			comunidade.setNomefantaziaComunidade(rs.getString("comufantasia"));
			coletor.setComunidade(comunidade);
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidadeid"));
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			coletor.setCidadeUsuario(cidade);
			coletor.setCpfUsuario(rs.getString("colcpf"));
			coletor.setNomeUsuario(rs.getString("colnome"));
			coletor.setDatanascimentoUsuario(rs.getDate("coldatanascimento").toLocalDate());
			coletor.setCelularUsuario(rs.getString("colcelular"));
			coletor.setTelefoneUsuario(rs.getString("coltelefone"));
			coletor.setEnderecoUsuario(rs.getString("colendereco"));
			coletor.setNumeroenderecoUsuario(rs.getString("colnendereco"));
			coletor.setIsativo(rs.getString("colativo"));
			coletor.setObservacoesUsuario(rs.getString("colobservacoes"));
			coletor.setRgUsuario(rs.getString("colrg"));
			coletor.setLoginUsuario(rs.getString("collogin"));
			coletor.setSenhaUsuario(rs.getString("colsenha"));
			coletor.setDataCadastro(rs.getDate("datacadastro").toLocalDate());
		}
		return coletor;
	}

}
