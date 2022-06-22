package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cidade;
import beans.Comunidade;
import beans.Fornecedor;

public class FornecedorDao {
	private Connection con;
	private PreparedStatement ps;
	private List<Fornecedor> listaFornecedors = new ArrayList<Fornecedor>();
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	
	public void cadastrar(Fornecedor com) throws SQLException {
		
		String expressao = "insert into fornecedor values(?,?,?,?,?,?,?,?,?,?)";
		ps = con.prepareStatement(expressao);
		ps.setInt(1, com.getIndice());
		ps.setInt(2, com.getCidade().getIdCidade());
		ps.setString(3, com.getCnpj());
		ps.setString(4, com.getNomeFantasia());
		ps.setString(5, com.getCelular());
		ps.setString(6, com.getTelefone());
		ps.setString(7, com.getRazaoSocial());
		ps.setString(8, com.getEndereco());
		ps.setString(9, com.getNumeroendereco());
		ps.setString(10, com.getObservacoes());
		ps.executeUpdate();
		
	}
	
	public void editar(Fornecedor com) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("update fornecedor set ");
		st.append("Cidade_cidadeId=?,");
		st.append("forNome=?, forRazaoSocial=?, forCnpj=?, ");
		st.append("forEndereco=?, forNEndereco=?,"
				+ " observacoes=?, forCelular=?, forTelefone=? ");
		st.append("where forId=?");
		ps = con.prepareStatement(st.toString());
		ps.setInt(1, com.getCidade().getIdCidade());
		ps.setString(2, com.getNomeFantasia());
		ps.setString(3, com.getRazaoSocial());
		ps.setString(4, com.getCnpj());
		ps.setString(5, com.getEndereco());
		ps.setString(6, com.getNumeroendereco());
		ps.setString(7, com.getObservacoes());
		ps.setString(8, com.getCelular());
		ps.setString(9, com.getTelefone());
		ps.setInt(10, com.getIndice());
		ps.executeUpdate();

	}
	
	public void excluir(Fornecedor com) throws SQLException {
		String expressao = "delete from fornecedor where forId=?";
		ps = con.prepareStatement(expressao);
		ps.setInt(1, com.getIndice());
		ps.executeUpdate();

	}
	
	public Fornecedor pesquisarComunidadeNomeFantasia(Fornecedor com) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "fornecedor.forId, "
				+ "cidade.cidadeNomeCidade "
				+ "from fornecedor inner join cidade on"
				+ " (fornecedor.Cidade_cidadeId = cidade.cidadeId) "
				+ "where fornecedor.forNome=?");
		ps = con.prepareStatement(st.toString());
		ps.setString(1, com.getNomeFantasia());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			com = new Fornecedor();
			com.setNomeFantasia(rs.getString("fornecedor.forNome"));
			Cidade cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidade.cidadeNomeCidade"));
			com.setCidade(cidade);
		}
		
		return com;
	}
	
	public Fornecedor pesquisarComunidadeIndice(Fornecedor com) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "fornecedor.forId, "
				+ "fornecedor.forNome, "
				+ "cidade.cidadeNomeCidade "
				+ "from fornecedor inner join cidade on"
				+ " (fornecedor.Cidade_cidadeId = cidade.cidadeId) "
				+ "where fornecedor.forId=?");
		ps = con.prepareStatement(st.toString());
		ps.setInt(1, com.getIndice());
		ResultSet rs = ps.executeQuery();
		com = null;
		while (rs.next()) {
			com = new Fornecedor();
			com.setIndice(Integer.valueOf(rs.getString("fornecedor.forId")));
			com.setNomeFantasia(rs.getString("fornecedor.forNome"));
			Cidade cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidade.cidadeNomeCidade"));
			com.setCidade(cidade);
		}
		
		return com;
	}
	
	public Fornecedor pesquisarComunidadeIndiceComCidadeTotal(Fornecedor com) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "fornecedor.forNome, "
				+ "fornecedor.forId, "
				+ "cidade.cidadeNomeCidade, "
				+ "cidade.cidadeId, "
				+ "cidade.cidadeUf "
				+ "from fornecedor inner join cidade on"
				+ " (fornecedor.Cidade_cidadeId = cidade.cidadeId) "
				+ "where fornecedor.forId=?");
		ps = con.prepareStatement(st.toString());
		ps.setInt(1, com.getIndice());
		ResultSet rs = ps.executeQuery();
		com = null;
		while (rs.next()) {
			com = new Fornecedor();
			com.setIndice(rs.getInt("fornecedor.forId"));
			com.setNomeFantasia(rs.getString("fornecedor.forNome"));
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidade.cidadeId"));
			cidade.setNomeCidade(rs.getString("cidade.cidadeNomeCidade"));
			cidade.setUfCidade(rs.getString("cidade.cidadeUf"));
			com.setCidade(cidade);
		}
		
		return com;
	}
	
	public Fornecedor pesquisarComunidadeIndiceMaximo() throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "max(forId) "
				+ "from fornecedor;");
		ps = con.prepareStatement(st.toString());
		ResultSet rs = ps.executeQuery();
		Fornecedor com = null;
		while(rs.next()) {
			com = new Fornecedor();
			com.setIndice(rs.getInt("max(forId)")+1);
		}

		return com;
	}
	
	public Fornecedor populartelaComunidade(Fornecedor com) throws SQLException{
		
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "fornecedor.forId, "
				+ "fornecedor.forNome, "
				+ "fornecedor.forRazaoSocial, "
				+ "fornecedor.forCnpj, "
				+ "fornecedor.forEndereco, "
				+ "fornecedor.forNEndereco, "
				+ "fornecedor.observacoes, "
				+ "cidade.cidadeNomeCidade, "
				+ "cidade.cidadeId, "
				+ "cidade.cidadeUf "
				+ "from fornecedor inner join cidade on"
				+ " (fornecedor.Cidade_cidadeId = cidade.cidadeId) "
				+ "where fornecedor.forId=?;");
		ps = con.prepareStatement(st.toString());
		ps.setInt(1, com.getIndice());
		ResultSet rs = ps.executeQuery();
		com = null;
		while (rs.next()) {
			com = new Fornecedor();
			com.setIndice(rs.getInt("fornecedor.forId"));
			com.setNomeFantasia(rs.getString("fornecedor.forNome"));
			com.setRazaoSocial(rs.getString("fornecedor.forRazaoSocial"));
			com.setCnpj(rs.getString("fornecedor.forCnpj"));
			com.setEndereco(rs.getString("fornecedor.forEndereco"));
			com.setNumeroendereco(rs.getString("fornecedor.forNEndereco"));
			com.setObservacoes(rs.getString("fornecedor.observacoes"));
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidade.cidadeId"));
			cidade.setNomeCidade(rs.getString("cidade.cidadeNomeCidade"));
			cidade.setUfCidade(rs.getString("cidade.cidadeUf"));
			com.setCidade(cidade);
			listaFornecedors.add(com);
		}		
		
		return com;
	}
	
		
	public List<Fornecedor> pesquisarListaComunidadeNomeFantasia(Fornecedor com) throws SQLException{
		
		StringBuilder st = new StringBuilder();
		st.append("select "
				+ "fornecedor.forId,"
				+ "fornecedor.forNome, "
				+ "cidade.cidadeNomeCidade "
				+ "from fornecedor inner join cidade on"
				+ " (fornecedor.Cidade_cidadeId = cidade.cidadeId) "
				+ "where fornecedor.forNome=?");
		ps = con.prepareStatement(st.toString());
		ps.setString(1, com.getNomeFantasia());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			com = new Fornecedor();
			com.setNomeFantasia(rs.getString("fornecedor.forNome"));
			com.setIndice(rs.getInt("fornecedor.forId"));
			Cidade cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidade.cidadeNomeCidade"));
			com.setCidade(cidade);
			listaFornecedors.add(com);
		}		
		
		return listaFornecedors;
	}
	
	public List<Fornecedor> pesquisarListaComunidadeCidade(Cidade c) throws SQLException{
		
		StringBuilder st = new StringBuilder();
		st.append("select fornecedor.forId, fornecedor.forNome from fornecedor"
				+ " where fornecedor.Cidade_cidadeId = "
				+ "(select cidade.cidadeId from cidade where cidade.cidadeNomeCidade=?)");
		ps = con.prepareStatement(st.toString());
		ps.setString(1, c.getNomeCidade());
		ResultSet rs = ps.executeQuery();
		Fornecedor com = null;
		while (rs.next()) {
			com = new Fornecedor();
			com.setIndice(rs.getInt("fornecedor.forId"));
			com.setNomeFantasia(rs.getString("fornecedor.forNome"));
			com.setCidade(c);
			listaFornecedors.add(com);
			
		}
		return listaFornecedors;
	}
	
}
