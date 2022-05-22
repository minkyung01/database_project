package database_project;

import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class screenTemplate3 extends JFrame implements ActionListener{
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/db2022team11";
	
	static final String USER = "DBTeam11";
	static final String PASS = "DBTeam11";
	
	public Connection conn;
	public Statement s;
	public ResultSet r;
	
	Container me = this;
	JPanel panel;
	JScrollPane ScPane;
	int count = 0;
	
	Font font = new Font("아임크리수진", Font.PLAIN, 12);
	
	private JComboBox Category; //열람할 테이블 선택하는 콤보버튼
	private JButton Show_Button = new JButton("확인");
	private JButton Delete_Button = new JButton("선택한 데이터 삭제");
	
	//테이블
	private Vector<String> Head = new Vector<String>();
	private JTable table;
	private DefaultTableModel model;
	private static final int BOOLEAN_COLUMN = 0;
	
	//생성자
	public screenTemplate3() {
		//콤보박스
		JPanel ComboBoxPanel = new JPanel();
		String[] category = {"지역", "매물", "부동산", "건물", "집주인"}; //열람할 테이블 선택
		Category = new JComboBox(category);
		ComboBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel msg = new JLabel("열람하고자 하는 테이블을 선택하세요: ");
		
		msg.setFont(font);
		Category.setFont(font);
		Show_Button.setFont(font);
		
		ComboBoxPanel.add(msg);
		ComboBoxPanel.add(Category);
		ComboBoxPanel.add(Show_Button);
		
		//전체 layout
		JPanel Top = new JPanel();
		Top.setLayout(new BoxLayout(Top, BoxLayout.Y_AXIS));
		Top.add(ComboBoxPanel);
		
		JPanel Halfway = new JPanel();
		Halfway.setLayout(new BoxLayout(Halfway, BoxLayout.X_AXIS));
		
		JPanel Bottom = new JPanel();
		Bottom.setLayout(new BoxLayout(Bottom, BoxLayout.X_AXIS));
		Bottom.add(Delete_Button);
		
		JPanel ShowVertical = new JPanel();
		ShowVertical.setLayout(new BoxLayout(ShowVertical, BoxLayout.Y_AXIS));
		ShowVertical.add(Halfway);
		ShowVertical.add(Bottom);
		
		add(Top, BorderLayout.NORTH);
		add(ShowVertical, BorderLayout.SOUTH);
		
		Show_Button.addActionListener(this);
		Delete_Button.addActionListener(this);
		
		//기본 설정
		setTitle("부동산 매물 DB [어쩔DB]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	//action 처리
	public void actionPerformed(ActionEvent e) {
		
		// ----------- DB 연결 ----------- //
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 연결
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
			System.out.println("정상적으로 연결되었습니다.");
			
		} catch (SQLException e1) {
			System.err.println("연결할 수 없습니다.");
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.err.println("드라이버를 로드할 수 없습니다.");
			e1.printStackTrace();
		}
		
		// ----------- 구현 ----------- //
		
		String t = ""; //table명
		
		if (count == 1) { //초기화 코드
			me.remove(panel);
			revalidate();
		}
		
		// ----------- 확인 ----------- //
		
		if (e.getSource() == Show_Button) { //확인 버튼 눌렀을 때
			Head.clear();
			Head.add("선택");
			String stmt = "";
			
			if(Category.getSelectedItem().toString() == "지역") { //지역 테이블 보여주기
				Head.add("area id");
				Head.add("구");
				Head.add("동");
				stmt = "SELECT * FROM DB2022_AREA";
				t = "DB2022_AREA";
			}
			else if(Category.getSelectedItem().toString() == "매물") { //매물 테이블 보여주기
				Head.add("Pid");
				Head.add("agency id");
				Head.add("owner id");
				Head.add("area id");
				Head.add("rent type");
				Head.add("price");
				Head.add("deposit");
				Head.add("sale date");
				Head.add("building id");
				Head.add("address");
				stmt = "SELECT * FROM DB2022_SALE";
				t = "DB2022_SALE";
			}
			else if(Category.getSelectedItem().toString() == "부동산") { //부동산 테이블 보여주기
				Head.add("agency id");
				Head.add("agency name");
				Head.add("agency address");
				Head.add("agency number");
				Head.add("area id");
				stmt = "SELECT * FROM DB2022_AGENCY";
				t = "DB2022_AGENCY";
			}
			else if(Category.getSelectedItem().toString() == "건물") { //건물 테이블 보여주기
				Head.add("building id");
				Head.add("building name");
				Head.add("building type");
				Head.add("area id");
				stmt = "SELECT * FROM DB2022_BUILDING";
				t = "DB2022_BUILDING";
			}
			else if(Category.getSelectedItem().toString() == "집주인") { //집주인 테이블 보여주기
				Head.add("owner id");
				Head.add("owner name");
				Head.add("owner number");
				stmt = "SELECT * FROM DB2022_OWNER";
				t = "DB2022_OWNER";
			}
			
			model = new DefaultTableModel(Head, 0);
			table = new JTable(model) {
				@Override
				public Class getColumnClass(int column) {
				if (column == 0) {
					return Boolean.class;
				} else
					return String.class;
				}
			};
			
			try {
				count = 1;
				s = conn.createStatement();
				r = s.executeQuery(stmt);
				ResultSetMetaData rsmd = r.getMetaData();
				int columnCnt = rsmd.getColumnCount();
				
				while (r.next()) {
				Vector<Object> tuple = new Vector<Object>();
				tuple.add(false);
				
					for (int i = 1; i < columnCnt + 1; i++) {
						tuple.add(r.getString(rsmd.getColumnName(i)));
					}
					model.addRow(tuple);
				}
				
			} catch (SQLException ee) {
				System.out.println("actionPerformed err : " + ee);
				ee.printStackTrace();
			}
			
			panel = new JPanel();
			ScPane = new JScrollPane(table);
			table.getModel().addTableModelListener(new CheckBoxModelListener());
			ScPane.setPreferredSize(new Dimension(650, 200));
			panel.add(ScPane);
			add(panel, BorderLayout.CENTER);
			revalidate();
		}
		
		// ----------- 삭제 ----------- //
		
		if(e.getSource() == Delete_Button) { //삭제 버튼 눌렀을 때
			
			if(Category.getSelectedItem().toString() == "지역") { //지역 테이블 삭제
				Vector<String> delete_area_id = new Vector<String>();
				
				try {
					String areaId = model.getColumnName(1);
					
					if(areaId == "area id") {
						for(int i = 0; i < table.getRowCount(); i++) {
							if(table.getValueAt(i, 0) == Boolean.TRUE) {
								delete_area_id.add((String) table.getValueAt(i, 1));
							}
						}
						
						for(int i = 0; i < delete_area_id.size(); i++) {
							for(int k = 0; k < model.getRowCount(); k++) {
								if(table.getValueAt(k, 0) == Boolean.TRUE) {
									model.removeRow(k);
								}
							}
						}
						
						for(int i = 0; i < delete_area_id.size(); i++) {
							String deleteStmt = "DELETE FROM DB2022_AREA WHERE area_id = ?";
							PreparedStatement p = conn.prepareStatement(deleteStmt);
							p.clearParameters();
							p.setString(1, String.valueOf(delete_area_id.get(i)));
							p.executeUpdate();
						}
					}
		
				} catch (SQLException e1) {
				System.out.println("actionPerformed err : " + e1);
				e1.printStackTrace();
				}
			}
			
			if(Category.getSelectedItem().toString() == "집주인") { //집주인 테이블 삭제
				Vector<String> delete_owner_id = new Vector<String>();
				
				try {
					String ownerId = model.getColumnName(1);
					
					if(ownerId == "owner id") {
						for(int i = 0; i < table.getRowCount(); i++) {
							if(table.getValueAt(i, 0) == Boolean.TRUE) {
								delete_owner_id.add((String) table.getValueAt(i, 1));
							}
						}
						
						for(int i = 0; i < delete_owner_id.size(); i++) {
							for(int k = 0; k < model.getRowCount(); k++) {
								if(table.getValueAt(k, 0) == Boolean.TRUE) {
									model.removeRow(k);
								}
							}
						}
						
						for(int i = 0; i < delete_owner_id.size(); i++) {
							String deleteStmt = "DELETE FROM DB2022_OWNER WHERE owner_id = ?";
							PreparedStatement p = conn.prepareStatement(deleteStmt);
							p.clearParameters();
							p.setString(1, String.valueOf(delete_owner_id.get(i)));
							p.executeUpdate();
						}
					}
		
				} catch (SQLException e1) {
				System.out.println("actionPerformed err : " + e1);
				e1.printStackTrace();
				}
			}
			
			if(Category.getSelectedItem().toString() == "부동산") { //부동산 테이블 삭제
				Vector<String> delete_agency_id = new Vector<String>();
				
				try {
					String agencyId = model.getColumnName(1);
					
					if(agencyId == "agency id") {
						for(int i = 0; i < table.getRowCount(); i++) {
							if(table.getValueAt(i, 0) == Boolean.TRUE) {
								delete_agency_id.add((String) table.getValueAt(i, 1));
							}
						}
						
						for(int i = 0; i < delete_agency_id.size(); i++) {
							for(int k = 0; k < model.getRowCount(); k++) {
								if(table.getValueAt(k, 0) == Boolean.TRUE) {
									model.removeRow(k);
								}
							}
						}
						
						for(int i = 0; i < delete_agency_id.size(); i++) {
							String deleteStmt = "DELETE FROM DB2022_AGENCY WHERE agency_id = ?";
							PreparedStatement p = conn.prepareStatement(deleteStmt);
							p.clearParameters();
							p.setString(1, String.valueOf(delete_agency_id.get(i)));
							p.executeUpdate();
						}
					}
		
				} catch (SQLException e1) {
				System.out.println("actionPerformed err : " + e1);
				e1.printStackTrace();
				}
			}
			
			if(Category.getSelectedItem().toString() == "건물") { //건물 테이블 삭제
				Vector<String> delete_building_id = new Vector<String>();
				
				try {
					String buildingId = model.getColumnName(1);
					
					if(buildingId == "building id") {
						for(int i = 0; i < table.getRowCount(); i++) {
							if(table.getValueAt(i, 0) == Boolean.TRUE) {
								delete_building_id.add((String) table.getValueAt(i, 1));
							}
						}
						
						for(int i = 0; i < delete_building_id.size(); i++) {
							for(int k = 0; k < model.getRowCount(); k++) {
								if(table.getValueAt(k, 0) == Boolean.TRUE) {
									model.removeRow(k);
								}
							}
						}
						
						for(int i = 0; i < delete_building_id.size(); i++) {
							String deleteStmt = "DELETE FROM DB2022_BUILDING WHERE building_id = ?";
							PreparedStatement p = conn.prepareStatement(deleteStmt);
							p.clearParameters();
							p.setString(1, String.valueOf(delete_building_id.get(i)));
							p.executeUpdate();
						}
					}
		
				} catch (SQLException e1) {
				System.out.println("actionPerformed err : " + e1);
				e1.printStackTrace();
				}
			}
			
			if(Category.getSelectedItem().toString() == "매물") { //매물 테이블 삭제
				Vector<String> delete_sale_id = new Vector<String>();
				
				try {
					String PId = model.getColumnName(1);
					
					if(PId == "Pid") {
						for(int i = 0; i < table.getRowCount(); i++) {
							if(table.getValueAt(i, 0) == Boolean.TRUE) {
								delete_sale_id.add((String) table.getValueAt(i, 1));
							}
						}
						
						for(int i = 0; i < delete_sale_id.size(); i++) {
							for(int k = 0; k < model.getRowCount(); k++) {
								if(table.getValueAt(k, 0) == Boolean.TRUE) {
									model.removeRow(k);
								}
							}
						}
						
						for(int i = 0; i < delete_sale_id.size(); i++) {
							String deleteStmt = "DELETE FROM DB2022_SALE WHERE Pid = ?";
							PreparedStatement p = conn.prepareStatement(deleteStmt);
							p.clearParameters();
							p.setString(1, String.valueOf(delete_sale_id.get(i)));
							p.executeUpdate();
						}
					}
		
				} catch (SQLException e1) {
				System.out.println("actionPerformed err : " + e1);
				e1.printStackTrace();
				}
			}
			
			panel = new JPanel();
			ScPane = new JScrollPane(table);
			ScPane.setPreferredSize(new Dimension(650, 200));
			panel.add(ScPane);
			add(panel, BorderLayout.CENTER);
			revalidate();
		}
		
	}
	
	public class CheckBoxModelListener implements TableModelListener {
		
		public void tableChanged(TableModelEvent e) {
			int row = e.getFirstRow();
			int column = e.getColumn();
			if (column == BOOLEAN_COLUMN) {
				TableModel model = (TableModel) e.getSource();
				String columnName = model.getColumnName(1);
				Boolean checked = (Boolean) model.getValueAt(row, column);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new screenTemplate3();
	}

}
