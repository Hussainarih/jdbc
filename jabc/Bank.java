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
public class Bank {
	private static FileInputStream inputStream;
	private static Connection connection;
	private static Statement statement;
	private static ResultSet result;
	
	private final static String SELECT_QUERY = "SELECT * from `company`";
	private final static String GET_BALANCE_QUERY =  "SELECT `balance` from `company` WHERE name = ?";
	private final static String QUERY =  "UPDATE `company` SET `balance` = `balance` + ? WHERE `name` = ?" ;
	private final static Scanner scan = new Scanner(System.in);
	private static PreparedStatement stmt;
	private static PreparedStatement prepareStatement;
	

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
			
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			
			result = statement.executeQuery(SELECT_QUERY);
			PrintTable.displayCustomer(result);
			
			
//			Take Data from the user & insert into database
			
			boolean confirm = transaction();
			
			
			
			if (confirm) {
				connection.commit();
			} else {
				connection.rollback();
			}
			
			System.out.println("--------------------------------");
			
			result = statement.executeQuery(SELECT_QUERY);
			PrintTable.displayCustomer(result);
			
			
		
		} catch ( SQLException | IOException e) {
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
	
	private static boolean transaction() {
		
	
		System.out.println("Enter senders name: ");
		String sender = scan.next();
		
		System.out.println("Enter receivers name: ");
		String receiver = scan.next();
		
		System.out.println("Enter the Amount:");
		int amount = scan.nextInt();
		
		
		
		
		
		if (checkBalance(sender,amount)) {
			int x = updateBalance(sender,-amount);
			int y = updateBalance(receiver, amount);
			
			return confirm(x,y);
			
		} else {
			System.out.println("Insufficient funds!!!");
			return false;
		}
		
	}


	private static boolean checkBalance(String sender, int amount) {
		
		try {
			prepareStatement = connection.prepareStatement(GET_BALANCE_QUERY);
			prepareStatement.setString(1, sender);
			ResultSet res = prepareStatement.executeQuery();
			
			if(res.next()) {
				int balance = res.getInt(1);
				return balance >= amount;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}


	private static boolean confirm(int x, int y) {
		
		if(x==1 && y==1) {
			System.out.println("Are you sure to send the money: (yes/no)");
			return "yes".equalsIgnoreCase(scan.next());
		}
		else
		{
			System.out.println("Transaction Failed");
			return false;
		}
		
	}
	private static int updateBalance(String customer, int amount) {
		
		try {
			stmt = connection.prepareStatement(QUERY);
			
			stmt.setInt(1, amount);
			stmt.setString(2, customer);
			
			int i = stmt.executeUpdate();
			return i;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}

}







