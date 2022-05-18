package comum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class ColetaPropriedades {

	private static String usuario;
	private static String senha;
	private static String url;
	private static String nomeDoBanco;
	private Properties propriedades;
	private boolean verificador;
	
	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		ColetaPropriedades.usuario = usuario;
	}

	public static String getSenha() {
		return senha;
	}

	public static void setSenha(String senha) {
		ColetaPropriedades.senha = senha;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ColetaPropriedades.url = url;
	}

	public static String getNomeDoBanco() {
		return nomeDoBanco;
	}

	public static void setNomeDoBanco(String nomeDoBanco) {
		ColetaPropriedades.nomeDoBanco = nomeDoBanco;
	}

	public Properties getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(Properties propriedades) {
		this.propriedades = propriedades;
	}

	public ColetaPropriedades()  {
		
		
		try {
			InputStream entrada = new FileInputStream("./FileConfiguration.tmp");
			propriedades = new Properties();
			propriedades.load(entrada);
			setUrl(propriedades.getProperty("url"));
			setNomeDoBanco(propriedades.getProperty("banco"));
			setUsuario(propriedades.getProperty("usuario"));
			setSenha(propriedades.getProperty("senha"));
			verificador = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			verificador = false;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			verificador = false;
		}
		
	}
	
	
}
