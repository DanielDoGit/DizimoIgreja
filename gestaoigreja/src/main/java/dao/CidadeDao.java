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
	
	public void cadastrar(Cidade ci) throws SQLException {
		
		ps = con.prepareStatement("select max(cidadeId) cidadeId from cidade;");
		ResultSet rs = ps.executeQuery();
		
		Cidade c = null;
		while (rs.next()) {
			c = new Cidade();
			c.setIdCidade(rs.getInt("cidadeId"));
		}
		c.setIdCidade(c.getIdCidade()+1);
		
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
	
	public void consultarCidadeCodigo(Cidade c1) throws SQLException {
		
		ps = con.prepareStatement("select * from cidade where cidadeId = ?;");
		ps.setInt(1, c1.getIdCidade());
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Cidade c = new Cidade();
			c.setIdCidade(rs.getInt("cidadeId"));
			c.setNomeCidade(rs.getString("cidadeNomeCidade"));
			c.setUfCidade(rs.getString("cidadeUf"));
			listaCidade.add(c);
		}
		
	}

	public void consultarCidadeNome(Cidade c1) throws SQLException {

		ps = con.prepareStatement("select * from cidade where cidadeNomeCidade = ?;");
		ps.setString(1, c1.getNomeCidade());
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Cidade c = new Cidade();
			c.setIdCidade(rs.getInt("cidadeId"));
			c.setNomeCidade(rs.getString("cidadeNomeCidade"));
			c.setUfCidade(rs.getString("cidadeUf"));
			listaCidade.add(c);
		}

	}
	
	public void consultarCidadeUF(Cidade c1) throws SQLException {

		ps = con.prepareStatement("select * from cidade where cidadeUf = ?;");
		ps.setString(1, c1.getUfCidade());
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Cidade c = new Cidade();
			c.setIdCidade(rs.getInt("cidadeId"));
			c.setNomeCidade(rs.getString("cidadeNomeCidade"));
			c.setUfCidade(rs.getString("cidadeUf"));
			listaCidade.add(c);
		}

	}
	
	
	
}
