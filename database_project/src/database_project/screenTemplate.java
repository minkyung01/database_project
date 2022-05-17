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
		String[] innerMenu = {"��ü", "����", "�Ź�", "�ε���", "�ǹ�", "������"};
		
		JMenu outerMenu = new JMenu("�Ź� ����");
		menu.add(new JMenu("������ ���� ����"));
		menu.add(new JMenu("�Ź� ����"));
		menu.add(new JMenu("������ ����"));
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
				case "��ü":
					System.out.println("��ü");
					break;
				case "����":
					System.out.println("����");
					break;
				case "�Ź�":
					System.out.println("�Ź�");
					break;
				case "�ε���":
					System.out.println("�ε���");
					break;
				case "�ǹ�":
					System.out.println("�ǹ�");
					break;
				case "������":
					System.out.println("������");
					break;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new screenTemplate();
	}

}
