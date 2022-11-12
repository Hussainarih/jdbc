package jabc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class program6 {
	

	

		private static FileInputStream inputStream;
		private static Connection connection;
		private static Statement statement;
		private static ResultSet result;
		
		private final static String QUERY =  "INSERT into `employees` (`useremp_id`,`first_name`,`last_name`,`mobile_num`,`Degination`,`salary`,`Hire_date`) values (?,?,?,?,?,?,?) ";
		private final static Scanner scan = new Scanner(System.in);
		

		static void close() throws SQLException, IOException {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		
		
		
		
		
		
		
		
		
		
		public static void main(String[] args) {
			
			try {
				
				inputStream = new FileInputStream("E:\\java\\startProject\\basicProgram\\jabc\\src\\com\\company\\utility\\mysqlinfo.properties");
			
				Properties properties = new Properties();
				
				properties.load(inputStream);
				
				String url = properties.getProperty("url");
				String username = properties.getProperty("username");
				String password = properties.getProperty("password");
				
				connection = DriverManager.getConnection(url, username, password);
				
				statement = connection.createStatement();
				
				result = statement.executeQuery("SELECT * from employees");
				PrintTable.displayemployees(result);
				
//				Take Data from the user & insert into database
				
				PreparedStatement stmt = connection.prepareStatement(QUERY);
				
				do {

					System.out.println("Id: ");
					stmt.setInt(1, scan.nextInt());
					
					System.out.println("First-Name:");
					stmt.setString(2, scan.next());
					
					System.out.println("Last_Name:");
					stmt.setString(3, scan.next());
					
					System.out.println("Mobile-No:");
					stmt.setLong(4, scan.nextLong());
					
					System.out.println("Degination:");
					stmt.setString(5, scan.next());
					
					
					
					System.out.println("Salary:");
					stmt.setInt(6, scan.nextInt());
					
					System.out.println("Hire_date:");
					stmt.setString(7, scan.next());
					
					stmt.addBatch();

					System.out.println("Do you want to enter more data: (yes/no)");
				} while ("yes".equalsIgnoreCase(scan.next()));
				
				stmt.executeBatch();
				
				
				
				System.out.println("--------------------------------");
				
				result = statement.executeQuery("SELECT * from employees");
				PrintTable.displayemployees(result);
				
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}

	}

