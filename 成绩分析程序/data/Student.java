package data;

import java.io.Serializable;

public class Student implements Serializable{
	
	private String  num;
	private String name;
	private int   grade;
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	Student(String[] word){
		num   = word[0];
		name  = word[1];
		grade = Integer.parseInt(word[2]);
	}
	
}
