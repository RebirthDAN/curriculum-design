package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{
	
	private String   name;
	private int[] num = new int[9];
	ArrayList<Student> students = new ArrayList<>();
	
	Course(String name){
		for(int i = 0;i < 9;i++) {
		   num[i] = 0;
		}
		num[2]    = 100;
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getNum(int index) {
		return num[index];
	}
	public void add(Student student) {
		students.add(student);
		int grade = student.getGrade();
		num[0] ++;
		num[3] += grade;
		if(num[1] < grade) num[1] = grade;
		if(num[2] > grade) num[2] = grade;
		if(grade >= 90)      num[4]++;
		else if(grade >= 80) num[5]++;
		else if(grade >= 70) num[6]++;
		else if(grade >= 60) num[7]++;
		else                 num[8]++;
	}
	
}
