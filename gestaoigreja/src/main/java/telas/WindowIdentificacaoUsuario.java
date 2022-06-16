package telas;

import org.eclipse.swt.widgets.Dialog;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class WindowIdentificacaoUsuario extends Dialog {

	protected Object result;
	protected Shell shlBoasVindasAo;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public WindowIdentificacaoUsuario(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");	
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		Display display = getParent().getDisplay();
		createContents();
		shlBoasVindasAo.open();
		shlBoasVindasAo.layout();
		
//		org.eclipse.swt.widgets.Monitor primary = display.getPrimaryMonitor();
//		org.eclipse.swt.graphics.Rectangle bounds = primary.getBounds();
//	    org.eclipse.swt.graphics.Rectangle rect = shlBoasVindasAo.getBounds();
//	   
//	    
//	    int x = bounds.x + (bounds.width - rect.width) / 2;
//	    int y = bounds.y + (bounds.height - rect.height) / 2;
//	    
//	    
	    shlBoasVindasAo.setLocation(PropriedadesShell.centralizarShell(shlBoasVindasAo, display));
		

		while (!shlBoasVindasAo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlBoasVindasAo = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlBoasVindasAo.setModified(true);
		shlBoasVindasAo.setSize(339, 182);
		shlBoasVindasAo.setText("Boas Vindas ao Sistema ! ");
		
		Label lblVoc = new Label(shlBoasVindasAo, SWT.NONE);
		lblVoc.setBounds(136, 36, 55, 15);
		lblVoc.setText("Você é ?");
		
		Button btnNewButton = new Button(shlBoasVindasAo, SWT.NONE);
		btnNewButton.setBounds(49, 81, 75, 25);
		btnNewButton.setText("Funcionário");
		btnNewButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				shlBoasVindasAo.dispose();
				new WindowLoginFuncionario().open();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnNewButton_1 = new Button(shlBoasVindasAo, SWT.NONE);
		btnNewButton_1.setBounds(192, 81, 75, 25);
		btnNewButton_1.setText("Coletor");
		btnNewButton_1.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				shlBoasVindasAo.dispose();
				new WindowLoginColetor().open();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

	}
}
