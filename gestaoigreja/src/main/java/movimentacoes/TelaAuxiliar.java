package movimentacoes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import comum.PropriedadesShell;

public abstract class TelaAuxiliar {

	protected Shell shlAuxiliar;
	protected Text text;
	protected Text text_1;
	protected Label lblValor;
	protected Button btnGravar;
	protected CLabel lblNewLabel;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaAuxiliar window = new TelaAuxiliar() {
			};
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getCurrent();
		
		shlAuxiliar.open();
		shlAuxiliar.setLocation(PropriedadesShell.centralizarShell(shlAuxiliar, display));
		shlAuxiliar.layout();
		while (!shlAuxiliar.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAuxiliar = new Shell();		
		shlAuxiliar.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		shlAuxiliar.setSize(436, 184);
		shlAuxiliar.setText("Auxiliar");
		
		text = new Text(shlAuxiliar, SWT.BORDER);
		text.setEnabled(false);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		text.setBounds(82, 33, 274, 21);
		
		Label lblNome = new Label(shlAuxiliar, SWT.NONE);
		lblNome.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblNome.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNome.setBounds(21, 36, 55, 15);
		lblNome.setText("Nome");
		
		lblNewLabel = new CLabel(shlAuxiliar, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblNewLabel.setImage(SWTResourceManager.getImage("./icones/lupa.png"));
		lblNewLabel.setBounds(362, 33, 45, 21);
		lblNewLabel.setText("");
		
		text_1 = new Text(shlAuxiliar, SWT.NONE);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		text_1.setBounds(82, 74, 119, 21);
		
		lblValor = new Label(shlAuxiliar, SWT.NONE);
		lblValor.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblValor.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblValor.setBounds(21, 80, 55, 15);
		lblValor.setText("Valor");
		
		btnGravar = new Button(shlAuxiliar, SWT.NONE);
		btnGravar.setBounds(322, 105, 75, 25);
		btnGravar.setText("Gravar");

	}
}
