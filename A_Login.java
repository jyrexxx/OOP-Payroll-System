package payroll;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

// login interface class
public class A_Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField pfPass;
	boolean clicked = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A_Login frame = new A_Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public A_Login() {
		setResizable(false);
		setTitle("Skygazer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// login button
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(494, 387, 152, 56);
		contentPane.add(lblLogin);
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// variables for text field of user name and password
				String pass = pfPass.getText();
				String user = txtUser.getText();

				// instantiation of encapsulation
				O_Encapsulation UserPass = new O_Encapsulation();

				// setCredentials method from the encapsulation class
				UserPass.setCredentials("jyreunix", "12345");
				String username = UserPass.getUsername();
				String password = UserPass.getPassword();

				if (txtUser.getText().equals("") && pfPass.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill up Username and Password");
				} else if (txtUser.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill out username.");
				} else if (pfPass.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill up password");
				} else if (pass.equals(password) && (user.equals(username))) {
					dispose();
					B_Menu.main(null);
				} else if (pass.equals(password) && !user.equals(username)) {
					JOptionPane.showMessageDialog(null, "Incorrect Username", "Login Error", JOptionPane.ERROR_MESSAGE);
					pfPass.setText(null);
				} else if (!pass.equals(password) && user.equals(username)) {
					JOptionPane.showMessageDialog(null, "Incorrect Password", "Login Error", JOptionPane.ERROR_MESSAGE);
					pfPass.setText(null);
				} else if (!pass.equals(password) && !user.equals(username)) {
					JOptionPane.showMessageDialog(null, "Incorrect Username and Password", "Login Error",
							JOptionPane.ERROR_MESSAGE);
					pfPass.setText(null);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Details", "Login Error", JOptionPane.ERROR_MESSAGE);
					pfPass.setText(null);
				}
			}

			public void mouseEntered(MouseEvent e) {
				lblLogin.setForeground(Color.cyan);
			}

			public void mouseExited(MouseEvent e) {
				lblLogin.setForeground(Color.white);
			}
		});

		// show password button
		JButton btnShowpass = new JButton();
		btnShowpass.setBounds(658, 313, 35, 28);
		contentPane.add(btnShowpass);
		btnShowpass.setBackground(new Color(255, 255, 255));
		btnShowpass.setOpaque(false);
		btnShowpass.setContentAreaFilled(false);
		btnShowpass.setBorderPainted(false);
		btnShowpass.setFocusPainted(false);
		btnShowpass.setIcon(new ImageIcon(A_Login.class.getResource("/images/eyeclose.png")));
		btnShowpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (clicked) {
					btnShowpass.setIcon(new ImageIcon(A_Login.class.getResource("/images/eyeopen.png")));
					clicked = false;
					pfPass.setEchoChar((char) 0);
				} else {
					btnShowpass.setIcon(new ImageIcon(A_Login.class.getResource("/images/eyeclose.png")));
					clicked = true;
					pfPass.setEchoChar('•');
				}
			}
		});

		// text field that inputs user name
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUser.setForeground(new Color(20, 37, 107));
		txtUser.setBounds(440, 241, 248, 28);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		txtUser.setOpaque(false);
		txtUser.setBorder(null);

		// password field that inputs password
		pfPass = new JPasswordField();
		pfPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pfPass.setForeground(new Color(20, 37, 107));
		pfPass.setBounds(440, 313, 216, 28);
		contentPane.add(pfPass);
		pfPass.setOpaque(false);
		pfPass.setBorder(null);

		// import background image
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(A_Login.class.getResource("/images/loginBG.png")));
		lblNewLabel.setBounds(0, 0, 784, 574);
		contentPane.add(lblNewLabel);
	}
}