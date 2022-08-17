package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import comum.ColetaPropriedades;

public class FabricaConexoes {
	
	private static Connection con;

	public FabricaConexoes(ColetaPropriedades col) throws SQLException {
		con = DriverManager.getConnection(
			  col.getUrl()+col.getNomeDoBanco(),
			  col.getUsuario(),
			  col.getSenha());
		con.setAutoCommit(true);
	}

	public Connection getCon() {
		return con;
	}
	
	public static void fechaConexao() throws SQLException {
		if (con != null) {
			FabricaConexoes.con.close();
		}
	}
	
	

}
