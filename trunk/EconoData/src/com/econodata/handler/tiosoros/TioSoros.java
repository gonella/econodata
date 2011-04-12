package com.econodata.handler.tiosoros;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

public class TioSoros {

	private final static Logger logger = Logger.getLogger(TioSoros.class);

	// String TIO_SOROS_ECONODATA_LINK="http://tiosoros.blogspot.com/";
	private final static String TIO_SOROS_ECONODATA_LINK = "http://tiosoros.blogspot.com/2010/12/ecotest.html";
	private final static String TIO_SOROS_CODE_FEED = "http://code.google.com/feeds/p/tiosoros/downloads/basic";

	public static boolean isConnection() {
		URL url;
		try {
			url = new URL(TIO_SOROS_ECONODATA_LINK);
			final URLConnection conn = url.openConnection();

			conn.connect();
			// assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());

			return true;

		} catch (MalformedURLException e) {
			logger.info("Link failed");
		} catch (IOException e) {
			logger.info("NO CONNECTION");
		}

		return false;
	}

	public boolean isNewUpdate(String productName,String productVersion) {

		boolean isConnection = isConnection();
		
		logger.debug("LINK " + TIO_SOROS_ECONODATA_LINK + " - "+ isConnection);
		InputStream is;
		try {
			is = new URL(TIO_SOROS_CODE_FEED).openConnection().getInputStream();
		
		
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = (SyndFeed) input.build(new InputStreamReader(is,Charset.forName("UTF-8")));

		Iterator entries = feed.getEntries().iterator();
		while (entries.hasNext()) {
			SyndEntry entry = (SyndEntry) entries.next();
			
			String feedTitle=entry.getTitle();
			
			logger.info("Checking the Update: " + feedTitle.trim());
			
			Date updatedDate = entry.getUpdatedDate();			
			logger.debug(updatedDate);
			
			boolean checkProductName = checkProductName(productName,feedTitle);
			
			if(checkProductName){
				logger.debug("Found the product");
				boolean checkProductVersionNewer = checkProductVersionNewer(productName,productVersion,feedTitle);
				if(checkProductVersionNewer){
					
					return true;
				}
			}
			
			
			/*if (entry.getContents().size() > 0) {
				SyndContent content = (SyndContent) entry.getContents().get(0);				
				System.out.println("Content value=" + content.getValue());
			}*/
		}
		
		} catch (MalformedURLException e) {
			logger.debug(e);
		} catch (IOException e) {
			logger.debug(e);
		} catch (IllegalArgumentException e) {
			logger.debug(e);
		} catch (FeedException e) {
			logger.debug(e);
		}

		return isConnection;
	}

	public boolean checkProductName(String productName,String feedTitle){
		
		int indexOf1 = feedTitle.indexOf(productName);
		if(indexOf1 >= 0){
			return true;
		}
		return false;
	}
	public boolean checkProductVersionNewer(String productName,String productVersion,String feedTitle){
		
		int indexOf1 = feedTitle.indexOf(productVersion);
		if(indexOf1 >= 0){
			return false;
		}
		
		int lastIndexOf = feedTitle.lastIndexOf("_");
		int indexOf = feedTitle.indexOf("_");
		
		if(indexOf <0 || lastIndexOf <0){
			return false;
		}
		String trim = feedTitle.substring(indexOf+1,lastIndexOf).trim();
		
		try {  
			Double productVersionNumber = Double.valueOf(productVersion); 
			Double feedTitleVersionNumber = Double.valueOf(trim);
			
			logger.debug("checking..CURRENT: "+productVersionNumber);
			logger.debug("checking..NEW: "+feedTitleVersionNumber);
		
		   if(feedTitleVersionNumber > productVersionNumber){
			   logger.info("# There is a new version : "+feedTitleVersionNumber+" - Current:"+productVersionNumber);
			   return true;
		   }
		   
		}catch (Exception e) {  
			logger.debug("No possible to convert the string version");
		} 
		
		return false;
	}
	
	public static void main(String arg[]) {

		String productName="EconoData";
		String productVersion="0.9";
		
		//Open Link here
		boolean newUpdate = new TioSoros().isNewUpdate(productName,productVersion);

	}

}
