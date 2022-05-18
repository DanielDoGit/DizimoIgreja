package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Funcionario;
import entidades.Usuario;

public class FuncionarioDAO implements UsuarioDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet resultSet;


	@Override
	public boolean AcessarSistema(Usuario u) throws SQLException {
		// TODO Auto-generated method stub
		Funcionario fun = new Funcionario();
		fun.setLogin(u.getLoginUsuario());
		fun.setSenha(u.getLoginUsuario());
		ps = con.prepareStatement("select ?,? from funcionario where funcLogin=? and funcSenha=?;");
		ps.setString(0, "funcLogin");
		ps.setString(1, "funcSenha");
		ps.setString(2, fun.getLogin());
		ps.setString(1, fun.getSenha());
		ps.executeUpdate();
		
		return false;
		
		
	}
	


}
