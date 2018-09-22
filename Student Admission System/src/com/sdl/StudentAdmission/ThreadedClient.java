package com.sdl.StudentAdmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadedClient implements Runnable
{
	private Socket clientsoc;
	private  ObjectOutputStream oos;
	private  ObjectInputStream ois;
	private DataOutputStream dos;
	private  DataInputStream dis;
	
	public ThreadedClient() 
	{
		try {
			clientsoc = new Socket("localhost",5057);
			System.out.println("Connected to Server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run()
	{
		System.out.println("ThreadedClient is running....");
	}
	public void sendObjectToServer(Wrapper w) throws IOException
	{
		oos = new ObjectOutputStream(clientsoc.getOutputStream());
		oos.writeObject(w);
	}
	public Wrapper recieveObjectFromServer() throws IOException, ClassNotFoundException
	{
		ois = new ObjectInputStream(clientsoc.getInputStream());
		Wrapper w = (Wrapper)ois.readObject();
		return w;
	}
	public void sendMsgtoServer(String str) throws IOException
	{
		dos = new DataOutputStream(clientsoc.getOutputStream());
		dos.writeUTF(str);
	}
	public String receiveMsgFromServer() throws IOException
	{
		dis = new DataInputStream(clientsoc.getInputStream());
		String msg = dis.readUTF();
		return msg;
	}
	public void close() throws IOException
	{
		System.out.println("Closed");
		clientsoc.close();
	}
}
