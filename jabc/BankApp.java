package jabc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Scanner;

public class BankApp {
	private final static String SELECT_QUERY = "SELECT * from `company`";
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/employee_bank";
		String un = "root";
		String pwd = "root";
		Connection con = null; 
		PreparedStatement statement1 = null;
		ResultSet set1 = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		PreparedStatement statement4 = null;
		PreparedStatement statement5 = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection(url, un, pwd);
			
			 Scanner scan = new Scanner(System.in);
			 
			 // Login Module
			 
			 System.out.println("<-----WELCOME TO COMPANY-BANK----->");
			 System.out.println("Enter Account Number:");
			 long acc_num = scan.nextLong();
			 System.out.println("Enter Pin Number:");
			 int pin = scan.nextInt();
			 
			 statement1 = con.prepareStatement("select * from bank where acc_num = ? and pin = ?");
			 
			 statement1.setLong(1,acc_num);
			 statement1.setInt(2,pin);
			 set1 = statement1.executeQuery();
			 
			 set1.next();
			 
			 String name = set1.getString(2);
			 int bal = set1.getInt(7);
			 
			 System.out.println("Welcome "+name);
			 System.out.println("Available Balanace is: "+bal);	 
			 
			 // Transfer Module
			 System.out.println("-----Tranfer Details----->");
			 System.out.println("Enter the User-Account Number");	
			 Long bacc_num = scan.nextLong();
			 System.out.println("Enter The Transfer Amount");
			 int t_amount = scan.nextInt();
			 
			 con.setAutoCommit(false);
			 
			 Savepoint s = con.setSavepoint();
			 
			 statement2 = con.prepareStatement("update bank set balance = balance - ?" + " where acc_num = ?");
			 statement2.setInt(1, t_amount);
			 statement2.setLong(2, acc_num);
			 
			 statement2.executeUpdate();
			 
			 System.out.println("<-----Incoming credit request----->");
			 System.out.println(name + "Bank no "+ acc_num  + " Wants To Transfer " +t_amount);
			 System.out.println("Press Y to Receive");
			 System.out.println("Press N to Reject");
			 
			String choice = scan.next();
			
			if(choice.equals("Y")) {
				statement3 = con.prepareStatement("update bank set balance = balance + ?" + "  where acc_num = ?");
				statement3.setInt(1, t_amount);
				statement3.setLong(2,bacc_num );
				statement3.executeUpdate();
				
				statement4 = con.prepareStatement("select * from bank" + " where acc_num = ?");
				statement4.setLong(1,bacc_num);
				ResultSet set2 = statement4.executeQuery();
				set2.next();
				System.out.println("Updated Balance is: "+set2.getInt(7));
				
				
				System.out.println("--------------------------------");
				
				set2 = statement4.executeQuery(SELECT_QUERY);
				PrintTable.displayCustomer(set2);
				
				
				
			}else {
				con.rollback(s);
				statement5 = con.prepareStatement("select * from bank" + " where acc_num = ?");
				statement5.setLong(1,bacc_num);
				ResultSet set2 = statement5.executeQuery();
				set2.next();
				System.out.println("Existing Balance is: "+set2.getInt(7));
				
				
				
				
			}
			
			
			
		}catch(NullPointerException e){
			e.printStackTrace();
		}
			catch(Exception e) {
				e.printStackTrace();
		}
		
		finally {
			
			try {
				statement5.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				statement4.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				statement3.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				statement2.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				statement1.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				set1.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
