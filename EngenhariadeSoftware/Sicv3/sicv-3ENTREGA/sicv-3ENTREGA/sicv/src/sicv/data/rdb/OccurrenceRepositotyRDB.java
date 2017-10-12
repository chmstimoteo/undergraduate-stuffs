package sicv.data.rdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.Constants;
import sicv.data.IOccurrenceRepository;
import sicv.model.occurrence.Occurrence;
import sicv.model.occurrence.Priority;
import sicv.util.Position;
import sicv.util.Status;

public class OccurrenceRepositotyRDB implements IOccurrenceRepository{

	
	
	
public void insert(Occurrence occurrence) {
		
		Connection con = null;
		
			try {
				
				String sql = null;
				         
				Class.forName(Constants.DB_DRIVER);	       
			    con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_LOGIN, Constants.DB_PASS);
					
			      
			      
				int id = 1;
				sql = "select id from occurrence";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					id++;
				}
				
				
				sql = "insert into occurrence (priority,status,id,lat,lng) values (";
				sql += occurrence.getPriority().ordinal() + ",";
				sql += occurrence.getStatus().ordinal() + ",";
				sql += occurrence.getId() + ",";
				sql += occurrence.getPosition().getLat() + ",";
				sql += occurrence.getPosition().getLng()+ ");";
				
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
	
	
	
	public Occurrence search(int id)  {
		Occurrence occurrence = null;
		String sql = null;
		Connection con = null;
		
        
			
	    
	    
	    
		try {

			Class.forName(Constants.DB_DRIVER);	       
		    con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_LOGIN, Constants.DB_PASS);
			Status status = null;
		    
			sql = "select * from occurrence where id=" + id + "";

			Statement stmt =  con.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			
			if (resultSet.next()) {
				
				int s = resultSet.getInt("status");
				
				switch (s) {
				case 1: status = Status.IN_ATTENDANT ;break;
				case 2: status =Status.IN_INITIAL_ATTENDANT ;break;
				case 3: status =Status.WAITING; break;
				}
							
				
				Priority priority = null;
				
				int p = resultSet.getInt("priority");
				
				switch (p) {
				case 0: priority =Priority.LOW; break;
				case 1: priority = Priority.HIGH ;break;
				case 2: priority =Priority.AVERAGE ;break;
				default:
					break;
				}
				
				int lat = resultSet.getInt("lat");
				int lng = resultSet.getInt("lng");
				Position position = new Position(lat,lng);
				occurrence = new Occurrence(priority,status,position);
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
		return occurrence;
}

	
	
	
	public void update(Occurrence occurrence)  {
		String sql = null;
		
		Connection con = null;
		try {
			
			Class.forName(Constants.DB_DRIVER);	       
		    con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_LOGIN, Constants.DB_PASS);
			
			sql = "UPDATE occurrence SET status=" + occurrence.getStatus().ordinal() + ", priority="
					+ occurrence.getPriority().ordinal()+",lat="+occurrence.getPosition().getLat()+",lng="+occurrence.getPosition().getLng() + " where id=" + occurrence.getId();
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
		
		
		
		boolean response = false;
		String sql = null;
		
		Connection con = null;
		
		try {
			
			
			sql = "select * from occurrence where id=" + id + "";
			
			Class.forName(Constants.DB_DRIVER);	       
		    con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_LOGIN, Constants.DB_PASS);
		    
		    
			Statement stmt =  con.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			response = resultSet.next();
			resultSet.close();
			stmt.close();
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
		
		return response;
		
		
	}



	public Iterator<Occurrence> iterator() {
		
		
		
		Occurrence occurrence = null;
		String sql = null;
		Connection con = null;
		List<Occurrence> list = new ArrayList<Occurrence>();
		
		try {

			Class.forName(Constants.DB_DRIVER);	       
		    con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_LOGIN, Constants.DB_PASS);
			Status status = null;
		    
			sql = "select * from occurrence";

			Statement stmt =  con.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			
			if (resultSet.next()) {
				
				int s = resultSet.getInt("status");
				
				switch (s) {
				case 1: status = Status.IN_ATTENDANT ;break;
				case 2: status =Status.IN_INITIAL_ATTENDANT ;break;
				case 3: status =Status.WAITING; break;
				}
							
				
				Priority priority = null;
				
				int p = resultSet.getInt("priority");
				
				switch (p) {
				case 0: priority =Priority.LOW; break;
				case 1: priority = Priority.HIGH ;break;
				case 2: priority =Priority.AVERAGE ;break;
				default:
					break;
				}
				
				int lat = resultSet.getInt("lat");
				int lng = resultSet.getInt("lng");
				int id = resultSet.getInt("id");
				Position position = new Position(lat,lng);
				occurrence = new Occurrence(priority,status,position);
				list.add(occurrence);
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
		return list.iterator();
		
		
	}



	public void remove(int id) {
		
		
	}


	
}
