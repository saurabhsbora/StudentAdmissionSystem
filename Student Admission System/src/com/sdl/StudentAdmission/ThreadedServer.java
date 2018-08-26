package com.sdl.StudentAdmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Vector;

public class ThreadedServer extends Thread
{
	private Socket sock;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	ThreadedServer(Socket sock) throws IOException
	{
		this.sock = sock;
		//ois = new ObjectInputStream(sock.getInputStream());
		//oos = new ObjectOutputStream(sock.getOutputStream());
		//dis = new DataInputStream(sock.getInputStream());
		//dos = new DataOutputStream(sock.getOutputStream());
	}
	public void run()
	{
		Wrapper w;
		Vector<String> v;
		int ch;
		try 
			{
				ois = new ObjectInputStream(sock.getInputStream());
				w = (Wrapper)ois.readObject();
				v = w.getVector();
				if(v.firstElement().equals("Login"))
				{
					DatabaseConnection dc = new DatabaseConnection();
					ch = dc.validate(v.elementAt(1),v.elementAt(2) ,"select username,password from authenticate where username = '"+v.elementAt(1)+"'");
					dos = new DataOutputStream(sock.getOutputStream());
					if(ch == 1)
					{
						dos.writeUTF("Authorization Successfull");
					}
					else
					{
						dos.writeUTF("Username or password does not match!");
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		   	  
 }



