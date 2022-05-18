package dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import comum.ColetaPropriedades;

public class FabricaConexoes {
	
	private Connection con;

	public FabricaConexoes(ColetaPropriedades col) throws SQLException {
		con = DriverManager.getConnection(col.getUrl()+col.getNomeDoBanco(),col.getUsuario(),col.getSenha());
	}

}
