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
import shared.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
public class ChatView extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtInput;
	static JLabel lblGroupName = new JLabel("Group Name");
	static JTextArea txtChat = new JTextArea();
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
	
	public void open(Topic topic){
		lblGroupName.setText(topic.name());
		txtChat.setText("");
		this.setVisible(true);
	}
	public void close(){
		this.setVisible(false);
	}
	public static void newMessage(String message){
		txtChat.append(message + "\n");
		txtChat.setCaretPosition(txtChat.getDocument().getLength());

	}
	public void setTopic(Topic topic){
		txtChat.setText("");
		lblGroupName.setText(topic.name());
	}
	
	private void submitMessage(){
		try {
			Driver.forumClient.forum.sendChatMessage(Driver.forumClient.user, txtInput.getText());
			txtInput.setText("");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public ChatView() {
		setResizable(false);
		setTitle("Chat");
		setBounds(100, 100, 533, 429);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGroup = new JLabel("Group");
			lblGroup.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblGroup.setBounds(20, 11, 46, 14);
			contentPanel.add(lblGroup);
		}
		{
			
			lblGroupName.setBounds(82, 11, 412, 14);
			contentPanel.add(lblGroupName);
		}
		
		txtInput = new JTextField();
		txtInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
//				System.out.println(key.getKeyCode()); //Return = 10;
				if (key.getKeyCode()==10){
					submitMessage();
				}
			}
		});
		txtInput.setBounds(20, 314, 375, 20);
		contentPanel.add(txtInput);
		txtInput.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setActionCommand("submit");
		btnSubmit.addActionListener(this);
		btnSubmit.setBounds(405, 313, 89, 23);
		contentPanel.add(btnSubmit);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
			scrollPane.setBounds(20, 36, 474, 261);

			contentPanel.add(scrollPane);
			{
				
				txtChat.setEditable(false);
				scrollPane.setViewportView(txtChat);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnClose = new JButton("Close");
				btnClose.setActionCommand("close");
				btnClose.addActionListener(this);
				buttonPane.add(btnClose);
			}
		}
	}

	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getActionCommand().equalsIgnoreCase("submit")){
			submitMessage();
		}
		if (ev.getActionCommand().equalsIgnoreCase("close")){
			this.setVisible(false);
		}
	}
}
