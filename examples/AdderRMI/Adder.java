
/*
 * This file defines the interface for the remote object
 * that is provided by the server and invoked by the client
 *
 */

import java.rmi.*;

public interface Adder extends java.rmi.Remote
{
   int add(int x, int y) throws RemoteException;
}


         
