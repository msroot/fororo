package client;

import java.rmi.*;

public interface ForumClientInterface extends java.rmi.Remote {
	 public String test() throws RemoteException;
}