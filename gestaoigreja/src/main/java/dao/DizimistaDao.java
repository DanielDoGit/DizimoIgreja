package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Cidade;
import beans.Dizimista;

public class DizimistaDao {

	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void cadastrarDizimista(Dizimista dizimista) throws Exception {

		StringBuilder st = new StringBuilder();
		st.append("insert into dizimista values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		PreparedStatement ps = connection.prepareStatement(st.toString());
		ps.setInt(1, dizimista.getIdDizimista());
		ps.setInt(2, dizimista.getCidade().getIdCidade());
		ps.setString(3, dizimista.getCpfDizimista());
		ps.setString(4, dizimista.getNomeDizimista());
		ps.setDate(5, Date.valueOf(dizimista.getDataNascimentoDizimista()));
		ps.setString(6, dizimista.getCelularDizimista());
		ps.setString(7, dizimista.getTelefoneDizimista());
		ps.setString(8, dizimista.getEdereco());
		ps.setString(9, dizimista.getNumerologradouro());
		ps.setString(10, dizimista.getObservacoesDizimista());
		ps.setDate(11, Date.valueOf(dizimista.getDatacadastroDizimista()));
		ps.setString(12, dizimista.getRgDizimista());
		ps.setString(13, dizimista.getIsativo());
		ps.setString(14, dizimista.getContatos());
		ps.executeUpdate();

	}

	public Integer puxarIndiceMaximo() throws Exception {

		StringBuilder st = new StringBuilder();
		st.append("select max(dizid) from dizimista");
		PreparedStatement ps = connection.prepareStatement(st.toString());
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt("max");

	}
	
	public void excluirDizimista(Dizimista dizi) throws Exception {
		StringBuilder st = new StringBuilder();
		st.append("delete from dizimista where dizid = ?");
		PreparedStatement ps = connection.prepareStatement(st.toString());
		ps.setInt(1, dizi.getIdDizimista());
		ps.executeUpdate();
	}
	

	public void editarDizimista(Dizimista dizi) throws Exception {
		StringBuilder st = new StringBuilder();
		st.append("update dizimista set " + "cidade_cidadeid = ? , " + "dizcpf = ?," + "diznome = ?,"
				+ "dizdatanascimento = ?," + "dizcelular = ?," + "diztelefone = ?," + "dizendereco = ?,"
				+ "diznendereco = ?," + "dizobservacoes = ?, " + "dizrg = ? ," + "isativo = ?, contatos = ?" + "where dizid = ?");
		PreparedStatement ps = connection.prepareStatement(st.toString());
		ps.setInt(1, dizi.getCidade().getIdCidade());
		ps.setString(2, dizi.getCpfDizimista());
		ps.setString(3, dizi.getNomeDizimista());
		ps.setDate(4, Date.valueOf(dizi.getDataNascimentoDizimista()));
		ps.setString(5, dizi.getCelularDizimista());
		ps.setString(6, dizi.getTelefoneDizimista());
		ps.setString(7, dizi.getEdereco());
		ps.setString(8, dizi.getNumerologradouro());
		ps.setString(9, dizi.getObservacoesDizimista());
		ps.setString(10, dizi.getRgDizimista());
		ps.setString(11, dizi.getIsativo());
		ps.setString(12, dizi.getContatos());
		ps.setInt(13, dizi.getIdDizimista());
		ps.executeUpdate();

	}

	public List<Dizimista> pesquisarListaDizimistasNome(Dizimista diz) throws Exception {
		List<Dizimista> listaDizimistas = new ArrayList<Dizimista>();
		StringBuilder st = new StringBuilder();
		st.append("select diznome, dizimista.cidade_cidadeid, dizid, cidadenomecidade, isativo from dizimista "
				+ "inner join cidade on (cidadeid = dizimista.cidade_cidadeid) where diznome ilike ? ");
		PreparedStatement ps = connection.prepareStatement(st.toString());
		ps.setString(1, "%"+diz.getNomeDizimista()+"%");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			diz = new Dizimista();
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidade_cidadeid"));
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			diz.setCidade(cidade);
			diz.setIdDizimista(rs.getInt("dizid"));
			diz.setNomeDizimista(rs.getString("diznome"));
			diz.setIsativo(rs.getString("isativo"));
			listaDizimistas.add(diz);
		}

		return listaDizimistas;
	}
	
	public List<Dizimista> pesquisarListaDizimistasNomeAtivos(Dizimista diz) throws Exception {
		List<Dizimista> listaDizimistas = new ArrayList<Dizimista>();
		StringBuilder st = new StringBuilder();
		st.append("select diznome, dizimista.cidade_cidadeid, dizid, cidadenomecidade, isativo from dizimista "
				+ "inner join cidade on (cidadeid = dizimista.cidade_cidadeid) where diznome ilike ? and isativo ilike '%s'");
		PreparedStatement ps = connection.prepareStatement(st.toString());
		ps.setString(1, "%"+diz.getNomeDizimista()+"%");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			diz = new Dizimista();
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidade_cidadeid"));
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			diz.setCidade(cidade);
			diz.setIdDizimista(rs.getInt("dizid"));
			diz.setNomeDizimista(rs.getString("diznome"));
			diz.setIsativo(rs.getString("isativo"));
			listaDizimistas.add(diz);
		}

		return listaDizimistas;
	}
	
	public List<Dizimista> pesquisarListaDizimistasCodigo(Dizimista diz) throws Exception {
		List<Dizimista> listaDizimistas = new ArrayList<Dizimista>();
		StringBuilder st = new StringBuilder();
		st.append("select diznome, dizimista.cidade_cidadeid, dizid, cidadenomecidade, isativo from dizimista "
				+ "inner join cidade on (cidadeid = dizimista.cidade_cidadeid) where dizid = ?");
		PreparedStatement ps = connection.prepareStatement(st.toString());
		ps.setInt(1,diz.getIdDizimista());
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			diz = new Dizimista();
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidade_cidadeid"));
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			diz.setCidade(cidade);
			diz.setIdDizimista(rs.getInt("dizid"));
			diz.setNomeDizimista(rs.getString("diznome"));
			diz.setIsativo(rs.getString("isativo"));
			listaDizimistas.add(diz);
		}

		return listaDizimistas;
	}
	
	public List<Dizimista> pesquisarListaDizimistasCodigoAtivos(Dizimista diz) throws Exception {
		List<Dizimista> listaDizimistas = new ArrayList<Dizimista>();
		StringBuilder st = new StringBuilder();
		st.append("select diznome, dizimista.cidade_cidadeid, dizid, cidadenomecidade, isativo from dizimista "
				+ "inner join cidade on (cidadeid = dizimista.cidade_cidadeid) where dizid = ? and isativo ilike '%s'");
		PreparedStatement ps = connection.prepareStatement(st.toString());
		ps.setInt(1,diz.getIdDizimista());
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			diz = new Dizimista();
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidade_cidadeid"));
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			diz.setCidade(cidade);
			diz.setIdDizimista(rs.getInt("dizid"));
			diz.setNomeDizimista(rs.getString("diznome"));
			diz.setIsativo(rs.getString("isativo"));
			listaDizimistas.add(diz);
		}

		return listaDizimistas;
	}
	
	public List<Dizimista> pesquisarListaDizimistasCidade(Dizimista diz) throws Exception {
		List<Dizimista> listaDizimistas = new ArrayList<Dizimista>();
		StringBuilder st = new StringBuilder();
		st.append("select diznome, dizimista.cidade_cidadeid, dizid, cidadenomecidade, isativo from dizimista "
				+ "inner join cidade on (cidadeid = dizimista.cidade_cidadeid) where cidadenomecidade ilike ?");
		PreparedStatement ps = connection.prepareStatement(st.toString());
		ps.setString(1,"%"+diz.getCidade().getNomeCidade()+"%");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			diz = new Dizimista();
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidade_cidadeid"));
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			diz.setCidade(cidade);
			diz.setIdDizimista(rs.getInt("dizid"));
			diz.setNomeDizimista(rs.getString("diznome"));
			diz.setIsativo(rs.getString("isativo"));
			listaDizimistas.add(diz);
		}

		return listaDizimistas;
	}
	
	public Dizimista consultatotal(Integer id) throws Exception{
		Dizimista dizi = new Dizimista();
		StringBuilder st = new StringBuilder();
		st.append("select * from dizimista "
				+ "inner join cidade on (cidadeid = dizimista.cidade_cidadeid) where dizid = ?");
		PreparedStatement ps = connection.prepareStatement(st.toString());
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		dizi.setIdDizimista(rs.getInt("dizid"));
		Cidade c = new Cidade();
		c.setIdCidade(rs.getInt("cidade_cidadeid"));
		c.setNomeCidade(rs.getString("cidadenomecidade"));
		dizi.setCidade(c);
		dizi.setNomeDizimista(rs.getString("diznome"));
		dizi.setCpfDizimista(rs.getString("dizcpf"));
		dizi.setRgDizimista(rs.getString("dizrg"));
		dizi.setDataNascimentoDizimista(rs.getDate("dizdatanascimento").toLocalDate());
		dizi.setCelularDizimista(rs.getString("dizcelular"));
		dizi.setTelefoneDizimista(rs.getString("diztelefone"));
		dizi.setEdereco(rs.getString("dizendereco"));
		dizi.setNumerologradouro(rs.getString("diznendereco"));
		dizi.setObservacoesDizimista(rs.getString("dizobservacoes"));
		dizi.setIsativo(rs.getString("isativo"));
		dizi.setContatos(rs.getString("contatos"));
		rs.close();
		ps.close();
		return dizi;
	}

}
