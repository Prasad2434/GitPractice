package restassured;

import io.restassured.path.json.JsonPath;
import restassured.jsonutil.StudentAPI;

public class StudentJSONValidation {

	public static void main(String[] args) {
		
		JsonPath studentJSON = new JsonPath(StudentAPI.getStudentJSON());
		Integer length = studentJSON.getMap("$").size();
		System.out.println("Length : " + length);
		//Print the lastName of the Student
		System.out.println("Last Name : " + studentJSON.getString("lastName"));
		
		//Print PinCode of the student
		System.out.println("PinCode : " + studentJSON.getString("address.pincode"));
		
		//Number of Subjects
		System.out.println("Length of Subject : " + studentJSON.getList("subjects").size());
		System.out.println("Length of Subject : " + studentJSON.getInt("subjects.size()"));
		int lengthOfSubjects = studentJSON.getInt("subjects.size()");
		
		//Print Subject and Department
		for (int i = 0; i < lengthOfSubjects; i++) {
			String subjectName = studentJSON.getString("subjects[" + i + "].subjectName");
			String departmentName = studentJSON.getString("subjects[" + i + "].departmentName");
			System.out.println("Subject : " + subjectName + ", Department : " + departmentName);
		}
				
		//Get Marks in Object Oriented Programming
		for (int i = 0; i <  lengthOfSubjects; i++) {
			
			String subjectName = studentJSON.getString("subjects[" + i + "].subjectName");
			if (subjectName.equalsIgnoreCase("Object Oriented Programming")) {
				System.out.println("Marks in Object Oriented Programming : " + 
							studentJSON.getInt("subjects[" + i + "].marks"));
				break;
			}
		}
		
		//Validate that sum of individual marks is equal to total marks
		Integer calculatedTotalMarks = 0;
		for (int i = 0; i < lengthOfSubjects; i++) {
			Integer partialResult = studentJSON.getInt("subjects[" + i + "].marks");
			calculatedTotalMarks += partialResult;
		}
		Integer totalMarksFromJSON = studentJSON.getInt("totalMarks");
		System.out.println("Are Marks Equal : " + calculatedTotalMarks
				.equals(totalMarksFromJSON));
	}
}
