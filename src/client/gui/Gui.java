package client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui {

	public JFrame frame;
	private JTextField textField;


	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 639, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * Displays all the new Groups users suggest and are currenty waiting
		 * aproved by admin
		 */
		JPanel groupRequests = new JPanel();
		groupRequests.setLayout(null);
		groupRequests.setVisible(false);
		frame.getContentPane().add(groupRequests);

		/**
		 * Functionalify for administrators so they can edit the users
		 */
		JPanel usersList = new JPanel();
		usersList.setLayout(null);
		usersList.setVisible(false);
		frame.getContentPane().add(usersList, BorderLayout.CENTER);

		/**
		 * Displays a sigle thread
		 */
		JPanel newThread = new JPanel();
		newThread.setLayout(null);
		newThread.setVisible(false);
		frame.getContentPane().add(newThread, BorderLayout.CENTER);

		JPanel registrer = new JPanel();
		frame.getContentPane().add(registrer, BorderLayout.CENTER);
		registrer.setVisible(false);
		registrer.setLayout(null);

		JPanel login = new JPanel();
		login.setLayout(null);
		login.setVisible(false);
		frame.getContentPane().add(login, BorderLayout.CENTER);

		JPanel displayThreads = new JPanel();
		displayThreads.setLayout(null);
		displayThreads.setVisible(false);
		frame.getContentPane().add(displayThreads, BorderLayout.CENTER);

		JPanel addGroup = new JPanel();
		addGroup.setLayout(null);
		addGroup.setVisible(false);
		frame.getContentPane().add(addGroup, BorderLayout.CENTER);

		final JPanel displaySingleThread = new JPanel();
		displaySingleThread.setLayout(null);
		frame.getContentPane().add(displaySingleThread, BorderLayout.CENTER);

		textField = new JTextField("ssssssssssssssss");
		textField.setBounds(0, 0, 150, 80);
		displaySingleThread.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(0, 0, 130, 47);
		displaySingleThread.add(btnNewButton_1);

		final JPanel home = new JPanel();
		home.setLayout(null);
		frame.getContentPane().add(home, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(139, 179, 89, 23);
		home.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				home.setVisible(false);
				displaySingleThread.setVisible(true);
			}
		});

	}
}
