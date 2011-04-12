package com.econodata.ui.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.econodata.handler.IOData;
import com.econodata.handler.fundamentus.ExtractWebData;
import com.econodata.main.EconoMain;
import com.econodata.model.FundamentusKey;
import com.econodata.model.SectorKey;
import com.econodata.model.Stock;
import com.econodata.ui.common.CommomAction;

public class StockConfigurationBySectorAction extends CommomAction{
	
	private Logger logger = Logger.getLogger(StockConfigurationBySectorAction.class);
	private final StockConfigurationBySector UI;
	
	public StockConfigurationBySector getUI() {
		return UI;
	}

	public StockConfigurationBySectorAction(StockConfigurationBySector UI) {
		super();
		this.UI = UI;
		refresh();
	}
	
	public void refresh() {
		
		getUI().getStockListAdded().removeAll();
		
		getUI().getComboSector().removeAll();
		
		SectorKey[] sectorKeyValues = SectorKey.values();
		for (int j = 0; j < sectorKeyValues.length; j++) {
			SectorKey sectorKey = sectorKeyValues[j];
			getUI().getComboSector().add(sectorKey.convert());
			getUI().getComboSector().setData(sectorKey.convert(), sectorKey);
		}
		
		HashMap<String, Stock> stockMapList = EconoMain.database.getDataMap();		
		Set<String> keySet = stockMapList.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String code = (String) iterator.next();
		
			HashMap hashMap = stockMapList.get(code).getDataMap();			
			String name = (String)hashMap.get(FundamentusKey.Nome.toString());			
			getUI().getStockListAdded().add(name);			
		}
	}
	

	public boolean addSector(int index) {

		if(index>=0 && index < SectorKey.values().length ){

			getUI().getStockListBySector().removeAll();
			getUI().getComboSector().select(index);
			
			SectorKey sectorKey = SectorKey.values()[index];
			
			HashMap<String, List<Stock>> allStockBySector = new ExtractWebData().getAllStockBySector(new SectorKey[]{sectorKey});

			Collection<List<Stock>> values = allStockBySector.values();
			for (Iterator iterator = values.iterator(); iterator.hasNext();) {
				List<Stock> list = (List<Stock>) iterator.next();
				
				for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
					Stock stock = (Stock) iterator2.next();
					getUI().getStockListBySector().add(stock.getName());
					getUI().getStockListBySector().setData(stock.getName(),stock);
				}
			}
			
			//refresh();
			return true;
		}
		
		
		return false;
	}

	public void saveInDisk() {
		IOData.writeData(EconoMain.database, EconoMain.DATA_STOCKCONTENT_DAT);
		
	}

	public boolean combineStock(int[] index) {

		for (int i = 0; i < index.length; i++) {
			int j = index[i];
			String name = getUI().getStockListBySector().getItem(j);
			Stock data = (Stock)getUI().getStockListBySector().getData(name);
			
			boolean exist = EconoMain.database.getDataMap().containsKey(name);
			
			if(!exist){
				EconoMain.database.getDataMap().put(name,data);
				refresh();
				return true;
			}

		}
		
		
		return false;
	}
	
}
