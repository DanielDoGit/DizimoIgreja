package telas.Pesquiar;

import java.sql.Connection;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import beans.CategoriaFuncionario;
import beans.Cidade;
import beans.Funcionario;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.FuncionarioDao;
import telas.Inicial;
import telas.WindowFuncionarioAction;

public class WindowFuncionarioRecuperarAction extends WindowFuncionarioRecuperar {

	private List<Funcionario> lista;

	public WindowFuncionarioRecuperarAction() {
		super.createContents();
		limparTabela();
		tratarEventos();
		super.openShell();
	}

	public void limparTabela() {
		TableItem ass[] = table.getItems();

		if (!text.getText().isEmpty()) {

			for (int s = 0; s < ass.length; s++) {
				ass[s].setText(0, "");
				ass[s].setText(1, "");
				ass[s].setText(2, "");

			}
			table.setItemCount(0);

		}
	}

	public void tratarEventos() {
		tltmPesquisar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				limparTabela();
				try {
					
					new WindowFuncionarioAction().verificarPermissaoPesquisar();
						if (combo.getSelectionIndex() == 0) {
							if (text.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
								Funcionario funcionario = new Funcionario();
								funcionario.setIdUsuario(Integer.valueOf(text.getText()));
								FuncionarioDao funcionarioDao = new FuncionarioDao();
								funcionarioDao.setConnectionFuncionarioDao(Inicial.startaPropertiesConnection());
								lista = funcionarioDao.pesquisarlistaFuncionarioCodigo(funcionario);
								if (lista != null && !lista.isEmpty()) {
									table.setItemCount(lista.size());
									TableItem itens[] = table.getItems();
									for (int i = 0; i < itens.length; i++) {
										itens[i].setText(0, String.valueOf(lista.get(i).getIdUsuario()));
										itens[i].setText(1, lista.get(i).getNomeUsuario());
										itens[i].setText(2, lista.get(i).getCidadeUsuario().getNomeCidade());
										itens[i].setText(3,
												lista.get(i).getCategoriaFuncionario().getNomeCategoraiFuncionario());
									}

								} else {
									PropriedadesShell.mensagemDeRetorno(
											"Nenhum registro foi encontrado com esse argumento: " + text.getText());
								}
							} else {
								PropriedadesShell.mensagemDeRetorno("O que você inseriu não é número");
							}
							
						} else if (combo.getSelectionIndex() == 1) {
							Funcionario funcionario = new Funcionario();
							funcionario.setNomeUsuario(text.getText());
							FuncionarioDao funcionarioDao = new FuncionarioDao();
							funcionarioDao.setConnectionFuncionarioDao(Inicial.startaPropertiesConnection());
							lista = funcionarioDao.pesquisarlistaFuncionarioNome(funcionario);
							if (lista != null && !lista.isEmpty()) {
								table.setItemCount(lista.size());
								TableItem itens[] = table.getItems();
								for (int i = 0; i < itens.length; i++) {
									itens[i].setText(0, String.valueOf(lista.get(i).getIdUsuario()));
									itens[i].setText(1, lista.get(i).getNomeUsuario());
									itens[i].setText(2, lista.get(i).getCidadeUsuario().getNomeCidade());
									itens[i].setText(3,
											lista.get(i).getCategoriaFuncionario().getNomeCategoraiFuncionario());
								}

							} else {
								PropriedadesShell.mensagemDeRetorno(
										"Nenhum registro foi encontrado com esse argumento: " + text.getText());
							}
							
						} else if (combo.getSelectionIndex() == 2) {

							Cidade cidade = new Cidade();
							cidade.setNomeCidade(text.getText());
							FuncionarioDao funcionarioDao = new FuncionarioDao();
							funcionarioDao.setConnectionFuncionarioDao(Inicial.startaPropertiesConnection());
							lista = funcionarioDao.pesquisarListaFuncionarioCidade(cidade);
							if (lista != null && !lista.isEmpty()) {
								table.setItemCount(lista.size());
								TableItem itens[] = table.getItems();
								for (int i = 0; i < itens.length; i++) {
									itens[i].setText(0, String.valueOf(lista.get(i).getIdUsuario()));
									itens[i].setText(1, lista.get(i).getNomeUsuario());
									itens[i].setText(2, lista.get(i).getCidadeUsuario().getNomeCidade());
									itens[i].setText(3,
											lista.get(i).getCategoriaFuncionario().getNomeCategoraiFuncionario());
								}
							} else {
								PropriedadesShell.mensagemDeRetorno(
										"Nenhum registro foi encontrado com esse argumento: " + text.getText());
							}
						
						} else if (combo.getSelectionIndex() == 3) {
							CategoriaFuncionario categoriaFuncionario = new CategoriaFuncionario();
							categoriaFuncionario.setNomeCategoraiFuncionario(text.getText());
							FuncionarioDao funcionarioDao = new FuncionarioDao();
							funcionarioDao.setConnectionFuncionarioDao(Inicial.startaPropertiesConnection());
							lista = funcionarioDao.pesquisarListaFuncionarioCoategoria(categoriaFuncionario);
							if (lista != null && !lista.isEmpty()) {
								table.setItemCount(lista.size());
								TableItem itens[] = table.getItems();
								for (int i = 0; i < itens.length; i++) {
									itens[i].setText(0, String.valueOf(lista.get(i).getIdUsuario()));
									itens[i].setText(1, lista.get(i).getNomeUsuario());
									itens[i].setText(2, lista.get(i).getCidadeUsuario().getNomeCidade());
									itens[i].setText(3,
											lista.get(i).getCategoriaFuncionario().getNomeCategoraiFuncionario());
								}
							} else {
								PropriedadesShell.mensagemDeRetorno(
										"Nenhum registro foi encontrado com esse argumento: " + text.getText());
							}
							
						}
						Inicial.fechaconexao();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					PropriedadesShell
							.mensagemDeErro("Não foi possivel realizar a consulta. Verfique o log e tente novamente");
					new EjetaException(e1);
				}

			}
		});

		tltmAceitar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				try {
					int i = table.getSelectionIndex();
					if (i != -1) {

						TableItem lista[] = table.getItems();
						Funcionario funcionario = new Funcionario();
						funcionario.setIdUsuario(Integer.valueOf(lista[i].getText(0)));
						WindowFuncionarioAction wca = new WindowFuncionarioAction();
						wca.verificarPermissaoEditar(funcionario);
					} else {
						PropriedadesShell.mensagemDeRetorno("Selecione um registro para ser editado");
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}

			}

		});

		tltmExcluir.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new WindowFuncionarioAction().verificarPermissaoExcluir();
					Connection conec = Inicial.startaPropertiesConnection();
						int i = table.getSelectionIndex();
						if (i != -1) {

							TableItem lista[] = table.getItems();
							Funcionario funcionario = new Funcionario();
							funcionario.setIdUsuario(Integer.valueOf(lista[i].getText(0)));

							if (AutenticadorUsuario.getusuario().getIdUsuario().equals(funcionario.getIdUsuario())
									&& AutenticadorUsuario.isColetor()) {
								AutenticadorUsuario.deletePermissoesFuncionario(funcionario);
								FuncionarioDao funcionarioDao = new FuncionarioDao();
								funcionarioDao.setConnectionFuncionarioDao(conec);
								funcionarioDao.excluirFuncionario(funcionario);
								limparTabela();

							} else if (!AutenticadorUsuario.getusuario().getIdUsuario()
									.equals(funcionario.getIdUsuario())) {
								AutenticadorUsuario.deletePermissoesFuncionario(funcionario);
								FuncionarioDao funcionarioDao = new FuncionarioDao();
								funcionarioDao.setConnectionFuncionarioDao(conec);
								funcionarioDao.excluirFuncionario(funcionario);
								limparTabela();

							} else {
								PropriedadesShell.mensagemDeErro(
										"Este funcionário está utlizando o sistema, portanto não é possível excluí-lo");
							}

						} else {
							PropriedadesShell.mensagemDeRetorno("Selecione um registro para ser excluído");
						}
					
					Inicial.fechaconexao();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}

			}

		});

	}

}
