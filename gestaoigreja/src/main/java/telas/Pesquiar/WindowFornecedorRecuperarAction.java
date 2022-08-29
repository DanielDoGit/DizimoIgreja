package telas.Pesquiar;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import beans.Cidade;
import beans.Fornecedor;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.FornecedorDao;
import telas.Inicial;
import telas.WindowFornecedorAction;

public class WindowFornecedorRecuperarAction extends WindowFornecedorRecuperar {

	public WindowFornecedorRecuperarAction() {
		super.createContents();
		this.tratarEventos();
		super.open();
	}

	public void tratarEventos() {
		btnEditar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (table.getSelectionIndex() != -1) {
					ass = table.getItems();

					Text t = new Text(shlPesquisar, SWT.None);

					t.setText(ass[table.getSelectionIndex()].getText(0));

					try {
						new WindowFornecedorAction().verificarPermissaoEditar(t.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					;

				} else {
					PropriedadesShell.mensagemDeRetorno("Selecione um registro para ser editado");
				}
			}

		});

		btnExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				ass = table.getItems();

				try {

					if (table.getSelectionIndex() != -1) {
						new WindowFornecedorAction()
								.verificarPermissaoExcluir(ass[table.getSelectionIndex()].getText(0));
						;
						int a = table.getSelectionIndex();
						ass[a].setText(0, "");
						ass[a].setText(1, "");
						ass[a].setText(2, "");

					} else {
						PropriedadesShell.mensagemDeRetorno("Selecione um registro para excluir");
					}

				} catch (NullPointerException e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				} catch (Exception e5) {
					new EjetaException(e5);
				}
			}
		});

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
						new WindowFornecedorAction().verificarPermissaoPesquisar();
						Connection con = Inicial.startaPropertiesConnection();
						FornecedorDao c = new FornecedorDao();
						c.setCon(con);

						Fornecedor co = null;
						if (combo.getSelectionIndex() == 0) {
							String string = text.getText();
							if (string != null && string.matches("[+-]?\\d*(\\.\\d+)?")) {
								Integer ij = Integer.valueOf(string);
								co = new Fornecedor();
								co.setIndice(ij);
								co = c.pesquisarFornecedorIndice(co);
								TableItem a = new TableItem(table, 0);
								a.setText(0, Integer.toString(co.getIndice()));
								a.setText(1, co.getNomeFantasia());
								a.setText(2, co.getCidade().getNomeCidade());

							} else {
								PropriedadesShell.mensagemDeRetorno("O que você inseriu não é número");
							}
						} else if (combo.getSelectionIndex() == 1) {
							co = new Fornecedor();
							co.setNomeFantasia(text.getText());
							List<Fornecedor> lista = c.pesquisarListaFornecedorNomeFantasia(co);
							if (lista != null && !lista.isEmpty()) {
								for (Fornecedor cc : lista) {
									TableItem a = new TableItem(table, 0);
									a.setText(0, Integer.toString(cc.getIndice()));
									a.setText(1, cc.getNomeFantasia());
									a.setText(2, cc.getCidade().getNomeCidade());

								}
							} else {
								PropriedadesShell.mensagemDeRetorno(
										"Não há nenhum registro com esse argumento: " + text.getText());
							}
						} else if (combo.getSelectionIndex() == 2) {
							table.setItemCount(0);

							co = new Fornecedor();
							Cidade cidade = new Cidade();
							cidade.setNomeCidade(text.getText());
							co.setCidade(cidade);
							TableItem itens = null;
							List<Fornecedor> lista = c.pesquisarListaFornecedorCidade(co.getCidade());
							if (lista != null && !lista.isEmpty()) {
								for (Fornecedor cc : lista) {
									itens = new TableItem(table, 0);
									itens.setText(0, Integer.toString(cc.getIndice()));
									itens.setText(1, cc.getNomeFantasia());
									itens.setText(2, cc.getCidade().getNomeCidade());
								}
							} else {
								PropriedadesShell.mensagemDeRetorno(
										"Não há nenhum registro com esse argumento: " + text.getText());
							}
						}

					} catch (SQLException e1) {
						PropriedadesShell.mensagemDeRetorno(
								"Não foi possível realizar a consulta. Verifique o log e tente novamente.");
						new EjetaException(e1);
					} catch (NullPointerException es) {
						PropriedadesShell.mensagemDeRetorno("Não foram encotrados registros com esse argumento: "+text.getText());
						new EjetaException(es);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Inicial.fechaconexao();

				} else {
					PropriedadesShell.mensagemDeRetorno("informe o código de busca");
				}
			}
		});
	}

}
