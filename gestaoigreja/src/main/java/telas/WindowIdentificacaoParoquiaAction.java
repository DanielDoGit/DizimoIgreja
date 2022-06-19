package telas;

import java.sql.SQLException;
import java.text.ParseException;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import beans.Cidade;
import beans.Paroquia;
import comum.EjetaException;
import dao.CidadeDao;
import dao.ParoquiaDao;
import telas.Pesquiar.WindowCidadeRecuperarParoquiaAction;

public class WindowIdentificacaoParoquiaAction extends WindowIdentificacaoParoquia{

	private ParoquiaDao paroquiaDao;
	private Cidade c;
	private Paroquia paroquiaGlobal;
	private boolean teste;
	
	public WindowIdentificacaoParoquiaAction() {
		
		try {
			super.createContents();
			this.verificarExistenciaParoquia();
			this.tratarEventos();
			super.open();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
	}
		
	public ParoquiaDao startaConexao() {
		ParoquiaDao paroquiaDao2 = new ParoquiaDao();
		try {
			paroquiaDao2.setCon(Inicial.startaPropertiesConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
		this.paroquiaDao = paroquiaDao2;
		return this.paroquiaDao;
	}
	
	public void verificarExistenciaParoquia(){
		try {
			if (startaConexao().consultarParoquiaTodos() == null) { 
				this.teste = false;
			}else {
				this.teste = true;
				this.populartelaParoquia(startaConexao().consultarParoquiaTodos());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}catch (NullPointerException e) {
			new EjetaException(e);
		}
		
	}
	
	public void populartelaParoquia(Paroquia p){

		try {
			c = new CidadeDao().consultarCidadePorCodigo(p.getCidade().getIdCidade());
			textCidade.setText(c.getNomeCidade());
			textFantasia.setText(p.getNomeFantasia());
			textRazaoSocial.setText(p.getRazaoSocial());
			textoFormattedText.getControl().setText(p.getCnpj());
			textoFormattedTextTelefone.getControl().setText(p.getTelefone());
			textoFormattedTextCelular.getControl().setText(p.getCelular());
			textEndereco.setText(p.getEndereco());
			textContatos.setText(p.getContatos());
			textNumeroEndereco.setText(Integer.toString(p.getNumeroLogradouro()));
			textBairro.setText(p.getBairro());
			textoFormattedTextCep.getControl().setText(p.getCep());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new EjetaException(e);
		}
		
	}
	
	public void tratarEventos() {
		tltmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				WindowCidadeRecuperarParoquiaAction windowCidadeRecuperarParoquiaAction = new WindowCidadeRecuperarParoquiaAction();
				if (windowCidadeRecuperarParoquiaAction.getC() != null) {
					textCidade.setText(windowCidadeRecuperarParoquiaAction.getC().getNomeCidade());
					c = windowCidadeRecuperarParoquiaAction.getC();
				}else {
					PropriedadesShell.mensagemDeErro("Não foi possivel realizar a busca de cidade. Tente Novamente");
					new EjetaException(new Exception());
				}
				
			}
		});
		
		btnGravar.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				
				if(!textCidade.getText().isEmpty() && !textoFormattedText.getControl().getText().isEmpty() &&
						!textFantasia.getText().isEmpty() && !textNumeroEndereco.getText().isEmpty() &&
						!textEndereco.getText().isEmpty()) {
					System.out.println(teste);
					try {
						if (teste == false) {
							Paroquia paroquia = new Paroquia();
							paroquia.setIndice(1);
							paroquia.setNomeFantasia(textFantasia.getText().trim());
							paroquia.setRazaoSocial(textRazaoSocial.getText().trim());
							paroquia.setCnpj(textoFormattedText.getControl().getText());
							paroquia.setTelefone(textoFormattedTextTelefone.getControl().getText());
							paroquia.setCelular(textoFormattedTextCelular.getControl().getText());
							paroquia.setContatos(textContatos.getText());
							paroquia.setCidade(c);
							paroquia.setEndereco(textEndereco.getText());
							paroquia.setBairro(textBairro.getText());
							if (textNumeroEndereco.getText().isEmpty()) {
								paroquia.setNumeroLogradouro(null);
							}else {
								paroquia.setNumeroLogradouro(Integer.valueOf(textNumeroEndereco.getText()));
							}
							paroquia.setCep(textoFormattedTextCep.getControl().getText());
							startaConexao().cadastrar(paroquia);
							paroquiaDao.getCon().close();
							shell.dispose();
						}else if (teste == true) {
							Paroquia paroquia = new Paroquia();
							paroquia.setIndice(1);
							paroquia.setNomeFantasia(textFantasia.getText().trim());
							paroquia.setRazaoSocial(textRazaoSocial.getText().trim());
							paroquia.setCnpj(textoFormattedText.getControl().getText());
							paroquia.setTelefone(textoFormattedTextTelefone.getControl().getText());
							paroquia.setCelular(textoFormattedTextCelular.getControl().getText());
							paroquia.setContatos(textContatos.getText());
							paroquia.setCidade(c);
							paroquia.setEndereco(textEndereco.getText());
							paroquia.setBairro(textBairro.getText());
							if (textNumeroEndereco.getText().isEmpty()) {
								paroquia.setNumeroLogradouro(null);
							}else {
								paroquia.setNumeroLogradouro(Integer.valueOf(textNumeroEndereco.getText()));
							}
							paroquia.setCep(textoFormattedTextCep.getControl().getText());
							startaConexao().editar(paroquia);
							paroquiaDao.getCon().close();
							shell.dispose();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						new EjetaException(e1);
					}
					
				}else {
					PropriedadesShell.mensagemDeRetorno("Verifique se os campos obrigatorios foram preenchidos");
				}
				
				
			}
		});
	}
	
	
	
	
	
}
