package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.JTextArea;

public class ThreadView {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThreadView window = new ThreadView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ThreadView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 679, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAppName = new JLabel("AppName");
		lblAppName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAppName.setBounds(21, 21, 134, 25);
		frame.getContentPane().add(lblAppName);
		
		JLabel lblDescription = new JLabel("Thread Details");
		lblDescription.setBackground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(52, 57, 542, 25);
		frame.getContentPane().add(lblDescription);
		
		JButton btnReply = new JButton("Reply");
		btnReply.setBounds(29, 215, 107, 23);
		frame.getContentPane().add(btnReply);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(146, 215, 107, 23);
		frame.getContentPane().add(btnRegister);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(263, 215, 107, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReturn = new JButton("Go Back");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReturn.setBounds(548, 518, 107, 23);
		frame.getContentPane().add(btnReturn);
		
		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(44, 249, 76, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUser = new JLabel("Author");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUser.setBounds(44, 275, 46, 15);
		frame.getContentPane().add(lblUser);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(44, 301, 46, 15);
		frame.getContentPane().add(lblDate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(380, 215, 107, 23);
		frame.getContentPane().add(btnDelete);
		
		table = new JTable();
		table.setBounds(52, 90, 542, 100);
		frame.getContentPane().add(table);
		
		JLabel lblThreadTitle = new JLabel("Thread Title");
		lblThreadTitle.setBounds(109, 250, 378, 14);
		frame.getContentPane().add(lblThreadTitle);
		
		JLabel label = new JLabel("Thread Title");
		label.setBounds(109, 275, 201, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Thread Title");
		label_1.setBounds(109, 302, 115, 14);
		frame.getContentPane().add(label_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(21, 335, 634, 172);
		frame.getContentPane().add(textArea);
	}
}
