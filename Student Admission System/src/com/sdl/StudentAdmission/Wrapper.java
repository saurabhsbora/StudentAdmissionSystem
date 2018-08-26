package com.sdl.StudentAdmission;

import java.io.Serializable;
import java.util.Vector;

public class Wrapper implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<String> v = new Vector<String>();
	
	public Wrapper(String a, String b)
	{
		v.add("Login");
		v.add(a);
		v.add(b);
	}
	public Vector<String> getVector()
	{
		return v;
	}
	 
}
