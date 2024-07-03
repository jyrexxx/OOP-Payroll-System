package payroll;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Attendance display class that will create an interface to display the text file in a table format
public class D_Attendance extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					D_Attendance frame = new D_Attendance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public D_Attendance() {
		setTitle("Attendance");
		setBounds(30, 30, 699, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// scroll pane for the table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 685, 900);
		contentPane.add(scrollPane);

		// table format for the data in the text file
		table = new JTable();
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Date", "Employee No.", "Employee name",
				"Position", "Time in", "Time out", "Late Hour", "Overtime Hour" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(77);
		table.getColumnModel().getColumn(2).setPreferredWidth(93);
		table.getColumnModel().getColumn(7).setPreferredWidth(82);
		scrollPane.setViewportView(table);

		// the name of the text file
		String filepath = "attendance.txt";
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