package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		st.append("insert into paroquia values(");
		st.append("?, ");
		st.append("?, ");
		st.append("?, ");
		st.append("?, ");
		st.append("?, ");
		st.append("?, ");
		st.append("?, ");
		st.append("?, ");
		st.append("?, ");
		st.append("?, ");
		st.append("?, ");
		st.append("?);");
	
		ps = con.prepareStatement(st.toString());
		ps.setInt(1, po.getIndice());
		ps.setString(2, po.getNomeFantasia());
		ps.setString(3, po.getRazaoSocial());
		ps.setString(4, po.getCnpj());
		ps.setString(5, po.getTelefone());
		ps.setString(6, po.getCelular());
		ps.setString(7, po.getContatos());
		ps.setString(8, po.getEndereco());
		ps.setInt(9, po.getNumeroLogradouro());
		ps.setString(10, po.getCep());
		ps.setString(11, po.getBairro());
		ps.setInt(12, po.getCidade().getIdCidade());
		ps.executeUpdate();
	}
	
	public void editar(Paroquia po) throws SQLException {
		StringBuilder st = new StringBuilder();
		st.append("update paroquia set ");
		st.append("paroNomefantasia=?, ");
		st.append("paroRazaoSocial=?, " );
		st.append("paroCnpj=?, ");
		st.append("paroTelef=?, ");
		st.append("paroCel=?, ");
		st.append("paroContatos=?, ");
		st.append("paroEndereco=?, ");
		st.append("paroNumEndereco=?, ");
		st.append("paroCep=?, ");
		st.append("paroBairro=?, ");
		st.append("paroCidade_fk=?");
		st.append(" where paroId=?;");
		System.out.println(st.toString());
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
		ps.setInt(11, po.getCidade().getIdCidade());
		ps.setInt(12, po.getIndice());
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
