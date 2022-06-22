package telas;

import java.awt.MenuItem;
import java.sql.SQLException;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;

import beans.Cidade;
import beans.Comunidade;
import beans.Fornecedor;
import comum.EjetaException;
import dao.ComunidadeDao;
import dao.FornecedorDao;
import telas.Pesquiar.WindowCidadeRecuperarParoquiaAction;

public class WindowFornecedorAction extends WindowFornecedor {
	
	private FornecedorDao comunidadeDao;
	private Fornecedor c;
	private Cidade cc;
	private String textoChecagem = "Cadastrar", textochecagemEditar = "Editar";
	private Button botaopropriedades;
	private org.eclipse.swt.widgets.MenuItem mntmCadastrar;

	public WindowFornecedorAction(org.eclipse.swt.widgets.MenuItem mntmCadastrar_1) {
		
		try {
			comunidadeDao = new FornecedorDao();
			comunidadeDao.setCon(Inicial.startaPropertiesConnection());
			super.createContents();
			mntmCadastrar = mntmCadastrar_1;
			
			if (mntmCadastrar_1.getText() == textoChecagem) {
				
				if (comunidadeDao.pesquisarComunidadeIndiceMaximo() != null) {
					c = comunidadeDao.pesquisarComunidadeIndiceMaximo(); 
					textCodigo.setText(Integer.toString(c.getIndice()));
				}else {
					c = new Fornecedor();
					c.setIndice(1);
				}
			}
			
			this.tratarEventos();
			super.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		} 
	}
	
	public WindowFornecedorAction(Object cccc , Text t1) {
		
		try {
			comunidadeDao = new FornecedorDao();
			comunidadeDao.setCon(Inicial.startaPropertiesConnection());
			super.createContents();
			if (cccc instanceof Button) {
				Button t = (Button) cccc;
				botaopropriedades = t;
				if (botaopropriedades.getText() == textochecagemEditar) {
					c = new Fornecedor();
					c.setIndice(Integer.valueOf(t1.getText()));
					c = comunidadeDao.populartelaComunidade(c);
					cc = c.getCidade();
					this.popularATela();
				}
			}
			this.tratarEventos();
			super.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		} 
	}
	
	public WindowFornecedorAction(String t1) {

		try {
			comunidadeDao = new FornecedorDao();
			comunidadeDao.setCon(Inicial.startaPropertiesConnection());
			//super.createContents();

			c = new Fornecedor();
			c.setIndice(Integer.valueOf(t1));
			comunidadeDao.excluir(c);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
	}
	
	
	
	public void popularATela() {
		textCodigo.setText(Integer.toString(c.getIndice()));
		textNomeFantasia.setText(c.getNomeFantasia());
		textRazaoSocial.setText(c.getRazaoSocial());
		formattedText.getControl().setText(c.getCnpj());
		textCidade.setText(c.getCidade().getNomeCidade());
		textEndereco.setText(c.getEndereco());
		textNumeroLogradouro.setText(c.getNumeroendereco());
		if (c.getObservacoes()==null) {
			textObservacoes.setText("");
		}else {
			textObservacoes.setText(c.getObservacoes());
		}
		
	}
	
	private void tratarEventos() {
		
		try {
			
			btnLimpar.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					textObservacoes.setText("");
					textNumeroLogradouro.setText("");
					textEndereco.setText("");
					textCidade.setText("");
					textRazaoSocial.setText("");
					formattedText.getControl().setText("");
					textNomeFantasia.setText("");
					
				}
			});
			
			btnGravar.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					if (!textCidade.getText().isEmpty() && !formattedText.getControl().getText().isEmpty()
						&& !textNomeFantasia.getText().isEmpty()) {
						
						if (mntmCadastrar != null) {
							
							if (textoChecagem == mntmCadastrar.getText()) {
								try {
									c.setNomeFantasia(textNomeFantasia.getText());
									c.setRazaoSocial(textRazaoSocial.getText());
									//String x = textCNPJ.getText().replaceAll("\\p{Punct}", "");
									c.setCnpj(formattedText.getControl().getText());
									c.setCidade(cc);
									c.setEndereco(textEndereco.getText());
									c.setNumeroendereco(textNumeroLogradouro.getText());
									c.setObservacoes(textObservacoes.getText());
									comunidadeDao.cadastrar(c);
									
									comunidadeDao.getCon().close();
									shell.dispose();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									PropriedadesShell.mensagemDeErro("ocorreu um erro");
									new EjetaException(e1);
								} 
							} 
							
						}else if(textochecagemEditar == botaopropriedades.getText()) {
							if (!textCidade.getText().isEmpty() && !formattedText.getControl().getText().isEmpty()
									&& !textNomeFantasia.getText().isEmpty()) {
							
							try {
									c.setNomeFantasia(textNomeFantasia.getText());
									c.setRazaoSocial(textRazaoSocial.getText());
									//String x = textCNPJ.getText().replaceAll("\\p{Punct}", "");
									c.setCnpj(formattedText.getControl().getText());
									c.setCidade(cc);
									c.setEndereco(textEndereco.getText());
									c.setNumeroendereco(textNumeroLogradouro.getText());
									c.setObservacoes(textObservacoes.getText());
									comunidadeDao.editar(c);
									
									comunidadeDao.getCon().close();
									shell.dispose();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									PropriedadesShell.mensagemDeErro("ocorreu um erro");
									new EjetaException(e1);
								} 
							}else  {
								PropriedadesShell.mensagemDeRetorno("Verifique se os campos foram preenchidos corretamente");
							}
						}
					}else  {
						PropriedadesShell.mensagemDeRetorno("Verifique se os campos foram preenchidos corretamente");
					}
					
				}
			});
			
			tltmPesquisar.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					WindowCidadeRecuperarParoquiaAction windowCidadeRecuperarParoquiaAction = new WindowCidadeRecuperarParoquiaAction();
					if (windowCidadeRecuperarParoquiaAction.getC() != null) {
						textCidade.setText(windowCidadeRecuperarParoquiaAction.getC().getNomeCidade());
						cc = windowCidadeRecuperarParoquiaAction.getC();
					}else {
						PropriedadesShell.mensagemDeErro("Não foi possivel realizar a busca de cidade. Tente Novamente");
						new EjetaException(new Exception());
					}
					
				};
			} );
		} catch (NullPointerException e) {
			PropriedadesShell.mensagemDeErro("Ocorreu uma exception");
			new EjetaException(e);
		}
		
	}
	
	
	
}
