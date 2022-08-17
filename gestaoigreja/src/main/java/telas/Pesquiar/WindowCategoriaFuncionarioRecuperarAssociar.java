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

public abstract class WindowCategoriaFuncionarioRecuperarAssociar {

	protected Shell shlPesquisar;
	protected Text text;
	protected Table table;
	protected TableItem ass[];
	protected Button btnEditar, btnPesquisar;
	protected CCombo combo;
	protected TableColumn tblclmnNewColumn, tblclmnNomeDizimista;
	
	public static void main(String[] args) {
		WindowCategoriaFuncionarioRecuperarAssociar ss = new WindowCategoriaFuncionarioRecuperarAssociar() {
		};
		try {
			ss.createContents();
			ss.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Shell getShlPesquisar() {
		return shlPesquisar;
	}

	public void setShlPesquisar(Shell shlPesquisar) {
		this.shlPesquisar = shlPesquisar;
	}
	

	public void open() throws Exception {
		Display display = Display.getDefault();
		
		shlPesquisar.open();
		shlPesquisar.setLocation(PropriedadesShell.centralizarShell(shlPesquisar, display));
		
		
		
		while (!shlPesquisar.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}

	
	protected void createContents()  {
		shlPesquisar = new Shell(SWT.MIN| SWT.CLOSE);
		shlPesquisar.setModified(true);
		shlPesquisar.setSize(701, 502);
		shlPesquisar.setText("Pesquisar");
		shlPesquisar.setLayout(null);
		shlPesquisar.layout();
		shlPesquisar.forceFocus();
		
		Label lblFiltro = new Label(shlPesquisar, SWT.NONE);
		lblFiltro.setBounds(32, 14, 55, 15);
		lblFiltro.setText("Filtro");
		
		text = new Text(shlPesquisar, SWT.BORDER);
		text.setBounds(146, 31, 338, 21);
		
		btnEditar = new Button(shlPesquisar, SWT.NONE);
		btnEditar.setBounds(590, 14, 62, 54);
		btnEditar.setText("Aceitar");
		
		table = new Table(shlPesquisar, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(32, 97, 633, 352);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
	
		tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(143);
		tblclmnNewColumn.setText("Código");
		
		tblclmnNomeDizimista = new TableColumn(table, SWT.NONE);
		tblclmnNomeDizimista.setWidth(486);
		tblclmnNomeDizimista.setText("Descrição Categoria");
						
		Label lblCampoDePesquisa = new Label(shlPesquisar, SWT.NONE);
		lblCampoDePesquisa.setBounds(146, 14, 62, 15);
		lblCampoDePesquisa.setText("Campo");
		
		combo = new CCombo(shlPesquisar, SWT.BORDER | SWT.READ_ONLY);
		combo.setListVisible(true);
		combo.setBounds(32, 31, 91, 21);
		combo.add("Código", 0);
		combo.add("Categoria",1);
		combo.select(1);
		
		btnPesquisar = new Button(shlPesquisar, SWT.NONE);
		btnPesquisar.setText("Pesquisar");
		btnPesquisar.setBounds(505, 14, 62, 54);
			

	}
}
