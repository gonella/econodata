package com.econodata.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.vietspider.common.util.Worker;
import org.vietspider.ui.widget.waiter.WaitLoading;

import swing2swt.layout.BorderLayout;

import com.econodata.handler.IOData;
import com.econodata.handler.ReaderStock;
import com.econodata.handler.fundamentus.ExtractWebData;
import com.econodata.model.Database;
import com.econodata.model.FundamentusKey;
import com.econodata.ui.common.MessageDialog;
import com.econodata.ui.common.TwButtonText;
import com.econodata.ui.config.ChartConfigDialog;
import com.econodata.ui.config.ConfigurationUIDialog;
import com.econodata.ui.resources.UIMessages;
import com.swtdesigner.SWTResourceManager;

public class EconoMain {

	public static Database database=new Database();
	
	private static final String COLUMN_1 = "ID";
	private static final int COLUMN_SIZE = 100;
	private static final String STOCK_PROPERTIES = "stock.properties";
	public static final String DATA_STOCKCONTENT_DAT = "data/stockcontent.dat";
	private final static Logger logger = Logger.getLogger(EconoMain.class);
	protected Shell shell;
	private Table table;

	private TwButtonText buttonConfiguration;
	private TwButtonText buttonChart;
	private TwButtonText buttonUpdate;
	
	
	private int iColumn;

	private Composite composite_table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EconoMain window = new EconoMain();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(1000, 600);
		shell.setText("EconoData");
		shell.setLayout(new BorderLayout(0, 0));
		//shell.setMaximized(true);
		
		//put in the middle of screen
		Monitor primary = Display.getDefault().getPrimaryMonitor();
		Rectangle bounds = primary.getBounds ();
		Rectangle rect = shell.getBounds ();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation (x, y);
		
		shell.addListener(SWT.Close, new Listener() {
		      public void handleEvent(Event event) {
		    	  logger.info("Existing.");
		    	  System.exit(0);
		      }
		    });
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new BorderLayout(0, 0));
		
		composite_table = new Composite(shell, SWT.NONE);
		composite_table.setLayoutData(BorderLayout.CENTER);
		composite_table.setLayout(new FillLayout(SWT.HORIZONTAL));
	   		
		int colorText = SWT.COLOR_BLACK;

		
		{
			Canvas canvas = new Canvas(shell, SWT.NONE);
			canvas.setLayout(new BorderLayout(0, 0));
			canvas.setLayoutData(BorderLayout.NORTH);
			canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			canvas.setBackgroundImage(SWTResourceManager.getImage(EconoMain.class, "/com/econodata/ui/resources/images/econodata_panel.png"));
			canvas.setBackgroundMode(SWT.INHERIT_DEFAULT);
			
			{
				Canvas canvas_1 = new Canvas(canvas, SWT.NONE);
				canvas_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
				canvas_1.setLayout(null);
				
				canvas_1.setLayoutData(BorderLayout.CENTER);
				{
					
					buttonConfiguration = new TwButtonText(canvas_1, SWT.NONE)
					{
						@Override
						public void buttonClick() {
							configuration();
						}
					};
					
					buttonConfiguration.setButtonTextColor(SWTResourceManager.getColor(colorText));
					buttonConfiguration.setBounds(10, 10, 103, 83);
					buttonConfiguration.setButtonImage(SWTResourceManager.getImage(EconoMain.class, "/com/econodata/ui/resources/images/Config02_48x48.png"));
					buttonConfiguration.setButtonText(UIMessages.getMessage("PANEL_CONFIGURATION"));
					
					buttonChart = new TwButtonText(canvas_1, SWT.NONE)
					{
						@Override
						public void buttonClick() {
							executeChart();						
						}
					};
					buttonChart.setButtonTextColor(SWTResourceManager.getColor(colorText));
					buttonChart.setBounds(119, 10, 103, 83);
					buttonChart.setButtonImage(SWTResourceManager.getImage(EconoMain.class, "/com/econodata/ui/resources/images/Chart02_48x48.png"));
					buttonChart.setButtonText(UIMessages.getMessage("PANEL_CHART"));
					
				
					buttonUpdate = new TwButtonText(canvas_1, SWT.NONE){
						@Override
						public void buttonClick() {
							updateWithFundamentus();						
						}
						};
						
						//buttonConfig.setButtonTextColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
						buttonUpdate.setButtonTextColor(SWTResourceManager.getColor(colorText));
						buttonUpdate.setBounds(228, 10, 103, 83);
						buttonUpdate.setButtonText(UIMessages.getMessage("PANEL_UPDATE"));
						buttonUpdate.setButtonImage(SWTResourceManager.getImage(EconoMain.class, "/com/econodata/ui/resources/images/Update02_48x48.png"));
				}
				
				
				
				
				
			}
						
		}
		
		init();
		
	}

	
	protected void executeChart() {
		
		stockSelected = new ArrayList<String>();
		for(int i=0;i<checkboxLists.length;i++)
		{
			Button checkbox=checkboxLists[i];			
			if(checkbox.getSelection()){
				stockSelected.add((String)checkbox.getData());
			}
		}
		
		if(!(stockSelected.size()>0)){
			MessageDialog message=new MessageDialog(shell);
			message.open();
			return;
		}
		
		new ChartConfigDialog(shell).open();
		
		
		
		 Worker excutor = new Worker() {
			  boolean ok=false;
		   
		      public void abort() {
		    	  ok=true;
		      }

		      public void before() {
		        
		      }

		      public void execute() {
		    	  
		    	  try {
		    		  int index=0;
		    		  while(index < 5 && !ok){
		    			  logger.info("doExecute :"+index);
		    			  Thread.sleep(5000);
		    			  index++;
		    		  }
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		      }
		      
		      public void after() {
		      
		       
		      }
		    };
		    new WaitLoading(shell, excutor).open();
		    
		    		
	}

	protected void configuration() {
		logger.info("Opening Configuration Dialog");
		new ConfigurationUIDialog(shell,this).open();
	}

	private void updateWithFundamentus() {

		File fileToDelete=new File(DATA_STOCKCONTENT_DAT);
		boolean deleted = fileToDelete.delete();
		logger.info("Deleting the cache data:"+DATA_STOCKCONTENT_DAT);
		
		logger.info("Reloading data");
		initData();
		
		logger.info("Refreshing...");
		refresh();
		
	}

	private void init(){
		
		initData();
		
		refresh();
		
	}
	public static final int nColumnExtra=2;

	private Button checkboxLists[];

	public static ArrayList<String> stockSelected;


	private void buildTable() {
		
		

		//table = new Table(composite_table, SWT.BORDER | SWT.FULL_SELECTION);
		table = new Table(composite_table, SWT.FULL_SELECTION | SWT.VIRTUAL | SWT.MULTI);		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		//table.setItemCount(data.length);

		
		//ADICIONANDO COLUNAS E LISTENERS
		FundamentusKey[] values = new FundamentusKey[EconoMain.database.getListSelectColumn().size()]; 
		EconoMain.database.getListSelectColumn().toArray(values);	
		
		final TableColumn tableColumn[]=new TableColumn[nColumnExtra+values.length];
		
		final String[][] data = buildData();
		Listener sortListener = sortTableListener(data, tableColumn);
				
		int iCheckbox=0;
		tableColumn[iCheckbox] = new TableColumn(table, SWT.NONE);
		tableColumn[iCheckbox].setWidth(50);
		//tableColumn[iCheckbox].setText(COLUMN_1);
    	tableColumn[iCheckbox].setData(0);
    	tableColumn[iCheckbox].setAlignment(SWT.CENTER);

    	int iId=1;
		tableColumn[iId] = new TableColumn(table, SWT.NONE);
		tableColumn[iId].setWidth(50);
		tableColumn[iId].setText(COLUMN_1);
    	tableColumn[iId].setData(1);
    	tableColumn[iId].setAlignment(SWT.CENTER);
    	tableColumn[iId].addListener(SWT.Selection, sortListener);

		iColumn = 0;
	    for (iColumn = 0; iColumn < values.length; iColumn++) {
	    	
	    	tableColumn[iColumn+nColumnExtra] = new TableColumn(table,SWT.NONE);
	    	tableColumn[iColumn+nColumnExtra].setText(values[iColumn].convert());
	    	tableColumn[iColumn+nColumnExtra].setWidth(COLUMN_SIZE);
	    	tableColumn[iColumn+nColumnExtra].setData(iColumn+nColumnExtra);	    	
	    	tableColumn[iColumn+nColumnExtra].setAlignment(SWT.RIGHT);
	    	tableColumn[iColumn+nColumnExtra].addListener(SWT.Selection, sortListener);

		}
	    table.setSortColumn(tableColumn[1]);
	    table.setSortDirection(SWT.UP);
	    
	      
	    //ADICIONANDO CONTEUDO DAS LINHAS
	    /////////////////////////////////
	   /* table.addListener(SWT.SetData, new Listener() {
			public void handleEvent(Event e) {
				TableItem item = (TableItem) e.item;
				int index = table.indexOf(item);
				String[] datum = data[index];
				item.setText(datum);
				}
		});*/
	    TableItem tableItem;
	    for (int i=0; i<data.length; i++) {
			tableItem = new TableItem (table, SWT.NONE);
			tableItem.setText(data[i]);
			
		}
	    
	    TableItem [] items = table.getItems ();
	    TableEditor editor;
	    checkboxLists = new Button[items.length];
		for (int i=0; i<items.length; i++) {
			
			editor = new TableEditor (table);
			checkboxLists[i] = new Button (table, SWT.CHECK);
			checkboxLists[i].pack ();
			editor.minimumWidth = checkboxLists[i].getSize ().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor (checkboxLists[i], items[i], 0);
			
			//ESSA COLUNA vai ser removida da lista de configurações para o usuario nao tirar manualmente
			checkboxLists[i].setData(data[i][nColumnExtra]);
						
		}
		
		table.addListener(SWT.MouseDown, new Listener() {
		        public void handleEvent(Event event) {
		          Point pt = new Point(event.x, event.y);
		          TableItem item = table.getItem(pt);
		          if (item == null)
		            return;
		          for (int i = 0; i < tableColumn.length; i++) {
		            Rectangle rect = item.getBounds(i);
		            if (rect.contains(pt)) {
		              int index = table.indexOf(item);
		              logger.info("Item " + index + "-" + i);
		            }
		          }
		        }
		      });
	}

	
	private String[][] buildData() {
		
		int index=0;
		Set<String> keySet = database.getDataMap().keySet();
		
		FundamentusKey[] values = new FundamentusKey[EconoMain.database.getListSelectColumn().size()]; 
		EconoMain.database.getListSelectColumn().toArray(values);

		String columnValues[][]=new String[keySet.size()][nColumnExtra+values.length];

		int row=0;
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String code = (String) iterator.next();
			HashMap hashMap = database.getDataMap().get(code).getDataMap();
			
			columnValues[row][1]=""+row;
			for (int col = 0; col < values.length; col++) {
				columnValues[row][col+nColumnExtra]=hashMap.get(values[col].getCode()).toString();
			}
		    row++;
		}
		return columnValues;
	}

	private void initData() {
		
		if(!IOData.isExist(DATA_STOCKCONTENT_DAT)){
			
			//First time
			database.setDataMap(new ExtractWebData().getDataFromStockList(ReaderStock.reader(STOCK_PROPERTIES)));
			database.setListSelectColumn(new ArrayList(Arrays.asList(FundamentusKey.values())));
			
			logger.info("List Stock Retrieved From Fundamentus :"+database.getDataMap().size());
			
			IOData.writeData(database, DATA_STOCKCONTENT_DAT);
			logger.info("Stock Data Saved in Disk :"+database.getDataMap().size());
		}
		else {
			database = IOData.readData(DATA_STOCKCONTENT_DAT);
			logger.info("List Stock Retrieved From "+DATA_STOCKCONTENT_DAT+": "+database.getDataMap().size());
		}
	}
	
	public void refresh(){
		

		buildTable();
	}
	
	private Listener sortTableListener(final String[][] data,
			final TableColumn[] tableColumn) {
		// Add sort indicator and sort data when column selected
		Listener sortListener = new Listener() {
			public void handleEvent(Event e) {
				// determine new sort column and direction
				TableColumn sortColumn = table.getSortColumn();
				TableColumn currentColumn = (TableColumn) e.widget;
				int dir = table.getSortDirection();
				if (sortColumn == currentColumn) {
					dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
				} else {
					table.setSortColumn(currentColumn);
					dir = SWT.UP;
				}
				// sort the data based on column and direction
				final int index = new Integer(currentColumn.getData().toString());//currentColumn == tableColumn[0] ? 0 : 1;
				final int direction = dir;
				Arrays.sort(data, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						String a = ((String[]) arg0)[index];
						String b = ((String[]) arg1)[index];
						
						try{
							Double aDouble = Double.parseDouble(a);
							Double bDouble = Double.parseDouble(b);
							
							if (aDouble==bDouble) return 0;
							if (direction == SWT.UP) {
								return aDouble < bDouble ? -1 : 1;
							}
							return aDouble < aDouble ? 1 : -1;
							
						}catch(Exception e){
							//logger.info("not number");
						
							if (a.equals(b)) return 0;
							if (direction == SWT.UP) {
								return a.compareTo(b);// ? -1 : 1;
							}
							return a.compareTo(b);// ? 1 : -1;
						}
																		
						
					}
				});
				// update data displayed in table
				table.setSortDirection(dir);
				table.clearAll();
			}
		};
		return sortListener;
	}
}
