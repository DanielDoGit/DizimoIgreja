package telas.Pesquiar;

import java.sql.SQLException;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import beans.Cidade;
import beans.Comunidade;
import beans.Fornecedor;
import comum.EjetaException;
import dao.CidadeDao;
import dao.ComunidadeDao;
import dao.FornecedorDao;
import telas.Inicial;
import telas.PropriedadesShell;
import telas.WindowComunidadeAction;
import telas.WindowFornecedor;
import telas.WindowFornecedorAction;
import telas.Editar.WindowCidadeEditar;

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
				
					//System.out.println(ass[table.getSelectionIndex()].getText(1));
					//System.out.println(table.getSelectionIndex());
					Text t = new Text(shlPesquisar, SWT.None);

					t.setText(ass[table.getSelectionIndex()].getText(0));
					
					new WindowFornecedorAction(btnEditar,t);
					//wc.open();
				}else {
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
					new WindowFornecedorAction(ass[table.getSelectionIndex()].getText(0));
					int a = table.getSelectionIndex();
					ass[a].setText(0, "");
					ass[a].setText(1, "");
					ass[a].setText(2, "");
					
					}else {
						PropriedadesShell.mensagemDeRetorno("Selecione um registro para excluir");
					}
				} catch (NullPointerException e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}
			}
		});
		
		btnPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				ass = table.getItems();
				
				
				if (!text.getText().isEmpty()) {
					if (ass.length > 0 ) {
						for (int i = 0; i < ass.length; i++ ) {
							ass[i].setText(0, "");
							ass[i].setText(1, "");
							ass[i].setText(2, "");
							
						}
						table.setItemCount(0);
					}
					

					try {
						
						FornecedorDao c = new FornecedorDao();
							c.setCon(Inicial.startaPropertiesConnection());
							
							// System.out.println(ij);
							TableItem a = new TableItem(table, 0);
							Fornecedor co = null;
							if (combo.getSelectionIndex() == 0) {
								Integer ij = Integer.valueOf(text.getText());
								co = new Fornecedor();
								co.setIndice(ij);
								co = c.pesquisarComunidadeIndice(co);
								a.setText(0, text.getText());
								a.setText(1, co.getNomeFantasia());
								a.setText(2, co.getCidade().getNomeCidade());
							} else if (combo.getSelectionIndex() == 1) {
								co = new Fornecedor();
								co.setNomeFantasia(text.getText());
								for (Fornecedor cc : c.pesquisarListaComunidadeNomeFantasia (co)) {
									a.setText(0, Integer.toString(cc.getIndice()));
									a.setText(1, cc.getNomeFantasia());
									a.setText(2, cc.getCidade().getNomeCidade());
								}
							} else if (combo.getSelectionIndex() == 2) {
								table.setItemCount(0);
								co = new Fornecedor();
								Cidade cidade = new Cidade();
								cidade.setNomeCidade(text.getText());
								co.setCidade(cidade);
								TableItem itens = null; 
								for (Fornecedor cc : c.pesquisarListaComunidadeCidade(co.getCidade())) {
									itens = new TableItem(table, SWT.ARROW);
									itens.setText(0, Integer.toString(cc.getIndice()));
									itens.setText(1, cc.getNomeFantasia());
									itens.setText(2, cc.getCidade().getNomeCidade());
								}
							}
						c.getCon().close();
						

						// table.getSelectionIndex()
						// System.out.println(table.getSelectionCount());

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						PropriedadesShell.mensagemDeRetorno(
								"N??o foi poss??vel realizar a consulta. Verifique o log e tente novamente.");
						new EjetaException(e1);
					}catch (NullPointerException es) {
						PropriedadesShell.mensagemDeErro("N??o foi poss??vel realizar a consulta. Verifique o log e tente novamente.");
						new EjetaException(es);
					}
					
						
					
				}else {
					PropriedadesShell.mensagemDeRetorno("informe o c??digo de busca");
				}
			}
		});
	}
	
}
