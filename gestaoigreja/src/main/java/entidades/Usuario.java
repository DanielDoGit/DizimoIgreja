package entidades;

import java.sql.SQLException;

public abstract class Usuario {
	
	protected static String login = null;
	protected static String senha = null;
	
	public abstract void AcessarSistema(Usuario u) throws SQLException;

}
