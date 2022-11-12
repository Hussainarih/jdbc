package jabc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee {
	
	private final static String QUERY = "update company SET salary= salary - 500000 ";
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/company";
		String username = "root";
		String password = "root";
		Connection con = null;
		Statement stmt = null;
		ResultSet res =  null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully");
			
			con = DriverManager.getConnection(url,username,password);
			System.out.println("Connection Established");
			
			
			stmt= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,0);
			
			String query = "select * from company";
			
			
			
			
			
		
			
			stmt.executeUpdate(QUERY);
			
			
			
			res = stmt.executeQuery(query);
			
			
			
			
			
			while(res.next()) {
				System.out.println(res.getInt(1)+" "+ res.getString(2)+" "+res.getString(3)+" "+res.getString(4)+" "+res.getString(5)+" "+res.getInt(6)+" "+res.getInt(7));
			}
		
		
	} catch (ClassNotFoundException e) {
		System.out.println("Driver not loaded successfully");
		
	}
	catch (SQLException e) {
		e.printStackTrace();
	
		}
		finally {
			
			try{
				res.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			try{
				stmt.close();
			}catch (SQLException e) {
				e.printStackTrace();
				
			}try{
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
				
			}
		}

	}

}
	