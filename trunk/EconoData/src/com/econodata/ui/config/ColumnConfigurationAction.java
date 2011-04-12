package com.econodata.ui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.econodata.handler.IOData;
import com.econodata.main.EconoMain;
import com.econodata.model.FundamentusKey;
import com.econodata.ui.common.CommomAction;

public class ColumnConfigurationAction extends CommomAction{
	
	private Logger logger = Logger.getLogger(ColumnConfigurationAction.class);
	private final ColumnConfiguration UI;
	
	public ColumnConfiguration getUI() {
		return UI;
	}

	public ColumnConfigurationAction(ColumnConfiguration UI) {
		super();
		this.UI = UI;
		refresh();
	}
	
	public void refresh() {
		logger.info("refresh: getListSelectColumn :"+EconoMain.database.getListSelectColumn().size());

		getUI().getListAvailableColumn().removeAll();
		getUI().getListSelectColumn().removeAll();

		FundamentusKey[] values = FundamentusKey.values();
		for (int i = 0; i < values.length; i++) {
			getUI().getListAvailableColumn().add(values[i].convert());			
		}
		
		List<FundamentusKey> valuesSelect = EconoMain.database.getListSelectColumn();
		
		for (Iterator iterator = valuesSelect.iterator(); iterator.hasNext();) {
			FundamentusKey fundamentusKey = (FundamentusKey) iterator.next();
			getUI().getListSelectColumn().add(fundamentusKey.convert());			

		}
		
	}

	public boolean removeColumn(int index) {
		logger.info(index);
		logger.info("removeColumn: getListSelectColumn :"+EconoMain.database.getListSelectColumn().size());

		if(index>=0){
			
			EconoMain.database.getListSelectColumn().remove(index);

			refresh();
			return true;
		}
		

		
		return false;
	}

	public boolean addColumn(int index) {

		logger.info(index);
		if(index>=0 && EconoMain.database.getListSelectColumn().size() < FundamentusKey.values().length ){
			
			EconoMain.database.getListSelectColumn().add(index, FundamentusKey.values()[index]);
			refresh();
			return true;
		}
		
		
		return false;
	}

	public void saveInDisk() {
		IOData.writeData(EconoMain.database, EconoMain.DATA_STOCKCONTENT_DAT);
		
	}

	public void reset() {
		
		EconoMain.database.setListSelectColumn(new ArrayList(Arrays.asList(FundamentusKey.values())));
		refresh();
	}
	
	
}
