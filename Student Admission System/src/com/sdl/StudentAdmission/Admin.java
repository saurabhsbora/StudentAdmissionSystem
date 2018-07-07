package com.sdl.StudentAdmission;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//import java.util.Collections;

public class Admin
{
	public static void displayAll()
	{
		int flag = 0;
		for(Student stu:Global.obj)
		{
			flag = 1;
			System.out.println(stu);
		}
		if(flag == 0)
			System.out.println("No Records Found");
	}
	public static void main(String args[]) throws IOException, ClassNotFoundException
	{
		int choice;
		String start = "---STUDENT ADMISSION SYSTEM---";
		String menu = "\n1.Register as a Student\n2.Login as Admin\n3.Exit\nEnter your choice:";
		
		ServerSocket Serversoc = new ServerSocket(5000);
		System.out.println("Waiting for connection...");
		Socket soc = Serversoc.accept();
		System.out.println("Client Connected "+soc.getInetAddress());
		
		DataOutputStream dout = new DataOutputStream(soc.getOutputStream());
        DataInputStream din = new DataInputStream(soc.getInputStream());
        dout.writeUTF(start);
        do 
        {
        	//Displaying menu on client side
        	dout.writeUTF(menu);
        	choice = din.readInt();
        	switch(choice)
        	{
        		case 1:Student s = new Student();
            		   ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
            		   oos.writeObject(s);
            		   ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
            		   Student stud = (Student)ois.readObject();
            		   Global.obj.add(stud);
            		   dout.writeUTF("Successfully Registered");
            		   break;
        		case 2:System.out.println("Logged as Admin");
            		   System.out.println("Displaying all records");
            		   displayAll();
            		   break;
        	}
        }while(choice!=3);
        System.out.println("Client Disconnected");
        dout.close();
        din.close();
        Serversoc.close();
	}
}
