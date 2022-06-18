package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cidade;
import beans.Paroquia;

public class ParoquiaDao {
	
	private static Connection con;
	private PreparedStatement ps;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public void cadastrar(Paroquia po) throws SQLException {
		
		StringBuilder st = new StringBuilder();
		st.append("insert into paroquia values( ");
		st.append("?,");
		st.append("?,");
		st.append("?,");
		st.append("?,");
		st.append("?,");
		st.append("?,");
		st.append("?,");
		st.append("?,");
		st.append("?,");
		st.append("?)");
	
		ps = con.prepareStatement(st.toString());
		ps.setString(1, po.getNomeFantasia());
		ps.setString(2, po.getRazaoSocial());
		ps.setString(3, po.getCnpj());
		ps.setString(4, po.getTelefone());
		ps.setString(5, po.getCelular());
		ps.setString(6, po.getContatos());
		ps.setString(7, po.getEndereco());
		ps.setInt(8, po.getNumeroLogradouro());
		ps.setString(9, po.getCep());
		ps.setString(10, po.getBairro());
		ps.executeUpdate();
	}
	
	public void editar(Paroquia po) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("update cidade set ");
		st.append("paroNomefantasia=?,");
		st.append("paroRazaoSocial=?," );
		st.append("paroCnpj=?,");
		st.append("paroTelef=?,");
		st.append("paroCel=?,");
		st.append("paroContatos=?,");
		st.append("paroEndereco=?,");
		st.append("paroNumEndereco=?,");
		st.append("paroCep=?,");
		st.append("paroBairro=?,");
		st.append("where paroId=?");
		ps = con.prepareStatement(st.toString());
		ps.setString(1, po.getNomeFantasia());
		ps.setString(2, po.getRazaoSocial());
		ps.setString(3, po.getCnpj());
		ps.setString(4, po.getTelefone());
		ps.setString(5, po.getCelular());
		ps.setString(6, po.getContatos());
		ps.setString(7, po.getEndereco());
		ps.setInt(8, po.getNumeroLogradouro());
		ps.setString(9, po.getCep());
		ps.setString(10, po.getBairro());
		ps.executeUpdate();
	}
		
	public Paroquia consultarParoquiaTodos() throws SQLException {
		
		ps = con.prepareStatement("select * from paroquia order by paroNomefantasia;");
		ResultSet rs = ps.executeQuery();
		CidadeDao cd = new CidadeDao();
		cd.setConnection(con);
		Paroquia p = null;
		while (rs.next()) {
			p = new Paroquia();
			p.setIndice(rs.getInt("paroId"));
			p.setNomeFantasia(rs.getString("paroNomeFantasia"));
			p.setRazaoSocial(rs.getString("paroRazaoSocial"));
			p.setCnpj(rs.getString("paroCnpj"));
			p.setTelefone(rs.getString("paroTelef"));
			p.setCelular(rs.getString("paroCel"));
			p.setContatos(rs.getString("paroContatos"));
			p.setEndereco(rs.getString("paroEndereco"));
			p.setNumeroLogradouro(rs.getInt("paroNumEndereco"));
			p.setCep(rs.getString("paroCep"));
			p.setBairro(rs.getString("paroBairro"));
			p.setCidade(cd.consultarCidadePorCodigo(rs.getInt("paroCidade_fk")));
		}
		return p;
		
	}
	

}
