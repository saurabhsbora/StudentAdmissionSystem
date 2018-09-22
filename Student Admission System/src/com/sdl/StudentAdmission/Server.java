package com.sdl.StudentAdmission;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server implements Runnable
{
	ServerSocket ss;
	HashMap<InetAddress,String> connections = new HashMap<>();
	int i = 0;
	//ArrayList<ThreadedServer> connections = new ArrayList<ThreadedServer>();
	public Server()
	{
		
	}
	public void run() 
	{
        try 
        {
			ss = new ServerSocket(5057);
			System.out.println("Server Started");
			System.out.println("Waiting for Clients.....");
		} 
        catch (IOException e1) 
        {
			e1.printStackTrace();
		}
        while (true) 
        {
            try
            { 
            	Socket newclient = ss.accept();
                
            	if(connections.isEmpty() || !connections.containsKey(newclient.getInetAddress()))
            	{
            		i = i+1;
            		String info = "Client :"+ Integer.toString(i);
            		System.out.println("A new client is connected : " + info);  
            		System.out.println("Assigning new thread for this client");
            		connections.put(newclient.getInetAddress(),info);
            	}
            	else
            	{
            		System.out.println(connections.get(newclient.getInetAddress()) + " connected");
            	}
                ThreadedServer ts = new ThreadedServer(newclient);
                new Thread(ts).start();
                
                  
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
