package jabc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintTable {
	

/*	public static void displayemployees(ResultSet result)throws SQLException {
		while(result.next()) {
		System.out.printf("%-3s %-10s %-13s %-18s %-23s %-28s %-10s \n",  result.getString(1),result.getString(2),result.getString(3),result.getString(4),
				result.getString(5),
				result.getString(6),
				result.getString(7));
				*/
			
		
	/*	public static void displayCustomer(ResultSet result)throws SQLException {
			while(result.next()) {
			System.out.printf("%-3s %-10s %-13s %-18s %-23s %-28s %-10s \n",  result.getString(1),result.getString(2),result.getString(3),result.getString(4),
					result.getString(5),
					result.getString(6),
					result.getString(7));
			*/
			
			
			public static void displaycompany(ResultSet result)throws SQLException {
				while(result.next()) {
					System.out.printf("%-3s %-10s %-13s %-18s %-23s %-28s %-30s  \n",  result.getString(1),result.getString(2),result.getString(3),result.getString(4),
							result.getString(5),
							result.getString(6),
							//result.getBlob(7),
							result.getString(7));
		}
		
	}
}