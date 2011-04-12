package com.econodata.main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.vietspider.common.util.Worker;
import org.vietspider.ui.widget.waiter.WaitLoading;

import swing2swt.layout.BorderLayout;

public class AppMain {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppMain window = new AppMain();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new BorderLayout(0, 0));
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				
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
		});
		button.setLayoutData(BorderLayout.NORTH);
		button.setText("New Button");

	}

}
