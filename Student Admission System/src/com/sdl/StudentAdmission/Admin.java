package com.sdl.StudentAdmission;
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
	public static void menu(DatabaseConnection o) throws SQLException
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
}
