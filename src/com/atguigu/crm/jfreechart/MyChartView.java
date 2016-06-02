package com.atguigu.crm.jfreechart;

import java.awt.Font;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class MyChartView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = (String)(model.get("title"));
		model.remove("title");
		PieDataset dataset = createDataset(model); 
		JFreeChart chart = createChart(title,dataset);
		chart.getTitle().setFont(new Font("隶书", Font.BOLD, 50));
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 20));
		PiePlot plot = (PiePlot) chart.getPlot();

		//设置标签字体、风格、字号
		plot.setLabelFont(new Font("微软雅黑", Font.ITALIC, 15));
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}/{3},{2}"));
		ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 500, 270);
	}
	
	private static PieDataset createDataset(Map<String, Object> map) {
		
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		
		for(Map.Entry<String, Object> entry: map.entrySet()){
			localDefaultPieDataset.setValue(entry.getKey(), (int)entry.getValue());
		}
		
		return localDefaultPieDataset;
	}

	private static JFreeChart createChart(String title ,PieDataset paramPieDataset) {
		JFreeChart localJFreeChart = ChartFactory.createPieChart3D(
				title, paramPieDataset, true, true, false);
		PiePlot3D localPiePlot3D = (PiePlot3D) localJFreeChart.getPlot();
		localPiePlot3D.setDarkerSides(true);
		localPiePlot3D.setStartAngle(290.0D);
		localPiePlot3D.setDirection(Rotation.CLOCKWISE);
		localPiePlot3D.setForegroundAlpha(0.5F);
		localPiePlot3D.setNoDataMessage("No data to display");
		return localJFreeChart;
	}

}
