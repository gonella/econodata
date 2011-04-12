package com.econodata.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.econodata.model.Database;
import com.econodata.model.Stock;

public class IOData {
	
	private final static Logger logger = Logger
	.getLogger(IOData.class);
	
	public static boolean writeData(Database data,String fileName){
		
		logger.info("Writing Data...");
		 File file = new File(fileName);  
		 FileOutputStream f;
		try {
			f = new FileOutputStream(file);			
			ObjectOutputStream s = new ObjectOutputStream(f);          
			s.writeObject(data);
			s.close();
			logger.info("Writing Data successful");	
			return true;
			
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}  
		logger.info("Writing Data ERROR");	

		return false;
	}
	
	public static Database readData(String fileName){
		
		Database data=null;
		
		File file = new File(fileName);  
		FileInputStream f;
		try {
			f = new FileInputStream(file);
			ObjectInputStream s = new ObjectInputStream(f);  
			data = (Database)s.readObject();         
			s.close();
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (ClassNotFoundException e) {
			logger.error(e);
		}  
		
		return data;
	}
	
	public static boolean isExist(String fileName){
		
		File file = new File(fileName);  
		return file.exists();
		
	}
	
}
