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
import org.eclipse.swt.widgets.Combo;

public class StockConfigurationBySector extends PanelCompositeBase {
	private List stockListBySector;
	private StockConfigurationBySectorAction action;
	private List stockListAdded;
	private Combo comboSector;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public StockConfigurationBySector(Composite arg0) {
		super(arg0, SWT.NONE,
				UIMessages.getMessage("PANEL_STOCK_CONFIGURATION_BY_SECTOR")
				);		
	
		setLayout(new FormLayout());
		
		Composite composite = new Composite(this, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.bottom = new FormAttachment(0, 245);
		fd_composite.right = new FormAttachment(0, 440);
		composite.setLayoutData(fd_composite);
		
		stockListBySector=new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		stockListBySector.setBounds(10, 58, 131, 139);
		
		Button button = new Button(composite, SWT.NONE);
		button.setToolTipText("Adicionar a\u00E7\u00E3o a lista");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addSector();
			}
		});
		button.setBounds(147, 10, 30, 21);
		button.setText("+");
		
		Label lblDispi = new Label(composite, SWT.NONE);
		lblDispi.setBounds(10, 37, 92, 15);
		lblDispi.setText("A\u00E7\u00F5es do Setor");
		
		Button btnSalvarEmDisco = new Button(composite, SWT.NONE);
		btnSalvarEmDisco.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveInDisk();
			}
		});
		btnSalvarEmDisco.setBounds(10, 203, 131, 25);
		btnSalvarEmDisco.setText("Salvar em disco");
		
		setComboSector(new Combo(composite, SWT.READ_ONLY));
		getComboSector().setBounds(10, 10, 131, 23);
		
		stockListAdded = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		stockListAdded.setBounds(192, 58, 131, 139);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				combineStock();
			}
		});
		button_1.setBounds(147, 102, 37, 25);
		button_1.setText(">>");
		
		Label lblAesExistentes = new Label(composite, SWT.NONE);
		lblAesExistentes.setText("A\u00E7\u00F5es ja acionadas");
		lblAesExistentes.setBounds(192, 37, 131, 15);
		
		Label lblesseProcessoPode = new Label(composite, SWT.NONE);
		lblesseProcessoPode.setText("(Esse processo pode demorar um pouco)");
		lblesseProcessoPode.setBounds(183, 10, 237, 15);

		action = new StockConfigurationBySectorAction(this);

	}

	protected void combineStock() {
        //String[] items = getStockListBySector().getSelection();
        int[] index = getStockListBySector().getSelectionIndices();

		boolean ok=false;
		if(getStockListBySector().getSelectionCount()>0 && (index!=null && index.length>0)){
			
			ok = action.combineStock(index);			
		}
		
		if(!ok){
			MessageDialog message=new MessageDialog(this.getShell());
			message.open();
		}
	}

	protected void saveInDisk() {
		action.saveInDisk();
	}

	protected void addSector() {
		
		boolean ok=false;
		if(getComboSector().getSelectionIndex()>=0){
			ok = action.addSector(getComboSector().getSelectionIndex());			
		}
		
		if(!ok){
			MessageDialog message=new MessageDialog(this.getShell());
			message.open();
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public StockConfigurationBySector rebuild(Composite parent){
		return new StockConfigurationBySector(parent);
	}

	public void setStockListBySector(List stockListBySector) {
		this.stockListBySector = stockListBySector;
	}

	public List getStockListBySector() {
		return stockListBySector;
	}

	public void setStockListAdded(List stockListAdded) {
		this.stockListAdded = stockListAdded;
	}

	public List getStockListAdded() {
		return stockListAdded;
	}

	public void setComboSector(Combo comboSector) {
		this.comboSector = comboSector;
	}

	public Combo getComboSector() {
		return comboSector;
	}

	
	
}
