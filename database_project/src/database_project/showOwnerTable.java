package database_project;

import java.sql.*;

public class showOwnerTable {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/db2022team11";
	
	static final String USER = "DBTeam11";
	static final String PASS = "DBTeam11";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String query2 = "SELECT * FROM DB2022_OWNER"; //DB2022_OWNER ¡˝¡÷¿Œ
		
		System.out.println("Connecting to database...");
		
		try(
				Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
				
				PreparedStatement pStmt2 = connect.prepareStatement(query2);
		) {
			connect.setAutoCommit(true);
			
			ResultSet rs2 = pStmt2.executeQuery();
			
			System.out.println("owner id: \t\t\towner name: \t\t\towner number: ");
			while(rs2.next()) {
				String owner_id = rs2.getString(1);
				String owner_name = rs2.getString(2);
				String owner_number = rs2.getString(3);
				
				System.out.println(owner_id + "\t\t\t" + owner_name + "\t\t\t" + owner_number);
			}
			
		} catch(SQLException se) {
			se.printStackTrace();
		}
		
		System.out.println("GoodBye");
	}

}
