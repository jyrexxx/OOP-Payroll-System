package payroll;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Attendance Record class that will store the inputs to the text file
public class R_Attendance extends JFrame {

	private JPanel contentPane;
	private JTextField txtDate;
	private JTextField txtName;
	private JTextField txtPos;
	private JTextField txtTimein;
	private JTextField txtTimeout;
	private JTextField txtLatehour;
	private JTextField txtOvertimehour;
	private JLabel background;

	// These arrays will be used in combo box
	private String[] empNumbers = { "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" };

	private String[] employeeNames = { "", "Shara", "Rosalina", "Remar", "Dongie", "Ronel", "Dennis", "Yasmine", "Herlyn",
			"Rosemarie", "Kent", "Aldrin", "James", "Ronald", "Rannie", "Axcel", "Roger", "Marlon", "Nimrod", "Robert",
			"Danny", "Scielo", "Evelyn", "Nimrod", "Robert", "Danny" };

	private String[] employeePositions = { "", "Cashier", "Cashier", "Chef", "Chef", "Chef", "Chef", "Chef", "Server", "Server",
			"Server", "Server", "Server", "Server", "Server", "Server", "Utility", "Utility", "Utility", "Utility",
			"Parking Attendant", "Staff", "Staff", "Kitchen Staff", "Kitchen Staff", "Kitchen Staff" };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					R_Attendance frame = new R_Attendance();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public R_Attendance() {
		setResizable(false);
		setTitle("Attendance");
		setBounds(0, 0, 1282, 634);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// combo box for the employee numbers, names, and positions
		JComboBox<String> cbEmpNo = new JComboBox<>();
		cbEmpNo.setForeground(new Color(20, 37, 107));
		cbEmpNo.setFont(new Font("Tahoma", Font.BOLD, 13));
		cbEmpNo.setBackground(new Color(217, 217, 255));
		cbEmpNo.setFocusable(false);
		cbEmpNo.setBounds(143, 189, 219, 32);
		contentPane.add(cbEmpNo);
		// a loop that goes through each element in the empNumbers collection and
		// assigns it to the variable employeeNumber.
		for (String employeeNumber : empNumbers) {
			cbEmpNo.addItem(employeeNumber);
		}
		cbEmpNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String) cbEmpNo.getSelectedItem();
				setResult(selectedOption);
			}
		});

		// file path is where the text file is stored
		String filepath = "attendance.txt";
		
		// record button that will execute the if else statement below
		JLabel lblRecord = new JLabel("RECORD");
		lblRecord.setForeground(new Color(255, 255, 255));
		lblRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecord.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRecord.setBounds(480, 449, 152, 57);
		contentPane.add(lblRecord);
		lblRecord.addMouseListener(new MouseAdapter() {
			// code for storing the inputs in the text file
			@Override
			public void mouseClicked(MouseEvent e) {
				String date = txtDate.getText();
				String empNo = (String) cbEmpNo.getSelectedItem();
				String pos = txtPos.getText();
				String employee = txtName.getText();
				String timein = txtTimein.getText();
				String timeout = txtTimeout.getText();
				String late = txtLatehour.getText();
				String over = txtOvertimehour.getText();

				// format of the validation message
				String message = "This are the inputs, Are you sure about the inputs?\n\n" + "Date: " + date + "\n"
						+ "No.: " + "EM" + empNo + "\n" + "Name: " + employee + "\n" + "Position: " + pos + "\n"
						+ "Time in: " + timein + "\n" + "Time out: " + timeout + "\n" + "Late: " + late + "\n"
						+ "Overtime: " + over;

				// if the text fields are not filled, it will tell the user to fill the blank text field
				if (date.equals("") && employee.equals("") && empNo.equals("") && pos.equals("") && timein.equals("")
						&& timeout.equals("") && late.equals("") && over.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill up blank text field");

				} else {
				// validation if the user is satisfied with the inputs to be recorded to the text file
					JButton yesbtn = new JButton("Yes");

					yesbtn.setBackground(Color.WHITE);
					yesbtn.setFocusPainted(false);
					yesbtn.setBorderPainted(true);

					JButton nobtn = new JButton("No");

					nobtn.setBackground(Color.WHITE);
					nobtn.setFocusPainted(false);
					nobtn.setBorderPainted(true);

					Object[] options = { yesbtn, nobtn };

					// Set the font for the option pane
					UIManager.put("OptionPane.messageFont", new Font("Open Sans Bold", Font.TRUETYPE_FONT, 15));

					JOptionPane pane = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_OPTION,
							null, options);

					JDialog dialog = pane.createDialog("Confirmation");
					yesbtn.addActionListener(new ActionListener() {
					// code to write or store the inputs in the text file
						@Override
						public void actionPerformed(ActionEvent e) {
							try (FileWriter writer = new FileWriter(filepath, true)) {
								writer.write(date + "," + empNo + "," + employee + "," + pos + "," + timein + ","
										+ timeout + "," + late + "," + over + "\r\n");
								JOptionPane.showMessageDialog(null, "Recorded to Attendance"); // append to text file

								dispose();
								R_Attendance.main(null);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							dialog.dispose();
						}
					});
					nobtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							dialog.dispose();
						}
					});
					dialog.setVisible(true);
				}
			}
			public void mouseEntered(MouseEvent e) {
				lblRecord.setForeground(Color.cyan);
			}
			public void mouseExited(MouseEvent e) {
				lblRecord.setForeground(Color.white);
			}
		});
		
		// scroll pane for the table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(733, 0, 534, 595);
		contentPane.add(scrollPane);
		
		// table format for the data in the text file
		JTable table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Date", "Employee No", "Name",
				"Position", "Time in", "Time out", "Late hrs", "Overtime hrs" }));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(71);
		table.getColumnModel().getColumn(0).setMinWidth(9);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setMinWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(65);
		table.getColumnModel().getColumn(2).setMinWidth(65);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setMinWidth(13);
		table.getColumnModel().getColumn(6).setPreferredWidth(65);
		table.getColumnModel().getColumn(7).setPreferredWidth(85);
		scrollPane.setViewportView(table);

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
		
		// back button to return to the menu class
		JLabel lblBack = new JLabel("BACK");
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setForeground(new Color(255, 255, 255));
		lblBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBack.setBounds(86, 449, 152, 57);
		contentPane.add(lblBack);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				B_Menu.main(null);
			}

			public void mouseEntered(MouseEvent e) {
				lblBack.setForeground(Color.cyan);
			}

			public void mouseExited(MouseEvent e) {
				lblBack.setForeground(Color.white);
			}
		});
		
		// text field for employee name
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setForeground(new Color(20, 37, 107));
		txtName.setBounds(148, 237, 197, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		txtName.setOpaque(false);
		txtName.setBorder(null);

		// text field for time in
		txtTimein = new JTextField();
		txtTimein.setOpaque(false);
		txtTimein.setForeground(new Color(20, 37, 107));
		txtTimein.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTimein.setColumns(10);
		txtTimein.setBorder(null);
		txtTimein.setBounds(527, 148, 197, 26);
		contentPane.add(txtTimein);

		// text field for time out
		txtTimeout = new JTextField();
		txtTimeout.setOpaque(false);
		txtTimeout.setForeground(new Color(20, 37, 107));
		txtTimeout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTimeout.setColumns(10);
		txtTimeout.setBorder(null);
		txtTimeout.setBounds(527, 193, 197, 26);
		contentPane.add(txtTimeout);

		// textfield for late hours
		txtLatehour = new JTextField();
		txtLatehour.setOpaque(false);
		txtLatehour.setForeground(new Color(20, 37, 107));
		txtLatehour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLatehour.setColumns(10);
		txtLatehour.setBorder(null);
		txtLatehour.setBounds(527, 238, 197, 25);
		contentPane.add(txtLatehour);

		// text field for overtime hours
		txtOvertimehour = new JTextField();
		txtOvertimehour.setOpaque(false);
		txtOvertimehour.setForeground(new Color(20, 37, 107));
		txtOvertimehour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtOvertimehour.setColumns(10);
		txtOvertimehour.setBorder(null);
		txtOvertimehour.setBounds(527, 282, 197, 26);
		contentPane.add(txtOvertimehour);

		// text field for date
		txtDate = new JTextField();
		txtDate.setOpaque(false);
		txtDate.setForeground(new Color(20, 37, 107));
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDate.setBorder(null);
		txtDate.setBounds(148, 148, 197, 26);
		txtDate.setColumns(10);
		contentPane.add(txtDate);

		// text field for position
		txtPos = new JTextField();
		txtPos.setEditable(false);
		txtPos.setOpaque(false);
		txtPos.setForeground(new Color(20, 37, 107));
		txtPos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPos.setColumns(10);
		txtPos.setBorder(null);
		txtPos.setBounds(148, 282, 197, 26);
		contentPane.add(txtPos);

		// import background image
		background = new JLabel("");
		background.setIcon(new ImageIcon(R_Attendance.class.getResource("/images/recordABG.png")));
		background.setBounds(0, 0, 1269, 610);
		contentPane.add(background);
	}
	
	// method that will input the name and position of the employee selected in the combo box
	private void setResult(String selectedOption) {
		try {
			// is responsible for converting the selectedOption string into an integer value.
			int index = Integer.parseInt(selectedOption);
			if (index >= 1 && index < empNumbers.length) {
				txtName.setText(employeeNames[index]);
				txtPos.setText(employeePositions[index]);
			}
		} catch (NumberFormatException e) {
			// Handle the case where selectedOption cannot be parsed as an integer
			txtName.setText("");
			txtPos.setText("");
		}
	}
}