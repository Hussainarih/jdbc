package jabc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Program2 {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/employee";
		String un = "root";
		String pwd = "root";
		Connection con = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully");
			
			 con = DriverManager.getConnection(url,un,pwd);
			System.out.println("Connection Established");
			
			String query = "insert into employee(`emp_id`,`first_name`,`last_name`,`degination`,`mobile_num`,`salary`,`hire_date`) values(?,?,?,?,?,?,?)";
			
			
			pstmt = con.prepareStatement(query);	
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Enter emp_id");
			int emp_id = scan.nextInt();
			System.out.println("Enter first-name:");
			String first_name= scan.next();
			System.out.println("Enter last-name:");
			String last_name = scan.next();
			System.out.println("Enter designation:");
			String desgnation = scan.next();
			System.out.println("Enter mobile_num:");
			long mobile_num = scan.nextLong();
			System.out.println("Enter salary::");
			int salary = scan.nextInt();
			System.out.println("Enter hiring_date:");
			String hire_date = scan.next();
			
			pstmt.setInt(1,emp_id);
			pstmt.setString(2,first_name);
			pstmt.setString(3,last_name);
			pstmt.setString(4,desgnation);
			pstmt.setLong(5, mobile_num);
			pstmt.setInt(6,salary);
			pstmt.setString(7,hire_date);
			
			pstmt.execute();
			
			System.out.println("Query Executed Succussfully");
			
			
			
			
			
			
			
			
		/*	 stmt= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,0);
			String query1 = "insert into employee(`emp_id`,`first_name`,`last_name`,`mobile_num`,`degination`,`salary`,`hire_date`) values(21,'Md Shaqib','Hussain',8789752830,'HOD',80000,'2023-01-05')";
			String query2 = "insert into employee(`emp_id`,`first_name`,`last_name`,`mobile_num`,`degination`,`salary`,`hire_date`) values(21,'Md Akib','Hussain',843450690,'Police',110000,'2001-11-11')";
			String query3 = "insert into employee(`emp_id`,`first_name`,`last_name`,`mobile_num`,`degination`,`salary`,`hire_date`) values(23,'Md Shaqib','Hussain',8789752830,'IPS',80000,'2005-01-05')";
			String query4 = "insert into employee(`emp_id`,`first_name`,`last_name`,`mobile_num`,`degination`,`salary`,`hire_date`) values(24,' Fiza','Hussain',843450560,'IAS',10000,'2000-04-11')";
			String query5= "insert into employee(`emp_id`,`first_name`,`last_name`,`mobile_num`,`degination`,`salary`,`hire_date`) values(25,'Arshi','Sheikh',8767752830,'HOD',90000,'2012-05-06')";
			String query6 = "insert into employee(`emp_id`,`first_name`,`last_name`,`mobile_num`,`degination`,`salary`,`hire_date`) values(26,' Zoya','Hussain',993450690,'buisness',40000,'2004-11-11')";
			String query7 = "insert into employee(`emp_id`,`first_name`,`last_name`,`mobile_num`,`degination`,`salary`,`hire_date`) values(27,'Md Shamshad','Hussain',7789752830,'deginer',20000,'2011-01-05')";
			String query8 = "insert into employee(`emp_id`,`first_name`,`last_name`,`mobile_num`,`degination`,`salary`,`hire_date`) values(28,'Md Faiz','Hussain',7765896403,'IAS',45000,'2022-01-06')";
			stmt.addBatch(query1);
			stmt.addBatch(query2);
			stmt.addBatch(query3);
			stmt.addBatch(query4);
			stmt.addBatch(query5);
			stmt.addBatch(query6);
			stmt.addBatch(query7);
			stmt.addBatch(query8);
			
			stmt.executeBatch();
			System.out.println("Queries Executed Succussfully");*/
			
			
		/*	stmt.execute(query);
			System.out.println("Query Executed Succussfully");
			stmt.execute(query2);
			System.out.println("Query Executed Succussfully");
			stmt.execute(query3);
			System.out.println("Query Executed Succussfully");
			stmt.execute(query4);
			System.out.println("Query Executed Succussfully");
			stmt.execute(query5);
			System.out.println("Query Executed Succussfully");
			stmt.execute(query6);
			System.out.println("Query Executed Succussfully");
			stmt.execute(query7);
			System.out.println("Query Executed Succussfully");
			stmt.execute(query8);
			System.out.println("Query Executed Succussfully");*/
			
			
		
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not loaded successfully");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		try {
	
			pstmt.close();
			con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
