package com.sdl.StudentAdmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	public static void main(String args[]) throws IOException
	{
		
        ServerSocket ss = new ServerSocket(5057);
        while (true) 
        {
            Socket newclient = null;
             
            try
            {
                
            	newclient = ss.accept();
                 
                System.out.println("A new client is connected : " + newclient);
                 
                DataInputStream dis = new DataInputStream(newclient.getInputStream());
                DataOutputStream dos = new DataOutputStream(newclient.getOutputStream());
                 
                System.out.println("Assigning new thread for this client");
 
                Thread t = new ClientInterface(newclient, dis, dos);
                
                t.start();
                 
            }
            catch (Exception e)
            {
            	newclient.close();
                e.printStackTrace();
            }
        }
	}
}
