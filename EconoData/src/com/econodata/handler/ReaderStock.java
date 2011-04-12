package com.econodata.handler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.econodata.model.Stock;

public class ReaderStock {
	
	public static  List<Stock> reader(String fileName) {
		
		List<Stock> list=new ArrayList<Stock>();
		
		CsvReader reader;
		try {
			reader = new CsvReader(fileName);
		
		reader.setDelimiter(':');
		
		String[] values=null;
		
		while (reader.readRecord())
		{	
			values = reader.getValues();
			
			if(values!=null){
				
				list.add(new Stock(values[0],null));
			}
		
		}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
			
}
