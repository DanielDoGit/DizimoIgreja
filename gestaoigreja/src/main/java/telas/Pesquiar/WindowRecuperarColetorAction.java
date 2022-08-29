package telas.Pesquiar;

import java.sql.Connection;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import beans.Cidade;
import beans.Coletor;
import beans.Comunidade;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.ColetorDao;
import telas.Inicial;
import telas.WindowColetorAction;

public class WindowRecuperarColetorAction extends WindowRecuperarColetor {

	private List<Coletor> lista;

	public WindowRecuperarColetorAction() {
		super.createContents();
		tratarEventos();
		super.openShell();
	}

	public void tratarEventos() {
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
							ass[i].setText(2, "");

						}
						table.setItemCount(0);
					}
				}
				try {

					new WindowColetorAction().verificarPermissaoPesquisar();
					if (combo.getSelectionIndex() == 0) {
						if (text.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
							Coletor coletor = new Coletor();
							coletor.setIdUsuario(Integer.valueOf(text.getText()));
							ColetorDao coletorDao = new ColetorDao();
							coletorDao.setConnectionOnComunidadeDao(Inicial.startaPropertiesConnection());
							lista = coletorDao.pesquisarlistaColetorCodigo(coletor);
							if (lista != null && !lista.isEmpty()) {
								table.setItemCount(lista.size());
								TableItem itens[] = table.getItems();
								for (int i = 0; i < itens.length; i++) {
									itens[i].setText(0, String.valueOf(lista.get(i).getIdUsuario()));
									itens[i].setText(1, lista.get(i).getNomeUsuario());
									itens[i].setText(2, lista.get(i).getCidadeUsuario().getNomeCidade());
									itens[i].setText(3, lista.get(i).getComunidade().getNomefantaziaComunidade());
								}

							} else {
								PropriedadesShell.mensagemDeRetorno(
										"Nenhum registro foi encontrado com esse argumento: " + text.getText());
							}
						} else {
							PropriedadesShell.mensagemDeRetorno("O que você inseriu não é número");
						}

					} else if (combo.getSelectionIndex() == 1) {
						Coletor coletor = new Coletor();
						coletor.setNomeUsuario(text.getText());
						ColetorDao coletorDao = new ColetorDao();
						coletorDao.setConnectionOnComunidadeDao(Inicial.startaPropertiesConnection());
						lista = coletorDao.pesquisarlistaColetorNome(coletor);
						if (lista != null && !lista.isEmpty()) {
							table.setItemCount(lista.size());
							TableItem itens[] = table.getItems();
							for (int i = 0; i < itens.length; i++) {
								itens[i].setText(0, String.valueOf(lista.get(i).getIdUsuario()));
								itens[i].setText(1, lista.get(i).getNomeUsuario());
								itens[i].setText(2, lista.get(i).getCidadeUsuario().getNomeCidade());
								itens[i].setText(3, lista.get(i).getComunidade().getNomefantaziaComunidade());
							}

						} else {
							PropriedadesShell.mensagemDeRetorno(
									"Nenhum registro foi encontrado com esse argumento: " + text.getText());
						}

					} else if (combo.getSelectionIndex() == 2) {

						Cidade cidade = new Cidade();
						cidade.setNomeCidade(text.getText());
						ColetorDao coletorDao = new ColetorDao();
						coletorDao.setConnectionOnComunidadeDao(Inicial.startaPropertiesConnection());
						lista = coletorDao.pesquisarListaColetorCidade(cidade);
						if (lista != null && !lista.isEmpty()) {
							table.setItemCount(lista.size());
							TableItem itens[] = table.getItems();
							for (int i = 0; i < itens.length; i++) {
								itens[i].setText(0, String.valueOf(lista.get(i).getIdUsuario()));
								itens[i].setText(1, lista.get(i).getNomeUsuario());
								itens[i].setText(2, lista.get(i).getCidadeUsuario().getNomeCidade());
								itens[i].setText(3, lista.get(i).getComunidade().getNomefantaziaComunidade());
							}
						} else {
							PropriedadesShell.mensagemDeRetorno(
									"Nenhum registro foi encontrado com esse argumento: " + text.getText());
						}

					} else if (combo.getSelectionIndex() == 3) {
						Comunidade comunidade = new Comunidade();
						comunidade.setNomefantaziaComunidade(text.getText());
						ColetorDao coletorDao = new ColetorDao();
						coletorDao.setConnectionOnComunidadeDao(Inicial.startaPropertiesConnection());
						lista = coletorDao.pesquisarListaColetorCoomunidade(comunidade);
						if (lista != null && !lista.isEmpty()) {
							table.setItemCount(lista.size());
							TableItem itens[] = table.getItems();
							for (int i = 0; i < itens.length; i++) {
								itens[i].setText(0, String.valueOf(lista.get(i).getIdUsuario()));
								itens[i].setText(1, lista.get(i).getNomeUsuario());
								itens[i].setText(2, lista.get(i).getCidadeUsuario().getNomeCidade());
								itens[i].setText(3, lista.get(i).getComunidade().getNomefantaziaComunidade());
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
						Coletor coletor = new Coletor();
						coletor.setIdUsuario(Integer.valueOf(lista[i].getText(0)));
						new WindowColetorAction().verificarPermissaoEditar(coletor);
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

					new WindowColetorAction().verificarPermissaoExcluir();
					Connection conec = Inicial.startaPropertiesConnection();
					AutenticadorUsuario.setCon(conec);
					int i = table.getSelectionIndex();
					if (i != -1) {

						TableItem item[] = table.getItems();
						Coletor coletor = new Coletor();
						coletor.setIdUsuario(Integer.valueOf(item[i].getText(0)));

						if (!AutenticadorUsuario.getusuario().getIdUsuario().equals(coletor.getIdUsuario())) {

							AutenticadorUsuario.deletePermissoesColetor(coletor);
							ColetorDao coletorDao = new ColetorDao();
							coletorDao.setConnectionOnComunidadeDao(conec);
							coletorDao.excluir(coletor);
							lista.remove(i);

							table.setItemCount(0);
							TableItem ass[] = table.getItems();

							if (!text.getText().isEmpty()) {
								if (ass.length > 0) {
									for (int s = 0; s < ass.length; i++) {
										ass[s].setText(0, "");
										ass[s].setText(1, "");
										ass[s].setText(2, "");

									}

								}
							}

						} else if (!AutenticadorUsuario.isColetor()
								&& AutenticadorUsuario.getusuario().getIdUsuario().equals(coletor.getIdUsuario())) {
							AutenticadorUsuario.deletePermissoesColetor(coletor);
							ColetorDao coletorDao = new ColetorDao();
							coletorDao.setConnectionOnComunidadeDao(conec);
							coletorDao.excluir(coletor);
							table.setItemCount(0);
							TableItem ass[] = table.getItems();

							if (!text.getText().isEmpty()) {
								if (ass.length > 0) {
									for (int s = 0; s < ass.length; i++) {
										ass[s].setText(0, "");
										ass[s].setText(1, "");
										ass[s].setText(2, "");

									}

								}
							}

						} else {
							PropriedadesShell.mensagemDeErro(
									"Este coletor está utlizando o sistema, portanto não é possível excluí-lo");
						}

					} else {
						PropriedadesShell.mensagemDeRetorno("Selecione um registro para ser excluído");
					}
					AutenticadorUsuario.getCon().close();
					Inicial.fechaconexao();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}

			}

		});

	}

}
