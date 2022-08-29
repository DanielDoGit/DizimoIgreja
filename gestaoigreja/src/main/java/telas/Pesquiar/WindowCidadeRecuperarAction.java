package telas.Pesquiar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import beans.Cidade;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.CidadeDao;
import telas.Inicial;
import telas.WindowCidadeAction;

public class WindowCidadeRecuperarAction extends WindowCidadeRecuperar {

	private List<Cidade> lista;
	
	public WindowCidadeRecuperarAction() {
		super.createContents();
		tratarEventos();
		super.open();
	}

	public void tratarEventos() {
		btnEditar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				try {

					if (table.getSelectionIndex() != -1) {
			
						new WindowCidadeAction().verificarPermissaoEditar(lista.get(table.getSelectionIndex()));

					} else {
						PropriedadesShell.mensagemDeRetorno("Selecione um registro para ser editado");
					}

					Inicial.fechaconexao();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}
			}

		});

		btnExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				ass = table.getItems();
				CidadeDao ci = new CidadeDao();
				try {
					new WindowCidadeAction().verificarPermissaoExcluir();
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

		btnPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				ass = table.getItems();
				lista = new ArrayList<Cidade>();
				TableItem a = null;
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
						new WindowCidadeAction().verificarPermissaPesquisar();
						CidadeDao c = new CidadeDao();
						c.setConnection(Inicial.startaPropertiesConnection());

						
						Cidade co = null;
						if (combo.getSelectionIndex() == 0) {
							Integer ij = Integer.valueOf(text.getText());
							co = c.consultarCidadePorCodigo(ij);
							table.setItemCount(lista.size());
							a = new TableItem(table, 0);
							if (co != null && co.getIdCidade() != null) {
								a.setText(0, Integer.toString(co.getIdCidade()));
								a.setText(1, co.getNomeCidade());
								a.setText(2, co.getUfCidade());
								lista.add(co);
							} else {

								PropriedadesShell.mensagemDeRetorno("Nao ha nenhum registro com esse numero");
								throw new NullPointerException("Nao ha nenhum registro com esse numero ");
							}
						} else if (combo.getSelectionIndex() == 1) {
							
							co = new Cidade();
							co.setNomeCidade(text.getText());
							lista = c.consultarCidadeNome(co);
							a = new TableItem(table, 0);
							if (!lista.isEmpty()) {
								table.setItemCount(lista.size());
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

							lista = c.consultarCidadeUF(co);
							itens = new TableItem(table, 0);
							if (!lista.isEmpty()) {
								table.setItemCount(lista.size());
								for (Cidade cc : lista) {
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
