package com.sdl.StudentAdmission;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class FeeReport implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int uid,amount;
	private java.sql.Date fee_date;
	private String status;

	public FeeReport(int uid, String dept) 
	{
		this.uid = uid;
		this.status = "NP";
		if(dept.equals("CS"))
			this.amount = 9436;
		else if(dept.equals("IT"))
			this.amount = 5936;
		else
			this.amount = 8436;
		this.fee_date = null;
	}
	public int getUid() 
	{
		return uid;
	}
	public void setUid(int uid) 
	{
		this.uid = uid;
	}
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	public java.sql.Date getFee_date() 
	{
		return fee_date;
	}
	public void setFee_date(java.sql.Date fee_date) 
	{
		this.fee_date = fee_date;
	}
	public String getStatus() 
	{
		return status;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	public static java.sql.Date getcurrentSqlDate() throws ParseException
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = LocalDate.now().toString();
		Date myDate = formatter.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		return sqlDate;
	}
	public static void main(String args[]) throws ParseException
	{
		
	}
}
