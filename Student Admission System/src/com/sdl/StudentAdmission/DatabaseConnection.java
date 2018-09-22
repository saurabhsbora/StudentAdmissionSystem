package com.sdl.StudentAdmission;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Vector;

public class DatabaseConnection {

	String DB_URL = "jdbc:mysql://127.0.0.1:3306/";
	String dbname="StudentRecords";
	String USER = "root";
	String PASS = "";
    Connection conn = null;
    public DatabaseConnection() throws ClassNotFoundException, SQLException
    {
    	Class.forName("com.mysql.jdbc.Driver");
	    System.out.println("Connecting to database...");
	    conn = DriverManager.getConnection(DB_URL+dbname,USER,PASS);
    }
    public void deleteRecord(int uid) throws SQLException
    {
    	PreparedStatement preparedStatement = null;
 		preparedStatement = conn.prepareStatement("delete from records where uniqueID = '"+uid+"'");
 		preparedStatement.executeUpdate();
    }
    private String md5(String pass) throws NoSuchAlgorithmException 
	{
		 MessageDigest md = MessageDigest.getInstance("MD5");
	     byte[] messageDigest = md.digest(pass.getBytes());
	     BigInteger number = new BigInteger(1, messageDigest);
	     String hashstring = number.toString(16);
	     return hashstring;
	}
	public void writeTomysql(Student s) throws SQLException
	{
	    PreparedStatement preparedStatement = null;
		preparedStatement = conn.prepareStatement("insert into records values (?, ?, ?, ?, ?,?, ?,?)");
        
        preparedStatement.setString(1, s.getFname());
        preparedStatement.setString(2, s.getLname());
        preparedStatement.setString(3, s.getEmail_id());
        preparedStatement.setString(4, s.getDept());
        preparedStatement.setInt(5, s.getadmYear());
        preparedStatement.setString(6, s.getEngyear());
        preparedStatement.setInt(7, s.getId());
        preparedStatement.setDouble(8,s.getPhone_no());
        preparedStatement.executeUpdate();
        System.out.println("Successfully added record to database");
	}
	public void writeCredentials(int a, String b, String c) throws NoSuchAlgorithmException, SQLException
	{
		 PreparedStatement preparedStatement = null;
		
	     String hashstring = md5(c);
		
		 preparedStatement = conn.prepareStatement("insert into student_credentials values(?, ?, ?)");
		 preparedStatement.setInt(1, a);
		 preparedStatement.setString(2, b);
		 preparedStatement.setString(3, hashstring);
		 preparedStatement.executeUpdate();
	}
	public void payFee(FeeReport fr) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		
		 preparedStatement = conn.prepareStatement("update fee set payment_date = ?, status = ? where uniqueID = '"+fr.getUid()+"'");
		 preparedStatement.setDate(1, fr.getFee_date());
		 preparedStatement.setString(2, fr.getStatus());
		 preparedStatement.executeUpdate();
	}
	public void writeFeeReport(FeeReport fr) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		
		 preparedStatement = conn.prepareStatement("insert into fee values(?, ?, ?, ?)");
		 preparedStatement.setInt(1, fr.getUid());
		 preparedStatement.setInt(2, fr.getAmount());
		 preparedStatement.setDate(3, fr.getFee_date());
		 preparedStatement.setString(4, fr.getStatus());
		 preparedStatement.executeUpdate();
	}
	public void count(String d,String y) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int depyear=0;
		 
		preparedStatement = conn.prepareStatement("Select count(*) from records where dept='"+d+"' and curr_year='"+y+"'");
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			 depyear =  resultSet.getInt(1);
		}
		System.out.println("Count of "+y+" in "+d+":"+depyear);	
	}
	public Vector<Student> retrieveMysql(String query) throws SQLException
	{
		Vector<Student> vs = new Vector<Student>();	
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

	    preparedStatement = conn.prepareStatement(query);
	    rs = preparedStatement.executeQuery();
	    
	    while(rs.next())
		{
			String fn = rs.getString(1);
            String ln = rs.getString(2);
            String e_id = rs.getString(3);
            String dept = rs.getString(4);
            int ady = rs.getInt(5);
            String ey = rs.getString(6);
            int uid = rs.getInt(7);
            long ph_no = rs.getLong(8);
            vs.add(new Student(fn,ln,e_id,dept,ey,ady,uid,ph_no));
		}
	    return vs;
	}
	public boolean validate(String u,String p,String query) throws SQLException, NoSuchAlgorithmException
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String un="",pw="";
		preparedStatement = conn.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		
        String cstring = md5(p);
      	
        while(resultSet.next())
		{
			un = resultSet.getString(1);
			pw = resultSet.getString(2);
		}
		return(un.equals(u) && pw.equals(cstring));
	}
	public String getFeeStatus(Wrapper w) throws SQLException
	{
		String status = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		preparedStatement = conn.prepareStatement("Select status from fee where uniqueID = '"+w.getUID()+"'");
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			status = resultSet.getString(1);
		}
		return status;
	}
	public void update(Student s) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		
		preparedStatement = conn.prepareStatement("update records set fname = ?, lname = ?, emailID = ?, dept = ?, admissionYear = ?, engineeringYear = ?, phoneNo = ? where uniqueID = '"+s.getId()+"'");
		preparedStatement.setString(1, s.getFname());
        preparedStatement.setString(2, s.getLname());
        preparedStatement.setString(3, s.getEmail_id());
        preparedStatement.setString(4, s.getDept());
        preparedStatement.setInt(5, s.getadmYear());
        preparedStatement.setString(6, s.getEngyear());
        preparedStatement.setDouble(7,s.getPhone_no());
        preparedStatement.executeUpdate();	
	}
	public int update(Wrapper w) throws SQLException, NoSuchAlgorithmException
	{
		String fname, lname, uname, email, pass;
		long pno;
		int uid;
		int ctr = 0;
		PreparedStatement preparedStatement = null;
		
		uid = w.getUID();
		fname = w.getStudentVector().firstElement().getFname();
		lname = w.getStudentVector().firstElement().getLname();
		email = w.getStudentVector().firstElement().getEmail_id();
		pno = w.getStudentVector().firstElement().getPhone_no();
		uname = w.getVector().elementAt(0);
		pass = w.getVector().elementAt(1);
		
		if(!fname.equals(""))
		{
		   preparedStatement = conn.prepareStatement("update records set fname = ? where uniqueID = '"+uid+"'");
		   preparedStatement.setString(1, fname);
		   preparedStatement.executeUpdate();
		   ctr = 1;
		}
		if(!lname.equals(""))
		{
		   preparedStatement = conn.prepareStatement("update records set lname = ? where uniqueID = '"+uid+"'");
		   preparedStatement.setString(1, lname);
		   preparedStatement.executeUpdate();
		   ctr = 1;
		}
		if(!email.equals(""))
		{
		   preparedStatement = conn.prepareStatement("update records set emailID = ? where uniqueID = '"+uid+"'");
		   preparedStatement.setString(1, email);
		   preparedStatement.executeUpdate();
		   ctr = 1;
		}
		if(pno != 0)
		{
		   preparedStatement = conn.prepareStatement("update records set phoneNo = ? where uniqueID = '"+uid+"'");
		   preparedStatement.setLong(1, pno);
		   preparedStatement.executeUpdate();
		   ctr = 1;
		}
		if(!uname.equals(""))
		{
		   preparedStatement = conn.prepareStatement("update student_credentials set username = ? where uniqueID = '"+uid+"'");
		   preparedStatement.setString(1, uname);
		   preparedStatement.executeUpdate();
		   ctr = 1;
		}
		if(!pass.equals(""))
		{
			preparedStatement = conn.prepareStatement("update student_credentials set password = ? where uniqueID = '"+uid+"'");
			preparedStatement.setString(1, md5(pass));
			preparedStatement.executeUpdate();
			ctr = 1;
		}
		return ctr;
	}
	public void closeDatabaseConnection() throws SQLException
	{
		System.out.println("Closed DB connection");
		conn.close();
	}
}
