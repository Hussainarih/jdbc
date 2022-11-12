package jabc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Program3 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/employee";
		String un = "root";
		String pwd = "root";
		Connection con = null;
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
			System.out.println("Enter the number of the rows to be inserted:");
			int n = scan.nextInt();
			
			con.setAutoCommit(false);
			
			for (int i = 1; i <=n; i++) {
				int emp_id = scan.nextInt();
				String first_name = scan.next();
				String last_name = scan.next();
				String degination = scan.next();
				long mobile_num = scan.nextLong();
				int salary = scan.nextInt();
				String hire_date = scan.next();
				
				pstmt.setInt(1,emp_id);
				pstmt.setString(2,first_name);
				pstmt.setString(3,last_name);
				pstmt.setString(4,degination);
				pstmt.setLong(5,mobile_num);
				pstmt.setInt(6,salary);
				pstmt.setString(7,hire_date);
				
				pstmt.execute();
			}
			
			con.commit();
			
		}
			catch (ClassNotFoundException e) {
			System.out.println("Driver not loaded successfully");
			}
			catch (SQLException e) {
			e.printStackTrace();
			}

			try
			{
	
			pstmt.close();
			con.close();
			} 
			catch (SQLException e) {
			e.printStackTrace();
		}
	}

}


	


