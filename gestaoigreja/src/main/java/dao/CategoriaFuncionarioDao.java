package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CategoriaFuncionario;

public class CategoriaFuncionarioDao {

	private Connection con;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public void inserircategoria(CategoriaFuncionario cf) throws Exception {
		StringBuilder st = new StringBuilder();
		st.append("insert into categoriafuncionario values (?,?)");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setInt(1, cf.getIdCategoriaFuncionario());
		ps.setString(2, cf.getNomeCategoraiFuncionario());
		ps.executeUpdate();
	}

	public void deletarcategoria(CategoriaFuncionario cf) throws Exception {
		StringBuilder st = new StringBuilder();
		st.append("delete from categoriafuncionario where catfuncid = ?");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setInt(1, cf.getIdCategoriaFuncionario());
		ps.executeUpdate();
	}

	public void editarcategoria(CategoriaFuncionario cf) throws Exception {
		StringBuilder st = new StringBuilder();
		st.append("update categoriafuncionario set catfuncnome = ? where catfuncid = ?");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setString(1, cf.getNomeCategoraiFuncionario());
		ps.setInt(2, cf.getIdCategoriaFuncionario());
		ps.executeUpdate();
	}

	public List<CategoriaFuncionario> pesquisarCategoriaPorNome(CategoriaFuncionario cf) throws Exception {
		List<CategoriaFuncionario> listacategorias = new ArrayList<CategoriaFuncionario>();
		StringBuilder st = new StringBuilder();
		st.append("select catfuncid, catfuncnome from categoriafuncionario where catfuncnome ilike ?");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setString(1, "%" + cf.getNomeCategoraiFuncionario() + "%");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			cf = new CategoriaFuncionario(rs.getInt(1), rs.getString(2));
			listacategorias.add(cf);
		}

		return listacategorias;
	}

	public List<CategoriaFuncionario> pesquisarCategoriaPorId(CategoriaFuncionario cf) throws Exception {
		List<CategoriaFuncionario> listacategorias = new ArrayList<CategoriaFuncionario>();
		StringBuilder st = new StringBuilder();
		st.append("select catfuncid, catfuncnome from categoriafuncionario where catfuncid = ?");
		PreparedStatement ps = con.prepareStatement(st.toString());
		ps.setInt(1, cf.getIdCategoriaFuncionario());
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			cf = new CategoriaFuncionario(rs.getInt("catfuncid"), rs.getString("catfuncnome"));
			System.out.println(cf.getIdCategoriaFuncionario());
			System.out.println(cf.getNomeCategoraiFuncionario());
			listacategorias.add(cf);
		}

		return listacategorias;
	}
	
	public CategoriaFuncionario retornaCategoriaFuncionarioMaximo() throws SQLException {
		PreparedStatement ps = con.prepareStatement("select max(catfuncid) from categoriaFuncionario;".toLowerCase());
		ResultSet rs = ps.executeQuery();
		
		CategoriaFuncionario c = null;
		while (rs.next()) {
			c = new CategoriaFuncionario();
			c.setIdCategoriaFuncionario(rs.getInt("max"));
		}
		c.setIdCategoriaFuncionario(c.getIdCategoriaFuncionario()+1);
		
		return c;
	}

}
