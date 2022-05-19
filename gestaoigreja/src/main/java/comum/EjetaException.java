package comum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import java.io.IOException;

public abstract class EjetaException {
		
	public EjetaException(Exception e) {
		try{
			
				FileWriter fw = new FileWriter("log.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw);
			    
			    Calendar c = GregorianCalendar.getInstance();
			    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			    out.println();
			    out.println();
			    out.println(formato.format(c.getTime()));
			    out.println(e.toString());
			    
			    out.close();
			} catch (IOException exe) {
				String a = exe+"\n Não foi possivel gravar o log";
				
				JOptionPane.showMessageDialog(null, a.trim(), "Erro", 0);
			}
		
	}

}
