package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Coletor;
import beans.Funcionario;
import beans.Usuario;
import comum.EjetaException;

public class AutenticadorUsuario {
	
	private static Funcionario func;
	private static Coletor col;
	private static Connection con;

	public Coletor getCol() {
		return col;
	}

	public void setCol(Coletor col) {
		this.col = col;
	}
	
	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		AutenticadorUsuario.con = con;
	}

	public Funcionario getFunc() {
		return func;
	}
	
	public static boolean isAuthentiquedUserFuncionario(String login, String senha) {
		
		try {
			col = null;
			PreparedStatement ps = 
			con.prepareStatement("select funcLogin, funcSenha from funcionario where funcLogin=? and funcSenha=?;");
			 ps.setString(1, login);
			 ps.setString(2, senha);
			 ResultSet rs = ps.executeQuery();			
			
			 while (rs.next()) {
				 Funcionario.setLoginUsuario(rs.getString("funcLogin"));
				 Funcionario.setSenhaUsuario(rs.getString("funcSenha")); 
			}
			
			
			//con.commit();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
		
		if (login.equals(Funcionario.getLoginUsuario()) && senha.equals(Funcionario.getSenhaUsuario()) && col == null){
			 return true;
		 }else {
			 return false;
		 }

	}
	
	
public static boolean  isAuthentiquedUserColetor(String login, String senha) {
		
		try {
			func = null;
			PreparedStatement ps = 
			con.prepareStatement("select colLogin,colSenha from Coletor where colLogin=? and colSenha=?;");
			 ps.setString(1, login);
			 ps.setString(2, senha);
			 ResultSet rs = ps.executeQuery();			
			
			 while (rs.next()) {
				 col = new Coletor();
				 col.setLoginUsuario(rs.getString("colLogin"));
				 col.setSenhaUsuario(rs.getString("colSenha")); 
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
	
	



	public AutenticadorUsuario(String login, String senha) {}
	
	
	
}
