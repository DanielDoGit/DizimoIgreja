package telas.Pesquiar;

import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import comum.PropriedadesShell;
import telas.Inicial;

public abstract class WindowRecuperarDizimista {

	protected Shell shell;
	protected Display display;
	protected Text text;
	protected Table table;
	protected Combo combo;
	protected ToolBar toolBar, toolBar_1, toolBar_1_1;
	protected ToolItem tltmPesquisar, tltmAceitar, tltmExcluir;
	protected TableColumn tblclmnCdigo, tblclmnNome, tblclmnAtivo, tblclmnCidadeDizimista;
	
	public static void main(String args[]) {
		try {
			
			WindowRecuperarDizimista shell = new WindowRecuperarDizimista() {
			};
			shell.createContents();
			shell.openShell();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openShell() {
		display = Display.getDefault();
		shell.setLocation(PropriedadesShell.centralizarShell(shell, display));
		shell.open();
		
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				Inicial.fechaconexao();
				display.sleep();
			}
		}
	}
	
	protected void createContents() {
		shell = new Shell (SWT.CLOSE | SWT.MIN  | SWT.TITLE);
		shell.setText("Pesquisar Dizimista");
		shell.setSize(838, 469);
		shell.setLayout(null);
		
		Label lblCampo = new Label(shell, SWT.NONE);
		lblCampo.setBounds(28, 21, 55, 15);
		lblCampo.setText("Filtro");
		
		combo = new Combo(shell, SWT.READ_ONLY);
		combo.add("Código", 0);
		combo.add("Nome",1);
		combo.add("Cidade",2);
		combo.select(1);
		combo.setBounds(28, 42, 136, 23);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(181, 42, 371, 21);
		
		Label lblCampo_1 = new Label(shell, SWT.NONE);
		lblCampo_1.setBounds(181, 21, 55, 15);
		lblCampo_1.setText("Campo");
		
		toolBar = new ToolBar(shell, SWT.FLAT | SWT.ARROW_DOWN);
		toolBar.setBounds(576, 10, 84, 62);
		
		tltmPesquisar = new ToolItem(toolBar, SWT.NONE);
		tltmPesquisar.setImage(SWTResourceManager.getImage("./icones/search.png"));
		tltmPesquisar.setText("Pesquisar");
		
		
		toolBar_1 = new ToolBar(shell, SWT.FLAT | SWT.ARROW_DOWN);
		toolBar_1.setBounds(669, 10, 62, 62);
		
		tltmAceitar = new ToolItem(toolBar_1, SWT.NONE);
		tltmAceitar.setImage(SWTResourceManager.getImage("./icones/update.png"));
		tltmAceitar.setText("Editar");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(28, 95, 776, 313);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		tblclmnCdigo = new TableColumn(table, SWT.NONE);
		tblclmnCdigo.setWidth(94);
		tblclmnCdigo.setText("Código");
		
		 tblclmnNome = new TableColumn(table, SWT.NONE);
		tblclmnNome.setWidth(280);
		tblclmnNome.setText("Nome ");
		
		tblclmnCidadeDizimista = new TableColumn(table, SWT.NONE);
		tblclmnCidadeDizimista.setWidth(165);
		tblclmnCidadeDizimista.setText("Cidade");
		
		tblclmnAtivo = new TableColumn(table, SWT.NONE);
		tblclmnAtivo.setWidth(231);
		tblclmnAtivo.setText("Ativo");
		
		toolBar_1_1 = new ToolBar(shell, SWT.FLAT | SWT.ARROW_DOWN);
		toolBar_1_1.setBounds(742, 10, 62, 62);
		
		tltmExcluir = new ToolItem(toolBar_1_1, SWT.NONE);
		tltmExcluir.setImage(SWTResourceManager.getImage("./icones/delete.png"));
		tltmExcluir.setText("Excluir");

	}
}
