package database_project;

import java.sql.*;

public class showAgencyTable {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/db2022team11";
	
	static final String USER = "DBTeam11";
	static final String PASS = "DBTeam11";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String query3 = "SELECT * FROM DB2022_AGENCY"; //DB2022_AGENCY ºÎµ¿»ê

		System.out.println("Connecting to database...");
		
		try(
				Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
				
				PreparedStatement pStmt3 = connect.prepareStatement(query3);
		) {
			connect.setAutoCommit(true);
			
			ResultSet rs3 = pStmt3.executeQuery();
			
			System.out.println("agency id: \t\t\tagency name: \t\t\tagency address: \t\t\tagency number: ");
			while(rs3.next()) {
				String agency_id = rs3.getString(1);
				String agency_name = rs3.getString(2);
				String agency_address = rs3.getString(3);
				String agency_number = rs3.getString(4);
				
				System.out.println(agency_id + "\t\t\t" + agency_name + "\t\t\t" + agency_address + "\t\t\t" + agency_number);
			}
			
		} catch(SQLException se) {
			se.printStackTrace();
		}
		
		System.out.println("GoodBye");
	}

}
