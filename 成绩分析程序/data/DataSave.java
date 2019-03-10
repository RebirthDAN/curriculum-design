package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataSave {
	
	public static void write(Course course,File file) {
		course.setName(course.getName().replace(".score",".txt"));
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter br = new BufferedWriter(fw);
			for(Student student : course.students) {
				br.write(student.getNum()+","+student.getName()+","+student.getGrade());
				br.newLine();
			}
			br.close();
			fw.close();
		} catch (IOException e) {
			System.out.println(e);
		}	
	}
	
	public static void save(Course course,File file) {
		course.setName(course.getName().replace(".txt",".score"));
		try{
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(course);
			oo.close();
			fo.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
	
}
