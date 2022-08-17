package telas.Pesquiar;

import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import beans.Cidade;
import beans.Dizimista;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.DizimistaDao;
import telas.Inicial;
import telas.WindowDizimistaAction;

public class WindowRecuperarDizimistaAction extends WindowRecuperarDizimista {

	private boolean permissaopesquisar = false, permissaoeditar = false, permissaoexcluir = false;
	private AutenticadorUsuario at;
	private List<Dizimista> listadizimista;

	public void verificarpermissoes() {
		try {
			AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
			at = new AutenticadorUsuario();
			if (AutenticadorUsuario.isColetor()) {
				permissaopesquisar = at.verificarPermissaoColetor(AutenticadorUsuario.getusuario(),
						new Permissoes(29, "Pesquisar Dizimista"));
				permissaoeditar = at.verificarPermissaoColetor(AutenticadorUsuario.getusuario(),
						new Permissoes(27, "Editar Dizimista"));
				permissaoexcluir = at.verificarPermissaoColetor(AutenticadorUsuario.getusuario(),
						new Permissoes(28, "Excluir Dizimista"));
			} else {
				permissaopesquisar = at.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(),
						new Permissoes(29, "Pesquisar Dizimista"));
				permissaoeditar = at.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(),
						new Permissoes(27, "Editar Dizimista"));
				permissaoexcluir = at.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(),
						new Permissoes(28, "Excluir Dizimista"));
			}
			Inicial.fechaconexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}

	}

	public void tratarEventosPesquisar() {
		tltmPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (permissaopesquisar) {
						PropriedadesShell.limapartabela(table);
						DizimistaDao diziDao = new DizimistaDao();
						diziDao.setConnection(Inicial.startaPropertiesConnection());

						if (combo.getSelectionIndex() == 0 && !text.getText().isEmpty()) {
							
							Dizimista dizi = new Dizimista();
							dizi.setIdDizimista(Integer.valueOf(text.getText()));
							listadizimista = diziDao.pesquisarListaDizimistasCodigo(dizi);
							if (!listadizimista.isEmpty()) {
								table.setItemCount(listadizimista.size());
								TableItem itens[] = table.getItems();
								for (int i = 0; i < itens.length; i++) {
									itens[i].setText(0, Integer.toString(listadizimista.get(i).getIdDizimista()));
									itens[i].setText(1, listadizimista.get(i).getNomeDizimista());
									itens[i].setText(2, listadizimista.get(i).getCidade().getNomeCidade());
									String x = listadizimista.get(i).getIsativo();
									if (x.equals("S")) {
										x = "Sim";
										itens[i].setText(3, x);
									}else {
										x = "Não";
										itens[i].setText(3, x);
									}
								}

							} else {
								PropriedadesShell.mensagemDeRetorno("Não foi encontrado nenhum registro");
							}

						} else if (combo.getSelectionIndex() == 1 && !text.getText().isEmpty()) {
							
							Dizimista dizi = new Dizimista();
							dizi.setNomeDizimista(text.getText());
							listadizimista = diziDao.pesquisarListaDizimistasNome(dizi);
							if (!listadizimista.isEmpty()) {
								table.setItemCount(listadizimista.size());
								TableItem itens[] = table.getItems();
								for (int i = 0; i < itens.length; i++) {
									itens[i].setText(0, Integer.toString(listadizimista.get(i).getIdDizimista()));
									itens[i].setText(1, listadizimista.get(i).getNomeDizimista());
									itens[i].setText(2, listadizimista.get(i).getCidade().getNomeCidade());
									String x = listadizimista.get(i).getIsativo();
									if (x.equals("S")) {
										x = "Sim";
										itens[i].setText(3, x);
									}else {
										x = "Não";
										itens[i].setText(3, x);
									}
								}

							} else {
						
								PropriedadesShell.mensagemDeRetorno("Não foi encontrado nenhum registro");
							}

						} else if (combo.getSelectionIndex() == 2 && !text.getText().isEmpty()) {
							
							Dizimista dizi = new Dizimista();
							Cidade cidade = new Cidade();
							cidade.setNomeCidade(text.getText());
							dizi.setCidade(cidade);
							listadizimista = diziDao.pesquisarListaDizimistasCidade(dizi);
							if (!listadizimista.isEmpty()) {
								table.setItemCount(listadizimista.size());
								TableItem itens[] = table.getItems();
								for (int i = 0; i < itens.length; i++) {
									itens[i].setText(0, Integer.toString(listadizimista.get(i).getIdDizimista()));
									itens[i].setText(1, listadizimista.get(i).getNomeDizimista());
									itens[i].setText(2, listadizimista.get(i).getCidade().getNomeCidade());
									String x = listadizimista.get(i).getIsativo();
									if (x.equals("S")) {
										x = "Sim";
										itens[i].setText(3, x);
									}else {
										x = "Não";
										itens[i].setText(3, x);
									}
								}

							} else {
								PropriedadesShell.mensagemDeRetorno("Não foi encontrado nenhum registro");
							}

						} else {
							PropriedadesShell.mensagemDeRetorno("Informe um argumento para realizar a consulta");
						}
					} else {
						PropriedadesShell
								.mensagemDeRetorno("Usuário sem permissão para acessar o recurso: Pesquisar Dizimista");
					}
				} catch (SQLException e1) {

					PropriedadesShell.mensagemDeRetorno("Ocorreu um erro SQL. Verifique o log e tente novamente");
					Inicial.fechaconexao();
					new EjetaException(e1);
				}catch (NumberFormatException e2) {
					PropriedadesShell.mensagemDeRetorno("O Argumento não é um número");
					Inicial.fechaconexao();
					new EjetaException(e2);
				} catch (Exception e2) {
					Inicial.fechaconexao();
					PropriedadesShell.mensagemDeRetorno("Ocorreu um erro. Verifique o log e tente novamente");
					new EjetaException(e2);
				}
				Inicial.fechaconexao();

			}

		});
	}

	public void tratarEventosEditar() {
		tltmAceitar.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (permissaoeditar) {
					int i = table.getSelectionIndex();
					if (i != -1) {
						WindowDizimistaAction wc = new WindowDizimistaAction("Editar".toLowerCase());
						wc.setDizimista(listadizimista.get(i));
						wc.popularATela();
						wc.open();
					}else {
						PropriedadesShell.mensagemDeRetorno("Selecione um item para ser editado");
					}
					
				}else {
					PropriedadesShell.mensagemDeRetorno("Usuário sem permissao para acessar o recurso: Editar Dizimista");
				}
				
				
			}
		});
	}

	public void tratarEventosExcluir() {
		tltmExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (permissaoexcluir) {
					int i = table.getSelectionIndex();
					if (i != -1) {
						
						DizimistaDao dizidao = new DizimistaDao();
						try {
							dizidao.setConnection(Inicial.startaPropertiesConnection());
							dizidao.excluirDizimista(listadizimista.get(i));
							PropriedadesShell.limapartabela(table);
						} catch (SQLException esql) {
							// TODO Auto-generated catch block
							Inicial.fechaconexao();
							PropriedadesShell.mensagemDeErro("Este registro esta participando de outros registros, por isso nao pode ser excluido.");
							new EjetaException(esql);
						}catch (Exception egeneric) {
							PropriedadesShell.mensagemDeErro("Ocorreu um erro. Verifique o log e tente novamente");
							Inicial.fechaconexao();
							new EjetaException(egeneric);
						}
						
					}else {
						PropriedadesShell.mensagemDeRetorno("Selecione uma registro para excluir");
					}
					
				}else {
					PropriedadesShell.mensagemDeRetorno("Usuário sem permissao para acessar o recurso: Excluir Dizimista");
				}
				Inicial.fechaconexao();
			}
		});
	}

	public WindowRecuperarDizimistaAction() {
		verificarpermissoes();
		super.createContents();
		tratarEventosPesquisar();
		tratarEventosExcluir();
		tratarEventosEditar();
	
		
		super.openShell();

	}

}
