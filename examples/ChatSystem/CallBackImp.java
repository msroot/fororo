import java.rmi.*;
import java.rmi.server.*;


public class CallBackImp extends UnicastRemoteObject
             implements CallBack
{
   private ChatClient thisClient;

   public CallBackImp(Object client) throws RemoteException 
   {
      thisClient = (ChatClient) client;
   }
   public void addMessage(String from, String message)throws RemoteException
   {
	thisClient.getMessageBox().append("<" + from + ">" +  message + "\n");
   }   
   public void quitMessage(String from)throws RemoteException
   {
	thisClient.getMessageBox().append(from + " has left.\n");
   }
   public void joinedMessage(String from)throws RemoteException
   {
	thisClient.getMessageBox().append(from + " has joined.\n");
   }
}
     