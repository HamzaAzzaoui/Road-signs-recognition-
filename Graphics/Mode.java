package Graphics;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class Mode extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mode frame = new Mode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mode() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 615);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(5, 50, 400, 400);
		contentPane.add(panel);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.RED);
		panel2.setBounds(595, 50, 400, 400);
		contentPane.add(panel2);
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.BLUE);
		panel3.setBounds(5, 460, 990, 150);
		contentPane.add(panel3);
		
		JButton btnChargerImage = new JButton("Charger Image");
		btnChargerImage.setBounds(110, 10, 160, 35);
		btnChargerImage.setBackground(Color.BLACK);
		btnChargerImage.setForeground(Color.WHITE);
		contentPane.add(btnChargerImage);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(500, 50, 2, 400);
		contentPane.add(separator);
	}
}
