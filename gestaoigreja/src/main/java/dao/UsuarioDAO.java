package dao;

import java.sql.SQLException;

import entidades.Usuario;

public abstract class UsuarioDAO {
	
	public abstract boolean AcessarSistema(Usuario u) throws SQLException;
	public abstract boolean ConsultarPermissao(Usuario u) throws SQLException;
}
