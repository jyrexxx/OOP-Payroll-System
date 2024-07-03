package payroll;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

// Calculator class will assist the owner to calculate
public class C_Calculator extends JFrame {

	private JPanel contentPane;
	private JTextField txtRateperhour;
	private JTextField txtTotalhours;
	private JLabel lblNetsalary;
	private JLabel lblGrossSalary;
	private JTextField txtSsS;
	private JTextField txtLate;
	private JTextField txtOT;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					C_Calculator frame = new C_Calculator();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public C_Calculator() {
		setTitle("Calculation");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 610);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// compute button that computes the input
		JLabel lblCompute = new JLabel("CALCULATE");
		lblCompute.setForeground(new Color(255, 255, 255));
		lblCompute.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompute.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCompute.setBounds(525, 467, 174, 59);
		contentPane.add(lblCompute);
		lblCompute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// if JLabel is clicked this method will execute the try, if failed the catch will be executed to prevent system termination
				try {
					// Integer.parseInt will turn the string to integer
					int rate = Integer.parseInt(txtRateperhour.getText()); // rate per hour text field
					int total = Integer.parseInt(txtTotalhours.getText()); // total hours text field
					int sss = Integer.parseInt(txtSsS.getText()); // SSS text field
					int late = Integer.parseInt(txtLate.getText()); // Late text field
					int OT = Integer.parseInt(txtOT.getText()); // Overtime text field

					int rXt = (rate * total); // rate per hour times total hours
					int overT = (rate + 9) * OT; // rate plus 9 times overtime hours
					int gross = rXt + overT; // sum of rXt plus sum of overT
					int lateRate = (rate - 10) * late; // rate minus 10 times late hours
					int net = (gross - sss) - lateRate; // sum of gross minus SSS then subtract the late rate to the sum

					// turns the integer to string in JLabel
					lblGrossSalary.setText(Integer.toString(gross));
					lblNetsalary.setText(Integer.toString(net));
					
				// catch method to prevent the user to input non numerical characters	
				} catch (NumberFormatException ex) {
					JOptionPane.showConfirmDialog(null, "Input must be numbers.", "Not a Number",
							JOptionPane.CLOSED_OPTION);
				}
			}
			public void mouseEntered(MouseEvent e) {
				lblCompute.setForeground(Color.cyan);
			}
			public void mouseExited(MouseEvent e) {
				lblCompute.setForeground(Color.white);
			}
		});

		// check box for SSS deduction
		JCheckBox checkbox = new JCheckBox("");
		checkbox.setBounds(40, 400, 21, 33);
		contentPane.add(checkbox);
		checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		// if the check box is selected it will display 325 the value of the SSS deduction if not then 0
				if (checkbox.isSelected()) {
					txtSsS.setText(Integer.toString(325));
				} else {
					txtSsS.setText(Integer.toString(0));
				}
			}
		});

		// back button
		JLabel lblBack = new JLabel("BACK");
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setForeground(Color.WHITE);
		lblBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBack.setBounds(65, 467, 174, 59);
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

		// rate per hour text field
		txtRateperhour = new JTextField();
		txtRateperhour.setForeground(new Color(20, 37, 107));
		txtRateperhour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRateperhour.setBounds(214, 146, 170, 39);
		contentPane.add(txtRateperhour);
		txtRateperhour.setColumns(10);
		txtRateperhour.setOpaque(false);
		txtRateperhour.setBorder(null);

		// total hours text field
		txtTotalhours = new JTextField();
		txtTotalhours.setOpaque(false);
		txtTotalhours.setForeground(new Color(20, 37, 107));
		txtTotalhours.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTotalhours.setColumns(10);
		txtTotalhours.setBorder(null);
		txtTotalhours.setBounds(214, 208, 170, 39);
		contentPane.add(txtTotalhours);

		// net salary JLabel will hold the sum of the calculated salary
		lblNetsalary = new JLabel();
		lblNetsalary.setForeground(new Color(20, 37, 107));
		lblNetsalary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNetsalary.setBounds(642, 261, 57, 26);
		contentPane.add(lblNetsalary);

		// gross salary JLabel will hold the sum of the calculated salary
		lblGrossSalary = new JLabel();
		lblGrossSalary.setForeground(new Color(20, 37, 107));
		lblGrossSalary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGrossSalary.setBounds(642, 190, 57, 26);
		contentPane.add(lblGrossSalary);

		// SSS text field
		txtSsS = new JTextField();
		txtSsS.setEditable(false);
		txtSsS.setOpaque(false);
		txtSsS.setForeground(new Color(20, 37, 107));
		txtSsS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSsS.setColumns(10);
		txtSsS.setBorder(null);
		txtSsS.setBounds(214, 394, 170, 39);
		contentPane.add(txtSsS);

		// Late text field
		txtLate = new JTextField();
		txtLate.setOpaque(false);
		txtLate.setForeground(new Color(20, 37, 107));
		txtLate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLate.setColumns(10);
		txtLate.setBorder(null);
		txtLate.setBounds(214, 270, 170, 39);
		contentPane.add(txtLate);

		// Overtime text field
		txtOT = new JTextField();
		txtOT.setOpaque(false);
		txtOT.setForeground(new Color(20, 37, 107));
		txtOT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtOT.setColumns(10);
		txtOT.setBorder(null);
		txtOT.setBounds(214, 331, 170, 39);
		contentPane.add(txtOT);

		// import background image
		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(C_Calculator.class.getResource("/images/salarycalcBG.png")));
		lblBG.setBounds(0, 0, 784, 574);
		contentPane.add(lblBG);
	}
}