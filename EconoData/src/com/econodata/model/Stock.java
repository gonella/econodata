package com.econodata.model;

import java.util.HashMap;

public class Stock  implements java.io.Serializable{

	private final String name;
	private HashMap dataMap;

	public Stock(String name,HashMap dataMap){
		this.name = name;
		this.setDataMap(dataMap);
		
	}

	public String getName() {
		return name;
	}

	public void setDataMap(HashMap dataMap) {
		this.dataMap = dataMap;
	}

	public HashMap getDataMap() {
		return dataMap;
	}
}
