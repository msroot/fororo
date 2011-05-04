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
import shared.*;
import client.ForumClient;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.*;

public class Main {

	private JFrame frame;
	private JTable table;
	private static Main window;
	private List<ThreadTopic> topics;
	private static ForumClient forumClient;;
	JLabel lblAppName = new JLabel("AppName");
	JLabel lblWelcomeMsg = new JLabel("Welcome Message");
	JButton btnNewGroup = new JButton("New Group");
	JButton btnRegister = new JButton("Register");
	JButton btnLogin = new JButton("Login");
	JButton btnAccounts = new JButton("Accounts");
	JButton btnRequests = new JButton("Requests");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forumClient = new ForumClient();
					forumClient.connect();
					window = new Main();
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
	public Main() {
		loadData();
		initialize();
	}
	
	public static void close(){
		try {
			window.frame.dispose();
			window.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Configure the appearance and enables/disables controls depending on 
	 * status (User logged in, Admin User Logged In, etc)
	 */
	public void setControls(){
		if (forumClient.user!=null){
			
		}
	}
	
	/**
	 * Load The data from DB to be displayed
	 */
	private void loadData(){
		try {
			this.topics = forumClient.forum.getThreadTopics();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		
		lblAppName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAppName.setBounds(21, 21, 134, 25);
		frame.getContentPane().add(lblAppName);
		
		lblWelcomeMsg.setBackground(Color.WHITE);
		lblWelcomeMsg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWelcomeMsg.setBounds(53, 57, 542, 52);
		frame.getContentPane().add(lblWelcomeMsg);
		
		
		btnNewGroup.setBounds(27, 121, 107, 23);
		frame.getContentPane().add(btnNewGroup);
		
		
		btnRegister.setBounds(144, 121, 107, 23);
		frame.getContentPane().add(btnRegister);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login.main(null);
			}
		});
		
		
		btnLogin.setBounds(261, 121, 107, 23);
		frame.getContentPane().add(btnLogin);
		
		
		btnAccounts.setBounds(378, 121, 107, 23);
		frame.getContentPane().add(btnAccounts);
		
		
		btnRequests.setBounds(495, 121, 107, 23);
		frame.getContentPane().add(btnRequests);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		Object[][] topicsTableModel;
		Object[][] rows = new Object[this.topics.size()][];
		String[] header = new String[]{
			"ID", "Group", "User"};
		
		for (int i=0;i<this.topics.size();i++){
			rows[i] = new String[]{this.topics.get(i).id(),this.topics.get(i).name(),this.topics.get(i).description()};
		}
		table.setModel(new DefaultTableModel(
				rows,
				header
			) {
			boolean[] columnEditables = new boolean[] {
				true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
//		table.setModel(new DefaultTableModel(
//				new Object[][] {{null, "Group Test", "User1"},{null, "g2", null},
//				},
//				new String[] {
//					"ID", "Group", "User"
//				}
//			) {
//			boolean[] columnEditables = new boolean[] {
//				true, false, false
//			};
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//		});
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(488);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(136);
		table.setBounds(21, 179, 634, 331);
		frame.getContentPane().add(table);
		
		JLabel lblNewLabel = new JLabel("Group");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(42, 159, 46, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUser.setBounds(529, 159, 46, 15);
		frame.getContentPane().add(lblUser);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					close();
			}
		});
		btnExit.setBounds(548, 521, 107, 23);
		frame.getContentPane().add(btnExit);
	}
}
