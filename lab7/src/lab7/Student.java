package lab7;

 public class Student{
	private String studentCode;
	private String studentName;
	
	public Student(String studentName, String studentCode) {
		this.studentName = studentName;
		this.studentCode = studentCode;
	
	}
	public String getStudentCode () {
		return studentCode;
	}
	public String getStudentName () {
		return studentName;
	}
	public String toString() {
		return studentName + " (" + studentCode +  ")";
		//
	}
} 