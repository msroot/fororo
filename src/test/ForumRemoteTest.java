package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.rmi.RemoteException;
import java.util.*;
import server.*;
import server.db.*;
import client.*;
import shared.*;

public class ForumRemoteTest {

    @Test
    public void accepts_connections() {
        try{
            ForumClient client = new ForumClient();
            assertTrue("It should connect", client.connect());
        } catch (Exception e){
        	e.printStackTrace();
            fail("Should not throw exception: " + e.getStackTrace());
        }
    }
}