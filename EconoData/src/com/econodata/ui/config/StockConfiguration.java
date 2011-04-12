package com.econodata.ui.config;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import com.econodata.ui.common.MessageDialog;
import com.econodata.ui.common.PanelCompositeBase;
import com.econodata.ui.resources.UIMessages;

public class StockConfiguration extends PanelCompositeBase {
	private Text text;
	private List stockList;
	private StockConfigurationAction action;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public StockConfiguration(Composite arg0) {
		super(arg0, SWT.NONE,
				UIMessages.getMessage("PANEL_STOCK_CONFIGURATION")
				);		
	
		setLayout(new FormLayout());
		
		Composite composite = new Composite(this, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.bottom = new FormAttachment(0, 245);
		fd_composite.right = new FormAttachment(0, 199);
		composite.setLayoutData(fd_composite);
		
		setStockList(new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL));
		getStockList().setBounds(10, 58, 131, 139);
		
		setText(new Text(composite, SWT.BORDER));
		getText().setBounds(10, 10, 131, 21);
		
		Button button = new Button(composite, SWT.NONE);
		button.setToolTipText("Adicionar a\u00E7\u00E3o a lista");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addStock();
			}
		});
		button.setBounds(147, 10, 30, 21);
		button.setText("+");
		
		Label lblDispi = new Label(composite, SWT.NONE);
		lblDispi.setBounds(10, 37, 92, 15);
		lblDispi.setText("A\u00E7\u00F5es listadas");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeStock();
			}
		});
		button_1.setToolTipText("Remove A\u00E7\u00E3o");
		button_1.setText("-");
		button_1.setBounds(147, 59, 30, 21);
		
		Button btnSalvarEmDisco = new Button(composite, SWT.NONE);
		btnSalvarEmDisco.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveInDisk();
			}
		});
		btnSalvarEmDisco.setBounds(10, 203, 131, 25);
		btnSalvarEmDisco.setText("Salvar em disco");

		action = new StockConfigurationAction(this);

	}

	protected void saveInDisk() {
		action.saveInDisk();
	}

	protected void removeStock() {
		boolean ok=false;
		if(getStockList().getSelectionIndex()>=0){
		
			String code = getStockList().getItem(getStockList().getSelectionIndex());		
			ok = action.removeStock(code);			
		}
		if(!ok){
			MessageDialog message=new MessageDialog(this.getShell());
			message.open();
		}
		
	}

	protected void addStock() {
		
		boolean ok = action.addStock(getText().getText());
		
		if(!ok){
			MessageDialog message=new MessageDialog(this.getShell());
			message.open();
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public StockConfiguration rebuild(Composite parent){
		return new StockConfiguration(parent);
	}

	public void setStockList(List stockList) {
		this.stockList = stockList;
	}

	public List getStockList() {
		return stockList;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Text getText() {
		return text;
	}
	
}
