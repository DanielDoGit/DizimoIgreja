package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Funcionario;
import beans.Usuario;
import comum.EjetaException;

public class FuncionarioDAO  {

	private static Connection con;
	private PreparedStatement ps;
	private ResultSet resultSet;

	
	public void setConnectionFuncionarioDao(Connection con) {
		this.con = con;
	}


	


}
