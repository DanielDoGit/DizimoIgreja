package telas;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import comum.ColetaPropriedades;
import comum.EjetaException;
import dao.FabricaConexoes;

public class Inicial {

	public static void main(String[] args) {

		EjetaException.verificarexistenciaArquivo();
		try {
			startaPropertiesConnection();
			fechaconexao();
			WindowIdentificacaoUsuario identifica = new WindowIdentificacaoUsuario(new Shell(), SWT.PRIMARY_MODAL);
			identifica.open();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel se conectar ao banco de dados. Verifique sua conexão e tente novamente", "Ocorreu um erro", 0);
			new EjetaException(e);
		}

	}

	public static Connection startaPropertiesConnection() throws SQLException {
		ColetaPropriedades coletaPropriedades = new ColetaPropriedades();
		FabricaConexoes fabrica = new FabricaConexoes(coletaPropriedades);
		return fabrica.getCon();
	}

	public static void fechaconexao() {
		try {
			FabricaConexoes.fechaConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
	}

}
