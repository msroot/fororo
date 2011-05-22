package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.JTextArea;
import shared.*;


public class ThreadView implements ActionListener{

	private JFrame frmViewThread;
	private JTable table;
	private ForumThread thread;
	private Topic topic;
	static ThreadView window;
	List<ForumThread> threads;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ThreadView(null,null);
					window.frmViewThread.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void open(Topic topic,ForumThread thread){
		new ThreadView(topic,thread);
	}
	/**
	 * Create the application.
	 */
	public ThreadView(Topic topic, ForumThread thread) {
		this.threads = new ArrayList<ForumThread>();
		this.topic = topic;
		this.thread = thread;
		loadTable();
		initialize();
	}
	
	private void loadTable(){
		readChilds(thread.children);
		for(ForumThread thread:threads){
			System.out.println(thread.title());
		}
		
	}
	
	private void readChilds(List<ForumThread> childs){
		for(ForumThread child:childs){
			readChilds(child.children);
			threads.add(child);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewThread = new JFrame();
		frmViewThread.setVisible(true);
		frmViewThread.setTitle("View Thread");
		frmViewThread.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frmViewThread.setResizable(false);
		frmViewThread.setBounds(100, 100, 679, 580);
		frmViewThread.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewThread.getContentPane().setLayout(null);
		
		JLabel lblAppName = new JLabel("Fororo");
		lblAppName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAppName.setBounds(21, 21, 134, 25);
		frmViewThread.getContentPane().add(lblAppName);
		
		JLabel lblDescription = new JLabel("Thread Details");
		lblDescription.setBackground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(52, 57, 542, 25);
		frmViewThread.getContentPane().add(lblDescription);
		
		JButton btnReply = new JButton("Reply");
		btnReply.setActionCommand("reply");
		btnReply.addActionListener(this);
		btnReply.setBounds(29, 215, 107, 23);
		frmViewThread.getContentPane().add(btnReply);
		
		JButton btnReturn = new JButton("Go Back");
		btnReturn.setActionCommand("go back");
		btnReturn.addActionListener(this);
		btnReturn.setBounds(548, 518, 107, 23);
		frmViewThread.getContentPane().add(btnReturn);
		
		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(44, 249, 76, 15);
		frmViewThread.getContentPane().add(lblNewLabel);
		
		JLabel lblUser = new JLabel("Author");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUser.setBounds(44, 275, 46, 15);
		frmViewThread.getContentPane().add(lblUser);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setActionCommand("delete");
		btnDelete.addActionListener(this);
		
		btnDelete.setBounds(146, 215, 107, 23);
		frmViewThread.getContentPane().add(btnDelete);
		
		table = new JTable();
		table.setBounds(52, 90, 542, 100);
		frmViewThread.getContentPane().add(table);
		
		JLabel lblThreadTitle = new JLabel(this.thread.title());
		lblThreadTitle.setBounds(109, 250, 378, 14);
		frmViewThread.getContentPane().add(lblThreadTitle);
		
		JLabel lblThreadAuthor = new JLabel(this.thread.userName());
		lblThreadAuthor.setBounds(109, 275, 201, 14);
		frmViewThread.getContentPane().add(lblThreadAuthor);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(this.thread.content());
		textArea.setBounds(28, 312, 620, 172);
		frmViewThread.getContentPane().add(textArea);
		
		JTree tree = new JTree();
		tree.setRootVisible(false);
		tree.setBounds(332, 218, 72, 64);
		frmViewThread.getContentPane().add(tree);
	}

	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getActionCommand().equalsIgnoreCase("delete")){
			
		}
		if (ev.getActionCommand().equalsIgnoreCase("reply")){
			
		}
		if (ev.getActionCommand().equalsIgnoreCase("go back")){
			GroupView.open(topic);
			frmViewThread.dispose();
		}
		
	}
}
