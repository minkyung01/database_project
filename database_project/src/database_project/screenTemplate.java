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

public class screenTemplate extends JFrame {
	
	public screenTemplate() {
		setTitle("screen template");
		createMenu();
		setSize(700, 700);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void createMenu() {
		JMenuBar menu = new JMenuBar();
		JMenuItem [] menuItem = new JMenuItem[6];
		String[] innerMenu = {"전체", "지역", "매물", "부동산", "건물", "집주인"};
		
		JMenu outerMenu = new JMenu("매물 보기");
		menu.add(new JMenu("집주인 정보 관리"));
		menu.add(new JMenu("매물 관리"));
		menu.add(new JMenu("세입자 관리"));
		menu.add(outerMenu);
		setJMenuBar(menu);
		
		MenuActionListener listener = new MenuActionListener();
		for(int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(innerMenu[i]);
			menuItem[i].addActionListener(listener);
			outerMenu.add(menuItem[i]);
		}
	}
	
	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			
			switch(cmd) {
				case "전체":
					System.out.println("전체");
					break;
				case "지역":
					System.out.println("지역");
					break;
				case "매물":
					System.out.println("매물");
					break;
				case "부동산":
					System.out.println("부동산");
					break;
				case "건물":
					System.out.println("건물");
					break;
				case "집주인":
					System.out.println("집주인");
					break;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new screenTemplate();
	}

}
