package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shared.*;
import client.ForumClient;
import java.rmi.RemoteException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RequestsView extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	List<Topic> topics;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RequestsView dialog = new RequestsView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void open(){
		
		new RequestsView();
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
	 * Create the dialog.
	 */
	public RequestsView() {
		loadData();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Authorise Pending Group Requests");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 595, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPendingGroupRequests = new JLabel("Pending Group Requests");
			lblPendingGroupRequests.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblPendingGroupRequests.setBounds(222, 10, 159, 14);
			contentPanel.add(lblPendingGroupRequests);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(23, 35, 532, 228);
			contentPanel.add(scrollPane);
			{
				List<Topic> activeTopics = new ArrayList<Topic>();
				for (Topic t:this.topics){
					if (!t.isActive()){
						activeTopics.add(t);
					}
				}
				
				Object[][] rows = new Object[activeTopics.size()][];
				String[] header = new String[] { "ID", "Group", "Author","Authorised","Topic" };

				for (int i = 0; i < activeTopics.size(); i++) {
						rows[i] = new Object[] { activeTopics.get(i).id(),
								activeTopics.get(i).name(),
								activeTopics.get(i).description(),activeTopics.get(i).isActive(), activeTopics.get(i)};
				}
				
				table = new JTable();
				table.setModel(new DefaultTableModel(rows, header) {
					boolean[] columnEditables = new boolean[] { false, false, false,true };
					Class[] columnTypes = new Class[] {
							Object.class, Object.class, Object.class, Boolean.class
						};
					
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
					public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
					}
				});
//				table.setModel(new DefaultTableModel(
//					new Object[][] {
//						{null, null, null, null},
//					},
//					new String[] {
//						"id", "Group Name", "Author", "Authorised"
//					}
//				) {
//					Class[] columnTypes = new Class[] {
//						Object.class, Object.class, Object.class, Boolean.class
//					};
//					public Class getColumnClass(int columnIndex) {
//						return columnTypes[columnIndex];
//					}
//					boolean[] columnEditables = new boolean[] {
//						true, false, false, true
//					};
//					public boolean isCellEditable(int row, int column) {
//						return columnEditables[column];
//					}
//				});
				table.getColumnModel().getColumn(0).setPreferredWidth(0);
				table.getColumnModel().getColumn(0).setMinWidth(0);
				table.getColumnModel().getColumn(0).setMaxWidth(0);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(1).setPreferredWidth(347);
				table.getColumnModel().getColumn(2).setResizable(false);
				table.getColumnModel().getColumn(2).setPreferredWidth(132);
				table.getColumnModel().getColumn(3).setResizable(false);
				table.getColumnModel().getColumn(3).setPreferredWidth(63);
				table.getColumnModel().getColumn(4).setPreferredWidth(0);
				table.getColumnModel().getColumn(4).setMinWidth(0);
				table.getColumnModel().getColumn(4).setMaxWidth(0);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
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
		if (ev.getActionCommand().equalsIgnoreCase("save")){
			for (int i=0;i<table.getModel().getColumnCount();i++){
				String id = (String) table.getModel().getValueAt(i, 0);
				Driver.forumClient.forum.approveTopic(Driver.forumClient.user, topic)
			}
			this.dispose();
		}
		if (ev.getActionCommand().equalsIgnoreCase("cancel")){
			this.dispose();
		}
	}

}
