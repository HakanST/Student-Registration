//Hakan Turgut
//Handler class

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JScrollPane;

public class Handler implements ActionListener 
{
	//Note: I made the program print the the roster before every time the user wants to add/delete a student/course to make it easier to refer to the whole roster of students.
	
	RegistrationSort registration = new RegistrationSort(); 
	
	JFrame jframe;
	JTextArea myTextArea;
	String rosterToString="";

	public Handler(JFrame jf) {
		jframe = jf;
	}

	public void actionPerformed(ActionEvent event) 
	{
		
		jframe.getContentPane().removeAll();
		myTextArea = new JTextArea(40,40); //Text area for which the students are going to be displayed on.
		jframe.getContentPane().add(myTextArea);
		
		JScrollPane scroll = new JScrollPane (myTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jframe.add(scroll);
		jframe.setVisible (true);

//		JScrollPane scrollPane = new JScrollPane(myTextArea); 
//		jframe.getContentPane().add(scrollPane); 
//		scrollPane.setVisible(true);
//		jframe.pack();
//		jframe.setVisible(true);
		
		String menuName = event.getActionCommand();

	    // Open file button
		if (menuName.equals("Open File")) 
		{	
			
			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
			
			jframe.setTitle("All Students");
			myTextArea.setBackground(Color.orange);
			registration.openFile();
			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
		    
		}//End of Open button
		
		//Save button
		if (menuName.equals("Save")) 
		{	
			registration.Save();
		}
		
		//Quit button
		if (menuName.equals("Quit")) 
		{
				System.exit(0);
			}
		
		//Display All Students button
		//runtime ϴ(n) n being the size of the array (or the number of students in the roster)
		if (menuName.equals("Display All Students")) //
		{
			
			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());	
			
			jframe.setTitle("All Students");
			myTextArea.setBackground(Color.orange);

			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
			
		}
		
		
		//Search For Student button 
		//runtime ϴ(n) n being the size of the array (or the number of students in the roster)
		if (menuName.equals("Search For Student")) 
		{
			
			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
			
			jframe.setTitle("Search For Student");
			myTextArea.setBackground(Color.orange);
			
			myTextArea.setText( registration.searchForStudent());
				
		}
		
		//Add Student button
		//runtime ϴ(log(n)), n being the number of students.
		if (menuName.equals("Add Student")) //
		{
			
			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
			
			jframe.setTitle("Add Student");
			myTextArea.setBackground(Color.orange);
			
			registration.addStudent();
			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
		
		}//End of Add Student button
		
		//Delete Student button
		//runtime ϴ(n), n being the number of students.
		if (menuName.equals("Delete Student")) 
		{

			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
			
			jframe.setTitle("Delete Student");
			myTextArea.setBackground(Color.orange);

			registration.deleteStudent();
			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
					
		}//End of Delete Student
		
		//Add Course button 
		//runtime ϴ(n), n being the number of students.
		if (menuName.equals("Add Course For A Student")) 
		{
			
			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
			
			jframe.setTitle("Add Course For A Student");
			myTextArea.setBackground(Color.orange);
			
			registration.addCourse();
			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
			
		}//End of "Add course for a student"
			
		//Delete Course button
		//runtime ϴ(n + m) n being number of students and m being number of courses
		if (menuName.equals("Delete Course For A Student")) //θ(n) runtime, n being the total amount of students
		{
			
			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
			
			jframe.setTitle("Delete Course For A Student");
			myTextArea.setBackground(Color.orange);

			registration.deleteCourse();
			myTextArea.setText("All Students: " + "\n\n" + registration.displayAllStudents());
			
		}//End of Delete course for a student		
		
	}//End of actionPerformed method
	
}//End of FileMenuHandler.java class //