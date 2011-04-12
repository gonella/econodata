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

public class ColumnConfiguration extends PanelCompositeBase {
	private List listSelectColumn;
	private ColumnConfigurationAction action;
	private List listAvailableColumn;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ColumnConfiguration(Composite arg0) {
		super(arg0, SWT.NONE,
				UIMessages.getMessage("PANEL_STOCK_COLUMN")
				);		
	
		setLayout(new FormLayout());
		
		Composite composite = new Composite(this, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.bottom = new FormAttachment(0, 254);
		fd_composite.right = new FormAttachment(0, 440);
		composite.setLayoutData(fd_composite);
		
		setListSelectColumn(new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL));
		getListSelectColumn().setBounds(188, 34, 131, 163);
		
		Label lblDispi = new Label(composite, SWT.NONE);
		lblDispi.setBounds(10, 13, 131, 15);
		lblDispi.setText("Colunas Disponiveis");
		
		Button btnSalvarEmDisco = new Button(composite, SWT.NONE);
		btnSalvarEmDisco.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveInDisk();
			}
		});
		btnSalvarEmDisco.setBounds(10, 209, 131, 25);
		btnSalvarEmDisco.setText("Salvar");
		
		setListAvailableColumn(new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL));
		getListAvailableColumn().setBounds(10, 34, 131, 163);
		
		Label lblDisponiveis = new Label(composite, SWT.NONE);
		lblDisponiveis.setText("Colunas Apresentadas");
		lblDisponiveis.setBounds(188, 13, 131, 15);
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				action.removeColumn(getListSelectColumn().getSelectionIndex());
				
			}			
		});
		button.setBounds(144, 75, 38, 25);
		button.setText("<<");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				action.addColumn(getListAvailableColumn().getSelectionIndex());
				
			}
		});
		button_1.setText(">>");
		button_1.setBounds(144, 106, 38, 25);
		
		Button btnRestaurar = new Button(composite, SWT.NONE);
		btnRestaurar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				action.reset();
			}
		});
		btnRestaurar.setBounds(188, 209, 131, 25);
		btnRestaurar.setText("Restaurar Original");

		action = new ColumnConfigurationAction(this);

	}

	protected void saveInDisk() {
		action.saveInDisk();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ColumnConfiguration rebuild(Composite parent){
		return new ColumnConfiguration(parent);
	}

	public void setListSelectColumn(List listSelectColumn) {
		this.listSelectColumn = listSelectColumn;
	}

	public List getListSelectColumn() {
		return listSelectColumn;
	}

	public void setListAvailableColumn(List listAvailableColumn) {
		this.listAvailableColumn = listAvailableColumn;
	}

	public List getListAvailableColumn() {
		return listAvailableColumn;
	}

	
	
}
