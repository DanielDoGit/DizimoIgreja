package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

 
import beans.Cidade;

public class CidadeDao {

	private static Connection con;
	private PreparedStatement ps;
	private List<Cidade> listaCidade = new ArrayList<Cidade>();
	
	public void setConnection (Connection con) {
		this.con = con;
	}
	
	public static Connection getCon() {
		return CidadeDao.con;
	}
	
	public void cadastrar(Cidade ci) throws SQLException {
		
		ps = con.prepareStatement("insert into cidade values(?,?,?)");
		ps.setInt(1, ci.getIdCidade());
		ps.setString(2, ci.getNomeCidade());
		ps.setString(3, ci.getUfCidade());
		ps.executeUpdate();
	}
	
	public void editar(Cidade ci) throws SQLException {
		
		ps = con.prepareStatement("update cidade set cidadeNomeCidade=?, cidadeUf=? where cidadeId=? ");
		ps.setInt(3, ci.getIdCidade());
		ps.setString(1, ci.getNomeCidade());
		ps.setString(2, ci.getUfCidade());
		ps.executeUpdate();
	}
	
	public void excluir(Cidade ci) throws SQLException {

		ps = con.prepareStatement("delete from cidade where cidadeId=? ");
		ps.setInt(1, ci.getIdCidade());
		ps.executeUpdate();
	}
	
	public void consultarCidadeTodos() throws SQLException {
		
		ps = con.prepareStatement("select * from cidade;");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Cidade c = new Cidade();
			c.setIdCidade(rs.getInt("cidadeId"));
			c.setNomeCidade(rs.getString("cidadeNomeCidade"));
			c.setUfCidade(rs.getString("cidadeUf"));
			listaCidade.add(c);
		}
		
	}
	
	public Cidade consultarCidadePorCodigo(Integer codigo) throws SQLException {
		
		ps = con.prepareStatement("select * from cidade where cidadeId = ?;");
		ps.setInt(1, codigo);
		
		ResultSet rs = ps.executeQuery();
		Cidade c = null;
		while (rs.next()) {
			 c = new Cidade();
			c.setIdCidade(rs.getInt("cidadeId"));
			c.setNomeCidade(rs.getString("cidadeNomeCidade"));
			c.setUfCidade(rs.getString("cidadeUf"));
			//listaCidade.add(c);
		}
		return c;
	}

	public List<Cidade> consultarCidadeNome(Cidade c1) throws SQLException {

		ps = con.prepareStatement("select * from cidade where exists (cidadeNomeCidade = ?);");
		ps.setString(1, c1.getNomeCidade());
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Cidade c = new Cidade();
			c.setIdCidade(rs.getInt("cidadeId"));
			c.setNomeCidade(rs.getString("cidadeNomeCidade"));
			c.setUfCidade(rs.getString("cidadeUf"));
			listaCidade.add(c);
		}
		return listaCidade;

	}
	
	public List<Cidade> consultarCidadeUF(Cidade c1) throws SQLException {

		ps = con.prepareStatement("select * from cidade where cidadeUf = ? order by cidadeUf;");
		ps.setString(1, c1.getUfCidade());
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Cidade c = new Cidade();
			c.setIdCidade(rs.getInt("cidadeId"));
			c.setNomeCidade(rs.getString("cidadeNomeCidade"));
			c.setUfCidade(rs.getString("cidadeUf"));
			listaCidade.add(c);
		}
		return listaCidade;

	}
	
	public Cidade retornaCidadeIndiceMaximo() throws SQLException {
		ps = con.prepareStatement("select max(cidadeId) from cidade;");
		ResultSet rs = ps.executeQuery();
		
		Cidade c = null;
		while (rs.next()) {
			c = new Cidade();
			c.setIdCidade(rs.getInt("max(cidadeId)"));
		}
		c.setIdCidade(c.getIdCidade()+1);
		
		return c;
	}
	
	
	
}
