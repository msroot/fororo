package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.ScrollPane;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import javax.swing.ListSelectionModel;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameTest extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static MainWindow window;
	private List<Topic> topics;
	JLabel lblAppName = new JLabel("AppName");
	JLabel lblWelcomeMsg = new JLabel("Welcome Message");
	static JButton btnNewGroup = new JButton("New Group");
	static JButton btnRegister = new JButton("Register");
	static JButton btnLogin = new JButton("Login");
	static JButton btnAccounts = new JButton("Accounts");
	static JButton btnRequests = new JButton("Requests");
	private final ScrollPane scrollPane = new ScrollPane();
	static FrameTest frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FrameTest();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the this.
	 */
	public FrameTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		loadData();
		initialize();
		setControls();
	}
	
	private void loadData(){
		try {
			this.topics = Driver.forumClient.forum.getTopics();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setControls(){
		if (Driver.forumClient.user==null){ //user is not Logged in
			btnNewGroup.setEnabled(false);
			btnAccounts.setVisible(false);
			btnRequests.setVisible(false);
			btnRegister.setEnabled(true);
		}else{
			btnNewGroup.setEnabled(true);
			btnLogin.setText("Logout");
			btnRegister.setEnabled(false);
			if (Driver.forumClient.user.type()==Driver.forumClient.user.type().ADMIN){
				btnAccounts.setVisible(true);
				btnRequests.setVisible(true);
			}else{
				btnAccounts.setVisible(false);
				btnRequests.setVisible(false);
			}
		}
	}
	
	private void initialize() {
		
		this.getContentPane().setBackground(UIManager.getColor("Button.background"));
		this.setResizable(false);
		this.setBounds(100, 100, 679, 580);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		
		lblAppName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAppName.setBounds(21, 21, 134, 25);
		this.getContentPane().add(lblAppName);
		
		lblWelcomeMsg.setBackground(Color.WHITE);
		lblWelcomeMsg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWelcomeMsg.setBounds(53, 57, 542, 52);
		this.getContentPane().add(lblWelcomeMsg);
		
		
		btnNewGroup.setBounds(27, 121, 107, 23);
		this.getContentPane().add(btnNewGroup);
		
		
		btnRegister.setBounds(144, 121, 107, 23);
		this.getContentPane().add(btnRegister);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Entered login action listener");
				if (btnLogin.getText().equalsIgnoreCase("login")){
					LoginDialog.main(null);
				}else{
					Driver.forumClient.user=null;
					btnLogin.setText("Login");
				}
				setControls();
			}
		});
		
		
		btnLogin.setBounds(261, 121, 107, 23);
		this.getContentPane().add(btnLogin);
		
		
		btnAccounts.setBounds(378, 121, 107, 23);
		this.getContentPane().add(btnAccounts);
		
		
		btnRequests.setBounds(495, 121, 107, 23);
		this.getContentPane().add(btnRequests);
	
//		this.getContentPane().add(table);
		
		JLabel lblNewLabel = new JLabel("Group");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(42, 159, 46, 15);
		this.getContentPane().add(lblNewLabel);
		
		JLabel lblUser = new JLabel("Description");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUser.setBounds(188, 159, 99, 15);
		this.getContentPane().add(lblUser);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					//close();
			}
		});
		btnExit.setBounds(548, 521, 107, 23);
		this.getContentPane().add(btnExit);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				//JOptionPane.showMessageDialog(null, table.getModel().getValueAt(row, 1));
				String id = (String) table.getModel().getValueAt(row, 0);
				String name = (String) table.getModel().getValueAt(row, 1);
				String description = (String) table.getModel().getValueAt(row, 2);
				String[] args = new String[]{id,name,description};
				Driver.openGroup(args);
//				close();
				frame.dispose();
				GroupView.main(args);
			}
		});
		table.setBorder(null);
//		this.getContentPane().add(table);
		Object[][] rows = new Object[this.topics.size()][];
		String[] header = new String[]{
			"ID", "Group", "Description"};
		
		for (int i=0;i<this.topics.size();i++){
			rows[i] = new String[]{this.topics.get(i).id(),this.topics.get(i).name(),this.topics.get(i).description()};
		}
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
				table.getColumnModel().getColumn(1).setPreferredWidth(120);
				table.getColumnModel().getColumn(2).setResizable(false);
				table.getColumnModel().getColumn(2).setPreferredWidth(470);
				table.setBounds(20, 180, 610, 315);
				scrollPane.setBounds(20, 180, 620, 315);
				scrollPane.add(table);
				this.getContentPane().add(scrollPane);
	}

}
