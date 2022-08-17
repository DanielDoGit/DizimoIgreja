package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cidade;
import beans.Comunidade;

public class ComunidadeDao {

	private Connection con;
	private PreparedStatement ps;
	private List<Comunidade> listaComunidade = new ArrayList<Comunidade>();
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	
	public void cadastrar(Comunidade com) throws SQLException {
		
		String expressao = "insert into comunidade values(?,?,?,?,?,?,?,?,?)";
		ps = con.prepareStatement(expressao);
		ps.setInt(1, com.getIdComunidade());
		ps.setInt(2, com.getCidade().getIdCidade());
		ps.setString(3, com.getNomefantaziaComunidade());
		ps.setString(4, com.getNomerazaosocialComunidade());
		ps.setString(5, com.getCnpjComunidade());
		ps.setString(6, com.getEnderecoComunidade());
		ps.setString(7, com.getNumeroenderecoComunidade());
		ps.setString(8, com.getBairroComunidade());
		ps.setString(9, com.getObservacoes());
		ps.executeUpdate();
		
	}
	
	public void editar(Comunidade com) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("update comunidade set ");
		st.append("cidade_cidadeid=?,");
		st.append("comufantasia=?, comurazaosocial=?, comucnpj=?,");
		st.append("comuendereco=?, comuNumeroEndereco=?, comuBairro=?, comuObservacoes=?");
		st.append("where comuId=?");
		ps = con.prepareStatement(st.toString().toLowerCase());
		ps.setInt(1, com.getCidade().getIdCidade());
		ps.setString(2, com.getNomefantaziaComunidade());
		ps.setString(3, com.getNomerazaosocialComunidade());
		ps.setString(4, com.getCnpjComunidade());
		ps.setString(5, com.getEnderecoComunidade());
		ps.setString(6,com.getNumeroenderecoComunidade());
		ps.setString(7, com.getBairroComunidade());
		ps.setString(8, com.getObservacoes());
		ps.setInt(9, com.getIdComunidade());
		ps.executeUpdate();

	}
	
	public void excluir(Comunidade com) throws SQLException {
		String expressao = "delete from comunidade where comuid=?";
		ps = con.prepareStatement(expressao.toLowerCase());
		ps.setInt(1, com.getIdComunidade());
		ps.executeUpdate();

	}
	
	public Comunidade pesquisarComunidadeNomeFantasia(Comunidade com) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "comunidade.comuid, "
				+ "cidade.cidadenomecidade "
				+ "from comunidade inner join cidade on"
				+ " (comunidade.cidade_cidadeid = cidade.cidadeid) "
				+ "where comunidade.comufantasia=?");
		ps = con.prepareStatement(st.toString().toLowerCase());
		ps.setString(1, com.getNomefantaziaComunidade());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(rs.getInt("comunidade.comuid"));
			com.setNomefantaziaComunidade(rs.getString("comunidade.comufantasia"));
			Cidade cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidade.cidadenomecidade"));
			com.setCidade(cidade);
		}
		
		return com;
	}
	
	public Comunidade pesquisarComunidadeIndice(Comunidade com) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "comuId, "
				+ "comuFantasia, "
				+ "cidade.cidadeNomeCidade "
				+ "from comunidade inner join cidade on"
				+ " (comunidade.Cidade_cidadeId = cidade.cidadeId) "
				+ "where comunidade.comuId=?");
		ps = con.prepareStatement(st.toString().toLowerCase());
		ps.setInt(1, com.getIdComunidade());
		ResultSet rs = ps.executeQuery();
		com = null;
		while (rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(rs.getInt("comuid"));
			com.setNomefantaziaComunidade(rs.getString("comufantasia"));
			Cidade cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			com.setCidade(cidade);
		}
		
		return com;
	}
	
	public Comunidade pesquisarComunidadeIndiceComCidadeTotal(Comunidade com) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "comunidade.comuFantasia, "
				+ "comunidade.comuId, "
				+ "cidade.cidadeNomeCidade, "
				+ "cidade.cidadeId, "
				+ "cidade.cidadeUf "
				+ "from comunidade inner join cidade on"
				+ " (comunidade.Cidade_cidadeId = cidade.cidadeId) "
				+ "where comunidade.comuId=?");
		ps = con.prepareStatement(st.toString().toLowerCase());
		ps.setInt(1, com.getIdComunidade());
		ResultSet rs = ps.executeQuery();
		com = null;
		while (rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(rs.getInt("comunidade.comuid"));
			com.setNomefantaziaComunidade(rs.getString("comunidade.comufantasia"));
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidade.cidadeid"));
			cidade.setNomeCidade(rs.getString("cidade.cidadenomecidade"));
			cidade.setUfCidade(rs.getString("cidade.cidadeuf"));
			com.setCidade(cidade);
		}
		
		return com;
	}
	
	public Comunidade pesquisarComunidadeIndiceMaximo() throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "max(comuid) "
				+ "from comunidade;");
		ps = con.prepareStatement(st.toString().toLowerCase());
		ResultSet rs = ps.executeQuery();
		Comunidade com = null;
		while(rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(rs.getInt("max"));
		}
		com.setIdComunidade(com.getIdComunidade()+1);
		return com;
	}
	
	public Comunidade populartelaComunidade(Comunidade com) throws SQLException{
		
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "comunidade.comuId, "
				+ "comunidade.comuFantasia, "
				+ "comunidade.comuRazaoSocial, "
				+ "comunidade.comuCnpj, "
				+ "comunidade.comuEndereco, "
				+ "comunidade.comuNumeroEndereco, "
				+ "comunidade.comuBairro, "
				+ "comunidade.comuObservacoes, "
				+ "cidade.cidadeNomeCidade, "
				+ "cidade.cidadeId, "
				+ "cidade.cidadeUf "
				+ "from comunidade inner join cidade on"
				+ " (comunidade.Cidade_cidadeId = cidade.cidadeId) "
				+ "where comunidade.comuId=?;");
		ps = con.prepareStatement(st.toString().toLowerCase());
		ps.setInt(1, com.getIdComunidade());
		ResultSet rs = ps.executeQuery();
		com = null;
		while (rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(rs.getInt("comuid"));
			com.setNomefantaziaComunidade(rs.getString("comufantasia"));
			com.setNomerazaosocialComunidade(rs.getString("comurazaosocial"));
			com.setCnpjComunidade(rs.getString("comucnpj"));
			com.setEnderecoComunidade(rs.getString("comuendereco"));
			com.setNumeroenderecoComunidade(rs.getString("comunumeroendereco"));
			com.setBairroComunidade(rs.getString("comubairro"));
			com.setObservacoes(rs.getString("comuobservacoes"));
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidadeid"));
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			cidade.setUfCidade(rs.getString("cidadeuf"));
			com.setCidade(cidade);
			listaComunidade.add(com);
		}		
		
		return com;
	}
	
		
	public List<Comunidade> pesquisarListaComunidadeNomeFantasia(Comunidade com) throws SQLException{
		
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "comunidade.comuId,"
				+ "comunidade.comuFantasia, "
				+ "cidade.cidadeNomeCidade "
				+ "from comunidade inner join cidade on"
				+ " (comunidade.Cidade_cidadeId = cidade.cidadeId) "
				+ "where comunidade.comuFantasia ilike ?;");
		ps = con.prepareStatement(st.toString());
		ps.setString(1, "%"+ com.getNomefantaziaComunidade() + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(rs.getInt("comuid"));
			com.setNomefantaziaComunidade(rs.getString("comufantasia"));
			Cidade cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			com.setCidade(cidade);
			listaComunidade.add(com);
		}		
		
		return listaComunidade;
	}
	
	public List<Comunidade> pesquisarListaComunidadeCidade(Cidade c) throws SQLException{
		
	
		StringBuilder st = new StringBuilder();
		st.append("select comunidade.comuId,comunidade.comuFantasia, cidade.cidadeNomeCidade from comunidade inner join cidade on \r\n"
				+ "(comunidade.Cidade_cidadeId = cidade.cidadeId) where cidadenomecidade ilike ?;");
		ps = con.prepareStatement(st.toString().toLowerCase());
		ps.setString(1, "%"+c.getNomeCidade()+"%");
		ResultSet rs = ps.executeQuery();
		
		Comunidade com = null;
		while (rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(rs.getInt("comuid"));
			com.setNomefantaziaComunidade(rs.getString("comufantasia"));
			c.setNomeCidade(rs.getString("cidadenomecidade"));
			com.setCidade(c);
			listaComunidade.add(com);
			
		}
		return listaComunidade;
	}
	
	
	
	
	
}
