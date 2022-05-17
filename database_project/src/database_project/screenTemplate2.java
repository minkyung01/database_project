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

public class screenTemplate2 extends JFrame {
	
	public screenTemplate2() {
		setTitle("screen template2");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout(50, 0));
		Color bg = new Color(244, 244, 244);
		
		JPanel top = new JPanel();
		top.setBackground(Color.black);
		c.add(top, BorderLayout.NORTH);
		JPanel middle = new JPanel();
		middle.setBackground(bg);
		c.add(middle, BorderLayout.CENTER);
		
		GridLayout grid = new GridLayout(1, 3);
		grid.setHgap(30);
		
		top.setLayout(grid);
		
		JButton home = new JButton("home");
		home.setBackground(Color.black);
		home.setForeground(Color.white);
		home.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		home.setBorderPainted(false);
		top.add(home);
		
		JButton title = new JButton("ºÎµ¿»ê ¸Å¹° DB ¾îÂ¿DB");
		title.setBackground(Color.black);
		title.setForeground(Color.white);
		title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		title.setBorderPainted(false);
		top.add(title);
		
		JButton id = new JButton("id: assertive_11");
		id.setBackground(Color.black);
		id.setForeground(Color.white);
		id.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		id.setBorderPainted(false);
		top.add(id);
		
		middle.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 100));
		JPanel menu = new JPanel();
		menu.setBackground(Color.green);
		menu.setSize(600, 200);
		middle.add(menu, BorderLayout.WEST);
		JPanel table = new JPanel();
		table.setBackground(Color.yellow);
		table.setSize(600, 400);
		middle.add(table, BorderLayout.EAST);
		
		setSize(700, 700);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new screenTemplate2();
	}

}
