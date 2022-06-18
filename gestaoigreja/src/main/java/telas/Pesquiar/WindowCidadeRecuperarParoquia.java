package telas.Pesquiar;

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

import java.sql.SQLException;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import beans.Cidade;
import beans.Paroquia;
import comum.EjetaException;
import dao.CidadeDao;
import telas.Inicial;
import telas.PropriedadesShell;
import telas.Editar.WindowCidadeEditar;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class WindowCidadeRecuperarParoquia {

	protected Shell shlPesquisar;
	private Text text;
	private Table table;
	private TableItem ass[];
	
	
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WindowCidadeRecuperarParoquia window = new WindowCidadeRecuperarParoquia();
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
	
	
	
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
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
		text.setBounds(146, 31, 420, 21);
		
		Button btnEditar = new Button(shlPesquisar, SWT.NONE);
		btnEditar.setBounds(671, 14, 62, 54);
		btnEditar.setText("Aceitar");
		btnEditar.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (table.getSelectionIndex() != -1) {
					ass = table.getItems();
					
					//System.out.println(ass[table.getSelectionIndex()].getText(1));
					//System.out.println(table.getSelectionIndex());
					new Paroquia().setCidade(
							new Cidade(
										Integer.valueOf(ass[table.getSelectionIndex()].getText(0)),
										ass[table.getSelectionIndex()].getText(1),
										ass[table.getSelectionIndex()].getText(2)
									  ));
					
					
					//wc.open();
				}else {
					PropriedadesShell.mensagemDeRetorno("Selecione um registro para ser editado");
				}
			}
			
		
		});
		
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
		lblCampoDePesquisa.setBounds(146, 14, 62, 15);
		lblCampoDePesquisa.setText("Campo");
		
		CCombo combo = new CCombo(shlPesquisar, SWT.BORDER | SWT.READ_ONLY);
		combo.setListVisible(true);
		combo.setBounds(32, 31, 91, 21);
		combo.add("Código", 0);
		combo.add("Cidade",1);
		combo.add("UF",2);
		combo.select(1);
		
		Button btnPesquisar = new Button(shlPesquisar, SWT.NONE);
		btnPesquisar.setText("Pesquisar");
		btnPesquisar.setBounds(586, 14, 62, 54);
		btnPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				ass = table.getItems();
				
				
				if (!text.getText().isBlank()) {
					if (ass.length > 0 ) {
						for (int i = 0; i < ass.length; i++ ) {
							ass[i].setText(0, "");
							ass[i].setText(1, "");
							ass[i].setText(2, "");
							
						}
						table.setItemCount(0);
					}
					

					try {
						
							CidadeDao c = new CidadeDao();
							c.setConnection(Inicial.startaPropertiesConnection());
							
							// System.out.println(ij);
							TableItem a = new TableItem(table, 0);
							Cidade co = null;
							if (combo.getSelectionIndex() == 0) {
								Integer ij = Integer.valueOf(text.getText());
								co = c.consultarCidadePorCodigo(ij);
								a.setText(0, Integer.toString(co.getIdCidade()));
								a.setText(1, co.getNomeCidade());
								a.setText(2, co.getUfCidade());
							} else if (combo.getSelectionIndex() == 1) {
								co = new Cidade();
								co.setNomeCidade(text.getText());
								for (Cidade cc : c.consultarCidadeNome(co)) {
									a.setText(0, Integer.toString(cc.getIdCidade()));
									a.setText(1, cc.getNomeCidade());
									a.setText(2, cc.getUfCidade());
								}
							} else if (combo.getSelectionIndex() == 2) {
								table.setItemCount(0);
								co = new Cidade();
								co.setUfCidade(text.getText());
								TableItem itens = null; 
								for (Cidade cc : c.consultarCidadeUF(co)) {
									itens = new TableItem(table, SWT.ARROW);
									itens.setText(0, Integer.toString(cc.getIdCidade()));
									itens.setText(1, cc.getNomeCidade());
									itens.setText(2, cc.getUfCidade());
								}
							}
						
						

						// table.getSelectionIndex()
						// System.out.println(table.getSelectionCount());

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						PropriedadesShell.mensagemDeRetorno(
								"Não foi possível realizar a consulta. Verifique o log e tente novamente.");
						new EjetaException(e1);
					}
						
					
				}else {
					PropriedadesShell.mensagemDeRetorno("informe o código de busca");
				}
			}
		});
		
		

	}
}
