package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CategoriaFuncionario;
import beans.Cidade;
import beans.Funcionario;

public class FuncionarioDao {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet resultSet;
	private List<Funcionario> listafuncionario = new ArrayList<Funcionario>();

	public void setConnectionFuncionarioDao(Connection con) {
		this.con = con;
	}
	
	public Connection getConnection() {
		return con;
	}

	public Funcionario consultarIndiceMaximo() throws SQLException {
		ps = null;
		StringBuilder st = new StringBuilder();
		st.append("select max(funcid) from funcionario;");
		ps = con.prepareStatement(st.toString());
		ResultSet rs = ps.executeQuery();
		rs.next();
		Funcionario funcionario = new Funcionario();
		funcionario.setIdUsuario(rs.getInt("max"));
		return funcionario;
	}

	
	public void cadastrarFuncionario(Funcionario func) throws SQLException {
		ps = null;
		String expressao = "insert into funcionario values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps = con.prepareStatement(expressao);
		ps.setInt(1,func.getIdUsuario());
		ps.setInt(2,func.getCategoriaFuncionario().getIdCategoriaFuncionario());
		ps.setInt(3,func.getCidadeUsuario().getIdCidade());
		ps.setString(4,func.getRgUsuario());
		ps.setString(5,func.getCpfUsuario());
		ps.setString(6,func.getNomeUsuario());
		ps.setDate(7, Date.valueOf(func.getDatanascimentoUsuario()));
		ps.setString(8,func.getCelularUsuario());
		ps.setString(9,func.getTelefoneUsuario());
		ps.setString(10,func.getEnderecoUsuario());
		ps.setString(11,func.getNumeroenderecoUsuario());
		ps.setString(12,func.getObservacoesUsuario());
		ps.setString(13,func.getLoginUsuario());
		ps.setString(14,func.getSenhaUsuario());
		ps.setString(15,func.getIsativo());
		ps.setDate(16, Date.valueOf(func.getDataCadastro()));
		ps.executeUpdate();

	}
	
	public void editarFuncionario(Funcionario funcionario) throws SQLException {
		ps = null;
		StringBuilder st = new StringBuilder();
		st.append("update funcionario set categoriafuncionario_catfuncid = ?," + " cidade_cidadeid = ?, "

				+ "funcrg = ?," + " funccpf = ?," + " funcnome = ?, "

				+ "funcdatanascimento = ?, " + "funccelular = ?," + " functelefone = ?, "

				+ "funcendereco = ?, " + " funcnumeroendereco = ?, "

				+ "funcobservacoes = ?, " + "funclogin = ?," + " funcsenha = ?," + "funcativo = ?"

				+ " where funcid = ?;");
		ps = con.prepareStatement(st.toString());
		ps.setInt(1, funcionario.getCategoriaFuncionario().getIdCategoriaFuncionario());
		ps.setInt(2, funcionario.getCidadeUsuario().getIdCidade());
		ps.setString(3, funcionario.getRgUsuario());
		ps.setString(4, funcionario.getCpfUsuario());
		ps.setString(5, funcionario.getNomeUsuario());
		ps.setDate(6, Date.valueOf(funcionario.getDatanascimentoUsuario()));
		ps.setString(7, funcionario.getCelularUsuario());
		ps.setString(8, funcionario.getTelefoneUsuario());
		ps.setString(9, funcionario.getEnderecoUsuario());
		ps.setString(10, funcionario.getNumeroenderecoUsuario());
		ps.setString(11, funcionario.getObservacoesUsuario());
		ps.setString(12, funcionario.getLoginUsuario());
		ps.setString(13, funcionario.getSenhaUsuario());
		ps.setString(14, funcionario.getIsativo());
		ps.setInt(15, funcionario.getIdUsuario());
		ps.executeUpdate();
	}
	
	public void excluirFuncionario(Funcionario coletor) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("delete from funcionario where funcid = ?");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setInt(1, coletor.getIdUsuario());
		ps.executeUpdate();

	}
	
	public Funcionario consultatotal(Funcionario funcionario) throws SQLException {

		StringBuilder st = new StringBuilder();
		st.append("select " + "funcid," + "funcrg, funccpf, \r\n" + "funcnome,\r\n" + "funcdatanascimento,\r\n" + "funccelular,\r\n"
				+ "functelefone,\r\n" + "funcendereco,\r\n" + "funcnumeroendereco,\r\n" + "funcobservacoes,\r\n" + "funclogin,\r\n" + "funcsenha,\r\n" + "funcativo,\r\n" + "datacadastro,"
				+ "cidade.cidadeid,\r\n" + "cidade.cidadenomecidade," + "categoriafuncionario.catfuncid, "
				+ "categoriafuncionario.catfuncnome\r\n"
				+ "from funcionario inner join cidade on (cidadeid = funcionario.cidade_cidadeid) inner join categoriafuncionario on (catfuncid = funcionario.categoriafuncionario_catfuncid) where funcid = ?;");
		ps = con.prepareStatement(st.toString());
		ps.setInt(1, funcionario.getIdUsuario());
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			funcionario = new Funcionario();
			funcionario.setIdUsuario(rs.getInt("funcid"));
			CategoriaFuncionario categoriaFuncionario = new CategoriaFuncionario();
			categoriaFuncionario.setIdCategoriaFuncionario(rs.getInt("catfuncid"));
			categoriaFuncionario.setNomeCategoraiFuncionario(rs.getString("catfuncnome"));
			funcionario.setCategoriaFuncionario(categoriaFuncionario);
			Cidade cidade = new Cidade();
			cidade.setIdCidade(rs.getInt("cidadeid"));
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			funcionario.setCidadeUsuario(cidade);
			funcionario.setRgUsuario(rs.getString("funcrg"));
			funcionario.setCpfUsuario(rs.getString("funccpf"));
			funcionario.setNomeUsuario(rs.getString("funcnome"));
			funcionario.setDatanascimentoUsuario(rs.getDate("funcdatanascimento").toLocalDate());
			funcionario.setCelularUsuario(rs.getString("funccelular"));
			funcionario.setTelefoneUsuario(rs.getString("functelefone"));
			funcionario.setEnderecoUsuario(rs.getString("funcendereco"));
			funcionario.setNumeroenderecoUsuario(rs.getString("funcnumeroendereco"));
			funcionario.setIsativo(rs.getString("funcativo"));
			funcionario.setObservacoesUsuario(rs.getString("funcobservacoes"));
			funcionario.setLoginUsuario(rs.getString("funclogin"));
			funcionario.setSenhaUsuario(rs.getString("funcsenha"));
			funcionario.setDataCadastro(rs.getDate("datacadastro").toLocalDate());
		}
		return funcionario;
	}
	
	public List<Funcionario> pesquisarListaFuncionarioCategoria(CategoriaFuncionario categoriaFuncionario) throws SQLException {
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		StringBuilder st = new StringBuilder();
		st.append(
				"select funcid, funcnome, catfuncnome, catfuncid, cidadenomecidade from funcionario inner join categoriafuncionario on (catfuncid = funcionario.categoriafuncionario_catfuncid ) inner join cidade on (cidadeid = funcionario.cidade_cidadeid)\r\n"
						+ "where funcnome ilike ?" + "; ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setString(1, "%" + categoriaFuncionario.getNomeCategoraiFuncionario() + "%");
		ResultSet rs = ps.executeQuery();
		Cidade cidade = null;
		Funcionario funcionario;
		while (rs.next()) {
			cidade = new Cidade();
			cidade.setNomeCidade(rs.getString("cidadenomecidade"));
			categoriaFuncionario = new CategoriaFuncionario();
			categoriaFuncionario.setIdCategoriaFuncionario(rs.getInt("catfuncid"));
			categoriaFuncionario.setNomeCategoraiFuncionario(rs.getString("catfuncnome"));
			funcionario = new Funcionario();
			funcionario.setIdUsuario(rs.getInt("funcid"));
			funcionario.setNomeUsuario(rs.getString("colnome"));
			funcionario.setCategoriaFuncionario(categoriaFuncionario);
			funcionario.setCidadeUsuario(cidade);
			listaFuncionario.add(funcionario);
		}

		return listaFuncionario;
	}
	
	public List<Funcionario> pesquisarlistaFuncionarioCodigo(Funcionario funcionario) throws SQLException {
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		StringBuilder st = new StringBuilder();
		st.append("select funcid, funcnome, catfuncnome, cidadenomecidade from funcionario, cidade, categoriafuncionario "
				+ "where (categoriafuncionario_catfuncid = categoriafuncionario.catfuncid) " + "and (cidadeid = funcionario.cidade_cidadeid)"
				+ " and funcid = ?; ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setInt(1, funcionario.getIdUsuario());
		ResultSet rs = ps.executeQuery();
		Cidade c = null;
		CategoriaFuncionario categoriaFuncionario = null;
		while (rs.next()) {
			c = new Cidade();
			c.setNomeCidade(rs.getString("cidadenomecidade"));
			categoriaFuncionario = new CategoriaFuncionario();
			categoriaFuncionario.setNomeCategoraiFuncionario(rs.getString("catfuncnome"));
			funcionario = new Funcionario();
			funcionario.setIdUsuario(rs.getInt("funcid"));
			funcionario.setNomeUsuario(rs.getString("funcnome"));
			funcionario.setCidadeUsuario(c);
			funcionario.setCategoriaFuncionario(categoriaFuncionario);
			listaFuncionario.add(funcionario);
		}

		return listaFuncionario;

	}
	
	public List<Funcionario> pesquisarlistaFuncionarioNome(Funcionario funcionario) throws SQLException {
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		StringBuilder st = new StringBuilder();
		st.append("select funcid, funcnome, catfuncnome, cidadenomecidade from funcionario, cidade, categoriafuncionario "
				+ "where (categoriafuncionario_catfuncid = categoriafuncionario.catfuncid) " + "and (cidadeid = funcionario.cidade_cidadeid)"
				+ " and funcnome ilike ?; ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setString(1, "%"+funcionario.getNomeUsuario()+"%");
		ResultSet rs = ps.executeQuery();
		Cidade c = null;
		CategoriaFuncionario categoriaFuncionario = null;
		while (rs.next()) {
			c = new Cidade();
			c.setNomeCidade(rs.getString("cidadenomecidade"));
			categoriaFuncionario = new CategoriaFuncionario();
			categoriaFuncionario.setNomeCategoraiFuncionario(rs.getString("catfuncnome"));
			funcionario = new Funcionario();
			funcionario.setIdUsuario(rs.getInt("funcid"));
			funcionario.setNomeUsuario(rs.getString("funcnome"));
			funcionario.setCidadeUsuario(c);
			funcionario.setCategoriaFuncionario(categoriaFuncionario);
			listaFuncionario.add(funcionario);
		}

		return listaFuncionario;

	}
	
	public List<Funcionario> pesquisarListaFuncionarioCidade(Cidade cidade) throws SQLException {
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		StringBuilder st = new StringBuilder();
		st.append(
				"select funcid, funcnome, catfuncnome, cidadenomecidade from funcionario inner join categoriafuncionario on (categoriafuncionario_catfuncid = categoriafuncionario.catfuncid ) inner join cidade on (cidadeid = funcionario.cidade_cidadeid)\r\n"
						+ "where cidadenomecidade ilike ?; ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setString(1, "%" + cidade.getNomeCidade() + "%");
		ResultSet rs = ps.executeQuery();

		Funcionario funcionario = null;
		Cidade c = null;
		CategoriaFuncionario categoriaFuncionario = null;
		while (rs.next()) {
			c = new Cidade();
			c.setNomeCidade(rs.getString("cidadenomecidade"));
			categoriaFuncionario = new CategoriaFuncionario();
			categoriaFuncionario.setNomeCategoraiFuncionario(rs.getString("catfuncnome"));
			funcionario = new Funcionario();
			funcionario.setIdUsuario(rs.getInt("funcid"));
			funcionario.setNomeUsuario(rs.getString("funcnome"));
			funcionario.setCidadeUsuario(c);
			funcionario.setCategoriaFuncionario(categoriaFuncionario);
			listaFuncionario.add(funcionario);
		}


		return listaFuncionario;
	}
	
	public List<Funcionario> pesquisarListaFuncionarioCoategoria(CategoriaFuncionario categoriaFuncionario) throws SQLException {
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		StringBuilder st = new StringBuilder();
		st.append(
				"select funcid, funcnome, catfuncnome, cidadenomecidade from funcionario inner join categoriafuncionario on (categoriafuncionario_catfuncid = categoriafuncionario.catfuncid ) inner join cidade on (cidadeid = funcionario.cidade_cidadeid)\r\n"
						+ "where catfuncnome ilike ?; ");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setString(1, "%" + categoriaFuncionario.getNomeCategoraiFuncionario() + "%");
		ResultSet rs = ps.executeQuery();

		Funcionario funcionario = null;
		Cidade c = null;
		
		while (rs.next()) {
			c = new Cidade();
			c.setNomeCidade(rs.getString("cidadenomecidade"));
			categoriaFuncionario = new CategoriaFuncionario();
			categoriaFuncionario.setNomeCategoraiFuncionario(rs.getString("catfuncnome"));
			funcionario = new Funcionario();
			funcionario.setIdUsuario(rs.getInt("funcid"));
			funcionario.setNomeUsuario(rs.getString("funcnome"));
			funcionario.setCidadeUsuario(c);
			funcionario.setCategoriaFuncionario(categoriaFuncionario);
			listaFuncionario.add(funcionario);
		}
		return listaFuncionario;
	}


}
