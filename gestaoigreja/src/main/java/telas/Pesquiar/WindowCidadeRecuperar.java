package telas.Pesquiar;

import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import beans.Cidade;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.CidadeDao;
import telas.Inicial;
import telas.Editar.WindowCidadeEditar;

public class WindowCidadeRecuperar {

	protected Shell shlPesquisar;
	private Text text;
	private Table table;
	private TableItem ass[];

	public static void main(String[] args) {
		try {
			WindowCidadeRecuperar window = new WindowCidadeRecuperar();
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
		shlPesquisar = new Shell(SWT.MIN | SWT.CLOSE);
		shlPesquisar.setModified(true);
		shlPesquisar.setSize(771, 502);
		shlPesquisar.setText("Pesquisar");
		shlPesquisar.setLayout(null);
		shlPesquisar.layout();

		Label lblFiltro = new Label(shlPesquisar, SWT.NONE);
		lblFiltro.setBounds(32, 14, 55, 15);
		lblFiltro.setText("Filtro");

		text = new Text(shlPesquisar, SWT.BORDER);
		text.setBounds(146, 31, 349, 21);

		Button btnEditar = new Button(shlPesquisar, SWT.NONE);
		btnEditar.setBounds(595, 14, 62, 54);
		btnEditar.setText("Editar");
		btnEditar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				try {
					AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
					AutenticadorUsuario autenticadorUsuario = new AutenticadorUsuario();
					Permissoes permissoes = new Permissoes(4, "Editar Cidade");
					if (autenticadorUsuario.verificarPermissaoColetor(AutenticadorUsuario.getusuario(), permissoes)
							|| autenticadorUsuario.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(),
									permissoes)) {

						if (table.getSelectionIndex() != -1) {
							ass = table.getItems();

							Text t = new Text(shlPesquisar, SWT.None);
							Text t2 = new Text(shlPesquisar, SWT.None);
							Text t3 = new Text(shlPesquisar, SWT.None);
							t.setText(ass[table.getSelectionIndex()].getText(0));
							t2.setText(ass[table.getSelectionIndex()].getText(1));
							t3.setText(ass[table.getSelectionIndex()].getText(2));
							new WindowCidadeEditar(t, t2, t3);

						} else {
							PropriedadesShell.mensagemDeRetorno("Selecione um registro para ser editado");
						}
						
					}else {
						PropriedadesShell.mensagemDeRetorno("Usuário sem permissao para acessar o recurso: "+permissoes.getNomepermissao());
					}
					Inicial.fechaconexao();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}
			}

		});

		Button btnExcluir = new Button(shlPesquisar, SWT.NONE);
		btnExcluir.setBounds(671, 14, 62, 54);
		btnExcluir.setText("Excluir");
		btnExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				ass = table.getItems();
				CidadeDao ci = new CidadeDao();
				try {
					if (table.getSelectionIndex() != -1) {
						ci.setConnection(Inicial.startaPropertiesConnection());
						Cidade c = new Cidade();
						c.setIdCidade(Integer.valueOf(ass[table.getSelectionIndex()].getText(0)));
						ci.excluir(c);
						int a = table.getSelectionIndex();
						ass[a].setText(0, "");
						ass[a].setText(1, "");
						ass[a].setText(2, "");
						Inicial.fechaconexao();
					} else {
						PropriedadesShell.mensagemDeRetorno("Selecione um registro para excluir");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		combo.add("Cidade", 1);
		combo.add("UF", 2);
		combo.select(1);

		Button btnPesquisar = new Button(shlPesquisar, SWT.NONE);
		btnPesquisar.setText("Pesquisar");
		btnPesquisar.setBounds(515, 14, 62, 54);
		btnPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				ass = table.getItems();

				if (!text.getText().isEmpty()) {
					if (ass.length > 0) {
						for (int i = 0; i < ass.length; i++) {
							ass[i].setText(0, "");
							ass[i].setText(1, "");
							ass[i].setText(2, "");

						}
						table.setItemCount(0);
					}

					try {

						CidadeDao c = new CidadeDao();
						c.setConnection(Inicial.startaPropertiesConnection());

						TableItem a = new TableItem(table, 0);
						Cidade co = null;
						if (combo.getSelectionIndex() == 0) {
							Integer ij = Integer.valueOf(text.getText());
							co = c.consultarCidadePorCodigo(ij);
							if (co != null && co.getIdCidade() != null) {
								a.setText(0, Integer.toString(co.getIdCidade()));
								a.setText(1, co.getNomeCidade());
								a.setText(2, co.getUfCidade());
							} else {

								PropriedadesShell.mensagemDeRetorno("Nao ha nenhum registro com esse numero");
								throw new NullPointerException("Nao ha nenhum registro com esse numero ");
							}
						} else if (combo.getSelectionIndex() == 1) {
							co = new Cidade();
							co.setNomeCidade(text.getText());
							List<Cidade> lista = c.consultarCidadeNome(co);
							if (!lista.isEmpty()) {

								for (Cidade cc : lista) {
									a.setText(0, Integer.toString(cc.getIdCidade()));
									a.setText(1, cc.getNomeCidade());
									a.setText(2, cc.getUfCidade());
								}
							} else {

								PropriedadesShell.mensagemDeRetorno(
										"Nao ha nenhum registro com este argumento: " + co.getNomeCidade());
								throw new NullPointerException(
										"Nao ha nenhum registro com este argumento: " + co.getNomeCidade());
							}

						} else if (combo.getSelectionIndex() == 2) {
							table.setItemCount(0);
							co = new Cidade();
							co.setUfCidade(text.getText());
							TableItem itens = null;

							List<Cidade> lista = c.consultarCidadeUF(co);
							if (!lista.isEmpty()) {

								for (Cidade cc : c.consultarCidadeUF(co)) {
									itens = new TableItem(table, SWT.ARROW);
									itens.setText(0, Integer.toString(cc.getIdCidade()));
									itens.setText(1, cc.getNomeCidade());
									itens.setText(2, cc.getUfCidade());
								}
							} else {

								PropriedadesShell.mensagemDeRetorno(
										"Nao ha nenhum registro com este argumento: " + co.getUfCidade());
								throw new NullPointerException(
										"Nao ha nenhum registro com este argumento: " + co.getUfCidade());
							}

						}
						Inicial.fechaconexao();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						PropriedadesShell.mensagemDeRetorno(
								"Não foi possível realizar a consulta. Verifique o log e tente novamente.");
						new EjetaException(e1);
					} catch (Exception ex) {
						new EjetaException(ex);
					}

				} else {
					PropriedadesShell.mensagemDeRetorno("informe o código de busca");
				}
			}
		});

	}
}
