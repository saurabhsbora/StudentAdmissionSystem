package com.sdl.StudentAdmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;

public class ClientInterface extends Thread
{
	DataInputStream dis;
	DataOutputStream dos;
	Socket s;
	
	ClientInterface(Socket s, DataInputStream dis, DataOutputStream dos)
	{
		this.s = s;
		this.dis = dis;
		this.dos = dos;
	}
	

public void run()
{
		int choice;
		String start = "---STUDENT ADMISSION SYSTEM---";
		String menu = "\n1.Register as a Student\n2.Login as Admin\n3.Exit\nEnter your choice:";

		DatabaseConnection dc = null;
		try 
		{
			dc = new DatabaseConnection();
		}	 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			dos.writeUTF(start);
			do 
			{
				//Displaying menu on client side
				dos.writeUTF(menu);
				choice = dis.readInt();
				switch(choice)
				{
    				case 1:ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
        		   	   	   Student stud = (Student)ois.readObject();
        		   	   	   Global.obj.add(stud);
        		   	   	   dos.writeUTF("Successfully Registered");
        		   	   	   dc.writeTomysql(stud);
        		   	   	   break;
    				case 2:System.out.println("Logged as Admin");
        		   			//System.out.println("Count:"+m.count());	
    			   			Admin.menu(dc);
    			   			break;
				}
			}while(choice!=3);
			System.out.println("Client Disconnected:"+s);
		}
		catch(IOException io)
		{
			io.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
 	}
}




