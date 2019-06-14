//Hakan Turgut
//Course class

public class Course {
	
	public String courseNumber;
	public double courseCredits;
	public	String courseGrade;
	
	/**
	 * Gives values to course variables.
	 * 
	 * @param cn represents course number parameter value to be stored to courseNumber string.
	 * @param cc represents course credits parameter value to to be stored to courseCredits double.
	 * @param cg represents course grade parameter value to be stored to courseGrade string.
	 */
	
	public Course(String cn, double cc, String cg){
		if (cn==null||cc<0||cg==null)
			throw new IllegalArgumentException("Not good for parameter");
		courseNumber=cn;
		courseCredits=cc;
		courseGrade=cg;
	}
	
	/**
	 * Gets course number.
	 * @return returns course number.
	 */
	
	public String getCourseNumber(){
		return courseNumber;
	}
	
	/**
	 * Gets course credits.
	 * @return returns course credits.
	 */
	
	public double getCourseCredits(){
		return courseCredits;
	}
	
	/**
	 * Gets grade.
	 * @return returns grade.
	 */
	
	public String getCourseGrade(){
		return courseGrade;
	}
	
	/**
	 * Sets course number equal to parameter value.
	 * @param cn course number  of course to be set.
	 */
	
	public void setCourseNumber(String cn){
		if (cn==null)
			throw new IllegalArgumentException("Course number invalid");
		courseNumber=cn;
	}
	
	/**
	 * Sets number of course credits equal to parameter value.
	 * @param cc course credits of course to be set.
	 */
	
	public void setCourseCredits(double cc){
		if (cc<0)
			throw new IllegalArgumentException("Course credit can't be negative");
		courseCredits=cc;
		
	}
	
	/**
	 * Sets grade of course equal to parameter value.
	 * @param g grade of course to be set.
	 */
	
	public void setGrade(String cg){
		if (cg==null)
			throw new IllegalArgumentException("Course grade invalid");
		courseGrade=cg;
	}
	
	/**
	 * Compares the course number of each course of each student. If courses contain the same number, true is returned and false otherwise
	 * @param course is the current course that is being compared
	 */
	
	public boolean isEqualCourseNumber(Course course) {
		if (this.courseNumber.equals(course.courseNumber))
			return true;
		else
			return false;
	}
	
	/**
	 * Each course's info will be stored in to one string and returned.
	 */
	
	public String getToString(){
		String course = courseNumber + ", " + courseCredits + ", " + courseGrade;
		return course;
	}

} //end of class

