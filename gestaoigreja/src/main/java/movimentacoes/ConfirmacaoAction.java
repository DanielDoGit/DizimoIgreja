package movimentacoes;

import java.sql.SQLException;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;

public class ConfirmacaoAction extends Confirmacao{
	
	private boolean isconfirmed = false;
	
	public boolean isIsconfirmed() {
		return isconfirmed;
	}

	public void setIsconfirmed(boolean isconfirmed) {
		this.isconfirmed = isconfirmed;
	}

	public ConfirmacaoAction() {
		super.createContents();
		tratarEventos();
	}
	
	public void tratarEventos() {
		btnConfirmar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!text.getText().isEmpty() ) {
					
					try {
						if (AutenticadorUsuario.verificarSenhaUsuário(text.getText())) {
							isconfirmed = true;
							shlConfirmao.dispose();
						}else {
							
							PropriedadesShell.mensagemDeErro("Senha invalida. Tente novamente");
							
						}
					} catch (SQLException e1) {
						PropriedadesShell.mensagemDeErro("Ocorreu um erro. Verifique o Log e tente novamente");
						new EjetaException(e1);
					}
					
				}else {
					PropriedadesShell.mensagemDeRetorno("Não é possível prosseguir, pois o campo está com espaços em branco");
				}
				
				
			}
		});
	}
	
	

}
