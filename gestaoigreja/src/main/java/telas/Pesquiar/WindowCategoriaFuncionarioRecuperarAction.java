package telas.Pesquiar;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import beans.CategoriaFuncionario;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.CategoriaFuncionarioDao;
import telas.Inicial;
import telas.WindowCategoriaFuncionarioAction;

public class WindowCategoriaFuncionarioRecuperarAction extends WindowCategoriaFuncionarioRecuperar {

	private List<CategoriaFuncionario> lista;

	public WindowCategoriaFuncionarioRecuperarAction() {
		try {
			super.createContents();
			tratareventos();
			super.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}

	}

	public void tratareventos() {
		tltmPesquisar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				TableItem ass[] = table.getItems();

				if (!text.getText().isEmpty()) {
					if (ass.length > 0) {
						for (int i = 0; i < ass.length; i++) {
							ass[i].setText(0, "");
							ass[i].setText(1, "");

						}
						table.setItemCount(0);
					}
				}

				try {
					Connection con = Inicial.startaPropertiesConnection();
					new WindowCategoriaFuncionarioAction().verificarPermissoesPesquisar();
						if (text.getText() != null && !text.getText().isEmpty()) {

							if (combo.getSelectionIndex() == 0) {

								CategoriaFuncionarioDao cf = new CategoriaFuncionarioDao();
								cf.setCon(con);
								lista = cf.pesquisarCategoriaPorId(
										new CategoriaFuncionario(Integer.valueOf(text.getText()), null));

								if (lista != null && !lista.isEmpty()) {
									table.setItemCount(lista.size());
									ass = table.getItems();
									for (int i = 0; i < ass.length; i++) {
										ass[i].setText(0, Integer.toString(lista.get(i).getIdCategoriaFuncionario()));
										ass[i].setText(1, lista.get(i).getNomeCategoraiFuncionario());
									}

								} else {
									PropriedadesShell.mensagemDeRetorno("Não há nenhum registro com esse argumento");
								}
							} else if (combo.getSelectionIndex() == 1) {

								CategoriaFuncionarioDao cf = new CategoriaFuncionarioDao();
								cf.setCon(con);
								lista = cf.pesquisarCategoriaPorNome(new CategoriaFuncionario(null, text.getText()));

								if (lista != null && !lista.isEmpty()) {
									table.setItemCount(lista.size());
									ass = table.getItems();
									for (int i = 0; i < ass.length; i++) {
										ass[i].setText(0, Integer.toString(lista.get(i).getIdCategoriaFuncionario()));
										ass[i].setText(1, lista.get(i).getNomeCategoraiFuncionario());
									}

								} else {
									PropriedadesShell.mensagemDeRetorno("Não há nenhum registro com esse argumento");
								}
							}

						} else {
							PropriedadesShell.mensagemDeRetorno("Informe um argumento para realizar uma consulta");
						}

					
					Inicial.fechaconexao();
				} catch (NumberFormatException s) {
					PropriedadesShell.mensagemDeErro("O que você digitou não é um argumento valido");
					new EjetaException(s);
				} catch (Exception s) {
					PropriedadesShell.mensagemDeErro("Ocorreu um erro. Verifique o log e tente novamente");
					new EjetaException(s);
				}
			}

		});

		tltmEditar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

				try {
					Connection con = Inicial.startaPropertiesConnection();
				new WindowCategoriaFuncionarioAction().editarCategoriaFuncionario();

						if (table.getSelectionIndex() != -1) {

							WindowCategoriaFuncionarioAction wcf = new WindowCategoriaFuncionarioAction();
							wcf.setCategoriaFuncionario(lista.get(table.getSelectionIndex()));
							wcf.populartelaeditar();
							wcf.editarCategoriaFuncionario();
							wcf.open();
							if(wcf.getShell().isDisposed()) {
								CategoriaFuncionarioDao cf = new CategoriaFuncionarioDao();
								cf.setCon(con);
								List<CategoriaFuncionario> listaAux = cf.pesquisarCategoriaPorId(lista.get(table.getSelectionIndex()));
								table.setItemCount(listaAux.size());
								ass = table.getItems();
								for (int i = 0; i < ass.length; i++) {
									ass[i].setText(0, Integer.toString(listaAux.get(i).getIdCategoriaFuncionario()));
									ass[i].setText(1, listaAux.get(i).getNomeCategoraiFuncionario());
								}

							}

						} else {
							PropriedadesShell.mensagemDeRetorno("Selecione um item para ser editado");
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
				// TODO Auto-generated method stub
				try {
					Connection con = Inicial.startaPropertiesConnection();
					new WindowCategoriaFuncionarioAction().verificarPermissoesExcluir();
						if (table.getSelectionIndex() != -1) {
							
							CategoriaFuncionarioDao cf = new CategoriaFuncionarioDao();
							cf.setCon(con);
							
							cf.deletarcategoria(lista.get(table.getSelectionIndex()));
							TableItem ass[] = table.getItems();

							if (!text.getText().isEmpty()) {
								if (ass.length > 0) {
									for (int i = 0; i < ass.length; i++) {
										ass[i].setText(0, "");
										ass[i].setText(1, "");

									}
									table.setItemCount(0);
								}
							}
					
						}else {
							PropriedadesShell.mensagemDeRetorno("Selecione um item para ser excluido");
						}
						
					
				} catch (SQLException e1) {
					PropriedadesShell.mensagemDeErro("Não é possível deletar esta categoria, pois pertence a outros registros");
					new EjetaException(e1);
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

	}

}
