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

public class GroupView {

	private JFrame frmGroup;
	GroupView window;
	List<ForumThread> threads;
	String[] args;
	JButton btnNewThread = new JButton("New Thread");
	JButton btnChat = new JButton("Chat");
	JButton btnDelete = new JButton("Delete");
	static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					static GroupView window;
					GroupView window = new GroupView(null);
					window.frmGroup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		window.args = args;
	}

	/**
	 * Create the application.
	 */
	public GroupView(String[] args) {
		this.args = args;
		load();
	}

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
			threads = Driver.forumClient.forum.getThreadsByTopic(args[0]);
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
		frmGroup = new JFrame();
		frmGroup.setTitle("View Group");
		frmGroup.getContentPane().setBackground(
				UIManager.getColor("Button.background"));
		frmGroup.setResizable(false);
		frmGroup.setBounds(100, 100, 679, 580);
		frmGroup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGroup.getContentPane().setLayout(null);

		JLabel lblAppName = new JLabel("AppName");
		lblAppName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAppName.setBounds(21, 21, 134, 25);
		frmGroup.getContentPane().add(lblAppName);

		JLabel lblDescription = new JLabel(args[2]);
		lblDescription.setBackground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(126, 85, 511, 25);
		frmGroup.getContentPane().add(lblDescription);
		btnNewThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				NewThread.main(this.window.args);
			}
		});

		btnNewThread.setBounds(27, 121, 107, 23);
		frmGroup.getContentPane().add(btnNewThread);

		JButton btnReturn = new JButton("Go Back");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();

				// MainWindow.main(args);
				MainWindow.open();
//				Driver.close();
				// MainWindow.setControls();
				// FrameTest.main(null);
			}
		});
		btnReturn.setBounds(548, 518, 107, 23);
		frmGroup.getContentPane().add(btnReturn);

		JLabel lblGroupName = new JLabel(args[1]);
		lblGroupName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGroupName.setBackground(Color.WHITE);
		lblGroupName.setBounds(126, 57, 511, 25);
		frmGroup.getContentPane().add(lblGroupName);
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChatView.main(null);
			}
		});

		btnChat.setBounds(144, 120, 107, 23);
		frmGroup.getContentPane().add(btnChat);

		btnDelete.setBounds(261, 120, 107, 23);
		frmGroup.getContentPane().add(btnDelete);

		JLabel lblGroup = new JLabel("Group Name");
		lblGroup.setBounds(31, 64, 85, 14);
		frmGroup.getContentPane().add(lblGroup);

		JLabel lblDescrip = new JLabel("Description");
		lblDescrip.setBounds(31, 92, 85, 14);
		frmGroup.getContentPane().add(lblDescrip);

		Object[][] rows = new Object[threads.size()][];
		String[] header = new String[] { "ID", "Title", "Content" };

		for (int i = 0; i < threads.size(); i++) {
			rows[i] = new String[] { threads.get(i).id(),
					threads.get(i).title(), threads.get(i).content() };
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 168, 616, 311);
		frmGroup.getContentPane().add(scrollPane);

		table = new JTable();
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
				// JOptionPane.showMessageDialog(null,
				// table.getModel().getValueAt(row, 1));
				String id = (String) table.getModel().getValueAt(row, 0);
				String title = (String) table.getModel().getValueAt(row, 1);
				String content = (String) table.getModel().getValueAt(row, 2);
				String[] args = new String[] { id, title, content };
				// Driver.openGroup(args);
				close();
				ThreadView.main(args);
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(table);
		// frame.getContentPane().add(table);
	}
}
