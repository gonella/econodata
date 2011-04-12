package com.econodata.handler.fundamentus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.vietspider.html.HTMLDocument;
import org.vietspider.html.HTMLNode;
import org.vietspider.html.Name;
import org.vietspider.html.path2.HTMLExtractor;
import org.vietspider.html.path2.NodePath;
import org.vietspider.html.path2.NodePathParser;
import org.vietspider.html.util.HTMLParserDetector;
import org.vietspider.ui.htmlexplorer.FastWebClient;

import com.econodata.model.FundamentusKey;
import com.econodata.model.SectorKey;
import com.econodata.model.Stock;

public class ExtractWebData {
	
	private Logger logger = Logger.getLogger(ExtractWebData.class);

	private static final String HTTP_WWW_FUNDAMENTUS_COM_BR_DETALHES_PHP_PAPEL = "http://www.fundamentus.com.br/detalhes.php?papel=";

	private FastWebClient fastWebClient;
	private HTMLParserDetector detector;
	private HTMLExtractor extractor;
	private NodePathParser pathParser;
	
	public ExtractWebData(){
		boolean decode = false;
		boolean cache = false;

		fastWebClient = new FastWebClient();
		detector = new HTMLParserDetector();
		extractor = new HTMLExtractor();
		pathParser = new NodePathParser();
		detector.setDecode(decode);
        detector.setCharset(null);
	}
	
	
	public HashMap<String,Stock> getDataFromStockList(List<Stock> listStock){

		HashMap<String,Stock> data=new HashMap<String,Stock>();
		HashMap map;
		for (Iterator iterator = listStock.iterator(); iterator.hasNext();) {
			Stock stock = (Stock) iterator.next();		
						
			map = getData(stock.getName());
			
			data.put(stock.getName(),new Stock(stock.getName(),map));
		}
		return data;
	}
	public HashMap getData(String code){
	
		HashMap bodyMap=new HashMap();

		String link = HTTP_WWW_FUNDAMENTUS_COM_BR_DETALHES_PHP_PAPEL+code;
        	
		FundamentusKey[] values = FundamentusKey.values();
		String tag[]=new String[values.length];
		for (int i = 0; i < values.length; i++) {
			tag[i] = values[i].getCode();
		}
		
		return getContent(link,tag);
	}
	
	public HashMap getContent(String link,String tag[]){
		
		HashMap bodyMap=new HashMap();

		try {
        	
        	logger.info("Getting web data from: "+link);
        	
			HTMLDocument document = fastWebClient.createDocument(null, link, false, detector);
        	
			for (int i = 0; i < tag.length; i++) {
				String keyTag = tag[i];

				NodePath nodePath  = pathParser.toPath(keyTag);
			      HTMLNode node = extractor.lookNode(document.getRoot(), nodePath);
			      //logger.info("NODE: "+node);
			      if(node!=null && (node.isNode(Name.A) || node.isNode(Name.SPAN) || node.isNode(Name.CONTENT) || node.isNode(Name.COMMENT) || node.isNode(Name.UNKNOWN))) {
			        //logger.info("VALUE: "+node.getTextValue());			        
			        bodyMap.put(keyTag, node.getTextValue());
			     }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}   
		return bodyMap;
	}
	public HashMap<String,List<Stock>> getAllStockBySector(){
		SectorKey[] sectorKeyvalues = SectorKey.values();
		return getAllStockBySector(sectorKeyvalues);
	}	
	public HashMap<String,List<Stock>> getAllStockBySector(SectorKey[] sectorKeyvalues){
		
		Integer nSector=41;
		Integer index=100;
		
		String tag[]=new String[index];
		for(int i=0;i<index;i++){
			tag[i]="BODY[0].DIV[0].DIV[1].TABLE[0].TBODY[0].TR["+i+"].TD[0].SPAN[0].A[0].CONTENT[0]";
		}
		
		String link="http://www.fundamentus.com.br/resultado.php?setor="; //41
		HashMap<String,List<Stock>> AllStockBySector=new HashMap<String,List<Stock>>();
		
		ArrayList list;
		for(int i=0;i<sectorKeyvalues.length;i++){
			
			String sectorName=sectorKeyvalues[i].name();
			
			String tmpLink=link+(sectorKeyvalues[i].getIndex());
			HashMap content = getContent(tmpLink, tag);			
			Collection values = content.values();
			
			list=new ArrayList();
			for (Iterator iterator = values.iterator(); iterator.hasNext();) {
				String code = (String) iterator.next();				
				HashMap data = getData(code);
				
				list.add(new Stock(code,data));
				
				//String Setor = (String)data.get(FundamentusKey.Setor.toString());
				//System.out.println(Setor.replaceAll(" ","_")+"("+i+"),");
			}
			
			AllStockBySector.put(sectorName, list);
		}
		return AllStockBySector;
	}
	
	 
}
