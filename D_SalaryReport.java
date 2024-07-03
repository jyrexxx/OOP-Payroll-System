package payroll;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Salary Report display class that will create an interface to display the text file in a table format
public class D_SalaryReport extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					D_SalaryReport frame = new D_SalaryReport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public D_SalaryReport() {
		setResizable(false);
		setTitle("Salary Report");
		setBounds(30, 30, 806, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// scroll pane for the table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 792, 551);
		contentPane.add(scrollPane);

		// table format for the data in the text file
		table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setBounds(0, 0, 1500, 500);
		table.setLayout(null);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Date", "No.", "Name", "Position",
				"Rate/hr", "OT rate", "Late rate", "Total hr/s", "Overtime", "Late", "SSS", "Gross", "Net" }));

		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(0).setMinWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setMinWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setMinWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(55);
		table.getColumnModel().getColumn(5).setMinWidth(55);
		table.getColumnModel().getColumn(6).setPreferredWidth(55);
		table.getColumnModel().getColumn(6).setMinWidth(55);
		table.getColumnModel().getColumn(7).setPreferredWidth(60);
		table.getColumnModel().getColumn(7).setMinWidth(60);
		table.getColumnModel().getColumn(8).setPreferredWidth(55);
		table.getColumnModel().getColumn(8).setMinWidth(55);
		table.getColumnModel().getColumn(9).setPreferredWidth(40);
		table.getColumnModel().getColumn(9).setMinWidth(40);
		table.getColumnModel().getColumn(10).setPreferredWidth(65);
		table.getColumnModel().getColumn(10).setMinWidth(65);
		table.getColumnModel().getColumn(11).setPreferredWidth(60);
		table.getColumnModel().getColumn(11).setMinWidth(60);
		table.getColumnModel().getColumn(12).setPreferredWidth(60);
		table.getColumnModel().getColumn(12).setMinWidth(60);
		scrollPane.setViewportView(table);

		// the name of the text file
		String filepath = "salaryreport.txt";
		// Read the text file and populate the table
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] rowData = line.split(",");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(rowData);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}