import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ChatClient extends UnicastRemoteObject {

   public final static int NORMAL = 0;
   public final static int MASTER = 1;
   private ChatServer chatServer;
   private TextArea messageBox;
   private JFrame userFrame;
   private JList list;
   private String userName;
   private int clientType;
    
   public TextArea getMessageBox() { return messageBox; }
   
   public static void main(String[] args) {
		try {
			ChatClient dialog = new ChatClient();
//			ChatClient.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
   public ChatClient() throws java.rmi.RemoteException {
      createFrame();
      try {
         chatServer =(ChatServer)Naming.lookup("//localhost/ChatServer");

	   int status;
         // create a CallBack to allow user to control the client
         CallBackImp CallBackControl = new CallBackImp(this);
	   do {
	      userName = getUserName();
		status = chatServer.addChatListener((CallBack) CallBackControl, userName);
	   } while (status != ChatServerImp.SUCCESS);
      } 
      catch(Exception e) {
         e.printStackTrace();
      }
   }

   /* getUserName prompts the user for their desired username.
    * It will do this until it receives a suitable string.
    * It will return the username upon success.
   */

    public String getUserName() {
	 JFrame inputFrame = new JFrame();
	 JOptionPane jop = new JOptionPane();
	 String name = null;
	 while (name == null)
	    name = jop.showInputDialog(inputFrame, "Enter a username", 
			"ChatClient",   JOptionPane.QUESTION_MESSAGE);
	return name;
    }

    /* createFrame builds the main GUI */
    public void createFrame() {
        
	 String title;

	 if (clientType == MASTER)
	    title = "Master Client";
	 else
	    title = "Chat Client";
       JFrame chatFrame = new JFrame(title);

	 chatFrame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    try {
			chatServer.removeChatListener(userName, "Leaving");
			System.exit(0);
		    } catch (Exception ex) {
			ex.printStackTrace();
		    }
		}
	    });
	
       Container chatContainer = chatFrame.getContentPane();

       JScrollPane sp = new JScrollPane(messageBox = new TextArea(40, 10));
       messageBox.setEditable(false);
       chatContainer.add(messageBox, BorderLayout.CENTER);

       JPanel controlPanel = new JPanel();
       final TextField inputText = new TextField(40);
       controlPanel.add(inputText);
       JButton sendButton = new JButton("Send Message");
       sendButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                   chatServer.sendChatMessage(userName, inputText.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                inputText.setText("");
            }
        });
       controlPanel.add(sendButton);


       JButton exitButton = new JButton("Exit Chat");
       exitButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    chatServer.removeChatListener(userName, "Leaving");
                    System.exit(0);
                } 
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
       });
       controlPanel.add(exitButton);

       chatContainer.add(controlPanel, BorderLayout.SOUTH);

       chatFrame.setSize(540, 400);
	 chatFrame.pack();

	/* This is apparently not available in the JRE available on nova. */
	//chatFrame.setLocationRelativeTo(null);

        chatFrame.setVisible(true);
    }

    /* User to close the userFrame through the listener object attached 
     * to the buttons.
     */
    public void close() {
	 userFrame.setVisible(false);
    }

    public static void main(String argv[]) {
       try {
          ChatClient chatClient = new ChatClient();
       } catch (Exception e ) {
          e.printStackTrace();
       }
    }
}