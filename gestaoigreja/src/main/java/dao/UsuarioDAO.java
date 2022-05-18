package dao;

import java.sql.SQLException;

import entidades.Usuario;

public interface UsuarioDAO {
	
	public abstract boolean AcessarSistema(Usuario u) throws SQLException;

}
