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

// Position Rates display class that will display the rates and positions
public class D_PositionRates extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					D_PositionRates frame = new D_PositionRates();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public D_PositionRates() {
		setTitle("Position Rates");
		setResizable(false);
		setBounds(40, 40, 798, 612);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// close button to return to the menu class
		JLabel lblClose = new JLabel("CLOSE");
		lblClose.setForeground(new Color(255, 255, 255));
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setBounds(315, 469, 158, 51);
		contentPane.add(lblClose);
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				B_Menu.main(null);
			}

			public void mouseEntered(MouseEvent e) {
				lblClose.setForeground(Color.cyan);
			}

			public void mouseExited(MouseEvent e) {
				lblClose.setForeground(Color.white);
			}
		});

		// import image background
		JLabel lblBG = new JLabel("");
		lblBG.setIcon(new ImageIcon(D_PositionRates.class.getResource("/images/posrateBG.png")));
		lblBG.setBounds(0, 0, 784, 574);
		contentPane.add(lblBG);
	}

}