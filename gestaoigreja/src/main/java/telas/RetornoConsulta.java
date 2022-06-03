package telas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RetornoConsulta {

	protected Shell shlPesquisar;
	protected CCombo combo;
	private Text text;
	private Table table;

	
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RetornoConsulta window = new RetornoConsulta();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Shell getShlPesquisar() {
		return shlPesquisar;
	}

	public void setShlPesquisar(Shell shlPesquisar) {
		this.shlPesquisar = shlPesquisar;
	}
	
	public CCombo getCombo() {
		return combo;
	}

	public void setCombo(CCombo combo) {
		this.combo = combo;
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPesquisar.open();
		shlPesquisar.setLocation(PropriedadesShell.centralizarShell(shlPesquisar, display));
		
		Button btnEditar = new Button(shlPesquisar, SWT.NONE);
		btnEditar.setText("Editar");
		btnEditar.setBounds(515, 14, 62, 54);
		
		
		
		
		while (!shlPesquisar.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPesquisar = new Shell(SWT.MIN| SWT.CLOSE);
		shlPesquisar.setImage(SWTResourceManager.getImage("D:\\LIMA SOFTWARE\\Workspace\\workspace-eclipse2020\\ContatosBranch\\icones\\lupa.png"));
		shlPesquisar.setModified(true);
		shlPesquisar.setSize(771, 502);
		shlPesquisar.setText("Pesquisar");
		shlPesquisar.setLayout(null);
		
		combo = new CCombo(shlPesquisar, SWT.BORDER | SWT.READ_ONLY);
		combo.setBounds(32, 31, 88, 21);
		shlPesquisar.layout();
		
		Label lblFiltro = new Label(shlPesquisar, SWT.NONE);
		lblFiltro.setBounds(32, 14, 55, 15);
		lblFiltro.setText("Filtro");
		
		text = new Text(shlPesquisar, SWT.BORDER);
		text.setBounds(146, 31, 349, 21);
		
		Button btnEditar = new Button(shlPesquisar, SWT.NONE);
		btnEditar.setBounds(595, 14, 62, 54);
		btnEditar.setText("Editar");
		
		Button btnExcluir = new Button(shlPesquisar, SWT.NONE);
		btnExcluir.setBounds(671, 14, 62, 54);
		btnExcluir.setText("Excluir");
		
		table = new Table(shlPesquisar, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(32, 97, 701, 352);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(143);
		tblclmnNewColumn.setText("Código");
		
		TableColumn tblclmnNomeDizimista = new TableColumn(table, SWT.NONE);
		tblclmnNomeDizimista.setWidth(420);
		tblclmnNomeDizimista.setText("Nome Cidade ");
		
		TableColumn tblclmnUf = new TableColumn(table, SWT.NONE);
		tblclmnUf.setWidth(135);
		tblclmnUf.setText("UF");
		
		Label lblCampoDePesquisa = new Label(shlPesquisar, SWT.NONE);
		lblCampoDePesquisa.setBounds(146, 14, 112, 15);
		lblCampoDePesquisa.setText("Campo de Pesquisa");

	}
}
