package telas.Pesquiar;

import java.sql.Connection;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import beans.Dizimista;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.DizimistaDao;
import telas.Inicial;

public class WindowDizimistaRecuperarAssociarAction  extends WindowDizimistaRecuperarAssociar{

	private List<Dizimista> listaDizimista;
	private Dizimista dizimista;
	
	
	public WindowDizimistaRecuperarAssociarAction() {
		
		try {
			super.createContents();
			tratarEventos();
			super.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
	}
	
	public void tratarEventos() {
		
		btnEditar.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				if (table.getSelectionIndex() != -1) {
					dizimista = listaDizimista.get(table.getSelectionIndex());
					shlPesquisar.dispose();
				}else {
					PropriedadesShell.mensagemDeRetorno("Selecione um item para aceitar");
				}
				
			}
		});
		
		btnPesquisar.addSelectionListener(new SelectionAdapter() {
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
					
					DizimistaDao diziDao = new DizimistaDao();
					diziDao.setConnection(con);
					
					
					if (combo.getSelectionIndex() == 0 && !text.getText().isEmpty()) {
						dizimista = new Dizimista();
						dizimista.setIdDizimista(Integer.valueOf(text.getText()));
						listaDizimista = diziDao.pesquisarListaDizimistasCodigoAtivos(dizimista);
						if (!listaDizimista.isEmpty()) {
							table.setItemCount(listaDizimista.size());
							TableItem[] tabeladeitens = table.getItems();
							for (int i = 0; i < tabeladeitens.length; i++) {
								tabeladeitens[i].setText(0, Integer.toString(listaDizimista.get(i).getIdDizimista()));
								tabeladeitens[i].setText(1, listaDizimista.get(i).getNomeDizimista());
							}
						}else {
							PropriedadesShell.mensagemDeRetorno("Não há nenhum registro com esse argumento");
						}
					}else if (combo.getSelectionIndex() == 1 && !text.getText().isEmpty()) {
						dizimista = new Dizimista();
						dizimista.setNomeDizimista(text.getText());
						listaDizimista = diziDao.pesquisarListaDizimistasNomeAtivos(dizimista);
						if (!listaDizimista.isEmpty()) {
							table.setItemCount(listaDizimista.size());
							TableItem[] tabeladeitens = table.getItems();
							for (int i = 0; i < listaDizimista.size(); i++) {
								tabeladeitens[i].setText(0, Integer.toString(listaDizimista.get(i).getIdDizimista()));
								tabeladeitens[i].setText(1, listaDizimista.get(i).getNomeDizimista());
							}
						}else {
							PropriedadesShell.mensagemDeRetorno("Não há nenhum registro com esse argumento");
						}
						
					}else {
						PropriedadesShell.mensagemDeRetorno("Informe um argumento para realizar a pesquisa");
					}
					
				} catch (NumberFormatException e2) {
					PropriedadesShell.mensagemDeErro("O que você digitou não é um número");
					new EjetaException(e2);
				}catch (Exception e2) {
					PropriedadesShell.mensagemDeErro("Ocorreu um erro. Verifique o Log e tente novamente");
					new EjetaException(e2);
				}

			}
		
		});
	}
	
	public Dizimista getDizimista() {
		return this.dizimista;
	}
	
	
}
