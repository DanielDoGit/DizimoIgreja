package telas;

import java.awt.Rectangle;

import javax.management.monitor.Monitor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class LoginColetorActionSWT {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Display display;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginColetorActionSWT window = new LoginColetorActionSWT();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
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
		shell.setSize(450, 300);
		shell.setText("Tela de Login");
		shell.setLayout(null);
		
		
			org.eclipse.swt.widgets.Monitor primary = display.getPrimaryMonitor();
			org.eclipse.swt.graphics.Rectangle bounds = primary.getBounds();
		    org.eclipse.swt.graphics.Rectangle rect = shell.getBounds();
		   
		    
		    int x = bounds.x + (bounds.width - rect.width) / 2;
		    int y = bounds.y + (bounds.height - rect.height) / 2;
		    
		    
		    shell.setLocation(x, y);
		
		Label lblLogin = new Label(shell, SWT.NONE);
		lblLogin.setBounds(35, 51, 55, 15);
		lblLogin.setText("Login");
		
		Label lblSenha = new Label(shell, SWT.NONE);
		lblSenha.setBounds(35, 109, 55, 15);
		lblSenha.setText("Senha");
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setBounds(96, 51, 268, 21);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(96, 109, 268, 21);
		
		Button btnAcessar = new Button(shell, SWT.NONE);
		btnAcessar.setBounds(301, 197, 75, 25);
		btnAcessar.setText("Acessar");
		btnAcessar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});;
		
		Button btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(198, 197, 75, 25);
		btnLimpar.setText("Limpar");
		btnLimpar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
				text.setText("");
				text_1.setText("");
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}
}
