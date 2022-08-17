package comum;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

public class EjetaException {

	private static File file;

	public static void verificarexistenciaArquivo() {
		try {
			file = new File("log.txt");
			if (file.exists()) {
				file.delete();
				file.createNewFile();
			} else {
				file.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			String a = e + "\n Não foi possivel criar o arquivo";

			JOptionPane.showMessageDialog(null, a.trim(), "Erro", 0);
		}
	}

	public EjetaException(Exception e) {
		try {

			FileWriter fw = new FileWriter(file, true);

			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);

			Calendar c = GregorianCalendar.getInstance();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			out.println();
			out.println();
			out.println(formato.format(c.getTime()));
			e.printStackTrace(out);
			out.println(e.getCause());
			out.close();
		} catch (IOException exe) {
			String a = exe + "\n Não foi possivel gravar o log";

			JOptionPane.showMessageDialog(null, a.trim(), "Erro", 0);
		}

	}

}
