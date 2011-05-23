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
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.JTextArea;
import shared.*;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Window to be Displayed when Requesting a Group/Topic Creation
 * 
 * @author Eduardo Nava
 * 
 */
public class ThreadView extends MouseAdapter implements ActionListener {

	private JFrame frmViewThread;
	private JTable table;
	private ForumThread thread;
	private Topic topic;
	static ThreadView window;
	List<ForumThread> threads;
	JLabel lblThreadTitle = new JLabel();
	JLabel lblThreadAuthor = new JLabel();
	JTextArea textArea = new JTextArea();
	ForumThread selectedThread;
	JButton btnReply = new JButton("Reply");
	/**
	 * For Design Time
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ThreadView(null, null);
					window.frmViewThread.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * To be Called to display the Window
	 */
	public static void open(Topic topic, ForumThread thread) {
		new ThreadView(topic, thread);
	}

	/**
	 * Create the application.
	 */
	public ThreadView(Topic topic, ForumThread thread) {
		this.threads = new ArrayList<ForumThread>();
		this.topic = topic;
		this.thread = thread;
		initialize();
		loadTable();

	}

	/**
	 * Displays the Loaded data in the Table
	 */
	private void loadTable() {
		threads.clear();
		this.threads.add(thread);
		readChilds(thread.children);
		for (ForumThread thread : threads) {
			System.out.println(thread.title());
		}

		Object[][] rows = new Object[threads.size()][];
		String[] header = new String[] { "ID", "Title", "Author", "Thread" };

		for (int i = 0; i < threads.size(); i++) {
			rows[i] = new Object[] { threads.get(i).id(),
					threads.get(i).title(), threads.get(i).userName(),
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
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);

		table.getColumnModel().getColumn(3).setPreferredWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	/**
	 * To Load The complete Tree of messages related to the thread
	 * 
	 * @param childs
	 */
	private void readChilds(List<ForumThread> childs) {
		for (ForumThread child : childs) {
			threads.add(child);
			readChilds(child.children);

		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewThread = new JFrame();
		frmViewThread.setVisible(true);
		frmViewThread.setTitle("View Thread");
		frmViewThread.getContentPane().setBackground(
				UIManager.getColor("Button.background"));
		frmViewThread.setResizable(false);
		frmViewThread.setBounds(100, 100, 679, 580);
		frmViewThread.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewThread.getContentPane().setLayout(null);

		JLabel lblAppName = new JLabel("Fororo");
		lblAppName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAppName.setBounds(21, 21, 134, 25);
		frmViewThread.getContentPane().add(lblAppName);

		JLabel lblDescription = new JLabel("Thread Details");
		lblDescription.setBackground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(52, 57, 542, 25);
		frmViewThread.getContentPane().add(lblDescription);

		
		btnReply.setEnabled(false);
		btnReply.setActionCommand("reply");
		btnReply.addActionListener(this);
		btnReply.setBounds(29, 215, 107, 23);
		frmViewThread.getContentPane().add(btnReply);

		JButton btnReturn = new JButton("Go Back");
		btnReturn.setActionCommand("go back");
		btnReturn.addActionListener(this);
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
		btnDelete.setVisible(false);
		btnDelete.setActionCommand("delete");
		btnDelete.addActionListener(this);

		btnDelete.setBounds(146, 215, 107, 23);
		frmViewThread.getContentPane().add(btnDelete);

		lblThreadTitle.setText(this.thread.title());
		lblThreadTitle.setBounds(109, 250, 378, 14);
		frmViewThread.getContentPane().add(lblThreadTitle);

		lblThreadAuthor.setText(this.thread.userName());
		lblThreadAuthor.setBounds(109, 275, 201, 14);
		frmViewThread.getContentPane().add(lblThreadAuthor);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 90, 609, 100);
		frmViewThread.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(29, 300, 609, 187);
		frmViewThread.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(textArea);
		textArea.setEditable(false);
		
				textArea.setText(this.thread.content().trim());

		if (Driver.forumClient.user != null) {
//			btnReply.setEnabled(true);
			if (Driver.forumClient.user.type() == User.Type.ADMIN) {
				btnDelete.setVisible(true);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {

		int row = table.getSelectedRow();
		selectedThread = (ForumThread) table.getModel().getValueAt(row, 3);
		lblThreadAuthor.setText(selectedThread.userName());
		lblThreadTitle.setText(selectedThread.title().trim());
		textArea.setText(selectedThread.content());
		if (Driver.forumClient.user != null) {
			btnReply.setEnabled(true);
		}
	}

	/**
	 * Resets the Table Data
	 */
	private void reloadTable() {
		thread.children.clear();
		try {
			thread = Driver.forumClient.forum.attachDescendantsToThread(thread);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadTable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getActionCommand().equalsIgnoreCase("delete")) {
			Object[] options = { "Yes", "No" };
			int response = JOptionPane.showOptionDialog(frmViewThread,
					"You are about to delete this Message"
							+ "\n Are you sure you want to delete it?",
					"Confirm Deletion", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (response == 0) {
				try {
					Driver.forumClient.forum.deleteThread(
							Driver.forumClient.user, selectedThread);
					JOptionPane.showMessageDialog(frmViewThread,
							"Message Deleted Succesfully");
					GroupView.open(topic);
					frmViewThread.dispose();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		if (ev.getActionCommand().equalsIgnoreCase("reply")) {
			ReplyThreadView.open(selectedThread);
			reloadTable();
		}
		if (ev.getActionCommand().equalsIgnoreCase("go back")) {
			GroupView.open(topic);
			frmViewThread.dispose();
		}

	}
}
