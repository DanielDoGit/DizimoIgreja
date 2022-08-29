package movimentacoes;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import comum.PropriedadesShell;

import org.eclipse.swt.widgets.Button;

public abstract class Confirmacao {

	protected Shell shlConfirmao;
	protected Text text;
	protected Button btnConfirmar;


	public static void main(String[] args) {
		try {
			Confirmacao window = new Confirmacao() {
			};
			window.createContents();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
	
		shlConfirmao.open();
		shlConfirmao.setLocation(PropriedadesShell.centralizarShell(shlConfirmao, display));
		shlConfirmao.layout();
		while (!shlConfirmao.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlConfirmao = new Shell(SWT.CLOSE);
		shlConfirmao.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		shlConfirmao.setSize(392, 158);
		shlConfirmao.setText("Confirmação");
		
		text = new Text(shlConfirmao, SWT.BORDER | SWT.PASSWORD);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		text.setBounds(28, 40, 314, 21);
		
		Label lblDigiteSuaSenha = new Label(shlConfirmao, SWT.NONE);
		lblDigiteSuaSenha.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblDigiteSuaSenha.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDigiteSuaSenha.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblDigiteSuaSenha.setBounds(121, 10, 135, 20);
		lblDigiteSuaSenha.setText("Digite sua senha");
		
		btnConfirmar = new Button(shlConfirmao, SWT.NONE);
		btnConfirmar.setBounds(142, 78, 85, 35);
		btnConfirmar.setText("Confirmar");

	}
}
