package telas.Pesquiar;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import beans.Cidade;
import beans.Comunidade;
import comum.EjetaException;
import dao.CidadeDao;
import dao.ComunidadeDao;
import telas.Inicial;
import telas.PropriedadesShell;
import telas.WindowComunidadeAction;
import telas.Editar.WindowCidadeEditar;

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
				// TODO Auto-generated method stub
				if (table.getSelectionIndex() != -1) {
					ass = table.getItems();
				
					//System.out.println(ass[table.getSelectionIndex()].getText(1));
					//System.out.println(table.getSelectionIndex());
					Text t = new Text(shlPesquisar, SWT.None);

					t.setText(ass[table.getSelectionIndex()].getText(0));
					
					new WindowComunidadeAction(btnEditar,t);
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
					new WindowComunidadeAction(ass[table.getSelectionIndex()].getText(0));
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
						
							ComunidadeDao c = new ComunidadeDao();
							c.setCon(Inicial.startaPropertiesConnection());
							
							// System.out.println(ij);
							TableItem a = new TableItem(table, 0);
							Comunidade co = null;
							if (combo.getSelectionIndex() == 0) {
								Integer ij = Integer.valueOf(text.getText());
								co = new Comunidade();
								co.setIdComunidade(ij);
								co = c.pesquisarComunidadeIndice(co);
								a.setText(0, text.getText());
								a.setText(1, co.getNomefantaziaComunidade());
								a.setText(2, co.getCidade().getNomeCidade());
							} else if (combo.getSelectionIndex() == 1) {
								co = new Comunidade();
								co.setNomefantaziaComunidade(text.getText());
								for (Comunidade cc : c.pesquisarListaComunidadeNomeFantasia (co)) {
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
						

						// table.getSelectionIndex()
						// System.out.println(table.getSelectionCount());

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						PropriedadesShell.mensagemDeRetorno(
								"Não foi possível realizar a consulta. Verifique o log e tente novamente.");
						new EjetaException(e1);
					}catch (NullPointerException es) {
						PropriedadesShell.mensagemDeErro("Não foi possível realizar a consulta. Verifique o log e tente novamente.");
						new EjetaException(es);
					}
					
						
					
				}else {
					PropriedadesShell.mensagemDeRetorno("informe o código de busca");
				}
			}
		});
	}
	
}
