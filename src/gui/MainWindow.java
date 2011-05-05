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

public class MainWindow {

	private JFrame frame;
	private static MainWindow window;
	private List<Topic> topics;
	JLabel lblAppName = new JLabel("AppName");
	JLabel lblWelcomeMsg = new JLabel("Welcome Message");
	static JButton btnNewGroup = new JButton("New Group");
	static JButton btnRegister = new JButton("Register");
	static JButton btnLogin = new JButton("Login");
	static JButton btnAccounts = new JButton("Accounts");
	static JButton btnRequests = new JButton("Requests");
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
	public MainWindow() {
		loadData();
		initialize();
		setControls();
	}

	/**
	 * Closes The Window
	 */
	public static void close() {

		try {
			// window.frame.dispose();
			window.frame.setVisible(false);
			// window.finalize();
			// window.close();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void open() {
		window.frame.setVisible(true);
		setControls();
	}

	/**
	 * Configure the appearance and enables/disables controls depending on
	 * status (User logged in, Admin User Logged In, etc)
	 */
	public static void setControls() {
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(
				UIManager.getColor("Button.background"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 679, 580);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				System.out.println("Entered login action listener");
				if (btnLogin.getText().equalsIgnoreCase("login")) {
					LoginDialog.main(null);
				} else {
					Driver.forumClient.user = null;
					btnLogin.setText("Login");
				}
				setControls();
			}
		});

		btnLogin.setBounds(261, 121, 107, 23);
		frame.getContentPane().add(btnLogin);
		btnAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountsView.main(null);
			}
		});

		btnAccounts.setBounds(378, 121, 107, 23);
		frame.getContentPane().add(btnAccounts);

		btnRequests.setBounds(495, 121, 107, 23);
		frame.getContentPane().add(btnRequests);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		btnExit.setBounds(548, 521, 107, 23);
		frame.getContentPane().add(btnExit);
		// frame.getContentPane().add(table);
		Object[][] rows = new Object[this.topics.size()][];
		String[] header = new String[] { "ID", "Group", "Description" };

		for (int i = 0; i < this.topics.size(); i++) {
			if (topics.get(i).isActive() == true) {
				rows[i] = new String[] { this.topics.get(i).id(),
						this.topics.get(i).name(),
						this.topics.get(i).description() };
			}
		}
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(rows, header) {
			boolean[] columnEditables = new boolean[] { true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			//JOptionPane.showMessageDialog(null, table.getModel().getValueAt(row, 1));
			String id = (String) table.getModel().getValueAt(row, 0);
			String name = (String) table.getModel().getValueAt(row, 1);
			String description = (String) table.getModel().getValueAt(row, 2);
			String[] args = new String[]{id,name,description};
			// Driver.openGroup(args);
			close();
			GroupView.main(args);
			}
			});
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(470);
		scrollPane_1.setBounds(21, 163, 611, 331);

		frame.getContentPane().add(scrollPane_1);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane_1.setViewportView(table);
	}
}
