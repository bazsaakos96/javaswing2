import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpMod extends JDialog {
	private JTable table;
	private EmpTM etm;



	/**
	 * Create the dialog.
	 */
	public EmpMod(JFrame f, EmpTM betm) {
		super (f,"Dolgozók listája", true);
		etm=betm;
		setBounds(100, 100, 724, 438);
		getContentPane().setLayout(null);
		
		JButton btnB = new JButton("Bezár");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); setVisible(false);
				

			}
		});
		btnB.setBounds(304, 365, 89, 23);
		getContentPane().add(btnB);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 54, 614, 280);
		getContentPane().add(scrollPane);
		
		table = new JTable(etm);
		scrollPane.setViewportView(table);
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
		tc = table.getColumnModel().getColumn(i);
		if (i==0 || i==1 || i==5) tc.setPreferredWidth(30);
		else {tc.setPreferredWidth(100);}
		}
		table.setAutoCreateRowSorter(true);
		TableRowSorter<EmpTM> trs =
		(TableRowSorter<EmpTM>)table.getRowSorter();
		trs.setSortable(0, false);
		
	}
	
}
