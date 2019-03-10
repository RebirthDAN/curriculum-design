package data;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{
	
	Course course;
	
	public TableModel(Course course) {
		this.course = course;
	}
	@Override
	public int getColumnCount() {
		return 3;
	}
	@Override
	public int getRowCount() {
		return (course == null?0:course.getNum(0));
	}
	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "学号";
		case 1:
			return "姓名";
		case 2:
			return "成绩";
		default:
			throw new IllegalArgumentException("Invalid column: " + columnIndex);
		}
	}
	@Override
	public Object getValueAt(int rowIndex,int columnIndex) {
		switch (columnIndex) {
		case 0:
			return (course == null||course.getNum(0) == 0?Object.class:course.students.get(rowIndex).getNum());
		case 1:
			return (course == null||course.getNum(0) == 0?Object.class:course.students.get(rowIndex).getName());
		case 2:
			return (course == null||course.getNum(0) == 0?Object.class:course.students.get(rowIndex).getGrade());
		default:
			throw new IllegalArgumentException("Invalid column: " + columnIndex);
		}
	}
	@Override
    public Class<?> getColumnClass(int columnIndex) {
        if ((columnIndex >= 0) && (columnIndex < getColumnCount())) {  
        	return getValueAt(0,columnIndex).getClass(); 
        } 
        else {  
        	throw new IllegalArgumentException("Invalid column: " + columnIndex);
        }  
	}
	
}
