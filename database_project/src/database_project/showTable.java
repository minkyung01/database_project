package database_project;

import java.sql.*;

public class showTable {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/db2022team11";
	
	static final String USER = "DBTeam11";
	static final String PASS = "DBTeam11";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String query1 = "SELECT * FROM DB2022_AREA"; //DB2022_AREA 지역
		String query2 = "SELECT * FROM DB2022_OWNER"; //DB2022_OWNER 집주인
		String query3 = "SELECT * FROM DB2022_AGENCY"; //DB2022_AGENCY 부동산
		String query4 = "SELECT * FROM DB2022_BUILDING"; //DB2022_BUILDING 건물
		String query5 = "SELECT * FROM DB2022_SALE"; //DB2022_SALE 매물
		
		System.out.println("Connecting to database...");
		
		try(
				Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
				
				PreparedStatement pStmt1 = connect.prepareStatement(query1);
				PreparedStatement pStmt2 = connect.prepareStatement(query2);
				PreparedStatement pStmt3 = connect.prepareStatement(query3);
				PreparedStatement pStmt4 = connect.prepareStatement(query4);
				PreparedStatement pStmt5 = connect.prepareStatement(query5);
		) {
			connect.setAutoCommit(true);
			
			ResultSet rs1 = pStmt1.executeQuery();
			ResultSet rs2 = pStmt2.executeQuery();
			ResultSet rs3 = pStmt3.executeQuery();
			ResultSet rs4 = pStmt4.executeQuery();
			ResultSet rs5 = pStmt5.executeQuery();
			
			System.out.println("area id: \t\t\tarea gu: \t\t\tarea dong: ");
			while(rs1.next()) {
				String area_id = rs1.getString(1);
				String gu = rs1.getString(2);
				String dong = rs1.getString(3);
				
				System.out.println(area_id + "\t\t\t" + gu + "\t\t\t" + dong);
			}
			
			System.out.println("owner id: \t\t\towner name: \t\t\towner number: ");
			while(rs2.next()) {
				String owner_id = rs2.getString(1);
				String owner_name = rs2.getString(2);
				String owner_number = rs2.getString(3);
				
				System.out.println(owner_id + "\t\t\t" + owner_name + "\t\t\t" + owner_number);
			}
			
			System.out.println("agency id: \t\t\tagency name: \t\t\tagency address: \t\t\tagency number: ");
			while(rs3.next()) {
				String agency_id = rs3.getString(1);
				String agency_name = rs3.getString(2);
				String agency_address = rs3.getString(3);
				String agency_number = rs3.getString(4);
				
				System.out.println(agency_id + "\t\t\t" + agency_name + "\t\t\t" + agency_address + "\t\t\t" + agency_number);
			}
			
			System.out.println("building id: \t\t\tbuilding name: \t\t\tbuilding type: ");
			while(rs4.next()) {
				String building_id = rs4.getString(1);
				String building_name = rs4.getString(2);
				String building_type = rs4.getString(3);
				
				System.out.println(building_id + "\t\t\t" + building_name + "\t\t\t" + building_type);
			}
			
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
