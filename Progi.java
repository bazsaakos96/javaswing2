import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Progi extends JFrame {

	private JPanel contentPane;
	private JTextField fnev;
	private JTextField fdb;
	private String forras = "Válasszon!";
	private File fbe;
	private String mes = "FelevesProgi üzenet";
	private EmpTM etm;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	private JTextField cnev;
	private String cel = "Válasszon!";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Progi frame = new Progi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Progi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 472);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBB = new JButton("Bet\u00F6lt\u00E9s");
		btnBB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (forras.equals("Válasszon!")) {
					JOptionPane.showMessageDialog(null, "Elõször válassza kia forrást!", mes, 0);
				}
				if (forras.equals("Helyi .csv fájl")) {
					FileDialog fd = new FileDialog(new Frame(), " ", FileDialog.LOAD);
					fd.setFile("*.csv");
					fd.setVisible(true);
					if (fd.getFile() != null) {

						fbe = new File(fd.getDirectory(), fd.getFile());
						String befnev = fd.getFile();
						fnev.setText(befnev);
						FileManager.CsvReader(fbe, etm);
					}
				}
				fdb.setText("" + etm.getRowCount());
			}
		});
		btnBB.setForeground(new Color(0, 100, 0));
		btnBB.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnBB.setBounds(10, 22, 121, 33);
		contentPane.add(btnBB);

		JButton btnLB = new JButton("Lista");
		btnLB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpList el = new EmpList(Progi.this, etm);
				el.setVisible(true);

			}
		});
		btnLB.setForeground(new Color(0, 100, 0));
		btnLB.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnLB.setBounds(10, 84, 121, 33);
		contentPane.add(btnLB);

		JButton btnB = new JButton("Bez\u00E1r");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // hibamentes kilépés
			}
		});
		btnB.setForeground(new Color(0,100,0));
		btnB.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnB.setBounds(535, 389, 121, 33);
		contentPane.add(btnB);

		JLabel lblF = new JLabel("Forr\u00E1s");
		lblF.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblF.setForeground(new Color(0, 100, 0));
		lblF.setBounds(141, 27, 56, 23);
		contentPane.add(lblF);

		JLabel lblAdatokSzma = new JLabel("Adatok sz\u00E1ma:");
		lblAdatokSzma.setForeground(new Color(0, 100, 0));
		lblAdatokSzma.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblAdatokSzma.setBounds(141, 89, 131, 23);
		contentPane.add(lblAdatokSzma);

		String elem[] = { "Válasszon!", "Helyi .dat fájl", "Helyi .xml fájl", "Helyi .csv fájl", "SQLite DB",
				"Web:JSON fájl" };
		JComboBox jcbf = new JComboBox();
		for (String s : elem)
			jcbf.addItem(s);
		jcbf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forras = (String) jcbf.getSelectedItem();
				fnev.setText(forras);
				fdb.setText("4");
			}
		});

		jcbf.setForeground(new Color(0, 100, 0));
		jcbf.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		jcbf.setBounds(203, 25, 153, 26);
		contentPane.add(jcbf);

		fnev = new JTextField();
		fnev.setFont(new Font("Segoe Script", Font.PLAIN, 12));
		fnev.setForeground(new Color(0, 100, 0));
		fnev.setBounds(366, 26, 290, 26);
		contentPane.add(fnev);
		fnev.setColumns(10);

		fdb = new JTextField();
		fdb.setText("0");
		fdb.setHorizontalAlignment(SwingConstants.RIGHT);
		fdb.setForeground(new Color(0, 100,0 ));
		fdb.setFont(new Font("Segoe Script", Font.PLAIN, 12));
		fdb.setColumns(10);
		fdb.setBounds(268, 85, 88, 33);
		contentPane.add(fdb);

		JButton btnÚA = new JButton("\u00DAj adat");
		btnÚA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kodv = 0;
				if (etm.getRowCount() == 0)
					kodv = 18;
				else
					kodv = (int) etm.getValueAt(etm.getRowCount() - 1, 1);
				EmpNew en = new EmpNew(Progi.this, kodv);
				en.setVisible(true);
				int kilep = en.KiLep();
				if (kilep == 1) {
					Emp newEmp = en.getEmp();
					Date d = newEmp.getSzulido();
					String ddd = sdf.format(d).toString();
					etm.addRow(new Object[] { new Boolean(false), newEmp.getKod(), newEmp.getNev(), ddd,
							newEmp.getLakohely(), newEmp.getIq() });
					fdb.setText("" + etm.getRowCount());
				}

			}
		});
		btnÚA.setForeground(new Color(0, 100, 0));
		btnÚA.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnÚA.setBounds(10, 148, 121, 33);
		contentPane.add(btnÚA);

		JButton modosit = new JButton("M\u00F3dos\u00EDt");
		modosit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		modosit.setForeground(new Color(0, 100, 0));
		modosit.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		modosit.setBounds(10, 209, 121, 33);
		contentPane.add(modosit);

		JButton torol = new JButton("T\u00F6rl\u00E9s");
		torol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		torol.setForeground(new Color(0, 100, 0));
		torol.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		torol.setBounds(10, 266, 121, 33);
		contentPane.add(torol);

		JButton kiir = new JButton("Ki\u00EDr\u00E1s");
		kiir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cel.equals("Válasszon!"))
					SMD("Elõször válassza ki a Cél-t!");
				else if (etm.getRowCount() == 0)
					SMD("Nincs kiírható adat");
				else if (cel.equals("Helyi .csv fájl")) {
					if (cnev.getText().length() == 0)
						SMD("Nincs megadva a cél fájl neve!");
					else {
						FileManager.CsvWriter(cnev.getText().toString(), etm);
					}
				} else if (cel.equals(">>> Forrás") && forras.equals("Helyi .csv fájl")) {
					String kiirfnev = fnev.getText();
					cnev.setText(kiirfnev);
					FileManager.CsvWriter(kiirfnev, etm);
				}

			}
		});
		kiir.setForeground(new Color(0, 100, 0));
		kiir.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		kiir.setBounds(10, 323, 121, 33);
		contentPane.add(kiir);

		JLabel lblCl = new JLabel("C\u00E9l");
		lblCl.setForeground(new Color(0, 100, 0));
		lblCl.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblCl.setBounds(141, 331, 56, 23);
		contentPane.add(lblCl);

		String elem2[] = { "Válasszon!", ">>> Forrás", "Helyi .dat fájl", "Helyi .xml fájl", "Helyi .csv fájl",
				"Helyi .json fájl", "Helyi .pdf fájl", "SQLite DB" };
		JComboBox jcbc = new JComboBox();
		jcbc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cel = (String) jcbc.getSelectedItem();
				if (cel.equals(">>> Forrás") && fnev.getText().equals(""))
					SMD("Nincs megadva a Forrás!");
				if (cel.equals(">>> Forrás") && !fnev.getText().equals(""))
					cnev.setText(fnev.getText());

			}
		});
		for (String s : elem2)
			jcbc.addItem(s);
		jcbc.setForeground(new Color(0, 100, 0));
		jcbc.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		jcbc.setBounds(203, 329, 153, 26);
		contentPane.add(jcbc);

		cnev = new JTextField();
		cnev.setForeground(new Color(102, 0, 102));
		cnev.setFont(new Font("Segoe Script", Font.PLAIN, 12));
		cnev.setColumns(10);
		cnev.setBounds(366, 330, 290, 26);
		contentPane.add(cnev);
		Object emptmn[] = { "Jel", "Kód", "Név", "Szülidõ", "Lakóhely", "IQ" };
		etm = new EmpTM(emptmn, 0);

	}

	public void SMD(String s) {
		JOptionPane.showMessageDialog(null, s, mes, 0);
	}
}
