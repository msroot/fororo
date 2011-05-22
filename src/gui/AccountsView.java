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
import java.util.ArrayList;
import java.util.List;
import shared.*;
import java.rmi.RemoteException;

public class AccountsView extends JDialog implements ActionListener{

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
		loadData();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Accounts");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 640, 309);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 39, 253, 164);
		contentPanel.add(scrollPane);
		
		TblUsers = new JTable();
		TblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		TblUsers.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"user1", Boolean.TRUE},
//			},
//			new String[] {
//				"User", "Enabled"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				Object.class, Boolean.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//			boolean[] columnEditables = new boolean[] {
//				false, true
//			};
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//		});
		
		Object[][] usersRows = new Object[users.size()-1][];
		List<User> admins = new ArrayList<User>();
		String[] header = new String[] {"User Name", "Enabled","User"};
		int j = 0;
		for (int i = 0; i < users.size(); i++) {
			if(!users.get(i).name().equalsIgnoreCase(Driver.forumClient.user.name()))
			{
				usersRows[j] = new Object[] {
				users.get(i).name(),
				users.get(i).isActive(),
				users.get(i)};
				if(users.get(i).type()==User.Type.ADMIN){
//					System.out.println("Found admin");
					admins.add(users.get(i));
				}
			}else j--;
			j++;	
		}
		
		Object[][] adminsRows = new Object[admins.size()][];
		for (int i = 0; i < admins.size(); i++) {
//			if(!users.get(i).name().equalsIgnoreCase(Driver.forumClient.user.name()))
//			{
				adminsRows[i] = new Object[] {
				admins.get(i).name(),
				false,
				admins.get(i)};
//			}
		}
		
		TblUsers = new JTable();
		TblUsers.setModel(new DefaultTableModel(usersRows, header) {
			boolean[] columnEditables = new boolean[] { false, true,false};
			Class[] columnTypes = new Class[] {
					Object.class, Boolean.class,User.class
				};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
			}
		});
		
		TblUsers.getColumnModel().getColumn(0).setResizable(false);
		TblUsers.getColumnModel().getColumn(0).setPreferredWidth(206);
		TblUsers.getColumnModel().getColumn(1).setResizable(false);
		TblUsers.getColumnModel().getColumn(1).setPreferredWidth(53);
		TblUsers.getColumnModel().getColumn(2).setMaxWidth(0);
		TblUsers.getColumnModel().getColumn(2).setMinWidth(0);
		TblUsers.getColumnModel().getColumn(2).setPreferredWidth(0);
		
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
				String[] adminHeader = new String[] {"User Name", "Delete","User"};
				TblAdmins.setModel(new DefaultTableModel(adminsRows, adminHeader) {
					boolean[] columnEditables = new boolean[] { false, true,false};
					Class[] columnTypes = new Class[] {
							Object.class, Boolean.class, User.class
						};
					
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
					public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
					}
				});
				TblAdmins.getColumnModel().getColumn(0).setResizable(false);
				TblAdmins.getColumnModel().getColumn(0).setPreferredWidth(206);
				TblAdmins.getColumnModel().getColumn(1).setResizable(false);
				TblAdmins.getColumnModel().getColumn(1).setPreferredWidth(53);
				TblAdmins.getColumnModel().getColumn(2).setMaxWidth(0);
				TblAdmins.getColumnModel().getColumn(2).setMinWidth(0);
				TblAdmins.getColumnModel().getColumn(2).setPreferredWidth(0);
				scrollPane_1.setViewportView(TblAdmins);
			}
		}
		{
			JLabel lblAdminAccounts = new JLabel("Admin Accounts");
			lblAdminAccounts.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblAdminAccounts.setBounds(412, 14, 101, 14);
			contentPanel.add(lblAdminAccounts);
		}
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.setBounds(509, 214, 89, 23);
		contentPanel.add(btnNewButton);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(this);
				okButton.setActionCommand("save");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getActionCommand().equalsIgnoreCase("cancel")){
			this.dispose();
		}
		if (ev.getActionCommand().equalsIgnoreCase("save")){
			for (int i=0;i<TblUsers.getModel().getRowCount();i++){
				System.out.print("Entered user");
				User user = (User)TblUsers.getModel().getValueAt(i, 2);
				user.isActive((Boolean)TblUsers.getModel().getValueAt(i, 1));
				try {
					Driver.forumClient.forum.updateUser(Driver.forumClient.user, user);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i=0;i<TblAdmins.getModel().getRowCount();i++){
				if ((Boolean)TblAdmins.getModel().getValueAt(i, 1)){
					System.out.print("Entered admin");
					User admin = (User)TblAdmins.getModel().getValueAt(i, 2);
					try {
						Driver.forumClient.forum.deleteUser(Driver.forumClient.user, admin);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			this.dispose();
		}
		
	}
}
