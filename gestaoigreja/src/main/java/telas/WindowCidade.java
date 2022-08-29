package telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import comum.PropriedadesShell;

public abstract class WindowCidade {

	protected Shell shell;
	protected Text text;
	protected Text text_1;
	protected Text text_2;
	protected Button btnGravar;
	protected Button btnLimpar;

	public static void main(String[] args) {
		try {
			WindowCidade window = new WindowCidade() {
			};
			window.createContents();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();

		shell.setLocation(PropriedadesShell.centralizarShell(shell, display));
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
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell(SWT.MIN | SWT.CLOSE);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		shell.setSize(450, 202);
		shell.setText("Cidade");

		Label lblCdigo = new Label(shell, SWT.NONE);
		lblCdigo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblCdigo.setBounds(134, 13, 50, 15);
		lblCdigo.setText("Código");

		text = new Text(shell, SWT.BORDER);
		text.setEnabled(false);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setBounds(186, 10, 76, 21);

		Label lblNomeCidade = new Label(shell, SWT.NONE);
		lblNomeCidade.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNomeCidade.setBounds(35, 59, 51, 15);
		lblNomeCidade.setText("Nome");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text_1.setBounds(92, 56, 319, 21);

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setTextLimit(2);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text_2.setBounds(92, 93, 82, 21);

		Label lblUf = new Label(shell, SWT.NONE);
		lblUf.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblUf.setText("UF");
		lblUf.setBounds(35, 96, 26, 15);

		btnGravar = new Button(shell, SWT.NONE);
		btnGravar.setBounds(336, 121, 75, 25);
		btnGravar.setText("Gravar");

		btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(236, 121, 75, 25);
		btnLimpar.setText("Limpar");
		

	}
}
