import java.rmi.*;

public interface CallBack extends Remote
{
   public void addMessage(String from, String message) throws RemoteException;
   public void quitMessage(String from) throws RemoteException;
   public void joinedMessage(String from) throws RemoteException;
}       