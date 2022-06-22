package telas;

import java.sql.Connection;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import comum.ColetaPropriedades;
import dao.FabricaConexoes;
import stupidTests.Teste;

public class Inicial{


	public static void main(String[] args) {
		
		WindowIdentificacaoUsuario identifica =  new WindowIdentificacaoUsuario(new Shell(),SWT.PRIMARY_MODAL);
		identifica.open();
		
	//	new Inicial(new Shell(),SWT.PRIMARY_MODAL);
	}
	
	public static final Connection startaPropertiesConnection() throws SQLException {
		ColetaPropriedades coletaPropriedades = new ColetaPropriedades();
		FabricaConexoes fabrica = new FabricaConexoes(coletaPropriedades);
		return fabrica.getCon();
		
	}
	
	
}
