package com.sdl.StudentAdmission;
import java.sql.*;

public class MysqltoJava {

	String DB_URL = "jdbc:mysql://127.0.0.1:3306/";
	String dbname="StudentRecords";
	String USER = "root";
	String PASS = "";
    Connection conn = null;
    public MysqltoJava() throws ClassNotFoundException, SQLException
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
        preparedStatement.setString(3, s.getEmail_id() );
        preparedStatement.setString(4, s.getDept());
        preparedStatement.setString(5, s.getCurrentyear());
        preparedStatement.setInt(6, s.getId());
        preparedStatement.setInt(7, s.getYear());
        preparedStatement.setDouble(8,s.getPhone_no());
        preparedStatement.executeUpdate();
        System.out.println("Successfully added record to database");
        preparedStatement.close();
        
        
	}
	public ResultSet displaydny(String d, String y) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

	    preparedStatement = conn.prepareStatement("Select * from records where dept='"+d+"' and curr_year='"+y+"'");
	    resultSet = preparedStatement.executeQuery();
	    return resultSet;
	}
	public ResultSet displayd(String d) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
	    preparedStatement = conn.prepareStatement("Select * from records where dept= '"+ d +"'");
	    resultSet = preparedStatement.executeQuery();
	    return resultSet;
	}
	public ResultSet displayuid(int uid)throws SQLException
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

	    preparedStatement = conn.prepareStatement("Select * from records where id='"+uid+"'");
	    resultSet = preparedStatement.executeQuery();
	    return resultSet;
	}
	public ResultSet displayall()throws SQLException
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

	    preparedStatement = conn.prepareStatement("Select * from records");
	    resultSet = preparedStatement.executeQuery();
	    return resultSet;
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
	public void retrieveMysql(ResultSet r) throws SQLException
	{
			
		ResultSet resultSet = r;
	    while (resultSet.next()) {
            
            String fn = resultSet.getString(1);
            String ln = resultSet.getString(2);
            String e_id = resultSet.getString(3);
            String dept = resultSet.getString(4);
            String cy = resultSet.getString(5);
            int id = resultSet.getInt(6);
            int year = resultSet.getInt(7);
            long ph_no = resultSet.getLong(8);
            
            System.out.print(fn+"\t");
            System.out.print(ln+"\t");
            System.out.print(e_id+"\t");
            System.out.print(dept+"\t");
            System.out.print(cy+"\t");
            System.out.print(id+"\t");
            System.out.print(year+"\t");
            System.out.print(ph_no+"\n");
            }
        
	}
}
