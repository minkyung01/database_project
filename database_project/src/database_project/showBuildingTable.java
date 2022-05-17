package database_project;

import java.sql.*;

public class showBuildingTable {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/db2022team11";
	
	static final String USER = "DBTeam11";
	static final String PASS = "DBTeam11";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String query4 = "SELECT * FROM DB2022_BUILDING"; //DB2022_BUILDING °Ç¹°
		
		System.out.println("Connecting to database...");
		
		try(
				Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
				
				PreparedStatement pStmt4 = connect.prepareStatement(query4);
		) {
			connect.setAutoCommit(true);
			
			ResultSet rs4 = pStmt4.executeQuery();
			
			System.out.println("building id: \t\t\tbuilding name: \t\t\tbuilding type: ");
			while(rs4.next()) {
				String building_id = rs4.getString(1);
				String building_name = rs4.getString(2);
				String building_type = rs4.getString(3);
				
				System.out.println(building_id + "\t\t\t" + building_name + "\t\t\t" + building_type);
			}
			
		} catch(SQLException se) {
			se.printStackTrace();
		}
		
		System.out.println("GoodBye");

	}

}
