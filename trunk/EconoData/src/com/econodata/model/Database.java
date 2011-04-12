package com.econodata.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Database implements java.io.Serializable{

	private HashMap<String,Stock> dataMap=null;
	
	private ArrayList<FundamentusKey> listSelectColumn=null;

	public void setDataMap(HashMap<String,Stock> dataMap) {
		this.dataMap = dataMap;
	}

	public HashMap<String,Stock> getDataMap() {
		return dataMap;
	}

	public void setListSelectColumn(ArrayList<FundamentusKey> listSelectColumn) {
		this.listSelectColumn = listSelectColumn;
	}

	public ArrayList<FundamentusKey> getListSelectColumn() {
		return listSelectColumn;
	} 

}
