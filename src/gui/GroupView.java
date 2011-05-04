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

public class GroupView {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupView window = new GroupView();
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
	public GroupView() {
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
		
		JLabel lblDescription = new JLabel("Group Description");
		lblDescription.setBackground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(53, 84, 542, 25);
		frame.getContentPane().add(lblDescription);
		
		JButton btnNewThread = new JButton("New Thread");
		btnNewThread.setBounds(27, 121, 107, 23);
		frame.getContentPane().add(btnNewThread);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(144, 121, 107, 23);
		frame.getContentPane().add(btnRegister);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(261, 121, 107, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReturn = new JButton("Go Back");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReturn.setBounds(548, 518, 107, 23);
		frame.getContentPane().add(btnReturn);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "Group Test", "User1", "Today"},
			},
			new String[] {
				"ID", "Group", "User", "Date"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(392);
		table.getColumnModel().getColumn(2).setPreferredWidth(136);
		table.getColumnModel().getColumn(3).setPreferredWidth(95);
		table.setBounds(21, 179, 634, 331);
		frame.getContentPane().add(table);
		
		JLabel lblNewLabel = new JLabel("Threads");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(42, 159, 46, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUser = new JLabel("Author");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUser.setBounds(459, 159, 46, 15);
		frame.getContentPane().add(lblUser);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(580, 159, 46, 15);
		frame.getContentPane().add(lblDate);
		
		JLabel lblGroupname = new JLabel("GroupName");
		lblGroupname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGroupname.setBackground(Color.WHITE);
		lblGroupname.setBounds(53, 57, 542, 25);
		frame.getContentPane().add(lblGroupname);
		
		JButton btnChat = new JButton("Chat");
		btnChat.setBounds(378, 121, 107, 23);
		frame.getContentPane().add(btnChat);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(495, 121, 107, 23);
		frame.getContentPane().add(btnDelete);
	}
}
