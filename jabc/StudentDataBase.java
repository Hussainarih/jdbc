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
import java.util.Scanner;

import com.company.connectors.ConnectorFactory;
import com.company.dto.Employee;

import java.util.Properties;

public class StudentDataBase {
	
	private static FileInputStream inputstream;
	private static Connection con;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet res;
	private static Scanner scan = new Scanner(System.in);
	
	
	
	private final static String INSERT_QUERY = "INSERT INTO `student`(`id`,`name`,`email`,`phone`,`degree`,`branch`,`year_of_passing`,`degree percentage`,`12th percentage`,`10th percentage`) values(?,?,?,?,?,?,?,?,?,?)";
	
	private final static String DELETE_QUERY ="delete from student where id =?";
	
	private final static String UPDATE_QUERY ="UPDATE  `student` SET  `id` = ?, WHERE `name` = ?"; 
	
	
	static void close()throws SQLException, IOException{
		if(res!=null) {
			res.close();
		}
		if(stmt!=null) {
			stmt.close();
		}
		if(con!=null) {
			con.close();
		}
		if(inputstream!=null) {
			inputstream.close();
		}
	}	

	public static void main(String[] args) {
		try {
		inputstream = new FileInputStream("E:\\java\\startProject\\basicProgram\\jabc\\src\\com\\student\\utility\\mysqlinfo.properties");
		Properties properties = new Properties();
		properties.load(inputstream);
		
		String url = properties.getProperty("url");
		String user = properties.getProperty("username");
		String pwd = properties.getProperty("password");
		
		
		con = DriverManager.getConnection(url,user,pwd);
		
		
		stmt = con.createStatement();
		
				
		PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY);
		
		
		
		
		
		do {
		
		System.out.println("Enter id");
		pstmt.setInt(1,scan.nextInt());
		System.out.println("Enter The Name:");
		pstmt.setString(2,scan.next());
		System.out.println("Enter Email:");
		pstmt.setString(3,scan.next());
		System.out.println("Enter Phone Number:");
		pstmt.setLong(4,scan.nextLong());
		System.out.println("Enter Degree:");
		pstmt.setString(5,scan.next());
		System.out.println("Enter Branch Name:");
		pstmt.setString(6,scan.next());
		System.out.println("Enter Year_of_Passing:");
		pstmt.setInt(7,scan.nextInt());
		System.out.println("Enter Your Degree Percentage:");
		pstmt.setInt(8,scan.nextInt());
		System.out.println("Enter Your 12th Percentage:");
		pstmt.setInt(9,scan.nextInt());
		System.out.println("Enter Your 10th Percentage:");
		pstmt.setInt(10,scan.nextInt());
		
		pstmt.addBatch();
		
		
		System.out.println("Do You Want To Enter More Data:(YES/NO)");
		
		
		
		
	}while("yes".equalsIgnoreCase(scan.next()));
		pstmt.executeBatch();
		
		
	//	System.out.println("Enter id");
	//	pstmt.setInt(1,scan.nextInt());
	//	pstmt.executeUpdate(DELETE_QUERY);	
		
		
		
		
		System.out.println("Enter id");
		pstmt.setInt(1,scan.nextInt());
		System.out.println("Enter The Name:");
		pstmt.setString(2,scan.next());
		pstmt.executeUpdate(UPDATE_QUERY);
		
		
		
		System.out.println("------------------------------");
		res = stmt.executeQuery("SELECT * FROM student");
		PrintTable.displayStudent(res);
		
		
		
		
		

		}

		
		
		
			
	 catch(IOException e) {
		e.printStackTrace();
		}
	catch (SQLException e) {
		e.printStackTrace();
	
		}
		
	}
	
}

	


