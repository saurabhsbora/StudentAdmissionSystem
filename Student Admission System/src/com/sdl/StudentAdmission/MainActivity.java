package com.sdl.StudentAdmission;
import java.util.Scanner;
public class MainActivity 
{
	public static void main(String args[])
	{
		int choice;
		//Admin ad = new Admin();
		Scanner sc = new Scanner(System.in);
		System.out.println("---STUDENT ADMISSION SYSTEM---");
		do
		{
			System.out.println("\n1.Register as a Student\n2.Display All");
			System.out.println("Enter your choice:");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1: Student a = new Student();
					a.register();
					Global.obj.add(a);
					break;
			case 2: //ad.displayAll();
					break;
			}
			
		}while(choice!=3);
		sc.close();
	}
}
