package telas.Pesquiar;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import beans.Cidade;
import beans.Comunidade;
import beans.Permissoes;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.AutenticadorUsuario;
import dao.ComunidadeDao;
import telas.Inicial;
import telas.WindowComunidadeAction;

public class WindowComunidadeRecuperarAction extends WindowComunidadeRecuperar {

	public WindowComunidadeRecuperarAction() {
		super.createContents();
		this.tratarEventos();
		super.open();
	}

	public void tratarEventos() {
		btnEditar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
					AutenticadorUsuario autenticadorUsuario = new AutenticadorUsuario();
					Permissoes permissoes = new Permissoes(17, "Editar Comunidade");
					if (autenticadorUsuario.verificarPermissaoColetor(AutenticadorUsuario.getusuario(), permissoes)
							|| autenticadorUsuario.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(),
									permissoes)) {

						Inicial.fechaconexao();
						if (table.getSelectionIndex() != -1) {
							ass = table.getItems();

							Text t = new Text(shlPesquisar, SWT.None);

							t.setText(ass[table.getSelectionIndex()].getText(0));

							new WindowComunidadeAction(btnEditar, t);

						} else {
							PropriedadesShell.mensagemDeRetorno("Selecione um registro para ser editado");
						}
					} else {
						PropriedadesShell.mensagemDeRetorno(
								"Usuário sem permissao para acessar o recurso: " + permissoes.getNomepermissao());
					}
				} catch (Exception e1) {
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

				try {

					AutenticadorUsuario.setCon(Inicial.startaPropertiesConnection());
					AutenticadorUsuario autenticadorUsuario = new AutenticadorUsuario();
					Permissoes permissoes = new Permissoes(8, "Excluir Comunidade");
					if (autenticadorUsuario.verificarPermissaoColetor(AutenticadorUsuario.getusuario(), permissoes)
							|| autenticadorUsuario.verificarPermissaoFuncionario(AutenticadorUsuario.getusuario(),
									permissoes)) {

						if (table.getSelectionIndex() != -1) {

							WindowComunidadeAction wc = new WindowComunidadeAction(
									ass[table.getSelectionIndex()].getText(0));
							if (wc.getverificadorexclusao()) {
								int a = table.getSelectionIndex();
								ass[a].setText(0, "");
								ass[a].setText(1, "");
								ass[a].setText(2, "");
							}
						} else {
							PropriedadesShell.mensagemDeRetorno("Selecione um registro para excluir");
						}
					}else {
						PropriedadesShell.mensagemDeRetorno("Usuário sem permissao para acessar o recurso: "+permissoes.getNomepermissao());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);

				}
			}
		});

		btnPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

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

						ComunidadeDao c = new ComunidadeDao();
						c.setCon(Inicial.startaPropertiesConnection());

						// System.out.println(ij);
						TableItem a = new TableItem(table, 0);
						Comunidade co = null;
						if (combo.getSelectionIndex() == 0) {
							if (text.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
								Integer ij = Integer.valueOf(text.getText());
								co = new Comunidade();
								co.setIdComunidade(ij);
								co = c.pesquisarComunidadeIndice(co);
								if (co != null) {
									a.setText(0, text.getText());
									a.setText(1, co.getNomefantaziaComunidade());
									a.setText(2, co.getCidade().getNomeCidade());
								} else {
									PropriedadesShell.mensagemDeRetorno("Não existe nenhuma cidade com esse código");
								}
							} else {
								PropriedadesShell.mensagemDeRetorno("O que você digitou não é numero");
							}
						} else if (combo.getSelectionIndex() == 1) {
							co = new Comunidade();
							co.setNomefantaziaComunidade(text.getText());
							for (Comunidade cc : c.pesquisarListaComunidadeNomeFantasia(co)) {
								a.setText(0, Integer.toString(cc.getIdComunidade()));
								a.setText(1, cc.getNomefantaziaComunidade());
								a.setText(2, cc.getCidade().getNomeCidade());
							}
						} else if (combo.getSelectionIndex() == 2) {
							table.setItemCount(0);
							co = new Comunidade();
							Cidade cidade = new Cidade();
							cidade.setNomeCidade(text.getText());
							co.setCidade(cidade);
							TableItem itens = null;
							for (Comunidade cc : c.pesquisarListaComunidadeCidade(co.getCidade())) {
								itens = new TableItem(table, SWT.ARROW);
								itens.setText(0, Integer.toString(cc.getIdComunidade()));
								itens.setText(1, cc.getNomefantaziaComunidade());
								itens.setText(2, cc.getCidade().getNomeCidade());
							}
						}
						c.getCon().close();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						PropriedadesShell.mensagemDeRetorno(
								"Não foi possível realizar a consulta. Verifique o log e tente novamente.");
						new EjetaException(e1);
					} catch (NullPointerException es) {
						PropriedadesShell.mensagemDeErro(
								"Não foi possível realizar a consulta. Verifique o log e tente novamente.");
						new EjetaException(es);
					} catch (Exception ex) {
						PropriedadesShell.mensagemDeErroDetalhada(ex);
						new EjetaException(ex);
					}

				} else {
					PropriedadesShell.mensagemDeRetorno("informe o código de busca");
				}
			}
		});
	}

}
