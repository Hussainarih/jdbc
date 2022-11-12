package jabc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/employee";
		String un = "root";
		String pwd = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully");
			
			Connection con = DriverManager.getConnection(url,un,pwd);
			System.out.println("Connection Established");
			
			
			Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,0);
			
			String query = "select * from employee";
			
			ResultSet res = stmt.executeQuery(query);
			
			System.out.println("Query Executed");
			
			ResultSetMetaData data = res.getMetaData();
			
			for(int i = 1; i<=data.getColumnCount(); i++) {
				System.out.println(data.getColumnName(i)+" "+ data.getColumnTypeName(i));
			}
			
			while(res.next()==true) {
				System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getLong(4)
				+" "+res.getString(5)+" "+res.getInt(6)+" "+ res.getString(7));
			}
			
			res.absolute(3);
		  System.out.println(res.getInt(1)+" " +res.getString(2)+" " +res.getString(3)+" " +res.getLong(4)+" " +res.getString(5)+" " +res.getInt(6)+" " +res.getDate(7));
			
			
			
		/*	System.out.println(data.getColumnCount());
			System.out.println(data.getColumnName(1)+" "+data.getColumnName(2)+" "+data.getColumnName(3)+" "+data.getColumnName(4)
			+" "+data.getColumnName(5)+" "+data.getColumnName(6)+" "+data.getColumnString(7));
			*/
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not loaded successfully");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		
	}

	}

}
