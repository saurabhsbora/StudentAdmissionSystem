package com.sdl.StudentAdmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException
	{
			int ch;
			Socket clientsoc = new Socket("localhost",5057);
			
			DataOutputStream dout = new DataOutputStream(clientsoc.getOutputStream());
	        DataInputStream din = new DataInputStream(clientsoc.getInputStream());
	        Scanner sc1 = new Scanner(System.in);
	        System.out.print(din.readUTF());
	        do 
			{
				System.out.print(din.readUTF());
				ch = sc1.nextInt();
				dout.writeInt(ch);
				switch(ch)
				{
				case 1:	Student s = new Student();
						s.register();
						ObjectOutputStream oos = new ObjectOutputStream(clientsoc.getOutputStream());
						oos.writeObject(s);
						System.out.print(din.readUTF());
						break;
				case 2:System.out.println("In admin Window");
						break;
				}
			}while(ch!=3);
	       System.out.println("GoodBye :)");
	       sc1.close();
	       dout.close();
	       din.close();
	       clientsoc.close();
	}
}
