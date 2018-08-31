package com.sdl.StudentAdmission;

import java.io.Serializable;
import java.util.Vector;

public class Wrapper implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private Vector<String> v = new Vector<String>();
	private Vector<Student> vs;
	private int operation = -1;
	private int uid = -1;
	
	public Wrapper(String type,String uname, String pass)
	{
		operation = 0;
		v.add("Login");
		v.add(type);
		v.add(uname);
		v.add(pass);
	}
	public Wrapper(Vector<Student> vs)
	{
		operation = 1;
		this.vs = vs;
	}
	public Wrapper(Student s,int uid,String uname, String pass)
	{
		operation = 0;
		vs = new Vector<Student>();
		vs.add(s);
		v.add("Register");
		v.add(uname);
		v.add(pass);
		this.uid = uid;
	}
	public Vector<String> getVector()
	{
		return v;
	}
	public Vector<Student> getStudentVector()
	{
		return vs;
	}
	public int getOperation()
	{
		return operation;
	}
	public int getUID()
	{
		return uid;
	}
}
