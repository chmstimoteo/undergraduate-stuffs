package sicv.data.rdb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import sicv.Constants;
import sicv.data.IEmployeeRepository;
import sicv.model.employee.Employee;




public  class EmployeeRepositoryRDB implements IEmployeeRepository{

	
	
	protected ResultSet resultSet; 

	public EmployeeRepositoryRDB() {
		
	}

	
	
	public void insert(Employee employee) {
		
		Connection con = null;
		
			try {
				
				String sql = null;
				         
				Class.forName(Constants.DB_DRIVER);	       
			    con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_LOGIN, Constants.DB_PASS);
					
			      
			      
				int id = 1;
				sql = "select id from employee";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					id++;
				}
				sql = "insert into employee (id,name,login,password,status) values (";
				sql += id + ",'";
				sql += employee.getName() + "','";
				sql += employee.getLogin() + "','";
				sql += employee.getPassword() + "',";
				sql += employee.getStatus().ordinal() + ");";
				
				stmt.executeUpdate(sql);
				stmt.close();
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		

	}
	
	
	
	public Employee search(int id)  {
		Employee employee = null;
		String sql = null;
		Connection con = null;
		
        
			
	    
	    
	    
		try {

			Class.forName(Constants.DB_DRIVER);	       
		    con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_LOGIN, Constants.DB_PASS);
			
		    
			sql = "select * from employee where id=" + id + "";

			Statement stmt =  con.createStatement();
			resultSet = stmt.executeQuery(sql);
			if (resultSet.next()) {
				employee = new Employee(id,resultSet.getString("name"), resultSet.getString("login"),
						resultSet.getString("password"));
			} 
			
			
			resultSet.close();
			stmt.close();
			
			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return employee;
}

	
	
	public void update(Employee employee)  {
		String sql = null;
		
		Connection con = null;
		try {
			
			Class.forName(Constants.DB_DRIVER);	       
		    con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_LOGIN, Constants.DB_PASS);
			
			sql = "UPDATE employee SET password='" + employee.getPassword() + "', name='"
					+ employee.getName()+"',login='"+employee.getLogin()+ "',status="+ employee.getStatus().ordinal()+ " where id=" + employee.getId();
			System.out.println(sql);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	public boolean exist(int id) {
		// TODO Auto-generated method stub
		return false;
	}



	public Iterator<Employee> iterator() {
		// TODO Auto-generated method stub
		return null;
	}



	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

	 
	
}
