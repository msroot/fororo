/*
 *
 *
 */

import java.rmi.*;
import java.rmi.server.*;
import java.net.MalformedURLException;

public class AdderImpl extends UnicastRemoteObject implements Adder
{
   public AdderImpl() throws RemoteException {}

   public int add(int x, int y) throws RemoteException
   {
      System.out.println( x + " + " + y + " = " + (x+y) );
      return x + y;
   }

   public static void main(String args[])
   {
      System.setSecurityManager(new RMISecurityManager());
      try {
         AdderImpl server = new AdderImpl();
         Naming.rebind("rmi://localhost:1099/AdderImpl",server);
         System.out.println("Created and registered AdderImpl object");
      }
      catch (RemoteException re)
      { 
         System.out.println("Unable to add remote object to registry");
      }
      catch (MalformedURLException me)
      {          
         System.out.println("Incorrect address for registry");
      }
      finally
      {
         System.out.println("Now waiting for remote invocations");
      }
   }
}         
