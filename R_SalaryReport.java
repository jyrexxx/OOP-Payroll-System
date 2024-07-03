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
import javax.swing.JCheckBox;
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

// Salary Report Record class that will store the inputs to the text file
public class R_SalaryReport extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtRateperhour;
	private JTextField txtTotalhours;
	private JTextField txtSSSdeduction;
	private JTextField txtGrossSalary;
	private JTextField txtNetsalary;
	private JTextField txtDate;
	private JTextField txtPos;
	private JTextField txtRate;
	private JTextField txtTotal;
	private JTextField txtSsS;
	private JTextField txtLate;
	private JTextField txtOT;
	private JTextField txtLatehrs;
	private JTextField txtOverhrs;
	private JTextField txtLaterate;
	private JTextField txtOTrate;

	private JLabel lblGross;
	private JLabel lblNet;
	private JLabel lblCalculate;
	private JLabel lblBG;

	// These arrays will be used in combo box
	private String[] empNumbers = { "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" };

	private String[] employeeNames = { "", "Shara", "Rosalina", "Remar", "Dongie", "Ronel", "Dennis", "China", "Yasmine",
			"Girly", "John", "Josie", "Analiza", "Herlyn", "Rosemarie", "Kent", "Aldrin", "James", "Ronald", "Rannie",
			"Roger", "Marlon", "Nimrod", "Roberto", "Danny", "Scielo" };

	private String[] employeePositions = { "", "Cashier", "Cashier", "Chef", "Chef", "Chef", "Chef", "Chef", "Kitchen Staff",
			"Kitchen Staff", "Kitchen Staff", "Kitchen Staff", "Kitchen Staff", "Server", "Server", "Server", "Server",
			"Server", "Server", "Server", "Utility", "Utility", "Utility", "Utility", "Parking Attendant",
			"Kitchen Staff" };

	// employee rate
	private String[] empRate = { "", "75", "75", "87", "87", "87", "87", "87", "62", "62", "62", "62", "62", "62", "62", "62",
			"62", "62", "62", "62", "50", "62", "62", "62", "62", "62" };

	// minus 10 late rate
	private String[] lateRate = { "", "65", "65", "77", "77", "77", "77", "77", "52", "52", "52", "52", "52", "52", "52", "52",
			"52", "52", "52", "52", "40", "52", "52", "52", "52", "52" };

	// plus 9 over time rate
	private String[] OTRate = { "", "84", "84", "96", "96", "96", "96", "96", "71", "71", "71", "71", "71", "71", "71", "71",
			"71", "71", "71", "71", "59", "71", "71", "71", "71", "71" };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					R_SalaryReport frame = new R_SalaryReport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public R_SalaryReport() {
		setResizable(false);
		setBounds(0, 0, 1282, 649);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// combo box for employee numbers, names, positions, rate, late rate, overtime rate
		JComboBox<String> cbEmpNo = new JComboBox<>();
		cbEmpNo.setBackground(new Color(217, 217, 255));
		cbEmpNo.setForeground(new Color(20, 37, 107));
		cbEmpNo.setFont(new Font("Tahoma", Font.BOLD, 13));
		cbEmpNo.setFocusable(false);
		cbEmpNo.setBounds(138, 176, 219, 32);
		contentPane.add(cbEmpNo);

		// loop that goes through each element in the empNumbers collection and assigns
		// it to the variable employeeNumber.
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
		
		// compute button that computes the input
		lblCalculate = new JLabel("CALCULATE");
		lblCalculate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalculate.setForeground(Color.WHITE);
		lblCalculate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCalculate.setBounds(1099, 545, 115, 39);
		contentPane.add(lblCalculate);
		lblCalculate.addMouseListener(new MouseAdapter() {
			// if JLabel is clicked this method will execute the try, if failed the catch will be executed to prevent system termination
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					// Integer.parseInt will turn the string to integer
					int rate = Integer.parseInt(txtRate.getText()); // Rate per hour text field
					int total = Integer.parseInt(txtTotal.getText()); // Total hours text field
					int sss = Integer.parseInt(txtSsS.getText()); // SSS text field
					int late = Integer.parseInt(txtLate.getText()); // Late text field
					int OT = Integer.parseInt(txtOT.getText()); // Overtime text field
					int otRate = Integer.parseInt(txtOTrate.getText()); // Overtime rate text field
					int LRate = Integer.parseInt(txtLaterate.getText()); // Late rate text field
					
					int rXt = (rate * total); // rate per hour times total hours
					int overT = OT * otRate; // overtime hours times overtime rate
					int gross = rXt + overT; // sum of rXt plus sum of overT
					int lateRate = late * LRate; // late hours times late rate
					int net = (gross - sss) - lateRate; // sum of gross minus SSS then subtract the lateRate
					
					// turns the integer to string in JLabel
					lblGross.setText(Integer.toString(gross));
					lblNet.setText(Integer.toString(net));
					txtGrossSalary.setText(Integer.toString(gross));
					txtNetsalary.setText(Integer.toString(net));
					txtLatehrs.setText(Integer.toString(late));
					txtOverhrs.setText(Integer.toString(OT));
					txtTotalhours.setText(Integer.toString(total));
					
				// catch method to prevent the user to input non numerical characters
				} catch (NumberFormatException ex) {
					JOptionPane.showConfirmDialog(null, "Input must be numbers.", "Not a Number",
							JOptionPane.CLOSED_OPTION);
				}
			}
			public void mouseEntered(MouseEvent e) {
				lblCalculate.setForeground(Color.cyan);
			}
			public void mouseExited(MouseEvent e) {
				lblCalculate.setForeground(Color.white);
			}
		});
		
		// check box for SSS deduction
		JCheckBox checkbox = new JCheckBox("");
		checkbox.setBounds(764, 561, 21, 23);
		contentPane.add(checkbox);
		checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		// if the check box is selected it will display 325 the value of the SSS deduction if not then 0
				if (checkbox.isSelected()) {
					txtSsS.setText(Integer.toString(325));
					txtSSSdeduction.setText(Integer.toString(325));
				} else {
					txtSsS.setText(Integer.toString(0));
					txtSSSdeduction.setText(Integer.toString(0));
				}
			}

		});

		// file path is where the text file is stored
		String filepath = "salaryreport.txt";
		
		// record button that will execute the if else statement below
		JLabel lblRecord = new JLabel("RECORD");
		lblRecord.setForeground(new Color(255, 255, 255));
		lblRecord.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecord.setBounds(480, 467, 152, 57);
		contentPane.add(lblRecord);
		lblRecord.addMouseListener(new MouseAdapter() {
			// code to store the inputs in the text file
			@Override
			public void mouseClicked(MouseEvent e) {
				String date = txtDate.getText();
				String employee = txtName.getText();
				String empNo = (String) cbEmpNo.getSelectedItem();
				String pos = txtPos.getText();
				String rate = txtRateperhour.getText();
				String total = txtTotalhours.getText();
				String sss = txtSSSdeduction.getText();
				String gross = txtGrossSalary.getText();
				String net = txtNetsalary.getText();
				String OT = txtOTrate.getText();
				String LR = txtLaterate.getText();
				String late = txtLate.getText();
				String over = txtOT.getText();

				// format of the validation message
				String message = "This are the inputs, Are you sure about the inputs?\n\n" + "Date: " + date + "\n"
						+ "No.: " + "EM" + empNo + "\n" + "Name: " + employee + "\n" + "Position: " + pos + "\n"
						+ "Rate: Php " + rate + "\n" + "OT rate: Php " + OT + "\n" + "Late rate: Php " + LR + "\n"
						+ "Total hrs: " + total + "\n" + "Overtime: " + over + "\n" + "Late: " + late + "\n"
						+ "SSS: Php " + sss + "\n" + "Gross: Php " + gross + "\n" + "Net: Php " + net;
				// if the text fields are not filled, it will tell the user to fill the blank text field
				if (date.equals("") && empNo.equals("") && employee.equals("") && pos.equals("") && rate.equals("")
						&& OT.equals("") && LR.equals("") && total.equals("") && over.equals("") && late.equals("")
						&& sss.equals("") && gross.equals("") && net.equals("")) {

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
								writer.write(date + "," + "EM" + empNo + "," + employee + "," + pos + "," + "Php "
										+ rate + "," + "Php " + OT + "," + "Php " + LR + "," + total + " Hrs" + ","
										+ over + "," + late + "," + "Php " + sss + "," + "Php " + gross + "," + "Php "
										+ net + "\r\n");
								JOptionPane.showMessageDialog(null, "Recorded to Salary Report"); // append to textfile

								dispose();
								R_SalaryReport.main(null);
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
		scrollPane.setBounds(715, 0, 551, 308);
		contentPane.add(scrollPane);

		// table format for the data in the text file
		JTable table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setBounds(683, 0, 1500, 500);
		table.setLayout(null);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "No.", "Name", "Position", "Rate/hr", "OT rate", "Late rate", "Total hr/s", "Overtime", "Late", "SSS", "Gross", "Net"
			}
		));
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
		lblBack.setBounds(86, 467, 152, 57);
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

		// gross salary label
		lblGross = new JLabel();
		lblGross.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGross.setBounds(1204, 410, 60, 20);
		lblGross.setForeground(new Color(20, 37, 107));
		contentPane.add(lblGross);

		// name text field
		txtName = new JTextField();
		txtName.setForeground(new Color(20, 37, 107));
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setBounds(147, 222, 197, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		txtName.setOpaque(false);
		txtName.setEditable(false);
		txtName.setBorder(null);

		// rate per hour text field
		txtRateperhour = new JTextField();
		txtRateperhour.setForeground(new Color(20, 37, 107));
		txtRateperhour.setEditable(false);
		txtRateperhour.setOpaque(false);
		txtRateperhour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRateperhour.setColumns(10);
		txtRateperhour.setBorder(null);
		txtRateperhour.setBounds(147, 310, 197, 26);
		contentPane.add(txtRateperhour);

		// total hours text field
		txtTotalhours = new JTextField();
		txtTotalhours.setEditable(false);
		txtTotalhours.setForeground(new Color(20, 37, 107));
		txtTotalhours.setOpaque(false);
		txtTotalhours.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTotalhours.setColumns(10);
		txtTotalhours.setBorder(null);
		txtTotalhours.setBounds(508, 134, 197, 26);
		contentPane.add(txtTotalhours);

		// SSS deduction text field
		txtSSSdeduction = new JTextField();
		txtSSSdeduction.setEditable(false);
		txtSSSdeduction.setForeground(new Color(20, 37, 107));
		txtSSSdeduction.setOpaque(false);
		txtSSSdeduction.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSSSdeduction.setColumns(10);
		txtSSSdeduction.setBorder(null);
		txtSSSdeduction.setBounds(508, 265, 197, 26);
		contentPane.add(txtSSSdeduction);

		// gross salary text field
		txtGrossSalary = new JTextField();
		txtGrossSalary.setEditable(false);
		txtGrossSalary.setForeground(new Color(20, 37, 107));
		txtGrossSalary.setOpaque(false);
		txtGrossSalary.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGrossSalary.setColumns(10);
		txtGrossSalary.setBorder(null);
		txtGrossSalary.setBounds(508, 310, 197, 26);
		contentPane.add(txtGrossSalary);

		// net salary text field
		txtNetsalary = new JTextField();
		txtNetsalary.setEditable(false);
		txtNetsalary.setForeground(new Color(20, 37, 107));
		txtNetsalary.setOpaque(false);
		txtNetsalary.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNetsalary.setColumns(10);
		txtNetsalary.setBorder(null);
		txtNetsalary.setBounds(508, 355, 197, 26);
		contentPane.add(txtNetsalary);

		// date text field
		txtDate = new JTextField();
		txtDate.setOpaque(false);
		txtDate.setForeground(new Color(20, 37, 107));
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDate.setColumns(10);
		txtDate.setBorder(null);
		txtDate.setBounds(148, 134, 197, 26);
		contentPane.add(txtDate);

		// position text field
		txtPos = new JTextField();
		txtPos.setOpaque(false);
		txtPos.setEditable(false);
		txtPos.setForeground(new Color(20, 37, 107));
		txtPos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPos.setColumns(10);
		txtPos.setBorder(null);
		txtPos.setBounds(148, 265, 197, 26);
		contentPane.add(txtPos);

		// rate per hour text field
		txtRate = new JTextField();
		txtRate.setBounds(903, 377, 108, 30);
		txtRate.setOpaque(false);
		txtRate.setBorder(null);
		txtRate.setColumns(10);
		txtRate.setForeground(new Color(20, 37, 107));
		contentPane.add(txtRate);

		// total hours text field
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(903, 423, 108, 30);
		txtTotal.setOpaque(false);
		txtTotal.setBorder(null);
		txtTotal.setForeground(new Color(20, 37, 107));
		contentPane.add(txtTotal);

		// net salary label
		lblNet = new JLabel("");
		lblNet.setForeground(new Color(20, 37, 107));
		lblNet.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNet.setBounds(1204, 460, 60, 20);
		contentPane.add(lblNet);

		// SSS deduction text field
		txtSsS = new JTextField();
		txtSsS.setEditable(false);
		txtSsS.setColumns(10);
		txtSsS.setBounds(903, 558, 108, 30);
		txtSsS.setOpaque(false);
		txtSsS.setBorder(null);
		txtSsS.setForeground(new Color(20, 37, 107));
		contentPane.add(txtSsS);

		// late text field
		txtLate = new JTextField();
		txtLate.setOpaque(false);
		txtLate.setForeground(new Color(20, 37, 107));
		txtLate.setColumns(10);
		txtLate.setBorder(null);
		txtLate.setBounds(903, 469, 108, 30);
		contentPane.add(txtLate);

		// OT rate text field
		txtOT = new JTextField();
		txtOT.setOpaque(false);
		txtOT.setForeground(new Color(20, 37, 107));
		txtOT.setColumns(10);
		txtOT.setBorder(null);
		txtOT.setBounds(903, 513, 108, 30);
		contentPane.add(txtOT);

		// late hours text field
		txtLatehrs = new JTextField();
		txtLatehrs.setEditable(false);
		txtLatehrs.setOpaque(false);
		txtLatehrs.setForeground(new Color(20, 37, 107));
		txtLatehrs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLatehrs.setColumns(10);
		txtLatehrs.setBorder(null);
		txtLatehrs.setBounds(508, 177, 197, 26);
		contentPane.add(txtLatehrs);

		// overtime hours text field
		txtOverhrs = new JTextField();
		txtOverhrs.setEditable(false);
		txtOverhrs.setOpaque(false);
		txtOverhrs.setForeground(new Color(20, 37, 107));
		txtOverhrs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtOverhrs.setColumns(10);
		txtOverhrs.setBorder(null);
		txtOverhrs.setBounds(508, 222, 197, 26);
		contentPane.add(txtOverhrs);

		// late rate text field
		txtLaterate = new JTextField();
		txtLaterate.setOpaque(false);
		txtLaterate.setForeground(new Color(20, 37, 107));
		txtLaterate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLaterate.setEditable(false);
		txtLaterate.setColumns(10);
		txtLaterate.setBorder(null);
		txtLaterate.setBounds(147, 356, 197, 26);
		contentPane.add(txtLaterate);

		// overtime rate text field
		txtOTrate = new JTextField();
		txtOTrate.setOpaque(false);
		txtOTrate.setForeground(new Color(20, 37, 107));
		txtOTrate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtOTrate.setEditable(false);
		txtOTrate.setColumns(10);
		txtOTrate.setBorder(null);
		txtOTrate.setBounds(147, 400, 197, 26);
		contentPane.add(txtOTrate);

		// image import
		lblBG = new JLabel();
		lblBG.setIcon(new ImageIcon(R_SalaryReport.class.getResource("/images/salaryRBG.png")));
		lblBG.setForeground(new Color(255, 255, 255));
		lblBG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBG.setBounds(0, 0, 1269, 610);
		contentPane.add(lblBG);
	}

	// method that will input the name and position of the employee selected in the combo box
	private void setResult(String selectedOption) {
		try {
			// is responsible for converting the selectedOption string into an integer
			// value.
			int index = Integer.parseInt(selectedOption);
			if (index >= 1 && index < empNumbers.length) {
				txtName.setText(employeeNames[index]);
				txtPos.setText(employeePositions[index]);
				txtRateperhour.setText(empRate[index]);
				txtLaterate.setText(lateRate[index]);
				txtOTrate.setText(OTRate[index]);
				txtRate.setText(empRate[index]);
			} else {
				txtName.setText("");
				txtPos.setText("");
			}
		} catch (NumberFormatException e) {
			// Handle the case where selectedOption cannot be parsed as an integer
			txtName.setText("");
			txtPos.setText("");
		}
	}
}