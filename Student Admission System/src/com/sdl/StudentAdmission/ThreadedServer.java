package com.sdl.StudentAdmission;

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
	private DataOutputStream dos;
	
	ThreadedServer(Socket sock) throws IOException
	{
		this.sock = sock;
	}
	public void LoginRegister(Wrapper w)
	{
		Vector<String> v;
		FeeReport fr;
		boolean ch;
		try
		{
			v = w.getVector();
			DatabaseConnection dc = new DatabaseConnection();
			if(v.firstElement().equals("Login"))
			{
				if(v.elementAt(1).equals("Student"))
					ch = dc.validate(v.elementAt(2),v.elementAt(3) ,"select username,password from student_credentials where username = '"+v.elementAt(2)+"'");
				else
					ch = dc.validate(v.elementAt(2),v.elementAt(3) ,"select username,password from admin_credentials");
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
				fr = w.getFr();
				dc.writeCredentials(w.getUID(), v.elementAt(1), v.elementAt(2));
				dc.writeFeeReport(fr);
			}
			dc.closeDatabaseConnection();
		} catch (ClassNotFoundException | IOException e) {
		e.printStackTrace();
		} catch (SQLException e) {
		e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
		}
		
	}
	public void connectAdmin() throws ClassNotFoundException, SQLException, IOException
	{
		DatabaseConnection dc = new DatabaseConnection();
		Wrapper w = new Wrapper(dc.retrieveMysql("Select * from records"));
		oos = new ObjectOutputStream(sock.getOutputStream());
		oos.writeObject(w);
		dc.closeDatabaseConnection();
	}
	public void connectStudent(Wrapper w) throws ClassNotFoundException, SQLException, IOException
	{
		DatabaseConnection dc = new DatabaseConnection();
		Wrapper w1 = new Wrapper(dc.retrieveMysql("Select r.* from records r, student_credentials s where r.uniqueID = s.uniqueID and s.username = '"+w.getMsg()+"'"));
		oos = new ObjectOutputStream(sock.getOutputStream());
		oos.writeObject(w1);
		dc.closeDatabaseConnection();
	}
	public void updateStudent(Wrapper w) throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException
	{
		DatabaseConnection dc = new DatabaseConnection();
		dos = new DataOutputStream(sock.getOutputStream());
		if(dc.update(w) == 1)
			dos.writeUTF("Updated Successfully");
		else
			dos.writeUTF("Nothing to update");
		dc.closeDatabaseConnection();
	}
	public void updateFeeReport(Wrapper w) throws ClassNotFoundException, SQLException, IOException
	{
		DatabaseConnection dc = new DatabaseConnection();
		dc.payFee(w.getFr());
		dos = new DataOutputStream(sock.getOutputStream());
		dos.writeUTF("Fee paid Successfully");
		dc.closeDatabaseConnection();
	}
	public void sendFeeStatus(Wrapper w) throws ClassNotFoundException, SQLException, IOException
	{
		String ans;
		DatabaseConnection dc = new DatabaseConnection();
		dos = new DataOutputStream(sock.getOutputStream());
		ans = dc.getFeeStatus(w);
		dos.writeUTF(ans);
		dc.closeDatabaseConnection();
	}
	public void deleteStudentFromAdmin(Wrapper w) throws ClassNotFoundException, SQLException
	{
		DatabaseConnection dc = new DatabaseConnection();
		dc.deleteRecord(w.getUID());
		dc.closeDatabaseConnection();
	}
	public void updateFromAdmin(Wrapper w) throws ClassNotFoundException, SQLException
	{
		DatabaseConnection dc = new DatabaseConnection();
		dc.update(w.getStudentVector().firstElement());
		dc.closeDatabaseConnection();
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
				case 2: try 
						{
							connectStudent(wrap);
						} catch (ClassNotFoundException | SQLException | IOException e) {
				
							e.printStackTrace();
						}
						break;
				case 3: try 
						{
							updateStudent(wrap);
						} 
						catch (ClassNotFoundException | SQLException | IOException | NoSuchAlgorithmException e) 
						{
							e.printStackTrace();
						}
						break;
				case 4:	try 
						{
							updateFeeReport(wrap);
						} 
						catch (ClassNotFoundException | SQLException | IOException e) {
							e.printStackTrace();
						}
						break;
				case 5: try 
						{
							sendFeeStatus(wrap);
						} catch (ClassNotFoundException | SQLException | IOException e) {
							e.printStackTrace();
						}
						break;
				case 6: try 
						{
							deleteStudentFromAdmin(wrap);
						} 
						catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
						break;
				case 7: try 
						{
							updateFromAdmin(wrap);
						} 
						catch (ClassNotFoundException | SQLException e) {					
							e.printStackTrace();
						}
			}
	}	
}




