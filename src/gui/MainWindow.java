package gui;

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
import javax.swing.JScrollPane;

public class MainWindow implements ActionListener{

	private JFrame frmMainWindow;
	private static MainWindow window;
	private List<Topic> topics;
	JLabel lblAppName = new JLabel("AppName");
	JLabel lblWelcomeMsg = new JLabel("Welcome Message");
	JButton btnNewGroup = new JButton("New Group");
	JButton btnRegister = new JButton("Register");
	JButton btnLogin = new JButton("Login");
	JButton btnAccounts = new JButton("Accounts");
	 JButton btnRequests = new JButton("Requests");
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JTable table = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					window = new MainWindow();
					window.frmMainWindow.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void open(){
		new MainWindow();
	}
	/**
	 * Create the application.
	 */
	public MainWindow() {
		loadData();
		initialize();
		setControls();
	}

	/**
	 * Closes The Window
	 */
	public void close() {

		try {
			// window.frame.dispose();
			frmMainWindow.setVisible(false);
			// window.finalize();
			// window.close();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static void open() {
//		window.frmMainWindow.setVisible(true);
//		setControls();
//	}

	/**
	 * Configure the appearance and enables/disables controls depending on
	 * status (User logged in, Admin User Logged In, etc)
	 */
	private void setControls() {
		if (Driver.forumClient.user == null) { // user is not Logged in
			btnNewGroup.setEnabled(false);
			btnAccounts.setVisible(false);
			btnRequests.setVisible(false);
			btnRegister.setEnabled(true);
		} else {
			btnNewGroup.setEnabled(true);
			btnLogin.setText("Logout");
			btnRegister.setEnabled(false);
			if (Driver.forumClient.user.type() == Driver.forumClient.user
					.type().ADMIN) {
				btnAccounts.setVisible(true);
				btnRequests.setVisible(true);
			} else {
				btnAccounts.setVisible(false);
				btnRequests.setVisible(false);
			}
		}
	}

	/**
	 * Load The data from DB to be displayed
	 */
	private void loadData() {
		try {
			this.topics = Driver.forumClient.forum.getTopics();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadTable(){
		List<Topic> activeTopics = new ArrayList<Topic>();
		for (Topic t:this.topics){
			if (t.isActive()){
				activeTopics.add(t);
			}
		}
		
		Object[][] rows = new Object[activeTopics.size()][];
		String[] header = new String[] { "ID", "Group", "Description","Topic" };

		for (int i = 0; i < activeTopics.size(); i++) {
				rows[i] = new Object[] { activeTopics.get(i).id(),
						activeTopics.get(i).name(),
						activeTopics.get(i).description(),
						activeTopics.get(i)};
		}
		
		table.setModel(new DefaultTableModel(rows, header) {
			boolean[] columnEditables = new boolean[] { true, false, false,false };
			Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Topic.class
				};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
				}
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});	
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(470);
		table.getColumnModel().getColumn(3).setPreferredWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainWindow = new JFrame();
		frmMainWindow.setVisible(true);
		frmMainWindow.setTitle("Main Window");
		frmMainWindow.getContentPane().setBackground(
				UIManager.getColor("Button.background"));
		frmMainWindow.setResizable(false);
		frmMainWindow.setBounds(100, 100, 679, 580);
		frmMainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMainWindow.getContentPane().setLayout(null);

		lblAppName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAppName.setBounds(21, 21, 134, 25);
		frmMainWindow.getContentPane().add(lblAppName);

		lblWelcomeMsg.setBackground(Color.WHITE);
		lblWelcomeMsg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWelcomeMsg.setBounds(53, 57, 542, 52);
		frmMainWindow.getContentPane().add(lblWelcomeMsg);
//		btnNewGroup.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				NewGroup.main(null);
//			}
//		});
		btnNewGroup.addActionListener(this);

		btnNewGroup.setBounds(27, 121, 107, 23);
		frmMainWindow.getContentPane().add(btnNewGroup);
		btnRegister.setActionCommand("Register");
		btnRegister.addActionListener(this);

		btnRegister.setBounds(144, 121, 107, 23);
		frmMainWindow.getContentPane().add(btnRegister);
		btnLogin.setActionCommand("login");
		btnLogin.addActionListener(this);

		btnLogin.setBounds(261, 121, 107, 23);
		frmMainWindow.getContentPane().add(btnLogin);
		btnAccounts.addActionListener(this);

		btnAccounts.setBounds(378, 121, 107, 23);
		frmMainWindow.getContentPane().add(btnAccounts);
		btnRequests.setActionCommand("requests");
		btnRequests.addActionListener(this);

		btnRequests.setBounds(495, 121, 107, 23);
		frmMainWindow.getContentPane().add(btnRequests);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		btnExit.setBounds(548, 521, 107, 23);
		frmMainWindow.getContentPane().add(btnExit);
		// frame.getContentPane().add(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		loadTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			//JOptionPane.showMessageDialog(null, table.getModel().getValueAt(row, 1));
			String id = (String) table.getModel().getValueAt(row, 0);
			String name = (String) table.getModel().getValueAt(row, 1);
			String description = (String) table.getModel().getValueAt(row, 2);
			String[] args = new String[]{id,name,description};
//			Driver.openGroup(args);
			frmMainWindow.dispose();
			GroupView.open((Topic) table.getModel().getValueAt(row, 3));

//			close();
//			GroupView.main(args);
			}
			});

		scrollPane_1.setBounds(21, 163, 611, 331);
		frmMainWindow.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(table);
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e);
		if (e.getActionCommand().equalsIgnoreCase("new group")){
			NewGroup.open();
		}
		if (e.getActionCommand().equalsIgnoreCase("register")){
			RegisterView.open();
		}
		if (e.getActionCommand().equalsIgnoreCase("login")){
			if (btnLogin.getText().equalsIgnoreCase("login")) {
				LoginDialog.open();
				} else {
				Driver.forumClient.user = null;
				btnLogin.setText("Login");
				}
			setControls();
		}
		if (e.getActionCommand().equalsIgnoreCase("accounts")){
			AccountsView.open();
		}
		if (e.getActionCommand().equalsIgnoreCase("requests")){
			RequestsView.open();
			loadData();
			loadTable();
		}
		
	}
}
