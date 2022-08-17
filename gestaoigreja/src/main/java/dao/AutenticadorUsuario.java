package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Coletor;
import beans.Funcionario;
import beans.PermissaoColetor;
import beans.PermissaoFuncionario;
import beans.Permissoes;
import beans.Usuario;
import comum.EjetaException;

public class AutenticadorUsuario {

	private static Usuario usuario;
	private static Connection con;
	private static boolean isColetor = false;
	

	public static boolean isColetor() {
		return isColetor;
	}

	public static void setColetor(boolean isColetor) {
		AutenticadorUsuario.isColetor = isColetor;
	}

	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		AutenticadorUsuario.con = con;
	}

	public static Usuario getusuario() {
		return usuario;
	}
	
	public static void setUsuario(Usuario usuario) {
		AutenticadorUsuario.usuario = usuario;
	}

	public static boolean isAuthentiquedUserFuncionario(String login, String senha) {

		try {
			
			if (con == null) {
				throw new NullPointerException("A Conexão esta nula ");
			}
			PreparedStatement ps = con.prepareStatement(
					"select funcLogin, funcSenha, funcid, funcnome from funcionario where funcLogin=? and funcSenha=?;");
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				usuario = new Funcionario();
				usuario.setLoginUsuario(rs.getString("funcLogin"));
				usuario.setSenhaUsuario(rs.getString("funcSenha"));
				usuario.setNomeUsuario(rs.getString("funcnome"));
				usuario.setIdUsuario(rs.getInt("funcid"));
			}

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}

		if (usuario != null && login.equals(usuario.getLoginUsuario()) && senha.equals(usuario.getSenhaUsuario())) {
			isColetor = false;
			return true;
		} else {
			return false;
		}

	}

	public static boolean isAuthentiquedUserColetor(String login, String senha) {

		try {
			
			if (con == null) {
				throw new NullPointerException("A Conexão esta nula ");
			}
			PreparedStatement ps = con
					.prepareStatement("select colLogin,colSenha, colid, colnome from coletor where colLogin=? and colSenha=?;");
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				usuario = new Coletor();
				usuario.setLoginUsuario(rs.getString("colLogin"));
				usuario.setSenhaUsuario(rs.getString("colSenha"));
				usuario.setNomeUsuario(rs.getString("colnome"));
				usuario.setIdUsuario(rs.getInt("colid"));
			}

			con.close();

		} catch (Exception e) {

			new EjetaException(e);
		}

		if (usuario != null && login.equals(usuario.getLoginUsuario()) && senha.equals(usuario.getSenhaUsuario())) {
			isColetor = true;
			return true;
		} else {
			return false;
		}

	}

	public void deletePermissoesColetor(Coletor coletor) throws Exception {

		StringBuilder st1 = new StringBuilder();
		st1.append("delete from permissaocoletor where colid_fk = ?;");
		PreparedStatement ps = con.prepareStatement(st1.toString());
		ps.setInt(1, coletor.getIdUsuario());
		ps.executeUpdate();

	}

	public List<Permissoes> popularPermissoes() throws Exception {
		List<Permissoes> listapermissao = new ArrayList<Permissoes>();
		Permissoes permissoes = null;
		StringBuilder st = new StringBuilder();
		st.append("select * from permissoes");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			permissoes = new Permissoes();
			permissoes.setId(rs.getInt(1));
			permissoes.setNomepermissao(rs.getString(2));
			listapermissao.add(permissoes);
		}
		con.close();
		return listapermissao;

	}

	public boolean verificarPermissaoColetor(Usuario coletor, Permissoes permissao) throws SQLException {
		Permissoes permissoes = new Permissoes();

		if (coletor != null) {
			StringBuilder st = new StringBuilder();
			st.append(
					"select permiid_fk, perminome from permissaocoletor inner join permissoes on (permiid_fk = permiid) where colid_fk= ? and permiid_fk = ?;");
			PreparedStatement ps = con.prepareStatement(st.toString());
			ps.setInt(1, coletor.getIdUsuario());
			ps.setInt(2, permissao.getId());
			ResultSet seResultSet = ps.executeQuery();

			while (seResultSet.next()) {
				permissoes.setId(seResultSet.getInt("permiid_fk"));
				permissoes.setNomepermissao(seResultSet.getString("perminome"));
			}

		}

		if (permissoes.getId() != null && (permissoes.getNomepermissao().equals(permissao.getNomepermissao()))) {
			return true;
		} else {
			return false;
		}
	}

	public PermissaoColetor recuperarPermissoesColetor(PermissaoColetor permissaoColetor) throws Exception {
		Permissoes permissoes = null;
		permissaoColetor.setListaPermissoes(new ArrayList<Permissoes>());

		if (permissaoColetor.getColetor() != null) {
			StringBuilder st = new StringBuilder();
			st.append(
					"select permiid_fk from permissaocoletor inner join permissoes on (permiid_fk = permiid) where colid_fk= ? ;");
			PreparedStatement ps = con.prepareStatement(st.toString());
			ps.setInt(1, permissaoColetor.getColetor().getIdUsuario());
			ResultSet seResultSet = ps.executeQuery();

			while (seResultSet.next()) {
				permissoes = new Permissoes();
				permissoes.setId(seResultSet.getInt("permiid_fk"));
				permissaoColetor.getListaPermissoes().add(permissoes);
			}

			con.close();

		} else {
			con.close();
			throw new NullPointerException("O coletor está nulo");
		}

		return permissaoColetor;
	}

	public void inputpermissaoColetor(PermissaoColetor pc) throws Exception {

		PreparedStatement ps = null;
		StringBuilder st1 = new StringBuilder();
		st1.append(
				"insert into permissaocoletor values ((select max(permissaocoletorid)+1 from permissaocoletor), ?, ?);");
		ps = con.prepareStatement(st1.toString());
		for (int i = 0; i < pc.getListaPermissoes().size(); i++) {
			ps.setInt(1, pc.getListaPermissoes().get(i).getId());
			ps.setInt(2, pc.getColetor().getIdUsuario());
			ps.executeUpdate();
			ps.clearParameters();
		}
	}

	public void inputpermissaoFuncionario(PermissaoFuncionario pf) throws Exception {

		PreparedStatement ps = null;
		StringBuilder st1 = new StringBuilder();
		st1.append("insert into permissaofuncionario values (?, ?);");
		ps = con.prepareStatement(st1.toString());
		for (int i = 0; i < pf.getListaPermissoes().size(); i++) {
			ps.setInt(1, pf.getListaPermissoes().get(i).getId());
			ps.setInt(2, pf.getFuncionario().getIdUsuario());
			ps.executeUpdate();
			ps.clearParameters();
		}
	}

	public boolean verificarPermissaoFuncionario(Usuario funcionario, Permissoes permissao) throws SQLException {
		Permissoes permissoes = new Permissoes();
		
		if (funcionario != null) {
			StringBuilder st = new StringBuilder();
			st.append(
					"select permissoes_permiid, perminome from permissaofuncionario inner join permissoes on (permissoes_permiid = permissoes.permiid) where funcionario_funcid = ? and permissoes_permiid = ?;");
			PreparedStatement ps = con.prepareStatement(st.toString());
			ps.setInt(1, funcionario.getIdUsuario());
			ps.setInt(2, permissao.getId());
			ResultSet seResultSet = ps.executeQuery();

			while (seResultSet.next()) {
				permissoes.setId(seResultSet.getInt("permissoes_permiid"));
				permissoes.setNomepermissao(seResultSet.getString("perminome"));
				
			}

		}

		if (permissoes.getId() != null && (permissoes.getNomepermissao().equals(permissao.getNomepermissao()))) {
			return true;
		} else {
			return false;
		}
	}
	
	public PermissaoFuncionario recuperarPermissoesFuncionario(PermissaoFuncionario permissaoFuncionario) throws Exception {
		Permissoes permissoes = null;
		permissaoFuncionario.setListaPermissoes(new ArrayList<Permissoes>());

		if (permissaoFuncionario.getFuncionario() != null) {
			StringBuilder st = new StringBuilder();
			st.append(
					"select permissoes_permiid from permissaofuncionario inner join permissoes on (permissoes_permiid = permiid) where funcionario_funcid = ? ;");
			PreparedStatement ps = con.prepareStatement(st.toString());
			ps.setInt(1, permissaoFuncionario.getFuncionario().getIdUsuario());
			ResultSet seResultSet = ps.executeQuery();

			while (seResultSet.next()) {
				permissoes = new Permissoes();
				permissoes.setId(seResultSet.getInt("permissoes_permiid"));
				permissaoFuncionario.getListaPermissoes().add(permissoes);
			}

			con.close();

		} else {
			con.close();
			throw new NullPointerException("O coletor está nulo");
		}

		return permissaoFuncionario;
	}
	
	public void deletePermissoesFuncionario(Funcionario funcionario) throws Exception {

		StringBuilder st1 = new StringBuilder();
		st1.append("delete from permissaofuncionario where funcionario_funcid = ?;");
		PreparedStatement ps = con.prepareStatement(st1.toString());
		ps.setInt(1, funcionario.getIdUsuario());
		ps.executeUpdate();

	}
}
