//Hakan Turgut
//RegistrationSort class

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class RegistrationSort {
	
		//Temporary variables to hold student data for current student 
		String firstName = ""; 
		String lastName = "";
		String ID = "";
		double totalCredits = 0;
		double GPA = 0.0;
		
		//Temporary variables to hold course data for current course
		String courseNumber = "";
		Double courseCredits = 0.0;
		String courseGrade = "";
		double earnedCredits = 0.0;
		
		String rosterToString = new String("");
		
		ArrayList <Course> courseList = new ArrayList <Course>(); //ArrayList for each course object
		Student[] roster = new Student[20]; //ArrayList for each student object. When roster array gets full, it gets resized to hold more students.
		Course tempCourse = new Course (courseNumber, courseCredits, courseGrade); //Temporary object to hold course data of current course
		Student tempStudent = new Student(firstName, lastName, ID, courseList, totalCredits,GPA) ;//Temporary object for to hold student data of current student
		
		int count=0;
	
	public void openFile() {	
	    String line = null;
		try
	    {
	    	
			JFileChooser fd = new JFileChooser();     
	        fd.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        fd.showOpenDialog(null);
			File f = fd.getSelectedFile(); //Whatever file the user clicks on, it gets stored in to variable f
		    
		    FileReader in = new FileReader(f);
		    BufferedReader br = new BufferedReader(in); //Opens file
	    
		    while ((line = br.readLine()) != null) //While line is not null
		    {	
		    	
		    	tempStudent = new Student(firstName, lastName, ID, courseList, totalCredits,GPA);
				
				lastName = "";
				firstName = "";
				ID = "";
				
		 	 	StringTokenizer myTokens = new StringTokenizer(line, ","); //Each line is broken up by commas in text file
		 
		  	 	if(myTokens.hasMoreTokens()) 
				 lastName = myTokens.nextToken(); //First token represents last name
		  	 	
		 	 	if(myTokens.hasMoreTokens()) 
				 firstName = myTokens.nextToken(); //Second token represents first name
		   	
		   	 	if(myTokens.hasMoreTokens()) 
				 ID = myTokens.nextToken();  //Third token represents ID number
		   	 	
		   	 	tempStudent.setFirstName(firstName); //Temporary object stores first name of current student
				tempStudent.setLastName(lastName); //Temporary object stores last name of current student
				tempStudent.setID(ID); //Temporary object stores ID of current student
		   	 	
				while (((line = br.readLine()) != null) && (!line.equals("-999")))  //reads and stores course information. It will keep doing this until -999 is read. -999 represents the end of courses
		   		{
		   		 
					tempCourse = new Course (courseNumber, courseCredits, courseGrade);
		   		 
		   			courseNumber = "";
		   			courseCredits = 0.0;
		   			
		   			myTokens = new StringTokenizer(line, ",");	
		   			  			
		   			if(myTokens.hasMoreTokens()) 
		   				courseNumber = myTokens.nextToken(); //First token of line after student info holds course number
		   		
		   			if(myTokens.hasMoreTokens()) 
		   				courseCredits = Double.parseDouble(myTokens.nextToken()); //Second token of line after student info holds course credits

		   			if(myTokens.hasMoreTokens()) 
		   				courseGrade = myTokens.nextToken();  //Third token of line after student info holds course grade
		   			
		   			tempCourse.setCourseNumber(courseNumber); //Temporary course object stores course number of current course
		   			tempCourse.setCourseCredits(courseCredits); //Temporary course object stores course credits of current course
		   			tempCourse.setGrade(courseGrade); //Temporary course object stores grade received of current course
		   			
		   			courseList.add(tempCourse); //Course data of the temporary object added to the course ArrayList
				
		   		} // end of inner while
		   		
		   		totalCredits = 0;
		   		GPA = 0.0;
		   		
		   		line=br.readLine(); //Last line is read and stores the last information for the student which is the total credits and GPA
		   		myTokens = new StringTokenizer(line, ",");
		   		
		   		//Last two temporary variables store both total credits and GPA
		   		if(myTokens.hasMoreTokens()) 
						totalCredits = Double.parseDouble(myTokens.nextToken()); //First token of last line holds total credits
		   		
		   		if(myTokens.hasMoreTokens()) 
						GPA = Double.parseDouble(myTokens.nextToken()); //Second token of last line holds GPA
		   		
		   		tempStudent.setTotalCredits(totalCredits); //Total credits stored to object
		   		tempStudent.setGPA(GPA); //GPA stored to student object
		   		tempStudent.setListCourses(courseList); //Course ArrayList stored to temporary student object
				
				insertToArray(tempStudent); //Current student data added to student ArrayList
				
				
				courseList = new ArrayList <Course>(); //Reset for next course
    	 
		    }//while
		    
		    in.close(); //closes file
		    br.close();
		    
	    }//End of Try
	    
	    catch (FileNotFoundException e)
	    {
	    	System.out.println(e.getMessage());
	    }
	    
	    catch (IOException e)
	    {
	    	System.out.println(e.getMessage());
	    }
	    
	    catch (IllegalArgumentException e) //
	    {
	    	System.out.println(line+" ("+ e.getMessage()+")"); //Data value does not satisfy require of class parameter.
	    }
	    
	    catch (NullPointerException e)
	    {
	    	System.out.println("User cancelled chosing file."); 
	    	//Since the program is looking for a file, when the user clicks on cancel, no file is opened. 
	    	//It stores in null as a result of this, causing an error and throwing an exception.
	    }
		
		rosterToStringM();

	}
	
	public void Save(){
		
		JFileChooser fileChooser = new JFileChooser(); 		
		int userSelection = fileChooser.showSaveDialog(null);
		
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
		    
    		try(FileWriter fw = new FileWriter(fileToSave.getAbsolutePath())) {
    		    fw.write(rosterToString);
    		    fw.close();
    		    
    			JOptionPane.showMessageDialog(null, "Roster successfully saved!");
    		}
    		catch (IOException io) {
    			JOptionPane.showMessageDialog(null,"Error saving roster.");
    		}
        } else {
        	JOptionPane.showMessageDialog(null,"Error saving roster.");
        }	
		
	}
	
	//runtime ϴ(n) n being the size of the array (or the number of students in the roster)
	public String displayAllStudents() 
	{
		return rosterToString;
	}
	
	//runtime ϴ(n) n being the size of the array (or the number of students in the roster)
	public String searchForStudent() 
	{
		
		String searchStudentL = JOptionPane.showInputDialog(null, "Enter the LAST name of the student you want to search from the roster");
		String searchStudentF = JOptionPane.showInputDialog(null, "Enter the FIRST name of the student you want to search from the roster");
		
			for (int i = 0; i<count; i++) 	
			{
				
				if ( ( roster[i].getLastName().equalsIgnoreCase(searchStudentL) ) && ( roster[i].getFirstName().equalsIgnoreCase(searchStudentF) ) ){
					return ("Searched Student: " +"\n\n" + studentToStringM(roster[i]));
					
				}
		
				if ( (i==count-1) && ( !( roster[i].getLastName().equalsIgnoreCase(searchStudentL) ) && !( roster[i].getFirstName().equalsIgnoreCase(searchStudentF) ) ) ){
					JOptionPane.showMessageDialog(null, "This student does not exist!");
					return null;
				}
				
			}
			JOptionPane.showMessageDialog(null, "This student does not exist!");
			return null;	

	}
	
	//runtime ϴ(log(n)), n being the number of students.
	public void addStudent() 
	{
		
		tempStudent = new Student(firstName, lastName, ID, courseList, totalCredits,GPA);
		
		lastName = "";
		firstName = "";
		ID = "";
			
		firstName = JOptionPane.showInputDialog(null, "Enter student's FIRST name to add another student. Enter DONE if no more students need to be added");
			
		lastName = JOptionPane.showInputDialog(null, "Enter student's LAST name");

		tempStudent.setFirstName(firstName); //Temporary object stores first name of current student
		tempStudent.setLastName(lastName); //Temporary object stores last name of current student

		//Both boolean variables will be used to test the name comparison
		boolean sameLastName = false; 
		boolean sameFirstName = false;
				
		//Compares last name
	   	for (int i = 0; i<count; i++)
	   	{
	   		
	   		sameLastName = roster[i].compareLastName(tempStudent); //Last name of current student compared to already existing students

			if (sameLastName == true)
   				break;
	   		
		}
	   		
	   		//If last name is the same, first name gets compared
	   		if (sameLastName==true)
	   		
	   		{
	   			
	   			for (int i = 0; i<count; i++)
	   			{
	   				
	   				sameFirstName = roster[i].compareFirstName(tempStudent); //First name of current student gets compared

	   				if (sameFirstName == true)
	   					break;			
	   				
	   			}
	   			
	   		}
	   		
	   		if (sameFirstName==true) //If first and last names are the same, message gets printed
	   			JOptionPane.showMessageDialog(null, "Another student in the roster also has the same first and last name");
	   		
	   		while(true) //While loop for ID number
	   		{
	   			
	   			ID = JOptionPane.showInputDialog(null, "Enter student's ID number");
	   			
	   			while(ID.length()!=6)
	   				ID = JOptionPane.showInputDialog(null, "ERROR! ID number must be 6 digits, please re-enter");
	   			
	   			tempStudent.setID(ID);
	   	
	   			boolean sameID=false;
	   		
				//Checks if ID number is equal to other student's ID numbers
	   			for (int i = 0; i<count; i++)
	   				{
	   		
	   				sameID = roster[i].isEqualID(tempStudent); //Current ID number compared to already existing ID numbers

	   					if (sameID == true)
	   						break;
	   				
	   				}
	   			
	   			if (sameID == true)
	   					JOptionPane.showMessageDialog(null, "Error! Another student already has this ID number");
	   			
	   			if (sameID == false)
	   			break;
	   			
	   		}
	   		
	   		while(true) //allows user to enter multiple courses for each student
	   		{
	   			
	   			courseNumber = "";
	   			courseCredits = 0.0;
	   			courseGrade = "";
	   				
	   			tempCourse = new Course (courseNumber, courseCredits, courseGrade); //re-initializing prevents a new added course from having data of the previous course
	   				
	   			courseNumber = JOptionPane.showInputDialog(null, "Enter course number to add another course. Enter DONE if the student doesn't have any more coures.");
	   			
	   			if (courseNumber.equalsIgnoreCase("DONE")) //Enter "STOP" when no more courses need to be added
					break;
	   				
	   			String courseCreditsString = JOptionPane.showInputDialog(null, "Enter course credits");
	   			courseCredits = Double.parseDouble(courseCreditsString);
			
	   			courseGrade = JOptionPane.showInputDialog(null, "Enter grade received");
	   			
				tempCourse.setCourseNumber(courseNumber); //Temporary course object stores course number of current course
	   			tempCourse.setCourseCredits(courseCredits); //Temporary course object stores course credits of current course
	   			tempCourse.setGrade(courseGrade); //Temporary course object stores grade received of current course
	   			
	   			//This code fragment was supposed to test whether the course numbers of two courses were equal or not but I was not able to successfully debug it.
	    		//
				while(true)
				{
	    			boolean sameCourseNumber = false;
	    		   
	    			//Compares course number with existing courses course number
	    			for (int j = 0; j< courseList.size(); j++)
	    			{
	    				sameCourseNumber = (tempCourse.isEqualCourseNumber(courseList.get(j))); //current course in course array list being compared to rest of the courses in the course array list. Changed this
	    				
	    				if (sameCourseNumber == true)
	        				throw new IllegalArgumentException("Error! This student is already taking another course with that same course number.");
	    			}
	    		   
	    			if (sameCourseNumber == false) {
	    	   			courseList.add(tempCourse); //Course data of the temporary object added to the course ArrayList		
	    				break;
	    			}
			   }
	   		}//end of inner while loop
	   		
	   		
	   		tempStudent.setListCourses(courseList); //Course ArrayList stored to temporary student object
	   		
	   		tempStudent.setTotalCredits(countCredits(tempStudent));
	   		tempStudent.setGPA(calculateGPA(tempStudent));
				
			insertToArray(tempStudent); //Current student data added to student ArrayList
			
			courseList = new ArrayList <Course>(); 
	  
	   		rosterToStringM();
	   		
	}
	
	//runtime ϴ(n) n being the size of the array (or the number of students in the roster)
	public void deleteStudent() 
	{
		String searchStudentL = JOptionPane.showInputDialog(null, "Enter the LAST name of the student you want to remove from the roster");
		String searchStudentF = JOptionPane.showInputDialog(null, "Enter the FIRST name of the student you want to remove from the roster");

				for (int i = 0; i<count; i++) {
					
					if ( ( roster[i].getLastName().equalsIgnoreCase(searchStudentL) ) && ( roster[i].getFirstName().equalsIgnoreCase(searchStudentF) ) )
						
					{
						
						//shifts the array to the left, overriding the data of the student that needs to be deleted
						for (int j=i;j<count;j++)
							roster[j]=roster[j+1];
						count--;
						break;
						
					}
				
					if ( (i==count-1) && ( !( roster[i].getLastName().equalsIgnoreCase(searchStudentL) )  ) ) 
						
						JOptionPane.showMessageDialog(null, "This student does not exist!");
					
				}
				
		rosterToStringM();

	}
	
	//runtime ϴ(n) n being the number of students
	public void addCourse() 
	{
			
		String searchStudentL = JOptionPane.showInputDialog(null, "Enter the LAST name of the student you want to add a course to.");
		String searchStudentF = JOptionPane.showInputDialog(null, "Enter the FIRST name of the student you want to add a course to.");

			for (int i = 0; i<count; i++)
				
			{
				
				if ( ( roster[i].getLastName().equalsIgnoreCase(searchStudentL) ) && ( roster[i].getFirstName().equalsIgnoreCase(searchStudentF) ) )
				{

			   		courseNumber = "";
					courseCredits = 0.0;
			   		courseGrade = "";
				   				
		   			tempCourse = new Course (courseNumber, courseCredits, courseGrade); //re-initializing prevents a new added course from having data of the previous course
				   				
				  	courseNumber = JOptionPane.showInputDialog(null, "Enter course number to add another course. Enter DONE if the student doesn't have any more coures.");
				   			 				
		   			String courseCreditsString = JOptionPane.showInputDialog(null, "Enter course credits");
		   			courseCredits = Double.parseDouble(courseCreditsString);
						
				   	courseGrade = JOptionPane.showInputDialog(null, "Enter grade received");

					tempCourse.setCourseNumber(courseNumber); //Temporary course object stores course number of current course
				   	tempCourse.setCourseCredits(courseCredits); //Temporary course object stores course credits of current course
				   	tempCourse.setGrade(courseGrade); //Temporary course object stores grade received of current course
				   			
				   	
					while(true)
					{
				    			
						boolean sameCourseNumber = false;
				    		   
				    	//Compares course number with existing courses course number
				 		for (int k = 0; k< (roster[i].listCourses.size()); k++)
				    	{
				 			
				    		sameCourseNumber = tempCourse.isEqualCourseNumber(roster[i].listCourses.get(k)); //current course in course array list being compared to rest of the courses in the course array list
				    				
				    		if (sameCourseNumber == true)
				        		throw new IllegalArgumentException("Error! This student is already taking another course with that same course number.");
				    	
				    	}
				    		   
				    	if (sameCourseNumber == false) {
				    		
				    	   	roster[i].listCourses.add(tempCourse); //Course data of the temporary object added to the course ArrayList		
				    				
				    	   	roster[i].setTotalCredits(countCredits(roster[i]));
				    	   	roster[i].setGPA(calculateGPA(roster[i]));
				    	   	
				    	   	rosterToStringM();
//				    	   	return ("All Students: " + "\n" + "\n" + rosterToString);
				    	   	
				    	   	return;
//				    	 	break;
//				    	   	return;
				    			
				    	}
				    			
					}//end of inner while loop
							
				}//End of if statement
				
				if ( (i==count-1) && (!( roster[i].getLastName().equalsIgnoreCase(searchStudentL) ) || !( roster[i].getFirstName().equalsIgnoreCase(searchStudentF) ) ))
				{
					JOptionPane.showMessageDialog(null, "This student does not exist!");
					return;
				}
				
			}//End of for loop
				
			rosterToStringM();
	}
	
	//runtime ϴ(n + m) n being number of students and m being number of courses
	public String deleteCourse() 
	{
		
		String searchStudentL = JOptionPane.showInputDialog(null, "Enter the LAST name of the student whose class needs to be removed.");
		String searchStudentF = JOptionPane.showInputDialog(null, "Enter the FIRST name of the student whose class needs to be removed.");

		String courseToDelete =  JOptionPane.showInputDialog(null, "Enter the number of the class that needs to be deleted.");

		for (int i = 0; i<count; i++) 
		{
			
			if ( ( roster[i].getLastName().equalsIgnoreCase(searchStudentL) ) && ( roster[i].getFirstName().equalsIgnoreCase(searchStudentF) ) )
			{
				
				for (int j = 0; j<roster[i].listCourses.size(); j++)
				{
					
					if (roster[i].listCourses.get(j).getCourseNumber().equals(courseToDelete))
					{
						
						roster[i].listCourses.remove(j);//If typed course number exists, it gets removed
				
						roster[i].setTotalCredits(countCredits(roster[i])); //Recount Credits since a course was removed
						roster[i].setGPA(calculateGPA(roster[i])); //Recalculate GPA since a course was removed
							
						rosterToStringM();
						return ("All Students: " +"\n" +"\n" + rosterToString);
						
					}
					
					if ((j==roster[i].listCourses.size()-1)&&(!(roster[i].listCourses.get(j)).equals(courseToDelete))){ //If Student doesn't exist
							
						JOptionPane.showMessageDialog(null, "This course does not exist!");
						return null;
							
					}	
				}
			
			}
		
			if ( (i==count-1) && (!( roster[i].getLastName().equalsIgnoreCase(searchStudentL) ) || !( roster[i].getFirstName().equalsIgnoreCase(searchStudentF) ) ))
			{
				JOptionPane.showMessageDialog(null, "This student does not exist!");
				return null;
			}
			
		}
		return null;
		
	}
	
	//runtime ϴ(log(n)) n being number of students 
	private void insertToArray(Student studentIn){
		
		int i;
		
		//Resize array if full 
		if (count==roster.length)
		{
			//fill new array
			Student[] rosterTemp = new Student[roster.length*2]; 
			for (int j=0;j<=count-1;j++)
				rosterTemp[j] = roster[j];	
			
			roster = rosterTemp; //Gives original roster a larger size

		}
		
		for ( i = count-1;(i>=0 && studentIn.getLastName().compareTo(roster[i].getLastName())<0);i--)
			roster[i+1] = roster[i];
		roster[i+1]=studentIn;
		count++;
		
	}
	
	//Calculates GPA of student
	private static double calculateGPA(Student student)
	{
		
		double earnedCredits=0;
		double totalCreditsM=0;
		double GPA=0;
		double roundedGPA=0;
		
		for (int i = 0; i<student.listCourses.size(); i++)
			earnedCredits += ((gradeConversion(student.listCourses.get(i).getCourseGrade()) ) * (student.listCourses.get(i).getCourseCredits())); //gradeConversion method called to return the credit value of each course for each student which gets multiplied to the course credit value 
		
			GPA = (earnedCredits/student.getTotalCredits()); //earned credits divided by total credits is equal to GPA value
			roundedGPA = Math.round(GPA * 1000.0) / 1000.0; //Rounds GPA to hundredth's decimal place
			
			return roundedGPA;
			
	}	//End of method
	
	//Counts total credits of a student
	private static double countCredits(Student student)
	{
		
		double credits=0;
		
		for (int i=0;i<student.listCourses.size();i++)
			credits+=student.listCourses.get(i).getCourseCredits();
		
		return credits;
		
	}//End of method
	
	//Converts letter grades to numerical value
	private static double gradeConversion (String grade)
	{
		
		if (grade.equals("A+"))
			return 4.0;
		
		if (grade.equals("A"))
			return 4.0;
		
		if (grade.equals("A-"))
			return 3.7;
		
		if (grade.equals("B+"))
			return 3.3;
		
		if (grade.equals("B"))
			return 3.0;
		
		if (grade.equals("B-"))
			return 2.7;
		
		if (grade.equals("C+"))
			return 2.3;
		
		if (grade.equals("C"))
			return 2.1;
		
		if (grade.equals("C-"))
			return 1.7;
		
		if (grade.equals("D+"))
			return 1.3;
		
		if (grade.equals("D"))
			return 1.0;
		
		if (grade.equals("F"))
			return 0.0;
		
		return 0;
		
	}//End of gradeConversion method
	
	//Stores all of the roster data for displaying all students 
	//runtime ϴ(n) n being the size of the array (or the number of students in the roster)
	private void rosterToStringM(){

		rosterToString="";
		
		for (int i = 0; i<count; i++) //i represents each student
   		{	
			if (roster[i] != null){
//			System.out.println(roster[i].getToString1()+"\n");
   			rosterToString+= roster[i].getToString1()+"\n"; //Prints first line of student information

   			for (int j = 0; j<roster[i].listCourses.size(); j++)//j represents each course of student i
   				rosterToString+=roster[i].listCourses.get(j).getToString() +"\n" ; //Prints course information
   			
   			rosterToString+=roster[i].getToString2() +"\n\n" ; //Prints last line of student information
			}
   		}
		
	}//End of method
	
	//Returns student data 
	private String studentToStringM(Student student)
	{
		
		String studentToString= "";
		
		studentToString+=student.getToString1()+"\n";
		
		for (int i = 0; i<student.listCourses.size(); i++)//
				studentToString+=student.listCourses.get(i).getToString() +"\n" ; //Prints course information
			
		studentToString+=student.getToString2() +"\n\n" ; //Prints last line of student information
		
		return studentToString;
			
	}//End of method
		
}
//
////