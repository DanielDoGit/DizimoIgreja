package telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;

public class WindowLoginColetor {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Display display;

	public void open() {
		display = Display.getCurrent();
		
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
		shell.setSize(293, 206);
		shell.setText("Tela de Login Coletor");
		shell.setLayout(null);
		    
		shell.setLocation(PropriedadesShell.centralizarShell(shell, display));
		
		Label lblLogin = new Label(shell, SWT.NONE);
		lblLogin.setBounds(10, 28, 42, 15);
		lblLogin.setText("Login");
		
		Label lblSenha = new Label(shell, SWT.NONE);
		lblSenha.setBounds(10, 64, 42, 15);
		lblSenha.setText("Senha");
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setBounds(56, 25, 188, 21);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(56, 61, 188, 21);
		
		Button btnAcessar = new Button(shell, SWT.NONE);
		btnAcessar.setBounds(169, 112, 75, 25);
		btnAcessar.setText("Acessar");
		btnAcessar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (!text.getText().isEmpty() && !text_1.getText().isEmpty() ) {
					
					try {
						AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
						if(AutenticadorUsuario.isAuthentiquedUserColetor(text.getText(), text_1.getText())) {
							
							shell.dispose();
							new WindowPrincipalAction();
							Inicial.fechaconexao();
						}else {
							MessageBox dialog =
								    new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.OK );
								dialog.setText("Mensagem");
								dialog.setMessage("Este usuário não está cadastrado ou está incorreto.");
								dialog.open();
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
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
		btnLimpar.setBounds(58, 112, 75, 25);
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
