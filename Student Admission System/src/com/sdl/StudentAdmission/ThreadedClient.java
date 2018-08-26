package com.sdl.StudentAdmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Thread.State;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ThreadedClient implements Runnable
{
	private Socket clientsoc;
	private  ObjectOutputStream oos;
	private  ObjectInputStream ois;
	private DataOutputStream dos;
	private  DataInputStream dis;
	
	public ThreadedClient() 
	{
		
	}
	public void run()
	{
		try {
			clientsoc = new Socket("localhost",5057);
			System.out.println("Connected to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendObjectToServer(Wrapper w)
	{
		try 
		{
			System.out.println(w.getVector());
			oos = new ObjectOutputStream(clientsoc.getOutputStream());
			oos.writeObject(w);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void recieveObjectFromServer() throws IOException, ClassNotFoundException
	{
		
		Wrapper w = (Wrapper)ois.readObject();
	}
	public  void sendMsgtoServer(String str) throws IOException
	{
		
		dos.writeUTF(str);
	}
	public  String receiveMsgFromServer() throws IOException
	{
		
		dis = new DataInputStream(clientsoc.getInputStream());
		return dis.readUTF();
	}
	public void close() throws IOException
	{
		ois.close();
		oos.close();
		dis.close();
		dos.close();
		clientsoc.close();
	}
	public static void main(String args[])
	{
		
	}
}
