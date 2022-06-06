package telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.CLabel;

public class IdentificacaoParoquia {

	protected Shell shell;
	protected Display display;
	private Label lblNewLabel;
	private Text text;
	private Text text_1;
	private Text text_2;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			IdentificacaoParoquia window = new IdentificacaoParoquia();
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
		createContents();
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.MIN| SWT.CLOSE);
		shell.setSize(635, 478);
		shell.setText("Identificação Paroquia");
		shell.setLocation(PropriedadesShell.centralizarShell(shell, Display.getCurrent()));
		shell.setLayout(null);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(28, 38, 86, 15);
		lblNewLabel.setText("Nome Fantasia");
		
		text = new Text(shell, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text.setBounds(120, 35, 476, 21);
		
		Label lblRazoSocial = new Label(shell, SWT.NONE);
		lblRazoSocial.setBounds(28, 80, 86, 15);
		lblRazoSocial.setText("Razão Social");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_1.setBounds(120, 74, 476, 21);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_2.setBounds(120, 130, 179, 21);
		
		
		Label lblRazoSocial_1 = new Label(shell, SWT.NONE);
		lblRazoSocial_1.setText("CNPJ");
		lblRazoSocial_1.setBounds(28, 133, 691, 15);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(28, 114, 568, 2);
		
		
		
		
		
		
		

	}
}
