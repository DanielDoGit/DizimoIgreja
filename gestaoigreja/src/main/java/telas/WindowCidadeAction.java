package telas;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import beans.Cidade;
import comum.EjetaException;
import dao.CidadeDao;
import dao.FabricaConexoes;

public class WindowCidadeAction extends WindowCidade{

	
	
	public WindowCidadeAction() {
		
		//Fazer a parte de permissões aqui
		open();
		CarregarEventos();

	}
	
	public void CarregarEventos() {
		
		getBtnNewButtonCadastrar().addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				Cidade cidade = null;
				try {
					cidade = new Cidade();
					cidade.setNomeCidade(getText_1().getText());
					cidade.setUfCidade(getText_2().getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}

				try {
					CidadeDao cidadeDao = new CidadeDao();
					cidadeDao.setConnection(Inicial.startaPropertiesConnection());
					cidadeDao.cadastrar(cidade);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					new EjetaException(e2);
				}
			}
		
		});
		
		getBtnNewButtonEditar().addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				Cidade cidade = null;
				try {
					cidade = new Cidade();
					cidade.setNomeCidade(getText_1().getText());
					cidade.setUfCidade(getText_2().getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					new EjetaException(e1);
				}

				try {
					CidadeDao cidadeDao = new CidadeDao();
					cidadeDao.setConnection(Inicial.startaPropertiesConnection());
					cidadeDao.cadastrar(cidade);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					new EjetaException(e2);
				}
			}
			
		});
		
	}
	
	
	
	
	
}
