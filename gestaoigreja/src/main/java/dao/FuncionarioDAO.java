package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comum.EjetaException;
import entidades.Funcionario;
import entidades.Usuario;

public class FuncionarioDAO  {

	private static Connection con;
	private PreparedStatement ps;
	private ResultSet resultSet;

	
	public void setConnectionFuncionarioDao(Connection con) {
		this.con = con;
	}


	


}
