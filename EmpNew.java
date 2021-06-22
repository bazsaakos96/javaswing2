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
	private JTextField txtNév;
	private JTextField szid;
	private JTextField lak;
	private JTextField txtIq;
	private JTextField kod;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	private String mes= "Emp progi üzenet";
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
		
		JLabel lblKód = new JLabel("K\u00F3d:");
		lblKód.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblKód.setBounds(10, 11, 46, 14);
		getContentPane().add(lblKód);
		
		JLabel lblNev = new JLabel("N\u00E9v:");
		lblNev.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblNev.setBounds(10, 48, 46, 14);
		getContentPane().add(lblNev);
		
		JLabel lblSzül = new JLabel("Sz\u00FClet\u00E9si id\u0151:");
		lblSzül.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblSzül.setBounds(10, 99, 124, 14);
		getContentPane().add(lblSzül);
		
		JLabel lblLak = new JLabel("Lak\u00F3hely:");
		lblLak.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblLak.setBounds(10, 145, 107, 14);
		getContentPane().add(lblLak);
		
		JLabel lblIQ = new JLabel("IQ:");
		lblIQ.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		lblIQ.setBounds(10, 191, 46, 14);
		getContentPane().add(lblIQ);
		
		JButton btnBezár = new JButton("Bez\u00E1r");
		btnBezár.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnBezár.setBackground(new Color(153, 255, 255));
		btnBezár.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnBezár.setBounds(267, 227, 89, 23);
		getContentPane().add(btnBezár);
		
		JButton btnBeszúr = new JButton("Besz\u00FAr");
		btnBeszúr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!filled(kod)) kod.setText(""+(maxKod+1));
				if (!filled(txtNév)) showMD("A Név mezõ üres!", 0);
				else if (!filled(szid)) showMD("A Születési idõ mezõ üres!", 0);
				else if (!goodDate(szid)) showMD("A Születési idõ mezõben hibás adat van!", 0);
				else if (!filled(lak)) showMD("A Lakóhely mezõ üres!", 0);
				else if (!filled(txtIq)) showMD("Az IQ mezõ üres!", 0);
				else if (!goodInt(txtIq)) showMD("Az IQ mezõben hibás adat van!", 0);
				else {
					adat = new Emp(StoI(RF(kod)), RF(txtNév), StoD(RF(szid)), RF(lak),
					StoI(RF(txtIq)));
					showMD("Adat beszúrva!", 1);
					kilep=1;
					dispose();
					setVisible(false);
					}

			}
		});
		btnBeszúr.setBackground(new Color(153, 255, 255));
		btnBeszúr.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnBeszúr.setBounds(83, 228, 89, 23);
		getContentPane().add(btnBeszúr);
		
		JButton btnLekér = new JButton("Lek\u00E9r");
		btnLekér.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kod.setText(""+(maxKod+1));
			}
		});
		btnLekér.setBackground(new Color(153, 255, 255));
		btnLekér.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnLekér.setBounds(282, 8, 89, 23);
		getContentPane().add(btnLekér);
		
		txtNév = new JTextField();
		txtNév.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		txtNév.setBounds(118, 46, 253, 20);
		getContentPane().add(txtNév);
		txtNév.setColumns(10);
		
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
			int x=-55; //tartalék ellenõrzés mert amugy van trycatch csak nah
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
