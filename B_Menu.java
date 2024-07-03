package payroll;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

// B_Menu is the class that calls other classes to run their interface
public class B_Menu extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B_Menu frame = new B_Menu();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public B_Menu() {
		setTitle("Menu");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// attendance button that calls the text file of the recorded attendance
		JLabel lblshowAttendance = new JLabel("ATTENDANCE");
		lblshowAttendance.setForeground(new Color(255, 255, 255));
		lblshowAttendance.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblshowAttendance.setHorizontalAlignment(SwingConstants.CENTER);
		lblshowAttendance.setBounds(149, 167, 157, 51);
		contentPane.add(lblshowAttendance);
		lblshowAttendance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				D_Attendance.main(null);
			}

			public void mouseEntered(MouseEvent e) {
				lblshowAttendance.setForeground(Color.cyan);
			}

			public void mouseExited(MouseEvent e) {
				lblshowAttendance.setForeground(Color.white);
			}
		});

		// position rates button that calls the position rate interface
		JLabel lblShowpositionrates = new JLabel("POSITION RATES");
		lblShowpositionrates.setForeground(new Color(255, 255, 255));
		lblShowpositionrates.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowpositionrates.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblShowpositionrates.setBounds(149, 249, 157, 51);
		contentPane.add(lblShowpositionrates);
		lblShowpositionrates.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				D_PositionRates.main(null);
			}

			public void mouseEntered(MouseEvent e) {
				lblShowpositionrates.setForeground(Color.cyan);
			}

			public void mouseExited(MouseEvent e) {
				lblShowpositionrates.setForeground(Color.white);
			}
		});

		// salary report button that calls the text file of the recorded salary report
		JLabel lblShowsalaryreport = new JLabel("SALARY REPORT");
		lblShowsalaryreport.setForeground(new Color(255, 255, 255));
		lblShowsalaryreport.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowsalaryreport.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblShowsalaryreport.setBounds(149, 337, 157, 51);
		contentPane.add(lblShowsalaryreport);
		lblShowsalaryreport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				D_SalaryReport.main(null);
			}

			public void mouseEntered(MouseEvent e) {
				lblShowsalaryreport.setForeground(Color.cyan);
			}

			public void mouseExited(MouseEvent e) {
				lblShowsalaryreport.setForeground(Color.white);
			}
		});

		// record attendance button that calls the salary report interface
		JLabel lblRecordattendance = new JLabel("ATTENDANCE");
		lblRecordattendance.setForeground(new Color(255, 255, 255));
		lblRecordattendance.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecordattendance.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRecordattendance.setBounds(477, 167, 157, 51);
		contentPane.add(lblRecordattendance);
		lblRecordattendance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				R_Attendance.main(null);
			}

			public void mouseEntered(MouseEvent e) {
				lblRecordattendance.setForeground(Color.cyan);
			}

			public void mouseExited(MouseEvent e) {
				lblRecordattendance.setForeground(Color.white);
			}
		});

		// salary calculation button that calls the salary report interface
		JLabel lblComputesalary = new JLabel("SALARY CALCULATION");
		lblComputesalary.setForeground(new Color(255, 255, 255));
		lblComputesalary.setHorizontalAlignment(SwingConstants.CENTER);
		lblComputesalary.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblComputesalary.setBounds(475, 249, 157, 51);
		contentPane.add(lblComputesalary);
		lblComputesalary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				C_Calculator.main(null);
			}

			public void mouseEntered(MouseEvent e) {
				lblComputesalary.setForeground(Color.cyan);
			}

			public void mouseExited(MouseEvent e) {
				lblComputesalary.setForeground(Color.white);
			}
		});

		// salary report button that calls the salary report interface
		JLabel lblRecordsalaryreport = new JLabel("SALARY REPORT");
		lblRecordsalaryreport.setForeground(new Color(255, 255, 255));
		lblRecordsalaryreport.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecordsalaryreport.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRecordsalaryreport.setBounds(477, 337, 157, 51);
		contentPane.add(lblRecordsalaryreport);
		lblRecordsalaryreport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				R_SalaryReport.main(null);
			}

			public void mouseEntered(MouseEvent e) {
				lblRecordsalaryreport.setForeground(Color.cyan);
			}

			public void mouseExited(MouseEvent e) {
				lblRecordsalaryreport.setForeground(Color.white);
			}
		});

		// logout button that calls the ownerLogin interface
		JLabel lblLogout = new JLabel("LOGOUT");
		lblLogout.setForeground(new Color(255, 255, 255));
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLogout.setBounds(317, 468, 157, 51);
		contentPane.add(lblLogout);
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				A_Login.main(null);
			}

			public void mouseEntered(MouseEvent e) {
				lblLogout.setForeground(Color.cyan);
			}

			public void mouseExited(MouseEvent e) {
				lblLogout.setForeground(Color.white);
			}
		});

		// import image background
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(B_Menu.class.getResource("/images/menuBG.png")));
		lblNewLabel.setBounds(0, 0, 790, 574);
		contentPane.add(lblNewLabel);
	}
}