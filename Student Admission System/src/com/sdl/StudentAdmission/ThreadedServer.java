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
	public void LoginRegister(Wrapper w)
	{
		Vector<String> v;
		//Vector<Student> vs;
		boolean ch;
		try
		{
			v = w.getVector();
			DatabaseConnection dc = new DatabaseConnection();
			if(v.firstElement().equals("Login"))
			{
				if(v.elementAt(1).equals("Student"))
					ch = dc.validate(v.elementAt(2),v.elementAt(3) ,"select username,password from authenticate where username = '"+v.elementAt(2)+"'");
				else
					ch = dc.validate(v.elementAt(2),v.elementAt(3) ,"select username,password from admin");
				dos = new DataOutputStream(sock.getOutputStream());
				if(ch)
				{
					dos.writeUTF("Authorization Successfull");
					//Wrapper w = new Wrapper(dc.retrieveMysql());
					//oos = new ObjectOutputStream(sock.getOutputStream());
					//oos.writeObject(w);
				}
				else
				{
					dos.writeUTF("Username or password does not match!"); 
				}
			 }
			else
			{
				dc.writeTomysql(w.getStudentVector().firstElement());
				v = w.getVector();
				dc.writeCredentials(w.getUID(), v.elementAt(1), v.elementAt(2));
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
	public void connectAdmin() throws ClassNotFoundException, SQLException, IOException
	{
		DatabaseConnection dc = new DatabaseConnection();
		Wrapper w = new Wrapper(dc.retrieveMysql());
		oos = new ObjectOutputStream(sock.getOutputStream());
		oos.writeObject(w);
	}
	public void run()
	{
		Wrapper wrap = null;
		int ch;
		try 
		{
			ois = new ObjectInputStream(sock.getInputStream());
			wrap = (Wrapper)ois.readObject();
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		ch = wrap.getOperation();
		switch(ch)
		{
		case 0: LoginRegister(wrap);
				break;
		case 1: try 
				{
				connectAdmin();
				} 
				catch (ClassNotFoundException | SQLException | IOException e) 
				{
				e.printStackTrace();
				}
				break;
		}
	}
}



