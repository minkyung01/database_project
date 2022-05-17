package database_project;

import java.sql.*;

public class showAreaTable {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/db2022team11";
	
	static final String USER = "DBTeam11";
	static final String PASS = "DBTeam11";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String query1 = "SELECT * FROM DB2022_AREA"; //DB2022_AREA Áö¿ª
		
		System.out.println("Connecting to database...");
		
		try(
				Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
				
				PreparedStatement pStmt1 = connect.prepareStatement(query1);
		) {
			connect.setAutoCommit(true);
			
			ResultSet rs1 = pStmt1.executeQuery();
			
			System.out.println("area id: \t\t\tarea gu: \t\t\tarea dong: ");
			while(rs1.next()) {
				String area_id = rs1.getString(1);
				String gu = rs1.getString(2);
				String dong = rs1.getString(3);
				
				System.out.println(area_id + "\t\t\t" + gu + "\t\t\t" + dong);
			}
			
		} catch(SQLException se) {
			se.printStackTrace();
		}
		
		System.out.println("GoodBye");
	}

}
