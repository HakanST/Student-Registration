//Hakan Turgut
//Student class


import java.util.ArrayList;

//student class

public class Student {
	public String firstName;
	public String lastName;
	public String ID;
	ArrayList <Course> listCourses = new ArrayList <Course>();
	public double totalCredits;
	public double GPA;

	/**
	 * Gives values to course variables.
	 * 
	 * @param fn represents first name parameter value to be stored to firstName string.
	 * @param ln represents last name parameter value to to be stored to last name string.
	 * @param id represents ID parameter value to be stored to ID string.
	 * @param lc represents listed courses parameter value to to be stored to courses listed string array.
	 * @param tc represents total credits parameter value to be stored to total credits integer.
	 * @param gpa represents GPA parameter value to be stored to GPA double variable.
	 */
	
	public Student (String fn, String ln, String id, ArrayList lc, double tc, double gpa){
		
		if (fn==null||ln==null||id==null||lc==null||tc<0||gpa>4)
			throw new IllegalArgumentException("Not good for parameter");
		
		firstName=fn;
		lastName=ln;
		ID=id;
		listCourses=lc;
		totalCredits=tc;
		GPA=gpa;
	}
	
	/**
	 * Gets first name.
	 * @return returns first name.
	 */
	
	public String getFirstName(){
		return firstName;
	}
	
	/**
	 * Gets last name.
	 * @return returns last name.
	 */
	
	public String getLastName(){
		return lastName;
	}
	
	
	/**
	 * Gets ID
	 * @return returns ID value.
	 */
	
	public String getID(){
		return ID;
	}
	
	
	/**
	 * Gets list of courses.
	 * @return returns list of courses.
	 */
	
	public ArrayList getListCourses(){
		return listCourses;
	}
	
	/**
	 * Gets total credits
	 * @return returns total credits
	 */
	
	public double getTotalCredits(){
		return totalCredits;
	}
	
	/**
	 * Gets GPA
	 * @return returns GPA
	 */
	
	public double getGPA(){
		return GPA;
	}
	
	/**
	 * Sets first name equal to parameter value.
	 * @param fn first name of student to be set.
	 */
	
	public void setFirstName(String fn){
		if (fn==null)
			throw new IllegalArgumentException("First name is invalid");

		String capitalizedFirstName = fn.substring(0, 1).toUpperCase() + fn.substring(1).toLowerCase();
		firstName=capitalizedFirstName;
	}
	
	
	/**
	 * Sets last name equal to parameter value.
	 * @param ln last name of student to be set.
	 */
	
	public void setLastName(String ln){
		if (ln==null)
			throw new IllegalArgumentException("Last name is invalid");
		
		String capitalizedLastName = ln.substring(0, 1).toUpperCase() + ln.substring(1).toLowerCase();
		lastName=capitalizedLastName;		
	}
	
	
	/**
	 * Sets ID equal to parameter value.
	 * @param id ID of student to be set.
	 */

	public void setID(String id){
		if (id.length()!=6)
			throw new IllegalArgumentException("ID number has to be 6 digits");
		if (id==null)
			throw new IllegalArgumentException("ID number is not valid");
		ID=id;
		
	}
	
	/**
	 * Sets list of courses equal to parameter value.
	 * @param lc list of courses to be set.
	 */
	
	public void setListCourses(ArrayList lc){
		if (lc==null)
			throw new IllegalArgumentException("ArrayList parameter value not good");
		listCourses=lc;
	}
	
	/**
	 * Sets total number of credits equal to parameter value.
	 * @param tc total number of credits to be set.
	 */
	
	public void setTotalCredits(double tc){
		if (tc<0)
			throw new IllegalArgumentException("Total credits can't be negative");
		totalCredits=tc;
		
	}
	
	/**
	 * Sets GPA equal to parameter value.
	 * @param gpa GPA of student to be set.
	 */
	
	public void setGPA(double gpa){
		if (gpa>4)
			throw new IllegalArgumentException("GPA can't be greater than 4.0");
		if (gpa<0)
			throw new IllegalArgumentException("GPA can't be negative");
		GPA=gpa;	
	}
	
	/**
	 * Compares the first name of each student and returns true if names are the same and false otherwise
	 * @param student is the current student that is being compared
	 */
	
	public boolean compareFirstName(Student student) {
		if (this.firstName.equalsIgnoreCase(student.firstName))
			return true;
		else
			return false;
	}
	
	/**
	 * Compares the last name of each student and returns true if names are the same and false otherwise
	 * @param student is the current student that is being compared
	 */
	
	public boolean compareLastName(Student student) {
		if (this.lastName.equalsIgnoreCase(student.lastName))
			return true;
		else
			return false;
	}
	
	/**
	 * Compares the ID number of each student and returns true if ID numbers are the same, false otherwise
	 * @param student is the current student that is being compared
	 */
	
	public boolean isEqualID(Student student) {
		if (this.ID.equals(student.ID))
			return true;
		else
			return false;
	}
	
	/**
	 * Each student's first line of info will be stored in to one string and returned.
	 */
	
	public String getToString1(){
		String student = lastName + ", " + firstName + ", " + ID;
		return student;
	}
	
	/**
	 * Each student's last line of info will be stored in to one string and returned.
	 */
	
	public String getToString2(){
		String student = totalCredits + ", " + GPA;
		return student;
	}

	
} //end of class
