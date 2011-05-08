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

public class NewThread extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTextField txtTitle;
	static NewThread dialog;
	JTextArea txtContent = new JTextArea();
	String[] args;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			dialog = new NewThread();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.args = args;
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the dialog.
	 */
	public NewThread() {
		setTitle("Create new Thread");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Title");
			lblName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblName.setBounds(35, 11, 60, 14);
			contentPanel.add(lblName);
		}
		{
			JLabel lblDescription = new JLabel("Content");
			lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblDescription.setBounds(10, 36, 85, 14);
			contentPanel.add(lblDescription);
		}
		{
			txtTitle = new JTextField();
			txtTitle.setBounds(115, 8, 255, 20);
			contentPanel.add(txtTitle);
			txtTitle.setColumns(10);
		}
		{
			
			txtContent.setRows(10);
			txtContent.setLineWrap(true);
			txtContent.setBounds(115, 36, 332, 166);
			contentPanel.add(txtContent);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//String userId, String title, String content, String topicId
						String topicId = dialog.args[0];
						String title = txtTitle.getText();
						String content = txtContent.getText();
//						String userId = Driver.forumClient.user.id();
						try {
//							Driver.forumClient.forum.createThread("1", title, content, topicId);
							Driver.forumClient.forum.createThread(Driver.forumClient.user, new ForumThread("",title,content,topicId,Driver.forumClient.user.name(),""));
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//GroupView.load();
						dialog.dispose();
					}
				});
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
	}

}
