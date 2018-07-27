package com.sdl.StudentAdmission;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;
public class Student implements Comparable<Student>,Serializable
{
	private static final long serialVersionUID = -4929836389095189613L;
	private String fname,lname,email_id,dept,currentyear;
	private int id,year;
	private long phone_no;
	public Student()
	{
		fname = lname = email_id = dept = currentyear="";
		id = year = 0;
		phone_no = 0;
		//System.out.println("Student data not initialized");
	}
	public Student(String fname, String lname, String email_id, String dept,
			String currentyear,int year, int id, long phone_no) 
	{
		this.fname = fname;
		this.lname = lname;
		this.email_id = email_id;
		this.dept = dept;
		this.year = year;
		this.id = id;
		this.phone_no = phone_no;
		this.currentyear = currentyear;
		
	}
	public String getCurrentyear() {
		return currentyear;
	}
	public void setCurrentyear(String currentyear) {
		this.currentyear = currentyear;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(long d) {
		this.phone_no = d;
	}
	public void register()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your First name:");
		setFname(sc.next());
		System.out.println("Enter your Last name:");
		setLname(sc.next());
		System.out.println("Enter your email ID:");
		setEmail_id(sc.next());
		System.out.println("Enter your Department as CS/IT/ENTC:");
		setDept(sc.next());
		System.out.println("Enter your Admission year:");
		setYear(sc.nextInt());
		System.out.println("Enter your Current Admission year as FE/SE/TE/BE:");
		setCurrentyear(sc.next());
		System.out.println("Enter your Unique ID:");
		setId(sc.nextInt());
		System.out.println("Enter your Contact No:");
		setPhone_no(sc.nextLong());
		
		
	}
	public void Display()
	{
		System.out.println("---STUDENT DETAILS---");
		System.out.println("First Name:"+getFname());
		System.out.println("Last Name:"+getLname());
		System.out.println("Email-ID:"+getEmail_id());
		System.out.println("Department:"+getDept());
		System.out.println("Admission Year:"+getYear());
		System.out.println("Current Admission Year Student:"+getCurrentyear());
		System.out.println("Unique ID:"+getId());
		System.out.println("Contact No:"+getPhone_no());
	}
	@Override
	public int compareTo(Student s) 
	{
		//Sorting based on Student id
		return (this.id - s.id);
	}
	public String toString()
	{
		 return getFname()+"\t"+getLname()+"\t"+getEmail_id()+"\t"+getDept()+"\t"+getYear()+"\t"+getCurrentyear()+"\t"+getId()+"\t"+getPhone_no();  
	}
	@Override
	public boolean equals(Object o)
	{
		Student stud = (Student)o;
		return (o instanceof Student) && (this.id == stud.id);
	}
	@Override
	public int hashCode() 
	{
	      return Objects.hash(id);
	}
}