package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import shared.*;
import java.rmi.RemoteException;

public class AccountsView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable TblUsers;
	static AccountsView dialog;
	private JTable TblAdmins;
	List<User> users;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new AccountsView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void open(){
		new AccountsView();
	}
	private void loadData(){
		try {
			this.users = Driver.forumClient.forum.getUsers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Create the dialog.
	 */
	public AccountsView() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Accounts");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 640, 290);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 39, 253, 164);
		contentPanel.add(scrollPane);
		
		TblUsers = new JTable();
		TblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TblUsers.setModel(new DefaultTableModel(
			new Object[][] {
				{"user1", Boolean.TRUE},
			},
			new String[] {
				"User", "Enabled"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		TblUsers.getColumnModel().getColumn(0).setResizable(false);
		TblUsers.getColumnModel().getColumn(0).setPreferredWidth(206);
		TblUsers.getColumnModel().getColumn(1).setResizable(false);
		TblUsers.getColumnModel().getColumn(1).setPreferredWidth(53);
		
		scrollPane.setViewportView(TblUsers);
		{
			JLabel lblNewLabel = new JLabel("Users Accounts");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setBounds(112, 14, 101, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(345, 39, 253, 164);
			contentPanel.add(scrollPane_1);
			{
				TblAdmins = new JTable();
				TblAdmins.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				TblAdmins.setModel(new DefaultTableModel(
					new Object[][] {
						{"user1", Boolean.TRUE},
					},
					new String[] {
						"User", "Delete"
					}
				) {
					Class[] columnTypes = new Class[] {
						Object.class, Boolean.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				TblAdmins.getColumnModel().getColumn(0).setResizable(false);
				TblAdmins.getColumnModel().getColumn(0).setPreferredWidth(206);
				TblAdmins.getColumnModel().getColumn(1).setResizable(false);
				TblAdmins.getColumnModel().getColumn(1).setPreferredWidth(53);
				scrollPane_1.setViewportView(TblAdmins);
			}
		}
		{
			JLabel lblAdminAccounts = new JLabel("Admin Accounts");
			lblAdminAccounts.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblAdminAccounts.setBounds(412, 14, 101, 14);
			contentPanel.add(lblAdminAccounts);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setVisible(true);
	}
}
