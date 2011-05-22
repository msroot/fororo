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
import java.awt.ScrollPane;
import javax.swing.JScrollPane;

/**
 * Displays Information about Groups or Topics
 * 
 * @author Eduardo Nava
 * 
 */
public class GroupView implements ActionListener {

	private JFrame frmGroup;
	GroupView window;
	List<ForumThread> threads;
	static String[] args;
	JButton btnNewThread = new JButton("New Thread");
	JButton btnChat = new JButton("Chat");
	JButton btnDelete = new JButton("Delete");
	JTable table;
	Topic topic;

	/**
	 * To Display it in Design Time
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// static GroupView window;
					GroupView window = new GroupView(null);
					window.frmGroup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// window.args = args;
	}

	/**
	 * Shows the Group Window
	 * 
	 * @param topic
	 *            The Topic or Group to show
	 */
	public static void open(Topic topic) {
		new GroupView(topic);
	}

	/**
	 * Constructor
	 */
	public GroupView(Topic topic) {
		this.topic = topic;
		load();
	}

	/**
	 * Load the Data From DB and Create Controls
	 */
	public void load() {
		loadData();
		initialize();
		setControls();
		frmGroup.setVisible(true);
	}

	/**
	 * Loads the Data From the server
	 */
	private void loadData() {
		try {
			threads = Driver.forumClient.forum.getThreadsByTopic(topic.id());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Closes The Window
	 */
	public void close() {
		try {
			this.frmGroup.dispose();
			// window.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Configure the appearance and enables/disables controls depending on
	 * status (User logged in, Admin User Logged In, etc)
	 */
	public void setControls() {
		if (Driver.forumClient.user == null) { // user is not Logged in
			btnNewThread.setEnabled(false);
			btnChat.setEnabled(false);
			btnDelete.setVisible(false);
		} else { // User is logged in
			btnNewThread.setEnabled(true);
			btnChat.setEnabled(true);
			if (Driver.forumClient.user.type() == Driver.forumClient.user
					.type().ADMIN) { // If user is an Admin
				btnDelete.setVisible(true);
			} else {
				btnDelete.setVisible(false);
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		if (Driver.forumClient.user != null) {
			Driver.setChatTopic(topic);
		}
		frmGroup = new JFrame();
		frmGroup.setTitle("View Group");
		frmGroup.getContentPane().setBackground(
				UIManager.getColor("Button.background"));
		frmGroup.setResizable(false);
		frmGroup.setBounds(100, 100, 679, 580);
		frmGroup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGroup.getContentPane().setLayout(null);

		JLabel lblAppName = new JLabel("Fororo");
		lblAppName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAppName.setBounds(21, 21, 134, 25);
		frmGroup.getContentPane().add(lblAppName);

		JLabel lblDescription = new JLabel(topic.description());
		lblDescription.setBackground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(126, 85, 511, 25);
		frmGroup.getContentPane().add(lblDescription);
		btnNewThread.addActionListener(this);

		btnNewThread.setBounds(27, 121, 107, 23);
		frmGroup.getContentPane().add(btnNewThread);

		JButton btnReturn = new JButton("Go Back");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
				MainWindow.open();

			}
		});
		btnReturn.setBounds(548, 518, 107, 23);
		frmGroup.getContentPane().add(btnReturn);

		JLabel lblGroupName = new JLabel(topic.name());
		lblGroupName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGroupName.setBackground(Color.WHITE);
		lblGroupName.setBounds(126, 57, 511, 25);
		frmGroup.getContentPane().add(lblGroupName);
		btnChat.addActionListener(this);
		btnChat.setActionCommand("chat");
		btnChat.setBounds(144, 120, 107, 23);
		frmGroup.getContentPane().add(btnChat);
		btnDelete.setActionCommand("delete");
		btnDelete.addActionListener(this);

		btnDelete.setBounds(261, 120, 107, 23);
		frmGroup.getContentPane().add(btnDelete);

		JLabel lblGroup = new JLabel("Group Name");
		lblGroup.setBounds(31, 64, 85, 14);
		frmGroup.getContentPane().add(lblGroup);

		JLabel lblDescrip = new JLabel("Description");
		lblDescrip.setBounds(31, 92, 85, 14);
		frmGroup.getContentPane().add(lblDescrip);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 168, 616, 311);
		frmGroup.getContentPane().add(scrollPane);

		table = new JTable();
		loadTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String id = (String) table.getModel().getValueAt(row, 0);
				String title = (String) table.getModel().getValueAt(row, 1);
				String content = (String) table.getModel().getValueAt(row, 2);
				String[] args = new String[] { id, title, content };
				close();
				ForumThread thread = (ForumThread) table.getModel().getValueAt(
						row, 3);
				try {
					thread = Driver.forumClient.forum
							.attachDescendantsToThread(thread);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ThreadView.open(topic, thread);
			}
		});

		scrollPane.setViewportView(table);
		// frame.getContentPane().add(table);
	}

	/**
	 * Displays the Loaded data in the table
	 */
	private void loadTable() {
		Object[][] rows = new Object[threads.size()][];
		String[] header = new String[] { "ID", "Title", "Content", "Thread" };

		for (int i = 0; i < threads.size(); i++) {
			rows[i] = new Object[] { threads.get(i).id(),
					threads.get(i).title(), threads.get(i).content(),
					threads.get(i) };
		}
		table.setModel(new DefaultTableModel(rows, header) {
			boolean[] columnEditables = new boolean[] { true, false, false,
					false };

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
		table.getColumnModel().getColumn(2).setPreferredWidth(400);

		table.getColumnModel().getColumn(3).setPreferredWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		System.out.print("Test");
		if (ev.getActionCommand().equalsIgnoreCase("new thread")) {
			NewThread.open(topic);
			loadData();
			loadTable();
		}
		if (ev.getActionCommand().equalsIgnoreCase("delete")) {
			try {
				Object[] options = { "Yes", "No" };
				int response = JOptionPane
						.showOptionDialog(
								frmGroup,
								"You are about to delete this group"
										+ "\n Are you sure you want to delete it?",
								"Confirm Deletion", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[1]);
				if (response == 0) {
					Driver.forumClient.forum.deleteTopic(
							Driver.forumClient.user, topic);
				}

				frmGroup.dispose();
				MainWindow.open();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ev.getActionCommand().equalsIgnoreCase("go back")) {
			frmGroup.dispose();
			MainWindow.open();
		}
		if (ev.getActionCommand().equalsIgnoreCase("chat")) {
			System.out.print("chat");
			Driver.openChat(topic);
		}

	}
}
