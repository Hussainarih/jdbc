package jabc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Properties;
import java.util.Scanner;

public class Blob {
	private static FileInputStream inputStream;
	private static Connection connection;
	private static Statement statement;
	private static ResultSet result;
	private static PreparedStatement statement1;
	private static FileInputStream file;
	
	private final static String QUERY = "UPDATE `company` SET `dp` = ? WHERE `id` = ?";
	private final static Scanner scan = new Scanner(System.in);
	private static CallableStatement call;

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
			
			
			// Binary large object(BLOB)
			
			
			
			
			result = statement.executeQuery("SELECT * from company");
			PrintTable.displaycompany(result);
			
			file= new FileInputStream("E:\\java\\startProject\\basicProgram\\jabc\\image\\arif.img.jpg");
			
			
			statement1 = connection.prepareStatement(QUERY);
			statement1.setBinaryStream(1, file);
			statement1.setInt(2,scan.nextInt());
			
			statement1.executeUpdate();
			
			
			
			
			
			
			
			
			
			
			System.out.println("--------------------------------");
			
			result = statement.executeQuery("SELECT * from company");
			PrintTable.displaycompany(result);
			
		
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
