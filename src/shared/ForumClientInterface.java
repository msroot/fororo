package shared;

import java.rmi.*;

public interface ForumClientInterface extends java.rmi.Remote {
	 public String test() throws RemoteException;
     public void updateChat(String messages)throws RemoteException;
}