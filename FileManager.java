import java.io.*;
import javax.swing.JOptionPane;

public class FileManager {
	private static String mes = "Emp program üzenet";

	public static void CsvReader(File fnev, EmpTM etm) {
		String kod = "", nev = "", szido = "", lakh = "", iq = "", s = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(fnev));
			s = in.readLine();
			s = in.readLine();
			while (s != null) {
				String[] st = s.split(";");
				etm.addRow(new Object[] { new Boolean(false), StoI(st[0]), st[1], st[2], st[3], StoI(st[4])

				});
				s = in.readLine();
			}
			in.close();
			JOptionPane.showMessageDialog(null, "Adatok beolvasva!", mes, 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "CsvReader: " + ioe.getMessage(), mes, 0);

		}
	}

	public static int StoI(String s) {
		int x = -55;
		x = Integer.parseInt(s);
		return x;
	}

	public static void CsvWriter(String fnev, EmpTM etm) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fnev));
			out.println("Kód;Név;Szülidõ;Lakóhely;IQ");
			int rdb = etm.getRowCount();
			int cdb = etm.getColumnCount();
			for (int i = 0; i < rdb; i++) {
				for (int j = 1; j < cdb - 1; j++) {
					out.print("" + etm.getValueAt(i, j) + ";");
				}
				out.println("" + etm.getValueAt(i, cdb - 1));
			}
			out.close();
			JOptionPane.showMessageDialog(null, "Adatok kiírva!", mes, 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "CsvWriter: " + ioe.getMessage(), mes, 0);
		}
	}
}
