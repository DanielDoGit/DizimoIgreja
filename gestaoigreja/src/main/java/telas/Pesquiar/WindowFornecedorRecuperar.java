package telas.Pesquiar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import comum.PropriedadesShell;

public abstract class WindowFornecedorRecuperar {

	protected Shell shlPesquisar;
	protected Text text;
	protected Table table;
	protected TableItem ass[];
	protected Button btnPesquisar;
	protected Button btnEditar;
	protected Button btnExcluir;
	protected CCombo combo;
	protected TableColumn tblclmnNewColumn, tblclmnNomeDizimista, tblclmnUf;
	

	public Shell getShlPesquisar() {
		return shlPesquisar;
	}

	public void setShlPesquisar(Shell shlPesquisar) {
		this.shlPesquisar = shlPesquisar;
	}
	
	
	public void open() {
		Display display = Display.getDefault();
		
		shlPesquisar.open();
		shlPesquisar.setLocation(PropriedadesShell.centralizarShell(shlPesquisar, display));
		
		
		
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
		shlPesquisar.setModified(true);
		//shlPesquisar.setImage(SWTResourceManager.getImage("D:\\LIMA SOFTWARE\\Workspace\\workspace-eclipse2020\\ContatosBranch\\icones\\lupa.png"));
		shlPesquisar.setSize(771, 502);
		shlPesquisar.setText("Pesquisar");
		shlPesquisar.setLayout(null);
		shlPesquisar.layout();
		
		Label lblFiltro = new Label(shlPesquisar, SWT.NONE);
		lblFiltro.setBounds(32, 14, 55, 15);
		lblFiltro.setText("Filtro");
		
		text = new Text(shlPesquisar, SWT.BORDER);
		text.setBounds(146, 31, 349, 21);
		
		btnEditar = new Button(shlPesquisar, SWT.NONE);
		btnEditar.setBounds(595, 14, 62, 54);
		btnEditar.setText("Editar");
		
		
		btnExcluir = new Button(shlPesquisar, SWT.NONE);
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
		tblclmnNomeDizimista.setWidth(349);
		tblclmnNomeDizimista.setText("Fornecedor");
		
		TableColumn tblclmnUf = new TableColumn(table, SWT.NONE);
		tblclmnUf.setWidth(206);
		tblclmnUf.setText("Cidade");
				
		Label lblCampoDePesquisa = new Label(shlPesquisar, SWT.NONE);
		lblCampoDePesquisa.setBounds(146, 14, 62, 15);
		lblCampoDePesquisa.setText("Campo");
		
		combo = new CCombo(shlPesquisar, SWT.BORDER | SWT.READ_ONLY);
		combo.setListVisible(true);
		combo.setBounds(32, 31, 91, 21);
		combo.add("Código", 0);
		combo.add("Fornecedor",1);
		combo.add("Cidade",2);
		combo.select(1);
		
		btnPesquisar = new Button(shlPesquisar, SWT.NONE);
		btnPesquisar.setText("Pesquisar");
		btnPesquisar.setBounds(515, 14, 62, 54);
		
		
		

	}
}
