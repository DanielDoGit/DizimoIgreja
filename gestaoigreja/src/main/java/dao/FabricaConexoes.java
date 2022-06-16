package dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import comum.ColetaPropriedades;

public class FabricaConexoes {
	
	private static Connection con;

	public FabricaConexoes(ColetaPropriedades col) throws SQLException {
		con = DriverManager.getConnection(
			  col.getUrl()+col.getNomeDoBanco(),
			  col.getUsuario(),
			  col.getSenha());
		PreparedStatement ps = con.prepareStatement("use gestaoigreja");
		ps.executeUpdate();
	}

	public Connection getCon() {
		return con;
	}
	
	public void fechaConexao() throws SQLException {
		this.con.close();
	}
	
	

}
