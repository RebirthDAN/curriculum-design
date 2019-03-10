package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataLoad {
	
	public static Course read(File file) {
		Course course = new Course(file.getAbsolutePath());
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String text = null;
			String regex = "\\p{Punct}";
			
			while((text = br.readLine()) != null) {
				Student student = new Student(text.split(regex));
				course.add(student);
			}
			br.close();
		}
		catch(IOException e) {
			return null;
		}
		return course;
	}
	
	public static Course load(File file) {
		Course course = new Course(file.getAbsolutePath());
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream oi = new ObjectInputStream(fis);
			course = (Course) oi.readObject();
			oi.close();
		}
		catch(ClassNotFoundException e) {
			return null;
		}
		catch(IOException e) {
			return null;
		}
		return course;
	}
	
}
