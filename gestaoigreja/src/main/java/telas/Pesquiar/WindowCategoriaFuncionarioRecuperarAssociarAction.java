package telas.Pesquiar;

import java.sql.Connection;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import beans.CategoriaFuncionario;
import comum.EjetaException;
import comum.PropriedadesShell;
import dao.CategoriaFuncionarioDao;
import telas.Inicial;

public class WindowCategoriaFuncionarioRecuperarAssociarAction  extends WindowCategoriaFuncionarioRecuperarAssociar{

	private List<CategoriaFuncionario> listaFuncionario;
	private CategoriaFuncionario categoriaFuncionario;
	
	
	public WindowCategoriaFuncionarioRecuperarAssociarAction() {
		
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
					categoriaFuncionario = listaFuncionario.get(table.getSelectionIndex());
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
					
					CategoriaFuncionarioDao cfd = new CategoriaFuncionarioDao();
					cfd.setCon(con);
					
					
					if (combo.getSelectionIndex() == 0 && !text.getText().isEmpty()) {
						categoriaFuncionario = new CategoriaFuncionario();
						categoriaFuncionario.setIdCategoriaFuncionario(Integer.valueOf(text.getText()));
						listaFuncionario = cfd.pesquisarCategoriaPorId(categoriaFuncionario);
						if (!listaFuncionario.isEmpty()) {
							table.setItemCount(listaFuncionario.size());
							TableItem[] tabeladeitens = table.getItems();
							for (int i = 0; i < tabeladeitens.length; i++) {
								tabeladeitens[i].setText(0, Integer.toString(listaFuncionario.get(i).getIdCategoriaFuncionario()));
								tabeladeitens[i].setText(1, listaFuncionario.get(i).getNomeCategoraiFuncionario());
							}
						}else {
							PropriedadesShell.mensagemDeRetorno("Não há nenhum registro com esse argumento");
						}
					}else if (combo.getSelectionIndex() == 1 && !text.getText().isEmpty()) {
						categoriaFuncionario = new CategoriaFuncionario();
						categoriaFuncionario.setNomeCategoraiFuncionario(text.getText());
						listaFuncionario = cfd.pesquisarCategoriaPorNome(categoriaFuncionario);
						if (!listaFuncionario.isEmpty()) {
							table.setItemCount(listaFuncionario.size());
							TableItem[] tabeladeitens = table.getItems();
							for (int i = 0; i < listaFuncionario.size(); i++) {
								tabeladeitens[i].setText(0, Integer.toString(listaFuncionario.get(i).getIdCategoriaFuncionario()));
								tabeladeitens[i].setText(1, listaFuncionario.get(i).getNomeCategoraiFuncionario());
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
	
	public CategoriaFuncionario getCategoriaFuncionario() {
		return categoriaFuncionario;
	}
	
	
}
