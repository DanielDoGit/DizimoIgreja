package telas.Pesquiar;

import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import beans.Cidade;
import beans.Comunidade;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.ComunidadeDao;
import telas.Inicial;

public class WindowComunidadeRecuperarAssociarAction extends WindowComunidadeRecuperarAssociar {

	private Comunidade c;
	private List<Comunidade> lista;

	public Comunidade getC() {
		return this.c;
	}

	public void setC(Comunidade c) {
		this.c = c;
	}

	public WindowComunidadeRecuperarAssociarAction() {

		try {
			super.createContents();
			this.tratarEventos();
			super.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new EjetaException(e);
		}

	}

	public void tratarEventos() {
		btnEditar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				if (table.getSelectionIndex() != -1) {
					
					try {
						if (combo.getSelectionIndex() != 0) {
							c = lista.get(table.getSelectionIndex());
							shlPesquisar.dispose();
						}else {
							
							shlPesquisar.dispose();
							
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println(e1.getCause());
						new EjetaException(e1);
					}

				} else {
					PropriedadesShell.mensagemDeRetorno("Selecione um registro para ser aceito");
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

						ComunidadeDao cDao = new ComunidadeDao();
						cDao.setCon(Inicial.startaPropertiesConnection());

						if (combo.getSelectionIndex() == 0) {
							Integer ij = Integer.valueOf(text.getText());
							c = new Comunidade();
							c.setIdComunidade(ij);

							c = cDao.pesquisarComunidadeIndice(c);

							if (c != null) {
								TableItem a = new TableItem(table, 0);
								a.setText(0, Integer.toString(c.getIdComunidade()));
								a.setText(1, c.getNomefantaziaComunidade());
								a.setText(2, c.getCidade().getNomeCidade());

							} else {
								PropriedadesShell.mensagemDeRetorno("Não há nenhum cadastro com esse número");
							}

						} else if (combo.getSelectionIndex() == 1) {
							c = new Comunidade();
							c.setNomefantaziaComunidade(text.getText());
							lista = cDao.pesquisarListaComunidadeNomeFantasia(c);

							if (lista != null) {
								table.setItemCount(lista.size());
								ass = table.getItems();
								for (int i = 0; i < lista.size(); i++) {
									ass[i].setText(0, Integer.toString(lista.get(i).getIdComunidade()));
									ass[i].setText(1, lista.get(i).getNomefantaziaComunidade());
									ass[i].setText(2, lista.get(i).getCidade().getNomeCidade());
								}

							} else {
								PropriedadesShell.mensagemDeRetorno("Não há nenhum cadastro com esse nome");
								throw new NullPointerException("Não ha cadastro com esse nome");
							}
						}	else if (combo.getSelectionIndex() == 2) {
								
								Cidade cidade = new Cidade();
								cidade.setNomeCidade(text.getText());
								lista = cDao.pesquisarListaComunidadeCidade(cidade);
								table.setItemCount(lista.size());
								ass = table.getItems();
								if(lista != null) {
									for (int i = 0; i < lista.size(); i++) {
										ass[i].setText(0, Integer.toString(lista.get(i).getIdComunidade()));
										ass[i].setText(1, lista.get(i).getNomefantaziaComunidade());
										ass[i].setText(2, lista.get(i).getCidade().getNomeCidade());
									}
								}
							}

					} catch (SQLException e1) {

						PropriedadesShell.mensagemDeRetorno(
								"Não foi possível realizar a consulta. Verifique o log e tente novamente.");
						new EjetaException(e1);
					} catch (NullPointerException e2) {
						new EjetaException(e2);
						System.out.println(e2);
						PropriedadesShell.mensagemDeErro("Ocorreu um erro");
					}

				} else {
					PropriedadesShell.mensagemDeRetorno("informe o código de busca");
				}
			}
		});
	}

}
