package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.*;

import shared.*;
public class GroupView {

	private JFrame frame;
	private JTable table;
	static GroupView window;
	List<ForumThread> threads;
	static String[] args;
	static JButton btnNewThread = new JButton("New Thread");
	static JButton btnChat = new JButton("Chat");
	static JButton btnDelete = new JButton("Delete");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new GroupView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		window.args = args;
	}

	/**
	 * Create the application.
	 */
	public GroupView() {
		loadData();
		initialize();
		setControls();
	}
	
	/**
	 * Loads the Data From the server
	 */
	private void loadData(){
		try {
			this.threads = Driver.forumClient.forum.getThreadsByTopic(args[0]);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Closes The Window
	 */
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
	public static void setControls(){
		if (Driver.forumClient.user==null){ //user is not Logged in
			btnNewThread.setEnabled(false);
			btnChat.setEnabled(false);
			btnDelete.setVisible(false);
		}else{								//User is logged in
			btnNewThread.setEnabled(true);
			btnChat.setEnabled(true);
			if (Driver.forumClient.user.type()==Driver.forumClient.user.type().ADMIN){ //If user is an Admin
				btnDelete.setVisible(true);
			}else{
				btnDelete.setVisible(false);
			}
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
		
		JLabel lblAppName = new JLabel("AppName");
		lblAppName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAppName.setBounds(21, 21, 134, 25);
		frame.getContentPane().add(lblAppName);
		
		JLabel lblDescription = new JLabel(args[1]);
		lblDescription.setBackground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(126, 85, 511, 25);
		frame.getContentPane().add(lblDescription);
		
		btnNewThread.setBounds(27, 121, 107, 23);
		frame.getContentPane().add(btnNewThread);
		
		JButton btnReturn = new JButton("Go Back");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
//				MainWindow.main(args);
				MainWindow.setControls();
			}
		});
		btnReturn.setBounds(548, 518, 107, 23);
		frame.getContentPane().add(btnReturn);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		Object[][] rows = new Object[this.threads.size()][];
		String[] header = new String[]{
			"ID", "Title", "Content"};
		
		for (int i=0;i<this.threads.size();i++){
			rows[i] = new String[]{this.threads.get(i).id(),this.threads.get(i).title(),this.threads.get(i).content()};
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
//			new Object[][] {
//				{null, "Group Test", "User1"},
//			},
//			new String[] {
//				"ID", "Group", "User"
//			}
//		) {
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
		table.getColumnModel().getColumn(1).setPreferredWidth(392);
		table.getColumnModel().getColumn(2).setPreferredWidth(136);
		table.setBounds(21, 179, 634, 331);
		frame.getContentPane().add(table);
		
		JLabel lblNewLabel = new JLabel("Threads");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(42, 159, 113, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUser = new JLabel("Author");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUser.setBounds(475, 159, 46, 15);
		frame.getContentPane().add(lblUser);
		
		JLabel lblGroupName = new JLabel(args[2]);
		lblGroupName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGroupName.setBackground(Color.WHITE);
		lblGroupName.setBounds(126, 57, 511, 25);
		frame.getContentPane().add(lblGroupName);
		
		btnChat.setBounds(144, 120, 107, 23);
		frame.getContentPane().add(btnChat);
		
		btnDelete.setBounds(261, 120, 107, 23);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblGroup = new JLabel("Group Name");
		lblGroup.setBounds(31, 64, 85, 14);
		frame.getContentPane().add(lblGroup);
		
		JLabel lblDescrip = new JLabel("Description");
		lblDescrip.setBounds(31, 92, 85, 14);
		frame.getContentPane().add(lblDescrip);
	}
}
