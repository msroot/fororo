package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import shared.*;

/**
 * Window to be Displayed when creating a new Group/Topic
 * 
 * @author Eduardo Nava
 * 
 */
public class NewGroup extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	JTextArea txtDescription = new JTextArea();

	/**
	 * For Design Time
	 */
	public static void main(String[] args) {
		try {
			NewGroup dialog = new NewGroup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To be Called to display the Window
	 */
	public static void open() {
		new NewGroup();
	}

	/**
	 * Create the dialog.
	 */
	public NewGroup() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Request Group Creation");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name");
			lblName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblName.setBounds(35, 11, 60, 14);
			contentPanel.add(lblName);
		}
		{
			JLabel lblDescription = new JLabel("Description");
			lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblDescription.setBounds(10, 36, 85, 14);
			contentPanel.add(lblDescription);
		}
		{
			txtName = new JTextField();
			txtName.setBounds(115, 8, 255, 20);
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}
		{

			txtDescription.setRows(10);
			txtDescription.setLineWrap(true);
			txtDescription.setBounds(115, 36, 332, 166);
			contentPanel.add(txtDescription);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Request");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getActionCommand().equalsIgnoreCase("ok")) {
			User user = Driver.forumClient.user;
			// String id, String name, String description, boolean isActive,
			// String userName, String created
			Topic topic = new Topic("1", txtName.getText(),
					txtDescription.getText(), false, "", "");
			try {
				Driver.forumClient.forum.createTopic(user, topic);
				this.dispose();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (ev.getActionCommand().equalsIgnoreCase("cancel")) {
			this.dispose();
		}
	}

}
