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
		 MessageDigest md = MessageDigest.getInstance("MD5");
	     byte[] messageDigest = md.digest(c.getBytes());
	     BigInteger number = new BigInteger(1, messageDigest);
	     String hashstring = number.toString(16);
		
		 preparedStatement = conn.prepareStatement("insert into authenticate values(?, ?, ?)");
		 preparedStatement.setInt(1, a);
		 preparedStatement.setString(2, b);
		 preparedStatement.setString(3, hashstring);
		 preparedStatement.executeUpdate();
	}
	public void count(String d,String y) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int depyear=0,dep=0;
		 
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
		
		MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(p.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String cstring = number.toString(16);
        //System.out.println(cstring);
		while(resultSet.next())
		{
			un = resultSet.getString(1);
			pw = resultSet.getString(2);
		}
		return(un.equals(u) && pw.equals(cstring));
	}
}
