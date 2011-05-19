package shared;

import java.rmi.*;

/**
 * Main <{@link ForumClientInterface} interface. Server/Client common interface for RMI
 * 
 * @author Victor Nava
 * 
 */
public interface ForumClientInterface extends java.rmi.Remote {
	 public String test() throws RemoteException;
     public void updateChat(String messages)throws RemoteException;
}