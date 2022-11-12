package jabc;

import java.util.List;
import java.util.Scanner;

import com.company.dao.EmployeeDAOImp1;
import com.company.dto.Employee;

public class DaoDriver {

	public static void main(String[] args) {
		EmployeeDAOImp1 daoImp1 = new EmployeeDAOImp1();
	/*	List<Employee> list = daoImp1.getEmployee();
		for(Employee e : list) {
			System.out.println(e);
		}*/
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the Employee ID to Updated:");
		int emp_id = sc.nextInt();
		Employee e = daoImp1.getEmployee(emp_id);
		System.out.println(e);
		
		System.out.println("Enter the salary to be updated:");
		int newsalary = sc.nextInt();
		
		e.setSalary(newsalary);
		
		System.out.println(daoImp1.updateEmployee(e));
	}

}
