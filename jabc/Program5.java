package jabc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program5 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/employee";
		String un = "root";
		String pwd = "root";
		Connection con = null;
		Statement stmt  = null;
		ResultSet res = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully");
			
			 con = DriverManager.getConnection(url,un,pwd);
			System.out.println("Connection Established");
			
			
			String query ="delete from employee where first_name = 'Md Arif'";  		
			
			stmt= con.createStatement();
		
			System.out.println("Number of rows updated "+stmt.executeUpdate(query));
			
		
			
		}
			catch (ClassNotFoundException e) {
			System.out.println("Driver not loaded successfully");
			}
			catch (SQLException e) {
			e.printStackTrace();
			}

			try
			{
	
			stmt.close();  
			con.close();
			} 
			catch (SQLException e) {
			e.printStackTrace();
		}
	}		
}
