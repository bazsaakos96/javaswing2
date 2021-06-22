import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class EmpNew extends JDialog {
	private JTextField txtN�v;
	private JTextField szid;
	private JTextField lak;
	private JTextField txtIq;
	private JTextField kod;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	private String mes= "Emp progi �zenet";
	private Emp adat;
	private int kilep=0;

	

	/**
	 * Create the dialog.
	 */
	public EmpNew(JFrame f, int maxKod) {
		super(f, true);
		setTitle("\u00DAj dolgoz\u00F3 felvitele");
		getContentPane().setBackground(Color.BLUE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblK�d = new JLabel("K\u00F3d:");
		lblK�d.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblK�d.setBounds(10, 11, 46, 14);
		getContentPane().add(lblK�d);
		
		JLabel lblNev = new JLabel("N\u00E9v:");
		lblNev.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblNev.setBounds(10, 48, 46, 14);
		getContentPane().add(lblNev);
		
		JLabel lblSz�l = new JLabel("Sz\u00FClet\u00E9si id\u0151:");
		lblSz�l.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblSz�l.setBounds(10, 99, 124, 14);
		getContentPane().add(lblSz�l);
		
		JLabel lblLak = new JLabel("Lak\u00F3hely:");
		lblLak.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblLak.setBounds(10, 145, 107, 14);
		getContentPane().add(lblLak);
		
		JLabel lblIQ = new JLabel("IQ:");
		lblIQ.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblIQ.setBounds(10, 191, 46, 14);
		getContentPane().add(lblIQ);
		
		JButton btnBez�r = new JButton("Bez\u00E1r");
		btnBez�r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnBez�r.setBackground(new Color(153, 255, 255));
		btnBez�r.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnBez�r.setBounds(267, 227, 89, 23);
		getContentPane().add(btnBez�r);
		
		JButton btnBesz�r = new JButton("Besz\u00FAr");
		btnBesz�r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!filled(kod)) kod.setText(""+(maxKod+1));
				if (!filled(txtN�v)) showMD("A N�v mez� �res!", 0);
				else if (!filled(szid)) showMD("A Sz�let�si id� mez� �res!", 0);
				else if (!goodDate(szid)) showMD("A Sz�let�si id� mez�ben hib�s adat van!", 0);
				else if (!filled(lak)) showMD("A Lak�hely mez� �res!", 0);
				else if (!filled(txtIq)) showMD("Az IQ mez� �res!", 0);
				else if (!goodInt(txtIq)) showMD("Az IQ mez�ben hib�s adat van!", 0);
				else {
					adat = new Emp(StoI(RF(kod)), RF(txtN�v), StoD(RF(szid)), RF(lak),
					StoI(RF(txtIq)));
					showMD("Adat besz�rva!", 1);
					kilep=1;
					dispose();
					setVisible(false);
					}

			}
		});
		btnBesz�r.setBackground(new Color(153, 255, 255));
		btnBesz�r.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnBesz�r.setBounds(83, 228, 89, 23);
		getContentPane().add(btnBesz�r);
		
		JButton btnLek�r = new JButton("Lek\u00E9r");
		btnLek�r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kod.setText(""+(maxKod+1));
			}
		});
		btnLek�r.setBackground(new Color(153, 255, 255));
		btnLek�r.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnLek�r.setBounds(282, 8, 89, 23);
		getContentPane().add(btnLek�r);
		
		txtN�v = new JTextField();
		txtN�v.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		txtN�v.setBounds(118, 46, 253, 20);
		getContentPane().add(txtN�v);
		txtN�v.setColumns(10);
		
		szid = new JTextField();
		szid.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		szid.setColumns(10);
		szid.setBounds(118, 97, 121, 20);
		getContentPane().add(szid);
		
		lak = new JTextField();
		lak.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lak.setColumns(10);
		lak.setBounds(118, 143, 257, 20);
		getContentPane().add(lak);
		
		txtIq = new JTextField();
		txtIq.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		txtIq.setColumns(10);
		txtIq.setBounds(118, 189, 86, 20);
		getContentPane().add(txtIq);
		
		JLabel lblformat = new JLabel("\u00E9\u00E9\u00E9\u00E9.hh.mm");
		lblformat.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblformat.setBounds(249, 100, 107, 14);
		getContentPane().add(lblformat);
		
		kod = new JTextField();
		kod.setEditable(false);
		kod.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		kod.setColumns(10);
		kod.setBounds(118, 8, 138, 20);
		getContentPane().add(kod);
		}
		public String RF(JTextField a) {
			return a.getText().toString();
			}

		public boolean filled(JTextField a) {
			String s = RF(a);
			if (s.length() > 0) return true; else return false;
			}
		public boolean goodDate(JTextField a) {
			String s = RF(a);
			Date testDate = null;
			try {
			testDate = sdf.parse(s);
			} catch (ParseException e){return false;}
			if (sdf.format(testDate).equals(s)) return true;
			else return false;
			}
		public boolean goodInt(JTextField a) {
			String s = RF(a);
			try {
			Integer.parseInt(s); return true;
			} catch (NumberFormatException e){return false;}
			}
		public void showMD(String s, int i){
			JOptionPane.showMessageDialog(null, s, mes, 2);
			}
		public Date StoD(String s){
			Date testDate = null, vid = null;
			try {
			testDate = sdf.parse(s);
			} catch (ParseException e) {return vid;}
			if (!sdf.format(testDate).equals(s)){return vid;}
			return testDate;
			}
		public int StoI(String s){
			int x=-55; //tartal�k ellen�rz�s mert amugy van trycatch csak nah
			x = Integer.parseInt(s);
			return x;
			}
		public Emp getEmp(){
			return adat;
			}
			public int KiLep() {
			return kilep;
			}


}
