package database_project;

import java.sql.*;

public class showSaleTable {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/db2022team11";
	
	static final String USER = "DBTeam11";
	static final String PASS = "DBTeam11";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String query5 = "SELECT * FROM DB2022_SALE"; //DB2022_SALE ¸Å¹°
		
		System.out.println("Connecting to database...");
		
		try(
				Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
				
				PreparedStatement pStmt5 = connect.prepareStatement(query5);
		) {
			connect.setAutoCommit(true);

			ResultSet rs5 = pStmt5.executeQuery();
			
			System.out.println("Pid: \t\t\trent type: \t\t\tprice: \t\t\tdeposit: \t\t\tsale date: \t\t\taddress: ");
			while(rs5.next()) {
				String Pid = rs5.getString(1);
				String rent_type = rs5.getString(5);
				int price = rs5.getInt(6);
				int deposit = rs5.getInt(7);
				Date sale_date = rs5.getDate(8);
				String address = rs5.getString(10);
				
				System.out.println(Pid + "\t\t\t" + rent_type + "\t\t\t" + price + "\t\t\t" + deposit + "\t\t\t" + sale_date + "\t\t\t" + address);
			}
			
		} catch(SQLException se) {
			se.printStackTrace();
		}
		
		System.out.println("GoodBye");
	}

}
