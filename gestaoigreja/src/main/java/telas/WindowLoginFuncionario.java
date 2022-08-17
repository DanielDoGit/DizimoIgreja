package telas;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import comum.ColetaPropriedades;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.FabricaConexoes;

public class WindowLoginFuncionario {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Display display;


	public static void main(String[] args) {
		new WindowLoginFuncionario().open();
	}
	
	public void open() {
		display = Display.getDefault();
		
		createContents(display);

		shell.open();
		shell.layout();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents(Display display) {
		shell = new Shell(display,  SWT.CLOSE | SWT.MIN | SWT.TITLE );
		shell.setSize(320, 218);
		shell.setText("Tela de Login Funcionario");
		shell.setLayout(null);
		    
		shell.setLocation(PropriedadesShell.centralizarShell(shell, display));
		
		Label lblLogin = new Label(shell, SWT.NONE);
		lblLogin.setBounds(23, 40, 42, 18);
		lblLogin.setText("Login");
		
		Label lblSenha = new Label(shell, SWT.NONE);
		lblSenha.setBounds(23, 82, 47, 18);
		lblSenha.setText("Senha");
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setBounds(71, 37, 183, 21);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(71, 79, 183, 21);
		
		Button btnAcessar = new Button(shell, SWT.NONE);
		btnAcessar.setBounds(206, 122, 75, 25);
		btnAcessar.setText("Acessar");
		btnAcessar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (!text.getText().isEmpty() && !text_1.getText().isEmpty() ) {
					
					try {
						AutenticadorUsuario.setCon(new FabricaConexoes(new ColetaPropriedades()).getCon());
						if(AutenticadorUsuario.isAuthentiquedUserFuncionario(text.getText(), text_1.getText())) {
							
							shell.dispose();
							new WindowPrincipalAction();
						}else {
							MessageBox dialog =
								    new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.OK );
								dialog.setText("Mensagem");
								dialog.setMessage("Este usuário não está cadastrado ou está incorreto.");
								dialog.open();
						}
					} catch (SQLException e1) {
						
						new EjetaException(e1);
					}
					
				
					
				}else {
					MessageBox dialog =
						    new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.OK );
						dialog.setText("Mensagem");
						dialog.setMessage("Verifique se os campos foram preenchidos corretamente !");
						dialog.open();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(125, 122, 75, 25);
		btnLimpar.setText("Limpar");
		btnLimpar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				text.setText("");
				text_1.setText("");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

	}
}
