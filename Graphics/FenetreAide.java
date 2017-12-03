package Graphics;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;


public class FenetreAide extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreAide frame = new FenetreAide();
					frame.setLocation(1000, 200);
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
	public FenetreAide() {
		getContentPane().setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setTitle("Aide");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 320, 430);
		getContentPane().setLayout(null);
		
		JLabel lblBienvenuDnasLapplication = new JLabel("Bienvenue dans l'application EazyTwizy !");
		
		lblBienvenuDnasLapplication.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblBienvenuDnasLapplication.setBounds(25, 10, 400, 15);
		getContentPane().add(lblBienvenuDnasLapplication);
		
		JButton btnOk = new JButton("OK");
		btnOk.setForeground(Color.WHITE);
		btnOk.setBackground(Color.BLACK);
		btnOk.setBounds(100, 380, 120, 35);
		getContentPane().add(btnOk);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textArea.setBounds(25, 52, 400, 100);
		textArea.setText("Mode automatique : Ce mode vous permet \n" +
				"de charger une image, de detecter tous les \n" +
				"panneaux présent sur celle ci, \n" +
				"et de les identifier.");
		textArea.setEditable(false);
		getContentPane().add(textArea);
		
		JTextArea textArea2 = new JTextArea();
		textArea2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textArea2.setBounds(25, 150, 400, 100);
		textArea2.setText("Mode pas a pas : Ce mode vous permet \n" +
				"de charger une image, de traiter \n" +
				"manuellement tous les panneaux présent,\n" +
				"sur celle ci et de les identifier.");
		textArea2.setEditable(false);
		getContentPane().add(textArea2);
		
		JTextArea textArea3 = new JTextArea();
		textArea3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textArea3.setBounds(25, 250, 400, 100);
		textArea3.setText("Mode video : Ce mode vous permet \n" +
				"de charger une video, ou bien \n" +
				"d'utiliser votre webcam, de detecter\n" +
				"tous les panneaux présent, a l'ecran \n" +
				"et de les identifier.");
		textArea3.setEditable(false);
		getContentPane().add(textArea3);
		
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
				dispose();
			}
		});
	}
}
