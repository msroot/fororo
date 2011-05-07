
/*
 * This file defines the interface for the remote object
 * that is provided by the server and invoked by the client
 *
 */

import java.rmi.*;
import java.rmi.server.ServerNotActiveException;

public interface Adder extends java.rmi.Remote
{
   int add(int x, int y) throws RemoteException;

String pong(String string)throws RemoteException, ServerNotActiveException;
}


         
