package com.econodata.ui.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.econodata.handler.IOData;
import com.econodata.handler.fundamentus.ExtractWebData;
import com.econodata.main.EconoMain;
import com.econodata.model.FundamentusKey;
import com.econodata.model.Stock;
import com.econodata.ui.common.CommomAction;

public class StockConfigurationAction extends CommomAction{
	
	private Logger logger = Logger.getLogger(StockConfigurationAction.class);
	private final StockConfiguration UI;
	
	public StockConfiguration getUI() {
		return UI;
	}

	public StockConfigurationAction(StockConfiguration UI) {
		super();
		this.UI = UI;
		refresh();
	}
	
	public void refresh() {
		
		HashMap<String, Stock> stockMapList = EconoMain.database.getDataMap();
		
		
		getUI().getStockList().removeAll();
		
		Set<String> keySet = stockMapList.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String code = (String) iterator.next();
		
			HashMap hashMap = stockMapList.get(code).getDataMap();
			
			String name = (String)hashMap.get(FundamentusKey.Nome.toString());
			
			getUI().getStockList().add(name);			
		}
		
	}

	public boolean removeStock(String code) {
		
		
		boolean exist = EconoMain.database.getDataMap().containsKey(code);
		
		if(exist){
			EconoMain.database.getDataMap().remove(code);
			//getUI().getStockList().remove(code);
			logger.info("removeStock:"+true);

			refresh();
			return true;			
		}
		
		logger.info("removeStock:"+false);

		
		return false;
	}

	public boolean addStock(String code) {

		if(!(code.isEmpty())){
			boolean exist = EconoMain.database.getDataMap().containsKey(code);
			
			if(!exist){
				EconoMain.database.getDataMap().put(code,
						new Stock(code,new ExtractWebData().getData(code))
				);
				refresh();
				return true;
			}
		}
		
		return false;
	}

	public void saveInDisk() {
		IOData.writeData(EconoMain.database, EconoMain.DATA_STOCKCONTENT_DAT);
		
	}
	
}
