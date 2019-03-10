package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;

import data.Course;
import data.DataLoad;
import data.DataSave;
import data.TableModel;

public class SetPanel extends JFrame{
	
	JTable table;
	JPanel jPanel;
	Course course;
	JFrame chartFrame;
	JPanel statePanel;
	JPanel tablePanel;
	JPanel chartPanel;
	JLabel stateLabel;
	JLabel tableLabel;
	BarChart barChart;
	PieChart pieChart;
	JPanel searchPanel;
	JLabel searchLabel;
	JScrollPane scroll;
	String searchString;
	JPanel analysisPanel;
	JLabel analysisTitle;
	JTextField searchText;
	TableModel tableModel;
	JButton barChartButton;
	JButton pieChartButton;
	TableRowSorter<TableModel> sorter;
	JLabel[] analysisLabels = new JLabel[31];
	
	public SetPanel(){
		course = null;
		jPanel = new JPanel();
		setTitle("成绩分析程序");		
		setResizable(false);
		setBackground(Color.white);
		setBounds(550,240,820,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		jPanel.setLayout(null);
		jPanel.setBounds(0,0,820,600);
		menu();
		searchPanel();
		statePanel();
		tablePanel();
		analysisPanel();
		add(jPanel);
		setVisible(true);
	}
	
	void analysisRefresh() {
		if(course != null && course.getNum(0) != 0) {
			for(int i = 18;i < 26;i++) {
				analysisLabels[i].setText(String.valueOf(i != 20?course.getNum(i-17):course.getNum(3)/course.getNum(0)));
			}
			for(int i = 26;i < 31;i++) {
				analysisLabels[i].setText(String.format("%.2f",course.getNum(i-22)*1.0/course.getNum(0)*100));
			}
		}
		else {
			for(int i =18;i < 31;i++) {
				analysisLabels[i].setText("");
			}
		}	
	}
	void courseRefresh() {
		stateLabel.setText(course != null?course.getName()+"共"+course.getNum(0)+"人":"");
		analysisRefresh();
		tableModel = new TableModel(course);
		sorter = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(sorter);
		table.setModel(tableModel);
	}
	
	void menu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu       = new JMenu("文件");
		JMenuItem item1  = new JMenuItem("打开文本文件成绩单");
		JMenuItem item2  = new JMenuItem("另存为文本文件成绩单");
		JMenuItem item3  = new JMenuItem("打开对象文件成绩单");
		JMenuItem item4  = new JMenuItem("另存为对象文件成绩单");
		JMenuItem item5  = new JMenuItem("清除数据");
		item1.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				JFileChooser jfc = new JFileChooser(".");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("文本文件(*.txt)","txt");
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("打开文本文件成绩单");
				jfc.setAcceptAllFileFilterUsed(false);
				int response = jfc.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					course = DataLoad.read(file);
					if(course == null) {
						JOptionPane.showMessageDialog(null,"读取失败","错误", JOptionPane.ERROR_MESSAGE);
					}
					else courseRefresh();
				}
			}
		});
		item2.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				JFileChooser jfc = new JFileChooser(".");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("文本文件(*.txt)","txt");
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("另存为文本文件成绩单");
				jfc.setAcceptAllFileFilterUsed(false);
				int response = jfc.showSaveDialog(null);
				if(response == JFileChooser.APPROVE_OPTION){
					if(course == null) {
						JOptionPane.showMessageDialog(null,"当前成绩单不存在","错误", JOptionPane.ERROR_MESSAGE);
					}
					else {
						File file = jfc.getSelectedFile();
						String name = jfc.getName(file);
						if(!name.endsWith(".txt")) {
							file = new File(jfc.getCurrentDirectory(),name+".txt");
						}
						DataSave.write(course,file);
					}
				}
			}
		});
		item3.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				JFileChooser jfc = new JFileChooser(".");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("对象文件(*.score)","score");
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("打开对象文件成绩单");
				jfc.setAcceptAllFileFilterUsed(false);
				int response = jfc.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					course = DataLoad.load(file);
					if(course == null) {
						JOptionPane.showMessageDialog(null,"读取失败","错误", JOptionPane.ERROR_MESSAGE);
					}
					else courseRefresh();
				}
			}
		});
		item4.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				JFileChooser jfc = new JFileChooser(".");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("对象文件(*.score)","score");
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("另存为文本文件成绩单");
				jfc.setAcceptAllFileFilterUsed(false);
				int response = jfc.showSaveDialog(null);
				if(response == JFileChooser.APPROVE_OPTION){
					if(course == null) {
						JOptionPane.showMessageDialog(null,"当前成绩单不存在","错误", JOptionPane.ERROR_MESSAGE);
					}
					else {
						File file = jfc.getSelectedFile();
						String name = jfc.getName(file);
						if(!name.endsWith(".score")) {
							file = new File(jfc.getCurrentDirectory(),name+".score");
						}
						DataSave.save(course,file);
					}
				}
			}
		});
		item5.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				course = null;
				courseRefresh();
			}
		});
		menu.add(item1);
		menu.add(item2);
		menu.addSeparator();
		menu.add(item3);
		menu.add(item4);
		menu.addSeparator();
		menu.add(item5);
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}
	
	void searchPanel() {
		searchPanel  = new JPanel();
		searchText   = new JTextField();
		searchLabel  = new JLabel("输入学号或姓名或成绩 可以自动查询，点击表格列头可以排序");
		searchPanel.setLayout(null);
		searchText.setBounds(3,5,200,25);
		searchPanel.setBounds(0,0,815,35);
		searchLabel.setBounds(210,5,550,25);
		searchLabel.setForeground(Color.RED);	
		searchLabel.setFont(new Font("null",0,12));
		searchPanel.setBorder(BorderFactory.createEtchedBorder());
		searchText.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				searchString = searchText.getText();
				sorter.setRowFilter(RowFilter.regexFilter(searchString));
			}
			public void insertUpdate(DocumentEvent e) {
				searchString = searchText.getText();
				sorter.setRowFilter(RowFilter.regexFilter(searchString));
			}
			public void removeUpdate(DocumentEvent e) {
				searchString = searchText.getText();
				sorter.setRowFilter(RowFilter.regexFilter(searchString));
			}
		});
		searchPanel.add(searchText);
		searchPanel.add(searchLabel);
		jPanel.add(searchPanel);
	}
	
	void statePanel() {
		statePanel = new JPanel();
		stateLabel = new JLabel();
		statePanel.setLayout(null);
		stateLabel.setBounds(5,2,550,22);
		statePanel.setBounds(0,520,815,26);
		statePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		statePanel.add(stateLabel);
		jPanel.add(statePanel);
	}
	
	void tablePanel() {
		tablePanel = new JPanel();
		tableLabel = new JLabel("成绩单");
		tableModel = new TableModel(course);
		table      = new JTable(tableModel);
        scroll     = new JScrollPane(table);
        sorter     = new TableRowSorter<TableModel>(tableModel);
		tablePanel.setLayout(null);
		table.setBounds(50,50,430,440);
		scroll.setBounds(10,30,430,440);
		tableLabel.setBounds(5,5,50,20);
		tablePanel.setBounds(1,35,450,485);
		tableLabel.setFont(new Font(null,1,15));
		table.getColumnModel().getColumn(0).setPreferredWidth(185);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(110);
        scroll.setBorder(BorderFactory.createLoweredBevelBorder());
		tablePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		tablePanel.add(tableLabel);
		tablePanel.add(scroll);
		jPanel.add(tablePanel);
	}
	
	void analysisPanel() {
		analysisPanel      = new JPanel();
		analysisTitle      = new JLabel("数据分析");
		analysisLabels[0]  = new JLabel("最高分");
		analysisLabels[1]  = new JLabel("最低分");
		analysisLabels[2]  = new JLabel("平均分");
		analysisLabels[3]  = new JLabel("优秀(90-100)");
		analysisLabels[4]  = new JLabel("良好(80-89)");
		analysisLabels[5]  = new JLabel("中等(70-79)");
		analysisLabels[6]  = new JLabel("及格(60-69)");
		analysisLabels[7]  = new JLabel("不及格(0-59)");
		int x = 30,y = 50,width = 80,hight = 20,hInterval = hight+28,xInterval = 270;
		analysisPanel.setLayout(null);
		analysisPanel.add(analysisTitle);
		analysisPanel.setBounds(450,35,365,485);
		analysisTitle.setBounds(5,5,width,hight);
		analysisTitle.setFont(new Font(null,1,15));
		analysisPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		for(int i = 0;i < 8;i++) {
			analysisLabels[i].setBounds(x,y+hInterval*i,width,hight);
			analysisLabels[i].setFont(new Font(null,0,13));
			analysisPanel.add(analysisLabels[i]);
		}
		for(int i = 8;i < 13;i++) {
			analysisLabels[i]  = new JLabel("人,占");
			analysisLabels[i].setBounds(x+2*xInterval/3,y+hInterval*(i-5),width,hight);
			analysisLabels[i].setFont(new Font(null,0,13));
			analysisPanel.add(analysisLabels[i]);
		}
		for(int i = 13;i < 18;i++) {
			analysisLabels[i] = new JLabel("%");
			analysisLabels[i].setBounds(x+xInterval,y+hInterval*(i-10),width,hight);
			analysisLabels[i].setFont(new Font(null,0,13));
			analysisPanel.add(analysisLabels[i]);
		}
		for(int i = 18;i < 26;i++) {
			analysisLabels[i] = new JLabel("",Label.LEFT);
			analysisLabels[i].setBounds(xInterval/2,y+hInterval*(i-18),width,hight);
			analysisLabels[i].setFont(new Font(null,0,13));
			analysisPanel.add(analysisLabels[i]);
		}
		for(int i = 26;i < 31;i++) {
			analysisLabels[i] = new JLabel("",Label.LEFT);
			analysisLabels[i].setBounds(7*xInterval/8,y+hInterval*(i-23),width,hight);
			analysisLabels[i].setFont(new Font(null,0,13));
			analysisPanel.add(analysisLabels[i]);
		}
		chartPanel();
		jPanel.add(analysisPanel);
	}
	
	void chartPanel() {
		chartPanel = new JPanel();
		barChartButton = new JButton("显示柱形分析图");
		pieChartButton = new JButton("显示饼形分析图");
		barChartButton.setBounds(38,21,125,21);
		pieChartButton.setBounds(202,21,125,21);
		chartPanel.add(barChartButton);
		chartPanel.add(pieChartButton);
		chartPanel.setLayout(null);
		chartPanel.setBounds(0,420,365,65);
		chartPanel.setBorder(BorderFactory.createEtchedBorder());
		barChartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                barChartFrame();
            }
        });
		pieChartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pieChartFrame();
            }
        });
		analysisPanel.add(chartPanel);
	}
	void barChartFrame() {
		if(course != null && course.getNum(0) != 0) {
			barChart = new BarChart(course);
			chartFrame = new JFrame("柱形图分析");
			chartFrame.setLayout(null);
			chartFrame.setBackground(Color.white);
			chartFrame.setBounds(600,340,720,400);
			barChart.getChartPanel().setBounds(0,0,715,370);
			chartFrame.add(barChart.getChartPanel());
			chartFrame.setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			chartFrame.setVisible(true);
		}
	}
	void pieChartFrame() {
		if(course != null && course.getNum(0) != 0) {
			pieChart = new PieChart(course);
			chartFrame = new JFrame("饼形图分析");
			chartFrame.setLayout(null);
			chartFrame.setBackground(Color.white);
			chartFrame.setBounds(600,340,720,400);
			pieChart.getChartPanel().setBounds(0,0,715,370);
			chartFrame.add(pieChart.getChartPanel());
			chartFrame.setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			chartFrame.setVisible(true);
		}
	}
	
}
