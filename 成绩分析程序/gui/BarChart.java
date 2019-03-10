package gui;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import data.Course;

public class BarChart {
	
	ChartPanel chartPanel;
	
	public BarChart(Course course) {
		CategoryDataset dataset = getDataSet(course);
        JFreeChart chart = ChartFactory.createBarChart3D("各分段成绩分析柱形图","分段","人数",dataset,PlotOrientation.VERTICAL,true,false,false);
        CategoryPlot plot=chart.getCategoryPlot();
        CategoryAxis domainAxis=plot.getDomainAxis();
        domainAxis.setLabelFont(new Font(null,1,14)); 
        domainAxis.setTickLabelFont(new Font(null,1,14));
        ValueAxis rangeAxis=plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font(null,0,15));
        chart.getLegend().setItemFont(new Font(null,1,15));
        chart.getTitle().setFont(new Font("宋体",1,20));
        chartPanel = new ChartPanel(chart,true);
	}
	
    private static CategoryDataset getDataSet(Course course) {    
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	dataset.addValue(course.getNum(8), "不及格(0-59)", "不及格(0-59)");
    	dataset.addValue(course.getNum(7),"不及格(0-59)","及格(60-69)");
    	dataset.addValue(course.getNum(6),"中等(70-79","中等(70-79)");
    	dataset.addValue(course.getNum(5),"良好(80-89)","良好(80-89)");
    	dataset.addValue(course.getNum(4),"优秀(90-100)","优秀(90-100)");
    	return dataset;
    }
    
    public ChartPanel getChartPanel() {
    	return chartPanel;
    }
    
}
