package com.sdl.StudentAdmission;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin
{
	
	/*public static void displayAll()
	{
		int flag = 0;
		for(Student stu:Global.obj)
		{
			flag = 1;
			System.out.println(stu);
		}
		if(flag == 0)
			System.out.println("No Records Found");
	}*/
	public static void menu(MysqltoJava o) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		
		int ch=0;
		do
		{
			System.out.println("1.Display According to Dept name as [CS,IT,ENTC]\n2.Display According to year and Dept name\n3.Display student using UID\n4.Display All\n5.Exit");
			System.out.println("Enter your choice:");
			ch = sc.nextInt();
			switch(ch)
			{
			case 1:System.out.println("Enter the dept name:");
				   String str = sc.next();
				   o.retrieveMysql(o.displayd(str));
				   break;
			case 2:System.out.println("Enter the dept name:");
			   	   String d = sc.next();
			   	   System.out.println("Enter the year:");
				   String y = sc.next();	
			   	   o.retrieveMysql(o.displaydny(d, y));
			   	   o.count(d, y);
			   	   break;
			case 3:System.out.println("Enter the Student UID:");
			   	   int s_id = sc.nextInt(); 
			   	   o.retrieveMysql(o.displayuid(s_id));
			   	   break;
			case 4:o.retrieveMysql(o.displayall());
				break;
			
			}
		}while(ch!=5);
		sc.close();
	}
	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException
	{
		int choice;
		String start = "---STUDENT ADMISSION SYSTEM---";
		String menu = "\n1.Register as a Student\n2.Login as Admin\n3.Exit\nEnter your choice:";
		
		ServerSocket Serversoc = new ServerSocket(5000);
		System.out.println("Waiting for connection...");
		Socket soc = Serversoc.accept();
		System.out.println("Client Connected "+soc.getInetAddress());
		MysqltoJava m = new MysqltoJava();
		
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
        		case 1:ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
            		   Student stud = (Student)ois.readObject();
            		   Global.obj.add(stud);
            		   dout.writeUTF("Successfully Registered");
            		   m.writeTomysql(stud);
            		   break;
        		case 2:System.out.println("Logged as Admin");
            		   //System.out.println("Count:"+m.count());	
        				menu(m);
            		   break;
        	}
        }while(choice!=3);
        System.out.println("Client Disconnected");
        dout.close();
        din.close();
        Serversoc.close();
	}
}
