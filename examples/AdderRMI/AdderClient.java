/*
 * This program attempts to make use of a remote object to 
 * add two integers together
 */

import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.net.MalformedURLException;

public class AdderClient
{
   public static void main(String args[])
   {
      // Generate two random numbers for adding with remote object
      int x = (int) (Math.random() * 100);
      int y = (int) (Math.random() * 100);

      try {
         // Attempt to obtain a reference to remote object
         Adder a  = (Adder) Naming.lookup("rmi://localhost:1099/AdderImpl");
         //System.out.println("sum of "+ x + " and " + y + " = " + a.add(x,y));
         
         for(int i=0; i<10;i++){
        	 System.out.println(a.pong("client" +i));
         }
        
         System.out.println("sum of "+ x + " and " + y + " = " + a.add(x,y) +" client");

      }
      catch (Exception re)
      { 
         System.out.println("Failed to retrieve reference to remote object:");
         System.out.println(re);
      }
   }
}

         
