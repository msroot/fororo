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
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.AbstractListModel;

public class ChatView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChatView dialog = new ChatView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChatView() {
		setTitle("Chat");
		setBounds(100, 100, 600, 429);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGroup = new JLabel("Group");
			lblGroup.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblGroup.setBounds(10, 11, 46, 14);
			contentPanel.add(lblGroup);
		}
		{
			JLabel lblNewLabel = new JLabel("Group Name");
			lblNewLabel.setBounds(66, 11, 313, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblUsersOnline = new JLabel("Users Online");
			lblUsersOnline.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblUsersOnline.setBounds(456, 11, 79, 14);
			contentPanel.add(lblUsersOnline);
		}
		
		JList lstUsers = new JList();
		lstUsers.setModel(new AbstractListModel() {
			String[] values = new String[] {"User1", "User2", "User3"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		lstUsers.setBounds(422, 36, 152, 256);
		contentPanel.add(lstUsers);
		
		txtInput = new JTextField();
		txtInput.setBounds(20, 314, 445, 20);
		contentPanel.add(txtInput);
		txtInput.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(485, 313, 89, 23);
		contentPanel.add(btnSubmit);
		
		JTextArea txtChat = new JTextArea();
		txtChat.setBounds(20, 36, 375, 256);
		contentPanel.add(txtChat);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
