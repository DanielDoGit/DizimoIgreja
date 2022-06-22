package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
		st.append("Cidade_cidadeId=?,");
		st.append("comuFantasia=?, comuRazaoSocial=?, comuCnpj=?,");
		st.append("comuEndereco=?, comuNumeroEndereco=?, comuBairro=?, comuObservacoes=?");
		st.append("where comuId=?");
		ps = con.prepareStatement(st.toString());
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
		String expressao = "delete from comunidade where comuId=?";
		ps = con.prepareStatement(expressao);
		ps.setInt(1, com.getIdComunidade());
		ps.executeUpdate();

	}
	
	public Comunidade pesquisarComunidadeNomeFantasia(Comunidade com) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "comunidade.comuId, "
				+ "cidade.cidadeNomeCidade "
				+ "from comunidade inner join cidade on"
				+ " (comunidade.Cidade_cidadeId = cidade.cidadeId) "
				+ "where comunidade.comuFantasia=?");
		ps = con.prepareStatement(st.toString());
		ps.setString(1, com.getNomefantaziaComunidade());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			com = new Comunidade();
			com.setNomefantaziaComunidade(rs.getString("comunidade.comuFantasia"));
			Cidade cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidade.cidadeNomeCidade"));
			com.setCidade(cidade);
		}
		
		return com;
	}
	
	public Comunidade pesquisarComunidadeIndice(Comunidade com) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "comunidade.comuId, "
				+ "comunidade.comuFantasia, "
				+ "cidade.cidadeNomeCidade "
				+ "from comunidade inner join cidade on"
				+ " (comunidade.Cidade_cidadeId = cidade.cidadeId) "
				+ "where comunidade.comuId=?");
		ps = con.prepareStatement(st.toString());
		ps.setInt(1, com.getIdComunidade());
		ResultSet rs = ps.executeQuery();
		com = null;
		while (rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(Integer.valueOf(rs.getString("comunidade.comuId")));
			com.setNomefantaziaComunidade(rs.getString("comunidade.comuFantasia"));
			Cidade cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidade.cidadeNomeCidade"));
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
		ps = con.prepareStatement(st.toString());
		ps.setInt(1, com.getIdComunidade());
		ResultSet rs = ps.executeQuery();
		com = null;
		while (rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(rs.getInt("comunidade.comuId"));
			com.setNomefantaziaComunidade(rs.getString("comunidade.comuFantasia"));
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidade.cidadeId"));
			cidade.setNomeCidade(rs.getString("cidade.cidadeNomeCidade"));
			cidade.setUfCidade(rs.getString("cidade.cidadeUf"));
			com.setCidade(cidade);
		}
		
		return com;
	}
	
	public Comunidade pesquisarComunidadeIndiceMaximo() throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "max(comuId) "
				+ "from comunidade;");
		ps = con.prepareStatement(st.toString());
		ResultSet rs = ps.executeQuery();
		Comunidade com = null;
		while(rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(rs.getInt("max(comuId)")+1);
		}

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
		ps = con.prepareStatement(st.toString());
		ps.setInt(1, com.getIdComunidade());
		ResultSet rs = ps.executeQuery();
		com = null;
		while (rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(rs.getInt("comunidade.comuId"));
			com.setNomefantaziaComunidade(rs.getString("comunidade.comuFantasia"));
			com.setNomerazaosocialComunidade(rs.getString("comunidade.comuRazaoSocial"));
			com.setCnpjComunidade(rs.getString("comunidade.comuCnpj"));
			com.setEnderecoComunidade(rs.getString("comunidade.comuEndereco"));
			com.setNumeroenderecoComunidade(rs.getString("comunidade.comuNumeroEndereco"));
			com.setBairroComunidade(rs.getString("comunidade.comuBairro"));
			com.setObservacoes(rs.getString("comunidade.comuObservacoes"));
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidade.cidadeId"));
			cidade.setNomeCidade(rs.getString("cidade.cidadeNomeCidade"));
			cidade.setUfCidade(rs.getString("cidade.cidadeUf"));
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
				+ "where comunidade.comuFantasia=?");
		ps = con.prepareStatement(st.toString());
		ps.setString(1, com.getNomefantaziaComunidade());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			com = new Comunidade();
			com.setNomefantaziaComunidade(rs.getString("comunidade.comuFantasia"));
			com.setIdComunidade(rs.getInt("comunidade.comuId"));
			Cidade cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidade.cidadeNomeCidade"));
			com.setCidade(cidade);
			listaComunidade.add(com);
		}		
		
		return listaComunidade;
	}
	
	public List<Comunidade> pesquisarListaComunidadeCidade(Cidade c) throws SQLException{
		
		StringBuilder st = new StringBuilder();
		st.append("select comunidade.comuId, comunidade.comuFantasia from comunidade"
				+ " where comunidade.Cidade_cidadeId = "
				+ "(select cidade.cidadeId from cidade where cidade.cidadeNomeCidade=?)");
		ps = con.prepareStatement(st.toString());
		ps.setString(1, c.getNomeCidade());
		ResultSet rs = ps.executeQuery();
		Comunidade com = null;
		while (rs.next()) {
			com = new Comunidade();
			com.setIdComunidade(rs.getInt("comunidade.comuId"));
			com.setNomefantaziaComunidade(rs.getString("comunidade.comuFantasia"));
			com.setCidade(c);
			listaComunidade.add(com);
			
		}
		return listaComunidade;
	}
	

	//relatorio
	public List<Comunidade> pesquisarListaComunidadeCnpj(){
		
		
		
		
		return listaComunidade;
	}
	
	public List<Comunidade> pesquisarListaComunidadeRazaoSocial(){
		
		
		
		
		return listaComunidade;
	}
	
	
	
	
	
}
