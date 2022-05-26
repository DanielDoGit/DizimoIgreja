package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import comum.EjetaException;
import entidades.Coletor;
import entidades.Funcionario;
import entidades.Usuario;

public class AutenticadorUsuario {
	
	private static Funcionario func;
	private static Coletor col;
	private Connection con;

	public Coletor getCol() {
		return col;
	}

	public void setCol(Coletor col) {
		this.col = col;
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Funcionario getFunc() {
		return func;
	}
	
	public boolean isAuthentiquedUserFuncionario(String login, String senha) {
		
		try {
			col = null;
			PreparedStatement ps = 
			con.prepareStatement("select funcLogin, funcSenha from Funcionario where funLogin=? and funcSenha=?;");
			 ps.setString(1, login);
			 ps.setString(2, senha);
			 ResultSet rs = ps.executeQuery();			
			
			 while (rs.next()) {
				 func = new Funcionario();
				 func.setLoginUsuario(rs.getString("funcLogin"));
				 func.setSenhaUsuario(rs.getString("funcSenha")); 
			}
			
			
			//con.commit();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (login.equals(func.getLoginUsuario()) && senha.equals(func.getSenhaUsuario()) && col == null){
			 return true;
		 }else {
			 return false;
		 }

	}
	
	
public boolean  isAuthentiquedUserColetor(String login, String senha) {
		
		try {
			func = null;
			PreparedStatement ps = 
			con.prepareStatement("select funcLogin,funcSenha from Coletor where funcLogin=? and funcSenha=?;");
			 ps.setString(1, login);
			 ps.setString(2, senha);
			 ResultSet rs = ps.executeQuery();			
			
			 while (rs.next()) {
				 col = new Coletor();
				 col.setLoginUsuario(rs.getString("funcLogin"));
				 col.setSenhaUsuario(rs.getString("funcSenha")); 
			}
			 
			 con.close();
			
			
			
			//con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
		
		 if (login.equals(col.getLoginUsuario()) && senha.equals(col.getSenhaUsuario()) && func == null){
			 return true;
		 }else {
			 return false;
		 }
		 	
	
	}
	
	



	public AutenticadorUsuario() {}
	
	
	
}
