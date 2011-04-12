package com.econodata.ui.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.jfree.ui.Align;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

public class ChartView {

	protected Shell shell;
	private final String title;
	private final CategoryDataset dataset;
	private final int x;
	private final int y;
	
	public ChartView(String title,CategoryDataset dataset,int x,int y){
		this.title = title;
		this.dataset = dataset;
		this.x = x;
		this.y = y;
		
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/*ChartView window = new ChartView();

			window.open();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		int witdh = this.x;
		int height = this.y;
		shell.setSize(witdh, height);
		shell.setText(title);
		
		Rectangle bounds = shell.getDisplay().getBounds();
		Rectangle rect = shell.getBounds ();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation (x, y);
		
		// Set the layout to a simple FillLayout
		FillLayout layout = new FillLayout();
		layout.type = SWT.VERTICAL;
		shell.setLayout(layout);

	
		createChart(dataset, "title", "titleX", "titleY", witdh, height);
		//createChart(shell,"P/L dos Ativos","Ativos","P/L");
	}
	public ChartComposite createChart(CategoryDataset dataset,String title,String titleX,String titleY,int x,int y){
		
		JFreeChart chart = createChart(dataset, title,titleX,titleY);
		ChartComposite chartComposite = new ChartComposite(shell, SWT.EMBEDDED,chart, true);
		
		return chartComposite;

	}
	public ChartComposite createChart(Composite parent,String title,String titleX,String titleY){
		
		CategoryDataset dataset = createDatasetDefaultCategoryDataset();
		JFreeChart chart = createChart(dataset, title,titleX,titleY);
		ChartComposite chartComposite = new ChartComposite(parent, SWT.EMBEDDED,chart, true);
		
		return chartComposite;

	}
	
	private JFreeChart createChart(CategoryDataset dataset,String title,String titleX,String titleY) {

		JFreeChart chart = ChartFactory.createBarChart(title, // chart
				// title
				titleX, // domain axis label
				titleY, // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

		TextTitle source = new TextTitle("EconoData v1.0");
		source.setFont(new Font("SansSerif", Font.PLAIN, 20));
		source.setPosition(RectangleEdge.TOP);
		source.setHorizontalAlignment(HorizontalAlignment.LEFT);

		chart.addSubtitle(source);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();

		//plot.setBackgroundPaint(Color.lightGray);
		plot.setBackgroundPaint(Color.white);
		// plot.setDomainGridlinePaint(Color.black);
		// plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.black);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, Color.red);
		renderer.setSeriesPaint(1, Color.orange);
		renderer.setDrawBarOutline(false);
		
		renderer.setItemMargin(0.0);

		return chart;

	}

	private void doWatermark(JFreeChart chart) {

		Image backgroundImage = chart.getPlot().getBackgroundImage();
		
		chart.setBackgroundImage(backgroundImage);
		chart.setBackgroundImageAlignment(Align.CENTER);
		chart.getPlot().setBackgroundAlpha(0.2f);
		
	}

	private CategoryDataset createDatasetDefaultCategoryDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// row keys...
		String series1 = "PETR4";
		String series2 = "VALE5";
		String series3 = "GGBR4";
		String series4 = "HBOR3";
		String series5 = "MMXM3";
		String series6 = "LLXL3";
		String series7 = "HGTX3";

		// column keys...
		String category1 = "Label 1";
		//String category2 = "Label 2";
		//String category3 = "Label 3";

		dataset.addValue(1.0, series1, category1);
		dataset.addValue(4.0, series2, category1);
		dataset.addValue(13.0, series3, category1);
		dataset.addValue(7.0, series4, category1);
		dataset.addValue(15.0, series5, category1);
		dataset.addValue(2.0, series6, category1);
		dataset.addValue(21.0, series7, category1);

		//dataset.addValue(5.0, series2, category1);
		//dataset.addValue(7.0, series2, category2);
		//dataset.addValue(6.0, series2, category3);

		return dataset;
	}
}
