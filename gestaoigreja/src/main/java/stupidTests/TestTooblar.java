package stupidTests;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.theme.ToolItemDrawData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.CLabel;

public class TestTooblar {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TestTooblar window = new TestTooblar();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
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
		shell = new Shell();
		shell.setModified(true);
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		
		
		GridData composicao = new GridData();
		
		
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.ARROW_RIGHT);
		toolBar.setBounds(63, 57, 174, 93);
		toolBar.setLocation(new Point(100, 50));
		toolBar.setLayoutData(composicao);
		
		
		
		ToolItem tltmPesquisar = new ToolItem(toolBar, SWT.RIGHT_TO_LEFT);
		tltmPesquisar.setImage(SWTResourceManager.getImage("C:\\Users\\danie\\eclipse-workspace\\gestaoigreja\\icones\\search.png"));
		tltmPesquisar.setText("Pesquisar");
		
		
		
		
		

	}
}
