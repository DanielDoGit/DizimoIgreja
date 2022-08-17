package telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import comum.EjetaException;
import comum.PropriedadesShell;

public abstract class WindowCategoriaFuncionario {

	protected Display display;
	protected Shell shell;
	protected Text text;
	protected Text text_1;
	protected Button btnLimpar, btnGravar;
	
	public Shell getShell() {
		return this.shell;
	}

	public static void main(String args[]) {
		try {

			WindowCategoriaFuncionario shell = new WindowCategoriaFuncionario() {
			};
			shell.createContents();
			shell.open();

		} catch (Exception e) {
			new EjetaException(e);
		}
	}

	public void open() {

		display = Display.getDefault();
		shell.open();
		shell.setLocation(PropriedadesShell.centralizarShell(shell, display));
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shell = new Shell(SWT.MIN | SWT.CLOSE);
		shell.setText("Categoria Funcionário");

		shell.setSize(468, 179);

		btnGravar = new Button(shell, SWT.NONE);
		btnGravar.setBounds(344, 102, 75, 25);
		btnGravar.setText("Gravar");

		btnLimpar = new Button(shell, SWT.NONE);
		btnLimpar.setBounds(250, 102, 75, 25);
		btnLimpar.setText("Limpar");

		text = new Text(shell, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(184, 10, 76, 21);

		Label lblCdigo = new Label(shell, SWT.NONE);
		lblCdigo.setBounds(123, 13, 55, 15);
		lblCdigo.setText("Código");

		Label lblDescrio = new Label(shell, SWT.NONE);
		lblDescrio.setBounds(10, 60, 55, 15);
		lblDescrio.setText("Descrição");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text_1.setBounds(71, 60, 348, 21);
		

	}
}
