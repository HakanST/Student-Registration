//Hakan Turgut
//RegistrationGUI class


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RegistrationGUI extends JFrame
{	
	JMenuBar menuBar;
	Handler fmh;
	
	public RegistrationGUI(String title, int height, int width) 
	{
		
		setTitle(title);
		setSize(height, width);
		setLocation(400, 200);
		
		menuBar = new JMenuBar();
		fileMenu();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private void fileMenu() //File Menu
	{
		
		JMenu fileMenu = new JMenu("File"); 

		fmh = new Handler(this);
		JMenuItem item;
		item = new JMenuItem("Open File"); // Open tab
		item.addActionListener(fmh);
		fileMenu.add(item);

		fileMenu.addSeparator(); // Add a horizontal separator line to seperate tabs.
		
		item = new JMenuItem("Save");
		item.addActionListener(fmh);
		fileMenu.add(item);

		fileMenu.addSeparator();
		
		item = new JMenuItem("Quit"); // Quit tab
		item.addActionListener(fmh);
		fileMenu.add(item);

		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
	
		JMenu displayMenu = new JMenu("Display");// Display button
		item = new JMenuItem("Display All Students");
		item.addActionListener(fmh);
		displayMenu.add(item);

		displayMenu.addSeparator(); // Add a horizontal separator line to seperate tabs.

		item = new JMenuItem("Search For Student"); 
		item.addActionListener(fmh);
		displayMenu.add(item);

		setJMenuBar(menuBar);
		menuBar.add(displayMenu);
		
		JMenu editMenu = new JMenu("Edit");// Display button
		item = new JMenuItem("Add Student");
		item.addActionListener(fmh);
		editMenu.add(item);

		editMenu.addSeparator(); // Add a horizontal separator line to seperate tabs

		item = new JMenuItem("Delete Student"); 
		item.addActionListener(fmh);
		editMenu.add(item);
		
		editMenu.addSeparator(); // Add a horizontal separator line to seperate tabs
		
		item = new JMenuItem("Add Course For A Student"); 
		item.addActionListener(fmh);
		editMenu.add(item);
		
		editMenu.addSeparator(); // Add a horizontal separator line to seperate tabs
		
		item = new JMenuItem("Delete Course For A Student"); 
		item.addActionListener(fmh);
		editMenu.add(item);
		
		setJMenuBar(menuBar);
		menuBar.add(editMenu);
		
	} // createMenu
	
}
    