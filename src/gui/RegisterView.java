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
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class RegisterView extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUserName;
	private JPasswordField passwordField;
	private RegisterView dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegisterView dialog  = new RegisterView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void open(){
		new RegisterView();
	}

	/**
	 * Create the dialog.
	 */
	public RegisterView() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Register");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 285, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("User Name");
			label.setBounds(23, 33, 61, 14);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Tahoma", Font.BOLD, 11));
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Password");
			label.setBounds(23, 58, 54, 14);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Tahoma", Font.BOLD, 11));
			contentPanel.add(label);
		}
		{
			txtUserName = new JTextField();
			txtUserName.setBounds(94, 30, 129, 20);
			txtUserName.setColumns(10);
			contentPanel.add(txtUserName);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(94, 55, 129, 20);
			contentPanel.add(passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegister = new JButton("Register");
				btnRegister.addActionListener(this);
				btnRegister.setActionCommand("OK");
				buttonPane.add(btnRegister);
				getRootPane().setDefaultButton(btnRegister);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(this);
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getActionCommand().equalsIgnoreCase("ok")){
			String userName = txtUserName.getText();
			String password = new String(passwordField.getPassword());
			try {
				Driver.forumClient.forum.registerUser(userName, password);
				this.dispose();
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ev.getActionCommand().equalsIgnoreCase("cancel")){
			this.dispose();
		}
	}

}