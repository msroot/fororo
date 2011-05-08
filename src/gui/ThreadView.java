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

	private JFrame frmViewThread;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThreadView window = new ThreadView();
					window.frmViewThread.setVisible(true);
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
		frmViewThread = new JFrame();
		frmViewThread.setTitle("View Thread");
		frmViewThread.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frmViewThread.setResizable(false);
		frmViewThread.setBounds(100, 100, 679, 580);
		frmViewThread.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewThread.getContentPane().setLayout(null);
		
		JLabel lblAppName = new JLabel("AppName");
		lblAppName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAppName.setBounds(21, 21, 134, 25);
		frmViewThread.getContentPane().add(lblAppName);
		
		JLabel lblDescription = new JLabel("Thread Details");
		lblDescription.setBackground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(52, 57, 542, 25);
		frmViewThread.getContentPane().add(lblDescription);
		
		JButton btnReply = new JButton("Reply");
		btnReply.setBounds(29, 215, 107, 23);
		frmViewThread.getContentPane().add(btnReply);
		
		JButton btnReturn = new JButton("Go Back");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReturn.setBounds(548, 518, 107, 23);
		frmViewThread.getContentPane().add(btnReturn);
		
		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(44, 249, 76, 15);
		frmViewThread.getContentPane().add(lblNewLabel);
		
		JLabel lblUser = new JLabel("Author");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUser.setBounds(44, 275, 46, 15);
		frmViewThread.getContentPane().add(lblUser);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(146, 215, 107, 23);
		frmViewThread.getContentPane().add(btnDelete);
		
		table = new JTable();
		table.setBounds(52, 90, 542, 100);
		frmViewThread.getContentPane().add(table);
		
		JLabel lblThreadTitle = new JLabel("Thread Title");
		lblThreadTitle.setBounds(109, 250, 378, 14);
		frmViewThread.getContentPane().add(lblThreadTitle);
		
		JLabel lblThreadAuthor = new JLabel("Thread Title");
		lblThreadAuthor.setBounds(109, 275, 201, 14);
		frmViewThread.getContentPane().add(lblThreadAuthor);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(28, 312, 620, 172);
		frmViewThread.getContentPane().add(textArea);
	}
}
