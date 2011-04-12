package com.econodata.ui.config;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.econodata.main.EconoMain;
import com.econodata.model.FundamentusKey;
import com.econodata.model.Stock;
import com.econodata.ui.chart.ChartView;
import com.econodata.ui.common.CommomAction;
import com.econodata.ui.common.MessageDialog;

public class ChartConfigDialogAction extends CommomAction{
	
	private Logger logger = Logger.getLogger(ChartConfigDialogAction.class);
	private final ChartConfigDialog UI;
	
	public ChartConfigDialog getUI() {
		return UI;
	}

	public ChartConfigDialogAction(ChartConfigDialog UI) {
		super();
		this.UI = UI;
		
		refresh();
	}
	
	@Override
	public void refresh() {
		getUI().getListColumn().removeAll();
		
		logger.info("Ativos selecionados: "+EconoMain.stockSelected.size());
		//Getting ONE stock to test the column, if there are NUMBERS
		Stock stock=null;		
		for (Iterator iterator = EconoMain.stockSelected.iterator(); iterator.hasNext();) {
			String stockCode = (String) iterator.next();
			stock = EconoMain.database.getDataMap().get(stockCode);
			break;
		}	
		logger.info(stock.getName());

		for (Iterator iterator = EconoMain.database.getListSelectColumn().iterator(); iterator
				.hasNext();) {
			FundamentusKey fundamentusKey = (FundamentusKey) iterator.next();
			
			String elem = (String)stock.getDataMap().get(fundamentusKey.toString());
			logger.info(elem);
			try{
				
				//removendo elementos
				elem=elem.replace(".", "").replace(",","").replace("%","");
				
				Integer value=Integer.parseInt(elem);
				getUI().getListColumn().add(fundamentusKey.convert());
				getUI().getListColumn().setData(fundamentusKey.convert(),fundamentusKey);
			}catch(NumberFormatException  e){
				logger.info("NOT NUMBER");			
			}
		}
		
		
	
	}

	public void doChart() {
		
		int[] selectionIndices = getUI().getListColumn().getSelectionIndices();
		
		if(selectionIndices==null && (selectionIndices!=null && selectionIndices.length<=0)){
			new MessageDialog(getUI().getShell()).open();
			return;
		}
		
		String strFund="[";
		FundamentusKey fundamentusKeys[]=new FundamentusKey[selectionIndices.length];
		for (int i = 0; i < selectionIndices.length; i++) {
			int index = selectionIndices[i];
			
			fundamentusKeys[i] = (FundamentusKey)getUI().getListColumn().getData(getUI().getListColumn().getItem(index));
			strFund=strFund+fundamentusKeys[i].convert()+",";
		}
		
		CategoryDataset dataset = getDataset(EconoMain.stockSelected,fundamentusKeys);
		//CategoryDataset dataset = createDatasetDefaultCategoryDataset();
		
		new ChartView("titleMain",dataset,600,600).open();
		
		
	}

	private CategoryDataset getDataset(ArrayList<String> stockSelected,FundamentusKey[] fundamentusKeys) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < fundamentusKeys.length; i++) {
			FundamentusKey fundamentusKey = fundamentusKeys[i];
			
			Stock stock;
			for (Iterator iterator = stockSelected.iterator(); iterator.hasNext();) {
				String stockCode = (String) iterator.next();
				stock = EconoMain.database.getDataMap().get(stockCode);
				
				String strValue=extractedToNumber((String)stock.getDataMap().get(fundamentusKey.toString()));
				 
				dataset.addValue(new Double(strValue), stock.getName(), fundamentusKey.convert());
			}	
			
			
		}
		
		return dataset;
	}
	private String extractedToNumber(String elem) {
		return elem.replace(".", "").replace(",","").replace("%","");
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
