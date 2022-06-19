package telas.Pesquiar;

import java.sql.SQLException;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import beans.Cidade;
import beans.Paroquia;
import comum.EjetaException;
import dao.CidadeDao;
import telas.Inicial;
import telas.PropriedadesShell;


public class WindowCidadeRecuperarParoquiaAction extends WindowCidadeRecuperarParoquia{

	private  Cidade c;
	
	public  Cidade getC() {
		return this.c;
	}

	public void setC(Cidade c) {
		this.c = c;
	}

	public WindowCidadeRecuperarParoquiaAction() {
		
		try {
			super.createContents();
			this.tratarEventos();
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
					ass = table.getItems();
					
					//System.out.println(ass[table.getSelectionIndex()].getText(1));
					//System.out.println(table.getSelectionIndex());
					c = new Cidade(
							Integer.valueOf(ass[table.getSelectionIndex()].getText(0)),
							ass[table.getSelectionIndex()].getText(1),
							ass[table.getSelectionIndex()].getText(2)
						  );
					shlPesquisar.dispose();
			
				}else {
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
					if (ass.length > 0 ) {
						for (int i = 0; i < ass.length; i++ ) {
							ass[i].setText(0, "");
							ass[i].setText(1, "");
							ass[i].setText(2, "");
							
						}
						table.setItemCount(0);
					}
					

					try {
						
							CidadeDao c = new CidadeDao();
							c.setConnection(Inicial.startaPropertiesConnection());
							
							// System.out.println(ij);
							TableItem a = new TableItem(table, 0);
							Cidade co = null;
							if (combo.getSelectionIndex() == 0) {
								Integer ij = Integer.valueOf(text.getText());
								co = c.consultarCidadePorCodigo(ij);
								a.setText(0, Integer.toString(co.getIdCidade()));
								a.setText(1, co.getNomeCidade());
								a.setText(2, co.getUfCidade());
							} else if (combo.getSelectionIndex() == 1) {
								co = new Cidade();
								co.setNomeCidade(text.getText());
								for (Cidade cc : c.consultarCidadeNome(co)) {
									a.setText(0, Integer.toString(cc.getIdCidade()));
									a.setText(1, cc.getNomeCidade());
									a.setText(2, cc.getUfCidade());
								}
							} else if (combo.getSelectionIndex() == 2) {
								table.setItemCount(0);
								co = new Cidade();
								co.setUfCidade(text.getText());
								TableItem itens = null; 
								for (Cidade cc : c.consultarCidadeUF(co)) {
									itens = new TableItem(table, SWT.ARROW);
									itens.setText(0, Integer.toString(cc.getIdCidade()));
									itens.setText(1, cc.getNomeCidade());
									itens.setText(2, cc.getUfCidade());
								}
							}
						
						

						// table.getSelectionIndex()
						// System.out.println(table.getSelectionCount());

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						PropriedadesShell.mensagemDeRetorno(
								"Não foi possível realizar a consulta. Verifique o log e tente novamente.");
						new EjetaException(e1);
					}
						
					
				}else {
					PropriedadesShell.mensagemDeRetorno("informe o código de busca");
				}
			}
		});
	}
	
}
