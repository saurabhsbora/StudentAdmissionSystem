package com.sdl.StudentAdmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable
{
	ServerSocket ss;
	ArrayList<ThreadedServer> connections = new ArrayList<ThreadedServer>();
	public Server()
	{
		
	}
	public void run() 
	{
        try 
        {
			ss = new ServerSocket(5057);
		} 
        catch (IOException e1) 
        {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        while (true) 
        {
        	System.out.println("Server Started");
			System.out.println("Waiting for Clients.....");
            try
            { 
            	Socket newclient = ss.accept();
                 
                System.out.println("A new client is connected : " + newclient);  
                System.out.println("Assigning new thread for this client");
                
                ThreadedServer ts = new ThreadedServer(newclient);
                new Thread(ts).start();
                
                connections.add(ts);
                 
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
	}
	public static void main(String args[]) 
	{
        (new Thread(new Server())).start();
    }
}
