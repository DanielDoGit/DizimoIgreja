package telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import comum.PropriedadesShell;

public class WindowIdentificacaoUsuario extends Dialog {

	protected Object result;
	protected Shell shlBoasVindasAo;

	public WindowIdentificacaoUsuario(Shell parent, int style) {
		super(parent, style);
	
	}

	public Object open() {
		Display display = Display.getDefault();
		createContents();
		
		shlBoasVindasAo.open();
		shlBoasVindasAo.layout();
	    shlBoasVindasAo.setLocation(PropriedadesShell.centralizarShell(shlBoasVindasAo, display));
	
		while (!shlBoasVindasAo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	private void createContents() {
		shlBoasVindasAo = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlBoasVindasAo.setModified(true);
		shlBoasVindasAo.setSize(339, 182);
		shlBoasVindasAo.setText("Boas Vindas ao Sistema ! ");
		
		Label lblVoc = new Label(shlBoasVindasAo, SWT.NONE);
		lblVoc.setBounds(136, 36, 55, 15);
		lblVoc.setText("Você é ?");
		
		Button btnNewButton = new Button(shlBoasVindasAo, SWT.NONE);
		btnNewButton.setBounds(49, 81, 92 , 32);
		btnNewButton.setText("Funcionário");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlBoasVindasAo.dispose();
				new WindowLoginFuncionario().open();
				
			}
		}); 
		
		Button btnNewButton_1 = new Button(shlBoasVindasAo, SWT.NONE);
		btnNewButton_1.setBounds(192, 81, 75, 32);
		btnNewButton_1.setText("Coletor");
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlBoasVindasAo.dispose();
				new WindowLoginColetor().open();
				
			}
		});
			

	}
}
